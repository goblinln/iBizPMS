import { ModelTool, Util } from 'ibiz-core';
import { Vue, Component, Prop, Inject } from 'vue-property-decorator';
import { EditorBase } from '../editor-base/editor-base';
import { VueLifeCycleProcessing } from '../../../decorators';
import { IPSAppDEView, IPSAppView, IPSPickerEditor } from '@ibiz/dynamic-model-api';

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
     * 编辑器初始化
     *
     * @memberof DataPickerEditor
     */
    public async initEditor() {
        switch (this.editorInstance?.editorType) {
            // 数据选择
            case 'MOBPICKER':
                break;
            // 多数据选择
            case 'MOBMPICKER':
                break;
            // 数据选择(下拉视图)
            case 'MOBPICKER_DROPDOWNVIEW':
                this.customProps.editortype = 'dropdown';
                break;
        }
        //TODO  待确认链接视图
        this.initPickupViewParams();
        this.initLinkViewParams();
        const entity = (this.editorInstance as any)?.getPSAppDataEntity?.();
        if(entity && !entity?.isFill){
           await entity.fill();
        } 
        this.customProps.acParams = ModelTool.getAcParams(this.editorInstance);
        this.customProps.sort = ModelTool.getAcSort(this.editorInstance);
        this.customProps.deMajorField = ModelTool.getEditorMajorName(this.editorInstance);
        this.customProps.deKeyField = ModelTool.getEditorKeyName(this.editorInstance);
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
                title: pickupAppView.title,
                width: pickupAppView?.width,
                height: pickupAppView?.height,
                placement: pickupAppView?.openMode,
                isRedirectView: pickupAppView.hasOwnProperty('redirectView') ? pickupAppView.redirectView : false,
                viewModelData: pickupAppView
            };
            this.customProps.pickupView = view;
        }
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
                valueitem: this.parentItem?.valueItemName || '',
                formState: this.contextState,
                ...this.customProps,
            },
            on: { formitemvaluechange: this.editorChange },
            style: this.customStyle
        })
    }

    /**
     * 初始化链接视图相关参数
     *
     * @memberof DataPickerEditor
     */
    public initLinkViewParams() {
        let linkAppView = (this.editorInstance as IPSPickerEditor).getPickupPSAppView();
        if (linkAppView) {
            const view: any = {
                viewname: 'app-view-shell',
                title: linkAppView.title,
                width: linkAppView?.width,
                height: linkAppView?.height,
                placement: linkAppView?.openMode,
                // todo getPSAppDERSPaths
                // deResParameters: Util.formatAppDERSPath(this.context, linkAppView.getPSAppDERSPaths),
                deResParameters: [],
                isRedirectView: linkAppView.hasOwnProperty('redirectView') ? linkAppView.redirectView : false,
                viewModelData: linkAppView
            }
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
                        { pathName: 'views', parameterName: linkAppView.codeName?.toLowerCase() }
                    ]
                })
            }
            this.customProps.linkview = view;
        }
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
            case 'MOBPICKER':
            case 'MOBMPICKER':
            case 'MOBPICKER_DROPDOWNVIEW':
                return this.renderDefault();
        }

    }
}
