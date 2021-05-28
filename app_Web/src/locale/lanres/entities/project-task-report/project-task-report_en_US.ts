import ProjectTaskReport_en_US_Base from './project-task-report_en_US_base';

function getLocaleResource(){
    const ProjectTaskReport_en_US_OwnData = {};
    const targetData = Object.assign(ProjectTaskReport_en_US_Base(), ProjectTaskReport_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;