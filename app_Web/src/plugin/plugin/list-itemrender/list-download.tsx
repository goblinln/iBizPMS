
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppListBase } from 'ibiz-vue/src/components/control/app-common-control/app-list-base';
import { Environment } from '@/environments/environment';
import axios from 'axios';

import '../plugin-style.less';

/**
 * 文件列表下载绘制插件插件类
 *
 * @export
 * @class ListDownload
 * @class ListDownload
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class ListDownload extends AppListBase {

!!!!模版产生代码错误:!!!!模版产生代码错误:----
Tip: If the failing expression is known to be legally refer to something that's sometimes null or missing, either specify a default value like myOptionalVar!myDefault, or use <#if myOptionalVar??>when-present<#else>when-missing</#if>. (These only cover the last step of the expression; to cover the whole expression, use parenthesis: (myOptionalVar.foo)!myDefault, (myOptionalVar.foo)??
----

----
FTL stack trace ("~" means nesting-related):
	- Failed at: ${Environment.ExportFile}  [in template "TEMPLCODE_zh_CN" at line 60, column 22]
----

}

