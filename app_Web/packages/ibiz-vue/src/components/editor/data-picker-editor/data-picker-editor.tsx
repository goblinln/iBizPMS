import { AppModelService, GetModelService, ModelTool, Util } from 'ibiz-core';
import { Component, Prop } from 'vue-property-decorator';
import { EditorBase } from '../editor-base/editor-base';
import { VueLifeCycleProcessing } from '../../../decorators';
import { IPSAppCodeList, IPSAppDEView, IPSAppView, IPSCodeListEditor, IPSDEFormItem, IPSPicker, IPSPickerEditor } from '@ibiz/dynamic-model-api';

/**
 * 自动完成编辑器
 *
 * @export
 * @class DataPickerEditor
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export default class DataPickerEditor extends EditorBase {

    /**
     * 是否忽略表单项值变化
     *
     * @type {boolean}
     * @memberof AppDefaultEditor
     */
    @Prop() public ignorefieldvaluechange?: any

    /**
     * 代码表对象
     *
     * @type {IPSAppCodeList}
     * @memberof DataPickerEditor
     */
    public codeList?: IPSAppCodeList | null;

    /**
     * 模型服务
     *
     * @type {AppModelService}
     * @memberof DataPickerEditor
     */
    public modelService !: AppModelService;

    /**
     * 编辑器初始化
     *
     * @memberof DataPickerEditor
     */
    public async initEditor() {
        await super.initEditor();
        await this.initModelService();
        // 加载链接视图和选择视图
        let pickupAppView: IPSAppView | null = (this.editorInstance as IPSPickerEditor)?.getPickupPSAppView?.();
        let linkAppView: IPSAppView | null = (this.editorInstance as IPSPickerEditor)?.getPickupPSAppView?.();
        await (pickupAppView as any)?.fill(true);
        await (linkAppView as any)?.fill(true);

        this.codeList = (this.editorInstance as IPSCodeListEditor)?.getPSAppCodeList?.();
        const { placeHolder } = this.editorInstance;
        this.customProps.deMajorField = ModelTool.getEditorMajorName(this.editorInstance);
        this.customProps.deKeyField = ModelTool.getEditorKeyName(this.editorInstance);
        switch (this.editorInstance?.editorType) {
            // 数据链接
            case 'PICKEREX_LINKONLY':
                this.customProps.editorType = 'linkonly';
                await this.initLinkViewParams();
                break;
            // 数据选择（无按钮）
            case 'PICKEREX_NOBUTTON':
                this.customProps.showButton = false;
            // 数据选择
            case 'PICKER':
                this.initPickupViewParams();
                this.initAcParams();
                break;
            // 数据选择（无AC、数据链接）
            case 'PICKEREX_NOAC_LINK':
                this.customProps.editorType = 'pickup-no-ac';
                this.initPickupViewParams();
                await this.initLinkViewParams();
                break;
            // 数据选择（下拉、数据链接）
            case 'PICKEREX_TRIGGER_LINK':
                this.initPickupViewParams();
                await this.initLinkViewParams();
                this.initAcParams();
                break;
            // 数据选择（下拉）
            case 'PICKEREX_TRIGGER':
                this.customProps.editorType = 'dropdown';
                this.initAcParams();
                break;
            // 数据选择（无AC）
            case 'PICKEREX_NOAC':
                this.customProps.editorType = 'pickup-no-ac';
                this.initPickupViewParams();
                break;
            // 数据选择（数据链接）
            case 'PICKEREX_LINK':
                this.customProps.editorType = 'linkonly';
                this.initPickupViewParams();
                await this.initLinkViewParams();
                this.initAcParams();
                break;
            // 数据选择（下拉视图）
            case 'PICKEREX_DROPDOWNVIEW':
                this.initPickupViewParams();
                break;
            // 数据选择（下拉视图、数据链接）
            case 'PICKEREX_DROPDOWNVIEW_LINK':
                this.initPickupViewParams();
                await this.initLinkViewParams();
                break;
            // 数据选择（嵌入选择视图）
            case 'PICKUPVIEW':
                this.customProps.formState = this.contextState;
                this.customProps.emptyText = placeHolder;
                this.customProps.viewModelData = pickupAppView || undefined;
                this.customProps.refreshitems = (this.editorInstance.getParentPSModelObject() as IPSDEFormItem).getResetItemNames();
                break;
            // 地址框（选择）
            case 'ADDRESSPICKUP':
                this.initPickupViewParams();
                this.initAcParams();
                break;
            // 地址框（支持选择、AC）
            case 'ADDRESSPICKUP_AC':
                this.initPickupViewParams();
                this.initAcParams();
                break;
        }
    }

    /**
     * 初始化选择视图相关参数
     *
     * @memberof DataPickerEditor
     */
    public initPickupViewParams() {
        let pickupAppView = (this.editorInstance as IPSPickerEditor).getPickupPSAppView();
        if (pickupAppView) {
            const view: any = {
                viewname: 'app-view-shell',
                title: this.$tl(pickupAppView.getCapPSLanguageRes()?.lanResTag, pickupAppView.title),
                width: pickupAppView?.width,
                height: pickupAppView?.height,
                placement: pickupAppView?.openMode,
                isRedirectView: pickupAppView.hasOwnProperty('redirectView') ? pickupAppView.redirectView : false,
                viewpath: pickupAppView?.modelPath
            };
            this.customProps.pickupView = view;
        }
    }

    /**
    * 初始化模型服务
    *
    * @memberof DataPickerEditor
    */
    public async initModelService() {
        this.modelService = await GetModelService(this.context);
    }


    /**
     * 初始化链接视图相关参数
     *
     * @memberof DataPickerEditor
     */
    public async initLinkViewParams() {
        let linkAppView = (this.editorInstance as IPSPicker).getLinkPSAppView();
        await linkAppView?.fill(true);
        if (linkAppView) {
            const view: any = {
                viewname: 'app-view-shell',
                title: this.$tl(linkAppView.getCapPSLanguageRes()?.lanResTag, linkAppView.title),
                width: linkAppView?.width,
                height: linkAppView?.height,
                placement: linkAppView?.openMode,
                deResParameters: Util.formatAppDERSPath(this.context, (linkAppView as IPSAppDEView).getPSAppDERSPaths()),
                isRedirectView: linkAppView.redirectView,
                viewpath: linkAppView?.modelPath
            }
            Object.defineProperty(view, 'viewModel', { enumerable: false, writable: true, value: linkAppView });
            if (linkAppView?.getPSAppDataEntity()) {
                Object.assign(view, {
                    parameters: [
                        { pathName: Util.srfpluralize(linkAppView.getPSAppDataEntity()?.codeName || '').toLowerCase(), parameterName: linkAppView.getPSAppDataEntity()?.codeName.toLowerCase() },
                        { pathName: 'views', parameterName: (linkAppView as IPSAppDEView).getPSDEViewCodeName()?.toLowerCase() },
                    ]
                })
            } else {
                Object.assign(view, {
                    parameters: [
                        { pathName: 'views', parameterName: linkAppView.codeName.toLowerCase() }
                    ]
                })
            }
            this.customProps.linkview = view;
        }
    }

    /**
     * 初始化AC相关参数
     *
     * @memberof DataPickerEditor
     */
    public initAcParams() {
        this.customProps.acParams = ModelTool.getAcParams(this.editorInstance);
        this.customProps.sort = ModelTool.getAcSort(this.editorInstance);
    }

    /**
     * 绘制嵌入视图
     * 
     * @memberof DataPickerEditor
     */
    public renderEmbedPicker() {
        return this.$createElement(this.editorComponentName, {
            props: {
                name: this.editorInstance.name,
                value: this.value,
                disabled: this.disabled,
                ignorefieldvaluechange: this.ignorefieldvaluechange,
                data: JSON.stringify(this.contextData),
                context: this.context,
                viewparams: this.viewparams,
                valueItem: this.parentItem?.valueItemName || '',
                ...this.customProps,
            },
            on: { formitemvaluechange: this.editorChange },
            style: this.customStyle
        })
    }

    /**
     * 默认绘制
     * 
     * @memberof DataPickerEditor
     */
    public renderDefault() {
        return this.$createElement(this.editorComponentName, {
            props: {
                parentCodeName: this.parentItem?.codeName,
                name: this.editorInstance.name,
                value: this.value,
                disabled: this.disabled,
                data: this.contextData,
                context: this.context,
                viewparams: this.viewparams,
                service: this.service,
                pickUpData: this.editorInstance.editorParams?.['pickupdata'] ? this.editorInstance.editorParams?.['pickupdata'] : null,
                valueitem: this.parentItem?.valueItemName || '',
                ...this.customProps,
            },
            on: { formitemvaluechange: this.editorChange },
            style: this.customStyle
        })
    }

    /**
     * 数据选择插件（地图定位）
     * 
     * @memberof DataPickerEditor
     */
    public renderMapPosition() {
        return this.$createElement(this.editorComponentName, {
            props: {
                name: this.editorInstance.name,
                value: this.value,
                disabled: this.disabled,
                data: this.contextData,
                context: this.context,
                viewparams: this.viewparams,
                formState: this.contextState,
                ...this.customProps
            },
            on: {
                change: (value: any) => { this.editorChange({ name: this.editorInstance.name, value: value }) },
                itemChange: (val1: any, val2: any) => { this.editorChange(val1); this.editorChange(val2); }
            }
        })
    }

    /**
     * 单位选择器
     * 
     * @memberof DataPickerEditor
     */
    public renderOrgSelect() {
        let params: any = this.initSelectPickerParams();
        Object.assign(params, {
            filter: this.editorInstance.editorParams?.['filter'] ? this.editorInstance.editorParams['filter'] : 'srforgid',
        });
        return this.$createElement(this.editorComponentName, {
            props: params,
            on: {
                "select-change": this.editorChange.bind(this),
            }
        })

    }

    /**
     * 部门人员选择器
     * 
     * @memberof DataPickerEditor
     */
    public renderDepartmentPersonnel() {
        let params: any = this.initSelectPickerParams();
        Object.assign(params, {
            filter: this.editorInstance.editorParams?.['filter'] ? this.editorInstance.editorParams['filter'] : 'srfpdept',
        });
        if (this.editorInstance.editorParams?.['treeurl']) {
            Object.assign(params, {
                treeurl: this.editorInstance.editorParams['treeurl'],
            })
        }
        return this.$createElement(this.editorComponentName, {
            props: params,
            on: {
                formitemvaluechange: this.editorChange.bind(this)
            }
        })
    }

    /**
     * 单位人员选择器
     * 
     * @memberof DataPickerEditor
     */
    public renderGroupSelect() {
        let params: any = this.initSelectPickerParams();
        Object.assign(params, {
            filter: this.editorInstance.editorParams?.['filter'] ? this.editorInstance.editorParams['filter'] : 'srforgid',
        });
        if (this.editorInstance.editorParams?.['treeurl']) {
            Object.assign(params, {
                treeurl: this.editorInstance.editorParams['treeurl'],
            })
        }
        return this.$createElement(this.editorComponentName, {
            props: params,
            on: {
                formitemvaluechange: this.editorChange.bind(this)
            }
        })
    }

    /**
     * 部门选择器
     * 
     * @memberof DataPickerEditor
     */
    public renderDepartmentSelect() {
        let params: any = this.initSelectPickerParams();
        Object.assign(params, {
            filter: this.editorInstance.editorParams?.['filter'] ? this.editorInstance.editorParams['filter'] : 'srforgid',
        });
        return this.$createElement(this.editorComponentName, {
            props: params,
            on: {
                "select-change": this.editorChange.bind(this)
            }
        })
    }

    /**
     * 单多选列表框
     * 
     * @memberof DataPickerEditor
     */
    public renderCommonMicrocom() {
        let params: any = this.initSelectPickerParams();
        Object.is(params, {
            viewparams: this.viewparams
        })
        return this.$createElement(this.editorComponentName, {
            props: params,
            on: {
                formitemvaluechange: this.editorChange.bind(this)
            }
        })
    }

    /**
     * 组织部门人员微服务组件参数处理
     * 
     * @memberof DataPickerEditor
     */
    public initSelectPickerParams() {
        let params: any = {
            name: this.editorInstance.name,
            value: this.value,
            valueitem: this.parentItem?.valueItemName || '',
            multiple: this.editorInstance.editorParams?.['multiple'] ? JSON.parse(this.editorInstance.editorParams['multiple'] as string) : false,
            url: this.editorInstance.editorParams?.['url'],
            filter: this.editorInstance.editorParams?.['filter'],
            fillMap: this.editorInstance.editorParams?.['fillMap'] ? eval('(' + this.editorInstance.editorParams['fillMap'] + ')') : { id: this.parentItem?.valueItemName || '', label: this.editorInstance.name },
            disabled: this.disabled,
            data: this.contextData,
            context: this.context,
            style: this.customStyle
        };
        if (this.codeList) {
            Object.assign(params, {
                tag: this.codeList.codeName,
                codelistType: this.codeList.codeListType,
                renderMode: this.codeList.orMode,
                valueSeparator: this.codeList.valueSeparator,
                textSeparator: this.codeList.textSeparator
            });
        }
        return params;
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof DataPickerEditor
     */
    public render(): any {
        if (!this.editorIsLoaded) {
            return null;
        }
        const { editorType: type, editorStyle: style } = this.editorInstance;
        const editorTypeStyle: string = `${type}${style && style != 'DEFAULT' ? '_' + style : ''}`;
        switch (editorTypeStyle) {
            case 'PICKEREX_LINKONLY':
            case 'PICKER':
            case 'PICKEREX_NOBUTTON':
            case 'PICKEREX_NOAC_LINK':
            case 'PICKEREX_TRIGGER_LINK':
            case 'PICKEREX_TRIGGER':
            case 'PICKEREX_NOAC':
            case 'PICKEREX_LINK':
            case 'PICKEREX_DROPDOWNVIEW':
            case 'PICKEREX_DROPDOWNVIEW_LINK':
            case 'ADDRESSPICKUP':
            case 'ADDRESSPICKUP_AC':
                return this.renderDefault();
            case 'PICKUPVIEW':
                return this.renderEmbedPicker()
            case 'PICKER_MAPPOSITION':
                return this.renderMapPosition();
            case 'PICKER_ORGSELECT':
            case 'PICKER_ORGMULTIPLE':
            case 'PICKER_ALLORGSELECT':
            case 'PICKER_ALLORGMULTIPLE':
                return this.renderOrgSelect();
            case 'PICKER_ALLDEPTPERSONSELECT':
            case 'PICKER_ALLDEPTPERSONMULTIPLE':
            case 'PICKER_DEPTPERSONSELECT':
            case 'PICKER_DEPTPERSONMULTIPLE':
                return this.renderDepartmentPersonnel();
            case 'PICKER_ALLEMPSELECT':
            case 'PICKER_ALLEMPMULTIPLE':
            case 'PICKER_EMPSELECT':
            case 'PICKER_EMPMULTIPLE':
                return this.renderGroupSelect();
            case 'PICKER_ALLDEPATMENTSELECT':
            case 'PICKER_ALLDEPATMENTMULTIPLE':
            case 'PICKER_DEPATMENTSELECT':
            case 'PICKER_DEPATMENTMULTIPLE':
                return this.renderDepartmentSelect();
            case 'PICKER_COMMONMICROCOM':
                return this.renderCommonMicrocom();
        }

    }
}
