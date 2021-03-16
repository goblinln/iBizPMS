import Project_en_US_Base from './project_en_US_base';

function getLocaleResource(){
    const Project_en_US_OwnData = {};
    const targetData = Object.assign(Project_en_US_Base(), Project_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;