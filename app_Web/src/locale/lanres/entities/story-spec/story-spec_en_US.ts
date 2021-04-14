import StorySpec_en_US_Base from './story-spec_en_US_base';

function getLocaleResource(){
    const StorySpec_en_US_OwnData = {};
    const targetData = Object.assign(StorySpec_en_US_Base(), StorySpec_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;