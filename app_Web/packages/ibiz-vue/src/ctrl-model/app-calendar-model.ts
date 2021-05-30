import { IPSAppDataEntity, IPSAppDEField, IPSDECalendar, IPSSysCalendar, IPSSysCalendarItem } from '@ibiz/dynamic-model-api';
import { ModelTool } from 'ibiz-core';

export class AppCalendarModel {

    /**
    * 日历实例对象
    *
    * @memberof AppGridModel
    */
    public calendarInstance !: IPSDECalendar;

    /**
	 * 日历项类型
	 *
	 * @returns {string}
	 * @memberof AppGridModel
	 */
    public itemType: string = '';

    /**
    * Creates an instance of AppGridModel.
    * 
    * @param {*} [opts={}]
    * @memberof AppGridModel
    */
    constructor(opts: any) {
        this.calendarInstance = opts;
    }
    
    public getDataItems(): any[] {
        let modelArray: any[] = [
            // 前端新增修改标识，新增为"0",修改为"1"或未设值
            {
            name: 'srffrontuf',
            prop: 'srffrontuf',
            dataType: 'TEXT',
            },
            {
            name: 'color',
            },
            {
            name: 'textColor',
            },
            {
            name: 'itemType',
            },
            {
            name: 'query',
            prop: 'query',
            },
        ];
        if(!this.itemType) {
            return modelArray;
        }
        //日历项实体映射
        const calendarItems: Array<IPSSysCalendarItem> = (this.calendarInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
        const item: IPSSysCalendarItem = calendarItems?.find((_item: IPSSysCalendarItem) => {
            return _item.itemType == this.itemType;
        }) as IPSSysCalendarItem;
        if(item) {
            const entity = item.getPSAppDataEntity() as IPSAppDataEntity;
            const idField: IPSAppDEField = item.getIdPSAppDEField() as IPSAppDEField;
            const beginField: IPSAppDEField = item.getBeginTimePSAppDEField() as IPSAppDEField;
            const endField: IPSAppDEField = item.getEndTimePSAppDEField() as IPSAppDEField;
            const textField: IPSAppDEField = item.getTextPSAppDEField() as IPSAppDEField;
            const bKColorField: IPSAppDEField = item.getBKColorPSAppDEField() as IPSAppDEField;
            const colorField: IPSAppDEField = item.getColorPSAppDEField() as IPSAppDEField;
            const contentField: IPSAppDEField = item.getContentPSAppDEField() as IPSAppDEField;
            let tempArr: any[] = [{
                name: entity?.codeName?.toLowerCase(),
                prop: idField?.codeName ? idField.codeName.toLowerCase()
                        : ((ModelTool.getAppEntityKeyField(entity) as IPSAppDEField)?.codeName || '').toLowerCase()
            }, {
                name: 'title',
                prop: textField?.codeName ? textField.codeName.toLowerCase()
                        : ((ModelTool.getAppEntityKeyField(entity) as IPSAppDEField)?.codeName || '').toLowerCase()
            }, {
                name: 'start',
                prop: beginField?.codeName ? beginField.codeName.toLowerCase() : beginField?.name?.toLowerCase()
            }, {
                name: 'end',
                prop: endField?.codeName ? endField.codeName.toLowerCase() : endField?.name?.toLowerCase()
            }, {
                name: 'bkcolor',
                prop: bKColorField?.codeName ? bKColorField.codeName.toLowerCase() : bKColorField?.name?.toLowerCase()
            }, {
                name: 'color',
                prop: colorField?.codeName ? colorField.codeName.toLowerCase() : colorField?.name?.toLowerCase()
            }];
            if (contentField) {
                tempArr.push({
                    name: 'content',
                    prop: contentField?.codeName ? contentField.codeName.toLowerCase() : contentField?.name?.toLowerCase()
                })
            }
            modelArray = [...modelArray, ...tempArr];
        }
        return modelArray;
    }
}