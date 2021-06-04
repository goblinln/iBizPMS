import ProjectWeekly_zh_CN_Base from './project-weekly_zh_CN_base';

function getLocaleResource(){
    const ProjectWeekly_zh_CN_OwnData = {};
    const targetData = Object.assign(ProjectWeekly_zh_CN_Base(), ProjectWeekly_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;