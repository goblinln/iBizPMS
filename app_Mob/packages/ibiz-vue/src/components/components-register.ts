// 注册插件
import Vue from 'vue';
import { IonPage } from '../components/common/ion-page/ion-page';
import appKeepAlive from '../components/common/app-keep-alive/app-keep-alive.vue';
import { AppEmbedView } from '../components/common/app-embed-view/app-embed-view';
// 预置组件

// 视图组件
import { AppDefaultIndexView } from './view/app-default-indexview/app-default-indexview';
import { AppDefaultMobMdView } from './view/app-default-mob-mdview/app-default-mob-mdview';
import { AppDefaultMobEditView } from './view/app-default-mob-editview/app-default-mob-editview';
import { AppDefaultMobPortalView } from './view/app-default-mob-portalview/app-default-mob-portalview';
import { AppDefaultMobChartView } from './view/app-default-mob-chartview/app-default-mob-chartview';
import { AppDefaultMobCalendarView } from './view/app-default-mob-calendarview/app-default-mob-calendarview';
import { AppDefaultMobTabExpView } from './view/app-default-mob-tabexpview/app-default-mob-tabexpview';
import { AppDefaultMobListExpView } from './view/app-default-mob-listexpview/app-default-mob-listexpview';
import { AppDefaultMobTreeView } from './view/app-default-mob-treeview/app-default-mob-treeview';
import { AppDefaultMobMeditView } from './view/app-default-mob-meditview/app-default-mob-meditview';
import { AppDefaultMobOptView } from './view/app-default-mob-optview/app-default-mob-optview';
import { AppDefaultPickUpMDView } from './view/app-default-mob-pickmdview/app-default-mob-pickmdview';
import { AppDefaultPickUpView } from './view/app-default-mob-pickview/app-default-mob-pickview';
import { AppDefaultMobMPickUpView } from './view/app-default-mob-mpickview/app-default-mob-mpickview';
import { AppDefaultMobDePortalView } from './view/app-default-mob-deportalview/app-default-mob-deportalview';
import { AppDefaultMobPickUpTreeView } from './view/app-default-mob-pickuptreeview/app-default-mob-pickuptreeview';
import { AppViewShell } from '../view-container/app-view-shell';
import { AppDefaultMobWFDynaEditView } from './view/app-default-mob-wfdynaeditview/app-default-mob-wfdynaeditview';
import { AppDefaultMobWFDynaActionView } from './view/app-default-mob-wfdynaactionview/app-default-mob-wfdynaactionview';
import { AppDefaultWfDynaExpMDView } from './view/app-default-mob-wfdynaexpmdview/app-default-mob-wfdynaexpmdview';
import { AppDefaultMobDeRedirectView } from './view/app-default-mob-deredirectview/app-default-mob-deredirectview';
import { AppDefaultNotSupportedView } from './view/app-default-notsupportedview';

// 部件组件
import { AppDefaultMobAppMenu } from './control/app-default-mob-appmenu/app-default-mob-appmenu';
import { AppDefaultMobMDCtrl } from './control/app-default-mob-mdctrl/app-default-mob-mdctrl';
import { AppDefaultMobForm } from './control/app-default-mob-form/app-default-mob-form';
import { AppDefaultMobSearchForm } from './control/app-default-mob-searchform/app-default-mob-searchform';
import { AppControlShell } from './control/app-control-shell/app-control-shell';
import { AppDefaultMobDashboard } from './control/app-default-mob-dashboard/app-default-mob-dashboard';
import { AppDefaultMobPortlet } from './control/app-default-mob-portlet/app-default-mob-portlet';
import { AppDefaultMobCalendar } from './control/app-default-mob-calendar/app-default-mob-calendar';
import { AppDefaultMobTabExpPanel } from './control/app-default-mob-tabexppanel/app-default-mob-tabexppanel';
import { AppDefaultMobTabViewPanel } from './control/app-default-mob-tabviewpanel/app-default-mob-tabviewpanel';
import { AppDefaultMobMeditViewPanel } from './control/app-default-mob-meditviewpanel/app-default-mob-meditviewpanel';
import { AppDefaultEditor } from './editor/app-default-editor';
import { ViewToolbar } from './control/view-toolbar/view-toolbar';
import { AppDefaultMobChart } from './control/app-default-mob-chart/app-default-mob-chart';
import { AppDefaultMobListExpBar } from './control/app-default-mob-listexpbar/app-default-mob-listexpbar';
import { AppDefaultMobTree } from './control/app-default-mob-tree/app-default-mob-tree';
import { AppDefaultMobPickUpViewPanel } from './control/app-default-mob-pickupviewpanel/app-default-mob-pickupviewpanel';
import { AppDefaultMobContextMenu } from './control/app-default-mob-contextmenu/app-default-mob-contextmenu';
import { AppDefaultMobPanel } from './control/app-default-mob-panel/app-default-mob-panel';
import { AppDefaultNotSupportedControl } from './control/app-default-notsupportedcontrol/app-default-notsupportedcontrol';

export const ComponentsRegister = {
    install(v: any, opt: any) {
        v.component('ion-page', IonPage);
        v.component('app-keep-alive', appKeepAlive);
        v.component('app-embed-view', AppEmbedView);
        v.component('app-list-menu', () => import('../components/common/app-list-menu/app-list-menu.vue'));
        v.component('app-slider-menu', () => import('../components/common/app-slider-menu/app-slider-menu.vue'));
        v.component('app-icon-menu', () => import('../components/common/app-icon-menu/app-icon-menu.vue'));
        v.component('app-radio', () => import('../components/common/app-radio/app-radio.vue'));
        v.component('app-multiple-select', () => import('../components/common/app-multiple-select/app-multiple-select.vue'));
        v.component('app-form-druipart', () => import('../components/common/app-form-druipart/app-form-druipart.vue'));
        v.component('app-card-list', () => import('../components/common/app-card-list/app-card-list.vue'));
        v.component('app-icon-list', () => import('../components/common/app-icon-list/app-icon-list.vue'));
        v.component('app-pic-menu', () => import('../components/common/app-pic-menu/app-pic-menu.vue'));
        v.component('app-picdown-menu', () => import('../components/common/app-picdown-menu/app-picdown-menu.vue'));
        v.component('app-picright-menu', () => import('../components/common/app-picright-menu/app-picright-menu.vue'));
        v.component('app-pictop-menu', () => import('../components/common/app-pictop-menu/app-pictop-menu.vue'));
        v.component('app-point-list', () => import('../components/common/app-point-list/app-point-list.vue'));
        v.component('app-round-list', () => import('../components/common/app-round-list/app-round-list.vue'));
        v.component('app-status-list', () => import('../components/common/app-status-list/app-status-list.vue'));
        v.component('app-table-list', () => import('../components/common/app-table-list/app-table-list.vue'));
        v.component('app-van-menu', () => import('../components/common/app-van-menu/app-van-menu.vue'));

        v.component('app-viewpager', () => import('../components/common/app-viewpager/app-viewpager.vue'));
        v.component('app-notice-bar', () => import('../components/common/app-notice-bar/app-notice-bar.vue'));
        v.component('app-twotitle-list', () => import('../components/common/app-twotitle-list/app-twotitle-list.vue'));
        v.component('app-time-list', () => import('../components/common/app-time-list/app-time-list.vue'));
        v.component('app-form-item', () => import('../components/common/app-form-item/app-form-item.vue'));
        v.component('app-form-group', () => import('../components/common/app-form-group/app-form-group.vue'));
        v.component('app-list-default', () => import('../components/common/app-list-default/app-list-default.vue'));
        v.component('app-list-swipe', () => import('../components/common/app-list-swipe/app-list-swipe.vue'));
        v.component('app-mob-file-list', () => import('../components/common/app-mob-file-list/app-mob-file-list.vue'));
        // 日历组件
        v.component('app-calendar', () => import('../components/common/app-calendar/app-calendar.vue'));
        v.component('app-vcalendar', () => import('../components/common/app-vcalendar/app-vcalendar.vue'));
        // 搜索表单2
        v.component('app-form-item2', () => import('../components/common/app-form-item2/app-form-item2.vue'));
        // 搜索表单2编辑器
        v.component('app-search-editor', () => import('../components/common/app-search-editor/app-search-editor.vue'));
        // ICON
        v.component('app-mob-icon', () => import('../components/common/app-mob-icon/app-mob-icon.vue'));

        // 编辑器 BEGIN
        // 评分器
        v.component('app-mob-rate', () => import('../components/common/app-mob-rate/app-mob-rate.vue'));
        // 下拉视图
        v.component('app-mob-select-drop-down', () => import('../components/common/app-mob-select-drop-down/app-mob-select-drop-down.vue'));
        // 多行文本
        v.component('app-mob-textarea', () => import('../components/common/app-mob-textarea/app-mob-textarea.vue'));
        // 图片选择
        v.component('app-mob-picture', () => import('../components/common/app-mob-picture/app-mob-picture.vue'));
        // 下拉列表 （多选）
        v.component('app-mob-check-list', () => import('../components/common/app-mob-check-list/app-mob-check-list.vue'));
        // 文件上传
        v.component('app-mob-file-upload', () => import('../components/common/app-mob-file-upload/app-mob-file-upload.vue'));
        // 开关
        v.component('app-mob-switch', () => import('../components/common/app-mob-switch/app-mob-switch.vue'));
        // 滑动输入条
        v.component('app-mob-slider', () => import('../components/common/app-mob-slider/app-mob-slider.vue'));
        //单选框
        v.component('app-mob-radio-list', () => import('../components/common/app-mob-radio-list/app-mob-radio-list.vue'));
        //下拉单选
        v.component('app-mob-dropdown-list', () => import('../components/common/app-mob-dropdown-list/app-mob-dropdown-list.vue'));
        //下拉单选
        v.component('app-mob-mpicker', () => import('../components/common/app-mob-mpicker/app-mob-mpicker.vue'));
        //span
        v.component('app-mob-span', () => import('../components/common/app-mob-span/app-mob-span.vue'));
        //录音 
        v.component('app-mob-recorder', () => import('../components/common/app-mob-recorder/app-mob-recorder.vue'));
        // 工作流审批意见控件
        v.component('app-wf-approval', () => import('../components/common/app-wf-approval/app-wf-approval.vue'));
        // 数据选择多选
        v.component('app-mob-picker', () => import('../components/common/app-mob-picker/app-mob-picker.vue'));
        // 下拉选择
        v.component('app-mob-select', () => import('../components/common/app-mob-select/app-mob-select.vue'));
        // 时间选择器
        v.component('app-mob-datetime-picker', () => import('../components/common/app-mob-datetime-picker/app-mob-datetime-picker.vue'));
        // 输入框
        v.component('app-mob-input', () => import('../components/common/app-mob-input/app-mob-input.vue'));
        // 步进器
        v.component('app-mob-stepper', () => import('../components/common/app-mob-stepper/app-mob-stepper.vue'));
        // 富文本（模态）
        v.component('app-rich-text', () => import('../components/common/app-rich-text/app-rich-text.vue'));
        // 富文本项
        v.component('app-mob-rich-text-editor', () => import('../components/common/app-mob-rich-text-editor/app-mob-rich-text-editor.vue'));
        // 下拉多选（带搜索）
        v.component('app-mob-check-list-search', () => import('../components/common/app-mob-check-list-search/app-mob-check-list-search.vue'));
        // 编辑器 END

        // 菜单样式 BEGIN
        // 图标视图
        v.component('app-mob-menu-ionic-view', () => import('../components/common/app-mob-menu-ionic-view/app-mob-menu-ionic-view.vue'));
        // 列表视图
        v.component('app-mob-menu-list-view', () => import('../components/common/app-mob-menu-list-view/app-mob-menu-list-view.vue'));
        // 图片滑动视图
        v.component('app-mob-menu-swiper-view', () => import('../components/common/app-mob-menu-swiper-view/app-mob-menu-swiper-view.vue'));
        //快速分组组件
        v.component('app-van-select', () => import('../components/common/app-van-select/app-van-select.vue'));
        // 默认样式视图
        v.component('app-mob-menu-default-view', () => import('../components/common/app-mob-menu-default-view/app-mob-menu-default-view.vue'));
        // 菜单样式 END

        //界面行为操作栏
        v.component('app-actionbar', () => import('../components/common/app-actionbar/app-actionbar.vue'));
        // 路由缓存
        v.component('app-list-index', () => import('../components/common/app-list-index/app-list-index.vue'));
        // 列表项插件
        v.component('app-list-index-text', () => import('../components/common/app-list-index-text/app-list-index-text.vue'));
        // 多数据快速分组组件
        v.component('app-quick-group-tab', () => import('../components/common/app-quick-group-tab/app-quick-group-tab.vue'));
        // 搜索历史组件
        v.component('app-search-history', () => import('../components/common/app-search-history/app-search-history.vue'));
        // 风格切换组件
        v.component('app-mob-select-changeStyle', () => import('../components/common/app-mob-select-changeStyle/app-mob-select-changeStyle.vue'));
        // setting组件
        v.component('app-setting', () => import('../components/common/app-setting/app-setting.vue'));
        // 定制组件
        v.component('app-customize', () => import('../components/common/app-customize/app-customize.vue'));
        // 主题切换组件
        v.component('app-mob-select-theme', () => import('../components/common/app-mob-select-theme/app-mob-select-theme.vue'));
        // 锚点列表插件
        v.component('app-anchor-list', () => import('../components/common/app-anchor-list/app-anchor-list.vue'));
        // 侧滑菜单组件
        v.component('app-mob-menu-sideslip-view', () => import('../components/common/app-mob-menu-sideslip-view/app-mob-menu-sideslip-view.vue'));
        // 动作面板
        v.component('app-mob-actionsheet', () => import('../components/common/app-mob-actionsheet/app-mob-actionsheet.vue'));
        // 地图定位
        v.component('app-mob-map', () => import('../components/common/app-mob-map/app-mob-map.vue'));
        // 树已选择列表
        v.component('app-select-tree-list', () => import('../components/common/app-select-tree-list/app-select-tree-list.vue'));
        // 上下文菜单
        v.component('app-mob-context-menu', () => import('../components/common/app-mob-context-menu/app-mob-context-menu.vue'));
        // 单位选择器
        v.component('app-mob-org-select', () => import('../components/common/app-mob-org-select/app-mob-org-select.vue'));
        // 部门选择器
        v.component('app-mob-department-select', () => import('../components/common/app-mob-department-select/app-mob-department-select.vue'));
        // 人员部门选择器
        v.component('app-mob-department-personnel', () => import('../components/common/app-mob-department-personnel/app-mob-department-personnel.vue'));
        v.component('app-mob-group-picker', () => import('../components/common/app-mob-group-picker/app-mob-group-picker.vue'));
        v.component('app-mob-group-select', () => import('../components/common/app-mob-group-select/app-mob-group-select.vue'));
        // 树选择
        v.component('app-tree', () => import('../components/common/app-tree/app-tree.vue'));
        v.component('app-rawitem', () => import('../components/common/app-rawitem/app-rawitem.vue'));
        //文件列表项
        v.component('app-mob-file-list-item', () => import('../components/common/app-mob-file-list-item/app-mob-file-list-item.vue'));
        v.component('app-mob-button', () => import('../components/common/app-mob-button/app-mob-button.vue'));
        v.component('app-default-editor', AppDefaultEditor);
        // ui组件 BEGIN
        // 按钮     
        // 视图组件
        v.component('app-view-shell', AppViewShell);
        v.component('app-default-mob-indexview', AppDefaultIndexView);
        v.component('app-default-mob-mdview', AppDefaultMobMdView);
        v.component('app-default-mob-editview', AppDefaultMobEditView);
        v.component('app-default-mob-portalview', AppDefaultMobPortalView);
        v.component('app-default-mob-chartview', AppDefaultMobChartView);
        v.component('app-default-mob-calendarview', AppDefaultMobCalendarView);
        v.component('app-default-mob-tabexpview', AppDefaultMobTabExpView);
        v.component('app-default-mob-listexpview', AppDefaultMobListExpView);
        v.component('app-default-mob-treeview', AppDefaultMobTreeView);
        v.component('app-default-mob-meditview', AppDefaultMobMeditView);
        v.component('app-default-mob-optview', AppDefaultMobOptView);
        v.component('app-default-mob-pickmdview', AppDefaultPickUpMDView);
        v.component('app-default-mob-pickview', AppDefaultPickUpView);
        v.component('app-default-mob-mpickview', AppDefaultMobMPickUpView);
        v.component('app-default-mob-deportalview', AppDefaultMobDePortalView);
        v.component('app-default-mob-pickuptreeview', AppDefaultMobPickUpTreeView);
        v.component('app-default-mob-wfdynaeditview', AppDefaultMobWFDynaEditView);
        v.component('app-default-mob-wfdynaactionview', AppDefaultMobWFDynaActionView);
        v.component('app-default-mob-wfdynaexpmdview', AppDefaultWfDynaExpMDView);
        v.component('app-default-mob-deredirectview', AppDefaultMobDeRedirectView);
        v.component('app-default-notsupportedview', AppDefaultNotSupportedView);
        // 部件组件
        v.component('app-default-mob-appmenu', AppDefaultMobAppMenu);
        v.component('app-default-mob-tabexppanel', AppDefaultMobTabExpPanel);
        v.component('app-default-mob-tabviewpanel', AppDefaultMobTabViewPanel);
        v.component('app-default-mob-meditviewpanel', AppDefaultMobMeditViewPanel);
        v.component('app-default-mob-dashboard', AppDefaultMobDashboard);
        v.component('app-default-mob-portlet', AppDefaultMobPortlet);
        v.component('app-default-mob-mdctrl', AppDefaultMobMDCtrl);
        v.component('app-default-mob-calendar', AppDefaultMobCalendar);
        v.component('app-control-shell', AppControlShell);
        v.component('app-default-mob-form', AppDefaultMobForm);
        v.component('app-default-mob-searchform', AppDefaultMobSearchForm);
        v.component('view-toolbar', ViewToolbar);
        v.component('app-default-mob-chart', AppDefaultMobChart);
        v.component('app-default-mob-listexpbar', AppDefaultMobListExpBar);
        v.component('app-default-mob-tree', AppDefaultMobTree);
        v.component('app-default-mob-pickupviewpanel', AppDefaultMobPickUpViewPanel);
        v.component('app-default-mob-contextmenu', AppDefaultMobContextMenu);
        v.component('app-default-mob-panel', AppDefaultMobPanel);
        v.component('app-default-notsupportedcontrol', AppDefaultNotSupportedControl);
    }
}