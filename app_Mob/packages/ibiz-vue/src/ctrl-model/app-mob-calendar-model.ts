import { ModelTool } from 'ibiz-core';
import { IPSDECalendar, IPSSysCalendar, IPSSysCalendarItem, IPSAppDERS } from '@ibiz/dynamic-model-api';

export class AppMobCalendarModel {

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
          {
              name: 'queryStart',
              prop: 'n_start_gtandeq'
          },
          {
              name: 'queryEnd',
              prop: 'n_end_ltandeq'
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
        ];
        // 关联主实体的主键
        const appDataEntity = this.calendarInstance.getPSAppDataEntity();
        if (appDataEntity?.major && appDataEntity.getMinorPSAppDERSs()) {
          const minorPSAppDERSs = appDataEntity.getMinorPSAppDERSs();
          minorPSAppDERSs?.forEach((minorAppDERS:IPSAppDERS) => {
            const majorAppDataEntity = minorAppDERS.getMajorPSAppDataEntity();
            const keyField =  ModelTool.getAppEntityKeyField(majorAppDataEntity);
            let tempArr: any[] = [{
              name : majorAppDataEntity?.codeName?.toLowerCase(),
              prop : keyField?.codeName.toLowerCase()
            }]
            modelArray.push(tempArr)
          });
        }
        if(!this.itemType) {
            return modelArray;
        }
        //日历项实体映射
        const calendarItems = (this.calendarInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
        const item = calendarItems?.find((_item: IPSSysCalendarItem) => {
            return _item.itemType == this.itemType;
        })
        if(item) {
            const entity = item.getPSAppDataEntity(),
                  idField = item.getIdPSAppDEField(),
                  textField = item.getTextPSAppDEField(),
                  beginField = item.getBeginTimePSAppDEField(),
                  endField = item.getEndTimePSAppDEField();
            const keyField =  ModelTool.getAppEntityKeyField(entity);
            const majorField = ModelTool.getAppEntityMajorField(entity);       
            let tempArr: any[] = [{
                name: entity?.codeName?.toLowerCase(),
                prop: idField?.codeName ? idField.codeName.toLowerCase() : keyField?.codeName?.toLowerCase()
            }, {
                name: 'title',
                prop: textField?.codeName ? textField.codeName.toLowerCase() : majorField?.codeName.toLowerCase()
            }, {
                name: 'start',
                prop: beginField?.codeName ? `n_${beginField.codeName.toLowerCase()}_gtandeq` : `n_${beginField?.name?.toLowerCase()}_gtandeq`
            }, {
                name: 'end',
                prop: endField?.codeName ? `n_${endField.codeName.toLowerCase()}_ltandeq` : `n_${endField?.name?.toLowerCase()}_gtandeq`
            }];
            modelArray = [...modelArray, ...tempArr];
        }
        return modelArray;
    }
}