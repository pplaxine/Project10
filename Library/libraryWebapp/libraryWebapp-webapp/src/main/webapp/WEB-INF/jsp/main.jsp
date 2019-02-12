<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="_include/head.jsp" %>
		<title>Home</title>
	</head>
	<body>

		<%@ include file="_include/header.jsp" %>
		<div class="bg_main text-light">
			<div id="bg_dark_filter" class="bg_dark_filter text-warning">
				<h2><s:text name="home.welcome"/> </h2>
				<div class="container-fluid h-100">
					<div class="row justify-content-center h-50 align-items-center">
						<div class="col-4 text-center font-weight-bold">
						
							<s:text name="home.search.title"/><br/>
							<input id="search" type="text" autocomplete="off" size="45" />
							<div id="results" class="text-left"></div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- TEST -->
	


	<script type="text/javascript">
		
		(function(){
			var darkFilter = document.getElementById('bg_dark_filter'),
				searchElem = document.getElementById('search');
			
			(function opacityDown() {
				searchElem.addEventListener("focus", function(e){
					console.log("work");
					darkFilter.style.transitionDuration ="2s";  
					darkFilter.style.background = "rgba(0,0,0,0.8)";
				});
			})();
			(function opacityUp() {
				searchElem.addEventListener("focusout", function(e){
					console.log("work");
					darkFilter.style.transitionDuration ="2s";  
					darkFilter.style.background = "rgba(0,0,0,0.2)";
				});
			})();
			
		})();
	
		//IIFE
		(function() {

			var searchElement = document.getElementById('search'),
				background = document.getElementById('bg_dark_filter'),
				bgStyle = getComputedStyle(background),
				result = document.getElementById('results'),
				selectedResult = -1, //le resultat selectionné (-1 = aucun)
				previousRequest,	
				previousValue = searchElement.value; //précédente recherche


			function getResults(keywords){
				// 1)CREATION DE L'OBJET
				var xhr = new XMLHttpRequest();

				// 2)FABRICATION DE LA REQUETE 
				// param1 = methode, param2 = url (avec param ?s=) + utilisation EUC pour eviter caracs interdits
				<!-- xhr.open('GET','http://localhost:8081/libraryWebapp-webapp/'+ encodeURIComponent(keywords)); -->
				xhr.open('GET','http://localhost:8081/libraryWebapp-webapp/getbook?bookDto=' + encodeURIComponent(keywords));

				// 4)RECUPERATION DE LA REPONSE 
				//param1 = le type EL, param2 = function à déclancher quand event survient (changement d'état de la Requête (4 diff))
				xhr.addEventListener('readystatechange', function(){
					//quand l'état de la requête est : DONE (4) et il y a pas d'erreur (status 200 = ok), donc la reponse a été récupérée
					if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){

						//on passe la réponse (en format text car pas de donnée complexes necessitant d'être organisées) à la fonction displayResults
						displayResults(xhr.responseText);
					}
				});

				// 3) ENVOI DE LA REQUETE (Methode GET donc pas de param)
				xhr.send(null);

				//stocké dans previousRequest (permet d'annuler la requete graçe a abort() )
				return xhr;
			}


			function displayResults(response) {
				//si pas de résultat (response.length = 0 revient à false) on cache conteneur 
				results.style.display = response.length && searchElement.value !=""? 'block': 'none';
				
	
				
				if(response.length){
					//tab contenant les valeurs entre les |
					response = response.split('|');
					var responseLen = response.length;

					//on vide les résultats 
					results.innerHTML ='';

					for(var i = 0, div ;i< responseLen; i++){
						// création d'un élément div dans élement <div id="results"> (ps: valeur renvoyée par methode appendChild est l'élément enfant crée) 
						div = results.appendChild(document.createElement('div'));
						//on ajout dans le text de la balise le premier(i) resultat de la tab response
						div.innerHTML = response[i];

						//quand cette div est clickée, on appel la fonction chooseResult en lui passant en param la div (la target de l'event déclancheur)
						div.addEventListener('click', function(e){
							chooseResult(e.target); 
						}); 
					} 
				}	  
			}


			function chooseResult(result){

				//contenu de la div passée en valeur du champ de recherche et enregistrée comme précédente valeur.
				searchElement.value = previousValue = result.innerHTML;

				//Les autres divs de suggestions sont cachées 
				results.style.display = 'none';
				//suppression de l'effet de focus (?????)
				result.className = ''; 
				//On remet la selection à zero (?????)
				selectedResult = -1; 
				//on reattribut le focus au champs search 
				searchElement.focus(); 
			}
			


			//-----------------------------------------------------------------------
			

			
			searchElement.addEventListener('keyup', function(e){

				//recupération dans tab des éléments <div> dans l'élément <div id="results"> (racine) 
				var divs = results.getElementsByTagName('div');

				// Si la touche pressée est flèche haut et qu'un resultat est séléctionné (-1 aucun séléctionné) 1)on supprime le focus sur cette div 2)On decrémente la position de l'objet selectionné  
				if(e.keyCode == 38 && selectedResult > -1){
					divs[selectedResult --].className = ''; 

					if(selectedResult > -1){
						//ajout du focus sur la div selectionnée 
						divs[selectedResult].className ='result_focus';
					}

				// Si la touche pressée est flèche bas et que la div selectionnée est au max l'avant dernière 
				} else if(e.keyCode == 40 && selectedResult < divs.length - 1 ){

					//attention ici élément <div id="results"> on affiche les résultats 
					results.style.display = 'block'; 

					if(selectedResult > -1 ){
						divs[selectedResult].className =''; 
					}

					divs[++selectedResult].className = 'result_focus';

				// Si la touche pressée est entrée 
				} else if (e.keyCode == 13 && selectedResult > -1){

					//On fait appel à la fonction qui affiche la suggestion dans la barre de recherche (elle cache les autre suggestions et re-initialise à 0 et met focus dans search)
					chooseResult(divs[selectedResult]);
				
				//Si le contenu du champs search à changé 
				} else if(searchElement.value != previousValue){
					previousValue = searchElement.value;

					//Si il existe une précedente requête et qu'elle est toujours en cours, on l'arrête  
					if(previousRequest && previousRequest.readyState < XMLHttpRequest.DONE){
						previousRequest.abort(); 
					}

					//appel à la méthode de recherche de suggestion en lui passant en param la valeur actuel dans l'élément search. 
					previousRequest = getResults(previousValue);

					//sélection de suggestion remise à zero à chaque carac écrit
					selectedResult = -1; 
				}





			});

		})();
		
	</script>
		
		<!--  Fin test -->

	</body>
</html>
