import ProjectStory_en_US_Base from './project-story_en_US_base';

function getLocaleResource(){
    const ProjectStory_en_US_OwnData = {};
    const targetData = Object.assign(ProjectStory_en_US_Base(), ProjectStory_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;