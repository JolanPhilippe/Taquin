/** Resolution avec creation d'un graphe annexe
	 * 
	 * @param gt
	 * @param typeRes 10: parcours en largeur | 20: parcours en profondeur
	 * @throws ValInexistanteException
	 * 
	 * @author Jolan
	 */
	public static void ResTaquinB1 (GrilleTaquin gt, int typeRes, int delai) throws ValInexistanteException{
		Date d1 = new Date();
		boolean overtime = false;
		LinkedList<GrilleTaquin> Marque = new LinkedList<GrilleTaquin> ();
		LinkedList<GrilleTaquin> ATraite = new LinkedList<GrilleTaquin> ();
		GrapheListe<GrilleTaquin> graphe = new GrapheListe <GrilleTaquin>();
		Marque.add(gt);
		boolean test = true;
		do{
			GrilleTaquin tete = Marque.getFirst();
			graphe.ajouterSommet(tete);
			boolean testContains = false;
			for (GrilleTaquin gtTest:ATraite) if(tete.equals(gtTest)) testContains=true;
			if(!testContains){
				for (GrilleTaquin grille : tete.successeur()){
					graphe.ajouterArc(tete, grille);
					if(!graphe.getGraphe().containsKey(grille)) graphe.ajouterSommet(grille);
					if(!ATraite.contains(grille) && !Marque.contains(grille)) Marque.add(grille);
					if (grille.equals(ref)){
						ref=grille;test = false; 
						ATraite.add(0,grille);
					}else{
						Date d2 = new Date();
						long s1 = d1.getTime()/1000;
						long s2 = d2.getTime()/1000;
						if (s2-s1>delai){test = false; overtime=true;}
					}
				}
				switch (typeRes){
					case 1:	ATraite.add(0,tete); break;
					case 2:	ATraite.add(ATraite.size(),tete); break;
					default: throw new ValInexistanteException("Ce mode de resolution est impossible");
				}
			}
			Marque.remove(0);
		}while (test);
		if(overtime){
			System.out.println("DELAI DEPASSE");
		}else{
			switch (typeRes){
				case 1:graphe.parcoursLarg(gt); break;
				case 2:graphe.parcoursProf(); break;
				default: throw new ValInexistanteException("Ce mode de resolution est impossible");
			}
			
			HashMap<GrilleTaquin,GrilleTaquin> lesPeres = graphe.getPeres();
			
			ArrayList<GrilleTaquin> succ = new ArrayList<GrilleTaquin>();
			GrilleTaquin pere = ref;
			while(pere!=null){
				succ.add(0,pere);
				pere = lesPeres.get(pere);
			}
			for(GrilleTaquin x:succ) System.out.println(x);
		}
	}
	
	/** Resolution sans creation 'un graphe annexe
	 * 
	 * @param gt
	 * @param typeRes 1: parcours en largeur | 2: parcours en profondeur | 3: tas par Manhattan | 4: tas prof+Manhattan
	 * @throws ValInexistanteException
	 * 
	 * @author Jolan
	 */
	public static String ResTaquinB2 (GrilleTaquin taquin, int typeRes) throws ValInexistanteException{
		Date d1 = new Date();
		LinkedList<GrilleTaquin> Marque = new LinkedList<GrilleTaquin> ();
		LinkedList<GrilleTaquin> ATraite = new LinkedList<GrilleTaquin> ();
		HashMap<GrilleTaquin,GrilleTaquin> lesPeres = new HashMap<GrilleTaquin,GrilleTaquin>();
		Marque.add(taquin);
		ATraite.add(taquin);
		boolean testContinu = true;
		while (testContinu){
			GrilleTaquin pos = ATraite.getFirst();
			ATraite.remove(0);
			if (pos.equals(ref)) 
				{testContinu=false; ref=pos;}
			else
				for (GrilleTaquin p : pos.successeur()){
					boolean testContains=false;for(GrilleTaquin t: Marque) if (t.equals(p)) testContains=true;
					if(!testContains){
						switch (typeRes){
							case 1:	ATraite.add(0,p); break;
							case 2:	ATraite.add(ATraite.size(),p); break;
							default: throw new ValInexistanteException("Ce mode de resolution est impossible");
						}
						Marque.add(p);
						if(!lesPeres.containsKey(p))
							lesPeres.put(p, pos);
					}
				}
			
		}
		
		ArrayList<GrilleTaquin> succ = new ArrayList<GrilleTaquin>();
		GrilleTaquin pere = ref;
		while(pere!=null){
			succ.add(0,pere);
			pere = lesPeres.get(pere);
		}
		String sol="";
		for (int i=0; i<succ.size()-1;i++){	
			GrilleTaquin ini=succ.get(i);
			GrilleTaquin nxt=succ.get(i+1);
			sol+=(ini.compZero(nxt));
		}
		System.out.println(sol);
		return sol;
	}
	
	/** Resout taquin avec gestion du dernier coup sans graphe annexe
	 * 
	 * @param gt
	 * @param typeRes 1: parcours en largeur | 2: parcours en profondeur 
	 * @throws ValInexistanteException
	 * 
	 * @author Jolan
	 */
	public static String ResTaquinB3 (GrilleTaquin taquin, int typeRes) throws ValInexistanteException{
		Date d1 = new Date();
		GrilleTaquin ref = Game.ref;
		Marque = new LinkedList<GrilleTaquin>();
		switch (typeRes){ //Definition de ATraite en fonction du mode de resolution
		case 1: //pile
			ATraite = new Pile<GrilleTaquin>(); break;
		case 2: //file
			ATraite = new File<GrilleTaquin>(); break;
		}
		HashMap<GrilleTaquin,GrilleTaquin> lesPeres = new HashMap<GrilleTaquin,GrilleTaquin>();
		Marque.add(taquin);
		ATraite.add(taquin);
		CharPred.put(taquin, 'z');
		boolean testContinu = true;
		while (testContinu){
			GrilleTaquin pos = ATraite.extract();
			char c = CharPred.get(pos);
			if (pos.equals(ref)) 
				{testContinu=false; ref=pos;}
			else
				for (GrilleTaquin p : pos.successeur(c).keySet()){
					boolean testContains=false;for(GrilleTaquin t: Marque) if (t.equals(p)) testContains=true;
					if(!testContains){		
						CharPred.put(p, c);
						ATraite.add(p);
						Marque.add(p);
						if(!lesPeres.containsKey(p))
							lesPeres.put(p, pos);
					}
				}
		}
		
		ArrayList<GrilleTaquin> succ = new ArrayList<GrilleTaquin>();
		GrilleTaquin pere = ref;
		while(pere!=null){
			succ.add(0,pere);
			pere = lesPeres.get(pere);
		}
		String sol="";
		for (int i=0; i<succ.size()-1;i++){	
			GrilleTaquin ini=succ.get(i);
			GrilleTaquin nxt=succ.get(i+1);
			sol+=(ini.compZero(nxt));
		}
		System.out.println(sol);
		return sol;
	}