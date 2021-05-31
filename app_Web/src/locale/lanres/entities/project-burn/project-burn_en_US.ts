import ProjectBurn_en_US_Base from './project-burn_en_US_base';

function getLocaleResource(){
    const ProjectBurn_en_US_OwnData = {};
    const targetData = Object.assign(ProjectBurn_en_US_Base(), ProjectBurn_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;