import{e as o,w as t}from"./p-38dcedc4.js";import{c as s}from"./p-5a5a5c96.js";const n=()=>{const n=window;n.addEventListener("statusTap",(()=>{o((()=>{const o=document.elementFromPoint(n.innerWidth/2,n.innerHeight/2);if(!o)return;const c=o.closest("ion-content");c&&new Promise((o=>s(c,o))).then((()=>{t((()=>c.scrollToTop(300)))}))}))}))};export{n as startStatusTap}