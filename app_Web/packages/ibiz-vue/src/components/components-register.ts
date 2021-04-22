// 注册插件
import Vue from 'vue';
// 预置组件
import InputBox from './common/input-box/input-box.vue';
import AppKeepAlive from './common/app-keep-alive/app-keep-alive.vue'
import AppLang from './common/app-lang/app-lang.vue'
import AppTheme from './common/app-theme/app-theme.vue'
import AppUser from './common/app-user/app-user.vue'
import AppForm from './common/app-form/app-form.vue'
import APPAutocomplete from './common/app-autocomplete/app-autocomplete.vue'
import AppFormDruipart from './common/app-form-druipart/app-form-druipart.vue'
import DropdownList from './common/dropdown-list/dropdown-list.vue'
import UploadFile from './common/upload-file/upload-file.vue'
import ContextMenuContainer from './common/context-menu-container/context-menu-container.vue'
import AppCheckboxList from './common/app-checkbox-list/app-checkbox-list.vue'
import AppRadioGroup from './common/app-radio-group/app-radio-group.vue'
import AppEmbedPicker from './common/app-embed-picker/app-embed-picker.vue'
import AppTreePicker from './common/app-tree-picker/app-tree-picker.vue'
import AppFileUpload from './common/app-file-upload/app-file-upload.vue'
import AppImageUpload from './common/app-image-upload/app-image-upload.vue'
import PropertyLayout from './common/property-layout/property-layout.vue'
import AppRangeEditor from './common/app-range-editor/app-range-editor.vue'
import AppExportExcel from './common/app-export-excel/app-export-excel.vue'
import AppFormGroup from './common/app-form-group/app-form-group.vue'
import AppFormItem from './common/app-form-item/app-form-item.vue'
import AppPicker from './common/app-picker/app-picker.vue'
import AppMpicker from './common/app-mpicker/app-mpicker.vue'
import AppUpicker from './common/app-upicker/app-upicker.vue'
import AppFormGroup2 from './common/app-form-group2/app-form-group2.vue'
import AppFormItem2 from './common/app-form-item2/app-form-item2.vue'
import CodeList from './common/codelist/codelist.vue'
import AppQuickMenus from './common/app-quick-menus/app-quick-menus.vue'
import AppCheckbox from './common/app-checkbox/app-checkbox.vue'
import AppColumnRender from './common/app-column-render/app-column-render.vue'
import AppPickerSelectView from './common/app-picker-select-view/app-picker-select-view.vue'
import AppSiderMenus from './common/app-sider-menus/app-sider-menus.vue'
import AppSpan from './common/app-span/app-span.vue'
import AppAddressSelection from './common/app-address-selection/app-address-selection.vue'
import DropdownListMpicker from './common/dropdown-list-mpicker/dropdown-list-mpicker.vue'
import AppRate from './common/app-rate/app-rate.vue'
import AppSwitch from './common/app-switch/app-switch.vue'
import AppSlider from './common/app-slider/app-slider.vue'
import AppStepper from './common/app-stepper/app-stepper.vue'
import DatePickerRange from './common/date-picker-range/date-picker-range.vue'
import AppRangeDate from './common/app-range-date/app-range-date.vue'
import AppActionBar from './common/app-actionbar/app-actionbar.vue'
import AppOrgSector from './common/app-orgsector/app-orgsector.vue'
import AppBuild from './common/app-build/app-build.vue'
import AppStudioAction from './common/app-studioaction/app-studioaction.vue'
import AppDebugActions from './common/app-debug-actions/app-debug-actions.vue'
import AppHeaderMenus from './common/app-header-menus/app-header-menus.vue'
import AppColumnLink from './common/app-column-link/app-column-link.vue'
import DropDownListDynamic from './common/dropdown-list-dynamic/dropdown-list-dynamic.vue'
import AppImagePreview from './common/app-image-preview/app-image-preview.vue'
import AppFormatData from './common/app-format-data/app-format-data.vue'
import AppUploadFileInfo from './common/app-upload-file-info/app-upload-file-info.vue'
import { ContextMenu } from './common/context-menu/context-menu'
import AppColumnFormat from './common/app-column-format/app-column-format.vue'
import AppQuickGroup from './common/app-quick-group/app-quick-group.vue'
import AppGroupPicker from './common/app-group-picker/app-group-picker.vue'
import AppWFApproval from './common/app-wf-approval/app-wf-approval.vue'
import Breadcrumb from './common/app-breadcrumb/app-breadcrumb.vue'
import AppTransfer from './common/app-transfer/app-transfer.vue'
import ContextMenuDrag from './common/context-menu-drag/context-menu-drag.vue'
import AppOrgSelect from './common/app-org-select/app-org-select.vue'
import AppDepartmentSelect from './common/app-department-select/app-department-select.vue'
import AppGroupSelect from './common/app-group-select/app-group-select.vue'
import UpdatePwd from './common/app-update-password/app-update-password.vue'
import AppMenuItem from './common/app-menu-item/app-menu-item.vue'
import AppFullScren from './common/app-full-scren/app-full-scren.vue'
import AppLockScren from './common/app-lock-scren/app-lock-scren.vue'
import ActionTimeline from './common/action-timeline/action-timeline.vue'
import ExtendActionTimeline from './common/extend-action-timeline/extend-action-timeline.vue'
import CronEditor from './common/cron-editor/cron-editor.vue'
import AppMessagePopover from './common/app-message-popover/app-message-popover.vue'
import AppPanelField from './common/app-panel-field/app-panel-field.vue'
import AppPanelButton from './common/app-panel-button/app-panel-button.vue'
import AppDepartmentPersonnel from './common/app-department-personnel/app-department-personnel.vue'
import DiskFileUpload from './common/disk-file-upload/disk-file-upload.vue'
import AvueCustomForm from './common/avue-custom-form/avue-custom-form.vue'
import DiskImageUpload from './common/disk-image-upload/disk-image-upload.vue'
import AppFormPart from './common/app-form-part/app-form-part.vue'
import AppAlert from './common/app-alert/app-alert.vue'
import AppAlertGroup from './common/app-alert-group/app-alert-group.vue'
import AppRawItem from './common/app-rawitem/app-rawitem.vue'
import AppImageRomate from './common/app-image-romate/app-image-romate.vue'
import { MenuIcon } from './common/menu-icon/menu-icon'
import AppVuePivottable from './common/app-vue-pivottable/app-vue-pivottable.vue';
import AppMapPosition from './common/app-map-position/app-map-position.vue';
import AppSortBar from './common/app-sort-bar/app-sort-bar.vue';
import AppAfterTime from './common/app-after-time/app-after-time.vue';
import AppInputIp from './common/app-input-ip/app-input-ip.vue';
import AppColorSpan from './common/app-color-span/app-color-span.vue';
import AppColorPicker from './common/app-color-picker/app-color-picker.vue';
import AppRichTextEditor from './common/app-rich-text-editor/app-rich-text-editor.vue';
import AppPortalDesign from './common/app-portal-design/app-portal-design.vue';
import AppNotSupportedEditor from './common/app-not-supported-editor/app-not-supported-editor.vue';
import { AppLayout } from './common/layout/app-layout/app-layout';
import { AppHeader } from './common/layout/app-header/app-header';
import { AppHeaderRightMenus } from './common/app-header-right-menus/app-header-right-menus';
import { AppContent } from './common/layout/app-content/app-content';
import { AppContentLeftExp } from './common/layout/app-content-left-exp/app-content-left-exp';
import { AppContentLeftNavMenu } from './common/layout/app-content-left-nav-menu/app-content-left-nav-menu';
import { AppContentBottomExp } from './common/layout/app-content-bottom-exp/app-content-bottom-exp';
import { AppFooter } from './common/layout/app-footer/app-footer';
import TabPageExpStyle2 from './common/tab-page-exp-style2/tab-page-exp-style2.vue';
import FilterTree from './common/filter-tree/filter-tree.vue';
import DropdownListHidden from './common/dropdown-list-hidden/dropdown-list-hidden.vue'
import HtmlContainer from './common/html-container/html-container.vue';
import AppFileUploadCamera from './common/app-file-upload-camera/app-file-upload-camera';
import AppFormGroupDataPanel from './common/app-form-group-data-panel/app-form-group-data-panel';
import TextFileUpload from './common/text-file-upload/text-file-upload.vue';
import AppCommonMicrocom from './common/app-common-microcom/app-common-microcom.vue';
import AppCalendarTimeline from './common/app-calendar-timeline/app-calendar-timeline.vue';

// 视图组件
import { AppViewShell } from '../view-container/app-view-shell';
import { AppDefaultIndexView } from './view/app-default-view/app-default-indexview';
import { AppDefaultEditView } from './view/app-default-view/app-default-editview';
import { AppDefaultListView } from './view/app-default-view/app-default-listview';
import { AppDefaultGridView } from './view/app-default-view/app-default-gridview';
import { AppDefaultChartView } from './view/app-default-view/app-default-chartview';
import { AppDefaultKanbanView } from './view/app-default-view/app-default-kanbanview';
import { AppDefaultCalendarView } from './view/app-default-view/app-default-calendarview';
import { AppDefaultDataViewView } from './view/app-default-view/app-default-dataview';
import { AppDefaultDePortalView } from './view/app-default-view/app-default-deportalview';
import { AppDefaultPortalView } from './view/app-default-view/app-default-portalview';
import { AppDefaultTabExpView } from './view/app-default-view/app-default-tabexpview';
import { AppDefaultNotSupportedView } from './view/app-default-view/app-default-notsupportedview';
import { AppDefaultMEditView } from './view/app-default-view/app-default-meditview';
import { AppDefaultGanttView } from './view/app-default-view/app-default-ganttview';
import { AppDefaultTreeView } from './view/app-default-view/app-default-treeview';
import { AppDefaultTreeGridExView } from './view/app-default-view/app-default-treegridexview';
import { AppDefaultListExpView } from './view/app-default-view/app-default-listexpview';
import { AppDefaultGridExpView } from './view/app-default-view/app-default-gridexpview';
import { AppDefaultWfDynaExpGridView } from './view/app-default-view/app-default-wfdynaexpgridview';
import { AppDefaultDataViewExpView } from './view/app-default-view/app-default-dataviewexpview';
import { AppDefaultCalendarExpView } from './view/app-default-view/app-default-calendarexpview';
import { AppDefaultPickupGridView } from './view/app-default-view/app-default-pickupgridview';
import { AppDefaultMPickUpView } from './view/app-default-view/app-default-mpickupview';
import { AppDefaultPickupView } from './view/app-default-view/app-default-pickupview';
import { AppDefaultTreeExpView } from './view/app-default-view/app-default-treeexpview';
import { AppDefaultPickupTreeView } from './view/app-default-view/app-default-pickuptreeview';
import { AppDefaultCustomView } from './view/app-default-view/app-default-customview';
import { AppDefaultOptView } from './view/app-default-view/app-default-optview';
import { AppDefaultWFDynaEditView } from './view/app-default-view/app-default-wfdynaeditview';
import { AppDefaultWFDynaActionView } from './view/app-default-view/app-default-wfdynaactionview';
import { AppDefaultFormPickupDataView } from './view/app-default-view/app-default-formpickupdataview';
import { AppDefaultIndexPickupDataView } from './view/app-default-view/app-default-indexpickupdataview';
import { AppDefaultWFDynaStartView } from './view/app-default-view/app-default-wfdynastartview';
import { AppDefaultWizardView } from './view/app-default-view/app-default-wizardview';
import { AppDefaultWFActionView } from './view/app-default-view/app-default-wfactionview';
import { AppDefaultDeRedirectView } from './view/app-default-view/app-default-deredirectview';
import { AppStyle2IndexView } from './view/app-style2-view/app-style2-indexview';
import { AppStyle2EditView } from './view/app-style2-view/app-style2-editview';
import { AppStyle2ListView } from './view/app-style2-view/app-style2-listview';
import { AppStyle2GridView } from './view/app-style2-view/app-style2-gridview';
import { AppStyle2ChartView } from './view/app-style2-view/app-style2-chartview';
import { AppStyle2KanbanView } from './view/app-style2-view/app-style2-kanbanview';
import { AppStyle2CalendarView } from './view/app-style2-view/app-style2-calendarview';
import { AppStyle2DataViewView } from './view/app-style2-view/app-style2-dataview';
import { AppStyle2DePortalView } from './view/app-style2-view/app-style2-deportalview';
import { AppStyle2PortalView } from './view/app-style2-view/app-style2-portalview';
import { AppStyle2TabExpView } from './view/app-style2-view/app-style2-tabexpview';
import { AppStyle2MEditView } from './view/app-style2-view/app-style2-meditview';
import { AppStyle2GanttView } from './view/app-style2-view/app-style2-ganttview';
import { AppStyle2TreeView } from './view/app-style2-view/app-style2-treeview';
import { AppStyle2TreeGridExView } from './view/app-style2-view/app-style2-treegridexview';
import { AppStyle2ListExpView } from './view/app-style2-view/app-style2-listexpview';
import { AppStyle2GridExpView } from './view/app-style2-view/app-style2-gridexpview';
import { AppStyle2WfDynaExpGridView } from './view/app-style2-view/app-style2-wfdynaexpgridview';
import { AppStyle2DataViewExpView } from './view/app-style2-view/app-style2-dataviewexpview';
import { AppStyle2CalendarExpView } from './view/app-style2-view/app-style2-calendarexpview';
import { AppStyle2PickupGridView } from './view/app-style2-view/app-style2-pickupgridview';
import { AppStyle2MPickUpView } from './view/app-style2-view/app-style2-mpickupview';
import { AppStyle2PickupView } from './view/app-style2-view/app-style2-pickupview';
import { AppStyle2TreeExpView } from './view/app-style2-view/app-style2-treeexpview';
import { AppStyle2PickupTreeView } from './view/app-style2-view/app-style2-pickuptreeview';
import { AppStyle2CustomView } from './view/app-style2-view/app-style2-customview';
import { AppStyle2OptView } from './view/app-style2-view/app-style2-optview';
import { AppStyle2WFDynaEditView } from './view/app-style2-view/app-style2-wfdynaeditview';
import { AppStyle2WFDynaActionView } from './view/app-style2-view/app-style2-wfdynaactionview';
import { AppStyle2HtmlView } from './view/app-style2-view/app-style2-htmlview';
import { AppStyle2FormPickupDataView } from './view/app-style2-view/app-style2-formpickupdataview';
import { AppStyle2IndexPickupDataView } from './view/app-style2-view/app-style2-indexpickupdataview';
import { AppStyle2WFDynaStartView } from './view/app-style2-view/app-style2-wfdynastartview';
import { AppStyle2WizardView } from './view/app-style2-view/app-style2-wizardview';
import { AppStyle2WFActionView } from './view/app-style2-view/app-style2-wfactionview';
import { AppStyle2DeRedirectView } from './view/app-style2-view/app-style2-deredirectview';
// 部件组件
import { ViewToolbar } from './control/view-toolbar/view-toolbar';
import { AppDefaultGrid } from './control/app-default-grid/app-default-grid';
import { AppDefaultChart } from './control/app-default-chart/app-default-chart';
import { AppDefaultAppmenu } from './control/app-default-appmenu/app-default-appmenu';
import { AppDefaultForm } from './control/app-default-form/app-default-form';
import { AppDefaultSearchForm } from './control/app-default-searchform/app-default-searchform';
import { AppDefaultEditor } from './editor/app-default-editor';
import { AppDefaultList } from './control/app-default-list/app-default-list';
import { AppControlShell } from './control/app-control-shell/app-control-shell';
import { AppDefaultDataView } from './control/app-default-dataview/app-default-dataview';
import { AppDefaultKanban } from './control/app-default-kanban/app-default-kanban';
import { AppDefaultPanel } from './control/app-default-panel/app-default-panel';
import { AppDefaultCalendar } from './control/app-default-calendar/app-default-calendar';
import { AppDefaultDashboard } from './control/app-default-dashboard/app-default-dashboard';
import { AppDefaultTabExpPanel } from './control/app-default-tab-exp-panel/app-default-tab-exp-panel';
import { AppDefaultTabViewPanel } from './control/app-default-tab-view-panel/app-default-tab-view-panel';
import { AppDefaultPortlet } from './control/app-default-portlet/app-default-portlet';
import { AppDefaultNotSupportedControl } from './control/app-default-notsupportedcontrol/app-default-notsupportedcontrol';
import { AppDefaultContextMenu } from './control/app-default-contextmenu/app-default-contextmenu';
import { AppDefaultGantt } from './control/app-default-gantt/app-default-gantt';
import { AppDefaultMEditViewPanel } from './control/app-default-meditviewpanel/app-default-meditviewpanel';
import { AppDefaultTree } from './control/app-default-treeview/app-default-treeview';
import { AppDefaultTreeGridEx } from './control/app-default-tree-grid-ex/app-default-tree-grid-ex';
import { AppDefaultListExpBar } from './control/app-default-list-exp-bar/app-default-list-exp-bar';
import { AppDefaultGridExpBar } from './control/app-default-grid-exp-bar/app-default-grid-exp-bar';
import { AppDefaultDataViewExpBar } from './control/app-default-dataview-exp-bar/app-default-dataview-exp-bar';
import { AppDefaultCalendarExpBar } from './control/app-default-calendar-exp-bar/app-default-calendar-exp-bar';
import { AppDefaultPickUpViewPanel } from './control/app-default-pick-up-view-panel/app-default-pick-up-view-panel';
import { AppDefaultTreeExpBar } from './control/app-default-tree-exp-bar/app-default-tree-exp-bar';
import { AppTimeLineCalendar } from './control/app-timeline-calendar/app-timeline-calendar';
import { AppPivotTable } from './control/app-pivot-table/app-pivot-table';
import { StudioView } from './common/studio-view/studio-view';
import { StudioViewStyle2 } from './common/studio-view-style2/studio-view-style2';
import { AppDefaultSearchBar } from './control/app-default-searchbar/app-default-searchbar';
import { AppDefaultWizardPanel } from './control/app-default-wizard-panel/app-default-wizard-panel';
import { AppDefaultStateWizardPanel } from './control/app-default-statewizard-panel/app-default-statewizard-panel';
import { NotificationSignal } from '../directives';
export const ComponentsRegister = {
    install(v: any, opt: any) {
        v.component('app-calendar-timeline', AppCalendarTimeline);
        v.component('app-common-microcom', AppCommonMicrocom);
        v.component('dropdown-list-hidden', DropdownListHidden);
        v.component('tab-page-exp-style2', TabPageExpStyle2);
        v.component('app-layout', AppLayout);
        v.component('app-header', AppHeader);
        v.component('app-header-right-menus', AppHeaderRightMenus);
        v.component('app-content', AppContent);
        v.component('app-content-left-exp', AppContentLeftExp);
        v.component('app-content-left-nav-menu', AppContentLeftNavMenu);
        v.component('app-content-bottom-exp', AppContentBottomExp);
        v.component('app-footer', AppFooter);
        v.component('app-department-personnel', AppDepartmentPersonnel);
        v.component('app-panel-button', AppPanelButton);
        v.component('app-panel-field', AppPanelField);
        v.component('app-full-scren', AppFullScren);
        v.component('app-lock-scren', AppLockScren);
        v.component('input-box', InputBox);
        v.component('app-keep-alive', AppKeepAlive);
        v.component('app-lang', AppLang);
        v.component('app-theme', AppTheme);
        v.component('app-user', AppUser);
        v.component('app-form', AppForm);
        v.component('app-autocomplete', APPAutocomplete);
        v.component('app-form-druipart', AppFormDruipart);
        v.component('dropdown-list', DropdownList);
        v.component('upload-file', UploadFile);
        v.component('context-menu-container', ContextMenuContainer);
        v.component('app-checkbox-list', AppCheckboxList);
        v.component('app-radio-group', AppRadioGroup);
        v.component('app-embed-picker', AppEmbedPicker);
        v.component('app-tree-picker', AppTreePicker);
        v.component('app-rich-text-editor', AppRichTextEditor);
        v.component('app-file-upload', AppFileUpload);
        v.component('app-image-upload', AppImageUpload);
        v.component('property-layout', PropertyLayout);
        v.component('app-range-editor', AppRangeEditor);
        v.component('app-export-excel', AppExportExcel);
        v.component('app-form-group', AppFormGroup);
        v.component('app-form-item', AppFormItem);
        v.component('app-picker', AppPicker);
        v.component('app-mpicker', AppMpicker);
        v.component('app-upicker', AppUpicker);
        v.component('app-form-group2', AppFormGroup2);
        v.component('app-form-item2', AppFormItem2);
        v.component('codelist', CodeList);
        v.component('app-quick-menus', AppQuickMenus);
        v.component('app-checkbox', AppCheckbox);
        v.component('app-column-render', AppColumnRender);
        v.component('app-picker-select-view', AppPickerSelectView);
        v.component('app-sider-menus', AppSiderMenus);
        v.component('app-span', AppSpan);
        v.component('app-address-selection', AppAddressSelection);
        v.component('dropdown-list-mpicker', DropdownListMpicker);
        v.component('app-rate', AppRate);
        v.component('app-switch', AppSwitch);
        v.component('app-slider', AppSlider);
        v.component('app-stepper', AppStepper);
        v.component('app-portal-design', AppPortalDesign);
        v.component('date-picker-range', DatePickerRange);
        v.component('app-range-date', AppRangeDate);
        v.component('app-actionbar', AppActionBar);
        v.component('app-orgsector', AppOrgSector);
        v.component('app-build', AppBuild);
        v.component('app-studioaction', AppStudioAction);
        v.component('app-debug-actions', AppDebugActions);
        v.component('app-header-menus', AppHeaderMenus);
        v.component('app-column-link', AppColumnLink);
        v.component('dropdown-list-dynamic', DropDownListDynamic);
        v.component('app-image-preview', AppImagePreview);
        v.component('app-format-data', AppFormatData);
        v.component('app-upload-file-info', AppUploadFileInfo);
        v.component('context-menu', ContextMenu);
        v.component('app-column-format', AppColumnFormat);
        v.component('app-quick-group', AppQuickGroup);
        v.component('app-org-select', AppOrgSelect);
        v.component('app-department-select', AppDepartmentSelect);
        v.component('app-group-select', AppGroupSelect);
        v.component('app-group-picker', AppGroupPicker);
        v.component('app-wf-approval', AppWFApproval);
        v.component('app-breadcrumb', Breadcrumb);
        v.component('app-transfer', AppTransfer);
        v.component('context-menu-drag', ContextMenuDrag);
        v.component('app-update-password', UpdatePwd);
        v.component('app-menu-item', AppMenuItem);
        v.component('action-timeline', ActionTimeline);
        v.component('extend-action-timeline', ExtendActionTimeline);
        v.component('cron-editor', CronEditor);
        v.component('app-message-popover', AppMessagePopover);
        v.component('disk-file-upload', DiskFileUpload);
        v.component('avue-custom-form', AvueCustomForm);
        v.component('disk-image-upload', DiskImageUpload);
        v.component('app-form-part', AppFormPart);
        v.component('app-alert', AppAlert);
        v.component('app-alert-group', AppAlertGroup);
        v.component('app-rawitem', AppRawItem);
        v.component('app-image-romate', AppImageRomate);
        v.component('menu-icon', MenuIcon);
        v.component('app-vue-pivottable', AppVuePivottable);
        v.component('app-map-position', AppMapPosition);
        v.component('app-sort-bar', AppSortBar);
        v.component('app-after-time', AppAfterTime);
        v.component('app-input-ip', AppInputIp);
        v.component('app-color-span', AppColorSpan);
        v.component('app-color-picker', AppColorPicker);
        v.component('app-not-supported-editor', AppNotSupportedEditor);
        v.component('studio-view', StudioView);
        v.component('studio-view-style2', StudioViewStyle2);
        v.component('filter-tree', FilterTree);
        v.component('html-container', HtmlContainer);
        v.component('app-file-upload-camera', AppFileUploadCamera);
        v.component('app-form-group-data-panel', AppFormGroupDataPanel);
        v.component('text-file-upload', TextFileUpload);
        // 视图组件
        v.component('app-view-shell', AppViewShell);
        v.component('app-default-indexview', AppDefaultIndexView);
        v.component('app-default-editview', AppDefaultEditView);
        v.component('app-default-listview', AppDefaultListView);
        v.component('app-default-gridview', AppDefaultGridView);
        v.component('app-default-chartview', AppDefaultChartView);
        v.component('app-default-kanbanview', AppDefaultKanbanView);
        v.component('app-default-calendarview', AppDefaultCalendarView);
        v.component('app-default-dataviewview', AppDefaultDataViewView);
        v.component('app-default-deportalview', AppDefaultDePortalView);
        v.component('app-default-portalview', AppDefaultPortalView);
        v.component('app-default-tabexpview', AppDefaultTabExpView);
        v.component('app-default-notsupportedview', AppDefaultNotSupportedView);
        v.component('app-default-meditview', AppDefaultMEditView);
        v.component('app-default-ganttview', AppDefaultGanttView);
        v.component('app-default-treeview', AppDefaultTreeView);
        v.component('app-default-treegridexview', AppDefaultTreeGridExView);
        v.component('app-default-listexpview', AppDefaultListExpView);
        v.component('app-default-gridexpview', AppDefaultGridExpView);
        v.component('app-default-wfdynaexpgridview', AppDefaultWfDynaExpGridView);
        v.component('app-default-dataviewexpview', AppDefaultDataViewExpView);
        v.component('app-default-calendarexpview', AppDefaultCalendarExpView);
        v.component('app-default-pickupgridview', AppDefaultPickupGridView);
        v.component('app-default-pickupview', AppDefaultPickupView);
        v.component('app-default-mpickupview', AppDefaultMPickUpView);
        v.component('app-default-treeexpview', AppDefaultTreeExpView);
        v.component('app-default-pickuptreeview', AppDefaultPickupTreeView);
        v.component('app-default-customview', AppDefaultCustomView);
        v.component('app-default-optview', AppDefaultOptView);
        v.component('app-default-wfdynaactionview', AppDefaultWFDynaActionView);
        v.component('app-default-wfdynaeditview', AppDefaultWFDynaEditView);
        v.component('app-default-formpickupdataview', AppDefaultFormPickupDataView);
        v.component('app-default-indexpickupdataview', AppDefaultIndexPickupDataView);
        v.component('app-default-wfdynastartview', AppDefaultWFDynaStartView);
        v.component('app-default-wizardview', AppDefaultWizardView);
        v.component('app-default-wfactionview', AppDefaultWFActionView);
        v.component("app-default-deredirectview",AppDefaultDeRedirectView);
        v.component('app-style2-indexview', AppStyle2IndexView);
        v.component('app-style2-editview', AppStyle2EditView);
        v.component('app-style2-listview', AppStyle2ListView);
        v.component('app-style2-gridview', AppStyle2GridView);
        v.component('app-style2-chartview', AppStyle2ChartView);
        v.component('app-style2-kanbanview', AppStyle2KanbanView);
        v.component('app-style2-calendarview', AppStyle2CalendarView);
        v.component('app-style2-dataviewview', AppStyle2DataViewView);
        v.component('app-style2-deportalview', AppStyle2DePortalView);
        v.component('app-style2-portalview', AppStyle2PortalView);
        v.component('app-style2-tabexpview', AppStyle2TabExpView);
        v.component('app-style2-meditview', AppStyle2MEditView);
        v.component('app-style2-ganttview', AppStyle2GanttView);
        v.component('app-style2-treeview', AppStyle2TreeView);
        v.component('app-style2-treegridexview', AppStyle2TreeGridExView);
        v.component('app-style2-listexpview', AppStyle2ListExpView);
        v.component('app-style2-gridexpview', AppStyle2GridExpView);
        v.component('app-style2-wfdynaexpgridview', AppStyle2WfDynaExpGridView);
        v.component('app-style2-dataviewexpview', AppStyle2DataViewExpView);
        v.component('app-style2-calendarexpview', AppStyle2CalendarExpView);
        v.component('app-style2-pickupgridview', AppStyle2PickupGridView);
        v.component('app-style2-pickupview', AppStyle2PickupView);
        v.component('app-style2-mpickupview', AppStyle2MPickUpView);
        v.component('app-style2-treeexpview', AppStyle2TreeExpView);
        v.component('app-style2-pickuptreeview', AppStyle2PickupTreeView);
        v.component('app-style2-customview', AppStyle2CustomView);
        v.component('app-style2-optview', AppStyle2OptView);
        v.component('app-style2-wfdynaactionview', AppStyle2WFDynaActionView);
        v.component('app-style2-wfdynaeditview', AppStyle2WFDynaEditView);
        v.component('app-style2-htmlview', AppStyle2HtmlView);
        v.component('app-style2-formpickupdataview', AppStyle2FormPickupDataView);
        v.component('app-style2-indexpickupdataview', AppStyle2IndexPickupDataView);
        v.component('app-style2-wfdynastartview', AppStyle2WFDynaStartView);
        v.component('app-style2-wizardview', AppStyle2WizardView);
        v.component('app-style2-wfactionview', AppStyle2WFActionView);
        v.component("app-style2-deredirectview",AppStyle2DeRedirectView);
        // 部件组件
        v.component('app-default-form', AppDefaultForm);
        v.component('app-default-searchform', AppDefaultSearchForm);
        v.component('app-default-editor', AppDefaultEditor);
        v.component('view-toolbar', ViewToolbar);
        v.component('app-default-list', AppDefaultList);
        v.component('app-default-grid', AppDefaultGrid);
        v.component('app-default-chart', AppDefaultChart);
        v.component('app-default-appmenu', AppDefaultAppmenu);
        v.component('app-control-shell', AppControlShell);
        v.component('app-default-dataview', AppDefaultDataView);
        v.component('app-default-kanban', AppDefaultKanban);
        v.component('app-default-panel', AppDefaultPanel);
        v.component('app-default-calendar', AppDefaultCalendar);
        v.component('app-default-dashboard', AppDefaultDashboard);
        v.component('app-default-portlet', AppDefaultPortlet);
        v.component('app-default-tabexp-panel', AppDefaultTabExpPanel);
        v.component('app-default-tab-view-panel', AppDefaultTabViewPanel);
        v.component('app-default-notsupportedcontrol', AppDefaultNotSupportedControl);
        v.component('app-default-contextmenu', AppDefaultContextMenu);
        v.component('app-default-gantt', AppDefaultGantt);
        v.component('app-default-meditViewPanel', AppDefaultMEditViewPanel);
        v.component('app-default-tree', AppDefaultTree);
        v.component('app-default-tree-grid-ex', AppDefaultTreeGridEx);
        v.component('app-default-list-exp-bar', AppDefaultListExpBar);
        v.component('app-default-grid-exp-bar', AppDefaultGridExpBar);
        v.component('app-default-dataview-exp-bar', AppDefaultDataViewExpBar);
        v.component('app-default-calendar-exp-bar', AppDefaultCalendarExpBar);
        v.component('app-default-pick-up-view-panel', AppDefaultPickUpViewPanel);
        v.component('app-default-tree-exp-bar', AppDefaultTreeExpBar);
        v.component('app-timeline-calendar', AppTimeLineCalendar);
        v.component('app-default-searchbar', AppDefaultSearchBar);
        v.component('app-default-wizard-panel', AppDefaultWizardPanel);
        v.component('app-default-state-wizard-panel', AppDefaultStateWizardPanel);
        v.component('app-pivot-table', AppPivotTable);
        // 注册指令
        v.directive('notification-signal', NotificationSignal);
    }
}