import Vue from 'vue';
import Router from 'vue-router';
import { AuthGuard } from '@/utils';
import qs from 'qs';
import { globalRoutes, indexRoutes} from '@/router';
import { AppServiceBase } from 'ibiz-core';
import { AppViewShell,AppIndexViewShell } from 'ibiz-vue';
import viewShell from 'ibiz-vue/src/components/common/view-shell/view-shell.vue'
Vue.use(Router);

const router = new Router({
    routes: [
        {
            path: '/appindexview/:appindexview?',
            beforeEnter: (to: any, from: any, next: any) => {
                const routerParamsName = 'appindexview';
                const params: any = {};
                if (to.params && to.params[routerParamsName]) {
                    Object.assign(params, qs.parse(to.params[routerParamsName], { delimiter: ';' }));
                }
                const url: string = '/appdata';
                const auth: Promise<any> = AuthGuard.getInstance().authGuard(url, params, router);
                auth.then(() => {
                    next();
                }).catch(() => {
                    next();
                });
            },
            meta: {  
                caption: 'app.views.appindexview.caption',
                info:'',
                viewType: 'APPINDEX',
                dynaModelFilePath:'PSSYSAPPS/Mob/PSAPPINDEXVIEWS/AppIndexView.json',
                parameters: [
                    { pathName: 'views', parameterName: 'appindexview' },
                ],
                requireAuth: true,
            },
            component: AppIndexViewShell
            },
            {
            path: '/viewshell',
            beforeEnter: (to: any, from: any, next: any) => {
                const routerParamsName = 'appindexview';
                const params: any = {};
                if (to.params && to.params[routerParamsName]) {
                    Object.assign(params, qs.parse(to.params[routerParamsName], { delimiter: ';' }));
                }
                const url: string = 'appdata';
                const auth: Promise<any> = AuthGuard.getInstance().authGuard(url, params, router);
                auth.then(() => {
                    next();
                }).catch(() => {
                    next();
                });
            },
            meta: {
                parameters: [
                    { pathName: 'viewshell', parameterName: 'viewshell' },
                ],
            },
            component:viewShell,
            children: [
                    {
                    path: 'projects/:project?/tasks/:task?/taskestimates/:taskestimate?/views/mobmdview',
                    meta: {
                        caption: 'entities.taskestimate.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'taskestimates', parameterName: 'taskestimate' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'taskestimate',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'taskestimates/:taskestimate?/views/mobmdview',
                    meta: {
                        caption: 'entities.taskestimate.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'taskestimates', parameterName: 'taskestimate' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'taskestimate',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/taskestimates/:taskestimate?/views/mobmdview9',
                    meta: {
                        caption: 'entities.taskestimate.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'taskestimates', parameterName: 'taskestimate' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'taskestimate',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'taskestimates/:taskestimate?/views/mobmdview9',
                    meta: {
                        caption: 'entities.taskestimate.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'taskestimates', parameterName: 'taskestimate' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'taskestimate',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/taskestimates/:taskestimate?/views/moboptionview',
                    meta: {
                        caption: 'entities.taskestimate.views.moboptionview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'taskestimates', parameterName: 'taskestimate' },
                            { pathName: 'views', parameterName: 'moboptionview' },
                        ],
                        resource:'taskestimate',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'taskestimates/:taskestimate?/views/moboptionview',
                    meta: {
                        caption: 'entities.taskestimate.views.moboptionview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'taskestimates', parameterName: 'taskestimate' },
                            { pathName: 'views', parameterName: 'moboptionview' },
                        ],
                        resource:'taskestimate',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/testcases/:testcase?/testcasesteps/:testcasestep?/views/mobmdview9',
                    meta: {
                        caption: 'entities.testcasestep.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'testcases', parameterName: 'testcase' },
                            { pathName: 'testcasesteps', parameterName: 'testcasestep' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'testcasestep',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'testcasesteps/:testcasestep?/views/mobmdview9',
                    meta: {
                        caption: 'entities.testcasestep.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testcasesteps', parameterName: 'testcasestep' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'testcasestep',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/views/mobtabexpview',
                    meta: {
                        caption: 'entities.project.views.mobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'views', parameterName: 'mobtabexpview' },
                        ],
                        resource:'project',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/views/mobmdview',
                    meta: {
                        caption: 'entities.project.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'project',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/views/mobpickupview',
                    meta: {
                        caption: 'entities.project.views.mobpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'views', parameterName: 'mobpickupview' },
                        ],
                        resource:'project',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/views/startmobeditview',
                    meta: {
                        caption: 'entities.project.views.startmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'views', parameterName: 'startmobeditview' },
                        ],
                        resource:'project',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/views/mobeditview',
                    meta: {
                        caption: 'entities.project.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'project',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/views/activitemobeditview',
                    meta: {
                        caption: 'entities.project.views.activitemobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'views', parameterName: 'activitemobeditview' },
                        ],
                        resource:'project',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/views/projectteammanagemobeditview',
                    meta: {
                        caption: 'entities.project.views.projectteammanagemobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'views', parameterName: 'projectteammanagemobeditview' },
                        ],
                        resource:'project',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/views/supmobeditview',
                    meta: {
                        caption: 'entities.project.views.supmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'views', parameterName: 'supmobeditview' },
                        ],
                        resource:'project',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/views/mobchartview',
                    meta: {
                        caption: 'entities.project.views.mobchartview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'views', parameterName: 'mobchartview' },
                        ],
                        resource:'project',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/views/closemobeditview',
                    meta: {
                        caption: 'entities.project.views.closemobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'views', parameterName: 'closemobeditview' },
                        ],
                        resource:'project',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/views/newmobeditview',
                    meta: {
                        caption: 'entities.project.views.newmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'project',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/views/mobpickupmdview',
                    meta: {
                        caption: 'entities.project.views.mobpickupmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'views', parameterName: 'mobpickupmdview' },
                        ],
                        resource:'project',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/projectteams/:projectteam?/views/projectteammobeditview',
                    meta: {
                        caption: 'entities.projectteam.views.projectteammobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-users',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'projectteams', parameterName: 'projectteam' },
                            { pathName: 'views', parameterName: 'projectteammobeditview' },
                        ],
                        resource:'projectteam',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projectteams/:projectteam?/views/projectteammobeditview',
                    meta: {
                        caption: 'entities.projectteam.views.projectteammobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-users',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projectteams', parameterName: 'projectteam' },
                            { pathName: 'views', parameterName: 'projectteammobeditview' },
                        ],
                        resource:'projectteam',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/projectteams/:projectteam?/views/mobmdview',
                    meta: {
                        caption: 'entities.projectteam.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-users',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'projectteams', parameterName: 'projectteam' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'projectteam',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projectteams/:projectteam?/views/mobmdview',
                    meta: {
                        caption: 'entities.projectteam.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-users',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projectteams', parameterName: 'projectteam' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'projectteam',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/productreleases/:productrelease?/views/mobmdview',
                    meta: {
                        caption: 'entities.productrelease.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-flag-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'productreleases', parameterName: 'productrelease' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'productrelease',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'productreleases/:productrelease?/views/mobmdview',
                    meta: {
                        caption: 'entities.productrelease.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-flag-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'productreleases', parameterName: 'productrelease' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'productrelease',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/productreleases/:productrelease?/views/mobpickupmdview',
                    meta: {
                        caption: 'entities.productrelease.views.mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-flag-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'productreleases', parameterName: 'productrelease' },
                            { pathName: 'views', parameterName: 'mobpickupmdview' },
                        ],
                        resource:'productrelease',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'productreleases/:productrelease?/views/mobpickupmdview',
                    meta: {
                        caption: 'entities.productrelease.views.mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-flag-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'productreleases', parameterName: 'productrelease' },
                            { pathName: 'views', parameterName: 'mobpickupmdview' },
                        ],
                        resource:'productrelease',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/productreleases/:productrelease?/views/mobtabexpview',
                    meta: {
                        caption: 'entities.productrelease.views.mobtabexpview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-flag-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'productreleases', parameterName: 'productrelease' },
                            { pathName: 'views', parameterName: 'mobtabexpview' },
                        ],
                        resource:'productrelease',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'productreleases/:productrelease?/views/mobtabexpview',
                    meta: {
                        caption: 'entities.productrelease.views.mobtabexpview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-flag-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'productreleases', parameterName: 'productrelease' },
                            { pathName: 'views', parameterName: 'mobtabexpview' },
                        ],
                        resource:'productrelease',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/productreleases/:productrelease?/views/editmobeditview',
                    meta: {
                        caption: 'entities.productrelease.views.editmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-flag-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'productreleases', parameterName: 'productrelease' },
                            { pathName: 'views', parameterName: 'editmobeditview' },
                        ],
                        resource:'productrelease',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'productreleases/:productrelease?/views/editmobeditview',
                    meta: {
                        caption: 'entities.productrelease.views.editmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-flag-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'productreleases', parameterName: 'productrelease' },
                            { pathName: 'views', parameterName: 'editmobeditview' },
                        ],
                        resource:'productrelease',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/productreleases/:productrelease?/views/mobpickupview',
                    meta: {
                        caption: 'entities.productrelease.views.mobpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-flag-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'productreleases', parameterName: 'productrelease' },
                            { pathName: 'views', parameterName: 'mobpickupview' },
                        ],
                        resource:'productrelease',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'productreleases/:productrelease?/views/mobpickupview',
                    meta: {
                        caption: 'entities.productrelease.views.mobpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-flag-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'productreleases', parameterName: 'productrelease' },
                            { pathName: 'views', parameterName: 'mobpickupview' },
                        ],
                        resource:'productrelease',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/productreleases/:productrelease?/views/newmobeditview',
                    meta: {
                        caption: 'entities.productrelease.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-flag-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'productreleases', parameterName: 'productrelease' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'productrelease',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'productreleases/:productrelease?/views/newmobeditview',
                    meta: {
                        caption: 'entities.productrelease.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-flag-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'productreleases', parameterName: 'productrelease' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'productrelease',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/productreleases/:productrelease?/views/mobeditview',
                    meta: {
                        caption: 'entities.productrelease.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-flag-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'productreleases', parameterName: 'productrelease' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'productrelease',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'productreleases/:productrelease?/views/mobeditview',
                    meta: {
                        caption: 'entities.productrelease.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-flag-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'productreleases', parameterName: 'productrelease' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'productrelease',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/testcases/:testcase?/views/mobeditview',
                    meta: {
                        caption: 'entities.testcase.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'testcases', parameterName: 'testcase' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'testcase',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'testcases/:testcase?/views/mobeditview',
                    meta: {
                        caption: 'entities.testcase.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testcases', parameterName: 'testcase' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'testcase',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/testcases/:testcase?/views/mobmdview',
                    meta: {
                        caption: 'entities.testcase.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'testcases', parameterName: 'testcase' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'testcase',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'testcases/:testcase?/views/mobmdview',
                    meta: {
                        caption: 'entities.testcase.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testcases', parameterName: 'testcase' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'testcase',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/testcases/:testcase?/views/usr2mobmpickupview',
                    meta: {
                        caption: 'entities.testcase.views.usr2mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'testcases', parameterName: 'testcase' },
                            { pathName: 'views', parameterName: 'usr2mobmpickupview' },
                        ],
                        resource:'testcase',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'testcases/:testcase?/views/usr2mobmpickupview',
                    meta: {
                        caption: 'entities.testcase.views.usr2mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testcases', parameterName: 'testcase' },
                            { pathName: 'views', parameterName: 'usr2mobmpickupview' },
                        ],
                        resource:'testcase',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/testcases/:testcase?/views/usr2mobpickupmdview',
                    meta: {
                        caption: 'entities.testcase.views.usr2mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'testcases', parameterName: 'testcase' },
                            { pathName: 'views', parameterName: 'usr2mobpickupmdview' },
                        ],
                        resource:'testcase',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'testcases/:testcase?/views/usr2mobpickupmdview',
                    meta: {
                        caption: 'entities.testcase.views.usr2mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testcases', parameterName: 'testcase' },
                            { pathName: 'views', parameterName: 'usr2mobpickupmdview' },
                        ],
                        resource:'testcase',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/testcases/:testcase?/views/createcasemobeditview',
                    meta: {
                        caption: 'entities.testcase.views.createcasemobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'testcases', parameterName: 'testcase' },
                            { pathName: 'views', parameterName: 'createcasemobeditview' },
                        ],
                        resource:'testcase',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'testcases/:testcase?/views/createcasemobeditview',
                    meta: {
                        caption: 'entities.testcase.views.createcasemobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testcases', parameterName: 'testcase' },
                            { pathName: 'views', parameterName: 'createcasemobeditview' },
                        ],
                        resource:'testcase',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/testcases/:testcase?/views/mobmdview_testtask',
                    meta: {
                        caption: 'entities.testcase.views.mobmdview_testtask.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'testcases', parameterName: 'testcase' },
                            { pathName: 'views', parameterName: 'mobmdview_testtask' },
                        ],
                        resource:'testcase',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'testcases/:testcase?/views/mobmdview_testtask',
                    meta: {
                        caption: 'entities.testcase.views.mobmdview_testtask.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testcases', parameterName: 'testcase' },
                            { pathName: 'views', parameterName: 'mobmdview_testtask' },
                        ],
                        resource:'testcase',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/testcases/:testcase?/views/mobmdview_testsuite',
                    meta: {
                        caption: 'entities.testcase.views.mobmdview_testsuite.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'testcases', parameterName: 'testcase' },
                            { pathName: 'views', parameterName: 'mobmdview_testsuite' },
                        ],
                        resource:'testcase',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'testcases/:testcase?/views/mobmdview_testsuite',
                    meta: {
                        caption: 'entities.testcase.views.mobmdview_testsuite.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testcases', parameterName: 'testcase' },
                            { pathName: 'views', parameterName: 'mobmdview_testsuite' },
                        ],
                        resource:'testcase',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/projectmodules/:projectmodule?/views/mobpickupmdview',
                    meta: {
                        caption: 'entities.projectmodule.views.mobpickupmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'projectmodules', parameterName: 'projectmodule' },
                            { pathName: 'views', parameterName: 'mobpickupmdview' },
                        ],
                        resource:'projectmodule',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projectmodules/:projectmodule?/views/mobpickupmdview',
                    meta: {
                        caption: 'entities.projectmodule.views.mobpickupmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projectmodules', parameterName: 'projectmodule' },
                            { pathName: 'views', parameterName: 'mobpickupmdview' },
                        ],
                        resource:'projectmodule',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/projectmodules/:projectmodule?/views/mobpickupview',
                    meta: {
                        caption: 'entities.projectmodule.views.mobpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'projectmodules', parameterName: 'projectmodule' },
                            { pathName: 'views', parameterName: 'mobpickupview' },
                        ],
                        resource:'projectmodule',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projectmodules/:projectmodule?/views/mobpickupview',
                    meta: {
                        caption: 'entities.projectmodule.views.mobpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projectmodules', parameterName: 'projectmodule' },
                            { pathName: 'views', parameterName: 'mobpickupview' },
                        ],
                        resource:'projectmodule',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/builds/:build?/views/mobmpickupview',
                    meta: {
                        caption: 'entities.build.views.mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'mobmpickupview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/builds/:build?/views/mobmpickupview',
                    meta: {
                        caption: 'entities.build.views.mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'mobmpickupview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'builds/:build?/views/mobmpickupview',
                    meta: {
                        caption: 'entities.build.views.mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'mobmpickupview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/builds/:build?/views/mobeditview',
                    meta: {
                        caption: 'entities.build.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/builds/:build?/views/mobeditview',
                    meta: {
                        caption: 'entities.build.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'builds/:build?/views/mobeditview',
                    meta: {
                        caption: 'entities.build.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/builds/:build?/views/logmobeditview',
                    meta: {
                        caption: 'entities.build.views.logmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'logmobeditview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/builds/:build?/views/logmobeditview',
                    meta: {
                        caption: 'entities.build.views.logmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'logmobeditview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'builds/:build?/views/logmobeditview',
                    meta: {
                        caption: 'entities.build.views.logmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'logmobeditview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/builds/:build?/views/editmobeditview',
                    meta: {
                        caption: 'entities.build.views.editmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'editmobeditview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/builds/:build?/views/editmobeditview',
                    meta: {
                        caption: 'entities.build.views.editmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'editmobeditview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'builds/:build?/views/editmobeditview',
                    meta: {
                        caption: 'entities.build.views.editmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'editmobeditview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/builds/:build?/views/mobpickupview',
                    meta: {
                        caption: 'entities.build.views.mobpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'mobpickupview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/builds/:build?/views/mobpickupview',
                    meta: {
                        caption: 'entities.build.views.mobpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'mobpickupview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'builds/:build?/views/mobpickupview',
                    meta: {
                        caption: 'entities.build.views.mobpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'mobpickupview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/builds/:build?/views/mobtabexpview',
                    meta: {
                        caption: 'entities.build.views.mobtabexpview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'mobtabexpview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/builds/:build?/views/mobtabexpview',
                    meta: {
                        caption: 'entities.build.views.mobtabexpview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'mobtabexpview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'builds/:build?/views/mobtabexpview',
                    meta: {
                        caption: 'entities.build.views.mobtabexpview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'mobtabexpview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/builds/:build?/views/mobpickupmdview',
                    meta: {
                        caption: 'entities.build.views.mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'mobpickupmdview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/builds/:build?/views/mobpickupmdview',
                    meta: {
                        caption: 'entities.build.views.mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'mobpickupmdview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'builds/:build?/views/mobpickupmdview',
                    meta: {
                        caption: 'entities.build.views.mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'mobpickupmdview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/builds/:build?/views/logmobmdview',
                    meta: {
                        caption: 'entities.build.views.logmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'logmobmdview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/builds/:build?/views/logmobmdview',
                    meta: {
                        caption: 'entities.build.views.logmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'logmobmdview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'builds/:build?/views/logmobmdview',
                    meta: {
                        caption: 'entities.build.views.logmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'logmobmdview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/builds/:build?/views/newmobeditview',
                    meta: {
                        caption: 'entities.build.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/builds/:build?/views/newmobeditview',
                    meta: {
                        caption: 'entities.build.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'builds/:build?/views/newmobeditview',
                    meta: {
                        caption: 'entities.build.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/builds/:build?/views/mobmdview',
                    meta: {
                        caption: 'entities.build.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/builds/:build?/views/mobmdview',
                    meta: {
                        caption: 'entities.build.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'builds/:build?/views/mobmdview',
                    meta: {
                        caption: 'entities.build.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'builds', parameterName: 'build' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'build',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/usr3mobmpickupleftview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobmpickupleftview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobmpickupleftview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/usr3mobmpickupleftview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobmpickupleftview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobmpickupleftview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/usr3mobmpickupleftview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobmpickupleftview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobmpickupleftview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/usr3mobmpickupleftview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobmpickupleftview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobmpickupleftview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/editnewmobeditview',
                    meta: {
                        caption: 'entities.bug.views.editnewmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'editnewmobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/editnewmobeditview',
                    meta: {
                        caption: 'entities.bug.views.editnewmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'editnewmobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/editnewmobeditview',
                    meta: {
                        caption: 'entities.bug.views.editnewmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'editnewmobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/editnewmobeditview',
                    meta: {
                        caption: 'entities.bug.views.editnewmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'editnewmobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/mobeditview',
                    meta: {
                        caption: 'entities.bug.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/mobeditview',
                    meta: {
                        caption: 'entities.bug.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/mobeditview',
                    meta: {
                        caption: 'entities.bug.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/mobeditview',
                    meta: {
                        caption: 'entities.bug.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/usr3mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/usr3mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/usr3mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/usr3mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/resolvemobeditview',
                    meta: {
                        caption: 'entities.bug.views.resolvemobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'resolvemobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/resolvemobeditview',
                    meta: {
                        caption: 'entities.bug.views.resolvemobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'resolvemobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/resolvemobeditview',
                    meta: {
                        caption: 'entities.bug.views.resolvemobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'resolvemobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/resolvemobeditview',
                    meta: {
                        caption: 'entities.bug.views.resolvemobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'resolvemobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/assigntomobeditview',
                    meta: {
                        caption: 'entities.bug.views.assigntomobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'assigntomobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/assigntomobeditview',
                    meta: {
                        caption: 'entities.bug.views.assigntomobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'assigntomobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/assigntomobeditview',
                    meta: {
                        caption: 'entities.bug.views.assigntomobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'assigntomobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/assigntomobeditview',
                    meta: {
                        caption: 'entities.bug.views.assigntomobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'assigntomobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/assmobmdview',
                    meta: {
                        caption: 'entities.bug.views.assmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'assmobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/assmobmdview',
                    meta: {
                        caption: 'entities.bug.views.assmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'assmobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/assmobmdview',
                    meta: {
                        caption: 'entities.bug.views.assmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'assmobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/assmobmdview',
                    meta: {
                        caption: 'entities.bug.views.assmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'assmobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/usr2mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr2mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr2mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/usr2mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr2mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr2mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/usr2mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr2mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr2mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/usr2mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr2mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr2mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/colsemobeditview',
                    meta: {
                        caption: 'entities.bug.views.colsemobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'colsemobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/colsemobeditview',
                    meta: {
                        caption: 'entities.bug.views.colsemobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'colsemobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/colsemobeditview',
                    meta: {
                        caption: 'entities.bug.views.colsemobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'colsemobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/colsemobeditview',
                    meta: {
                        caption: 'entities.bug.views.colsemobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'colsemobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/usr3mobpickupbuildresolvedmdview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobpickupbuildresolvedmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobpickupbuildresolvedmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/usr3mobpickupbuildresolvedmdview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobpickupbuildresolvedmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobpickupbuildresolvedmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/usr3mobpickupbuildresolvedmdview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobpickupbuildresolvedmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobpickupbuildresolvedmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/usr3mobpickupbuildresolvedmdview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobpickupbuildresolvedmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobpickupbuildresolvedmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/closemoboptionview',
                    meta: {
                        caption: 'entities.bug.views.closemoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'closemoboptionview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/closemoboptionview',
                    meta: {
                        caption: 'entities.bug.views.closemoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'closemoboptionview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/closemoboptionview',
                    meta: {
                        caption: 'entities.bug.views.closemoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'closemoboptionview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/closemoboptionview',
                    meta: {
                        caption: 'entities.bug.views.closemoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'closemoboptionview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/usr3mobmpickupview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobmpickupview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/usr3mobmpickupview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobmpickupview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/usr3mobmpickupview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobmpickupview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/usr3mobmpickupview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobmpickupview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/usr4mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr4mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr4mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/usr4mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr4mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr4mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/usr4mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr4mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr4mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/usr4mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr4mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr4mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/usr6mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr6mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr6mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/usr6mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr6mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr6mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/usr6mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr6mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr6mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/usr6mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr6mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr6mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/assmobmdview9',
                    meta: {
                        caption: 'entities.bug.views.assmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'assmobmdview9' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/assmobmdview9',
                    meta: {
                        caption: 'entities.bug.views.assmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'assmobmdview9' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/assmobmdview9',
                    meta: {
                        caption: 'entities.bug.views.assmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'assmobmdview9' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/assmobmdview9',
                    meta: {
                        caption: 'entities.bug.views.assmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'assmobmdview9' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/usr5mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr5mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr5mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/usr5mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr5mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr5mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/usr5mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr5mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr5mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/usr5mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr5mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr5mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/confirmmobeditview',
                    meta: {
                        caption: 'entities.bug.views.confirmmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'confirmmobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/confirmmobeditview',
                    meta: {
                        caption: 'entities.bug.views.confirmmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'confirmmobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/confirmmobeditview',
                    meta: {
                        caption: 'entities.bug.views.confirmmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'confirmmobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/confirmmobeditview',
                    meta: {
                        caption: 'entities.bug.views.confirmmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'confirmmobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/rmoboptionview',
                    meta: {
                        caption: 'entities.bug.views.rmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'rmoboptionview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/rmoboptionview',
                    meta: {
                        caption: 'entities.bug.views.rmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'rmoboptionview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/rmoboptionview',
                    meta: {
                        caption: 'entities.bug.views.rmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'rmoboptionview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/rmoboptionview',
                    meta: {
                        caption: 'entities.bug.views.rmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'rmoboptionview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/newmobeditview',
                    meta: {
                        caption: 'entities.bug.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/newmobeditview',
                    meta: {
                        caption: 'entities.bug.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/newmobeditview',
                    meta: {
                        caption: 'entities.bug.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/newmobeditview',
                    meta: {
                        caption: 'entities.bug.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/usr2mobmpickupview',
                    meta: {
                        caption: 'entities.bug.views.usr2mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr2mobmpickupview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/usr2mobmpickupview',
                    meta: {
                        caption: 'entities.bug.views.usr2mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr2mobmpickupview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/usr2mobmpickupview',
                    meta: {
                        caption: 'entities.bug.views.usr2mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr2mobmpickupview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/usr2mobmpickupview',
                    meta: {
                        caption: 'entities.bug.views.usr2mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr2mobmpickupview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/usr3mobpickupmdview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobpickupmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/usr3mobpickupmdview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobpickupmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/usr3mobpickupmdview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobpickupmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/usr3mobpickupmdview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobpickupmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/assmoboptionview',
                    meta: {
                        caption: 'entities.bug.views.assmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'assmoboptionview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/assmoboptionview',
                    meta: {
                        caption: 'entities.bug.views.assmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'assmoboptionview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/assmoboptionview',
                    meta: {
                        caption: 'entities.bug.views.assmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'assmoboptionview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/assmoboptionview',
                    meta: {
                        caption: 'entities.bug.views.assmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'assmoboptionview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/acmoboptionview',
                    meta: {
                        caption: 'entities.bug.views.acmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'acmoboptionview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/acmoboptionview',
                    meta: {
                        caption: 'entities.bug.views.acmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'acmoboptionview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/acmoboptionview',
                    meta: {
                        caption: 'entities.bug.views.acmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'acmoboptionview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/acmoboptionview',
                    meta: {
                        caption: 'entities.bug.views.acmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'acmoboptionview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/cmoboptionview',
                    meta: {
                        caption: 'entities.bug.views.cmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'cmoboptionview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/cmoboptionview',
                    meta: {
                        caption: 'entities.bug.views.cmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'cmoboptionview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/cmoboptionview',
                    meta: {
                        caption: 'entities.bug.views.cmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'cmoboptionview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/cmoboptionview',
                    meta: {
                        caption: 'entities.bug.views.cmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'cmoboptionview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/testmobmdview',
                    meta: {
                        caption: 'entities.bug.views.testmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'testmobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/testmobmdview',
                    meta: {
                        caption: 'entities.bug.views.testmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'testmobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/testmobmdview',
                    meta: {
                        caption: 'entities.bug.views.testmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'testmobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/testmobmdview',
                    meta: {
                        caption: 'entities.bug.views.testmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'testmobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/planmobmdview9',
                    meta: {
                        caption: 'entities.bug.views.planmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'planmobmdview9' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/planmobmdview9',
                    meta: {
                        caption: 'entities.bug.views.planmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'planmobmdview9' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/planmobmdview9',
                    meta: {
                        caption: 'entities.bug.views.planmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'planmobmdview9' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/planmobmdview9',
                    meta: {
                        caption: 'entities.bug.views.planmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'planmobmdview9' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/usr3mobmpickupbuildcreatebugview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobmpickupbuildcreatebugview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobmpickupbuildcreatebugview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/usr3mobmpickupbuildcreatebugview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobmpickupbuildcreatebugview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobmpickupbuildcreatebugview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/usr3mobmpickupbuildcreatebugview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobmpickupbuildcreatebugview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobmpickupbuildcreatebugview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/usr3mobmpickupbuildcreatebugview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobmpickupbuildcreatebugview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobmpickupbuildcreatebugview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/mobmdview',
                    meta: {
                        caption: 'entities.bug.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/mobmdview',
                    meta: {
                        caption: 'entities.bug.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/mobmdview',
                    meta: {
                        caption: 'entities.bug.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/mobmdview',
                    meta: {
                        caption: 'entities.bug.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/usr3mobpickupmdview1',
                    meta: {
                        caption: 'entities.bug.views.usr3mobpickupmdview1.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobpickupmdview1' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/usr3mobpickupmdview1',
                    meta: {
                        caption: 'entities.bug.views.usr3mobpickupmdview1.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobpickupmdview1' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/usr3mobpickupmdview1',
                    meta: {
                        caption: 'entities.bug.views.usr3mobpickupmdview1.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobpickupmdview1' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/usr3mobpickupmdview1',
                    meta: {
                        caption: 'entities.bug.views.usr3mobpickupmdview1.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr3mobpickupmdview1' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/activationmobeditview',
                    meta: {
                        caption: 'entities.bug.views.activationmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'activationmobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/activationmobeditview',
                    meta: {
                        caption: 'entities.bug.views.activationmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'activationmobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/activationmobeditview',
                    meta: {
                        caption: 'entities.bug.views.activationmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'activationmobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/activationmobeditview',
                    meta: {
                        caption: 'entities.bug.views.activationmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'activationmobeditview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/usr2mobpickupmdview',
                    meta: {
                        caption: 'entities.bug.views.usr2mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr2mobpickupmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/usr2mobpickupmdview',
                    meta: {
                        caption: 'entities.bug.views.usr2mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr2mobpickupmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/usr2mobpickupmdview',
                    meta: {
                        caption: 'entities.bug.views.usr2mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr2mobpickupmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/usr2mobpickupmdview',
                    meta: {
                        caption: 'entities.bug.views.usr2mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'usr2mobpickupmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/assmoremobmdview',
                    meta: {
                        caption: 'entities.bug.views.assmoremobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'assmoremobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/assmoremobmdview',
                    meta: {
                        caption: 'entities.bug.views.assmoremobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'assmoremobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/assmoremobmdview',
                    meta: {
                        caption: 'entities.bug.views.assmoremobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'assmoremobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/assmoremobmdview',
                    meta: {
                        caption: 'entities.bug.views.assmoremobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'assmoremobmdview' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'tests/:test?/bugs/:bug?/views/logmobmdview9',
                    meta: {
                        caption: 'entities.bug.views.logmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tests', parameterName: 'test' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'logmobmdview9' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'projects/:project?/bugs/:bug?/views/logmobmdview9',
                    meta: {
                        caption: 'entities.bug.views.logmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'logmobmdview9' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/bugs/:bug?/views/logmobmdview9',
                    meta: {
                        caption: 'entities.bug.views.logmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'logmobmdview9' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'bugs/:bug?/views/logmobmdview9',
                    meta: {
                        caption: 'entities.bug.views.logmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'bugs', parameterName: 'bug' },
                            { pathName: 'views', parameterName: 'logmobmdview9' },
                        ],
                        resource:'bug',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/views/testmobmdview',
                    meta: {
                        caption: 'entities.product.views.testmobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'views', parameterName: 'testmobmdview' },
                        ],
                        resource:'product',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/views/mobtabexpview',
                    meta: {
                        caption: 'entities.product.views.mobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'views', parameterName: 'mobtabexpview' },
                        ],
                        resource:'product',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/views/mobpickupview',
                    meta: {
                        caption: 'entities.product.views.mobpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'views', parameterName: 'mobpickupview' },
                        ],
                        resource:'product',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/views/mobmdview',
                    meta: {
                        caption: 'entities.product.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'product',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/views/closemobeditview',
                    meta: {
                        caption: 'entities.product.views.closemobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'views', parameterName: 'closemobeditview' },
                        ],
                        resource:'product',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/views/mobeditview',
                    meta: {
                        caption: 'entities.product.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'product',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/views/prodmobtabexpview',
                    meta: {
                        caption: 'entities.product.views.prodmobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'views', parameterName: 'prodmobtabexpview' },
                        ],
                        resource:'product',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/views/newmobeditview',
                    meta: {
                        caption: 'entities.product.views.newmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'product',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/views/mobpickupmdview',
                    meta: {
                        caption: 'entities.product.views.mobpickupmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'views', parameterName: 'mobpickupmdview' },
                        ],
                        resource:'product',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/views/mobchartview',
                    meta: {
                        caption: 'entities.product.views.mobchartview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'views', parameterName: 'mobchartview' },
                        ],
                        resource:'product',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/views/mobchartview9',
                    meta: {
                        caption: 'entities.product.views.mobchartview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'views', parameterName: 'mobchartview9' },
                        ],
                        resource:'product',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/productplans/:productplan?/views/mobeditview',
                    meta: {
                        caption: 'entities.productplan.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'productplans', parameterName: 'productplan' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'productplan',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'productplans/:productplan?/views/mobeditview',
                    meta: {
                        caption: 'entities.productplan.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'productplans', parameterName: 'productplan' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'productplan',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/productplans/:productplan?/views/mobtabexpview',
                    meta: {
                        caption: 'entities.productplan.views.mobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'productplans', parameterName: 'productplan' },
                            { pathName: 'views', parameterName: 'mobtabexpview' },
                        ],
                        resource:'productplan',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'productplans/:productplan?/views/mobtabexpview',
                    meta: {
                        caption: 'entities.productplan.views.mobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'productplans', parameterName: 'productplan' },
                            { pathName: 'views', parameterName: 'mobtabexpview' },
                        ],
                        resource:'productplan',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/productplans/:productplan?/views/editmobeditview',
                    meta: {
                        caption: 'entities.productplan.views.editmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'productplans', parameterName: 'productplan' },
                            { pathName: 'views', parameterName: 'editmobeditview' },
                        ],
                        resource:'productplan',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'productplans/:productplan?/views/editmobeditview',
                    meta: {
                        caption: 'entities.productplan.views.editmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'productplans', parameterName: 'productplan' },
                            { pathName: 'views', parameterName: 'editmobeditview' },
                        ],
                        resource:'productplan',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/productplans/:productplan?/views/mobmdview',
                    meta: {
                        caption: 'entities.productplan.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'productplans', parameterName: 'productplan' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'productplan',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'productplans/:productplan?/views/mobmdview',
                    meta: {
                        caption: 'entities.productplan.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'productplans', parameterName: 'productplan' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'productplan',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/productplans/:productplan?/views/newmobeditview',
                    meta: {
                        caption: 'entities.productplan.views.newmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'productplans', parameterName: 'productplan' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'productplan',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'productplans/:productplan?/views/newmobeditview',
                    meta: {
                        caption: 'entities.productplan.views.newmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'productplans', parameterName: 'productplan' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'productplan',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/productplans/:productplan?/views/usr2mobpickupmdview',
                    meta: {
                        caption: 'entities.productplan.views.usr2mobpickupmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'productplans', parameterName: 'productplan' },
                            { pathName: 'views', parameterName: 'usr2mobpickupmdview' },
                        ],
                        resource:'productplan',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'productplans/:productplan?/views/usr2mobpickupmdview',
                    meta: {
                        caption: 'entities.productplan.views.usr2mobpickupmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'productplans', parameterName: 'productplan' },
                            { pathName: 'views', parameterName: 'usr2mobpickupmdview' },
                        ],
                        resource:'productplan',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/productmodules/:productmodule?/views/mobpickupview',
                    meta: {
                        caption: 'entities.productmodule.views.mobpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'productmodules', parameterName: 'productmodule' },
                            { pathName: 'views', parameterName: 'mobpickupview' },
                        ],
                        resource:'productmodule',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'productmodules/:productmodule?/views/mobpickupview',
                    meta: {
                        caption: 'entities.productmodule.views.mobpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'productmodules', parameterName: 'productmodule' },
                            { pathName: 'views', parameterName: 'mobpickupview' },
                        ],
                        resource:'productmodule',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'products/:product?/productmodules/:productmodule?/views/mobpickupmdview',
                    meta: {
                        caption: 'entities.productmodule.views.mobpickupmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'productmodules', parameterName: 'productmodule' },
                            { pathName: 'views', parameterName: 'mobpickupmdview' },
                        ],
                        resource:'productmodule',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'productmodules/:productmodule?/views/mobpickupmdview',
                    meta: {
                        caption: 'entities.productmodule.views.mobpickupmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'productmodules', parameterName: 'productmodule' },
                            { pathName: 'views', parameterName: 'mobpickupmdview' },
                        ],
                        resource:'productmodule',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/activemobtask',
                    meta: {
                        caption: 'entities.task.views.activemobtask.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'activemobtask' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/activemobtask',
                    meta: {
                        caption: 'entities.task.views.activemobtask.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'activemobtask' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/mycompletetaskmobmdviewweekly',
                    meta: {
                        caption: 'entities.task.views.mycompletetaskmobmdviewweekly.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'mycompletetaskmobmdviewweekly' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/mycompletetaskmobmdviewweekly',
                    meta: {
                        caption: 'entities.task.views.mycompletetaskmobmdviewweekly.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'mycompletetaskmobmdviewweekly' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/editmobeditview',
                    meta: {
                        caption: 'entities.task.views.editmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'editmobeditview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/editmobeditview',
                    meta: {
                        caption: 'entities.task.views.editmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'editmobeditview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/cancelmoboptionview',
                    meta: {
                        caption: 'entities.task.views.cancelmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'cancelmoboptionview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/cancelmoboptionview',
                    meta: {
                        caption: 'entities.task.views.cancelmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'cancelmoboptionview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/assmobmdview9',
                    meta: {
                        caption: 'entities.task.views.assmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'assmobmdview9' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/assmobmdview9',
                    meta: {
                        caption: 'entities.task.views.assmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'assmobmdview9' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/favoritemobmdview',
                    meta: {
                        caption: 'entities.task.views.favoritemobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'favoritemobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/favoritemobmdview',
                    meta: {
                        caption: 'entities.task.views.favoritemobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'favoritemobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/moboptionview',
                    meta: {
                        caption: 'entities.task.views.moboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'moboptionview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/moboptionview',
                    meta: {
                        caption: 'entities.task.views.moboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'moboptionview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/favoritemobmdview9',
                    meta: {
                        caption: 'entities.task.views.favoritemobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'favoritemobmdview9' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/favoritemobmdview9',
                    meta: {
                        caption: 'entities.task.views.favoritemobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'favoritemobmdview9' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/newmobeditview',
                    meta: {
                        caption: 'entities.task.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/newmobeditview',
                    meta: {
                        caption: 'entities.task.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/monthlyplanstaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.monthlyplanstaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'monthlyplanstaskmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/monthlyplanstaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.monthlyplanstaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'monthlyplanstaskmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/mycompletetaskmobmdview1',
                    meta: {
                        caption: 'entities.task.views.mycompletetaskmobmdview1.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'mycompletetaskmobmdview1' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/mycompletetaskmobmdview1',
                    meta: {
                        caption: 'entities.task.views.mycompletetaskmobmdview1.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'mycompletetaskmobmdview1' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/dailydonetaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.dailydonetaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'dailydonetaskmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/dailydonetaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.dailydonetaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'dailydonetaskmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/startmoboptionview',
                    meta: {
                        caption: 'entities.task.views.startmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'startmoboptionview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/startmoboptionview',
                    meta: {
                        caption: 'entities.task.views.startmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'startmoboptionview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/monthlymycompletetaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.monthlymycompletetaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'monthlymycompletetaskmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/monthlymycompletetaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.monthlymycompletetaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'monthlymycompletetaskmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/mobpickupmdview',
                    meta: {
                        caption: 'entities.task.views.mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'mobpickupmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/mobpickupmdview',
                    meta: {
                        caption: 'entities.task.views.mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'mobpickupmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/weeklyplanstaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.weeklyplanstaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'weeklyplanstaskmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/weeklyplanstaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.weeklyplanstaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'weeklyplanstaskmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/stopmoboptionview',
                    meta: {
                        caption: 'entities.task.views.stopmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'stopmoboptionview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/stopmoboptionview',
                    meta: {
                        caption: 'entities.task.views.stopmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'stopmoboptionview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/gsmoboptionview',
                    meta: {
                        caption: 'entities.task.views.gsmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'gsmoboptionview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/gsmoboptionview',
                    meta: {
                        caption: 'entities.task.views.gsmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'gsmoboptionview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/usr2moboptionview',
                    meta: {
                        caption: 'entities.task.views.usr2moboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'usr2moboptionview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/usr2moboptionview',
                    meta: {
                        caption: 'entities.task.views.usr2moboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'usr2moboptionview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/weeklylydonetaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.weeklylydonetaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'weeklylydonetaskmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/weeklylydonetaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.weeklylydonetaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'weeklylydonetaskmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/commoboptionview',
                    meta: {
                        caption: 'entities.task.views.commoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'commoboptionview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/commoboptionview',
                    meta: {
                        caption: 'entities.task.views.commoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'commoboptionview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/mobeditview',
                    meta: {
                        caption: 'entities.task.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/mobeditview',
                    meta: {
                        caption: 'entities.task.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/assmobmdview',
                    meta: {
                        caption: 'entities.task.views.assmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'assmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/assmobmdview',
                    meta: {
                        caption: 'entities.task.views.assmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'assmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/monthlydonetaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.monthlydonetaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'monthlydonetaskmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/monthlydonetaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.monthlydonetaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'monthlydonetaskmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/mycompletetaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.mycompletetaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'mycompletetaskmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/mycompletetaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.mycompletetaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'mycompletetaskmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/favoritemoremobmdview',
                    meta: {
                        caption: 'entities.task.views.favoritemoremobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'favoritemoremobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/favoritemoremobmdview',
                    meta: {
                        caption: 'entities.task.views.favoritemoremobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'favoritemoremobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/closemoboptionview',
                    meta: {
                        caption: 'entities.task.views.closemoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'closemoboptionview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/closemoboptionview',
                    meta: {
                        caption: 'entities.task.views.closemoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'closemoboptionview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/mobmdview',
                    meta: {
                        caption: 'entities.task.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/mobmdview',
                    meta: {
                        caption: 'entities.task.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/dailyplanstaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.dailyplanstaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'dailyplanstaskmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/dailyplanstaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.dailyplanstaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'dailyplanstaskmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/myplanstomorrowtaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.myplanstomorrowtaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'myplanstomorrowtaskmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/myplanstomorrowtaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.myplanstomorrowtaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'myplanstomorrowtaskmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/mobpickupview',
                    meta: {
                        caption: 'entities.task.views.mobpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'mobpickupview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/mobpickupview',
                    meta: {
                        caption: 'entities.task.views.mobpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'mobpickupview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/mycompletetaskmobmdviewnextplanweekly',
                    meta: {
                        caption: 'entities.task.views.mycompletetaskmobmdviewnextplanweekly.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'mycompletetaskmobmdviewnextplanweekly' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/mycompletetaskmobmdviewnextplanweekly',
                    meta: {
                        caption: 'entities.task.views.mycompletetaskmobmdviewnextplanweekly.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'mycompletetaskmobmdviewnextplanweekly' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/assmoremobmdview',
                    meta: {
                        caption: 'entities.task.views.assmoremobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'assmoremobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/assmoremobmdview',
                    meta: {
                        caption: 'entities.task.views.assmoremobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'assmoremobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/tasks/:task?/views/monthlymyplanstaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.monthlymyplanstaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'monthlymyplanstaskmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'tasks/:task?/views/monthlymyplanstaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.monthlymyplanstaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'tasks', parameterName: 'task' },
                            { pathName: 'views', parameterName: 'monthlymyplanstaskmobmdview' },
                        ],
                        resource:'task',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/testtasks/:testtask?/views/moboptionviewclose',
                    meta: {
                        caption: 'entities.testtask.views.moboptionviewclose.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'testtasks', parameterName: 'testtask' },
                            { pathName: 'views', parameterName: 'moboptionviewclose' },
                        ],
                        resource:'testtask',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'testtasks/:testtask?/views/moboptionviewclose',
                    meta: {
                        caption: 'entities.testtask.views.moboptionviewclose.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testtasks', parameterName: 'testtask' },
                            { pathName: 'views', parameterName: 'moboptionviewclose' },
                        ],
                        resource:'testtask',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/testtasks/:testtask?/views/mobeditview',
                    meta: {
                        caption: 'entities.testtask.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'testtasks', parameterName: 'testtask' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'testtask',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'testtasks/:testtask?/views/mobeditview',
                    meta: {
                        caption: 'entities.testtask.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testtasks', parameterName: 'testtask' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'testtask',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/testtasks/:testtask?/views/newmobeditview',
                    meta: {
                        caption: 'entities.testtask.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'testtasks', parameterName: 'testtask' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'testtask',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'testtasks/:testtask?/views/newmobeditview',
                    meta: {
                        caption: 'entities.testtask.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testtasks', parameterName: 'testtask' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'testtask',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/testtasks/:testtask?/views/moboptionviewactivite',
                    meta: {
                        caption: 'entities.testtask.views.moboptionviewactivite.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'testtasks', parameterName: 'testtask' },
                            { pathName: 'views', parameterName: 'moboptionviewactivite' },
                        ],
                        resource:'testtask',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'testtasks/:testtask?/views/moboptionviewactivite',
                    meta: {
                        caption: 'entities.testtask.views.moboptionviewactivite.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testtasks', parameterName: 'testtask' },
                            { pathName: 'views', parameterName: 'moboptionviewactivite' },
                        ],
                        resource:'testtask',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/testtasks/:testtask?/views/editnewmobeditview',
                    meta: {
                        caption: 'entities.testtask.views.editnewmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'testtasks', parameterName: 'testtask' },
                            { pathName: 'views', parameterName: 'editnewmobeditview' },
                        ],
                        resource:'testtask',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'testtasks/:testtask?/views/editnewmobeditview',
                    meta: {
                        caption: 'entities.testtask.views.editnewmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testtasks', parameterName: 'testtask' },
                            { pathName: 'views', parameterName: 'editnewmobeditview' },
                        ],
                        resource:'testtask',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/testtasks/:testtask?/views/moboptionviewblock',
                    meta: {
                        caption: 'entities.testtask.views.moboptionviewblock.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'testtasks', parameterName: 'testtask' },
                            { pathName: 'views', parameterName: 'moboptionviewblock' },
                        ],
                        resource:'testtask',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'testtasks/:testtask?/views/moboptionviewblock',
                    meta: {
                        caption: 'entities.testtask.views.moboptionviewblock.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testtasks', parameterName: 'testtask' },
                            { pathName: 'views', parameterName: 'moboptionviewblock' },
                        ],
                        resource:'testtask',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/testtasks/:testtask?/views/mobtabexpview',
                    meta: {
                        caption: 'entities.testtask.views.mobtabexpview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'testtasks', parameterName: 'testtask' },
                            { pathName: 'views', parameterName: 'mobtabexpview' },
                        ],
                        resource:'testtask',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'testtasks/:testtask?/views/mobtabexpview',
                    meta: {
                        caption: 'entities.testtask.views.mobtabexpview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testtasks', parameterName: 'testtask' },
                            { pathName: 'views', parameterName: 'mobtabexpview' },
                        ],
                        resource:'testtask',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/testtasks/:testtask?/views/mobmdview',
                    meta: {
                        caption: 'entities.testtask.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'testtasks', parameterName: 'testtask' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'testtask',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'testtasks/:testtask?/views/mobmdview',
                    meta: {
                        caption: 'entities.testtask.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testtasks', parameterName: 'testtask' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'testtask',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/testtasks/:testtask?/views/moboptionviewstart',
                    meta: {
                        caption: 'entities.testtask.views.moboptionviewstart.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'testtasks', parameterName: 'testtask' },
                            { pathName: 'views', parameterName: 'moboptionviewstart' },
                        ],
                        resource:'testtask',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'testtasks/:testtask?/views/moboptionviewstart',
                    meta: {
                        caption: 'entities.testtask.views.moboptionviewstart.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testtasks', parameterName: 'testtask' },
                            { pathName: 'views', parameterName: 'moboptionviewstart' },
                        ],
                        resource:'testtask',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/editmobeditview',
                    meta: {
                        caption: 'entities.story.views.editmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'editmobeditview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/editmobeditview',
                    meta: {
                        caption: 'entities.story.views.editmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'editmobeditview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/editmobeditview',
                    meta: {
                        caption: 'entities.story.views.editmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'editmobeditview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/linkstorymobpickupmdview',
                    meta: {
                        caption: 'entities.story.views.linkstorymobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'linkstorymobpickupmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/linkstorymobpickupmdview',
                    meta: {
                        caption: 'entities.story.views.linkstorymobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'linkstorymobpickupmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/linkstorymobpickupmdview',
                    meta: {
                        caption: 'entities.story.views.linkstorymobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'linkstorymobpickupmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/mobeditview',
                    meta: {
                        caption: 'entities.story.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/mobeditview',
                    meta: {
                        caption: 'entities.story.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/mobeditview',
                    meta: {
                        caption: 'entities.story.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/acmoboptionview',
                    meta: {
                        caption: 'entities.story.views.acmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'acmoboptionview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/acmoboptionview',
                    meta: {
                        caption: 'entities.story.views.acmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'acmoboptionview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/acmoboptionview',
                    meta: {
                        caption: 'entities.story.views.acmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'acmoboptionview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/usr2mobmdview_5219',
                    meta: {
                        caption: 'entities.story.views.usr2mobmdview_5219.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr2mobmdview_5219' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/usr2mobmdview_5219',
                    meta: {
                        caption: 'entities.story.views.usr2mobmdview_5219.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr2mobmdview_5219' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/usr2mobmdview_5219',
                    meta: {
                        caption: 'entities.story.views.usr2mobmdview_5219.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr2mobmdview_5219' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/linkstorymobmpickupview',
                    meta: {
                        caption: 'entities.story.views.linkstorymobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'linkstorymobmpickupview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/linkstorymobmpickupview',
                    meta: {
                        caption: 'entities.story.views.linkstorymobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'linkstorymobmpickupview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/linkstorymobmpickupview',
                    meta: {
                        caption: 'entities.story.views.linkstorymobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'linkstorymobmpickupview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/asmoboptionview',
                    meta: {
                        caption: 'entities.story.views.asmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'asmoboptionview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/asmoboptionview',
                    meta: {
                        caption: 'entities.story.views.asmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'asmoboptionview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/asmoboptionview',
                    meta: {
                        caption: 'entities.story.views.asmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'asmoboptionview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/assmobmdview9',
                    meta: {
                        caption: 'entities.story.views.assmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'assmobmdview9' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/assmobmdview9',
                    meta: {
                        caption: 'entities.story.views.assmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'assmobmdview9' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/assmobmdview9',
                    meta: {
                        caption: 'entities.story.views.assmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'assmobmdview9' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/assmoremobmdview',
                    meta: {
                        caption: 'entities.story.views.assmoremobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'assmoremobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/assmoremobmdview',
                    meta: {
                        caption: 'entities.story.views.assmoremobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'assmoremobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/assmoremobmdview',
                    meta: {
                        caption: 'entities.story.views.assmoremobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'assmoremobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/usr3mobmdview',
                    meta: {
                        caption: 'entities.story.views.usr3mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr3mobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/usr3mobmdview',
                    meta: {
                        caption: 'entities.story.views.usr3mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr3mobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/usr3mobmdview',
                    meta: {
                        caption: 'entities.story.views.usr3mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr3mobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/newmobeditview',
                    meta: {
                        caption: 'entities.story.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/newmobeditview',
                    meta: {
                        caption: 'entities.story.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/newmobeditview',
                    meta: {
                        caption: 'entities.story.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/usr3mobpickupmdview',
                    meta: {
                        caption: 'entities.story.views.usr3mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr3mobpickupmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/usr3mobpickupmdview',
                    meta: {
                        caption: 'entities.story.views.usr3mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr3mobpickupmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/usr3mobpickupmdview',
                    meta: {
                        caption: 'entities.story.views.usr3mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr3mobpickupmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/favoritemobmdview',
                    meta: {
                        caption: 'entities.story.views.favoritemobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'favoritemobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/favoritemobmdview',
                    meta: {
                        caption: 'entities.story.views.favoritemobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'favoritemobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/favoritemobmdview',
                    meta: {
                        caption: 'entities.story.views.favoritemobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'favoritemobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/usr2mobpickupmdbuildview',
                    meta: {
                        caption: 'entities.story.views.usr2mobpickupmdbuildview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr2mobpickupmdbuildview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/usr2mobpickupmdbuildview',
                    meta: {
                        caption: 'entities.story.views.usr2mobpickupmdbuildview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr2mobpickupmdbuildview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/usr2mobpickupmdbuildview',
                    meta: {
                        caption: 'entities.story.views.usr2mobpickupmdbuildview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr2mobpickupmdbuildview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/mobpickupmdview',
                    meta: {
                        caption: 'entities.story.views.mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'mobpickupmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/mobpickupmdview',
                    meta: {
                        caption: 'entities.story.views.mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'mobpickupmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/mobpickupmdview',
                    meta: {
                        caption: 'entities.story.views.mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'mobpickupmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/mobmdviewcurproject',
                    meta: {
                        caption: 'entities.story.views.mobmdviewcurproject.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'mobmdviewcurproject' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/mobmdviewcurproject',
                    meta: {
                        caption: 'entities.story.views.mobmdviewcurproject.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'mobmdviewcurproject' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/mobmdviewcurproject',
                    meta: {
                        caption: 'entities.story.views.mobmdviewcurproject.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'mobmdviewcurproject' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/favoritemoremobmdview',
                    meta: {
                        caption: 'entities.story.views.favoritemoremobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'favoritemoremobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/favoritemoremobmdview',
                    meta: {
                        caption: 'entities.story.views.favoritemoremobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'favoritemoremobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/favoritemoremobmdview',
                    meta: {
                        caption: 'entities.story.views.favoritemoremobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'favoritemoremobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/logmobmdview9',
                    meta: {
                        caption: 'entities.story.views.logmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'logmobmdview9' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/logmobmdview9',
                    meta: {
                        caption: 'entities.story.views.logmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'logmobmdview9' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/logmobmdview9',
                    meta: {
                        caption: 'entities.story.views.logmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'logmobmdview9' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/mobpickupview',
                    meta: {
                        caption: 'entities.story.views.mobpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'mobpickupview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/mobpickupview',
                    meta: {
                        caption: 'entities.story.views.mobpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'mobpickupview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/mobpickupview',
                    meta: {
                        caption: 'entities.story.views.mobpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'mobpickupview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/rmoboptionview',
                    meta: {
                        caption: 'entities.story.views.rmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'rmoboptionview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/rmoboptionview',
                    meta: {
                        caption: 'entities.story.views.rmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'rmoboptionview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/rmoboptionview',
                    meta: {
                        caption: 'entities.story.views.rmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'rmoboptionview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/mobmdview',
                    meta: {
                        caption: 'entities.story.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/mobmdview',
                    meta: {
                        caption: 'entities.story.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/mobmdview',
                    meta: {
                        caption: 'entities.story.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/usr4mobmdview',
                    meta: {
                        caption: 'entities.story.views.usr4mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr4mobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/usr4mobmdview',
                    meta: {
                        caption: 'entities.story.views.usr4mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr4mobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/usr4mobmdview',
                    meta: {
                        caption: 'entities.story.views.usr4mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr4mobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/changemoboptionview',
                    meta: {
                        caption: 'entities.story.views.changemoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'changemoboptionview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/changemoboptionview',
                    meta: {
                        caption: 'entities.story.views.changemoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'changemoboptionview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/changemoboptionview',
                    meta: {
                        caption: 'entities.story.views.changemoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'changemoboptionview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/usr2mobmpickupview',
                    meta: {
                        caption: 'entities.story.views.usr2mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr2mobmpickupview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/usr2mobmpickupview',
                    meta: {
                        caption: 'entities.story.views.usr2mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr2mobmpickupview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/usr2mobmpickupview',
                    meta: {
                        caption: 'entities.story.views.usr2mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr2mobmpickupview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/mobmdview9',
                    meta: {
                        caption: 'entities.story.views.mobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/mobmdview9',
                    meta: {
                        caption: 'entities.story.views.mobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/mobmdview9',
                    meta: {
                        caption: 'entities.story.views.mobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/favoritemobmdview9',
                    meta: {
                        caption: 'entities.story.views.favoritemobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'favoritemobmdview9' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/favoritemobmdview9',
                    meta: {
                        caption: 'entities.story.views.favoritemobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'favoritemobmdview9' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/favoritemobmdview9',
                    meta: {
                        caption: 'entities.story.views.favoritemobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'favoritemobmdview9' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/usr2mobmdview',
                    meta: {
                        caption: 'entities.story.views.usr2mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr2mobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/usr2mobmdview',
                    meta: {
                        caption: 'entities.story.views.usr2mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr2mobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/usr2mobmdview',
                    meta: {
                        caption: 'entities.story.views.usr2mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr2mobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/usr2mobmpickupbuildview',
                    meta: {
                        caption: 'entities.story.views.usr2mobmpickupbuildview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr2mobmpickupbuildview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/usr2mobmpickupbuildview',
                    meta: {
                        caption: 'entities.story.views.usr2mobmpickupbuildview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr2mobmpickupbuildview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/usr2mobmpickupbuildview',
                    meta: {
                        caption: 'entities.story.views.usr2mobmpickupbuildview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr2mobmpickupbuildview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/moboptionview',
                    meta: {
                        caption: 'entities.story.views.moboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'moboptionview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/moboptionview',
                    meta: {
                        caption: 'entities.story.views.moboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'moboptionview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/moboptionview',
                    meta: {
                        caption: 'entities.story.views.moboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'moboptionview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/usr3mobmpickupview',
                    meta: {
                        caption: 'entities.story.views.usr3mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr3mobmpickupview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/usr3mobmpickupview',
                    meta: {
                        caption: 'entities.story.views.usr3mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr3mobmpickupview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/usr3mobmpickupview',
                    meta: {
                        caption: 'entities.story.views.usr3mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr3mobmpickupview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/assmobmdview',
                    meta: {
                        caption: 'entities.story.views.assmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'assmobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/assmobmdview',
                    meta: {
                        caption: 'entities.story.views.assmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'assmobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/assmobmdview',
                    meta: {
                        caption: 'entities.story.views.assmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'assmobmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/moblistview',
                    meta: {
                        caption: 'entities.story.views.moblistview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'moblistview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/moblistview',
                    meta: {
                        caption: 'entities.story.views.moblistview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'moblistview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/moblistview',
                    meta: {
                        caption: 'entities.story.views.moblistview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'moblistview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/usr2mobpickupmdview',
                    meta: {
                        caption: 'entities.story.views.usr2mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr2mobpickupmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/usr2mobpickupmdview',
                    meta: {
                        caption: 'entities.story.views.usr2mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr2mobpickupmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/usr2mobpickupmdview',
                    meta: {
                        caption: 'entities.story.views.usr2mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'usr2mobpickupmdview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'projects/:project?/stories/:story?/views/cmoboptionview',
                    meta: {
                        caption: 'entities.story.views.cmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'projects', parameterName: 'project' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'cmoboptionview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'products/:product?/stories/:story?/views/cmoboptionview',
                    meta: {
                        caption: 'entities.story.views.cmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'products', parameterName: 'product' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'cmoboptionview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'stories/:story?/views/cmoboptionview',
                    meta: {
                        caption: 'entities.story.views.cmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'stories', parameterName: 'story' },
                            { pathName: 'views', parameterName: 'cmoboptionview' },
                        ],
                        resource:'story',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'productlines/:productline?/views/mobpickupview',
                    meta: {
                        caption: 'entities.productline.views.mobpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'productlines', parameterName: 'productline' },
                            { pathName: 'views', parameterName: 'mobpickupview' },
                        ],
                        resource:'productline',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'productlines/:productline?/views/mobpickupmdview',
                    meta: {
                        caption: 'entities.productline.views.mobpickupmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'productlines', parameterName: 'productline' },
                            { pathName: 'views', parameterName: 'mobpickupmdview' },
                        ],
                        resource:'productline',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzmonthlies/:ibzmonthly?/views/createmobeditview',
                    meta: {
                        caption: 'entities.ibzmonthly.views.createmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzmonthlies', parameterName: 'ibzmonthly' },
                            { pathName: 'views', parameterName: 'createmobeditview' },
                        ],
                        resource:'ibzmonthly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzmonthlies/:ibzmonthly?/views/myreceivedmobtabexpview',
                    meta: {
                        caption: 'entities.ibzmonthly.views.myreceivedmobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzmonthlies', parameterName: 'ibzmonthly' },
                            { pathName: 'views', parameterName: 'myreceivedmobtabexpview' },
                        ],
                        resource:'ibzmonthly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzmonthlies/:ibzmonthly?/views/myreceivedmobeditview',
                    meta: {
                        caption: 'entities.ibzmonthly.views.myreceivedmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzmonthlies', parameterName: 'ibzmonthly' },
                            { pathName: 'views', parameterName: 'myreceivedmobeditview' },
                        ],
                        resource:'ibzmonthly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzmonthlies/:ibzmonthly?/views/editmobeditview',
                    meta: {
                        caption: 'entities.ibzmonthly.views.editmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzmonthlies', parameterName: 'ibzmonthly' },
                            { pathName: 'views', parameterName: 'editmobeditview' },
                        ],
                        resource:'ibzmonthly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzmonthlies/:ibzmonthly?/views/mobeditview',
                    meta: {
                        caption: 'entities.ibzmonthly.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzmonthlies', parameterName: 'ibzmonthly' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'ibzmonthly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzmonthlies/:ibzmonthly?/views/myreceivedmobmdview',
                    meta: {
                        caption: 'entities.ibzmonthly.views.myreceivedmobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzmonthlies', parameterName: 'ibzmonthly' },
                            { pathName: 'views', parameterName: 'myreceivedmobmdview' },
                        ],
                        resource:'ibzmonthly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzmonthlies/:ibzmonthly?/views/maininfomobtabexpview',
                    meta: {
                        caption: 'entities.ibzmonthly.views.maininfomobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzmonthlies', parameterName: 'ibzmonthly' },
                            { pathName: 'views', parameterName: 'maininfomobtabexpview' },
                        ],
                        resource:'ibzmonthly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzmonthlies/:ibzmonthly?/views/monthlymobmdview',
                    meta: {
                        caption: 'entities.ibzmonthly.views.monthlymobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzmonthlies', parameterName: 'ibzmonthly' },
                            { pathName: 'views', parameterName: 'monthlymobmdview' },
                        ],
                        resource:'ibzmonthly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzmonthlies/:ibzmonthly?/views/maininfomobeditview',
                    meta: {
                        caption: 'entities.ibzmonthly.views.maininfomobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzmonthlies', parameterName: 'ibzmonthly' },
                            { pathName: 'views', parameterName: 'maininfomobeditview' },
                        ],
                        resource:'ibzmonthly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzmonthlies/:ibzmonthly?/views/mysubmitmobmdview',
                    meta: {
                        caption: 'entities.ibzmonthly.views.mysubmitmobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzmonthlies', parameterName: 'ibzmonthly' },
                            { pathName: 'views', parameterName: 'mysubmitmobmdview' },
                        ],
                        resource:'ibzmonthly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'sysemployees/:sysemployee?/views/headportraitmobeditview',
                    meta: {
                        caption: 'entities.sysemployee.views.headportraitmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'sysemployees', parameterName: 'sysemployee' },
                            { pathName: 'views', parameterName: 'headportraitmobeditview' },
                        ],
                        resource:'sysemployee',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'sysemployees/:sysemployee?/views/mpkmobpickuptreeview',
                    meta: {
                        caption: 'entities.sysemployee.views.mpkmobpickuptreeview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'sysemployees', parameterName: 'sysemployee' },
                            { pathName: 'views', parameterName: 'mpkmobpickuptreeview' },
                        ],
                        resource:'sysemployee',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'sysemployees/:sysemployee?/views/treemobpickupview',
                    meta: {
                        caption: 'entities.sysemployee.views.treemobpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'sysemployees', parameterName: 'sysemployee' },
                            { pathName: 'views', parameterName: 'treemobpickupview' },
                        ],
                        resource:'sysemployee',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'sysemployees/:sysemployee?/views/mobpickuptreeview',
                    meta: {
                        caption: 'entities.sysemployee.views.mobpickuptreeview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'sysemployees', parameterName: 'sysemployee' },
                            { pathName: 'views', parameterName: 'mobpickuptreeview' },
                        ],
                        resource:'sysemployee',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'sysemployees/:sysemployee?/views/usertreemobmpickupview',
                    meta: {
                        caption: 'entities.sysemployee.views.usertreemobmpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'sysemployees', parameterName: 'sysemployee' },
                            { pathName: 'views', parameterName: 'usertreemobmpickupview' },
                        ],
                        resource:'sysemployee',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'sysemployees/:sysemployee?/views/loginmobeditview',
                    meta: {
                        caption: 'entities.sysemployee.views.loginmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'sysemployees', parameterName: 'sysemployee' },
                            { pathName: 'views', parameterName: 'loginmobeditview' },
                        ],
                        resource:'sysemployee',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'taskteams/:taskteam?/views/mobmdview9',
                    meta: {
                        caption: 'entities.taskteam.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'taskteams', parameterName: 'taskteam' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'taskteam',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzmyterritories/:ibzmyterritory?/views/mobmdview9',
                    meta: {
                        caption: 'entities.ibzmyterritory.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzmyterritories', parameterName: 'ibzmyterritory' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'ibzmyterritory',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzmyterritories/:ibzmyterritory?/views/mobtabexpview',
                    meta: {
                        caption: 'entities.ibzmyterritory.views.mobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzmyterritories', parameterName: 'ibzmyterritory' },
                            { pathName: 'views', parameterName: 'mobtabexpview' },
                        ],
                        resource:'ibzmyterritory',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzmyterritories/:ibzmyterritory?/views/mobdashboardview',
                    meta: {
                        caption: 'entities.ibzmyterritory.views.mobdashboardview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzmyterritories', parameterName: 'ibzmyterritory' },
                            { pathName: 'views', parameterName: 'mobdashboardview' },
                        ],
                        resource:'ibzmyterritory',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzmyterritories/:ibzmyterritory?/views/dailymobtabexpview',
                    meta: {
                        caption: 'entities.ibzmyterritory.views.dailymobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzmyterritories', parameterName: 'ibzmyterritory' },
                            { pathName: 'views', parameterName: 'dailymobtabexpview' },
                        ],
                        resource:'ibzmyterritory',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzmyterritories/:ibzmyterritory?/views/mobcalendarview',
                    meta: {
                        caption: 'entities.ibzmyterritory.views.mobcalendarview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzmyterritories', parameterName: 'ibzmyterritory' },
                            { pathName: 'views', parameterName: 'mobcalendarview' },
                        ],
                        resource:'ibzmyterritory',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzmyterritories/:ibzmyterritory?/views/reportmobtabexpview',
                    meta: {
                        caption: 'entities.ibzmyterritory.views.reportmobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzmyterritories', parameterName: 'ibzmyterritory' },
                            { pathName: 'views', parameterName: 'reportmobtabexpview' },
                        ],
                        resource:'ibzmyterritory',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'doclibs/:doclib?/docs/:doc?/actions/:action?/views/mobmdview9',
                    meta: {
                        caption: 'entities.action.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'doclibs', parameterName: 'doclib' },
                            { pathName: 'docs', parameterName: 'doc' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'testsuites/:testsuite?/actions/:action?/views/mobmdview9',
                    meta: {
                        caption: 'entities.action.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testsuites', parameterName: 'testsuite' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'ibzreportlies/:ibzreportly?/actions/:action?/views/mobmdview9',
                    meta: {
                        caption: 'entities.action.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzreportlies', parameterName: 'ibzreportly' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'ibzmonthlies/:ibzmonthly?/actions/:action?/views/mobmdview9',
                    meta: {
                        caption: 'entities.action.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzmonthlies', parameterName: 'ibzmonthly' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'ibzdailies/:ibzdaily?/actions/:action?/views/mobmdview9',
                    meta: {
                        caption: 'entities.action.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzdailies', parameterName: 'ibzdaily' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'docs/:doc?/actions/:action?/views/mobmdview9',
                    meta: {
                        caption: 'entities.action.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'docs', parameterName: 'doc' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'doclibs/:doclib?/actions/:action?/views/mobmdview9',
                    meta: {
                        caption: 'entities.action.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'doclibs', parameterName: 'doclib' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'ibzweeklies/:ibzweekly?/actions/:action?/views/mobmdview9',
                    meta: {
                        caption: 'entities.action.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzweeklies', parameterName: 'ibzweekly' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'todos/:todo?/actions/:action?/views/mobmdview9',
                    meta: {
                        caption: 'entities.action.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'todos', parameterName: 'todo' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'actions/:action?/views/mobmdview9',
                    meta: {
                        caption: 'entities.action.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'action',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'doclibs/:doclib?/docs/:doc?/actions/:action?/views/allmobmdview9',
                    meta: {
                        caption: 'entities.action.views.allmobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'doclibs', parameterName: 'doclib' },
                            { pathName: 'docs', parameterName: 'doc' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'allmobmdview9' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'testsuites/:testsuite?/actions/:action?/views/allmobmdview9',
                    meta: {
                        caption: 'entities.action.views.allmobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testsuites', parameterName: 'testsuite' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'allmobmdview9' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'ibzreportlies/:ibzreportly?/actions/:action?/views/allmobmdview9',
                    meta: {
                        caption: 'entities.action.views.allmobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzreportlies', parameterName: 'ibzreportly' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'allmobmdview9' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'ibzmonthlies/:ibzmonthly?/actions/:action?/views/allmobmdview9',
                    meta: {
                        caption: 'entities.action.views.allmobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzmonthlies', parameterName: 'ibzmonthly' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'allmobmdview9' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'ibzdailies/:ibzdaily?/actions/:action?/views/allmobmdview9',
                    meta: {
                        caption: 'entities.action.views.allmobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzdailies', parameterName: 'ibzdaily' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'allmobmdview9' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'docs/:doc?/actions/:action?/views/allmobmdview9',
                    meta: {
                        caption: 'entities.action.views.allmobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'docs', parameterName: 'doc' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'allmobmdview9' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'doclibs/:doclib?/actions/:action?/views/allmobmdview9',
                    meta: {
                        caption: 'entities.action.views.allmobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'doclibs', parameterName: 'doclib' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'allmobmdview9' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'ibzweeklies/:ibzweekly?/actions/:action?/views/allmobmdview9',
                    meta: {
                        caption: 'entities.action.views.allmobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzweeklies', parameterName: 'ibzweekly' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'allmobmdview9' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'todos/:todo?/actions/:action?/views/allmobmdview9',
                    meta: {
                        caption: 'entities.action.views.allmobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'todos', parameterName: 'todo' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'allmobmdview9' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'actions/:action?/views/allmobmdview9',
                    meta: {
                        caption: 'entities.action.views.allmobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'allmobmdview9' },
                        ],
                        resource:'action',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'doclibs/:doclib?/docs/:doc?/actions/:action?/views/moremobmdview',
                    meta: {
                        caption: 'entities.action.views.moremobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'doclibs', parameterName: 'doclib' },
                            { pathName: 'docs', parameterName: 'doc' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'moremobmdview' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'testsuites/:testsuite?/actions/:action?/views/moremobmdview',
                    meta: {
                        caption: 'entities.action.views.moremobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testsuites', parameterName: 'testsuite' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'moremobmdview' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'ibzreportlies/:ibzreportly?/actions/:action?/views/moremobmdview',
                    meta: {
                        caption: 'entities.action.views.moremobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzreportlies', parameterName: 'ibzreportly' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'moremobmdview' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'ibzmonthlies/:ibzmonthly?/actions/:action?/views/moremobmdview',
                    meta: {
                        caption: 'entities.action.views.moremobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzmonthlies', parameterName: 'ibzmonthly' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'moremobmdview' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'ibzdailies/:ibzdaily?/actions/:action?/views/moremobmdview',
                    meta: {
                        caption: 'entities.action.views.moremobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzdailies', parameterName: 'ibzdaily' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'moremobmdview' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'docs/:doc?/actions/:action?/views/moremobmdview',
                    meta: {
                        caption: 'entities.action.views.moremobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'docs', parameterName: 'doc' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'moremobmdview' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'doclibs/:doclib?/actions/:action?/views/moremobmdview',
                    meta: {
                        caption: 'entities.action.views.moremobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'doclibs', parameterName: 'doclib' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'moremobmdview' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'ibzweeklies/:ibzweekly?/actions/:action?/views/moremobmdview',
                    meta: {
                        caption: 'entities.action.views.moremobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzweeklies', parameterName: 'ibzweekly' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'moremobmdview' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'todos/:todo?/actions/:action?/views/moremobmdview',
                    meta: {
                        caption: 'entities.action.views.moremobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'todos', parameterName: 'todo' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'moremobmdview' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'actions/:action?/views/moremobmdview',
                    meta: {
                        caption: 'entities.action.views.moremobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'moremobmdview' },
                        ],
                        resource:'action',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'doclibs/:doclib?/docs/:doc?/actions/:action?/views/mobmapview',
                    meta: {
                        caption: 'entities.action.views.mobmapview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'doclibs', parameterName: 'doclib' },
                            { pathName: 'docs', parameterName: 'doc' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'mobmapview' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'testsuites/:testsuite?/actions/:action?/views/mobmapview',
                    meta: {
                        caption: 'entities.action.views.mobmapview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testsuites', parameterName: 'testsuite' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'mobmapview' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'ibzreportlies/:ibzreportly?/actions/:action?/views/mobmapview',
                    meta: {
                        caption: 'entities.action.views.mobmapview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzreportlies', parameterName: 'ibzreportly' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'mobmapview' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'ibzmonthlies/:ibzmonthly?/actions/:action?/views/mobmapview',
                    meta: {
                        caption: 'entities.action.views.mobmapview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzmonthlies', parameterName: 'ibzmonthly' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'mobmapview' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'ibzdailies/:ibzdaily?/actions/:action?/views/mobmapview',
                    meta: {
                        caption: 'entities.action.views.mobmapview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzdailies', parameterName: 'ibzdaily' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'mobmapview' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'docs/:doc?/actions/:action?/views/mobmapview',
                    meta: {
                        caption: 'entities.action.views.mobmapview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'docs', parameterName: 'doc' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'mobmapview' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'doclibs/:doclib?/actions/:action?/views/mobmapview',
                    meta: {
                        caption: 'entities.action.views.mobmapview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'doclibs', parameterName: 'doclib' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'mobmapview' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'ibzweeklies/:ibzweekly?/actions/:action?/views/mobmapview',
                    meta: {
                        caption: 'entities.action.views.mobmapview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzweeklies', parameterName: 'ibzweekly' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'mobmapview' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'todos/:todo?/actions/:action?/views/mobmapview',
                    meta: {
                        caption: 'entities.action.views.mobmapview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'todos', parameterName: 'todo' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'mobmapview' },
                        ],
                        resource:'action',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'actions/:action?/views/mobmapview',
                    meta: {
                        caption: 'entities.action.views.mobmapview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'actions', parameterName: 'action' },
                            { pathName: 'views', parameterName: 'mobmapview' },
                        ],
                        resource:'action',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzfavorites/:ibzfavorites?/views/mobtabexpview',
                    meta: {
                        caption: 'entities.ibzfavorites.views.mobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzfavorites', parameterName: 'ibzfavorites' },
                            { pathName: 'views', parameterName: 'mobtabexpview' },
                        ],
                        resource:'ibzfavorites',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzreportlies/:ibzreportly?/views/maininfomobeditview',
                    meta: {
                        caption: 'entities.ibzreportly.views.maininfomobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzreportlies', parameterName: 'ibzreportly' },
                            { pathName: 'views', parameterName: 'maininfomobeditview' },
                        ],
                        resource:'ibzreportly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzreportlies/:ibzreportly?/views/createmobeditview',
                    meta: {
                        caption: 'entities.ibzreportly.views.createmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzreportlies', parameterName: 'ibzreportly' },
                            { pathName: 'views', parameterName: 'createmobeditview' },
                        ],
                        resource:'ibzreportly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzreportlies/:ibzreportly?/views/reportlymobmdview',
                    meta: {
                        caption: 'entities.ibzreportly.views.reportlymobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzreportlies', parameterName: 'ibzreportly' },
                            { pathName: 'views', parameterName: 'reportlymobmdview' },
                        ],
                        resource:'ibzreportly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzreportlies/:ibzreportly?/views/mobeditview',
                    meta: {
                        caption: 'entities.ibzreportly.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzreportlies', parameterName: 'ibzreportly' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'ibzreportly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibztaskteams/:ibztaskteam?/views/mobeditview9',
                    meta: {
                        caption: 'entities.ibztaskteam.views.mobeditview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibztaskteams', parameterName: 'ibztaskteam' },
                            { pathName: 'views', parameterName: 'mobeditview9' },
                        ],
                        resource:'ibztaskteam',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibztaskteams/:ibztaskteam?/views/mobmeditview9',
                    meta: {
                        caption: 'entities.ibztaskteam.views.mobmeditview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibztaskteams', parameterName: 'ibztaskteam' },
                            { pathName: 'views', parameterName: 'mobmeditview9' },
                        ],
                        resource:'ibztaskteam',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibztaskestimates/:ibztaskestimate?/views/mobmeditview9',
                    meta: {
                        caption: 'entities.ibztaskestimate.views.mobmeditview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibztaskestimates', parameterName: 'ibztaskestimate' },
                            { pathName: 'views', parameterName: 'mobmeditview9' },
                        ],
                        resource:'ibztaskestimate',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibztaskestimates/:ibztaskestimate?/views/mobeditview9',
                    meta: {
                        caption: 'entities.ibztaskestimate.views.mobeditview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibztaskestimates', parameterName: 'ibztaskestimate' },
                            { pathName: 'views', parameterName: 'mobeditview9' },
                        ],
                        resource:'ibztaskestimate',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'testsuites/:testsuite?/views/mobtabexpview',
                    meta: {
                        caption: 'entities.testsuite.views.mobtabexpview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-suitcase',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testsuites', parameterName: 'testsuite' },
                            { pathName: 'views', parameterName: 'mobtabexpview' },
                        ],
                        resource:'testsuite',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'testsuites/:testsuite?/views/newmobeditview',
                    meta: {
                        caption: 'entities.testsuite.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-suitcase',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testsuites', parameterName: 'testsuite' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'testsuite',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'testsuites/:testsuite?/views/mobeditview',
                    meta: {
                        caption: 'entities.testsuite.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-suitcase',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testsuites', parameterName: 'testsuite' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'testsuite',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'testsuites/:testsuite?/views/mobmdview',
                    meta: {
                        caption: 'entities.testsuite.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-suitcase',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'testsuites', parameterName: 'testsuite' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'testsuite',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'files/:file?/views/mobmdview9',
                    meta: {
                        caption: 'entities.file.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'files', parameterName: 'file' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'file',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzdailies/:ibzdaily?/views/dailymobmdview',
                    meta: {
                        caption: 'entities.ibzdaily.views.dailymobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzdailies', parameterName: 'ibzdaily' },
                            { pathName: 'views', parameterName: 'dailymobmdview' },
                        ],
                        resource:'ibzdaily',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzdailies/:ibzdaily?/views/dailyreportsubmitmobmdview',
                    meta: {
                        caption: 'entities.ibzdaily.views.dailyreportsubmitmobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzdailies', parameterName: 'ibzdaily' },
                            { pathName: 'views', parameterName: 'dailyreportsubmitmobmdview' },
                        ],
                        resource:'ibzdaily',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzdailies/:ibzdaily?/views/dailycreatemobeditview',
                    meta: {
                        caption: 'entities.ibzdaily.views.dailycreatemobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzdailies', parameterName: 'ibzdaily' },
                            { pathName: 'views', parameterName: 'dailycreatemobeditview' },
                        ],
                        resource:'ibzdaily',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzdailies/:ibzdaily?/views/dailymobeditview',
                    meta: {
                        caption: 'entities.ibzdaily.views.dailymobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzdailies', parameterName: 'ibzdaily' },
                            { pathName: 'views', parameterName: 'dailymobeditview' },
                        ],
                        resource:'ibzdaily',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzdailies/:ibzdaily?/views/dailycompletetaskmobmdview',
                    meta: {
                        caption: 'entities.ibzdaily.views.dailycompletetaskmobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzdailies', parameterName: 'ibzdaily' },
                            { pathName: 'views', parameterName: 'dailycompletetaskmobmdview' },
                        ],
                        resource:'ibzdaily',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzdailies/:ibzdaily?/views/reportreceivedmobmdview',
                    meta: {
                        caption: 'entities.ibzdaily.views.reportreceivedmobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzdailies', parameterName: 'ibzdaily' },
                            { pathName: 'views', parameterName: 'reportreceivedmobmdview' },
                        ],
                        resource:'ibzdaily',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzdailies/:ibzdaily?/views/dailymobtabexpview',
                    meta: {
                        caption: 'entities.ibzdaily.views.dailymobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzdailies', parameterName: 'ibzdaily' },
                            { pathName: 'views', parameterName: 'dailymobtabexpview' },
                        ],
                        resource:'ibzdaily',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzdailies/:ibzdaily?/views/mydailymobtabexpview',
                    meta: {
                        caption: 'entities.ibzdaily.views.mydailymobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzdailies', parameterName: 'ibzdaily' },
                            { pathName: 'views', parameterName: 'mydailymobtabexpview' },
                        ],
                        resource:'ibzdaily',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzdailies/:ibzdaily?/views/mobeditview',
                    meta: {
                        caption: 'entities.ibzdaily.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzdailies', parameterName: 'ibzdaily' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'ibzdaily',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzdailies/:ibzdaily?/views/mymobmdview',
                    meta: {
                        caption: 'entities.ibzdaily.views.mymobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzdailies', parameterName: 'ibzdaily' },
                            { pathName: 'views', parameterName: 'mymobmdview' },
                        ],
                        resource:'ibzdaily',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzdailies/:ibzdaily?/views/myremobeditview',
                    meta: {
                        caption: 'entities.ibzdaily.views.myremobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzdailies', parameterName: 'ibzdaily' },
                            { pathName: 'views', parameterName: 'myremobeditview' },
                        ],
                        resource:'ibzdaily',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzdailies/:ibzdaily?/views/dailyplanstomorrowtaskmobmdview',
                    meta: {
                        caption: 'entities.ibzdaily.views.dailyplanstomorrowtaskmobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzdailies', parameterName: 'ibzdaily' },
                            { pathName: 'views', parameterName: 'dailyplanstomorrowtaskmobmdview' },
                        ],
                        resource:'ibzdaily',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzdailies/:ibzdaily?/views/dailyinfomobeditview',
                    meta: {
                        caption: 'entities.ibzdaily.views.dailyinfomobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzdailies', parameterName: 'ibzdaily' },
                            { pathName: 'views', parameterName: 'dailyinfomobeditview' },
                        ],
                        resource:'ibzdaily',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'doclibs/:doclib?/views/projectdoclibmobtreeview',
                    meta: {
                        caption: 'entities.doclib.views.projectdoclibmobtreeview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'doclibs', parameterName: 'doclib' },
                            { pathName: 'views', parameterName: 'projectdoclibmobtreeview' },
                        ],
                        resource:'doclib',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'doclibs/:doclib?/views/mobproducttreeview',
                    meta: {
                        caption: 'entities.doclib.views.mobproducttreeview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'doclibs', parameterName: 'doclib' },
                            { pathName: 'views', parameterName: 'mobproducttreeview' },
                        ],
                        resource:'doclib',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'doclibs/:doclib?/views/mobeditview',
                    meta: {
                        caption: 'entities.doclib.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'doclibs', parameterName: 'doclib' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'doclib',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'modules/:module?/views/mobpickupmdview',
                    meta: {
                        caption: 'entities.module.views.mobpickupmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'modules', parameterName: 'module' },
                            { pathName: 'views', parameterName: 'mobpickupmdview' },
                        ],
                        resource:'module',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'modules/:module?/views/mobpickupview',
                    meta: {
                        caption: 'entities.module.views.mobpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'modules', parameterName: 'module' },
                            { pathName: 'views', parameterName: 'mobpickupview' },
                        ],
                        resource:'module',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'sysupdatelogs/:sysupdatelog?/views/mobeditview',
                    meta: {
                        caption: 'entities.sysupdatelog.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'sysupdatelogs', parameterName: 'sysupdatelog' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'sysupdatelog',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'sysupdatelogs/:sysupdatelog?/views/mobmdview',
                    meta: {
                        caption: 'entities.sysupdatelog.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'sysupdatelogs', parameterName: 'sysupdatelog' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'sysupdatelog',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'users/:user?/views/mobmpickupview',
                    meta: {
                        caption: 'entities.user.views.mobmpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'users', parameterName: 'user' },
                            { pathName: 'views', parameterName: 'mobmpickupview' },
                        ],
                        resource:'user',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'users/:user?/views/usercentermobeditview',
                    meta: {
                        caption: 'entities.user.views.usercentermobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'users', parameterName: 'user' },
                            { pathName: 'views', parameterName: 'usercentermobeditview' },
                        ],
                        resource:'user',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'users/:user?/views/mobpickupmdview',
                    meta: {
                        caption: 'entities.user.views.mobpickupmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'users', parameterName: 'user' },
                            { pathName: 'views', parameterName: 'mobpickupmdview' },
                        ],
                        resource:'user',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'users/:user?/views/mobpickupview',
                    meta: {
                        caption: 'entities.user.views.mobpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'users', parameterName: 'user' },
                            { pathName: 'views', parameterName: 'mobpickupview' },
                        ],
                        resource:'user',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzreports/:ibzreport?/views/mobmdview',
                    meta: {
                        caption: 'entities.ibzreport.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzreports', parameterName: 'ibzreport' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'ibzreport',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzreports/:ibzreport?/views/myremobmdview',
                    meta: {
                        caption: 'entities.ibzreport.views.myremobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzreports', parameterName: 'ibzreport' },
                            { pathName: 'views', parameterName: 'myremobmdview' },
                        ],
                        resource:'ibzreport',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'productstats/:productstats?/views/mobmdview',
                    meta: {
                        caption: 'entities.productstats.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'productstats', parameterName: 'productstats' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'productstats',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'productstats/:productstats?/views/testmobmdview',
                    meta: {
                        caption: 'entities.productstats.views.testmobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'productstats', parameterName: 'productstats' },
                            { pathName: 'views', parameterName: 'testmobmdview' },
                        ],
                        resource:'productstats',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'productstats/:productstats?/views/mobtabexpview',
                    meta: {
                        caption: 'entities.productstats.views.mobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'productstats', parameterName: 'productstats' },
                            { pathName: 'views', parameterName: 'mobtabexpview' },
                        ],
                        resource:'productstats',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'sysupdatelogs/:sysupdatelog?/sysupdatefeatures/:sysupdatefeatures?/views/mobeditview',
                    meta: {
                        caption: 'entities.sysupdatefeatures.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'sysupdatelogs', parameterName: 'sysupdatelog' },
                            { pathName: 'sysupdatefeatures', parameterName: 'sysupdatefeatures' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'sysupdatefeatures',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'sysupdatefeatures/:sysupdatefeatures?/views/mobeditview',
                    meta: {
                        caption: 'entities.sysupdatefeatures.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'sysupdatefeatures', parameterName: 'sysupdatefeatures' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'sysupdatefeatures',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'sysupdatelogs/:sysupdatelog?/sysupdatefeatures/:sysupdatefeatures?/views/ymobmdview9',
                    meta: {
                        caption: 'entities.sysupdatefeatures.views.ymobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'sysupdatelogs', parameterName: 'sysupdatelog' },
                            { pathName: 'sysupdatefeatures', parameterName: 'sysupdatefeatures' },
                            { pathName: 'views', parameterName: 'ymobmdview9' },
                        ],
                        resource:'sysupdatefeatures',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'sysupdatefeatures/:sysupdatefeatures?/views/ymobmdview9',
                    meta: {
                        caption: 'entities.sysupdatefeatures.views.ymobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'sysupdatefeatures', parameterName: 'sysupdatefeatures' },
                            { pathName: 'views', parameterName: 'ymobmdview9' },
                        ],
                        resource:'sysupdatefeatures',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'sysupdatelogs/:sysupdatelog?/sysupdatefeatures/:sysupdatefeatures?/views/mobmdview',
                    meta: {
                        caption: 'entities.sysupdatefeatures.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'sysupdatelogs', parameterName: 'sysupdatelog' },
                            { pathName: 'sysupdatefeatures', parameterName: 'sysupdatefeatures' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'sysupdatefeatures',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'sysupdatefeatures/:sysupdatefeatures?/views/mobmdview',
                    meta: {
                        caption: 'entities.sysupdatefeatures.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'sysupdatefeatures', parameterName: 'sysupdatefeatures' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'sysupdatefeatures',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'sysupdatelogs/:sysupdatelog?/sysupdatefeatures/:sysupdatefeatures?/views/mobmdview9',
                    meta: {
                        caption: 'entities.sysupdatefeatures.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'sysupdatelogs', parameterName: 'sysupdatelog' },
                            { pathName: 'sysupdatefeatures', parameterName: 'sysupdatefeatures' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'sysupdatefeatures',
                        requireAuth: true,
                        },
                        component: AppViewShell,
                    },
                    {
                    path: 'sysupdatefeatures/:sysupdatefeatures?/views/mobmdview9',
                    meta: {
                        caption: 'entities.sysupdatefeatures.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'sysupdatefeatures', parameterName: 'sysupdatefeatures' },
                            { pathName: 'views', parameterName: 'mobmdview9' },
                        ],
                        resource:'sysupdatefeatures',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'todos/:todo?/views/newmobeditview',
                    meta: {
                        caption: 'entities.todo.views.newmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'todos', parameterName: 'todo' },
                            { pathName: 'views', parameterName: 'newmobeditview' },
                        ],
                        resource:'todo',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'todos/:todo?/views/mobmdview',
                    meta: {
                        caption: 'entities.todo.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'todos', parameterName: 'todo' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'todo',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'todos/:todo?/views/mobredirectview',
                    meta: {
                        caption: 'entities.todo.views.mobredirectview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'todos', parameterName: 'todo' },
                            { pathName: 'views', parameterName: 'mobredirectview' },
                        ],
                        resource:'todo',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'todos/:todo?/views/moboptionview',
                    meta: {
                        caption: 'entities.todo.views.moboptionview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'todos', parameterName: 'todo' },
                            { pathName: 'views', parameterName: 'moboptionview' },
                        ],
                        resource:'todo',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'todos/:todo?/views/moblistview',
                    meta: {
                        caption: 'entities.todo.views.moblistview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'todos', parameterName: 'todo' },
                            { pathName: 'views', parameterName: 'moblistview' },
                        ],
                        resource:'todo',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'todos/:todo?/views/mobeditview',
                    meta: {
                        caption: 'entities.todo.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'todos', parameterName: 'todo' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'todo',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzweeklies/:ibzweekly?/views/mobeditview',
                    meta: {
                        caption: 'entities.ibzweekly.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzweeklies', parameterName: 'ibzweekly' },
                            { pathName: 'views', parameterName: 'mobeditview' },
                        ],
                        resource:'ibzweekly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzweeklies/:ibzweekly?/views/mobmdview',
                    meta: {
                        caption: 'entities.ibzweekly.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzweeklies', parameterName: 'ibzweekly' },
                            { pathName: 'views', parameterName: 'mobmdview' },
                        ],
                        resource:'ibzweekly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzweeklies/:ibzweekly?/views/usr2mobtabexpview',
                    meta: {
                        caption: 'entities.ibzweekly.views.usr2mobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzweeklies', parameterName: 'ibzweekly' },
                            { pathName: 'views', parameterName: 'usr2mobtabexpview' },
                        ],
                        resource:'ibzweekly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzweeklies/:ibzweekly?/views/mobeditviewmian',
                    meta: {
                        caption: 'entities.ibzweekly.views.mobeditviewmian.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzweeklies', parameterName: 'ibzweekly' },
                            { pathName: 'views', parameterName: 'mobeditviewmian' },
                        ],
                        resource:'ibzweekly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzweeklies/:ibzweekly?/views/mobeditviewmainmytijiao',
                    meta: {
                        caption: 'entities.ibzweekly.views.mobeditviewmainmytijiao.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzweeklies', parameterName: 'ibzweekly' },
                            { pathName: 'views', parameterName: 'mobeditviewmainmytijiao' },
                        ],
                        resource:'ibzweekly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzweeklies/:ibzweekly?/views/mobeditviewmainreceived',
                    meta: {
                        caption: 'entities.ibzweekly.views.mobeditviewmainreceived.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzweeklies', parameterName: 'ibzweekly' },
                            { pathName: 'views', parameterName: 'mobeditviewmainreceived' },
                        ],
                        resource:'ibzweekly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzweeklies/:ibzweekly?/views/usr2mobtabexpviewmytijiao',
                    meta: {
                        caption: 'entities.ibzweekly.views.usr2mobtabexpviewmytijiao.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzweeklies', parameterName: 'ibzweekly' },
                            { pathName: 'views', parameterName: 'usr2mobtabexpviewmytijiao' },
                        ],
                        resource:'ibzweekly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzweeklies/:ibzweekly?/views/usr2mobtabexpviewmyreceived',
                    meta: {
                        caption: 'entities.ibzweekly.views.usr2mobtabexpviewmyreceived.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzweeklies', parameterName: 'ibzweekly' },
                            { pathName: 'views', parameterName: 'usr2mobtabexpviewmyreceived' },
                        ],
                        resource:'ibzweekly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzweeklies/:ibzweekly?/views/mobeditviewcreate',
                    meta: {
                        caption: 'entities.ibzweekly.views.mobeditviewcreate.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzweeklies', parameterName: 'ibzweekly' },
                            { pathName: 'views', parameterName: 'mobeditviewcreate' },
                        ],
                        resource:'ibzweekly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzweeklies/:ibzweekly?/views/usr2mobeditview',
                    meta: {
                        caption: 'entities.ibzweekly.views.usr2mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzweeklies', parameterName: 'ibzweekly' },
                            { pathName: 'views', parameterName: 'usr2mobeditview' },
                        ],
                        resource:'ibzweekly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzweeklies/:ibzweekly?/views/usr2mobmdview',
                    meta: {
                        caption: 'entities.ibzweekly.views.usr2mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzweeklies', parameterName: 'ibzweekly' },
                            { pathName: 'views', parameterName: 'usr2mobmdview' },
                        ],
                        resource:'ibzweekly',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                    {
                    path: 'ibzprojectteams/:ibzprojectteam?/views/projectteammobmeditview',
                    meta: {
                        caption: 'entities.ibzprojectteam.views.projectteammobmeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-users',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'ibzprojectteams', parameterName: 'ibzprojectteam' },
                            { pathName: 'views', parameterName: 'projectteammobmeditview' },
                        ],
                        resource:'ibzprojectteam',
                        requireAuth: true,
                    },
                    component: AppViewShell
                    },
                {
                    path: 'views/taskteammobeditview9',
                    meta: {
                        caption: 'entities.ibztaskteam.views.mobeditview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskteammobeditview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskactivemobtask',
                    meta: {
                        caption: 'entities.task.views.activemobtask.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskactivemobtask' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzweeklymobeditview',
                    meta: {
                        caption: 'entities.ibzweekly.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzweeklymobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzweeklymobmdview',
                    meta: {
                        caption: 'entities.ibzweekly.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzweeklymobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/projectteamprojectteammobeditview',
                    meta: {
                        caption: 'entities.projectteam.views.projectteammobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-users',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'projectteamprojectteammobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugusr3mobmpickupleftview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobmpickupleftview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugusr3mobmpickupleftview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storyeditmobeditview',
                    meta: {
                        caption: 'entities.story.views.editmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storyeditmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/producttestmobmdview',
                    meta: {
                        caption: 'entities.product.views.testmobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'producttestmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/testtaskmoboptionviewclose',
                    meta: {
                        caption: 'entities.testtask.views.moboptionviewclose.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'testtaskmoboptionviewclose' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productmodulemobpickupview',
                    meta: {
                        caption: 'entities.productmodule.views.mobpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productmodulemobpickupview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzweeklyusr2mobtabexpview',
                    meta: {
                        caption: 'entities.ibzweekly.views.usr2mobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzweeklyusr2mobtabexpview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzdailydailymobmdview',
                    meta: {
                        caption: 'entities.ibzdaily.views.dailymobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzdailydailymobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/todonewmobeditview',
                    meta: {
                        caption: 'entities.todo.views.newmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'todonewmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzreportlymaininfomobeditview',
                    meta: {
                        caption: 'entities.ibzreportly.views.maininfomobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzreportlymaininfomobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzdailydailyreportsubmitmobmdview',
                    meta: {
                        caption: 'entities.ibzdaily.views.dailyreportsubmitmobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzdailydailyreportsubmitmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugeditnewmobeditview',
                    meta: {
                        caption: 'entities.bug.views.editnewmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugeditnewmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/sysemployeeheadportraitmobeditview',
                    meta: {
                        caption: 'entities.sysemployee.views.headportraitmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'sysemployeeheadportraitmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugmobeditview',
                    meta: {
                        caption: 'entities.bug.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storylinkstorymobpickupmdview',
                    meta: {
                        caption: 'entities.story.views.linkstorymobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storylinkstorymobpickupmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugusr3mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugusr3mobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/projectmobtabexpview',
                    meta: {
                        caption: 'entities.project.views.mobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'projectmobtabexpview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/casemobeditview',
                    meta: {
                        caption: 'entities.testcase.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'casemobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzmonthlycreatemobeditview',
                    meta: {
                        caption: 'entities.ibzmonthly.views.createmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzmonthlycreatemobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/casemobmdview',
                    meta: {
                        caption: 'entities.testcase.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'casemobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storymobeditview',
                    meta: {
                        caption: 'entities.story.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storymobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugresolvemobeditview',
                    meta: {
                        caption: 'entities.bug.views.resolvemobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugresolvemobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storyacmoboptionview',
                    meta: {
                        caption: 'entities.story.views.acmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storyacmoboptionview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/todomobmdview',
                    meta: {
                        caption: 'entities.todo.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'todomobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/buildmobmpickupview',
                    meta: {
                        caption: 'entities.build.views.mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'buildmobmpickupview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/releasemobmdview',
                    meta: {
                        caption: 'entities.productrelease.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-flag-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'releasemobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storyusr2mobmdview_5219',
                    meta: {
                        caption: 'entities.story.views.usr2mobmdview_5219.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storyusr2mobmdview_5219' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/sysemployeempkmobpickuptreeview',
                    meta: {
                        caption: 'entities.sysemployee.views.mpkmobpickuptreeview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'sysemployeempkmobpickuptreeview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/doclibprojectdoclibmobtreeview',
                    meta: {
                        caption: 'entities.doclib.views.projectdoclibmobtreeview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'doclibprojectdoclibmobtreeview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskmycompletetaskmobmdviewweekly',
                    meta: {
                        caption: 'entities.task.views.mycompletetaskmobmdviewweekly.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskmycompletetaskmobmdviewweekly' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/projectmobmdview',
                    meta: {
                        caption: 'entities.project.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'projectmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productmobtabexpview',
                    meta: {
                        caption: 'entities.product.views.mobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productmobtabexpview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productmobpickupview',
                    meta: {
                        caption: 'entities.product.views.mobpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productmobpickupview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzmonthlymyreceivedmobtabexpview',
                    meta: {
                        caption: 'entities.ibzmonthly.views.myreceivedmobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzmonthlymyreceivedmobtabexpview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/actionmobmdview9',
                    meta: {
                        caption: 'entities.action.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'actionmobmdview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/projectmobpickupview',
                    meta: {
                        caption: 'entities.project.views.mobpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'projectmobpickupview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskeditmobeditview',
                    meta: {
                        caption: 'entities.task.views.editmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskeditmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugassigntomobeditview',
                    meta: {
                        caption: 'entities.bug.views.assigntomobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugassigntomobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productmobmdview',
                    meta: {
                        caption: 'entities.product.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzreportlycreatemobeditview',
                    meta: {
                        caption: 'entities.ibzreportly.views.createmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzreportlycreatemobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/projectstartmobeditview',
                    meta: {
                        caption: 'entities.project.views.startmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'projectstartmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskcancelmoboptionview',
                    meta: {
                        caption: 'entities.task.views.cancelmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskcancelmoboptionview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/buildmobeditview',
                    meta: {
                        caption: 'entities.build.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'buildmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/modulemobpickupmdview',
                    meta: {
                        caption: 'entities.module.views.mobpickupmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'modulemobpickupmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/sysemployeetreemobpickupview',
                    meta: {
                        caption: 'entities.sysemployee.views.treemobpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'sysemployeetreemobpickupview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storylinkstorymobmpickupview',
                    meta: {
                        caption: 'entities.story.views.linkstorymobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storylinkstorymobmpickupview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskassmobmdview9',
                    meta: {
                        caption: 'entities.task.views.assmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskassmobmdview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/buildlogmobeditview',
                    meta: {
                        caption: 'entities.build.views.logmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'buildlogmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugassmobmdview',
                    meta: {
                        caption: 'entities.bug.views.assmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugassmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/filemobmdview9',
                    meta: {
                        caption: 'entities.file.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'filemobmdview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzmonthlymyreceivedmobeditview',
                    meta: {
                        caption: 'entities.ibzmonthly.views.myreceivedmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzmonthlymyreceivedmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productlinemobpickupview',
                    meta: {
                        caption: 'entities.productline.views.mobpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productlinemobpickupview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productstatsmobmdview',
                    meta: {
                        caption: 'entities.productstats.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productstatsmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzreportlyreportlymobmdview',
                    meta: {
                        caption: 'entities.ibzreportly.views.reportlymobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzreportlyreportlymobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzweeklymobeditviewmian',
                    meta: {
                        caption: 'entities.ibzweekly.views.mobeditviewmian.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzweeklymobeditviewmian' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/buildeditmobeditview',
                    meta: {
                        caption: 'entities.build.views.editmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'buildeditmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/modulemobpickupview',
                    meta: {
                        caption: 'entities.module.views.mobpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'modulemobpickupview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productplanmobeditview',
                    meta: {
                        caption: 'entities.productplan.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productplanmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugusr2mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr2mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugusr2mobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskteammobmdview9',
                    meta: {
                        caption: 'entities.taskteam.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskteammobmdview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugcolsemobeditview',
                    meta: {
                        caption: 'entities.bug.views.colsemobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugcolsemobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/projectmobeditview',
                    meta: {
                        caption: 'entities.project.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'projectmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productplanmobtabexpview',
                    meta: {
                        caption: 'entities.productplan.views.mobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productplanmobtabexpview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/sysemployeemobpickuptreeview',
                    meta: {
                        caption: 'entities.sysemployee.views.mobpickuptreeview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'sysemployeemobpickuptreeview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/sysemployeeusertreemobmpickupview',
                    meta: {
                        caption: 'entities.sysemployee.views.usertreemobmpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'sysemployeeusertreemobmpickupview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/buildmobpickupview',
                    meta: {
                        caption: 'entities.build.views.mobpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'buildmobpickupview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/testtaskmobeditview',
                    meta: {
                        caption: 'entities.testtask.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'testtaskmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugusr3mobpickupbuildresolvedmdview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobpickupbuildresolvedmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugusr3mobpickupbuildresolvedmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/projectactivitemobeditview',
                    meta: {
                        caption: 'entities.project.views.activitemobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'projectactivitemobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskfavoritemobmdview',
                    meta: {
                        caption: 'entities.task.views.favoritemobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskfavoritemobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/caseusr2mobmpickupview',
                    meta: {
                        caption: 'entities.testcase.views.usr2mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'caseusr2mobmpickupview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugclosemoboptionview',
                    meta: {
                        caption: 'entities.bug.views.closemoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugclosemoboptionview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/testtasknewmobeditview',
                    meta: {
                        caption: 'entities.testtask.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'testtasknewmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productstatstestmobmdview',
                    meta: {
                        caption: 'entities.productstats.views.testmobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productstatstestmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzmonthlyeditmobeditview',
                    meta: {
                        caption: 'entities.ibzmonthly.views.editmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzmonthlyeditmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzweeklymobeditviewmainmytijiao',
                    meta: {
                        caption: 'entities.ibzweekly.views.mobeditviewmainmytijiao.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzweeklymobeditviewmainmytijiao' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzdailydailycreatemobeditview',
                    meta: {
                        caption: 'entities.ibzdaily.views.dailycreatemobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzdailydailycreatemobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzmonthlymobeditview',
                    meta: {
                        caption: 'entities.ibzmonthly.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzmonthlymobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/testtaskmoboptionviewactivite',
                    meta: {
                        caption: 'entities.testtask.views.moboptionviewactivite.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'testtaskmoboptionviewactivite' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storyasmoboptionview',
                    meta: {
                        caption: 'entities.story.views.asmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storyasmoboptionview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzdailydailymobeditview',
                    meta: {
                        caption: 'entities.ibzdaily.views.dailymobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzdailydailymobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storyassmobmdview9',
                    meta: {
                        caption: 'entities.story.views.assmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storyassmobmdview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storyassmoremobmdview',
                    meta: {
                        caption: 'entities.story.views.assmoremobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storyassmoremobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskmoboptionview',
                    meta: {
                        caption: 'entities.task.views.moboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskmoboptionview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugusr3mobmpickupview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugusr3mobmpickupview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storyusr3mobmdview',
                    meta: {
                        caption: 'entities.story.views.usr3mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storyusr3mobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskfavoritemobmdview9',
                    meta: {
                        caption: 'entities.task.views.favoritemobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskfavoritemobmdview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/todomoboptionview',
                    meta: {
                        caption: 'entities.todo.views.moboptionview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'todomoboptionview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzmyterritorymobmdview9',
                    meta: {
                        caption: 'entities.ibzmyterritory.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzmyterritorymobmdview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productlinemobpickupmdview',
                    meta: {
                        caption: 'entities.productline.views.mobpickupmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productlinemobpickupmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/projectprojectteammanagemobeditview',
                    meta: {
                        caption: 'entities.project.views.projectteammanagemobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'projectprojectteammanagemobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storynewmobeditview',
                    meta: {
                        caption: 'entities.story.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storynewmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/testsuitemobtabexpview',
                    meta: {
                        caption: 'entities.testsuite.views.mobtabexpview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-suitcase',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'testsuitemobtabexpview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskestimatemobmdview',
                    meta: {
                        caption: 'entities.taskestimate.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskestimatemobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productclosemobeditview',
                    meta: {
                        caption: 'entities.product.views.closemobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productclosemobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskteammobmeditview9',
                    meta: {
                        caption: 'entities.ibztaskteam.views.mobmeditview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskteammobmeditview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/actionallmobmdview9',
                    meta: {
                        caption: 'entities.action.views.allmobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'actionallmobmdview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugusr4mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr4mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugusr4mobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/tasknewmobeditview',
                    meta: {
                        caption: 'entities.task.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'tasknewmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzdailydailycompletetaskmobmdview',
                    meta: {
                        caption: 'entities.ibzdaily.views.dailycompletetaskmobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzdailydailycompletetaskmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/caseusr2mobpickupmdview',
                    meta: {
                        caption: 'entities.testcase.views.usr2mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'caseusr2mobpickupmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskmonthlyplanstaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.monthlyplanstaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskmonthlyplanstaskmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugusr6mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr6mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugusr6mobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/releasemobpickupmdview',
                    meta: {
                        caption: 'entities.productrelease.views.mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-flag-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'releasemobpickupmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storyusr3mobpickupmdview',
                    meta: {
                        caption: 'entities.story.views.usr3mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storyusr3mobpickupmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzdailyreportreceivedmobmdview',
                    meta: {
                        caption: 'entities.ibzdaily.views.reportreceivedmobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzdailyreportreceivedmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/casecreatecasemobeditview',
                    meta: {
                        caption: 'entities.testcase.views.createcasemobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'casecreatecasemobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugassmobmdview9',
                    meta: {
                        caption: 'entities.bug.views.assmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugassmobmdview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/projectteammobmdview',
                    meta: {
                        caption: 'entities.projectteam.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-users',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'projectteammobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/appportalview',
                    meta: {
                        caption: 'app.views.appportalview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'appportalview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzweeklymobeditviewmainreceived',
                    meta: {
                        caption: 'entities.ibzweekly.views.mobeditviewmainreceived.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzweeklymobeditviewmainreceived' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzmonthlymyreceivedmobmdview',
                    meta: {
                        caption: 'entities.ibzmonthly.views.myreceivedmobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzmonthlymyreceivedmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskmycompletetaskmobmdview1',
                    meta: {
                        caption: 'entities.task.views.mycompletetaskmobmdview1.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskmycompletetaskmobmdview1' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storyfavoritemobmdview',
                    meta: {
                        caption: 'entities.story.views.favoritemobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storyfavoritemobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/releasemobtabexpview',
                    meta: {
                        caption: 'entities.productrelease.views.mobtabexpview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-flag-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'releasemobtabexpview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzdailydailymobtabexpview',
                    meta: {
                        caption: 'entities.ibzdaily.views.dailymobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzdailydailymobtabexpview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productmobeditview',
                    meta: {
                        caption: 'entities.product.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzdailymydailymobtabexpview',
                    meta: {
                        caption: 'entities.ibzdaily.views.mydailymobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzdailymydailymobtabexpview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskdailydonetaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.dailydonetaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskdailydonetaskmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzmonthlymaininfomobtabexpview',
                    meta: {
                        caption: 'entities.ibzmonthly.views.maininfomobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzmonthlymaininfomobtabexpview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/testsuitenewmobeditview',
                    meta: {
                        caption: 'entities.testsuite.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-suitcase',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'testsuitenewmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storyusr2mobpickupmdbuildview',
                    meta: {
                        caption: 'entities.story.views.usr2mobpickupmdbuildview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storyusr2mobpickupmdbuildview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugusr5mobmdview',
                    meta: {
                        caption: 'entities.bug.views.usr5mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugusr5mobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/buildmobtabexpview',
                    meta: {
                        caption: 'entities.build.views.mobtabexpview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'buildmobtabexpview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/projectmodulemobpickupmdview',
                    meta: {
                        caption: 'entities.projectmodule.views.mobpickupmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'projectmodulemobpickupmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/sysupdatefeaturesmobeditview',
                    meta: {
                        caption: 'entities.sysupdatefeatures.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'sysupdatefeaturesmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzmyterritorymobtabexpview',
                    meta: {
                        caption: 'entities.ibzmyterritory.views.mobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzmyterritorymobtabexpview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/projectsupmobeditview',
                    meta: {
                        caption: 'entities.project.views.supmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'projectsupmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storymobpickupmdview',
                    meta: {
                        caption: 'entities.story.views.mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storymobpickupmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/sysupdatefeaturesymobmdview9',
                    meta: {
                        caption: 'entities.sysupdatefeatures.views.ymobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'sysupdatefeaturesymobmdview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzweeklyusr2mobtabexpviewmytijiao',
                    meta: {
                        caption: 'entities.ibzweekly.views.usr2mobtabexpviewmytijiao.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzweeklyusr2mobtabexpviewmytijiao' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskstartmoboptionview',
                    meta: {
                        caption: 'entities.task.views.startmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskstartmoboptionview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/todomoblistview',
                    meta: {
                        caption: 'entities.todo.views.moblistview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'todomoblistview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/actionmoremobmdview',
                    meta: {
                        caption: 'entities.action.views.moremobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'actionmoremobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskmonthlymycompletetaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.monthlymycompletetaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskmonthlymycompletetaskmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/testsuitemobeditview',
                    meta: {
                        caption: 'entities.testsuite.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-suitcase',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'testsuitemobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugconfirmmobeditview',
                    meta: {
                        caption: 'entities.bug.views.confirmmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugconfirmmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugrmoboptionview',
                    meta: {
                        caption: 'entities.bug.views.rmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugrmoboptionview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/doclibmobproducttreeview',
                    meta: {
                        caption: 'entities.doclib.views.mobproducttreeview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'doclibmobproducttreeview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzmyterritorymobdashboardview',
                    meta: {
                        caption: 'entities.ibzmyterritory.views.mobdashboardview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzmyterritorymobdashboardview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugnewmobeditview',
                    meta: {
                        caption: 'entities.bug.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugnewmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskmobpickupmdview',
                    meta: {
                        caption: 'entities.task.views.mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskmobpickupmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzweeklyusr2mobtabexpviewmyreceived',
                    meta: {
                        caption: 'entities.ibzweekly.views.usr2mobtabexpviewmyreceived.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzweeklyusr2mobtabexpviewmyreceived' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/projectmobchartview',
                    meta: {
                        caption: 'entities.project.views.mobchartview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'projectmobchartview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzweeklymobeditviewcreate',
                    meta: {
                        caption: 'entities.ibzweekly.views.mobeditviewcreate.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzweeklymobeditviewcreate' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/usermobmpickupview',
                    meta: {
                        caption: 'entities.user.views.mobmpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'usermobmpickupview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/releaseeditmobeditview',
                    meta: {
                        caption: 'entities.productrelease.views.editmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-flag-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'releaseeditmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskestimatemobmeditview9',
                    meta: {
                        caption: 'entities.ibztaskestimate.views.mobmeditview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskestimatemobmeditview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzmonthlymonthlymobmdview',
                    meta: {
                        caption: 'entities.ibzmonthly.views.monthlymobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzmonthlymonthlymobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskestimatemobeditview9',
                    meta: {
                        caption: 'entities.ibztaskestimate.views.mobeditview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskestimatemobeditview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storymobmdviewcurproject',
                    meta: {
                        caption: 'entities.story.views.mobmdviewcurproject.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storymobmdviewcurproject' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzweeklyusr2mobeditview',
                    meta: {
                        caption: 'entities.ibzweekly.views.usr2mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzweeklyusr2mobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storyfavoritemoremobmdview',
                    meta: {
                        caption: 'entities.story.views.favoritemoremobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storyfavoritemoremobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/buildmobpickupmdview',
                    meta: {
                        caption: 'entities.build.views.mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'buildmobpickupmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzmyterritorydailymobtabexpview',
                    meta: {
                        caption: 'entities.ibzmyterritory.views.dailymobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzmyterritorydailymobtabexpview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/casemobmdview_testtask',
                    meta: {
                        caption: 'entities.testcase.views.mobmdview_testtask.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'casemobmdview_testtask' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productstatsmobtabexpview',
                    meta: {
                        caption: 'entities.productstats.views.mobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productstatsmobtabexpview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugusr2mobmpickupview',
                    meta: {
                        caption: 'entities.bug.views.usr2mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugusr2mobmpickupview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugusr3mobpickupmdview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugusr3mobpickupmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskweeklyplanstaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.weeklyplanstaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskweeklyplanstaskmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugassmoboptionview',
                    meta: {
                        caption: 'entities.bug.views.assmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugassmoboptionview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskstopmoboptionview',
                    meta: {
                        caption: 'entities.task.views.stopmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskstopmoboptionview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storylogmobmdview9',
                    meta: {
                        caption: 'entities.story.views.logmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storylogmobmdview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzmonthlymaininfomobeditview',
                    meta: {
                        caption: 'entities.ibzmonthly.views.maininfomobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzmonthlymaininfomobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzmonthlymysubmitmobmdview',
                    meta: {
                        caption: 'entities.ibzmonthly.views.mysubmitmobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzmonthlymysubmitmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storymobpickupview',
                    meta: {
                        caption: 'entities.story.views.mobpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storymobpickupview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storyrmoboptionview',
                    meta: {
                        caption: 'entities.story.views.rmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storyrmoboptionview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/testtaskeditnewmobeditview',
                    meta: {
                        caption: 'entities.testtask.views.editnewmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'testtaskeditnewmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugacmoboptionview',
                    meta: {
                        caption: 'entities.bug.views.acmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugacmoboptionview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/buildlogmobmdview',
                    meta: {
                        caption: 'entities.build.views.logmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'buildlogmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storymobmdview',
                    meta: {
                        caption: 'entities.story.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storymobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskgsmoboptionview',
                    meta: {
                        caption: 'entities.task.views.gsmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskgsmoboptionview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzdailymobeditview',
                    meta: {
                        caption: 'entities.ibzdaily.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzdailymobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/sysupdatefeaturesmobmdview',
                    meta: {
                        caption: 'entities.sysupdatefeatures.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'sysupdatefeaturesmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/testsuitemobmdview',
                    meta: {
                        caption: 'entities.testsuite.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-suitcase',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'testsuitemobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugcmoboptionview',
                    meta: {
                        caption: 'entities.bug.views.cmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugcmoboptionview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskusr2moboptionview',
                    meta: {
                        caption: 'entities.task.views.usr2moboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskusr2moboptionview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugtestmobmdview',
                    meta: {
                        caption: 'entities.bug.views.testmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugtestmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzweeklyusr2mobmdview',
                    meta: {
                        caption: 'entities.ibzweekly.views.usr2mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzweeklyusr2mobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storyusr4mobmdview',
                    meta: {
                        caption: 'entities.story.views.usr4mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storyusr4mobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/userusercentermobeditview',
                    meta: {
                        caption: 'entities.user.views.usercentermobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'userusercentermobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzfavoritesmobtabexpview',
                    meta: {
                        caption: 'entities.ibzfavorites.views.mobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzfavoritesmobtabexpview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskestimatemobmdview9',
                    meta: {
                        caption: 'entities.taskestimate.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskestimatemobmdview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzmyterritorymobcalendarview',
                    meta: {
                        caption: 'entities.ibzmyterritory.views.mobcalendarview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzmyterritorymobcalendarview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskweeklylydonetaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.weeklylydonetaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskweeklylydonetaskmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productprodmobtabexpview',
                    meta: {
                        caption: 'entities.product.views.prodmobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productprodmobtabexpview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/projectclosemobeditview',
                    meta: {
                        caption: 'entities.project.views.closemobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'projectclosemobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzdailymymobmdview',
                    meta: {
                        caption: 'entities.ibzdaily.views.mymobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzdailymymobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugplanmobmdview9',
                    meta: {
                        caption: 'entities.bug.views.planmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugplanmobmdview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzdailymyremobeditview',
                    meta: {
                        caption: 'entities.ibzdaily.views.myremobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzdailymyremobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productplaneditmobeditview',
                    meta: {
                        caption: 'entities.productplan.views.editmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productplaneditmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskcommoboptionview',
                    meta: {
                        caption: 'entities.task.views.commoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskcommoboptionview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzmyterritoryreportmobtabexpview',
                    meta: {
                        caption: 'entities.ibzmyterritory.views.reportmobtabexpview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzmyterritoryreportmobtabexpview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskmobeditview',
                    meta: {
                        caption: 'entities.task.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskassmobmdview',
                    meta: {
                        caption: 'entities.task.views.assmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskassmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storychangemoboptionview',
                    meta: {
                        caption: 'entities.story.views.changemoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storychangemoboptionview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskestimatemoboptionview',
                    meta: {
                        caption: 'entities.taskestimate.views.moboptionview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskestimatemoboptionview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/sysupdatefeaturesmobmdview9',
                    meta: {
                        caption: 'entities.sysupdatefeatures.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'sysupdatefeaturesmobmdview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskmonthlydonetaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.monthlydonetaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskmonthlydonetaskmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/sysemployeeloginmobeditview',
                    meta: {
                        caption: 'entities.sysemployee.views.loginmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'sysemployeeloginmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/todomobeditview',
                    meta: {
                        caption: 'entities.todo.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'todomobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskmycompletetaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.mycompletetaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskmycompletetaskmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/releasemobpickupview',
                    meta: {
                        caption: 'entities.productrelease.views.mobpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-flag-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'releasemobpickupview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugusr3mobmpickupbuildcreatebugview',
                    meta: {
                        caption: 'entities.bug.views.usr3mobmpickupbuildcreatebugview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugusr3mobmpickupbuildcreatebugview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/projectmodulemobpickupview',
                    meta: {
                        caption: 'entities.projectmodule.views.mobpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'projectmodulemobpickupview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/testtaskmoboptionviewblock',
                    meta: {
                        caption: 'entities.testtask.views.moboptionviewblock.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'testtaskmoboptionviewblock' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzreportmobmdview',
                    meta: {
                        caption: 'entities.ibzreport.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzreportmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productplanmobmdview',
                    meta: {
                        caption: 'entities.productplan.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productplanmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/testtaskmobtabexpview',
                    meta: {
                        caption: 'entities.testtask.views.mobtabexpview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'testtaskmobtabexpview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storyusr2mobmpickupview',
                    meta: {
                        caption: 'entities.story.views.usr2mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storyusr2mobmpickupview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productplannewmobeditview',
                    meta: {
                        caption: 'entities.productplan.views.newmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productplannewmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/testtaskmobmdview',
                    meta: {
                        caption: 'entities.testtask.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'testtaskmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzdailydailyplanstomorrowtaskmobmdview',
                    meta: {
                        caption: 'entities.ibzdaily.views.dailyplanstomorrowtaskmobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzdailydailyplanstomorrowtaskmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productplanusr2mobpickupmdview',
                    meta: {
                        caption: 'entities.productplan.views.usr2mobpickupmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productplanusr2mobpickupmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugmobmdview',
                    meta: {
                        caption: 'entities.bug.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/sysupdatelogmobeditview',
                    meta: {
                        caption: 'entities.sysupdatelog.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'sysupdatelogmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugusr3mobpickupmdview1',
                    meta: {
                        caption: 'entities.bug.views.usr3mobpickupmdview1.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugusr3mobpickupmdview1' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugactivationmobeditview',
                    meta: {
                        caption: 'entities.bug.views.activationmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugactivationmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/buildnewmobeditview',
                    meta: {
                        caption: 'entities.build.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'buildnewmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskfavoritemoremobmdview',
                    meta: {
                        caption: 'entities.task.views.favoritemoremobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskfavoritemoremobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storymobmdview9',
                    meta: {
                        caption: 'entities.story.views.mobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storymobmdview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskclosemoboptionview',
                    meta: {
                        caption: 'entities.task.views.closemoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskclosemoboptionview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/actionmobmapview',
                    meta: {
                        caption: 'entities.action.views.mobmapview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'actionmobmapview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storyfavoritemobmdview9',
                    meta: {
                        caption: 'entities.story.views.favoritemobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storyfavoritemobmdview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/casemobmdview_testsuite',
                    meta: {
                        caption: 'entities.testcase.views.mobmdview_testsuite.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'casemobmdview_testsuite' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskmobmdview',
                    meta: {
                        caption: 'entities.task.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/releasenewmobeditview',
                    meta: {
                        caption: 'entities.productrelease.views.newmobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-flag-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'releasenewmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productnewmobeditview',
                    meta: {
                        caption: 'entities.product.views.newmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productnewmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskdailyplanstaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.dailyplanstaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskdailyplanstaskmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productmobpickupmdview',
                    meta: {
                        caption: 'entities.product.views.mobpickupmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productmobpickupmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/projectteamprojectteammobmeditview',
                    meta: {
                        caption: 'entities.ibzprojectteam.views.projectteammobmeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-users',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'projectteamprojectteammobmeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/casestepmobmdview9',
                    meta: {
                        caption: 'entities.testcasestep.views.mobmdview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'casestepmobmdview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskmyplanstomorrowtaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.myplanstomorrowtaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskmyplanstomorrowtaskmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskmobpickupview',
                    meta: {
                        caption: 'entities.task.views.mobpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskmobpickupview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/testtaskmoboptionviewstart',
                    meta: {
                        caption: 'entities.testtask.views.moboptionviewstart.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-clipboard',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'testtaskmoboptionviewstart' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storyusr2mobmdview',
                    meta: {
                        caption: 'entities.story.views.usr2mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storyusr2mobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storyusr2mobmpickupbuildview',
                    meta: {
                        caption: 'entities.story.views.usr2mobmpickupbuildview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storyusr2mobmpickupbuildview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskmycompletetaskmobmdviewnextplanweekly',
                    meta: {
                        caption: 'entities.task.views.mycompletetaskmobmdviewnextplanweekly.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskmycompletetaskmobmdviewnextplanweekly' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storymoboptionview',
                    meta: {
                        caption: 'entities.story.views.moboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storymoboptionview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzreportlymobeditview',
                    meta: {
                        caption: 'entities.ibzreportly.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzreportlymobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/doclibmobeditview',
                    meta: {
                        caption: 'entities.doclib.views.mobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'doclibmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productmobchartview',
                    meta: {
                        caption: 'entities.product.views.mobchartview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productmobchartview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storyusr3mobmpickupview',
                    meta: {
                        caption: 'entities.story.views.usr3mobmpickupview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storyusr3mobmpickupview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugusr2mobpickupmdview',
                    meta: {
                        caption: 'entities.bug.views.usr2mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugusr2mobpickupmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/bugassmoremobmdview',
                    meta: {
                        caption: 'entities.bug.views.assmoremobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'bugassmoremobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/buildmobmdview',
                    meta: {
                        caption: 'entities.build.views.mobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-code-fork',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'buildmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzdailydailyinfomobeditview',
                    meta: {
                        caption: 'entities.ibzdaily.views.dailyinfomobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzdailydailyinfomobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storyassmobmdview',
                    meta: {
                        caption: 'entities.story.views.assmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storyassmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productmodulemobpickupmdview',
                    meta: {
                        caption: 'entities.productmodule.views.mobpickupmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productmodulemobpickupmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/releasemobeditview',
                    meta: {
                        caption: 'entities.productrelease.views.mobeditview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-flag-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'releasemobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskassmoremobmdview',
                    meta: {
                        caption: 'entities.task.views.assmoremobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskassmoremobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/usermobpickupmdview',
                    meta: {
                        caption: 'entities.user.views.mobpickupmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'usermobpickupmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storymoblistview',
                    meta: {
                        caption: 'entities.story.views.moblistview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storymoblistview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/projectnewmobeditview',
                    meta: {
                        caption: 'entities.project.views.newmobeditview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'projectnewmobeditview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/projectmobpickupmdview',
                    meta: {
                        caption: 'entities.project.views.mobpickupmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'projectmobpickupmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/buglogmobmdview9',
                    meta: {
                        caption: 'entities.bug.views.logmobmdview9.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-bug',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'buglogmobmdview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/sysupdatelogmobmdview',
                    meta: {
                        caption: 'entities.sysupdatelog.views.mobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'sysupdatelogmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/taskmonthlymyplanstaskmobmdview',
                    meta: {
                        caption: 'entities.task.views.monthlymyplanstaskmobmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-tasks',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'taskmonthlymyplanstaskmobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storyusr2mobpickupmdview',
                    meta: {
                        caption: 'entities.story.views.usr2mobpickupmdview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storyusr2mobpickupmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/productmobchartview9',
                    meta: {
                        caption: 'entities.product.views.mobchartview9.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'productmobchartview9' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/appportalview2',
                    meta: {
                        caption: 'app.views.appportalview2.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'appportalview2' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/storycmoboptionview',
                    meta: {
                        caption: 'entities.story.views.cmoboptionview.caption',
                        info:'',
                        imgPath: '',
                        iconCls: 'fa fa-star-o',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'storycmoboptionview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/ibzreportmyremobmdview',
                    meta: {
                        caption: 'entities.ibzreport.views.myremobmdview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'ibzreportmyremobmdview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
                {
                    path: 'views/usermobpickupview',
                    meta: {
                        caption: 'entities.user.views.mobpickupview.caption',
                        info:'',
                        parameters: [
                            { pathName: 'appindexview', parameterName: 'appindexview' },
                            { pathName: 'views', parameterName: 'usermobpickupview' },
                        ],
                        requireAuth: true,
                    },
                    component: AppViewShell,
                },
            ...indexRoutes,
            ],
        },
        ...globalRoutes,
        {
            path: '/login/:login?',
            name: 'login',
            meta: {  
                caption: '登录',
                viewType: 'login',
                requireAuth: false,
                ignoreAddPage: true,
            },
            beforeEnter: (to: any, from: any, next: any) => {
                router.app.$store.commit('resetRootStateData');
                next();
            },
            component: () => import('ibiz-vue/src/components/common/login/login.vue'),
        },
        {
            path: '/appredirectview',
            name: 'appredirectview',
            meta: {  
                caption: '全局应用重定向视图',
                viewType: 'REDIRECTVIEW',
                requireAuth: false,
                ignoreAddPage: true,
            },
            component: () => import('@components/app-redirect-view/app-redirect-view.vue'),
        },
        {
            path: '/404',
            component: () => import('ibiz-vue/src/components/common/404/404.vue')
        },
        {
            path: '/500',
            component: () => import('ibiz-vue/src/components/common/500/500.vue')
        },
        {
            path: '*',
            redirect: 'appindexview'
        },
    ],
});


// 解决路由跳转路由重复时报错
const originalPush = Router.prototype.push
Router.prototype.push = function push(location: any) {
    let result: any = originalPush.call(this, location);
    return result.catch((err: any) => err);
}
export default router;
