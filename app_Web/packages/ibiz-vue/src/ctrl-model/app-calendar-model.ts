import { IBizCalendarModel } from 'ibiz-core';

export class AppCalendarModel {

    /**
    * 日历实例对象
    *
    * @memberof AppGridModel
    */
    public calendarInstance !: IBizCalendarModel;

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
        const { calendarItems } = this.calendarInstance;
        const item: any = calendarItems?.find((_item: any) => {
            return _item.itemType == this.itemType;
        })
        if(item) {
            const {
                getPSAppDataEntity: entity,
                getIdPSAppDEField: idField,
                getTextPSAppDEField: textField,
                getBeginTimePSAppDEField: beginField,
                getEndTimePSAppDEField: endField,
                getBkColorPSAppDEField: bkColorField,
                getColorPSAppDEField: colorField
            } = item;
            let tempArr: any[] = [{
                name: entity?.codeName?.toLowerCase(),
                prop: idField?.codeName ? idField.codeName.toLowerCase() : entity?.getKeyPSAppDEField?.codeName?.toLowerCase()
            }, {
                name: 'title',
                prop: textField?.codeName ? textField.codeName.toLowerCase() : entity?.getMajorPSAppDEField?.codeName.toLowerCase()
            }, {
                name: 'start',
                prop: beginField?.codeName ? beginField.codeName.toLowerCase() : beginField?.name?.toLowerCase()
            }, {
                name: 'end',
                prop: endField?.codeName ? endField.codeName.toLowerCase() : endField?.name?.toLowerCase()
            }, {
                name: 'bkcolor',
                prop: bkColorField?.codeName ? bkColorField.codeName.toLowerCase() : bkColorField?.name?.toLowerCase()
            }, {
                name: 'color',
                prop: colorField?.codeName ? colorField.codeName.toLowerCase() : colorField?.name?.toLowerCase()
            }];
            modelArray = [...modelArray, ...tempArr];
        }
        return modelArray;
    }
}