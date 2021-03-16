import IBZProStory_en_US_Base from './ibzpro-story_en_US_base';

function getLocaleResource(){
    const IBZProStory_en_US_OwnData = {};
    const targetData = Object.assign(IBZProStory_en_US_Base(), IBZProStory_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;