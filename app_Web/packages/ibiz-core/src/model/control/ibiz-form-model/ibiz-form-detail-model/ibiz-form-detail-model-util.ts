import { IBizFormButtonModel } from './ibiz-form-button-model';
import { IBizFormDruipartModel } from './ibiz-form-druipart-model';
import { IBizFormGroupPanelModel } from './ibiz-form-group-panel-model';
import { IBizFormIFrameModel } from './ibiz-form-iframe-model';
import { IBizFormItemModel } from './ibiz-form-item-model';
import { IBizFormPageModel } from './ibiz-form-page-model';
import { IBizFormPartModel } from './ibiz-form-part-model';
import { IBizFormRawItemModel } from './ibiz-form-raw-item-model';
import { IBizFormTabPageModel } from './ibiz-form-tab-page-model';
import { IBizFormTabPanelModel } from './ibiz-form-tab-panel-model';

/**
 * 表单成员工具类
 *
 * @export
 * @class IBizFormDetailModelUtil
 */
export class IBizFormDetailModelUtil {

    /**
     * 通过模型json实例化表单成员对象
     *
     * @param {*} modelData
     * @memberof IBizFormDetailModel
     */
    public static newFormDetailInstance(modelData: any, formRef: any, parentRef: any, context: any): any {
        switch (modelData.detailType) {
            // 表单分页
            case 'FORMPAGE':
                return new IBizFormPageModel({ formRef, modelData, context });
            // 表单分组面板
            case 'GROUPPANEL':
                return new IBizFormGroupPanelModel({ formRef, parentRef, modelData, context });
            // 表单tab部件分页
            case 'TABPAGE':
                return new IBizFormTabPageModel({ formRef, parentRef, modelData, context });
            // 表单tab部件
            case 'TABPANEL':
                return new IBizFormTabPanelModel({ formRef, parentRef, modelData, context });
            // 表单项
            case 'FORMITEM':
                return new IBizFormItemModel({ formRef, parentRef, modelData, context });
            // 表单按钮
            case 'BUTTON':
                return new IBizFormButtonModel({ formRef, parentRef, modelData, context });
            // 关系界面
            case 'DRUIPART':
                return new IBizFormDruipartModel({ formRef, parentRef, modelData, context });
            // 直接内容
            case 'RAWITEM':
                return new IBizFormRawItemModel({ formRef, parentRef, modelData, context });
            // iframe
            case 'IFRAME':
                return new IBizFormIFrameModel({ formRef, parentRef, modelData, context });
            // 表单部件
            case 'FORMPART':
                return new IBizFormPartModel({ formRef, parentRef, modelData, context });
            // TODO 符合表单项缺少detailType
            default:
                modelData.detailType = 'FORMITEM';
                return new IBizFormItemModel({ formRef, parentRef, modelData, context });
        }
    }
}