import ProjectTaskestimate_en_US_Base from './project-taskestimate_en_US_base';

function getLocaleResource(){
    const ProjectTaskestimate_en_US_OwnData = {};
    const targetData = Object.assign(ProjectTaskestimate_en_US_Base(), ProjectTaskestimate_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;