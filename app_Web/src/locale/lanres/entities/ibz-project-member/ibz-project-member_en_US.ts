import IbzProjectMember_en_US_Base from './ibz-project-member_en_US_base';

function getLocaleResource(){
    const IbzProjectMember_en_US_OwnData = {};
    const targetData = Object.assign(IbzProjectMember_en_US_Base(), IbzProjectMember_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;