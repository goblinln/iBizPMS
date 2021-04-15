import IBZProStoryModule_en_US_Base from './ibzpro-story-module_en_US_base';

function getLocaleResource(){
    const IBZProStoryModule_en_US_OwnData = {};
    const targetData = Object.assign(IBZProStoryModule_en_US_Base(), IBZProStoryModule_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;