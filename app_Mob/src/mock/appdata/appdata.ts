import { MockAdapter } from '../mock-adapter';
const mock = MockAdapter.getInstance();

import Mock from 'mockjs'
const Random = Mock.Random;

// 获取应用数据
mock.onGet('appdata').reply((config: any) => {
    let status = MockAdapter.mockStatus(config);
    return [status, {
        remotetag: Random.string('upper', 128),
        localdata: {
            pk1: Random.guid(),
            pk2: Random.guid(),
            pk3: Random.guid(),
        }
    }];
});

