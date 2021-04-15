import IBZTaskTeam_zh_CN_Base from './ibztask-team_zh_CN_base';

function getLocaleResource(){
    const IBZTaskTeam_zh_CN_OwnData = {};
    const targetData = Object.assign(IBZTaskTeam_zh_CN_Base(), IBZTaskTeam_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;