
import {Loader} from "./components/Loader.js";
import { Header } from "./components/Header.js";
import { Main } from "./components/Main.js";
import { Router } from "./components/Router.js";
import { Footer } from "./components/Footer.js";
import { FooterPage } from "./components/FooterPage.js";
import { FooterPageButton } from "./components/FooterPageButton.js";

export function App(){

  const visorSize = 2,
           cacheSize = visorSize * 2;
        

  if(!localStorage.getItem("visorSize")) {
   localStorage.setItem("visorSize",visorSize);
  }
localStorage.setItem("visorSize",visorSize);
localStorage.setItem("activePage",1);
localStorage.setItem("cacheSize", cacheSize);



 const  $root = document.getElementById("root");
  $root.innerHTML = null;
  Header($root);
  $root.appendChild(Main());
  $root.appendChild(Loader());
  //$root.appendChild(FooterPage());
  
  new Promise((resolve,reject)=>{
    alert("Entra en Footer")
    resolve(FooterPageButton())
  }).then(async ()=>{
    alert("Entra en el ajax")
    try {
      await new Promise((resolve, reject) => {
        ajax({
          url: api.API_HARNINA,
          cbSuccess: (posts_1) => {
            alert("Entra en router");
            resolve(Router())
          }
        });
      });
    } catch (e) {
      return console.log(e);
    }
    
   // Router(); Hay que arreglarlo o deberiamos



}
