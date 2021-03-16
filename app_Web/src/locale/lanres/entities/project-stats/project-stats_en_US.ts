import ProjectStats_en_US_Base from './project-stats_en_US_base';

function getLocaleResource(){
    const ProjectStats_en_US_OwnData = {};
    const targetData = Object.assign(ProjectStats_en_US_Base(), ProjectStats_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;