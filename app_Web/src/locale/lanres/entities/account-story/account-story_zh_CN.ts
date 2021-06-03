import AccountStory_zh_CN_Base from './account-story_zh_CN_base';

function getLocaleResource(){
    const AccountStory_zh_CN_OwnData = {};
    const targetData = Object.assign(AccountStory_zh_CN_Base(), AccountStory_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;