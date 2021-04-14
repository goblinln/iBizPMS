import IBZTaskTeam_en_US_Base from './ibztask-team_en_US_base';

function getLocaleResource(){
    const IBZTaskTeam_en_US_OwnData = {};
    const targetData = Object.assign(IBZTaskTeam_en_US_Base(), IBZTaskTeam_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;