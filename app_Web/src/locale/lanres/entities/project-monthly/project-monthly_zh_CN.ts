import ProjectMonthly_zh_CN_Base from './project-monthly_zh_CN_base';

function getLocaleResource(){
    const ProjectMonthly_zh_CN_OwnData = {};
    const targetData = Object.assign(ProjectMonthly_zh_CN_Base(), ProjectMonthly_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;