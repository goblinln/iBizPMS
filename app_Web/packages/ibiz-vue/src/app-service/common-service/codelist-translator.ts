import { SingletonMode } from '../../decorators/SingletonMode';
import { AppServiceBase, LayoutState, Util } from 'ibiz-core';
import { CodeListService } from 'ibiz-service';

/**
 * 代码表翻译器
 *
 * @export
 * @class CodeListTranslator
 */
@SingletonMode()
export class CodeListTranslator {
    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof CodeList
     */  
    private codeListService!:CodeListService;

    /**
     * Creates an instance of CodeListTranslator.
     * @memberof CodeListTranslator
     */
    constructor() {
        let $store = AppServiceBase.getInstance().getAppStore();
        this.codeListService = new CodeListService({ $store: $store });
    }

    /**
     * 文本分隔符
     * @type {boolean}
     * @memberof CodeList
     */
    private textSeparator: string = '、';

    /**
     * 值分隔符
     * @type {boolean}
     * @memberof CodeList
     */
    private valueSeparator: string = ',';

    /**
     * 获得封装的codelist对象
     *
     * @param {*} codeList 代码表模型对象
     * @param {*} context 上下文
     * @param {*} [viewparams] 视图参数
     * @param {boolean} [isLoading=false] 是否显示加载信息
     * @returns
     * @memberof CodeListTranslator
     */
    private getCodeListObj(codeList: any, context: any, viewparams?: any, isLoading: boolean = false){
        return {
            tag: codeList.codeName,
            type: codeList.codeListType,
            context: context,
            viewparam: viewparams,
            isloading: isLoading
        }
    }

    /**
     * 获取代码表文本
     *
     * @param {*} value 传入的value值
     * @param {*} codeList 代码表对象
     * @param {*} _this 上下文索引
     * @returns
     * @memberof CodeListTranslator
     */
    public async getCodeListText(value: any, codeList: any, _this: any, context: any, viewparams?: any, isLoading: boolean = false){
        let selectedItems = await this.getSelectedCodeListItems(value, codeList, _this, context, viewparams, isLoading)
        if(selectedItems.length == 0){
            return this.getEmptyText(codeList,_this);
        }
        let texts: any[] = []
        selectedItems.forEach((item: any)=>{
            if(codeList.codeListType == 'STATIC'){
                texts.push(this.getLocaleText(`codelist.${codeList.codeName}.${item.value}`, _this, item.text));
            }else{
                texts.push(item.text);
                // todo 动态代码表多语言
            }
        })
        return texts.join(this.textSeparator);
    }

    /**
     * 获取选中的代码表项集合
     *
     * @param {*} value 传入的value值
     * @param {*} codeList 代码表的模型对象
     * @param {*} _this 上下文索引
     * @returns
     * @memberof CodeListTranslator
     */
    public async getSelectedCodeListItems(value: any, codeList: any, _this: any, context: any, viewparams?: any, isLoading: boolean = false){
        if(Util.isEmpty(value)){
            return []
        }
        // 获取代码表值项数组
        let codeListItems: any[] = [];
        if(codeList.codeListType == 'STATIC'){
            codeListItems = codeList.getPSCodeItems;
        }else{
            codeListItems = await this.codeListService.getDataItems(this.getCodeListObj(codeList,context,viewparams,isLoading));
        }
        return this.getSelectedItems(value, codeListItems, codeList);
    }

    /**
     * 获取代码表空值
     *
     * @param {*} codeList 代码表模型对象
     * @param {*} _this 上下文索引
     * @memberof CodeListTranslator
     */
    private getEmptyText(codeList: any, _this: any){
        return this.getLocaleText(`codelist.${codeList.codeName}.empty`,_this, codeList.emptyText);
    }


    /**
     * 获取多语言文本
     *
     * @param {string} localeTag 多语言标识
     * @param {*} _this 上下文索引
     * @param {string} defaultText 默认文本，多语言失效时使用
     * @memberof CodeListTranslator
     */
    private getLocaleText(localeTag: string, _this: any, defaultText: string){
        if(!Util.isEmpty(_this.$t) && typeof _this.$t === 'function' && !Util.isEmpty(localeTag)){
            let localText = _this.$t(localeTag);
            if(!Util.isEmpty(localText)){
                return localText;
            }
        }
        return defaultText == '未定义' ? '' : defaultText ;
    }

    /**
     * 解析value值获取选中的项
     *
     * @param {*} value 传入的value值
     * @param {any[]} codeListItems 代码表项集合
     * @param {*} codeList 代码表模型对象
     * @memberof CodeListTranslator
     */
    private getSelectedItems(value: any, codeListItems: any[], codeList: any){
        if(codeListItems){
            let valueSeparator = codeList.valueSeparator || this.valueSeparator;
            // 值的集合
            let values: any[] = [];
            // 选中代码表项的集合
            let selectedItems: any[] = [];
            if(codeList.orMode == 'NUM'){
                codeListItems.forEach((_item: any, index: number)=>{
                    const nValue = parseInt((value as any), 10);
                    if((parseInt(_item.value, 10) & nValue) > 0){
                        selectedItems.push(Util.deepCopy(_item));
                    } 
                });
            }else{
                if(Util.typeOf(value) == 'number'){
                    values = [value]
                }else{
                    values = [...(value as any).split(valueSeparator)];
                }
                values.forEach((v: any)=>{
                    let selected = codeListItems.find((_item:any)=> _item.value == v);
                    if(selected){
                        selectedItems.push(selected);
                    }
                })
            }
            return selectedItems;
        }
        return []
    }

}
