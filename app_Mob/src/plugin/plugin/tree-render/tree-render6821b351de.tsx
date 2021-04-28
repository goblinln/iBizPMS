
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
!!!!模版产生代码错误:!!!!模版产生代码错误:----
Tip: If the failing expression is known to be legally refer to something that's sometimes null or missing, either specify a default value like myOptionalVar!myDefault, or use <#if myOptionalVar??>when-present<#else>when-missing</#if>. (These only cover the last step of the expression; to cover the whole expression, use parenthesis: (myOptionalVar.foo)!myDefault, (myOptionalVar.foo)??
----

----
FTL stack trace ("~" means nesting-related):
	- Failed at: ${this.downloadUrl}  [in template "TEMPLCODE2_zh_CN" at line 46, column 21]
----
import '../plugin-style.less';

/**
 * 移动端树附件插件插件类
 *
 * @export
 * @class TREE_RENDER6821b351de
 * @class TREE_RENDER6821b351de
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class TREE_RENDER6821b351de extends AppControlBase {

        <app-tree-word 
            :treeNav="treeNav" 
            :valueNodes="valueNodes" 
            :rootNodes="rootNodes" 
            @nav_click="nav_click" 
            @node_touch="node_touch" 
            @click_node="click_node" />

}

