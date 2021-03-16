import { Util } from 'ibiz-core';
import { Vue, Component, Prop, Inject } from 'vue-property-decorator';
import { EditorBase } from '../editor-base/editor-base';
import { VueLifeCycleProcessing } from '../../../decorators';

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
     * 编辑器初始化
     *
     * @memberof DataPickerEditor
     */
    public initEditor() {
        const { deMajorField, deKeyField, pickupAppView, placeHolder } = this.editorInstance;
        this.customProps.deMajorField = deMajorField;
        this.customProps.deKeyField = deKeyField;
        switch (this.editorInstance?.editorType) {
            // 数据链接
            case 'PICKEREX_LINKONLY':
                this.customProps.editorType = 'linkonly';
                this.initLinkViewParams();
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
                this.initLinkViewParams();
                break;
            // 数据选择（下拉、数据链接）
            case 'PICKEREX_TRIGGER_LINK':
                this.initPickupViewParams();
                this.initLinkViewParams();
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
                this.initLinkViewParams();
                this.initAcParams();
                break;
            // 数据选择（下拉视图）
            case 'PICKEREX_DROPDOWNVIEW':
                this.initPickupViewParams();
                break;
            // 数据选择（下拉视图、数据链接）
            case 'PICKEREX_DROPDOWNVIEW_LINK':
                this.initPickupViewParams();
                this.initLinkViewParams();
                break;
            // 数据选择（嵌入选择视图）
            case 'PICKUPVIEW':
                this.customProps.formState = this.contextState;
                this.customProps.emptyText = placeHolder;
                this.customProps.refviewname = pickupAppView && Util.srfFilePath2(pickupAppView?.codeName);
                this.customProps.refreshitems = [];
                break;
            // 地址框（选择）
            case 'ADDRESSPICKUP':
                this.initPickupViewParams();
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
        const { pickupAppView } = this.editorInstance;
        if (pickupAppView) {
            const view: any = {
                viewname: 'app-view-shell',
                title: pickupAppView.title,
                width: pickupAppView?.width,
                height: pickupAppView?.height,
                placement: pickupAppView?.openMode,
                isRedirectView: pickupAppView.hasOwnProperty('redirectView') ? pickupAppView.redirectView : false,
                viewpath: pickupAppView.dynaModelFilePath
            };
            this.customProps.pickupView = view;
        }
    }

    /**
     * 初始化链接视图相关参数
     *
     * @memberof DataPickerEditor
     */
    public initLinkViewParams() {
        const { linkAppView } = this.editorInstance;
        if (linkAppView) {
            const view: any = {
                viewname: 'app-view-shell',
                title: linkAppView.title,
                width: linkAppView?.width,
                height: linkAppView?.height,
                placement: linkAppView?.openMode,
                deResParameters: Util.formatAppDERSPath(this.context, linkAppView.getPSAppDERSPaths),
                isRedirectView: linkAppView.hasOwnProperty('redirectView') ? linkAppView.redirectView : false,
                viewpath: linkAppView.dynaModelFilePath
            }
            if (linkAppView.appDataEntity) {
                Object.assign(view, {
                    parameters: [
                        { pathName: Util.srfpluralize(linkAppView.appDataEntity.codeName).toLowerCase(), parameterName: linkAppView.appDataEntity.codeName.toLowerCase() },
                        { pathName: 'views', parameterName: linkAppView.getPSDEViewCodeName.toLowerCase() },
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
        const { acParams, getPSAppDEACMode } = this.editorInstance;
        this.customProps.acParams = acParams;
        if (getPSAppDEACMode?.getMinorSortPSAppDEField && getPSAppDEACMode?.minorSortDir) {
            this.customProps.sort = `${getPSAppDEACMode.getMinorSortPSAppDEField.codeName?.toLowerCase()},${getPSAppDEACMode.minorSortDir.toLowerCase()}`
        }
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
                valueItem: this.editorInstance?.valueItemName || '',
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
                name: this.editorInstance.name,
                value: this.value,
                disabled: this.disabled,
                data: this.contextData,
                context: this.context,
                viewparams: this.viewparams,
                service: this.service,
                valueitem: this.editorInstance?.valueItemName || '',
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
            filter: this.editorInstance.getEditorParam('filter') ? this.editorInstance.getEditorParam('filter') : 'srforgid',
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
            filter: this.editorInstance.getEditorParam('filter') ? this.editorInstance.getEditorParam('filter') : 'srfpdept',
        });
        if (this.editorInstance.getEditorParam('treeurl')) {
            Object.assign(params, {
                treeurl: this.editorInstance.getEditorParam('treeurl'),
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
            filter: this.editorInstance.getEditorParam('filter') ? this.editorInstance.getEditorParam('filter') : 'srforgid',
        });
        if (this.editorInstance.getEditorParam('treeurl')) {
            Object.assign(params, {
                treeurl: this.editorInstance.getEditorParam('treeurl'),
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
            filter: this.editorInstance.getEditorParam('filter') ? this.editorInstance.getEditorParam('filter') : 'srforgid',
        });
        return this.$createElement(this.editorComponentName, {
            props: params,
            on: {
                "select-change": this.editorChange.bind(this)
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
            valueitem: this.editorInstance.valueItemName,
            multiple: this.editorInstance.getEditorParam('multiple') ? JSON.parse(this.editorInstance.getEditorParam('multiple')) : false,
            url: this.editorInstance.getEditorParam('url'),
            fillMap: this.editorInstance.getEditorParam('fillMap') ? this.editorInstance.getEditorParam('fillMap') : { id: this.editorInstance.valueItemName, label: this.editorInstance.name },
            disabled: this.disabled,
            data: this.contextData,
            context: this.context,
            style: this.customStyle
        };
        const codeList: any = this.editorInstance.codeList;
        if (codeList) {
            Object.assign(params, {
                tag: codeList.codeName,
                codelistType: codeList.codeListType,
                renderMode: codeList.orMode,
                valueSeparator: codeList.valueSeparator,
                textSeparator: codeList.textSeparator
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
        }

    }
}
