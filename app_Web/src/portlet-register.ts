import ProductProductExpView from '@/widgets/product/product-exp-view-portlet/product-exp-view-portlet.vue';
import ActionAllTrends from '@/widgets/action/all-trends-portlet/all-trends-portlet.vue';
import MyWork from '@/widgets/app/my-work-portlet/my-work-portlet.vue';
import BugAssignedToMeBug from '@/widgets/bug/assigned-to-me-bug-portlet/assigned-to-me-bug-portlet.vue';
import TaskAssignToMe from '@/widgets/task/assign-to-me-portlet/assign-to-me-portlet.vue';
import ProductTextExpView from '@/widgets/product/text-exp-view-portlet/text-exp-view-portlet.vue';
import ProjectProjectExpView from '@/widgets/project/project-exp-view-portlet/project-exp-view-portlet.vue';
import ProductProductStatusChart from '@/widgets/product/product-status-chart-portlet/product-status-chart-portlet.vue';
import TodoMyUpcoming from '@/widgets/todo/my-upcoming-portlet/my-upcoming-portlet.vue';
import CaseMeCreateCase from '@/widgets/case/me-create-case-portlet/me-create-case-portlet.vue';
import CaseMainDetail from '@/widgets/case/main-detail-portlet/main-detail-portlet.vue';
import CaseDashboardQuickAction from '@/widgets/case/dashboard-quick-action-portlet/dashboard-quick-action-portlet.vue';
import CaseMainInfo from '@/widgets/case/main-info-portlet/main-info-portlet.vue';
import ProductProductMainToolbar from '@/widgets/product/product-main-toolbar-portlet/product-main-toolbar-portlet.vue';
import ProductProductInfo from '@/widgets/product/product-info-portlet/product-info-portlet.vue';
import ProductUnClosedProduct from '@/widgets/product/un-closed-product-portlet/un-closed-product-portlet.vue';
import BurnBurnDownChart from '@/widgets/burn/burn-down-chart-portlet/burn-down-chart-portlet.vue';
import ActionActionHistory from '@/widgets/action/action-history-portlet/action-history-portlet.vue';
import ActionProjectTrendsTimeline from '@/widgets/action/project-trends-timeline-portlet/project-trends-timeline-portlet.vue';
import ActionProductTrendsTimeline from '@/widgets/action/product-trends-timeline-portlet/product-trends-timeline-portlet.vue';
import StoryBaseInfo from '@/widgets/story/base-info-portlet/base-info-portlet.vue';
import StoryAssignedToMeStory from '@/widgets/story/assigned-to-me-story-portlet/assigned-to-me-story-portlet.vue';
import StoryMainToolbar from '@/widgets/story/main-toolbar-portlet/main-toolbar-portlet.vue';
import StoryStoryRelated from '@/widgets/story/story-related-portlet/story-related-portlet.vue';
import StoryStoryspec from '@/widgets/story/storyspec-portlet/storyspec-portlet.vue';
import TodoTodoaction from '@/widgets/todo/todoaction-portlet/todoaction-portlet.vue';
import TodoTodoBase from '@/widgets/todo/todo-base-portlet/todo-base-portlet.vue';
import TodoTodoDesc from '@/widgets/todo/todo-desc-portlet/todo-desc-portlet.vue';
import TodoTodoDashboardActions from '@/widgets/todo/todo-dashboard-actions-portlet/todo-dashboard-actions-portlet.vue';
import ProjectUnClosedProject from '@/widgets/project/un-closed-project-portlet/un-closed-project-portlet.vue';
import ProjectProjectMainToolbar from '@/widgets/project/project-main-toolbar-portlet/project-main-toolbar-portlet.vue';
import ProjectDashBoradInfoView from '@/widgets/project/dash-borad-info-view-portlet/dash-borad-info-view-portlet.vue';
import ProjectProjectStatusBar from '@/widgets/project/project-status-bar-portlet/project-status-bar-portlet.vue';
import ProductLifeGetRoadmaps from '@/widgets/product-life/get-roadmaps-portlet/get-roadmaps-portlet.vue';
import TaskToolbar from '@/widgets/task/toolbar-portlet/toolbar-portlet.vue';
import TaskMainInfo from '@/widgets/task/main-info-portlet/main-info-portlet.vue';
import TaskMainDetail from '@/widgets/task/main-detail-portlet/main-detail-portlet.vue';
import TaskWorkInfo from '@/widgets/task/work-info-portlet/work-info-portlet.vue';
import TestSuiteCurSuitCaseGridView from '@/widgets/test-suite/cur-suit-case-grid-view-portlet/cur-suit-case-grid-view-portlet.vue';
import TestSuiteMainInfoView from '@/widgets/test-suite/main-info-view-portlet/main-info-view-portlet.vue';
import TestTaskMainInfo from '@/widgets/test-task/main-info-portlet/main-info-portlet.vue';
import TestTaskToTestTestTask from '@/widgets/test-task/to-test-test-task-portlet/to-test-test-task-portlet.vue';
import TestTaskActionBar from '@/widgets/test-task/action-bar-portlet/action-bar-portlet.vue';
import TestTaskMainDetail from '@/widgets/test-task/main-detail-portlet/main-detail-portlet.vue';
import BugDashboardBugLife from '@/widgets/bug/dashboard-bug-life-portlet/dashboard-bug-life-portlet.vue';
import BugActionHistoryList from '@/widgets/bug/action-history-list-portlet/action-history-list-portlet.vue';
import BugDashboardBugMain from '@/widgets/bug/dashboard-bug-main-portlet/dashboard-bug-main-portlet.vue';
import BugBugDashboardActions from '@/widgets/bug/bug-dashboard-actions-portlet/bug-dashboard-actions-portlet.vue';
import BugStepsInfo from '@/widgets/bug/steps-info-portlet/steps-info-portlet.vue';

export const  PortletComponent = {
    install(v: any, opt: any) {
        v.component('app-product-product-exp-view-portlet', ProductProductExpView);
        v.component('app-action-all-trends-portlet', ActionAllTrends);
        v.component('app-my-work-portlet', MyWork);
        v.component('app-bug-assigned-to-me-bug-portlet', BugAssignedToMeBug);
        v.component('app-task-assign-to-me-portlet', TaskAssignToMe);
        v.component('app-product-text-exp-view-portlet', ProductTextExpView);
        v.component('app-project-project-exp-view-portlet', ProjectProjectExpView);
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
        v.component('app-burn-burn-down-chart-portlet', BurnBurnDownChart);
        v.component('app-action-project-trends-timeline-portlet', ActionProjectTrendsTimeline);
        v.component('app-project-project-main-toolbar-portlet', ProjectProjectMainToolbar);
        v.component('app-project-dash-borad-info-view-portlet', ProjectDashBoradInfoView);
        v.component('app-product-un-closed-product-portlet', ProductUnClosedProduct);
        v.component('app-product-product-status-chart-portlet', ProductProductStatusChart);
        v.component('app-story-assigned-to-me-story-portlet', StoryAssignedToMeStory);
        v.component('app-product-life-get-roadmaps-portlet', ProductLifeGetRoadmaps);
        v.component('app-action-product-trends-timeline-portlet', ActionProductTrendsTimeline);
        v.component('app-product-product-main-toolbar-portlet', ProductProductMainToolbar);
        v.component('app-product-product-info-portlet', ProductProductInfo);
        v.component('app-test-suite-cur-suit-case-grid-view-portlet', TestSuiteCurSuitCaseGridView);
        v.component('app-test-suite-main-info-view-portlet', TestSuiteMainInfoView);
        v.component('app-project-un-closed-project-portlet', ProjectUnClosedProject);
        v.component('app-project-project-status-bar-portlet', ProjectProjectStatusBar);
        v.component('app-todo-todo-desc-portlet', TodoTodoDesc);
        v.component('app-todo-todoaction-portlet', TodoTodoaction);
        v.component('app-todo-todo-dashboard-actions-portlet', TodoTodoDashboardActions);
        v.component('app-todo-todo-base-portlet', TodoTodoBase);
        v.component('app-todo-my-upcoming-portlet', TodoMyUpcoming);
        v.component('app-test-task-to-test-test-task-portlet', TestTaskToTestTestTask);
        v.component('app-case-me-create-case-portlet', CaseMeCreateCase);
        v.component('case-main-detail-portlet', CaseMainDetail);
        v.component('case-dashboard-quick-action-portlet', CaseDashboardQuickAction);
        v.component('case-main-info-portlet', CaseMainInfo);
        v.component('product-product-main-toolbar-portlet', ProductProductMainToolbar);
        v.component('product-product-info-portlet', ProductProductInfo);
        v.component('product-un-closed-product-portlet', ProductUnClosedProduct);
        v.component('burn-burn-down-chart-portlet', BurnBurnDownChart);
        v.component('action-action-history-portlet', ActionActionHistory);
        v.component('action-project-trends-timeline-portlet', ActionProjectTrendsTimeline);
        v.component('action-product-trends-timeline-portlet', ActionProductTrendsTimeline);
        v.component('story-base-info-portlet', StoryBaseInfo);
        v.component('story-assigned-to-me-story-portlet', StoryAssignedToMeStory);
        v.component('story-main-toolbar-portlet', StoryMainToolbar);
        v.component('story-story-related-portlet', StoryStoryRelated);
        v.component('story-storyspec-portlet', StoryStoryspec);
        v.component('todo-todoaction-portlet', TodoTodoaction);
        v.component('todo-todo-base-portlet', TodoTodoBase);
        v.component('todo-todo-desc-portlet', TodoTodoDesc);
        v.component('todo-todo-dashboard-actions-portlet', TodoTodoDashboardActions);
        v.component('project-un-closed-project-portlet', ProjectUnClosedProject);
        v.component('project-project-main-toolbar-portlet', ProjectProjectMainToolbar);
        v.component('project-dash-borad-info-view-portlet', ProjectDashBoradInfoView);
        v.component('project-project-status-bar-portlet', ProjectProjectStatusBar);
        v.component('product-life-get-roadmaps-portlet', ProductLifeGetRoadmaps);
        v.component('task-toolbar-portlet', TaskToolbar);
        v.component('task-main-info-portlet', TaskMainInfo);
        v.component('task-main-detail-portlet', TaskMainDetail);
        v.component('task-work-info-portlet', TaskWorkInfo);
        v.component('test-suite-cur-suit-case-grid-view-portlet', TestSuiteCurSuitCaseGridView);
        v.component('test-suite-main-info-view-portlet', TestSuiteMainInfoView);
        v.component('test-task-main-info-portlet', TestTaskMainInfo);
        v.component('test-task-to-test-test-task-portlet', TestTaskToTestTestTask);
        v.component('test-task-action-bar-portlet', TestTaskActionBar);
        v.component('test-task-main-detail-portlet', TestTaskMainDetail);
        v.component('bug-dashboard-bug-life-portlet', BugDashboardBugLife);
        v.component('bug-action-history-list-portlet', BugActionHistoryList);
        v.component('bug-dashboard-bug-main-portlet', BugDashboardBugMain);
        v.component('bug-bug-dashboard-actions-portlet', BugBugDashboardActions);
        v.component('bug-steps-info-portlet', BugStepsInfo);
    }
};