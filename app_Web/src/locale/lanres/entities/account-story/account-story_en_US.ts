import AccountStory_en_US_Base from './account-story_en_US_base';

function getLocaleResource(){
    const AccountStory_en_US_OwnData = {};
    const targetData = Object.assign(AccountStory_en_US_Base(), AccountStory_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;