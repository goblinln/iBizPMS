import { Component } from 'vue-property-decorator';
import { IPSAppDataEntity } from '@ibiz/dynamic-model-api';
import moment from 'moment';
import { VueLifeCycleProcessing } from '../../../decorators';
import { EditorBase } from '../editor-base/editor-base';
import { Util } from 'ibiz-core';

/**
 * 日期时间选择编辑器
 *
 * @export
 * @class DatePickerEditor
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export default class DatePickerEditor extends EditorBase {

    /**
     * 日期属性格式化
     * 
     * @type {*}
     * @memberof DatePickerEditor
     */
    public dateValueFormat: any;

    /**
     * 编辑器初始化
     *
     * @memberof DatePickerEditor
     */
    public async initEditor() {
        await super.initEditor();
        this.initValueFormat();
        this.customProps.placeholder = this.editorInstance.placeHolder || '请选择时间...';
        this.customProps.transfer = Object.is('SEARCHFORM', this.containerCtrl?.controlType) ? false : true;
        this.customStyle.minWidth = '150px';
        switch (this.editorInstance?.editorType) {
            // 时间选择控件
            case 'DATEPICKEREX':
                this.customProps.type = 'date';
                this.customProps.format = 'yyyy-MM-dd HH:mm:ss';
                break;
            // 时间选择控件_无小时
            case 'DATEPICKEREX_NOTIME':
                this.customProps.type = 'date';
                this.customProps.format = 'yyyy-MM-dd';
                break;
            // 时间选择器(新）
            case 'DATEPICKER':
                this.customProps.type = 'datetime';
                this.customProps.format = 'yyyy-MM-dd HH:mm:ss';
                break;
            // 时间选择控件_小时
            case 'DATEPICKEREX_HOUR':
                this.customProps.format = 'yyyy-MM-dd HH';
                break;
            // 时间选择控件_分钟
            case 'DATEPICKEREX_MINUTE':
                this.customProps.format = 'yyyy-MM-dd HH:mm';
                break;
            // 时间选择控件_秒钟
            case 'DATEPICKEREX_SECOND':
                this.customProps.format = 'yyyy-MM-dd HH:mm:ss';
                break;
            // 时间选择控件_无日期
            case 'DATEPICKEREX_NODAY':
                this.customProps.format = 'HH:mm:ss';
                break;
            // 时间选择控件_无日期无秒钟
            case 'DATEPICKEREX_NODAY_NOSECOND':
                this.customProps.format = 'HH:mm';
                break;
            // 时间选择控件_无秒钟
            case 'DATEPICKEREX_NOSECOND':
                this.customProps.format = 'yyyy-MM-dd HH:mm';
                break;
        }
    }

    /**
     * 日期格式初始化
     *
     * @memberof DatePickerEditor
     */
    public initValueFormat() {
        const entity: IPSAppDataEntity = this.containerCtrl?.getPSAppDataEntity?.() as IPSAppDataEntity;
        if (entity) {
            this.dateValueFormat = entity.findPSAppDEField(this.parentItem?.getPSAppDEField?.()?.codeName)?.valueFormat;
        }
    }

    /**
     * 编辑器change回调
     *
     * @param {{ name: string; value: any }} $event
     * @memberof DatePickerEditor
     */
    public handleChange(value1: any, value2?: any) {
        let tempFormat: string = this.customProps.format.replace("yyyy", "YYYY").replace("dd", "DD");
        if (this.dateValueFormat && this.dateValueFormat != tempFormat) {
            value1 = this.formatDate(value1, tempFormat, this.dateValueFormat);
        }
        this.editorChange({ name: this.editorInstance.name, value: value1 });
    }

    /**
     * 设置编辑器的自定义高宽
     *
     * @memberof DatePickerEditor
     */
    public setCustomStyle() {
        let { editorWidth, editorHeight } = this.editorInstance;
        this.customStyle = {};
        if (!Util.isEmpty(editorWidth) && editorWidth != 0) {
            this.handleEditorWidth(editorWidth);
        }
        if (!Util.isEmpty(editorHeight) && editorHeight != 0) {
            this.customStyle.height = editorHeight > 1 ? editorHeight + "px" : editorHeight * 100 + "%";
        }
    }

    /**
     * 根据类型处理编辑器宽度
     *
     * @memberof DatePickerEditor
     */
    public handleEditorWidth(width: number) {
        switch (this.editorInstance?.editorType) {
            // 时间选择控件
            case 'DATEPICKEREX':
            case 'DATEPICKER':
            case 'DATEPICKEREX_SECOND':
                this.customProps.format = 'yyyy-MM-dd HH:mm:ss';
                this.calcCustomStyle(width,160);
                break;
            case 'DATEPICKEREX_MINUTE':
            case 'DATEPICKEREX_NOSECOND':
                this.calcCustomStyle(width,140);
                break;
            case 'DATEPICKEREX_HOUR':
                this.calcCustomStyle(width,120);
                break;
            case 'DATEPICKEREX_NOTIME':
                this.calcCustomStyle(width,100);
                break;
            case 'DATEPICKEREX_NODAY':
                this.calcCustomStyle(width,90);
                break;
            case 'DATEPICKEREX_NODAY_NOSECOND':
                this.calcCustomStyle(width,70);
                break;
        }
    }

    /**
     * 计算自定义样式
     *
     * @param date  时间
     * @param format    格式
     * @memberof DatePickerEditor
     */
    public calcCustomStyle(value: number, limit: number) {
        if ( value == limit) {
            this.customStyle.width = '100%';
        }else {
            this.customStyle.width = value > 1 ? value + "px" : value * 100 + "%";
        }
    }

    /**
     * 日期格式化
     *
     * @param date  时间
     * @param format    格式
     * @memberof DatePickerEditor
     */
    public formatDate(date: any, valueFormat: string, typeFormat: string) {
        if (!typeFormat) {
            return date;
        }
        try {
            const tempDate = moment(date, valueFormat).format(typeFormat);
            if (tempDate == 'Invalid date') {
                return date;
            } else {
                return tempDate;
            }
        } catch (error) {
            return date;
        }
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof DatePickerEditor
     */
    public render(): any {
        if (!this.editorIsLoaded) {
            return null;
        }
        return this.$createElement(this.editorComponentName, {
            props: {
                name: this.editorInstance.name,
                value: this.value,
                disabled: this.disabled,
                ...this.customProps,
            },
            on: { "on-change": this.handleChange },
            style: this.customStyle
        })
    }
}
