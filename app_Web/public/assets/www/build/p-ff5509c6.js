import{a as t}from"./p-dca98503.js";import{n as s,G as n}from"./p-f2477f10.js";class e extends class{constructor(){this.cacheMap=new Map,this.typeData={},this.getTypeData()}async getConfigJson(n){if(n){const e=n;try{const n=e.__proto__.constructor.name;if(this.cacheMap.has(n))return this.cacheMap.get(n);const o=this.typeData[n],c=[];if(s(o))for(let t=0;t<o.length;t++){const n=o[t];if(s(n)){const t=await this.fetchJson(`./assets/json/model-config/${encodeURIComponent(n)}.json`);s(t)&&t.forEach((t=>{const n=c.findIndex((s=>s.group===t.group));if(s(t.methods))if(n>=0){const s=c[n];t.methods.forEach((t=>{s.methods.findIndex((s=>s.name===t.name))>=0?Object.assign(s.methods[n],t):s.methods.push(t)}))}else c.push(t)}))}}return s(c)?(this.cacheMap.set(n,c),c.forEach((s=>{s.methods=t(s.methods,"order")})),t(c,"order")):[]}catch(t){console.log("模型类型计算显示配置失败!",t)}}return null}async getTypeData(){const t=await this.fetchJson("./assets/json/modelints.json");t&&(this.typeData=t)}async fetchJson(t){try{const s=await fetch(t);return await s.json()||[]}catch(t){console.error("获取配置JSON失败!",t)}}}{constructor(){if(e.instance)return e.instance;super()}static getInstance(){return this.instance}}e.instance=new e;const o=e.getInstance();class c{constructor(){if(this.store=n.getInstance(),c.instance)return c.instance}get modelService(){return this.store.data.modelService}static getInstance(){return this.instance}}c.instance=new c;export{c as A,o as m}