import SubStory_en_US_Base from './sub-story_en_US_base';

function getLocaleResource(){
    const SubStory_en_US_OwnData = {};
    const targetData = Object.assign(SubStory_en_US_Base(), SubStory_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;