import ProjectDaily_en_US_Base from './project-daily_en_US_base';

function getLocaleResource(){
    const ProjectDaily_en_US_OwnData = {};
    const targetData = Object.assign(ProjectDaily_en_US_Base(), ProjectDaily_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;