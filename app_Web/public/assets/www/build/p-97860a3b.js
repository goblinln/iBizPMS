import{m as s,a}from"./p-c3d35135.js";import{G as e,c as o,n as t,a as n,M as i}from"./p-bcf82055.js";class p{static async objModel(a){const e={item:a},o=await s.create({component:"app-model-main",componentProps:e});await o.present()}static async arrModel(a){const e={item:a},o=await s.create({component:"app-model-list",componentProps:e});await o.present()}}const c=new class{constructor(){this.store=e.getInstance()}get appLoaded(){return this.store.data.appLoaded}async openDesignView(s){if(this.openDesignCallback){s.isFill||await s.fill();const e=o(s);if(t(e)){const a=s.getPSModelService(),t=n(s),i=o(t);this.openDesignCallback({dynaInstId:a.app.M.getPSDynaInstId,previewModelType:i,previewModel:t.M,modelType:e,model:s.M})}else a.create({header:"抱歉",subHeader:"无法打开设计视图!",message:"当前模型尚未支持打开设计视图，敬请期待.",buttons:["确认"]}).then((s=>s.present()))}}setConfig(s,a,e,o){const t=this.store.data;t.modelService=s,t.model=a,t.context=e,t.params=o}async openView(s){s&&s.instanceof?s.instanceof("app.view.IPSAppView")&&(s.isFill||await s.fill(),p.objModel(new i(s))):console.warn("给予模型类型不正确，请检查!",s)}};export{p as O,c as a}