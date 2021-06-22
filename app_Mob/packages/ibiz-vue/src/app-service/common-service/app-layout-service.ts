import { AppDefaultIndexViewLayout } from "../../components/layout/app-default-layout/app-default-indexview-layout/app-default-indexview-layout";
import { AppDefaultMobMdViewLayout } from "../../components/layout/app-default-layout/app-default-mob-mdview-layout/app-default-mob-mdview-layout";
import { AppDefaultMobEditViewLayout } from "../../components/layout/app-default-layout/app-default-mob-editview-layout/app-default-mob-editview-layout";
import { AppDefaultMobEditView3Layout } from "../../components/layout/app-default-layout/app-default-mob-editview3-layout/app-default-mob-editview3-layout";
import { AppDefaultMobPortalViewLayout } from "../../components/layout/app-default-layout/app-default-mob-portalview-layout/app-default-mob-portalview-layout";
import { AppDefaultMobCalendarViewLayout } from "../../components/layout/app-default-layout/app-default-mob-calendarview-layout/app-default-mob-calendarview-layout";
import { AppDefaultMobChartViewLayout } from '../../components/layout/app-default-layout/app-default-mob-chartview-layout/app-default-mob-chartview-layout';
import { AppDefaultMobTabExpViewLayout } from "../../components/layout/app-default-layout/app-default-mob-tabexpview-layout/app-default-mob-tabexpview-layout";
import { AppDefaultMobListExpViewLayout } from '../../components/layout/app-default-layout/app-default-mob-listexpview-layout/app-default-mob-listexpview-layout';
import { AppDefaultMobTreeViewLayout } from '../../components/layout/app-default-layout/app-default-mob-treeview-layout/app-default-mob-treeview-layout';
import { AppDefaultMobMeditViewViewLayout } from "../../components/layout/app-default-layout/app-default-mob-meditview-layout/app-default-mob-meditview-layout";
import { AppDefaultOptViewLayout } from "../../components/layout/app-default-layout/app-default-optview-layout/app-default-optview-layout";
import { AppDefaultMobPickUpMdViewLayout } from "../../components/layout/app-default-layout/app-default-mob-pickupmdview-layout/app-default-mob-pickupmdview-layout";
import { AppDefaultMobPickUpViewLayout } from "../../components/layout/app-default-layout/app-default-mob-pickupview-layout/app-default-mob-pickupview-layout";
import { AppDefaultMobMPickUpViewLayout } from "../../components/layout/app-default-layout/app-default-mob-mpickupview-layout/app-default-mob-mpickupview-layout";
import { AppDefaultMobDePortalViewLayout } from "../../components/layout/app-default-layout/app-default-mob-deportalview-layout/app-default-mob-deportalview-layout";
import { AppDefaultMobPickUpTreeViewLayout } from "../../components/layout/app-default-layout/app-default-mob-pickuptreeview-layout/app-default-mob-pickuptreeview-layout";
import { AppDefaultMobWFDynaEditViewLayout } from "../../components/layout/app-default-layout/app-default-mob-wfdynaeditview-layout/app-default-mob-wfdynaeditview-layout";
import { AppDefaultMobWFDynaActionViewLayout } from "../../components/layout/app-default-layout/app-default-mob-wfdynaactionview-layout/app-default-mob-wfdynaactionview-layout";
import { AppDefaultMobWfDynaExpMdViewLayout } from "../../components/layout/app-default-layout/app-default-mob-wfdynaexpmdview-layout/app-default-mob-wfdynaexpmdview-layout";
import { AppDefaultMobWFDynaEditView3Layout } from "../../components/layout/app-default-layout/app-default-mob-wfdynaeditview3-layout/app-default-mob-wfdynaeditview3-layout";
import { AppDefaultMobWFDynaStartViewLayout } from "../../components/layout/app-default-layout/app-default-mob-wfdynastartview-layout/app-default-mob-wfdynastartview-layout";
import { AppDefaultMobDeRedirectViewLayout } from "../../components/layout/app-default-layout/app-default-mob-deredirectview-layout/app-default-mob-deredirectview-layout";
import { AppDefaultMobMapViewLayout } from "../../components/layout/app-default-layout/app-default-mob-mapview-layout/app-default-mob-mapview-layout";

/**
 * 应用组件服务
 * 
 * @memberof AppLayoutService
 */
export class AppLayoutService {

    /**
     * 布局组件Map
     * 
     * @memberof AppLayoutService
     */
    protected static layoutMap: Map<string, any> = new Map();

    /**
     * 注册布局组件
     * 
     * @memberof AppLayoutService
     */
    public static registerLayoutComponent() {
        this.layoutMap.set("APPINDEXVIEW-DEFAULT", AppDefaultIndexViewLayout);
        this.layoutMap.set("DEMOBMDVIEW-DEFAULT", AppDefaultMobMdViewLayout);
        this.layoutMap.set("DEMOBMDVIEW9-DEFAULT", AppDefaultMobMdViewLayout);
        this.layoutMap.set("DEMOBEDITVIEW-DEFAULT", AppDefaultMobEditViewLayout);
        this.layoutMap.set("DEMOBEDITVIEW3-DEFAULT", AppDefaultMobEditView3Layout);
        this.layoutMap.set("APPPORTALVIEW-DEFAULT", AppDefaultMobPortalViewLayout);
        this.layoutMap.set("DEMOBCALENDARVIEW-DEFAULT", AppDefaultMobCalendarViewLayout);
        this.layoutMap.set("DEMOBCHARTVIEW-DEFAULT", AppDefaultMobChartViewLayout);
        this.layoutMap.set("DEMOBTABEXPVIEW-DEFAULT", AppDefaultMobTabExpViewLayout);
        this.layoutMap.set("DEMOBLISTEXPVIEW-DEFAULT", AppDefaultMobListExpViewLayout);
        this.layoutMap.set("DEMOBTREEVIEW-DEFAULT", AppDefaultMobTreeViewLayout);
        this.layoutMap.set("DEMOBMEDITVIEW9-DEFAULT", AppDefaultMobMeditViewViewLayout);
        this.layoutMap.set("DEMOBEDITVIEW9-DEFAULT", AppDefaultMobEditViewLayout);
        this.layoutMap.set("DEMOBOPTVIEW-DEFAULT", AppDefaultOptViewLayout);
        this.layoutMap.set("DEMOBPICKUPMDVIEW-DEFAULT", AppDefaultMobPickUpMdViewLayout);
        this.layoutMap.set("DEMOBPICKUPVIEW-DEFAULT", AppDefaultMobPickUpViewLayout);
        this.layoutMap.set("DEMOBMPICKUPVIEW-DEFAULT", AppDefaultMobMPickUpViewLayout);
        this.layoutMap.set("DEMOBPORTALVIEW-DEFAULT", AppDefaultMobDePortalViewLayout);
        this.layoutMap.set("DEMOBPICKUPTREEVIEW-DEFAULT", AppDefaultMobPickUpTreeViewLayout);
        this.layoutMap.set("DEMOBWFDYNAEDITVIEW-DEFAULT", AppDefaultMobWFDynaEditViewLayout);
        this.layoutMap.set("DEMOBWFDYNAACTIONVIEW-DEFAULT", AppDefaultMobWFDynaActionViewLayout);
        this.layoutMap.set("DEMOBWFDYNAEXPMDVIEW-DEFAULT", AppDefaultMobWfDynaExpMdViewLayout);
        this.layoutMap.set("DEMOBWFDYNAEDITVIEW3-DEFAULT", AppDefaultMobWFDynaEditView3Layout);
        this.layoutMap.set("DEMOBWFDYNASTARTVIEW-DEFAULT", AppDefaultMobWFDynaStartViewLayout);
        this.layoutMap.set("DEMOBREDIRECTVIEW-DEFAULT", AppDefaultMobDeRedirectViewLayout);
        this.layoutMap.set("DEMAPVIEW-DEFAULT", AppDefaultMobMapViewLayout);
    }

    /**
     * 获取布局组件
     * 
     * @memberof AppLayoutService
     */
    public static getLayoutComponent(key: string) {
        return this.layoutMap.get(key);
    }

}