import IBZStoryAction_en_US_Base from './ibzstory-action_en_US_base';

function getLocaleResource(){
    const IBZStoryAction_en_US_OwnData = {};
    const targetData = Object.assign(IBZStoryAction_en_US_Base(), IBZStoryAction_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;