import{r as t,d as i,h as s,c as a}from"./p-38dcedc4.js";import{g as e,c as o}from"./p-202e7480.js";import{g as n}from"./p-8f72be08.js";import{a as h,d as r}from"./p-285477f2.js";import{t as c}from"./p-a5c39d62.js";import"./p-5a5a5c96.js";const l=class{constructor(s){t(this,s),this.ionNavWillLoad=i(this,"ionNavWillLoad",7),this.ionNavWillChange=i(this,"ionNavWillChange",3),this.ionNavDidChange=i(this,"ionNavDidChange",3),this.animationEnabled=!0,this.mode=e(this),this.animated=!0}swipeHandlerChanged(){this.gesture&&this.gesture.enable(void 0!==this.swipeHandler)}async connectedCallback(){this.gesture=(await import("./p-7d99f5f4.js")).createSwipeBackGesture(this.el,(()=>!!this.swipeHandler&&this.swipeHandler.canStart()&&this.animationEnabled),(()=>this.swipeHandler&&this.swipeHandler.onStart()),(t=>this.ani&&this.ani.progressStep(t)),((t,i,s)=>{if(this.ani){this.animationEnabled=!1,this.ani.onFinish((()=>{this.animationEnabled=!0,this.swipeHandler&&this.swipeHandler.onEnd(t)}),{oneTimeCallback:!0});let a=t?-.001:.001;t?a+=n([0,0],[.32,.72],[0,1],[1,1],i)[0]:(this.ani.easing("cubic-bezier(1, 0, 0.68, 0.28)"),a+=n([0,0],[1,0],[.68,.28],[1,1],i)[0]),this.ani.progressEnd(t?1:0,a,s)}})),this.swipeHandlerChanged()}componentWillLoad(){this.ionNavWillLoad.emit()}disconnectedCallback(){this.gesture&&(this.gesture.destroy(),this.gesture=void 0)}async commit(t,i,s){const a=await this.lock();let e=!1;try{e=await this.transition(t,i,s)}catch(t){console.error(t)}return a(),e}async setRouteId(t,i,s,a){return{changed:await this.setRoot(t,i,{duration:"root"===s?0:void 0,direction:"back"===s?"back":"forward",animationBuilder:a}),element:this.activeEl}}async getRouteId(){const t=this.activeEl;return t?{id:t.tagName,element:t}:void 0}async setRoot(t,i,s){if(this.activeComponent===t)return!1;const a=this.activeEl,e=await h(this.delegate,this.el,t,["ion-page","ion-page-invisible"],i);return this.activeComponent=t,this.activeEl=e,await this.commit(e,a,s),await r(this.delegate,a),!0}async transition(t,i,s={}){if(i===t)return!1;this.ionNavWillChange.emit();const{el:a,mode:e}=this,n=this.animated&&o.getBoolean("animated",!0),h=this.animation||s.animationBuilder||o.get("navAnimation");return await c(Object.assign(Object.assign({mode:e,animated:n,enteringEl:t,leavingEl:i,baseEl:a,progressCallback:s.progressAnimation?t=>this.ani=t:void 0},s),{animationBuilder:h})),this.ionNavDidChange.emit(),!0}async lock(){const t=this.waitPromise;let i;return this.waitPromise=new Promise((t=>i=t)),void 0!==t&&await t,i}render(){return s("slot",null)}get el(){return a(this)}static get watchers(){return{swipeHandler:["swipeHandlerChanged"]}}};l.style=":host{left:0;right:0;top:0;bottom:0;position:absolute;contain:layout size style;overflow:hidden;z-index:0}";export{l as ion_router_outlet}