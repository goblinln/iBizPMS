import { AppUILogicService } from "./app-logic/appuilogic";

/**
 * 安装插件
 *
 */
export const vueinstall = function (): void {
    // 避免出现重复实例化
    if ((window as any).___ibz___uilogic_init === true) {
        return;
    }
    (window as any).___ibz___uilogic_init = true;
    (window as any).UILogicService = AppUILogicService.getInstance();
};
