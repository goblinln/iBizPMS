import IbzMyTerritoryMyWork from '@/widgets/ibz-my-territory/my-work-portlet/my-work-portlet.vue';
import CaseMainDetail from '@/widgets/case/main-detail-portlet/main-detail-portlet.vue';
import CaseDashboardQuickAction from '@/widgets/case/dashboard-quick-action-portlet/dashboard-quick-action-portlet.vue';
import CaseMeCreateCase from '@/widgets/case/me-create-case-portlet/me-create-case-portlet.vue';
import CaseMainInfo from '@/widgets/case/main-info-portlet/main-info-portlet.vue';
import ProductProductMainToolbar from '@/widgets/product/product-main-toolbar-portlet/product-main-toolbar-portlet.vue';
import ProductProductInfo from '@/widgets/product/product-info-portlet/product-info-portlet.vue';
import ProductProductStatusChart from '@/widgets/product/product-status-chart-portlet/product-status-chart-portlet.vue';
import ProductProductStatus from '@/widgets/product/product-status-portlet/product-status-portlet.vue';
import ProductProductExpView from '@/widgets/product/product-exp-view-portlet/product-exp-view-portlet.vue';
import ProductUnClosedProduct from '@/widgets/product/un-closed-product-portlet/un-closed-product-portlet.vue';
import ProductTextExpView from '@/widgets/product/text-exp-view-portlet/text-exp-view-portlet.vue';
import BurnBurnDownChart from '@/widgets/burn/burn-down-chart-portlet/burn-down-chart-portlet.vue';
import ActionActionHistory from '@/widgets/action/action-history-portlet/action-history-portlet.vue';
import ActionAllTrends from '@/widgets/action/all-trends-portlet/all-trends-portlet.vue';
import ActionProjectTrendsTimeline from '@/widgets/action/project-trends-timeline-portlet/project-trends-timeline-portlet.vue';
import ActionProductTrendsTimeline from '@/widgets/action/product-trends-timeline-portlet/product-trends-timeline-portlet.vue';
import CompanyCompanyInfo from '@/widgets/company/company-info-portlet/company-info-portlet.vue';
import StoryBaseInfo from '@/widgets/story/base-info-portlet/base-info-portlet.vue';
import StoryAssignedToMeStory from '@/widgets/story/assigned-to-me-story-portlet/assigned-to-me-story-portlet.vue';
import StoryMainToolbar from '@/widgets/story/main-toolbar-portlet/main-toolbar-portlet.vue';
import StoryStoryRelated from '@/widgets/story/story-related-portlet/story-related-portlet.vue';
import StoryBaseInfo_EditMode from '@/widgets/story/base-info-edit-mode-portlet/base-info-edit-mode-portlet.vue';
import StoryStoryspec from '@/widgets/story/storyspec-portlet/storyspec-portlet.vue';
import ProjectUnClosedProject from '@/widgets/project/un-closed-project-portlet/un-closed-project-portlet.vue';
import ProjectProjectMainToolbar from '@/widgets/project/project-main-toolbar-portlet/project-main-toolbar-portlet.vue';
import ProjectDashBoradInfoView from '@/widgets/project/dash-borad-info-view-portlet/dash-borad-info-view-portlet.vue';
import ProjectProjectStatusBar from '@/widgets/project/project-status-bar-portlet/project-status-bar-portlet.vue';
import ProjectProjectExpView from '@/widgets/project/project-exp-view-portlet/project-exp-view-portlet.vue';
import ProductLifeGetRoadmap from '@/widgets/product-life/get-roadmap-portlet/get-roadmap-portlet.vue';
import TaskToolbar from '@/widgets/task/toolbar-portlet/toolbar-portlet.vue';
import TaskMainInfo from '@/widgets/task/main-info-portlet/main-info-portlet.vue';
import TaskMainDetail from '@/widgets/task/main-detail-portlet/main-detail-portlet.vue';
import TaskWorkInfo from '@/widgets/task/work-info-portlet/work-info-portlet.vue';
import TaskAssignToMe from '@/widgets/task/assign-to-me-portlet/assign-to-me-portlet.vue';
import TestSuiteCurSuitCaseGridView from '@/widgets/test-suite/cur-suit-case-grid-view-portlet/cur-suit-case-grid-view-portlet.vue';
import TestSuiteMainInfoView from '@/widgets/test-suite/main-info-view-portlet/main-info-view-portlet.vue';
import TestTaskMainInfo from '@/widgets/test-task/main-info-portlet/main-info-portlet.vue';
import TestTaskToTestTestTask from '@/widgets/test-task/to-test-test-task-portlet/to-test-test-task-portlet.vue';
import TestTaskActionBar from '@/widgets/test-task/action-bar-portlet/action-bar-portlet.vue';
import TestTaskMainDetail from '@/widgets/test-task/main-detail-portlet/main-detail-portlet.vue';
import ProjectStatsStatsInfo from '@/widgets/project-stats/stats-info-portlet/stats-info-portlet.vue';
import ProjectStatsTASKTIME from '@/widgets/project-stats/tasktime-portlet/tasktime-portlet.vue';
import BugDashboardBugLife from '@/widgets/bug/dashboard-bug-life-portlet/dashboard-bug-life-portlet.vue';
import BugActionHistoryList from '@/widgets/bug/action-history-list-portlet/action-history-list-portlet.vue';
import BugDashboardBugMain from '@/widgets/bug/dashboard-bug-main-portlet/dashboard-bug-main-portlet.vue';
import BugAssignedToMeBug from '@/widgets/bug/assigned-to-me-bug-portlet/assigned-to-me-bug-portlet.vue';
import BugBugDashboardActions from '@/widgets/bug/bug-dashboard-actions-portlet/bug-dashboard-actions-portlet.vue';
import BugStepsInfo from '@/widgets/bug/steps-info-portlet/steps-info-portlet.vue';

export const  PortletComponent = {
    install(v: any, opt: any) {
        v.component('app-bug-steps-info-portlet', BugStepsInfo);
        v.component('app-bug-action-history-list-portlet', BugActionHistoryList);
        v.component('app-bug-bug-dashboard-actions-portlet', BugBugDashboardActions);
        v.component('app-bug-dashboard-bug-main-portlet', BugDashboardBugMain);
        v.component('app-bug-dashboard-bug-life-portlet', BugDashboardBugLife);
        v.component('app-story-storyspec-portlet', StoryStoryspec);
        v.component('app-action-action-history-portlet', ActionActionHistory);
        v.component('app-story-main-toolbar-portlet', StoryMainToolbar);
        v.component('app-story-base-info-portlet', StoryBaseInfo);
        v.component('app-story-story-related-portlet', StoryStoryRelated);
        v.component('app-task-main-detail-portlet', TaskMainDetail);
        v.component('app-task-toolbar-portlet', TaskToolbar);
        v.component('app-task-main-info-portlet', TaskMainInfo);
        v.component('app-task-work-info-portlet', TaskWorkInfo);
        v.component('app-case-main-info-portlet', CaseMainInfo);
        v.component('app-case-dashboard-quick-action-portlet', CaseDashboardQuickAction);
        v.component('app-case-main-detail-portlet', CaseMainDetail);
        v.component('app-test-task-main-detail-portlet', TestTaskMainDetail);
        v.component('app-test-task-action-bar-portlet', TestTaskActionBar);
        v.component('app-test-task-main-info-portlet', TestTaskMainInfo);
        v.component('app-story-base-info-edit-mode-portlet', StoryBaseInfo_EditMode);
        v.component('app-burn-burn-down-chart-portlet', BurnBurnDownChart);
        v.component('app-action-project-trends-timeline-portlet', ActionProjectTrendsTimeline);
        v.component('app-project-project-main-toolbar-portlet', ProjectProjectMainToolbar);
        v.component('app-project-dash-borad-info-view-portlet', ProjectDashBoradInfoView);
        v.component('app-product-product-exp-view-portlet', ProductProductExpView);
        v.component('app-product-un-closed-product-portlet', ProductUnClosedProduct);
        v.component('app-product-product-status-chart-portlet', ProductProductStatusChart);
        v.component('app-story-assigned-to-me-story-portlet', StoryAssignedToMeStory);
        v.component('app-company-company-info-portlet', CompanyCompanyInfo);
        v.component('app-product-life-get-roadmap-portlet', ProductLifeGetRoadmap);
        v.component('app-action-product-trends-timeline-portlet', ActionProductTrendsTimeline);
        v.component('app-product-product-main-toolbar-portlet', ProductProductMainToolbar);
        v.component('app-product-product-info-portlet', ProductProductInfo);
        v.component('app-test-suite-cur-suit-case-grid-view-portlet', TestSuiteCurSuitCaseGridView);
        v.component('app-test-suite-main-info-view-portlet', TestSuiteMainInfoView);
        v.component('app-product-product-status-portlet', ProductProductStatus);
        v.component('app-action-all-trends-portlet', ActionAllTrends);
        v.component('app-bug-assigned-to-me-bug-portlet', BugAssignedToMeBug);
        v.component('app-task-assign-to-me-portlet', TaskAssignToMe);
        v.component('app-product-text-exp-view-portlet', ProductTextExpView);
        v.component('app-project-project-exp-view-portlet', ProjectProjectExpView);
        v.component('app-project-un-closed-project-portlet', ProjectUnClosedProject);
        v.component('app-project-project-status-bar-portlet', ProjectProjectStatusBar);
        v.component('app-project-stats-tasktime-portlet', ProjectStatsTASKTIME);
        v.component('app-project-stats-stats-info-portlet', ProjectStatsStatsInfo);
        v.component('app-ibz-my-territory-my-work-portlet', IbzMyTerritoryMyWork);
        v.component('app-test-task-to-test-test-task-portlet', TestTaskToTestTestTask);
        v.component('app-case-me-create-case-portlet', CaseMeCreateCase);
        v.component('case-main-detail-portlet', CaseMainDetail);
        v.component('case-dashboard-quick-action-portlet', CaseDashboardQuickAction);
        v.component('case-me-create-case-portlet', CaseMeCreateCase);
        v.component('case-main-info-portlet', CaseMainInfo);
        v.component('product-product-main-toolbar-portlet', ProductProductMainToolbar);
        v.component('product-product-info-portlet', ProductProductInfo);
        v.component('product-product-status-chart-portlet', ProductProductStatusChart);
        v.component('product-product-status-portlet', ProductProductStatus);
        v.component('product-product-exp-view-portlet', ProductProductExpView);
        v.component('product-un-closed-product-portlet', ProductUnClosedProduct);
        v.component('product-text-exp-view-portlet', ProductTextExpView);
        v.component('burn-burn-down-chart-portlet', BurnBurnDownChart);
        v.component('action-action-history-portlet', ActionActionHistory);
        v.component('action-all-trends-portlet', ActionAllTrends);
        v.component('action-project-trends-timeline-portlet', ActionProjectTrendsTimeline);
        v.component('action-product-trends-timeline-portlet', ActionProductTrendsTimeline);
        v.component('company-company-info-portlet', CompanyCompanyInfo);
        v.component('story-base-info-portlet', StoryBaseInfo);
        v.component('story-assigned-to-me-story-portlet', StoryAssignedToMeStory);
        v.component('story-main-toolbar-portlet', StoryMainToolbar);
        v.component('story-story-related-portlet', StoryStoryRelated);
        v.component('story-base-info-edit-mode-portlet', StoryBaseInfo_EditMode);
        v.component('story-storyspec-portlet', StoryStoryspec);
        v.component('project-un-closed-project-portlet', ProjectUnClosedProject);
        v.component('project-project-main-toolbar-portlet', ProjectProjectMainToolbar);
        v.component('project-dash-borad-info-view-portlet', ProjectDashBoradInfoView);
        v.component('project-project-status-bar-portlet', ProjectProjectStatusBar);
        v.component('project-project-exp-view-portlet', ProjectProjectExpView);
        v.component('product-life-get-roadmap-portlet', ProductLifeGetRoadmap);
        v.component('task-toolbar-portlet', TaskToolbar);
        v.component('task-main-info-portlet', TaskMainInfo);
        v.component('task-main-detail-portlet', TaskMainDetail);
        v.component('task-work-info-portlet', TaskWorkInfo);
        v.component('task-assign-to-me-portlet', TaskAssignToMe);
        v.component('test-suite-cur-suit-case-grid-view-portlet', TestSuiteCurSuitCaseGridView);
        v.component('test-suite-main-info-view-portlet', TestSuiteMainInfoView);
        v.component('test-task-main-info-portlet', TestTaskMainInfo);
        v.component('test-task-to-test-test-task-portlet', TestTaskToTestTestTask);
        v.component('test-task-action-bar-portlet', TestTaskActionBar);
        v.component('test-task-main-detail-portlet', TestTaskMainDetail);
        v.component('project-stats-stats-info-portlet', ProjectStatsStatsInfo);
        v.component('project-stats-tasktime-portlet', ProjectStatsTASKTIME);
        v.component('bug-dashboard-bug-life-portlet', BugDashboardBugLife);
        v.component('bug-action-history-list-portlet', BugActionHistoryList);
        v.component('bug-dashboard-bug-main-portlet', BugDashboardBugMain);
        v.component('bug-assigned-to-me-bug-portlet', BugAssignedToMeBug);
        v.component('bug-bug-dashboard-actions-portlet', BugBugDashboardActions);
        v.component('bug-steps-info-portlet', BugStepsInfo);
    }
};