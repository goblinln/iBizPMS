import{r as o,h as i,H as s}from"./p-38dcedc4.js";import{g as t}from"./p-202e7480.js";import{s as e}from"./p-c3d35135.js";import{g as r}from"./p-7840618d.js";import"./p-f2660943.js";import"./p-5a5a5c96.js";const n=class{constructor(i){o(this,i),this.options=[]}onSelect(o){const i=this.options.find((i=>i.value===o.target.value));i&&e(i.handler)}render(){const o=this.options.find((o=>o.checked)),e=o?o.value:void 0;return i(s,{class:t(this)},i("ion-list",null,void 0!==this.header&&i("ion-list-header",null,this.header),(void 0!==this.subHeader||void 0!==this.message)&&i("ion-item",null,i("ion-label",{class:"ion-text-wrap"},void 0!==this.subHeader&&i("h3",null,this.subHeader),void 0!==this.message&&i("p",null,this.message))),i("ion-radio-group",{value:e},this.options.map((o=>i("ion-item",{class:r(o.cssClass)},i("ion-label",null,o.text),i("ion-radio",{value:o.value,disabled:o.disabled})))))))}};n.style=".sc-ion-select-popover-h ion-list.sc-ion-select-popover{margin-left:0;margin-right:0;margin-top:-1px;margin-bottom:-1px}.sc-ion-select-popover-h ion-list-header.sc-ion-select-popover,.sc-ion-select-popover-h ion-label.sc-ion-select-popover{margin-left:0;margin-right:0;margin-top:0;margin-bottom:0}";export{n as ion_select_popover}