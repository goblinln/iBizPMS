import ProjectWeekly_en_US_Base from './project-weekly_en_US_base';

function getLocaleResource(){
    const ProjectWeekly_en_US_OwnData = {};
    const targetData = Object.assign(ProjectWeekly_en_US_Base(), ProjectWeekly_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;