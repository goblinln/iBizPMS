
# **用户工时统计**(ACCOUNTTASKESTIMATE)

### 所有用户工时(AllAccountEstimate)<div id="AccountTaskestimate_AllAccountEstimate"></div>
```sql
SELECT T1.* FROM (SELECT
	t1.account,t1.`YEAR`,concat(t1.`YEAR`, t1.`MONTH`) as `MONTH`,
	sum(if(t1.`day` = '01',t1.consumed, 0)) as oneconsumed,
	sum(if(t1.`day` = '02',t1.consumed, 0)) as twoconsumed,
	sum(if(t1.`day` = '03',t1.consumed, 0)) as threeconsumed,
	sum(if(t1.`day` = '04',t1.consumed, 0)) as FOURONSUMED,
	sum(if(t1.`day` = '05',t1.consumed, 0)) as fiveconsumed,
	sum(if(t1.`day` = '06',t1.consumed, 0)) as sixconsumed,
	sum(if(t1.`day` = '07',t1.consumed, 0)) as sevenconsumed,
	sum(if(t1.`day` = '08',t1.consumed, 0)) as eightconsumed,
	sum(if(t1.`day` = '09',t1.consumed, 0)) as nineconsumed,
	sum(if(t1.`day` = '10',t1.consumed, 0)) as tenconsumed,
	sum(if(t1.`day` = '11',t1.consumed, 0)) as elevenconsumed,
	sum(if(t1.`day` = '12',t1.consumed, 0)) as twelveconsumed,
	sum(if(t1.`day` = '13',t1.consumed, 0)) as thirteenconsumed,
	sum(if(t1.`day` = '14',t1.consumed, 0)) as fourteenconsumed,
	sum(if(t1.`day` = '15',t1.consumed, 0)) as fifteenconsumed,
	sum(if(t1.`day` = '16',t1.consumed, 0)) as sixteenconsumed,
	sum(if(t1.`day` = '17',t1.consumed, 0)) as seventeenconsumed,
	sum(if(t1.`day` = '18',t1.consumed, 0)) as eighteenconsumed,
	sum(if(t1.`day` = '19',t1.consumed, 0)) as nineteenconsumed,
	sum(if(t1.`day` = '20',t1.consumed, 0)) as twentyconsumed,
	sum(if(t1.`day` = '21',t1.consumed, 0)) as twentyoneconsumed,
	sum(if(t1.`day` = '22',t1.consumed, 0)) as twentytwoconsumed,
	sum(if(t1.`day` = '23',t1.consumed, 0)) as twentythreeconsumed,
	sum(if(t1.`day` = '24',t1.consumed, 0)) as twentyfourconsumed,
	sum(if(t1.`day` = '25',t1.consumed, 0)) as twentyfiveconsumed,
	sum(if(t1.`day` = '26',t1.consumed, 0)) as twentysixconsumed,
	sum(if(t1.`day` = '27',t1.consumed, 0)) as twentysevenconsumed,
	sum(if(t1.`day` = '28',t1.consumed, 0)) as twentyeightconsumed,
	sum(if(t1.`day` = '29',t1.consumed, 0)) as twentynineconsumed,
	sum(if(t1.`day` = '30',t1.consumed, 0)) as thirtyconsumed,
	sum(if(t1.`day` = '31',t1.consumed, 0)) as thirtyoneconsumed,
	sum(if(t1.`day` = '01',t1.EVALUATIONTIME, 0)) as oneEVALUATIONTIME,
	sum(if(t1.`day` = '02',t1.EVALUATIONTIME, 0)) as twoEVALUATIONTIME,
	sum(if(t1.`day` = '03',t1.EVALUATIONTIME, 0)) as threeEVALUATIONTIME,
	sum(if(t1.`day` = '04',t1.EVALUATIONTIME, 0)) as fourEVALUATIONTIME,
	sum(if(t1.`day` = '05',t1.EVALUATIONTIME, 0)) as fiveEVALUATIONTIME,
	sum(if(t1.`day` = '06',t1.EVALUATIONTIME, 0)) as sixEVALUATIONTIME,
	sum(if(t1.`day` = '07',t1.EVALUATIONTIME, 0)) as sevenEVALUATIONTIME,
	sum(if(t1.`day` = '08',t1.EVALUATIONTIME, 0)) as eightEVALUATIONTIME,
	sum(if(t1.`day` = '09',t1.EVALUATIONTIME, 0)) as nineEVALUATIONTIME,
	sum(if(t1.`day` = '10',t1.EVALUATIONTIME, 0)) as tenEVALUATIONTIME,
	sum(if(t1.`day` = '11',t1.EVALUATIONTIME, 0)) as elevenEVALUATIONTIME,
	sum(if(t1.`day` = '12',t1.EVALUATIONTIME, 0)) as twelveEVALUATIONTIME,
	sum(if(t1.`day` = '13',t1.EVALUATIONTIME, 0)) as thirteenEVALUATIONTIME,
	sum(if(t1.`day` = '14',t1.EVALUATIONTIME, 0)) as fourteenEVALUATIONTIME,
	sum(if(t1.`day` = '15',t1.EVALUATIONTIME, 0)) as fifteenEVALUATIONTIME,
	sum(if(t1.`day` = '16',t1.EVALUATIONTIME, 0)) as sixteenEVALUATIONTIME,
	sum(if(t1.`day` = '17',t1.EVALUATIONTIME, 0)) as seventeenEVALUATIONTIME,
	sum(if(t1.`day` = '18',t1.EVALUATIONTIME, 0)) as eighteenEVALUATIONTIME,
	sum(if(t1.`day` = '19',t1.EVALUATIONTIME, 0)) as nineteenEVALUATIONTIME,
	sum(if(t1.`day` = '20',t1.EVALUATIONTIME, 0)) as twentyEVALUATIONTIME,
	sum(if(t1.`day` = '21',t1.EVALUATIONTIME, 0)) as twentyoneEVALUATIONTIME,
	sum(if(t1.`day` = '22',t1.EVALUATIONTIME, 0)) as twentytwoEVALUATIONTIME,
	sum(if(t1.`day` = '23',t1.EVALUATIONTIME, 0)) as twentythreeEVALUATIONTIME,
	sum(if(t1.`day` = '24',t1.EVALUATIONTIME, 0)) as twentyfourEVALUATIONTIME,
	sum(if(t1.`day` = '25',t1.EVALUATIONTIME, 0)) as twentyfiveEVALUATIONTIME,
	sum(if(t1.`day` = '26',t1.EVALUATIONTIME, 0)) as twentysixEVALUATIONTIME,
	sum(if(t1.`day` = '27',t1.EVALUATIONTIME, 0)) as twentysevenEVALUATIONTIME,
	sum(if(t1.`day` = '28',t1.EVALUATIONTIME, 0)) as twentyeightEVALUATIONTIME,
	sum(if(t1.`day` = '29',t1.EVALUATIONTIME, 0)) as twentynineEVALUATIONTIME,
	sum(if(t1.`day` = '30',t1.EVALUATIONTIME, 0)) as thirtyEVALUATIONTIME,
	sum(if(t1.`day` = '31',t1.EVALUATIONTIME, 0)) as thirtyoneEVALUATIONTIME,
	sum(if(t1.`day` = '01',t1.EVALUATIONCOST, 0)) as oneEVALUATIONCOST,
	sum(if(t1.`day` = '02',t1.EVALUATIONCOST, 0)) as twoEVALUATIONCOST,
	sum(if(t1.`day` = '03',t1.EVALUATIONCOST, 0)) as threeEVALUATIONCOST,
	sum(if(t1.`day` = '04',t1.EVALUATIONCOST, 0)) as fourEVALUATIONCOST,
	sum(if(t1.`day` = '05',t1.EVALUATIONCOST, 0)) as fiveEVALUATIONCOST,
	sum(if(t1.`day` = '06',t1.EVALUATIONCOST, 0)) as sixEVALUATIONCOST,
	sum(if(t1.`day` = '07',t1.EVALUATIONCOST, 0)) as sevenEVALUATIONCOST,
	sum(if(t1.`day` = '08',t1.EVALUATIONCOST, 0)) as eightEVALUATIONCOST,
	sum(if(t1.`day` = '09',t1.EVALUATIONCOST, 0)) as nineEVALUATIONCOST,
	sum(if(t1.`day` = '10',t1.EVALUATIONCOST, 0)) as tenEVALUATIONCOST,
	sum(if(t1.`day` = '11',t1.EVALUATIONCOST, 0)) as elevenEVALUATIONCOST,
	sum(if(t1.`day` = '12',t1.EVALUATIONCOST, 0)) as twelveEVALUATIONCOST,
	sum(if(t1.`day` = '13',t1.EVALUATIONCOST, 0)) as thirteenEVALUATIONCOST,
	sum(if(t1.`day` = '14',t1.EVALUATIONCOST, 0)) as fourteenEVALUATIONCOST,
	sum(if(t1.`day` = '15',t1.EVALUATIONCOST, 0)) as fifteenEVALUATIONCOST,
	sum(if(t1.`day` = '16',t1.EVALUATIONCOST, 0)) as sixteenEVALUATIONCOST,
	sum(if(t1.`day` = '17',t1.EVALUATIONCOST, 0)) as seventeenEVALUATIONCOST,
	sum(if(t1.`day` = '18',t1.EVALUATIONCOST, 0)) as eighteenEVALUATIONCOST,
	sum(if(t1.`day` = '19',t1.EVALUATIONCOST, 0)) as nineteenEVALUATIONCOST,
	sum(if(t1.`day` = '20',t1.EVALUATIONCOST, 0)) as twentyEVALUATIONCOST,
	sum(if(t1.`day` = '21',t1.EVALUATIONCOST, 0)) as twentyoneEVALUATIONCOST,
	sum(if(t1.`day` = '22',t1.EVALUATIONCOST, 0)) as twentytwoEVALUATIONCOST,
	sum(if(t1.`day` = '23',t1.EVALUATIONCOST, 0)) as twentythreeEVALUATIONCOST,
	sum(if(t1.`day` = '24',t1.EVALUATIONCOST, 0)) as twentyfourEVALUATIONCOST,
	sum(if(t1.`day` = '25',t1.EVALUATIONCOST, 0)) as twentyfiveEVALUATIONCOST,
	sum(if(t1.`day` = '26',t1.EVALUATIONCOST, 0)) as twentysixEVALUATIONCOST,
	sum(if(t1.`day` = '27',t1.EVALUATIONCOST, 0)) as twentysevenEVALUATIONCOST,
	sum(if(t1.`day` = '28',t1.EVALUATIONCOST, 0)) as twentyeightEVALUATIONCOST,
	sum(if(t1.`day` = '29',t1.EVALUATIONCOST, 0)) as twentynineEVALUATIONCOST,
	sum(if(t1.`day` = '30',t1.EVALUATIONCOST, 0)) as thirtyEVALUATIONCOST,
	sum(if(t1.`day` = '31',t1.EVALUATIONCOST, 0)) as thirtyoneEVALUATIONCOST,
	SUM(T1.consumed) AS consumed,SUM(T1.EVALUATIONCOST) AS EVALUATIONCOST, SUM(T1.INPUTCOST) AS INPUTCOST, SUM(T1.EVALUATIONTIME) AS EVALUATIONTIME
FROM
	(
SELECT YEAR
	( t1.date ) AS `year`,
	Right(100 + MONTH ( t1.date ), 2) AS `month`,
	Right(100 + day ( t1.date ), 2) AS `day`,
	t1.account,
	t1.date,
	IFNULL(ROUND( sum( t1.consumed ), 2 ),0) AS consumed,
        IFNULL(ROUND( sum( t1.EVALUATIONCOST ), 2 ),0) AS EVALUATIONCOST,
        IFNULL(ROUND( sum( t1.INPUTCOST ), 2 ),0) AS INPUTCOST,
        IFNULL(ROUND( sum( t1.EVALUATIONTIME ), 2 ),0) AS EVALUATIONTIME 
FROM
	`zt_taskestimate` t1
	LEFT JOIN `zt_task` t11 ON t1.`TASK` = t11.`ID`
WHERE
	t1.date <> '0000-00-00'  and (t11.deleted = '0' or t11.deleted is null)
GROUP BY
	t1.account,
	t1.date 
union
select 
YEAR( t1.date ) AS `year`,
	Right(100 + MONTH ( t1.date ), 2) AS `month`,
	Right(100 + day ( t1.date ), 2) AS `day`,
	t1.account,
	t1.date,
	ROUND( sum( t1.consumed ), 2 ) AS consumed,
	0.00 AS EVALUATIONCOST,
	0.00 AS INPUTCOST,
	0.00 AS EVALUATIONTIME
	from zt_todo t1 left join zt_project t11 on t11.id = t1.idvalue 
	where t1.type = 'custom' and t1.idvalue > 0
GROUP BY
	t1.account,
	t1.date 
	) t1 GROUP BY t1.account, t1.`YEAR`,t1.`MONTH`) T1
```
### 用户日工时统计(CurDaySum)<div id="AccountTaskestimate_CurDaySum"></div>
```sql
SELECT t1.* from (
SELECT YEAR
	( t1.date ) AS `year`,
	RIGHT ( 100 + MONTH ( t1.date ), 2 ) AS `month`,
	RIGHT ( 100 + DAY ( t1.date ), 2 ) AS `day`,
	CONCAT(year(t1.date),RIGHT ( 100 + MONTH ( t1.date ), 2 ),RIGHT ( 100 + DAY ( t1.date ), 2 )) as dayname ,
	t11.project,
	t21.`name` AS projectname,
	t1.account,
	t1.date,
	IFNULL( ROUND( sum( t1.consumed ), 2 ), 0 ) AS consumed,
	IFNULL( ROUND( sum( t1.EVALUATIONCOST ), 2 ), 0 ) AS EVALUATIONCOST,
	IFNULL( ROUND( sum( t1.INPUTCOST ), 2 ), 0 ) AS INPUTCOST,
	IFNULL( ROUND( sum( t1.EVALUATIONTIME ), 2 ), 0 ) AS EVALUATIONTIME 
FROM
	`zt_taskestimate` t1
	LEFT JOIN `zt_task` t11 ON t1.`TASK` = t11.`ID`
	LEFT JOIN `zt_project` t21 ON t11.`PROJECT` = t21.`ID` 
WHERE
	t1.date <> '0000-00-00' 
	and (t11.deleted = '0' or t11.deleted is null)
GROUP BY
	t11.project,
	t21.`name`,
	t1.account,
	t1.date UNION
SELECT YEAR
	( t1.date ) AS `year`,
	RIGHT ( 100 + MONTH ( t1.date ), 2 ) AS `month`,
	RIGHT ( 100 + DAY ( t1.date ), 2 ) AS `day`,
	CONCAT(year(t1.date),RIGHT ( 100 + MONTH ( t1.date ), 2 ),RIGHT ( 100 + DAY ( t1.date ), 2 )) as dayname ,
	t1.idvalue AS project,
	t11.`name` AS projectname,
	t1.account,
	t1.date,
	IFNULL( ROUND( sum( t1.consumed ), 2 ), 0 ) AS consumed,
	0.00 AS EVALUATIONCOST,
	0.00 AS INPUTCOST,
	0.00 AS EVALUATIONTIME 
FROM
	zt_todo t1
	LEFT JOIN zt_project t11 ON t11.id = t1.idvalue 
WHERE
	t1.type = 'custom' 
	AND t1.idvalue > 0 
GROUP BY
	t1.idvalue,
	t11.`name`,
	t1.account,
	t1.date )t1
WHERE t1.account = #{srf.datacontext.account} 
t1.dayname =  #{srf.datacontext.dayname} 

```
### 用户当日工时（项目）(CurDaySunByProject)<div id="AccountTaskestimate_CurDaySunByProject"></div>
```sql
	SELECT
		t1.* 
	FROM
		(
		SELECT YEAR
			( t1.date ) AS `year`,
			RIGHT ( 100 + MONTH ( t1.date ), 2 ) AS `month`,
			RIGHT ( 100 + DAY ( t1.date ), 2 ) AS `day`,
			CONCAT(				
				year(t1.date),'-',RIGHT ( 100 + MONTH ( t1.date ), 2 ),'-',RIGHT ( 100 + DAY ( t1.date ), 2 )
			) AS dayname,
			t11.project,
			t21.`name` AS projectname,
			t1.account,
			t1.date,
			IFNULL( ROUND( sum( t1.consumed ), 2 ), 0 ) AS consumed,
			IFNULL( ROUND( sum( t1.EVALUATIONCOST ), 2 ), 0 ) AS EVALUATIONCOST,
			IFNULL( ROUND( sum( t1.INPUTCOST ), 2 ), 0 ) AS INPUTCOST,
			IFNULL( ROUND( sum( t1.EVALUATIONTIME ), 2 ), 0 ) AS EVALUATIONTIME 
		FROM
			`zt_taskestimate` t1
			LEFT JOIN `zt_task` t11 ON t1.`TASK` = t11.`ID`
			LEFT JOIN `zt_project` t21 ON t11.`PROJECT` = t21.`ID` 
		WHERE
			t1.date <> '0000-00-00' 
			and (t11.deleted = '0' or t11.deleted is null)
		GROUP BY
			t11.project,
			t21.`name`,
			t1.account,
			t1.date UNION
		SELECT YEAR
			( t1.date ) AS `year`,
			RIGHT ( 100 + MONTH ( t1.date ), 2 ) AS `month`,
			RIGHT ( 100 + DAY ( t1.date ), 2 ) AS `day`,
			CONCAT(
year(t1.date),'-',RIGHT ( 100 + MONTH ( t1.date ), 2 ),'-',RIGHT ( 100 + DAY ( t1.date ), 2 ) 
			) AS dayname,
			t1.idvalue AS project,
			t11.`name` AS projectname,
			t1.account,
			t1.date,
			IFNULL( ROUND( sum( t1.consumed ), 2 ), 0 ) AS consumed,
			0.00 AS EVALUATIONCOST,
			0.00 AS INPUTCOST,
			0.00 AS EVALUATIONTIME 
		FROM
			zt_todo t1
			LEFT JOIN zt_project t11 ON t11.id = t1.idvalue 
		WHERE
			t1.type = 'custom' 
			AND t1.idvalue > 0 
		GROUP BY
			t1.idvalue,
			t11.`name`,
			t1.account,
			t1.date 
		) t1
WHERE t1.project = #{srf.datacontext.project } 
t1.dayname =  #{srf.datacontext.dayname} 

```
### 数据查询(DEFAULT)<div id="AccountTaskestimate_Default"></div>
```sql
SELECT T1.* FROM (SELECT
	t1.account,t1.`YEAR`,concat(t1.`YEAR`, t1.`MONTH`) as `MONTH`,
	sum(if(t1.`day` = '01',t1.consumed, 0)) as oneconsumed,
	sum(if(t1.`day` = '02',t1.consumed, 0)) as twoconsumed,
	sum(if(t1.`day` = '03',t1.consumed, 0)) as threeconsumed,
	sum(if(t1.`day` = '04',t1.consumed, 0)) as FOURONSUMED,
	sum(if(t1.`day` = '05',t1.consumed, 0)) as fiveconsumed,
	sum(if(t1.`day` = '06',t1.consumed, 0)) as sixconsumed,
	sum(if(t1.`day` = '07',t1.consumed, 0)) as sevenconsumed,
	sum(if(t1.`day` = '08',t1.consumed, 0)) as eightconsumed,
	sum(if(t1.`day` = '09',t1.consumed, 0)) as nineconsumed,
	sum(if(t1.`day` = '10',t1.consumed, 0)) as tenconsumed,
	sum(if(t1.`day` = '11',t1.consumed, 0)) as elevenconsumed,
	sum(if(t1.`day` = '12',t1.consumed, 0)) as twelveconsumed,
	sum(if(t1.`day` = '13',t1.consumed, 0)) as thirteenconsumed,
	sum(if(t1.`day` = '14',t1.consumed, 0)) as fourteenconsumed,
	sum(if(t1.`day` = '15',t1.consumed, 0)) as fifteenconsumed,
	sum(if(t1.`day` = '16',t1.consumed, 0)) as sixteenconsumed,
	sum(if(t1.`day` = '17',t1.consumed, 0)) as seventeenconsumed,
	sum(if(t1.`day` = '18',t1.consumed, 0)) as eighteenconsumed,
	sum(if(t1.`day` = '19',t1.consumed, 0)) as nineteenconsumed,
	sum(if(t1.`day` = '20',t1.consumed, 0)) as twentyconsumed,
	sum(if(t1.`day` = '21',t1.consumed, 0)) as twentyoneconsumed,
	sum(if(t1.`day` = '22',t1.consumed, 0)) as twentytwoconsumed,
	sum(if(t1.`day` = '23',t1.consumed, 0)) as twentythreeconsumed,
	sum(if(t1.`day` = '24',t1.consumed, 0)) as twentyfourconsumed,
	sum(if(t1.`day` = '25',t1.consumed, 0)) as twentyfiveconsumed,
	sum(if(t1.`day` = '26',t1.consumed, 0)) as twentysixconsumed,
	sum(if(t1.`day` = '27',t1.consumed, 0)) as twentysevenconsumed,
	sum(if(t1.`day` = '28',t1.consumed, 0)) as twentyeightconsumed,
	sum(if(t1.`day` = '29',t1.consumed, 0)) as twentynineconsumed,
	sum(if(t1.`day` = '30',t1.consumed, 0)) as thirtyconsumed,
	sum(if(t1.`day` = '31',t1.consumed, 0)) as thirtyoneconsumed,
	sum(if(t1.`day` = '01',t1.EVALUATIONTIME, 0)) as oneEVALUATIONTIME,
	sum(if(t1.`day` = '02',t1.EVALUATIONTIME, 0)) as twoEVALUATIONTIME,
	sum(if(t1.`day` = '03',t1.EVALUATIONTIME, 0)) as threeEVALUATIONTIME,
	sum(if(t1.`day` = '04',t1.EVALUATIONTIME, 0)) as fourEVALUATIONTIME,
	sum(if(t1.`day` = '05',t1.EVALUATIONTIME, 0)) as fiveEVALUATIONTIME,
	sum(if(t1.`day` = '06',t1.EVALUATIONTIME, 0)) as sixEVALUATIONTIME,
	sum(if(t1.`day` = '07',t1.EVALUATIONTIME, 0)) as sevenEVALUATIONTIME,
	sum(if(t1.`day` = '08',t1.EVALUATIONTIME, 0)) as eightEVALUATIONTIME,
	sum(if(t1.`day` = '09',t1.EVALUATIONTIME, 0)) as nineEVALUATIONTIME,
	sum(if(t1.`day` = '10',t1.EVALUATIONTIME, 0)) as tenEVALUATIONTIME,
	sum(if(t1.`day` = '11',t1.EVALUATIONTIME, 0)) as elevenEVALUATIONTIME,
	sum(if(t1.`day` = '12',t1.EVALUATIONTIME, 0)) as twelveEVALUATIONTIME,
	sum(if(t1.`day` = '13',t1.EVALUATIONTIME, 0)) as thirteenEVALUATIONTIME,
	sum(if(t1.`day` = '14',t1.EVALUATIONTIME, 0)) as fourteenEVALUATIONTIME,
	sum(if(t1.`day` = '15',t1.EVALUATIONTIME, 0)) as fifteenEVALUATIONTIME,
	sum(if(t1.`day` = '16',t1.EVALUATIONTIME, 0)) as sixteenEVALUATIONTIME,
	sum(if(t1.`day` = '17',t1.EVALUATIONTIME, 0)) as seventeenEVALUATIONTIME,
	sum(if(t1.`day` = '18',t1.EVALUATIONTIME, 0)) as eighteenEVALUATIONTIME,
	sum(if(t1.`day` = '19',t1.EVALUATIONTIME, 0)) as nineteenEVALUATIONTIME,
	sum(if(t1.`day` = '20',t1.EVALUATIONTIME, 0)) as twentyEVALUATIONTIME,
	sum(if(t1.`day` = '21',t1.EVALUATIONTIME, 0)) as twentyoneEVALUATIONTIME,
	sum(if(t1.`day` = '22',t1.EVALUATIONTIME, 0)) as twentytwoEVALUATIONTIME,
	sum(if(t1.`day` = '23',t1.EVALUATIONTIME, 0)) as twentythreeEVALUATIONTIME,
	sum(if(t1.`day` = '24',t1.EVALUATIONTIME, 0)) as twentyfourEVALUATIONTIME,
	sum(if(t1.`day` = '25',t1.EVALUATIONTIME, 0)) as twentyfiveEVALUATIONTIME,
	sum(if(t1.`day` = '26',t1.EVALUATIONTIME, 0)) as twentysixEVALUATIONTIME,
	sum(if(t1.`day` = '27',t1.EVALUATIONTIME, 0)) as twentysevenEVALUATIONTIME,
	sum(if(t1.`day` = '28',t1.EVALUATIONTIME, 0)) as twentyeightEVALUATIONTIME,
	sum(if(t1.`day` = '29',t1.EVALUATIONTIME, 0)) as twentynineEVALUATIONTIME,
	sum(if(t1.`day` = '30',t1.EVALUATIONTIME, 0)) as thirtyEVALUATIONTIME,
	sum(if(t1.`day` = '31',t1.EVALUATIONTIME, 0)) as thirtyoneEVALUATIONTIME,
	sum(if(t1.`day` = '01',t1.EVALUATIONCOST, 0)) as oneEVALUATIONCOST,
	sum(if(t1.`day` = '02',t1.EVALUATIONCOST, 0)) as twoEVALUATIONCOST,
	sum(if(t1.`day` = '03',t1.EVALUATIONCOST, 0)) as threeEVALUATIONCOST,
	sum(if(t1.`day` = '04',t1.EVALUATIONCOST, 0)) as fourEVALUATIONCOST,
	sum(if(t1.`day` = '05',t1.EVALUATIONCOST, 0)) as fiveEVALUATIONCOST,
	sum(if(t1.`day` = '06',t1.EVALUATIONCOST, 0)) as sixEVALUATIONCOST,
	sum(if(t1.`day` = '07',t1.EVALUATIONCOST, 0)) as sevenEVALUATIONCOST,
	sum(if(t1.`day` = '08',t1.EVALUATIONCOST, 0)) as eightEVALUATIONCOST,
	sum(if(t1.`day` = '09',t1.EVALUATIONCOST, 0)) as nineEVALUATIONCOST,
	sum(if(t1.`day` = '10',t1.EVALUATIONCOST, 0)) as tenEVALUATIONCOST,
	sum(if(t1.`day` = '11',t1.EVALUATIONCOST, 0)) as elevenEVALUATIONCOST,
	sum(if(t1.`day` = '12',t1.EVALUATIONCOST, 0)) as twelveEVALUATIONCOST,
	sum(if(t1.`day` = '13',t1.EVALUATIONCOST, 0)) as thirteenEVALUATIONCOST,
	sum(if(t1.`day` = '14',t1.EVALUATIONCOST, 0)) as fourteenEVALUATIONCOST,
	sum(if(t1.`day` = '15',t1.EVALUATIONCOST, 0)) as fifteenEVALUATIONCOST,
	sum(if(t1.`day` = '16',t1.EVALUATIONCOST, 0)) as sixteenEVALUATIONCOST,
	sum(if(t1.`day` = '17',t1.EVALUATIONCOST, 0)) as seventeenEVALUATIONCOST,
	sum(if(t1.`day` = '18',t1.EVALUATIONCOST, 0)) as eighteenEVALUATIONCOST,
	sum(if(t1.`day` = '19',t1.EVALUATIONCOST, 0)) as nineteenEVALUATIONCOST,
	sum(if(t1.`day` = '20',t1.EVALUATIONCOST, 0)) as twentyEVALUATIONCOST,
	sum(if(t1.`day` = '21',t1.EVALUATIONCOST, 0)) as twentyoneEVALUATIONCOST,
	sum(if(t1.`day` = '22',t1.EVALUATIONCOST, 0)) as twentytwoEVALUATIONCOST,
	sum(if(t1.`day` = '23',t1.EVALUATIONCOST, 0)) as twentythreeEVALUATIONCOST,
	sum(if(t1.`day` = '24',t1.EVALUATIONCOST, 0)) as twentyfourEVALUATIONCOST,
	sum(if(t1.`day` = '25',t1.EVALUATIONCOST, 0)) as twentyfiveEVALUATIONCOST,
	sum(if(t1.`day` = '26',t1.EVALUATIONCOST, 0)) as twentysixEVALUATIONCOST,
	sum(if(t1.`day` = '27',t1.EVALUATIONCOST, 0)) as twentysevenEVALUATIONCOST,
	sum(if(t1.`day` = '28',t1.EVALUATIONCOST, 0)) as twentyeightEVALUATIONCOST,
	sum(if(t1.`day` = '29',t1.EVALUATIONCOST, 0)) as twentynineEVALUATIONCOST,
	sum(if(t1.`day` = '30',t1.EVALUATIONCOST, 0)) as thirtyEVALUATIONCOST,
	sum(if(t1.`day` = '31',t1.EVALUATIONCOST, 0)) as thirtyoneEVALUATIONCOST,
	SUM(T1.consumed) AS consumed,SUM(T1.EVALUATIONCOST) AS EVALUATIONCOST, SUM(T1.INPUTCOST) AS INPUTCOST, SUM(T1.EVALUATIONTIME) AS EVALUATIONTIME
FROM
	(
SELECT YEAR
	( t1.date ) AS `year`,
	Right(100 + MONTH ( t1.date ), 2) AS `month`,
	Right(100 + day ( t1.date ), 2) AS `day`,
	t1.account,
	t1.date,
	ROUND( sum( t1.consumed ), 2 ) AS consumed,
	ROUND( sum( t1.EVALUATIONCOST ), 2 ) AS EVALUATIONCOST,
	ROUND( sum( t1.INPUTCOST ), 2 ) AS INPUTCOST,
	ROUND( sum( t1.EVALUATIONTIME ), 2 ) AS EVALUATIONTIME
FROM
	`zt_taskestimate` t1
	LEFT JOIN `zt_task` t11 ON t1.`TASK` = t11.`ID`
WHERE
	t1.date <> '0000-00-00'  and (t11.deleted = '0' or t11.deleted is null)
GROUP BY
	t1.account,
	t1.date 
union
select 
YEAR( t1.date ) AS `year`,
	Right(100 + MONTH ( t1.date ), 2) AS `month`,
	Right(100 + day ( t1.date ), 2) AS `day`,
	t1.account,
	t1.date,
	ROUND( sum( t1.consumed ), 2 ) AS consumed,
	0.00 AS EVALUATIONCOST,
	0.00 AS INPUTCOST,
	0.00 AS EVALUATIONTIME
	from zt_todo t1 left join zt_project t11 on t11.id = t1.idvalue 
	where t1.type = 'custom' and t1.idvalue > 0
GROUP BY
	t1.account,
	t1.date 
	) t1 GROUP BY t1.account, t1.`YEAR`,t1.`MONTH`) T1
WHERE t1.account =#{srf.webcontext.account} 

```
### 我的工时统计(MyDaySum)<div id="AccountTaskestimate_MyDaySum"></div>
```sql
SELECT
	t1.* 
FROM
	(
	SELECT YEAR
		( t1.date ) AS `year`,
		RIGHT ( 100 + MONTH ( t1.date ), 2 ) AS `month`,
		RIGHT ( 100 + DAY ( t1.date ), 2 ) AS `day`,
		CONCAT(
			YEAR ( t1.date ),
			RIGHT ('月',1),
			RIGHT ( 100 + MONTH ( t1.date ), 2 ),
			RIGHT ('月',1),
			RIGHT ( 100 + DAY ( t1.date ), 2 ) ,
			RIGHT ('日',1)
		) AS dayname,
		t11.project,
		t21.`name` AS projectname,
		t1.account,
		t1.date,
		IFNULL( ROUND( sum( t1.consumed ), 2 ), 0 ) AS consumed,
		IFNULL( ROUND( sum( t1.EVALUATIONCOST ), 2 ), 0 ) AS EVALUATIONCOST,
		IFNULL( ROUND( sum( t1.INPUTCOST ), 2 ), 0 ) AS INPUTCOST,
		IFNULL( ROUND( sum( t1.EVALUATIONTIME ), 2 ), 0 ) AS EVALUATIONTIME 
	FROM
		`zt_taskestimate` t1
		LEFT JOIN `zt_task` t11 ON t1.`TASK` = t11.`ID`
		LEFT JOIN `zt_project` t21 ON t11.`PROJECT` = t21.`ID` 
	WHERE
		t1.date <> '0000-00-00' 
		and (t11.deleted = '0' or t11.deleted is null)
	GROUP BY
		t11.project,
		t21.`name`,
		t1.account,
		t1.date UNION
	SELECT YEAR
		( t1.date ) AS `year`,
		RIGHT ( 100 + MONTH ( t1.date ), 2 ) AS `month`,
		RIGHT ( 100 + DAY ( t1.date ), 2 ) AS `day`,
		CONCAT(

			YEAR ( t1.date ),
			RIGHT ('月',1),
			RIGHT ( 100 + MONTH ( t1.date ), 2 ),
			RIGHT ('月',1),
			RIGHT ( 100 + DAY ( t1.date ), 2 ) ,
			RIGHT ('日',1)
		) AS dayname,
		t1.idvalue AS project,
		t11.`name` AS projectname,
		t1.account,
		t1.date,
		IFNULL( ROUND( sum( t1.consumed ), 2 ), 0 ) AS consumed,
		0.00 AS EVALUATIONCOST,
		0.00 AS INPUTCOST,
		0.00 AS EVALUATIONTIME 
	FROM
		zt_todo t1
		LEFT JOIN zt_project t11 ON t11.id = t1.idvalue 
	WHERE
		t1.type = 'custom' 
		AND t1.idvalue > 0 
	GROUP BY
		t1.idvalue,
		t11.`name`,
		t1.account,
	t1.date 
	) t1
WHERE t1.account =  #{srf.sessioncontext.srfloginname} 
t1.dayname =  #{srf.datacontext.dayname} 

```
### 默认（全部数据）(VIEW)<div id="AccountTaskestimate_View"></div>
```sql
SELECT
t1.`DATE`,
t1.`EVALUATIONCOST`,
t1.`INPUTCOST`
FROM `zt_taskestimate` t1 

```

# **系统日志**(ZT_ACTION)

### 项目立项变更线路操作历史记录(BianGengLineHistory)<div id="Action_BianGengLineHistory"></div>
```sql
SELECT
t1.`ACTION`,
(case when t1.objectType in ('bug','story','release') and t1.action in ('changestatus','resolved','closed', 'reviewed') and t1.extra <> '' then CONCAT_WS('_',t1.objectType,t1.action,t1.extra) else '' end) AS `ACTIONMANNER`,
t1.`ACTOR`,
t1.`COMMENT`,
t1.`DATE`,
DATE_FORMAT(t1.date,'%m月%d日 %H:%i') AS `DATE1`,
t1.`ID`,
0 AS `ISACTORSS`,
t1.`comment` AS `LASTCOMMENT`,
(case when DATE_FORMAT(t1.date,'%Y-%m') = DATE_FORMAT(DATE_ADD(now(),INTERVAL -1 MONTH),'%Y-%m') then '1' end) AS `LASTMONTH`,
(case when YEARWEEK(DATE_ADD(now(),INTERVAL -1 WEEK)) = YEARWEEK(t1.date) then '1' end) AS `LASTWEEK`,
t1.`OBJECTID`,
t1.`OBJECTTYPE`,
t1.`PRODUCT`,
t1.`PROJECT`,
t1.`READ`,
t1.id AS `SRFKEY`,
(case when DATE_FORMAT(t1.date,'%Y-%m') = DATE_FORMAT(now(),'%Y-%m') then '1' end) AS `THISMONTH`,
(case when YEARWEEK(now()) = YEARWEEK(t1.date) then '1' end) AS `THISWEEK`,
(case when DATE_FORMAT(t1.date,'%Y-%m-%d') = DATE_FORMAT(now(),'%Y-%m-%d') then '1' end) AS `TODAY`,
(case when DATE_FORMAT(t1.date,'%Y-%m-%d') = DATE_FORMAT(DATE_ADD(now(),INTERVAL -1 DAY),'%Y-%m-%d') then '1' end) AS `YESTERDAY`
FROM `zt_action` t1 

```
### DEFAULT(DEFAULT)<div id="Action_Default"></div>
```sql
SELECT
t1.`ACTION`,
(case when t1.objectType in ('bug','story','release') and t1.action in ('changestatus','resolved','closed', 'reviewed') and t1.extra <> '' then CONCAT_WS('_',t1.objectType,t1.action,t1.extra) else '' end) AS `ACTIONMANNER`,
t1.ACTOR AS `ACTOR`,
t1.`COMMENT`,
t1.`DATE`,
DATE_FORMAT(t1.date,'%m月%d日 %H:%i') as `date1`,
t1.`ID`,
t1.id as srfkey,
0 AS `ISACTORSS`,
t1.`comment` AS `LASTCOMMENT`,
t1.`OBJECTID`,
t1.`OBJECTTYPE`,
t1.`PRODUCT`,
t1.`PROJECT`,
t1.`READ`
FROM `zt_action` t1
```
### 动态(根据类型过滤)(MobType)<div id="Action_MobType"></div>
```sql
SELECT t1.`ACTION`, t1.`actor` as actor, t1.`DATE`, t1.`ID`, t1.`OBJECTID`, t1.id as srfkey,(case when t1.`OBJECTTYPE` in ('daily','weekly','monthly') then CONCAT_WS('','ibz',t1.objectType) else t1.objectType end ) as OBJECTTYPE, t1.`PRODUCT`, t1.`PROJECT`, t1.`READ`, t1.`comment`, t1.extra, case when t1.objectType in ('bug','story','release') and t1.action in ('changestatus','resolved','closed', 'reviewed') and t1.extra <> '' then CONCAT_WS('_',t1.objectType,t1.action,t1.extra) else '' end as ActionManner FROM `zt_action` t1  WHERE ( t1.`OBJECTID` = ${srfdatacontext('srfparentkey','{"defname":"OBJECTID","dename":"ZT_ACTION"}')} AND t1.`OBJECTTYPE` = ${srfdatacontext('objecttype','{"defname":"OBJECTTYPE","dename":"ZT_ACTION"}')} )
```
### 项目动态(我的)(MyTrends)<div id="Action_MyTrends"></div>
```sql
SELECT
	t1.* 
FROM
	(
SELECT
	t1.`ACTION`,
	t1.actor AS actor,
	t1.`DATE`,
	t1.`ID`,
	t1.`OBJECTID`,
	t1.`OBJECTTYPE`,
	t1.`PRODUCT`,
	t1.`PROJECT`,
	t1.`READ`,
	t1.extra,
CASE
	
	WHEN t1.objectType IN ( 'bug', 'story', 'release' ) 
	AND t1.action IN ( 'changestatus', 'resolved', 'closed', 'reviewed' ) 
	AND t1.extra <> '' THEN
		CONCAT_WS( '_', t1.objectType, t1.action, t1.extra ) ELSE '' 
	END AS ActionManner,
	( CASE WHEN DATE_FORMAT( t1.date, '%Y-%m-%d' ) = DATE_FORMAT( now( ), '%Y-%m-%d' ) THEN '1' END ) AS Today,
	t1.id AS srfkey,
	(
	CASE
			
			WHEN DATE_FORMAT( t1.date, '%Y-%m-%d' ) = DATE_FORMAT( DATE_ADD( now( ), INTERVAL - 1 DAY ), '%Y-%m-%d' ) THEN
			'1' 
		END 
		) AS yesterday,
		( CASE WHEN YEARWEEK( now( ) ) = YEARWEEK( t1.date ) THEN '1' END ) AS thisweek,
		(
		CASE
				
				WHEN YEARWEEK( DATE_ADD( now( ), INTERVAL - 1 WEEK ) ) = YEARWEEK( t1.date ) THEN
				'1' 
			END 
			) AS lastweek,
			( CASE WHEN DATE_FORMAT( t1.date, '%Y-%m' ) = DATE_FORMAT( now( ), '%Y-%m' ) THEN '1' END ) AS thismonth,
			(
			CASE
					
					WHEN DATE_FORMAT( t1.date, '%Y-%m' ) = DATE_FORMAT( DATE_ADD( now( ), INTERVAL - 1 MONTH ), '%Y-%m' ) THEN
					'1' 
				END 
				) AS lastmonth 
			FROM
				`zt_action` t1
) t1
WHERE t1.actor = #{srf.sessioncontext.srfloginname} 

```
### 产品动态(产品相关所有)(ProductTrends)<div id="Action_ProductTrends"></div>
```sql
select t1.* from (SELECT
t1.`ACTION`,
t1.actor as actor,
t1.`DATE`,
t1.`ID`,
t1.id as srfkey,
t1.`OBJECTID`,
t1.`OBJECTTYPE`,
t1.`PRODUCT`,
t1.`PROJECT`,
t1.`READ`,
t1.extra,
case when t1.objectType in ('bug','story','release') and t1.action in ('changestatus','resolved','closed', 'reviewed') and t1.extra <> '' then CONCAT_WS('_',t1.objectType,t1.action,t1.extra) else '' end as ActionManner,
(case when DATE_FORMAT(t1.date,'%Y-%m-%d') = DATE_FORMAT(now(),'%Y-%m-%d') then '1' end) as Today,
(case when DATE_FORMAT(t1.date,'%Y-%m-%d') = DATE_FORMAT(DATE_ADD(now(),INTERVAL -1 DAY),'%Y-%m-%d') then '1' end) as yesterday,
(case when YEARWEEK(now()) = YEARWEEK(t1.date) then '1' end) as thisweek,
(case when YEARWEEK(DATE_ADD(now(),INTERVAL -1 WEEK)) = YEARWEEK(t1.date) then '1' end) as lastweek,
(case when DATE_FORMAT(t1.date,'%Y-%m') = DATE_FORMAT(now(),'%Y-%m') then '1' end) as thismonth,
(case when DATE_FORMAT(t1.date,'%Y-%m') = DATE_FORMAT(DATE_ADD(now(),INTERVAL -1 MONTH),'%Y-%m') then '1' end) as lastmonth
FROM `zt_action` t1 
) t1

WHERE ( t1.`PRODUCT` LIKE  CONCAT('%,',${srfdatacontext('srfparentkey','{"defname":"PRODUCT","dename":"ZT_ACTION"}')},',%') ) 

```
### 项目动态(项目相关所有)(ProjectTrends)<div id="Action_ProjectTrends"></div>
```sql
SELECT
	t1.* 
FROM
	(
SELECT
	t1.`ACTION`,
	t1.actor AS actor,
	t1.`DATE`,
	t1.`ID`,
	t1.`OBJECTID`,
	t1.`OBJECTTYPE`,
	t1.`PRODUCT`,
	t1.`PROJECT`,
	t1.`READ`,
	t1.extra,
CASE
	
	WHEN t1.objectType IN ( 'bug', 'story', 'release' ) 
	AND t1.action IN ( 'changestatus', 'resolved', 'closed', 'reviewed' ) 
	AND t1.extra <> '' THEN
		CONCAT_WS( '_', t1.objectType, t1.action, t1.extra ) ELSE '' 
	END AS ActionManner,
	( CASE WHEN DATE_FORMAT( t1.date, '%Y-%m-%d' ) = DATE_FORMAT( now( ), '%Y-%m-%d' ) THEN '1' END ) AS Today,
	t1.id AS srfkey,
	(
	CASE
			
			WHEN DATE_FORMAT( t1.date, '%Y-%m-%d' ) = DATE_FORMAT( DATE_ADD( now( ), INTERVAL - 1 DAY ), '%Y-%m-%d' ) THEN
			'1' 
		END 
		) AS yesterday,
		( CASE WHEN YEARWEEK( now( ) ) = YEARWEEK( t1.date ) THEN '1' END ) AS thisweek,
		(
		CASE
				
				WHEN YEARWEEK( DATE_ADD( now( ), INTERVAL - 1 WEEK ) ) = YEARWEEK( t1.date ) THEN
				'1' 
			END 
			) AS lastweek,
			( CASE WHEN DATE_FORMAT( t1.date, '%Y-%m' ) = DATE_FORMAT( now( ), '%Y-%m' ) THEN '1' END ) AS thismonth,
			(
			CASE
					
					WHEN DATE_FORMAT( t1.date, '%Y-%m' ) = DATE_FORMAT( DATE_ADD( now( ), INTERVAL - 1 MONTH ), '%Y-%m' ) THEN
					'1' 
				END 
				) AS lastmonth 
			FROM
				`zt_action` t1
) t1
WHERE ( t1.`PROJECT` = ${srfdatacontext('srfparentkey','{"defname":"PROJECT","dename":"ZT_ACTION"}')} ) 

```
### 查询用户使用年(QueryUserYEAR)<div id="Action_QueryUserYEAR"></div>
```sql
select DISTINCT DATE_FORMAT(t1.date,'%Y') as date1 from zt_action t1
WHERE  t1.actor = #{srf.sessioncontext.srfloginname} 

```
### 动态(根据类型过滤)(Type)<div id="Action_Type"></div>
```sql
SELECT
t1.`ACTION`,
t1.`actor` as actor,
t1.`DATE`,
t1.`ID`,
t1.id as srfkey,
t1.`OBJECTID`,
(case when t1.`OBJECTTYPE` in ('daily','weekly','monthly') then CONCAT_WS('','ibz',t1.objectType) else t1.objectType end ) as OBJECTTYPE,
t1.`PRODUCT`,
t1.`PROJECT`,
t1.`READ`,
t1.`comment`,
t1.extra,
(case when t1.actor =  #{srf.sessioncontext.srfloginname} then 1 else 0 end) as ISACTORSS,
case when t1.objectType in ('bug','story','release') and t1.action in ('changestatus','resolved','closed', 'reviewed') and t1.extra <> '' then CONCAT_WS('_',t1.objectType,t1.action,t1.extra) else '' end as ActionManner
FROM `zt_action` t1 
WHERE 
( t1.`OBJECTID` = ${srfdatacontext('srfparentkey','{"defname":"OBJECTID","dename":"ZT_ACTION"}')}  AND  t1.`OBJECTTYPE` =  ${srfdatacontext('objecttype','{"defname":"OBJECTTYPE","dename":"ZT_ACTION"}')} )
```
### 默认（全部数据）(VIEW)<div id="Action_View"></div>
```sql
SELECT
t1.`ACTION`,
(case when t1.objectType in ('bug','story','release') and t1.action in ('changestatus','resolved','closed', 'reviewed') and t1.extra <> '' then CONCAT_WS('_',t1.objectType,t1.action,t1.extra) else '' end) AS `ACTIONMANNER`,
t1.`ACTOR`,
t1.`COMMENT`,
t1.`DATE`,
DATE_FORMAT(t1.date,'%m月%d日 %H:%i') AS `DATE1`,
t1.`EXTRA`,
t1.`ID`,
0 AS `ISACTORSS`,
t1.`comment` AS `LASTCOMMENT`,
(case when DATE_FORMAT(t1.date,'%Y-%m') = DATE_FORMAT(DATE_ADD(now(),INTERVAL -1 MONTH),'%Y-%m') then '1' end) AS `LASTMONTH`,
(case when YEARWEEK(DATE_ADD(now(),INTERVAL -1 WEEK)) = YEARWEEK(t1.date) then '1' end) AS `LASTWEEK`,
t1.`OBJECTID`,
t1.`OBJECTTYPE`,
t1.`PRODUCT`,
t1.`PROJECT`,
t1.`READ`,
t1.id AS `SRFKEY`,
(case when DATE_FORMAT(t1.date,'%Y-%m') = DATE_FORMAT(now(),'%Y-%m') then '1' end) AS `THISMONTH`,
(case when YEARWEEK(now()) = YEARWEEK(t1.date) then '1' end) AS `THISWEEK`,
(case when DATE_FORMAT(t1.date,'%Y-%m-%d') = DATE_FORMAT(now(),'%Y-%m-%d') then '1' end) AS `TODAY`,
(case when DATE_FORMAT(t1.date,'%Y-%m-%d') = DATE_FORMAT(DATE_ADD(now(),INTERVAL -1 DAY),'%Y-%m-%d') then '1' end) AS `YESTERDAY`
FROM `zt_action` t1 

```

# **block**(ZT_BLOCK)

### DEFAULT(DEFAULT)<div id="Block_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`BLOCK`,
t1.`GRID`,
t1.`HEIGHT`,
t1.`HIDDEN`,
t1.`ID`,
t1.`MODULE`,
t1.`ORDER`,
t1.`SOURCE`,
t1.`TITLE`
FROM `zt_block` t1 

```
### 默认（全部数据）(VIEW)<div id="Block_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`BLOCK`,
t1.`GRID`,
t1.`HEIGHT`,
t1.`HIDDEN`,
t1.`ID`,
t1.`MODULE`,
t1.`ORDER`,
t1.`PARAMS`,
t1.`SOURCE`,
t1.`TITLE`
FROM `zt_block` t1 

```

# **产品的分支和平台信息**(ZT_BRANCH)

### CurProduct(CurProduct)<div id="Branch_CurProduct"></div>
```sql
select t1.* from (select '0' as `DELETED`,0 as `ID`,'所有平台' AS `NAME`, 0 as `order`, (case when #{srf.webcontext.product} is not null then #{srf.webcontext.product} else #{srf.webcontext.products} end  ) as product from dual union all SELECT t1.`DELETED`, t1.`ID`, t1.`NAME`, t1.`ORDER`, t1.`PRODUCT` FROM `zt_branch` t1) t1
WHERE t1.DELETED = '0' 
( ( t1.`PRODUCT` = ${srfwebcontext('product','{"defname":"PRODUCT","dename":"ZT_BRANCH"}')}  OR  t1.`PRODUCT` = ${srfdatacontext('product','{"defname":"PRODUCT","dename":"ZT_BRANCH"}')}  OR  t1.`PRODUCT` = ${srfdatacontext('products','{"defname":"PRODUCT","dename":"ZT_BRANCH"}')}  or t1.`product` is null) ) 

```
### DEFAULT(DEFAULT)<div id="Branch_Default"></div>
```sql
SELECT
t1.`DELETED`,
t1.`ID`,
t1.`NAME`,
t1.`ORDER`,
t1.`PRODUCT`
FROM `zt_branch` t1 

WHERE t1.DELETED = '0' 

```
### DefaultBranch(DefaultBranch)<div id="Branch_DefaultBranch"></div>
```sql
SELECT
'0' AS `DELETED`,
0 AS `ID`,
'所有平台' AS`NAME`,
0 AS `ORDER`,
t2.`id` AS `PRODUCT`
FROM `zt_branch` t1 RIGHT JOIN `zt_product` t2 ON t1.product=t2.id
WHERE
( ( t2.`ID` = ${srfwebcontext('product','{"defname":"PRODUCT","dename":"ZT_BRANCH"}')}  OR  t2.`ID` = ${srfdatacontext('product','{"defname":"PRODUCT","dename":"ZT_BRANCH"}')}  OR  t2.`ID` = ${srfdatacontext('products','{"defname":"PRODUCT","dename":"ZT_BRANCH"}')} ) )
```
### 默认（全部数据）(VIEW)<div id="Branch_View"></div>
```sql
SELECT
t1.`DELETED`,
t1.`ID`,
t1.`NAME`,
t1.`ORDER`,
t1.`PRODUCT`
FROM `zt_branch` t1 

WHERE t1.DELETED = '0' 

```

# **Bug**(ZT_BUG)

### 指派给我Bug(AssignedToMyBug)<div id="Bug_AssignedToMyBug"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'bug' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
t1.`MODULE`,
t51.`NAME` AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root ) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) as overduebugs 
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID
WHERE t1.DELETED = '0' 
( t1.`ASSIGNEDTO` =  ${srfsessioncontext('srfloginname','{"defname":"ASSIGNEDTO","dename":"ZT_BUG"}')} ) 

```
### 指派给我Bug（PC）(AssignedToMyBugPc)<div id="Bug_AssignedToMyBugPc"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'bug' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
t1.`MODULE`,
t51.`NAME` AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root ) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) as overduebugs 
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID
WHERE t1.DELETED = '0' 
( t1.`ASSIGNEDTO` =  ${srfsessioncontext('srfloginname','{"defname":"ASSIGNEDTO","dename":"ZT_BUG"}')} ) 
t1.deadline is not null and t1.deadline <> '0000-00-00' 
t1.assigneddate is not null and t1.assigneddate <> '0000-00-00 00:00:00' 

```
### 版本关联Bug（已解决）(BuildBugs)<div id="Bug_BuildBugs"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t1.`BROWSER`,
t1.`CASE`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'bug' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
t1.`MODULE`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) as overduebugs 
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
WHERE t1.`DELETED` ='0' AND FIND_IN_SET ( t1.ID, (SELECT BUGS FROM ZT_BUILD WHERE ID= ${srfdatacontext('srfparentkey','{"defname":"ID","dename":"ZT_BUILD"}')}))
```
### 版本可关联的已解决的Bugs集合(buildLinkResolvedBugs)<div id="Bug_BuildLinkResolvedBugs"></div>
```sql
SELECT
	t1.`ACTIVATEDCOUNT`,
	t1.`ACTIVATEDDATE`,
	t1.`ASSIGNEDDATE`,
	t1.`ASSIGNEDTO`,
	t1.`BRANCH`,
	t1.`BROWSER`,
	t1.`CASE`,
	t1.`CASEVERSION`,
	t1.`CLOSEDBY`,
	t1.`CLOSEDDATE`,
	t1.`COLOR`,
	t1.`CONFIRMED`,
	t1.`DEADLINE`,
	t1.`DELETED`,
	t1.`DUPLICATEBUG`,
	t1.`ENTRY`,
	t1.`FOUND`,
	t1.`HARDWARE`,
	t1.`ID`,
	0 AS `ISFAVORITES`,
	t1.`KEYWORDS`,
	t1.`LASTEDITEDBY`,
	t1.`LASTEDITEDDATE`,
	t1.`LINES`,
	t1.`LINKBUG`,
	t1.`MAILTO`,
	t1.`MODULE`,
	t1.`OPENEDBUILD`,
	t1.`OPENEDBY`,
	t1.`OPENEDDATE`,
	t1.`OS`,
	t1.`PLAN`,
	t1.`PRI`,
	t1.`PRODUCT`,
	t1.`PROJECT`,
	t1.`REPO`,
	t1.`REPOTYPE`,
	t1.`RESOLUTION`,
	t1.`RESOLVEDBUILD`,
(case when t1.`RESOLVEDBY` is null or t1.`RESOLVEDBY` = '' then  #{srf.sessioncontext.srfloginname} else t1.`RESOLVEDBY` end)	as `RESOLVEDBY`,
	t1.`RESOLVEDDATE`,
	t1.`RESULT`,
	t1.`SEVERITY`,
	t1.`STATUS`,
	t1.`STORY`,
	t1.`STORYVERSION`,
	t1.`SUBSTATUS`,
	t1.`TASK`,
	t1.`TESTTASK`,
	t1.`TITLE`,
	t1.`TOSTORY`,
	t1.`TOTASK`,
	t1.`TYPE`,
	t1.`V1`,
	case when t1.`status` = 'resolved' then '10' else '20' end as V2,
	( CASE WHEN t1.DEADLINE = '0000-00-00' THEN 0 ELSE datediff( t1.deadline, now( ) ) END ) AS overduebugs 
FROM
	`zt_bug` t1
WHERE t1.DELETED = '0' 
t1.product = ( SELECT product FROM zt_build WHERE project =  (select ttt.project from zt_build ttt where ttt.id = #{srf.datacontext.project})
		AND id = #{srf.datacontext.build} ) 
	AND t1.project = (select ttt.project from zt_build ttt where ttt.id = #{srf.datacontext.project}) 
	AND t1.`status` <> 'closed' 
	AND t1.id NOT IN ( SELECT t3.id FROM zt_bug t3, zt_build t4  WHERE
		t4.id = #{srf.datacontext.build} AND FIND_IN_SET( t3.id, t4.bugs ) ) 

```
### 版本关联Bug（已解决）(BuildOpenBugs)<div id="Bug_BuildOpenBugs"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t1.`BROWSER`,
t1.`CASE`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'bug' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
t1.`MODULE`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t1.`PROJECT`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) as overduebugs 
FROM `zt_bug` t1 
	where 
	t1.DELETED = '0'  
	AND
FIND_IN_SET(
 ${srfdatacontext('srfparentkey','{"defname":"PROJECT","dename":"ZT_BUILD"}')},(SELECT OPENEDBUILD FROM `zt_bug` WHERE ID=t1.ID))
```
### Build产生的Bug(BuildProduceBug)<div id="Bug_BuildProduceBug"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t71.`TITLE` AS `CASENAME`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
(case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` ='active' and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`,
( CASE WHEN t1.deadline IS NULL  OR t1.deadline = '0000-00-00'  OR t1.deadline = '1970-01-01' THEN ''  WHEN t1.`status` = 'resolved'  AND t1.deadline < DATE_FORMAT( t1.resolvedDate, '%y-%m-%d' ) THEN CONCAT_WS( '', '延期', TIMESTAMPDIFF( DAY, t1.deadline, t1.resolvedDate ), '天' ) ELSE ''  END ) AS `DELAYRESOLVE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
'' AS `MAILTOPK`,
t1.`MODULE`,
t51.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) AS `OVERDUEBUGS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID 
LEFT JOIN zt_case t71 ON t1.`CASE` = t71.ID 

WHERE t1.DELETED = '0' 
( FIND_IN_SET((select t.build from zt_testtask t inner join zt_testreport tt on t.id = tt.tasks where tt.id = #{srf.datacontext.srfparentkey} 
),t1.OPENEDBUILD)  and t1.openedDate > (select tt.`begin` from  zt_testreport tt  where tt.id = #{srf.datacontext.srfparentkey} 
) and t1.openedDate < (select tt.`end` from  zt_testreport tt  where tt.id = #{srf.datacontext.srfparentkey} 
) ) 

```
### Build产生的Bug-模块分布(BuildProduceBugModule)<div id="Bug_BuildProduceBugModule"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
t1.`MODULE`,
CONCAT(
	'/',
 case when	(
SELECT
	GROUP_CONCAT( tt.NAME SEPARATOR '/' ) 
FROM
	zt_module tt 
WHERE
	FIND_IN_SET( tt.id, t51.path ) 
	AND tt.type = 'story' 
GROUP BY
	tt.root 
	LIMIT 0,1
	) is not null then (
SELECT
	GROUP_CONCAT( tt.NAME SEPARATOR '/' ) 
FROM
	zt_module tt 
WHERE
	FIND_IN_SET( tt.id, t51.path ) 
	AND tt.type = 'story' 
GROUP BY
	tt.root 
	LIMIT 0,1
	) when t51.`name` is null then '' else t51.`name` end
	)  AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root ) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) AS `OVERDUEBUGS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID
WHERE t1.DELETED = '0' 
FIND_IN_SET((select t.build from zt_testtask t inner join zt_testreport tt on t.id = tt.tasks where tt.id = #{srf.datacontext.srfparentkey} 
),t1.OPENEDBUILD)  and t1.openedDate > (select tt.`begin` from  zt_testreport tt  where tt.id = #{srf.datacontext.srfparentkey} 
) and t1.openedDate < (select tt.`end` from  zt_testreport tt  where tt.id = #{srf.datacontext.srfparentkey} 
) 

```
### Build产生的Bug-模块分布(项目)(BuildProduceBugModule_Project)<div id="Bug_BuildProduceBugModule_Project"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
t1.`MODULE`,
CONCAT(
	'/',
 case when	(
SELECT
	GROUP_CONCAT( tt.NAME SEPARATOR '/' ) 
FROM
	zt_module tt 
WHERE
	FIND_IN_SET( tt.id, t51.path ) 
	AND tt.type = 'story' 
GROUP BY
	tt.root 
	LIMIT 0,1
	) is not null then (
SELECT
	GROUP_CONCAT( tt.NAME SEPARATOR '/' ) 
FROM
	zt_module tt 
WHERE
	FIND_IN_SET( tt.id, t51.path ) 
	AND tt.type = 'story' 
GROUP BY
	tt.root 
	LIMIT 0,1
	) when t51.`name` is null then '' else t51.`name` end
	)  AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root ) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) AS `OVERDUEBUGS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID 
	INNER JOIN (select tt.id from zt_build tt where tt.deleted = '0' and tt.id in (
	select t1.build from zt_testtask t1 where FIND_IN_SET(t1.id,(SELECT tasks from zt_testreport where id = #{srf.datacontext.srfparentkey} )))) t71 on FIND_IN_SET(t71.id,t1.openedBuild)

WHERE t1.DELETED = '0' 
( t1.openedDate > (select tt.`begin` from  zt_testreport tt  where tt.id =  #{srf.datacontext.srfparentkey} 
) and t1.openedDate < (select CONCAT(DATE_FORMAT(tt.`end`,'%Y-%m-%d'),' 23:59:59') from  zt_testreport tt  where tt.id =  #{srf.datacontext.srfparentkey} 
) and not EXISTS(select 1 from zt_build t where FIND_IN_SET(t.id,t1.openedBuild) and FIND_IN_SET(t.id,(select GROUP_CONCAT(tt.id) from (select tt.* from zt_build tt where  tt.id in (
	select t1.build from zt_testtask t1 where FIND_IN_SET(t1.id,(SELECT tasks from zt_testreport where id =  #{srf.datacontext.srfparentkey} )))) tt GROUP BY tt.product)) = 0) and FIND_IN_SET('trunk',t1.openedBuild) = 0 ) 

```
### Build产生的Bug-创建者分布(BuildProduceBugOpenedBy)<div id="Bug_BuildProduceBugOpenedBy"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
t1.`MODULE`,
t51.`NAME` AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root ) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t71.realname AS `OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) AS `OVERDUEBUGS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID 
LEFT JOIN zt_user t71 on t1.openedBy = t71.account
WHERE t1.DELETED = '0' 
FIND_IN_SET((select t.build from zt_testtask t inner join zt_testreport tt on t.id = tt.tasks where tt.id = #{srf.datacontext.srfparentkey} 
),t1.OPENEDBUILD)  and t1.openedDate > (select tt.`begin` from  zt_testreport tt  where tt.id = #{srf.datacontext.srfparentkey} 
) and t1.openedDate < (select tt.`end` from  zt_testreport tt  where tt.id = #{srf.datacontext.srfparentkey} 
) 

```
### Build产生的Bug-创建者分布(项目)(BuildProduceBugOpenedBy_Project)<div id="Bug_BuildProduceBugOpenedBy_Project"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
t1.`MODULE`,
t51.`NAME` AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root ) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t71.realname as `OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) AS `OVERDUEBUGS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID 
LEFT JOIN zt_user t71 on t1.openedBy = t71.account
	INNER JOIN (select tt.id from zt_build tt where tt.deleted = '0' and tt.id in (
	select t1.build from zt_testtask t1 where FIND_IN_SET(t1.id,(SELECT tasks from zt_testreport where id = #{srf.datacontext.srfparentkey} )))) t81 on FIND_IN_SET(t81.id,t1.openedBuild)

WHERE t1.DELETED = '0' 
( t1.openedDate > (select tt.`begin` from  zt_testreport tt  where tt.id =  #{srf.datacontext.srfparentkey} 
) and t1.openedDate < (select CONCAT(DATE_FORMAT(tt.`end`,'%Y-%m-%d'),' 23:59:59') from  zt_testreport tt  where tt.id =  #{srf.datacontext.srfparentkey} 
) and not EXISTS(select 1 from zt_build t where FIND_IN_SET(t.id,t1.openedBuild) and FIND_IN_SET(t.id,(select GROUP_CONCAT(tt.id) from (select tt.* from zt_build tt where  tt.id in (
	select t1.build from zt_testtask t1 where FIND_IN_SET(t1.id,(SELECT tasks from zt_testreport where id =  #{srf.datacontext.srfparentkey} )))) tt GROUP BY tt.product)) = 0) and FIND_IN_SET('trunk',t1.openedBuild) = 0 ) 
t1.openedBy is not null and t1.openedBy <> '' 

```
### Build产生的Bug（已解决）(BuildProduceBugRES)<div id="Bug_BuildProduceBugRES"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t71.`TITLE` AS `CASENAME`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
(case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` ='active' and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`,
( CASE WHEN t1.deadline IS NULL  OR t1.deadline = '0000-00-00'  OR t1.deadline = '1970-01-01' THEN ''  WHEN t1.`status` = 'resolved'  AND t1.deadline < DATE_FORMAT( t1.resolvedDate, '%y-%m-%d' ) THEN CONCAT_WS( '', '延期', TIMESTAMPDIFF( DAY, t1.deadline, t1.resolvedDate ), '天' ) ELSE ''  END ) AS `DELAYRESOLVE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
'' AS `MAILTOPK`,
t1.`MODULE`,
t51.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) AS `OVERDUEBUGS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID 
LEFT JOIN zt_case t71 ON t1.`CASE` = t71.ID 

WHERE t1.DELETED = '0' 
( FIND_IN_SET((select t.build from zt_testtask t inner join zt_testreport tt on t.id = tt.tasks where tt.id = #{srf.datacontext.srfparentkey} 
),t1.OPENEDBUILD)  and t1.openedDate > (select tt.`begin` from  zt_testreport tt  where tt.id = #{srf.datacontext.srfparentkey} 
) and t1.openedDate < (select tt.`end` from  zt_testreport tt  where tt.id = #{srf.datacontext.srfparentkey} 
)  AND  t1.resolution is not null and t1.resolution <> '' and t1.`status` in ('resolved','closed') ) 

```
### Build产生的Bug-解决者分布(BuildProduceBugRESOLVEDBY)<div id="Bug_BuildProduceBugRESOLVEDBY"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
t1.`MODULE`,
t51.`NAME` AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root ) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) AS `OVERDUEBUGS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t71.realname AS `RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID 
LEFT JOIN zt_user t71 on t1.RESOLVEDBY = t71.account
WHERE t1.DELETED = '0' 
FIND_IN_SET((select t.build from zt_testtask t inner join zt_testreport tt on t.id = tt.tasks where tt.id = #{srf.datacontext.srfparentkey} 
),t1.OPENEDBUILD)  and t1.openedDate > (select tt.`begin` from  zt_testreport tt  where tt.id = #{srf.datacontext.srfparentkey} 
) and t1.openedDate < (select tt.`end` from  zt_testreport tt  where tt.id = #{srf.datacontext.srfparentkey} 
) 
t1.resolution is not null and t1.resolution <> '' and t1.`status` in ('resolved','closed') 

```
### Build产生的Bug-解决者分布(项目)(BuildProduceBugRESOLVEDBY_Project)<div id="Bug_BuildProduceBugRESOLVEDBY_Project"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
t1.`MODULE`,
t51.`NAME` AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root ) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) AS `OVERDUEBUGS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t71.realname as  `RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID 
LEFT JOIN zt_user t71 on t1.RESOLVEDBY = t71.account
INNER JOIN (select tt.id from zt_build tt where tt.deleted = '0' and tt.id in (
	select t1.build from zt_testtask t1 where FIND_IN_SET(t1.id,(SELECT tasks from zt_testreport where id = #{srf.datacontext.srfparentkey} )))) t81 on FIND_IN_SET(t81.id,t1.openedBuild)

WHERE t1.DELETED = '0' 
( t1.openedDate > (select tt.`begin` from  zt_testreport tt  where tt.id =  #{srf.datacontext.srfparentkey} 
) and t1.openedDate < (select CONCAT(DATE_FORMAT(tt.`end`,'%Y-%m-%d'),' 23:59:59') from  zt_testreport tt  where tt.id =  #{srf.datacontext.srfparentkey} 
) and not EXISTS(select 1 from zt_build t where FIND_IN_SET(t.id,t1.openedBuild) and FIND_IN_SET(t.id,(select GROUP_CONCAT(tt.id) from (select tt.* from zt_build tt where  tt.id in (
	select t1.build from zt_testtask t1 where FIND_IN_SET(t1.id,(SELECT tasks from zt_testreport where id =  #{srf.datacontext.srfparentkey} )))) tt GROUP BY tt.product)) = 0) and FIND_IN_SET('trunk',t1.openedBuild) = 0 ) 
t1.RESOLVEDBY is not null and t1.RESOLVEDBY <> '' 

```
### Build产生的Bug-解决方案分布(项目)(BuildProduceBugResolution_Project)<div id="Bug_BuildProduceBugResolution_Project"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
t1.`MODULE`,
t51.`NAME` AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root ) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) AS `OVERDUEBUGS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID 
INNER JOIN (select tt.id from zt_build tt where tt.deleted = '0' and tt.id in (
	select t1.build from zt_testtask t1 where FIND_IN_SET(t1.id,(SELECT tasks from zt_testreport where id = #{srf.datacontext.srfparentkey} )))) t71 on FIND_IN_SET(t71.id,t1.openedBuild)


WHERE t1.DELETED = '0' 
( t1.openedDate > (select tt.`begin` from  zt_testreport tt  where tt.id =  #{srf.datacontext.srfparentkey} 
) and t1.openedDate < (select CONCAT(DATE_FORMAT(tt.`end`,'%Y-%m-%d'),' 23:59:59') from  zt_testreport tt  where tt.id =  #{srf.datacontext.srfparentkey} 
) and not EXISTS(select 1 from zt_build t where FIND_IN_SET(t.id,t1.openedBuild) and FIND_IN_SET(t.id,(select GROUP_CONCAT(tt.id) from (select tt.* from zt_build tt where  tt.id in (
	select t1.build from zt_testtask t1 where FIND_IN_SET(t1.id,(SELECT tasks from zt_testreport where id =  #{srf.datacontext.srfparentkey} )))) tt GROUP BY tt.product)) = 0) and FIND_IN_SET('trunk',t1.openedBuild) = 0 ) 
t1.`RESOLUTION` is not null and t1.`RESOLUTION` <> '' 

```
### Build产生的Bug-严重程度分布(项目)(BuildProduceBugSeverity_Project)<div id="Bug_BuildProduceBugSeverity_Project"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
t1.`MODULE`,
t51.`NAME` AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root ) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) AS `OVERDUEBUGS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID 
INNER JOIN (select tt.id from zt_build tt where tt.deleted = '0' and tt.id in (
	select t1.build from zt_testtask t1 where FIND_IN_SET(t1.id,(SELECT tasks from zt_testreport where id = #{srf.datacontext.srfparentkey} )))) t71 on FIND_IN_SET(t71.id,t1.openedBuild)

WHERE t1.DELETED = '0' 
( t1.openedDate > (select tt.`begin` from  zt_testreport tt  where tt.id =  #{srf.datacontext.srfparentkey} 
) and t1.openedDate < (select CONCAT(DATE_FORMAT(tt.`end`,'%Y-%m-%d'),' 23:59:59') from  zt_testreport tt  where tt.id =  #{srf.datacontext.srfparentkey} 
) and not EXISTS(select 1 from zt_build t where FIND_IN_SET(t.id,t1.openedBuild) and FIND_IN_SET(t.id,(select GROUP_CONCAT(tt.id) from (select tt.* from zt_build tt where  tt.id in (
	select t1.build from zt_testtask t1 where FIND_IN_SET(t1.id,(SELECT tasks from zt_testreport where id =  #{srf.datacontext.srfparentkey} )))) tt GROUP BY tt.product)) = 0) and FIND_IN_SET('trunk',t1.openedBuild) = 0 ) 

```
### Build产生的Bug-状态分布(项目)(BuildProduceBugStatus_Project)<div id="Bug_BuildProduceBugStatus_Project"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
t1.`MODULE`,
t51.`NAME` AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root ) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) AS `OVERDUEBUGS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID 
INNER JOIN (select tt.id from zt_build tt where tt.deleted = '0' and tt.id in (
	select t1.build from zt_testtask t1 where FIND_IN_SET(t1.id,(SELECT tasks from zt_testreport where id = #{srf.datacontext.srfparentkey} )))) t71 on FIND_IN_SET(t71.id,t1.openedBuild)

WHERE t1.DELETED = '0' 
( t1.openedDate > (select tt.`begin` from  zt_testreport tt  where tt.id =  #{srf.datacontext.srfparentkey} 
) and t1.openedDate < (select CONCAT(DATE_FORMAT(tt.`end`,'%Y-%m-%d'),' 23:59:59') from  zt_testreport tt  where tt.id =  #{srf.datacontext.srfparentkey} 
) and not EXISTS(select 1 from zt_build t where FIND_IN_SET(t.id,t1.openedBuild) and FIND_IN_SET(t.id,(select GROUP_CONCAT(tt.id) from (select tt.* from zt_build tt where  tt.id in (
	select t1.build from zt_testtask t1 where FIND_IN_SET(t1.id,(SELECT tasks from zt_testreport where id =  #{srf.datacontext.srfparentkey} )))) tt GROUP BY tt.product)) = 0) and FIND_IN_SET('trunk',t1.openedBuild) = 0 ) 

```
### Build产生的Bug-类型分布(项目)(BuildProduceBugType_Project)<div id="Bug_BuildProduceBugType_Project"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
t1.`MODULE`,
t51.`NAME` AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root ) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) AS `OVERDUEBUGS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
case when t1.`TYPE` = 'codeerror' then '代码错误' when t1.`TYPE` = 'config' then '配置相关' when t1.`TYPE` = 'install' then '安装部署' when t1.`TYPE` = 'security' then '安全相关' when t1.`TYPE` = 'performance' then '性能问题' when t1.`TYPE` = 'standard' then '标准规范' when t1.`TYPE` = 'automation' then '测试脚本' when t1.`TYPE` = 'designdefect' then '设计缺陷' when t1.`TYPE` = 'others' then '其他' else t1.type end as  `TYPE`,
t1.`V1`,
t1.`V2`
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID 
INNER JOIN (select tt.id from zt_build tt where tt.deleted = '0' and tt.id in (
	select t1.build from zt_testtask t1 where FIND_IN_SET(t1.id,(SELECT tasks from zt_testreport where id = #{srf.datacontext.srfparentkey} )))) t71 on FIND_IN_SET(t71.id,t1.openedBuild)

WHERE t1.DELETED = '0' 
( t1.openedDate > (select tt.`begin` from  zt_testreport tt  where tt.id =  #{srf.datacontext.srfparentkey} 
) and t1.openedDate < (select CONCAT(DATE_FORMAT(tt.`end`,'%Y-%m-%d'),' 23:59:59') from  zt_testreport tt  where tt.id =  #{srf.datacontext.srfparentkey} 
) and not EXISTS(select 1 from zt_build t where FIND_IN_SET(t.id,t1.openedBuild) and FIND_IN_SET(t.id,(select GROUP_CONCAT(tt.id) from (select tt.* from zt_build tt where  tt.id in (
	select t1.build from zt_testtask t1 where FIND_IN_SET(t1.id,(SELECT tasks from zt_testreport where id =  #{srf.datacontext.srfparentkey} )))) tt GROUP BY tt.product)) = 0) and FIND_IN_SET('trunk',t1.openedBuild) = 0) 

```
### 当前用户解决的Bug(CurUserResolve)<div id="Bug_CurUserResolve"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t71.`TITLE` AS `CASENAME`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
(case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` ='active' and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`,
( CASE WHEN t1.deadline IS NULL  OR t1.deadline = '0000-00-00'  OR t1.deadline = '1970-01-01' THEN ''  WHEN t1.`status` = 'resolved'  AND t1.deadline < DATE_FORMAT( t1.resolvedDate, '%y-%m-%d' ) THEN CONCAT_WS( '', '延期', TIMESTAMPDIFF( DAY, t1.deadline, t1.resolvedDate ), '天' ) ELSE ''  END ) AS `DELAYRESOLVE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
'' AS `MAILTOPK`,
t1.`MODULE`,
t51.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) AS `OVERDUEBUGS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID 
LEFT JOIN zt_case t71 ON t1.`CASE` = t71.ID 

WHERE t1.DELETED = '0' 
( t1.`RESOLVEDBY` =  ${srfsessioncontext('srfloginname','{"defname":"RESOLVEDBY","dename":"ZT_BUG"}')}  AND  ((DATE_FORMAT(t1.resolvedDate,'%Y') = DATE_FORMAT(now(),'%Y')  and #{srf.datacontext.curyear} is null) or (DATE_FORMAT(t1.resolvedDate,'%Y') = #{srf.datacontext.curyear}) ) ) 

```
### DEFAULT(DEFAULT)<div id="Bug_Default"></div>
```sql
SELECT t1.`ACTIVATEDCOUNT`, t1.`ACTIVATEDDATE`, t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`BRANCH`, t61.`NAME` AS `BRANCHNAME`, t1.`BROWSER`, t1.`CASE`, t1.`CASEVERSION`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`COLOR`, t1.`CONFIRMED`, t1.`DEADLINE`, t1.`DELETED`, t1.`DUPLICATEBUG`, t1.`ENTRY`, t1.`FOUND`, t1.`HARDWARE`, t1.`ID`, (select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'bug' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`, t1.`KEYWORDS`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LINES`, t1.`LINKBUG`, t1.`MAILTO`, t1.`MODULE`, t51.`NAME` AS `MODULENAME`, (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root ) AS `MODULENAME1`, t1.`OPENEDBUILD`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`OS`, t1.`PLAN`, t1.`PRI`, t1.`PRODUCT`, t11.`NAME` AS `PRODUCTNAME`, t1.`PROJECT`, t21.`NAME` AS `PROJECTNAME`, t1.`REPO`, t1.`REPOTYPE`, t1.`RESOLUTION`, t1.`RESOLVEDBUILD`, t1.`RESOLVEDBY`, t1.`RESOLVEDDATE`, t1.`RESULT`, t1.`SEVERITY`, t1.`STATUS`, t1.`STORY`, t31.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, t1.`TASK`, t41.`NAME` AS `TASKNAME`, t1.`TESTTASK`, t1.`TITLE`, t1.`TOSTORY`, t1.`TOTASK`, t1.`TYPE`, t1.`V1`, t1.`V2`, (case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) as overduebugs,  ( CASE WHEN t1.deadline IS NULL  			OR t1.deadline = '0000-00-00'  			OR t1.deadline = '1970-01-01' THEN 				''  				WHEN t1.`status` = 'active'  				AND t1.deadline < DATE_FORMAT( now(), '%y-%m-%d' ) THEN 					CONCAT_WS( '', '延期', TIMESTAMPDIFF( DAY, t1.deadline, now()), '天' ) ELSE ''  			END ) AS `DELAY`,  ( CASE WHEN t1.deadline IS NULL  			OR t1.deadline = '0000-00-00'  			OR t1.deadline = '1970-01-01' THEN 				''  				WHEN t1.`status` = 'resolved'  				AND t1.deadline < DATE_FORMAT( t1.resolvedDate, '%y-%m-%d' ) THEN 					CONCAT_WS( '', '延期', TIMESTAMPDIFF( DAY, t1.deadline, t1.resolvedDate ), '天' ) ELSE ''  			END ) AS `DELAYRESOLVE` FROM `zt_bug` t1  LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID  LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID  LEFT JOIN zt_story t31 ON t1.STORY = t31.ID  LEFT JOIN zt_task t41 ON t1.TASK = t41.ID  LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID  LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID
WHERE t1.DELETED = '0' 

```
### ES批量的导入(ESBulk)<div id="Bug_ESBulk"></div>
```sql
SELECT
	t1.id,
	t1.title,
	t1.color,
	t1.steps,
	t1.deleted,
	t1.product,
	t1.project 
FROM
	zt_bug t1
```
### 我代理的Bug(MyAgentBug)<div id="Bug_MyAgentBug"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
case when t71.`AGENTUSER` = #{srf.sessioncontext.srfloginname} then t71.`AGENTUSER` else t1.`ASSIGNEDTO` end as `ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'bug' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
t1.`MODULE`,
t51.`NAME` AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root ) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) as overduebugs, 
( CASE WHEN t1.deadline IS NULL 
			OR t1.deadline = '0000-00-00' 
			OR t1.deadline = '1970-01-01' THEN
				'' 
				WHEN t1.`status` = 'active' 
				AND t1.deadline < DATE_FORMAT( now(), '%y-%m-%d' ) THEN
					CONCAT_WS( '', '延期', TIMESTAMPDIFF( DAY, t1.deadline, now()), '天' ) ELSE '' 
			END ) AS `DELAY`, 
( CASE WHEN t1.deadline IS NULL 
			OR t1.deadline = '0000-00-00' 
			OR t1.deadline = '1970-01-01' THEN
				'' 
				WHEN t1.`status` = 'resolved' 
				AND t1.deadline < DATE_FORMAT( t1.resolvedDate, '%y-%m-%d' ) THEN
					CONCAT_WS( '', '延期', TIMESTAMPDIFF( DAY, t1.deadline, t1.resolvedDate ), '天' ) ELSE '' 
			END ) AS `DELAYRESOLVE`
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID
LEFT JOIN t_ibz_agent t71 ON t1.assignedTo = t71.CREATEMANNAME and DATE_FORMAT(now(), '%Y-%m-%d') >= t71.AGENTBEGIN and DATE_FORMAT(now(), '%Y-%m-%d') <= t71.AGENTEND
WHERE t1.deleted = '0' 

```
### 累计创建的Bug数(MyCurOpenedBug)<div id="Bug_MyCurOpenedBug"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t71.`TITLE` AS `CASENAME`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
(case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` ='active' and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`,
( CASE WHEN t1.deadline IS NULL  OR t1.deadline = '0000-00-00'  OR t1.deadline = '1970-01-01' THEN ''  WHEN t1.`status` = 'resolved'  AND t1.deadline < DATE_FORMAT( t1.resolvedDate, '%y-%m-%d' ) THEN CONCAT_WS( '', '延期', TIMESTAMPDIFF( DAY, t1.deadline, t1.resolvedDate ), '天' ) ELSE ''  END ) AS `DELAYRESOLVE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
'' AS `MAILTOPK`,
t1.`MODULE`,
t51.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) AS `OVERDUEBUGS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID 
LEFT JOIN zt_case t71 ON t1.`CASE` = t71.ID 

WHERE t1.DELETED = '0' 
( DATE_FORMAT( t1.openedDate, '%Y' ) = DATE_FORMAT(now(), '%Y' )  AND  t1.`OPENEDBY` =  ${srfsessioncontext('srfloginname','{"defname":"OPENEDBY","dename":"ZT_BUG"}')} ) 

```
### 我的收藏(MyFavorites)<div id="Bug_MyFavorites"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'bug' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
t1.`MODULE`,
t51.`NAME` AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root ) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) as overduebugs 
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID 

WHERE t1.DELETED = '0' 
t1.id in (select t.OBJECTID from T_IBZ_FAVORITES t where t.type = 'bug' and t.account = #{srf.sessioncontext.srfloginname}) 

```
### 计划关联bug(去除已关联)(NotCurPlanLinkBug)<div id="Bug_NotCurPlanLinkBug"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t71.`TITLE` AS `CASENAME`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
t1.`MODULE`,
t51.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) AS `OVERDUEBUGS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID 
LEFT JOIN zt_case t71 ON t1.`CASE` = t71.ID 

WHERE t1.DELETED = '0' 
t1.plan <> ${srfwebcontext('plan','{"defname":"id","dename":"ZT_PRODUCTPLAN"}')} 
t1.product = ${srfwebcontext('product','{"defname":"id","dename":"ZT_PRODUCT"}')} 
t1.`status` = 'active' 

```
### 发布关联Bug（已解决）(ReleaseBugs)<div id="Bug_ReleaseBugs"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t1.`BROWSER`,
t1.`CASE`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'bug' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
t1.`MODULE`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) as overduebugs 
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
WHERE t1.`DELETED` ='0' AND FIND_IN_SET ( t1.ID, (SELECT BUGS FROM ZT_RELEASE WHERE ID= ${srfdatacontext('srfparentkey','{"defname":"ID","dename":"ZT_RELEASE"}')}))
```
### 发布关联Bug（未解决）(ReleaseLeftBugs)<div id="Bug_ReleaseLeftBugs"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t1.`BROWSER`,
t1.`CASE`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'bug' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
t1.`MODULE`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) as overduebugs 
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
WHERE t1.`DELETED` = '0' AND FIND_IN_SET ( t1.ID, (SELECT LEFTBUGS FROM ZT_RELEASE WHERE ID= ${srfdatacontext('srfparentkey','{"defname":"ID","dename":"ZT_RELEASE"}')}))
```
### 发布可关联的bug（遗留）(ReleaseLinkableLeftBug)<div id="Bug_ReleaseLinkableLeftBug"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t71.`TITLE` AS `CASENAME`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
(case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` ='active' and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`,
( CASE WHEN t1.deadline IS NULL  OR t1.deadline = '0000-00-00'  OR t1.deadline = '1970-01-01' THEN ''  WHEN t1.`status` = 'resolved'  AND t1.deadline < DATE_FORMAT( t1.resolvedDate, '%y-%m-%d' ) THEN CONCAT_WS( '', '延期', TIMESTAMPDIFF( DAY, t1.deadline, t1.resolvedDate ), '天' ) ELSE ''  END ) AS `DELAYRESOLVE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
'' AS `MAILTOPK`,
t1.`MODULE`,
t51.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) AS `OVERDUEBUGS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID 
LEFT JOIN zt_case t71 ON t1.`CASE` = t71.ID 

WHERE t1.DELETED = '0' 
( t1.`PRODUCT` = ${srfdatacontext('product','{"defname":"PRODUCT","dename":"ZT_BUG"}')} ) 

```
### 发布可关联的bug（已解决）(ReleaseLinkableResolvedBug)<div id="Bug_ReleaseLinkableResolvedBug"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t71.`TITLE` AS `CASENAME`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
(case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` ='active' and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`,
( CASE WHEN t1.deadline IS NULL  OR t1.deadline = '0000-00-00'  OR t1.deadline = '1970-01-01' THEN ''  WHEN t1.`status` = 'resolved'  AND t1.deadline < DATE_FORMAT( t1.resolvedDate, '%y-%m-%d' ) THEN CONCAT_WS( '', '延期', TIMESTAMPDIFF( DAY, t1.deadline, t1.resolvedDate ), '天' ) ELSE ''  END ) AS `DELAYRESOLVE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
'' AS `MAILTOPK`,
t1.`MODULE`,
t51.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) AS `OVERDUEBUGS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID 
LEFT JOIN zt_case t71 ON t1.`CASE` = t71.ID 

WHERE t1.DELETED = '0' 
( t1.`PRODUCT` = ${srfdatacontext('product','{"defname":"PRODUCT","dename":"ZT_BUG"}')} ) 

```
### 发布关联Bug（未解决）(ReportBugs)<div id="Bug_ReportBugs"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t1.`BROWSER`,
t1.`CASE`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'bug' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
t1.`MODULE`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) as overduebugs 
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
WHERE t1.`DELETED` = '0' AND FIND_IN_SET ( t1.ID, (SELECT BUGS FROM ZT_TESTREPORT WHERE ID= ${srfdatacontext('srfparentkey','{"defname":"ID","dename":"ZT_TESTREPORT "}')}))
```
### 版本关联bug(遗留得bug)(SelectBugByBuild)<div id="Bug_SelectBugByBuild"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t71.`TITLE` AS `CASENAME`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
(case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` ='active' and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`,
( CASE WHEN t1.deadline IS NULL  OR t1.deadline = '0000-00-00'  OR t1.deadline = '1970-01-01' THEN ''  WHEN t1.`status` = 'resolved'  AND t1.deadline < DATE_FORMAT( t1.resolvedDate, '%y-%m-%d' ) THEN CONCAT_WS( '', '延期', TIMESTAMPDIFF( DAY, t1.deadline, t1.resolvedDate ), '天' ) ELSE ''  END ) AS `DELAYRESOLVE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
'' AS `MAILTOPK`,
t1.`MODULE`,
t51.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) AS `OVERDUEBUGS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID 
LEFT JOIN zt_case t71 ON t1.`CASE` = t71.ID 

WHERE t1.DELETED = '0' 
( FIND_IN_SET((select t.build from zt_testtask t inner join zt_testreport tt on t.id = tt.tasks where tt.id = #{srf.datacontext.srfparentkey} 
),t1.OPENEDBUILD)  and t1.openedDate > (select tt.`begin` from  zt_testreport tt  where tt.id = #{srf.datacontext.srfparentkey} 
) and t1.openedDate < (select tt.`end` from  zt_testreport tt  where tt.id = #{srf.datacontext.srfparentkey} 
)  AND  (t1.`status` = 'active' or t1.resolvedDate > (select zt_testreport.`end` from zt_testreport where zt_testreport.id = 
 #{srf.datacontext.srfparentkey} 
)) ) 

```
### 查询遗留得bug(项目)(SelectBugsByProject)<div id="Bug_SelectBugsByProject"></div>
```sql
SELECT distinct
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t61.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
t1.`MODULE`,
t51.`NAME` AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t51.path ) AND tt.type = 'story'  GROUP BY tt.root ) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) AS `OVERDUEBUGS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STORY`,
t31.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t41.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`
FROM `zt_bug` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
LEFT JOIN zt_project t21 ON t1.PROJECT = t21.ID 
LEFT JOIN zt_story t31 ON t1.STORY = t31.ID 
LEFT JOIN zt_task t41 ON t1.TASK = t41.ID 
LEFT JOIN zt_module t51 ON t1.MODULE = t51.ID 
LEFT JOIN zt_branch t61 ON t1.BRANCH = t61.ID 
INNER JOIN (select tt.id from zt_build tt where tt.deleted = '0' and tt.id in (
	select t1.build from zt_testtask t1 where FIND_IN_SET(t1.id, (select tasks from zt_testreport where id = #{srf.datacontext.srfparentkey} )))) t71 on FIND_IN_SET(t71.id,t1.openedBuild)
WHERE t1.deleted = '0' 
 t1.openedDate > (select tt.`begin` from  zt_testreport tt  where tt.id = #{srf.datacontext.srfparentkey}
) and t1.openedDate < (select CONCAT(DATE_FORMAT(tt.`end`,'%Y-%m-%d'),' 23:59:59') from  zt_testreport tt  where tt.id =#{srf.datacontext.srfparentkey}
) and not EXISTS(select 1 from zt_build t where FIND_IN_SET(t.id,t1.openedBuild) and FIND_IN_SET(t.id,(select GROUP_CONCAT(tt.id) from (select tt.* from zt_build tt where  tt.id in (
	select t1.build from zt_testtask t1 where FIND_IN_SET(t1.id,(SELECT tasks from zt_testreport where id =#{srf.datacontext.srfparentkey})))) tt GROUP BY tt.product)) = 0) and FIND_IN_SET('trunk',t1.openedBuild) = 0 and  (t1.`status` = 'active' or t1.resolvedDate > (select CONCAT(DATE_FORMAT(tt.`end`,'%Y-%m-%d'),' 23:59:59') from zt_testreport tt where tt.id = #{srf.datacontext.srfparentkey})) 

```
### 任务相关bug(TaskBug)<div id="Bug_TaskBug"></div>
```sql
SELECT
	t1.id,
	t1.product,
	t1.branch,
	t1.module,
	t1.project,
	t1.title,
        t1.task,
	t1.keywords,
	t1.pri,
	t1.type,
	t1.status,
	t1.subStatus,
	t1.mailto,
	t1.openedBy,
	t1.openedDate,
	t1.assignedTo,
	t1.assignedDate,
	t1.resolvedBy,
	t1.resolvedDate,
	t1.lastEditedBy,
	t1.lastEditedDate,
	t31.`NAME` AS `PRODOCTNAME`,
	t51.`NAME` AS `PROJECTNAME`,
	(
SELECT
	( CASE WHEN COUNT( t.IBZ_FAVORITESID ) > 0 THEN 1 ELSE 0 END ) AS ISFAVORITES 
FROM
	T_IBZ_FAVORITES t 
WHERE
	t.TYPE = 'task' 
	AND t.ACCOUNT = #{srf.sessioncontext.srfloginname} 
	AND t.OBJECTID = t1.id 
	) AS `ISFAVORITES`
	
FROM
	zt_bug t1	
        LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID
	LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID
	LEFT JOIN zt_branch t41 ON t1.BRANCH = t41.ID 
	LEFT JOIN zt_project t51 ON t1.PROJECT = t51.ID
  	

WHERE t1.deleted='0' 
 
t1.task = ${srfdatacontext('srfparentkey')} 

```
### 默认（全部数据）(VIEW)<div id="Bug_View"></div>
```sql
SELECT
t1.`ACTIVATEDCOUNT`,
t1.`ACTIVATEDDATE`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t21.`NAME` AS `BRANCHNAME`,
t1.`BROWSER`,
t1.`CASE`,
t41.`TITLE` AS `CASENAME`,
t1.`CASEVERSION`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`COLOR`,
t1.`CONFIRMED`,
t1.`DEADLINE`,
(case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` ='active' and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`,
( CASE WHEN t1.deadline IS NULL  OR t1.deadline = '0000-00-00'  OR t1.deadline = '1970-01-01' THEN ''  WHEN t1.`status` = 'resolved'  AND t1.deadline < DATE_FORMAT( t1.resolvedDate, '%y-%m-%d' ) THEN CONCAT_WS( '', '延期', TIMESTAMPDIFF( DAY, t1.deadline, t1.resolvedDate ), '天' ) ELSE ''  END ) AS `DELAYRESOLVE`,
t1.`DELETED`,
t1.`DUPLICATEBUG`,
t1.`ENTRY`,
t1.`FOUND`,
t1.`HARDWARE`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINES`,
t1.`LINKBUG`,
t1.`MAILTO`,
'' AS `MAILTOPK`,
t1.`MODULE`,
t71.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t71.path ) AND tt.type = 'story'  GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`,
t1.`OPENEDBUILD`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OS`,
(case when t1.DEADLINE = '0000-00-00' then 0 else datediff(t1.deadline, now() ) end) AS `OVERDUEBUGS`,
t1.`PLAN`,
t1.`PRI`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t51.`NAME` AS `PROJECTNAME`,
t1.`REPO`,
t1.`REPOTYPE`,
t1.`RESOLUTION`,
t1.`RESOLVEDBUILD`,
t1.`RESOLVEDBY`,
t1.`RESOLVEDDATE`,
t1.`RESULT`,
t1.`SEVERITY`,
t1.`STATUS`,
t1.`STEPS`,
t1.`STORY`,
t61.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASK`,
t31.`NAME` AS `TASKNAME`,
t1.`TESTTASK`,
t1.`TITLE`,
t1.`TOSTORY`,
t1.`TOTASK`,
t1.`TYPE`,
t1.`V1`,
t1.`V2`
FROM `zt_bug` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 
LEFT JOIN `zt_branch` t21 ON t1.`BRANCH` = t21.`ID` 
LEFT JOIN `zt_task` t31 ON t1.`TASK` = t31.`ID` 
LEFT JOIN `zt_case` t41 ON t1.`CASE` = t41.`ID` 
LEFT JOIN `zt_project` t51 ON t1.`PROJECT` = t51.`ID` 
LEFT JOIN `zt_story` t61 ON t1.`STORY` = t61.`ID` 
LEFT JOIN `zt_module` t71 ON t1.`MODULE` = t71.`ID` 

WHERE t1.DELETED = '0' 

```

# **Bug统计**(IBZ_BUGSTATS)

### Bug在每个解决方案的Bug数(BugCountInResolution)<div id="BugStats_BugCountInResolution"></div>
```sql
SELECT
t1.project,
t1.projectname,
sum(if(t1.resolution='tostory',t1.ss,0)) as bugtostory,
sum(if(t1.resolution='bydesign',t1.ss,0)) as bugbydesign,
sum(if(t1.resolution='duplicate',t1.ss,0)) as bugduplicate,
sum(if(t1.resolution='fixed',t1.ss,0)) as bugfixed,
sum(if(t1.resolution='external',t1.ss,0)) as bugexternal,
sum(if(t1.resolution='willnotfix',t1.ss,0)) as bugwillnotfix,
sum(if(t1.resolution='notrepro',t1.ss,0)) as bugnotrepro,
sum(if(t1.resolution='postponed',t1.ss,0)) as bugpostponed,
count(1) as bugcnt
from
(
SELECT
t1.resolution,
t1.project,
t11.name as projectname,
1 as ss
from
zt_bug t1
left join zt_project t11 on t1.project = t11.id
where t1.deleted='0' and t1.project <>0 and t1.resolution <> ''
)t1
GROUP BY
t1.project,
t1.projectname

```
### Bug完成表(BugResolvedBy)<div id="BugStats_BugResolvedBy"></div>
```sql
SELECT
	t1.resolvedBy,
	t1.product,
	t1.productname,
	t1.bugcnt,
	t11.bugcnt AS bugtotal 
FROM
	(
	SELECT
		t1.resolvedBy,
		t1.product,
		t11.`name` AS productname,
		COUNT( t1.id ) AS bugcnt 
	FROM
		`zt_bug` t1
		LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
	WHERE
		t1.deleted = '0' 
		AND t1.resolvedBy <> '' 
		AND t1.product <> 0 
		AND t11.deleted = '0' 
	GROUP BY
		t1.resolvedBy,
		t1.product,
		t11.`name` 
	) t1
	INNER JOIN (
	SELECT
		t1.resolvedBy,
		COUNT( t1.id ) AS bugcnt 
	FROM
		`zt_bug` t1
		LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
	WHERE
		t1.deleted = '0' 
		AND t1.resolvedBy <> '' 
		AND t1.product <> 0 
		AND t11.deleted = '0' 
	GROUP BY
		t1.resolvedBy 
	) t11 ON t1.resolvedBy = t11.resolvedBy 
ORDER BY
	t1.resolvedBy ASC
```
### bug解决汇总表(BugResolvedGird)<div id="BugStats_BugResolvedGird"></div>
```sql
SELECT t1.resolvedBy,t1.resolution as bugresolution,t1.id as bugid,t1.title as bugtitle,t1.pri as bugpri,t1.severity as bugseverity,t1.openedBy as bugopenedby,t1.openedDate as bugopeneddate,t1.resolvedDate as bugresolvedDate,t1.`status` as bugstatus ,t2.dept,DATE_FORMAT(t1.resolvedDate,'%Y-%m-%d') as `begin`,DATE_FORMAT(t1.resolvedDate,'%Y-%m-%d') as `end`

from zt_bug t1 LEFT JOIN zt_user t2 on t1.resolvedBy = t2.account where t1.deleted = '0' and t1.resolution = 'fixed' and t1.`status` in ('closed','resolved') 
and ( t2.dept = #{srf.datacontext.dept} or #{srf.datacontext.dept} is null )
and (DATE_FORMAT(t1.resolvedDate,'%Y-%m-%d') >= #{srf.datacontext.begin} or #{srf.datacontext.begin} is null)
and (DATE_FORMAT(t1.resolvedDate,'%Y-%m-%d') <= #{srf.datacontext.end} or #{srf.datacontext.end} is null)

ORDER BY t1.resolvedBy
```
### Bug指派表(BugassignedTo)<div id="BugStats_BugassignedTo"></div>
```sql
SELECT
	t1.assignedTo , t1.product,t1.productname,t1.bugcnt,t11.bugcnt as bugtotal
FROM
	(
SELECT
	t1.assignedTo,
	t1.product,
	t11.`name` as productname,
	COUNT( t1.id ) AS bugcnt 
FROM
	`zt_bug` t1
	LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
WHERE
	t1.deleted = '0' 
	AND t1.assignedTo <> '' 
	AND t1.assignedTo IS NOT NULL 
	AND t1.product <> 0 
	AND t1.assignedTo <> 'closed' 
	AND t1.`status` = 'active'
	and t11.deleted = '0'
GROUP BY
	t1.assignedTo,
	t1.product,
	t11.`name` 
	) t1 inner join (SELECT
	t1.assignedTo,
	COUNT( t1.id ) AS bugcnt 
FROM
	`zt_bug` t1
	LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID 
WHERE
	t1.deleted = '0' 
	AND t1.assignedTo <> '' 
	AND t1.assignedTo IS NOT NULL 
	AND t1.product <> 0 
	AND t1.assignedTo <> 'closed' 
	AND t1.`status` = 'active'
	and t11.deleted = '0'
GROUP BY
	t1.assignedTo) t11 on t1.assignedTo = t11.assignedTo
ORDER BY
	t1.assignedTo asc
	
	
```
### Bug创建表(DEFAULT)<div id="BugStats_Default"></div>
```sql
SELECT
	t1.openedBy,
	sum( IF ( t1.resolution = '', t1.v1, 0 ) ) AS Bugwjj,
	sum( IF ( t1.resolution = 'bydesign', t1.v1, 0 ) ) AS Bugbydesign,
	SUM( IF ( t1.resolution = 'duplicate', t1.v1, 0 ) ) AS Bugduplicate,
	SUM( IF ( t1.resolution = 'fixed', t1.v1, 0 ) ) AS Bugfixed,
	SUM( IF ( t1.resolution = 'external', t1.v1, 0 ) ) AS Bugexternal,
	sum( IF ( t1.resolution = 'notrepro', t1.v1, 0 ) ) AS Bugnotrepro,
	SUM( IF ( t1.resolution = 'postponed', t1.v1, 0 ) ) AS Bugpostponed,
	SUM( IF ( t1.resolution = 'willnotfix', t1.v1, 0 ) ) AS Bugwillnotfix,
	CONCAT( ROUND( case when (SUM( IF ( t1.resolution = 'fixed', t1.v1, 0 ) ) + SUM( IF ( t1.resolution = 'postponed', t1.v1, 0 ) )) = 0 then 0 else (SUM( IF ( t1.resolution = 'fixed', t1.v1, 0 ) ) + SUM( IF ( t1.resolution = 'postponed', t1.v1, 0 ) ))/ (sum( IF ( t1.resolution = 'bydesign', t1.v1, 0 ) ) + SUM( IF ( t1.resolution = 'duplicate', t1.v1, 0 ) ) + SUM( IF ( t1.resolution = 'fixed', t1.v1, 0 ) ) + SUM( IF ( t1.resolution = 'external', t1.v1, 0 ) ) + sum( IF ( t1.resolution = 'notrepro', t1.v1, 0 ) ) + SUM( IF ( t1.resolution = 'postponed', t1.v1, 0 ) ) + SUM( IF ( t1.resolution = 'willnotfix', t1.v1, 0 ) )) * 100 end,2), '%') as BugEfficient,
	SUM( IF ( t1.resolution = 'tostory', t1.v1, 0 ) ) AS Bugtostory,
	SUM( t1.v1 ) AS BUGTOTAL 
FROM
	(
SELECT
	t1.`OPENEDBY`,
	t1.`OPENEDDATE`,
	t1.`PRODUCT`,
	t1.`PROJECT`,
	t1.`RESOLUTION`,
	1 AS `V1` 
FROM
	`zt_bug` t1
	LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID
WHERE
	t1.deleted = '0' 
	) t1 
WHERE
	( t1.openedDate >= #{srf.datacontext.openeddatelt}  OR #{srf.datacontext.openeddatelt} IS NULL ) 
	AND ( t1.openedDate <= #{srf.datacontext.openeddategt} OR #{srf.datacontext.openeddategt} is null ) 
	AND ( t1.PRODUCT = #{srf.datacontext.producteq} OR #{srf.datacontext.producteq}  IS NULL ) 
	AND ( t1.PROJECT = #{srf.datacontext.projecteq}  OR #{srf.datacontext.projecteq}  IS NULL ) 
GROUP BY
	t1.openedBy
```
### 产品Bug解决方案汇总(ProductBugResolutionStats)<div id="BugStats_ProductBugResolutionStats"></div>
```sql
select t1.id AS Product, 
	t1.`name` AS ProductName, 
	ifnull(t2.Bugwjj, 0) AS Bugwjj, 
	ifnull(t2.Bugbydesign, 0) AS Bugbydesign, 
	ifnull(t2.Bugduplicate, 0) AS Bugduplicate, 
	ifnull(t2.Bugfixed, 0) AS Bugfixed, 
	ifnull(t2.Bugexternal, 0) AS Bugexternal, 
	ifnull(t2.Bugnotrepro, 0) AS Bugnotrepro, 
	ifnull(t2.Bugpostponed, 0) AS Bugpostponed, 
	ifnull(t2.Bugwillnotfix, 0) AS Bugwillnotfix, 
	ifnull(t2.BugEfficient, '100.00%') AS BugEfficient, 
	ifnull(t2.Bugtostory, 0) AS Bugtostory, 
	ifnull(t2.BUGTOTAL, 0) AS BUGTOTAL 
	from zt_product t1 left join (SELECT
	t1.product, 
	t1.project,
	sum( IF ( t1.resolution = '', t1.v1, 0 ) ) AS Bugwjj,
	sum( IF ( t1.resolution = 'bydesign', t1.v1, 0 ) ) AS Bugbydesign,
	SUM( IF ( t1.resolution = 'duplicate', t1.v1, 0 ) ) AS Bugduplicate,
	SUM( IF ( t1.resolution = 'fixed', t1.v1, 0 ) ) AS Bugfixed,
	SUM( IF ( t1.resolution = 'external', t1.v1, 0 ) ) AS Bugexternal,
	sum( IF ( t1.resolution = 'notrepro', t1.v1, 0 ) ) AS Bugnotrepro,
	SUM( IF ( t1.resolution = 'postponed', t1.v1, 0 ) ) AS Bugpostponed,
	SUM( IF ( t1.resolution = 'willnotfix', t1.v1, 0 ) ) AS Bugwillnotfix,
	CONCAT( ROUND( case when (SUM( IF ( t1.resolution = 'fixed', t1.v1, 0 ) ) + SUM( IF ( t1.resolution = 'postponed', t1.v1, 0 ) )) = 0 then 0 else (SUM( IF ( t1.resolution = 'fixed', t1.v1, 0 ) ) + SUM( IF ( t1.resolution = 'postponed', t1.v1, 0 ) ))/ (sum( IF ( t1.resolution = 'bydesign', t1.v1, 0 ) ) + SUM( IF ( t1.resolution = 'duplicate', t1.v1, 0 ) ) + SUM( IF ( t1.resolution = 'fixed', t1.v1, 0 ) ) + SUM( IF ( t1.resolution = 'external', t1.v1, 0 ) ) + sum( IF ( t1.resolution = 'notrepro', t1.v1, 0 ) ) + SUM( IF ( t1.resolution = 'postponed', t1.v1, 0 ) ) + SUM( IF ( t1.resolution = 'willnotfix', t1.v1, 0 ) )) * 100 end,2), '%') as BugEfficient,
	SUM( IF ( t1.resolution = 'tostory', t1.v1, 0 ) ) AS Bugtostory,
	SUM( t1.v1 ) AS BUGTOTAL 
FROM
	(
SELECT
	t1.`OPENEDBY`,
	t1.`OPENEDDATE`,
	t1.`PRODUCT`,
	t1.`PROJECT`,
	t1.`RESOLUTION`,
	1 AS `V1` 
FROM
	`zt_bug` t1
WHERE
	t1.deleted = '0'
	) t1 
WHERE
	( t1.openedDate >= #{srf.datacontext.openeddatelt}  OR #{srf.datacontext.openeddatelt} IS NULL ) 
	AND ( t1.openedDate <= #{srf.datacontext.openeddategt} OR #{srf.datacontext.openeddategt} is null ) 
GROUP BY
	t1.product) t2 on t1.id = t2.product
	where t1.deleted = '0'
	AND ( t2.PRODUCT = #{srf.datacontext.producteq} OR #{srf.datacontext.producteq}  IS NULL ) 
	AND ( t2.PROJECT = #{srf.datacontext.projecteq}  OR #{srf.datacontext.projecteq}  IS NULL )
```
### 产品Bug状态汇总(ProductBugStatusSum)<div id="BugStats_ProductBugStatusSum"></div>
```sql
select t1.id AS Product, 
	t1.`name` AS ProductName, 
	ifnull(t2.ActiveBug, 0) AS BugActive, 
	ifnull(t2.ResolvedBug, 0) AS BugResolved, 
	ifnull(t2.ClosedBug, 0) AS BugClosed, 
	ifnull(t2.BugEfficient, '100.00%') AS BugEfficient, 
	ifnull(t2.BUGTOTAL, 0) AS BUGTOTAL 
	from zt_product t1 left join (SELECT
	t1.product, 
	t1.project, 
	sum( IF ( t1.`status` = 'active', t1.v1, 0 ) ) AS ActiveBug,
	sum( IF ( t1.`status` = 'resolved', t1.v1, 0 ) ) AS ResolvedBug,
	SUM( IF ( t1.`status` = 'closed', t1.v1, 0 ) ) AS ClosedBug,
	CONCAT( ROUND( case when (SUM( IF ( t1.`status` = 'closed', t1.v1, 0 ) ) + SUM( IF ( t1.`status` = 'resolved', t1.v1, 0 ) )) = 0 then 0 else (SUM( IF ( t1.`status` = 'closed', t1.v1, 0 ) ) + SUM( IF ( t1.`status` = 'resolved', t1.v1, 0 ) ))/ (sum( IF ( t1.`status` = 'active', t1.v1, 0 ) ) + SUM( IF ( t1.`status` = 'resolved', t1.v1, 0 ) ) + SUM( IF ( t1.`status` = 'closed', t1.v1, 0 ) )) * 100 end,2), '%') as BugEfficient,
	SUM( t1.v1 ) AS BUGTOTAL 
FROM
	(
SELECT
	t1.`OPENEDBY`,
	t1.`OPENEDDATE`,
	t1.`PRODUCT`,
	t1.`PROJECT`,
	t1.`STATUS`,
	1 AS `V1` 
FROM
	`zt_bug` t1
WHERE
	t1.deleted = '0'
	) t1 
WHERE
	( t1.openedDate >= #{srf.datacontext.openeddatelt}  OR #{srf.datacontext.openeddatelt} IS NULL ) 
	AND ( t1.openedDate <= #{srf.datacontext.openeddategt} OR #{srf.datacontext.openeddategt} is null ) 
GROUP BY
	t1.product) t2 on t1.id = t2.product
	where t1.deleted = '0'
		AND ( t2.PRODUCT = #{srf.datacontext.producteq} OR #{srf.datacontext.producteq}  IS NULL ) 
	AND ( t2.PROJECT = #{srf.datacontext.projecteq}  OR #{srf.datacontext.projecteq}  IS NULL )
```
### 产品创建bug占比(ProductCreateBug)<div id="BugStats_ProductCreateBug"></div>
```sql
SELECT t1.*,t2.productallbug, CONCAT(ROUND(t1.createbugcnt/(case when t2.productallbug = 0 or t2.productallbug is null then 1 else t2.productallbug end)*100,1),'%') from (
SELECT t1.openedBy,t1.productid,t1.productname,COUNT(1) as createbugcnt from (
SELECT t1.id ,t1.openedBy,t2.id as productid,t1.product,t2.`name` as productname from zt_bug t1
LEFT JOIN zt_product t2 on t2.id = t1.product
) t1 GROUP BY t1.openedBy,t1.productid  )t1
LEFT JOIN ( select t1.productid,t1.productname,count(1) as productallbug from (
SELECT t1.id ,t1.openedBy,t2.id as productid,t1.product,t2.`name` as productname from zt_bug t1
LEFT JOIN zt_product t2 on t2.id = t1.product
) t1 GROUP BY t1.productid ) t2 on t2.productid = t1.productid HAVING t1.openedBy = #{srf.sessioncontext.srfloginname}
```
### 项目bug状态统计(ProjectBugStatusCount)<div id="BugStats_ProjectBugStatusCount"></div>
```sql
SELECT
	t1.project,
	t1.projectname,
		sum( IF ( t1.`status` = 'resolved', t1.ss, 0 ) ) AS bugresolved,
		sum( IF ( t1.`status` = 'closed', t1.ss, 0 ) ) AS bugclosed,
		sum( IF ( t1.`status` = 'active', t1.ss, 0 ) ) AS bugactive,
	count(1) as bugcnt
FROM
	(
	SELECT
		t1.`status`,
		t1.project,
		t11.`name` AS projectname,
		1 AS ss,
		t11.deleted
	FROM
		zt_bug t1
		LEFT JOIN zt_project t11 ON t1.project = t11.id 
		where t1.deleted = '0' and t1.project <> 0
	) t1 
GROUP BY
	t1.project,
	t1.projectname
```
### 默认（全部数据）(VIEW)<div id="BugStats_View"></div>
```sql
SELECT
t1.`ASSIGNEDTO`,
0 AS `BUGBYDESIGN`,
0 AS `BUGCNT`,
0 AS `BUGDUPLICATE`,
0% AS `BUGEFFICIENT`,
0 AS `BUGEXTERNAL`,
0 AS `BUGFIXED`,
0 AS `BUGNOTREPRO`,
0 AS `BUGPOSTPONED`,
0 AS `BUGTOSTORY`,
0 AS `BUGTOTAL`,
0 AS `BUGWILLNOTFIX`,
0 AS `BUGWJJ`,
t1.`ID`,
t1.`OPENEDBY`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t2.`PROJECTNAME`,
t1.`RESOLVEDBY`,
t1.`TITLE`
FROM `zt_bug` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 

```

# **版本**(ZT_BUILD)

### Bug产品版本(BugProductBuild)<div id="Build_BugProductBuild"></div>
```sql
select t1.* from (select 0 as `BRANCH`,
	'' as `BUILDER`,
	null as `DATE`,
	'0' AS `DELETED`,
	'' AS `FILEPATH`,
	100000000 AS `ID`,
	'trunk' AS `IDs`,
	'主干' AS `NAME`,
	null AS `PRODUCT`,
	'' AS `PRODUCTNAME`,
	null as `PROJECT`,
	'' AS `SCMPATH`  from dual 
union all

SELECT
	t1.`BRANCH`,
	t1.`BUILDER`,
	t1.`DATE`,
	t1.`DELETED`,
	t1.`FILEPATH`,
	t1.`ID`,
	t1.`ID` AS `IDS`,
	(case when (select tt.`name` from zt_release tt where tt.build = t1.id and tt.deleted = '0'  order by tt.date desc LIMIT 0,1) is null then t1.`name` else (select tt.`name` from zt_release tt where tt.build = t1.id and tt.deleted = '0' order by tt.date desc LIMIT 0,1) end) AS `NAME`,
	t1.`PRODUCT`,
	t11.`NAME` AS `PRODUCTNAME`,
	t1.`PROJECT`,
	t1.`SCMPATH` 
FROM
	`zt_build` t1
	LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID where not EXISTS(select 1 from zt_release t2 where t2.build = t1.id and t2.`status` = 'terminate')) t1
WHERE t1.DELETED = '0' 
( t1.`PRODUCT` = ${srfdatacontext('bugproduct','{"defname":"PRODUCT","dename":"ZT_BUILD"}')}  OR  t1.`PRODUCT` = ${srfwebcontext('bugproduct','{"defname":"PRODUCT","dename":"ZT_BUILD"}')} 
 or t1.`PRODUCT` is null) 
( t1.`PROJECT` = ${srfdatacontext('bugproject','{"defname":"PRODUCT","dename":"ZT_BUILD"}')}  
 or t1.`PROJECT` is null or ${srfdatacontext('bugproject','{"defname":"PRODUCT","dename":"ZT_BUILD"}')} is null) 

```
### 产品版本(CurProduct)<div id="Build_CurProduct"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`BUILDER`,
t1.BUILDER AS `BUILDERPK`,
t1.`DATE`,
t1.`DELETED`,
t1.`FILEPATH`,
t1.`ID`,
t1.`NAME`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t1.`SCMPATH`
FROM `zt_build` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 

WHERE t1.DELETED = '0' 
( ( t1.`PRODUCT` = ${srfdatacontext('product','{"defname":"PRODUCT","dename":"ZT_BUILD"}')}  OR  t1.`PRODUCT` = ${srfwebcontext('product','{"defname":"PRODUCT","dename":"ZT_BUILD"}')} ) ) 

```
### DEFAULT(DEFAULT)<div id="Build_Default"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`BUILDER`,
t1.BUILDER AS `BUILDERPK`,
t1.`DATE`,
t1.`DELETED`,
t1.`FILEPATH`,
t1.`ID`,
t1.`NAME`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t1.`SCMPATH`
FROM `zt_build` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 

WHERE t1.DELETED = '0' 

```
### 测试版本(TestBuild)<div id="Build_TestBuild"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`BUILDER`,
t1.`DATE`,
t1.`DELETED`,
t1.`FILEPATH`,
t1.`ID`,
case when (select t2.`name` from zt_release t2 where t2.build = t1.id and t2.deleted = '0' ORDER BY t2.date desc LIMIT 0,1) is null then t1.`name` else (select t2.`name` from zt_release t2 where t2.build = t1.id and t2.deleted = '0' ORDER BY t2.date desc LIMIT 0,1) end as `NAME`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t1.`SCMPATH`
FROM `zt_build` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID
WHERE t1.DELETED = '0' 

```
### 测试轮次(TestRounds)<div id="Build_TestRounds"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`BUILDER`,
t1.BUILDER AS `BUILDERPK`,
t1.`DATE`,
t1.`DELETED`,
t1.`FILEPATH`,
t1.`ID`,
t1.`NAME`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t1.`SCMPATH`
FROM `zt_build` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 

WHERE t1.DELETED = '0' 
( FIND_IN_SET(t1.id,(select tt.builds from zt_testreport tt where tt.id = ${srfwebcontext('srfparentkey','{"defname":"ID","dename":"ZT_BUILD"}')})) ) 

```
### 更新日志(UpdateLog)<div id="Build_UpdateLog"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`BUGS`,
t1.`BUILDER`,
t1.`DATE`,
t1.`DELETED`,
t1.`DESC`,
t1.`FILEPATH`,
t1.`ID`,
CONCAT_WS('','PMS ',t1.`name`,' 主要更新') as `NAME`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t1.`SCMPATH`,
t1.`STORIES`
FROM `zt_build` t1 
LEFT JOIN zt_product t11 ON t1.PRODUCT = t11.ID
WHERE t1.deleted = '0' 

```
### 默认（全部数据）(VIEW)<div id="Build_View"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`BUGS`,
t1.`BUILDER`,
t1.BUILDER AS `BUILDERPK`,
t1.`DATE`,
t1.`DELETED`,
t1.`DESC`,
t1.`FILEPATH`,
t1.`ID`,
t1.`NAME`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t1.`SCMPATH`,
t1.`STORIES`
FROM `zt_build` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 

WHERE t1.DELETED = '0' 

```

# **burn**(ZT_BURN)

### DEFAULT(DEFAULT)<div id="Burn_Default"></div>
```sql
SELECT
t1.`CONSUMED`,
t1.`DATE`,
t1.`ESTIMATE`,
concat(t1.`PROJECT`,'__',t1.`TASK`,'__',t1.`DATE`) AS `ID`,
1 AS `ISWEEKEND`,
t1.`LEFT`,
t1.`PROJECT`,
t1.`TASK`
FROM `zt_burn` t1 

```
### 燃尽图预计（含周末）(ESTIMATEANDLEFT)<div id="Burn_ESTIMATEANDLEFT"></div>
```sql
SELECT
t1.`CONSUMED`,
t1.`DATE`,
ROUND((select estimate from zt_burn where date=t2.begin and project=t1.project)*(1-(DATEDIFF(t1.date,t2.`begin`))/(DATEDIFF(t2.`end`,t2.`begin`))),1) AS `ESTIMATE`,
t1.`LEFT`,
t1.`PROJECT`,
DATE_FORMAT(t1.date,'%w')  as ISWEEKEND
FROM `zt_burn` t1  
LEFT JOIN zt_project t2 on t1.project=t2.id
```
### 默认（全部数据）(VIEW)<div id="Burn_View"></div>
```sql
SELECT
t1.`CONSUMED`,
t1.`DATE`,
t1.`ESTIMATE`,
concat(t1.`PROJECT`,'__',t1.`TASK`,'__',t1.`DATE`) AS `ID`,
1 AS `ISWEEKEND`,
t1.`LEFT`,
t1.`PROJECT`,
t1.`TASK`
FROM `zt_burn` t1 

```

# **测试用例**(ZT_CASE)

### 批量新建用例(BatchNew)<div id="Case_BatchNew"></div>
```sql
SELECT
t1.`AUTO`,
t1.`BRANCH`,
t1.`COLOR`,
t1.`DELETED`,
t1.`FRAME`,
t1.`FREQUENCY`,
t1.`FROMBUG`,
t1.`FROMCASEID`,
t1.`FROMCASEVERSION`,
t1.`HOWRUN`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LASTRUNDATE`,
t1.`LASTRUNNER`,
t1.`LASTRUNRESULT`,
(case when t1.`LASTRUNRESULT` = '' or t1.`LASTRUNRESULT` is null then 'no' else t1.`LASTRUNRESULT` end) AS `LASTRUNRESULT1`,
t1.`LIB`,
t11.`NAME` AS `LIBNAME`,
t1.`LINKCASE`,
t1.`MODULE`,
t31.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t31.path ) AND tt.type = 'story'  GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`ORDER`,
t1.`PATH`,
t1.`PRI`,
t1.`PRODUCT`,
t41.`NAME` AS `PRODUCTNAME`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID` ) AS `RESULTCNT`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID`  and caseResult in('fail','blocked') ) AS `RESULTFALICNT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SCRIPTEDBY`,
t1.`SCRIPTEDDATE`,
t1.`SCRIPTLOCATION`,
t1.`SCRIPTSTATUS`,
t1.`STAGE`,
t1.`STATUS`,
(case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`,
(SELECT COUNT(1) FROM zt_casestep WHERE `case` = t1.`ID` AND version = t1.`VERSION` ) AS `STEPCNT`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TITLE`,
(SELECT COUNT(1) FROM zt_bug WHERE `case` = t1.`ID` ) AS `TOBUGCNT`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_case` t1 
LEFT JOIN `zt_testsuite` t11 ON t1.`LIB` = t11.`ID` 
LEFT JOIN `zt_story` t21 ON t1.`STORY` = t21.`ID` 
LEFT JOIN `zt_module` t31 ON t1.`MODULE` = t31.`ID` 
LEFT JOIN `zt_product` t41 ON t1.`PRODUCT` = t41.`ID` 

WHERE t1.DELETED = '0' 
( 1<>1 ) 

```
### 累计创建的用例(CurOpenedCase)<div id="Case_CurOpenedCase"></div>
```sql
SELECT
t1.`AUTO`,
t1.`BRANCH`,
t1.`COLOR`,
t1.`DELETED`,
t1.`FRAME`,
t1.`FREQUENCY`,
t1.`FROMBUG`,
t1.`FROMCASEID`,
t1.`FROMCASEVERSION`,
t1.`HOWRUN`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LASTRUNDATE`,
t1.`LASTRUNNER`,
t1.`LASTRUNRESULT`,
(case when t1.`LASTRUNRESULT` = '' or t1.`LASTRUNRESULT` is null then 'no' else t1.`LASTRUNRESULT` end) AS `LASTRUNRESULT1`,
t1.`LIB`,
t11.`NAME` AS `LIBNAME`,
t1.`LINKCASE`,
t1.`MODULE`,
t31.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t31.path ) AND tt.type = 'story'  GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`ORDER`,
t1.`PATH`,
t1.`PRI`,
t1.`PRODUCT`,
t41.`NAME` AS `PRODUCTNAME`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID` ) AS `RESULTCNT`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID`  and caseResult in('fail','blocked') ) AS `RESULTFALICNT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SCRIPTEDBY`,
t1.`SCRIPTEDDATE`,
t1.`SCRIPTLOCATION`,
t1.`SCRIPTSTATUS`,
t1.`STAGE`,
t1.`STATUS`,
(case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`,
(SELECT COUNT(1) FROM zt_casestep WHERE `case` = t1.`ID` AND version = t1.`VERSION` ) AS `STEPCNT`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TITLE`,
(SELECT COUNT(1) FROM zt_bug WHERE `case` = t1.`ID` ) AS `TOBUGCNT`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_case` t1 
LEFT JOIN `zt_testsuite` t11 ON t1.`LIB` = t11.`ID` 
LEFT JOIN `zt_story` t21 ON t1.`STORY` = t21.`ID` 
LEFT JOIN `zt_module` t31 ON t1.`MODULE` = t31.`ID` 
LEFT JOIN `zt_product` t41 ON t1.`PRODUCT` = t41.`ID` 

WHERE t1.DELETED = '0' 
( DATE_FORMAT( t1.openedDate, '%Y' ) = DATE_FORMAT(now(), '%Y' )  AND  t1.`OPENEDBY` =  ${srfsessioncontext('srfloginname','{"defname":"OPENEDBY","dename":"ZT_CASE"}')} ) 

```
### 套件关联用例(CurSuite)<div id="Case_CurSuite"></div>
```sql
SELECT
t1.`AUTO`,
t1.`BRANCH`,
t1.`COLOR`,
t1.`DELETED`,
t1.`FRAME`,
t1.`FREQUENCY`,
t1.`FROMBUG`,
t1.`FROMCASEID`,
t1.`FROMCASEVERSION`,
t1.`HOWRUN`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LASTRUNDATE`,
t1.`LASTRUNNER`,
t1.`LASTRUNRESULT`,
(case when t1.`LASTRUNRESULT` = '' or t1.`LASTRUNRESULT` is null then 'no' else t1.`LASTRUNRESULT` end) AS `LASTRUNRESULT1`,
t1.`LIB`,
t11.`NAME` AS `LIBNAME`,
t1.`LINKCASE`,
t1.`MODULE`,
t31.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t31.path ) AND tt.type = 'story'  GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`ORDER`,
t1.`PATH`,
t1.`PRI`,
t1.`PRODUCT`,
t41.`NAME` AS `PRODUCTNAME`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID` ) AS `RESULTCNT`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID`  and caseResult in('fail','blocked') ) AS `RESULTFALICNT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SCRIPTEDBY`,
t1.`SCRIPTEDDATE`,
t1.`SCRIPTLOCATION`,
t1.`SCRIPTSTATUS`,
t1.`STAGE`,
t1.`STATUS`,
(case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`,
(SELECT COUNT(1) FROM zt_casestep WHERE `case` = t1.`ID` AND version = t1.`VERSION` ) AS `STEPCNT`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TITLE`,
(SELECT COUNT(1) FROM zt_bug WHERE `case` = t1.`ID` ) AS `TOBUGCNT`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_case` t1 
LEFT JOIN `zt_testsuite` t11 ON t1.`LIB` = t11.`ID` 
LEFT JOIN `zt_story` t21 ON t1.`STORY` = t21.`ID` 
LEFT JOIN `zt_module` t31 ON t1.`MODULE` = t31.`ID` 
LEFT JOIN `zt_product` t41 ON t1.`PRODUCT` = t41.`ID` 

WHERE EXISTS(SELECT * FROM `zt_suitecase` t51 
 WHERE 
 t1.`ID` = t51.`CASE`  AND  ( t51.`SUITE` = ${srfdatacontext('srfparentkey','{"defname":"SUITE","dename":"ZT_SUITECASE"}')} ) ) 
t1.DELETED = '0' 

```
### 测试单关联用例(CurTestTask)<div id="Case_CurTestTask"></div>
```sql
SELECT
t1.`AUTO`,
t1.`BRANCH`,
t1.`COLOR`,
t1.`DELETED`,
t1.`FRAME`,
t1.`FREQUENCY`,
t1.`FROMBUG`,
t1.`FROMCASEID`,
t1.`FROMCASEVERSION`,
t1.`HOWRUN`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'case' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t41.`LASTRUNDATE`,
t41.`LASTRUNNER`,
t41.`LASTRUNRESULT`,
(case when (select count(1) from zt_testresult tt where tt.`case` = t1.id and tt.run = t41.id and tt.caseResult = 'fail') > 0 then 'fail' when t41.`LASTRUNRESULT` = '' or t41.`LASTRUNRESULT` is null then 'no' else t41.`LASTRUNRESULT` end) as  `LASTRUNRESULT1`,
t1.`LIB`,
t1.`LINKCASE`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`ORDER`,
t1.`PATH`,
t1.`PRI`,
t1.`PRODUCT`,
t31.`NAME` AS `PRODUCTNAME`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SCRIPTEDBY`,
t1.`SCRIPTEDDATE`,
t1.`SCRIPTLOCATION`,
t1.`SCRIPTSTATUS`,
t1.`STAGE`,
t1.`STATUS`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TYPE`,
t41.task as task,
t1.`VERSION`,
t41.assignedTo as assignedTo,
(case when t1.version > t41.version then 'casechange' else t41.`status` end) as `status1`
FROM `zt_case` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.STORY = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_testrun t41 ON t1.id = t41.`case`
WHERE t41.`TASK` = ${srfdatacontext('srfparentkey','{"defname":"TASK","dename":"ZT_TESTRUN"}')} 
t1.DELETED = '0' 

```
### DEFAULT(DEFAULT)<div id="Case_Default"></div>
```sql
SELECT
t1.`AUTO`,
t1.`BRANCH`,
t1.`COLOR`,
t1.`DELETED`,
t1.`FRAME`,
t1.`FREQUENCY`,
t1.`FROMBUG`,
t1.`FROMCASEID`,
t1.`FROMCASEVERSION`,
t1.`HOWRUN`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'case' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LASTRUNDATE`,
t1.`LASTRUNNER`,
t1.`LASTRUNRESULT`,
(case when t1.`LASTRUNRESULT` = '' or t1.`LASTRUNRESULT` is null then 'no' else t1.`LASTRUNRESULT` end) AS `LASTRUNRESULT1`,
t1.`LIB`,
t41.`NAME` AS `LIBNAME`,
t1.`LINKCASE`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`ORDER`,
t1.`PATH`,
t1.`PRI`,
t1.`PRODUCT`,
t31.`NAME` AS `PRODUCTNAME`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID` ) AS `RESULTCNT`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID`  and caseResult in('fail','blocked') ) AS `RESULTFALICNT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SCRIPTEDBY`,
t1.`SCRIPTEDDATE`,
t1.`SCRIPTLOCATION`,
t1.`SCRIPTSTATUS`,
t1.`STAGE`,
t1.`STATUS`,
(case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`,
(SELECT COUNT(1) FROM zt_casestep WHERE `case` = t1.`ID` AND version = t1.`VERSION` ) AS `STEPCNT`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TITLE`,
(SELECT COUNT(1) FROM zt_bug WHERE `case` = t1.`ID` ) AS `TOBUGCNT`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_case` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.STORY = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_testsuite t41 ON t1.LIB = t41.ID
WHERE t1.DELETED = '0' 

```
### ES批量的导入(ESBulk)<div id="Case_ESBulk"></div>
```sql
SELECT
	t1.id,
	t1.title,
	t1.precondition,
	t1.PRODUCT,
	t1.deleted
FROM
	zt_case t1
```
### 测试报告关联-按模块(ModuleRePortCase)<div id="Case_ModuleRePortCase"></div>
```sql
SELECT 
t1.`AUTO`,
t1.`BRANCH`,
t1.`COLOR`,
t1.`DELETED`,
t1.`FRAME`,
t1.`FREQUENCY`,
t1.`FROMBUG`,
t1.`FROMCASEID`,
t1.`FROMCASEVERSION`,
t1.`HOWRUN`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.LASTRUNDATE AS `LASTRUNDATE`,
t1.`LASTRUNNER`,
t1.LASTRUNRESULT AS `LASTRUNRESULT`,
(case when t1.`LASTRUNRESULT` = '' or t1.`LASTRUNRESULT` is null then 'no' else t1.`LASTRUNRESULT` end) AS `LASTRUNRESULT1`,
t1.`LIB`,
t41.`NAME` AS `LIBNAME`,
t1.`LINKCASE`,
t1.`MODULE`,
	CONCAT(
	'/',
 case when	(
SELECT
	GROUP_CONCAT( tt.NAME SEPARATOR '/' ) 
FROM
	zt_module tt 
WHERE
	FIND_IN_SET( tt.id, t11.path ) 
	AND tt.type = 'story' 
GROUP BY
	tt.root 
	LIMIT 0,1
	) is not null then (
SELECT
	GROUP_CONCAT( tt.NAME SEPARATOR '/' ) 
FROM
	zt_module tt 
WHERE
	FIND_IN_SET( tt.id, t11.path ) 
	AND tt.type = 'story' 
GROUP BY
	tt.root 
	LIMIT 0,1
	) when t11.`name` is null then '' else t11.`name` end
	) AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`ORDER`,
t1.`PATH`,
t1.`PRI`,
t1.`PRODUCT`,
t31.`NAME` AS `PRODUCTNAME`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID` ) AS `RESULTCNT`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID`  and caseResult in('fail','blocked') ) AS `RESULTFALICNT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SCRIPTEDBY`,
t1.`SCRIPTEDDATE`,
t1.`SCRIPTLOCATION`,
t1.`SCRIPTSTATUS`,
t1.`STAGE`,
t1.`STATUS`,
(case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`,
(SELECT COUNT(1) FROM zt_casestep WHERE `case` = t1.`ID` AND version = t1.`VERSION` ) AS `STEPCNT`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TITLE`,
(SELECT COUNT(1) FROM zt_bug WHERE `case` = t1.`ID` ) AS `TOBUGCNT`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_case` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.STORY = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_testsuite t41 ON t1.LIB = t41.ID
LEFT JOIN zt_testrun t51 on t51.`case` = t1.id
LEFT JOIN zt_testreport t61 on t51.task = t61.tasks
WHERE t1.DELETED = '0' 
t61.id = ${srfwebcontext('srfparentkey','{"defname":"PATH","dename":"ZT_CASE"}')} 

```
### 测试报告关联-按模块-条目(ModuleRePortCaseEntry)<div id="Case_ModuleRePortCaseEntry"></div>
```sql
select t1.lastRunResult1,t1.RESULTCNT as RESULTCNT, t1.PRODUCT,CONCAT(ROUND(t1.RESULTCNT / t1.sss * 100, 2),'','%') as TASK from( select t1.PRODUCT,t1.lastRunResult1,t1.resultcnt,(SELECT
	COUNT( 1 ) 
FROM
	zt_case ttt 
LEFT JOIN zt_testrun t51 on t51.`case` = ttt.id
LEFT JOIN zt_testreport t61 on t51.task = t61.tasks
WHERE
	t61.id = ${srfwebcontext('srfparentkey','{"defname":"PATH","dename":"ZT_CASE"}')}
	AND ttt.DELETED = '0' ) as sss from ( select t1.PRODUCT,t1.lastRunResult1,COUNT(t1.lastRunResult1) as RESULTCNT from ( SELECT 
	CONCAT(
	'/',
 case when	(
SELECT
	GROUP_CONCAT( tt.NAME SEPARATOR '/' ) 
FROM
	zt_module tt 
WHERE
	FIND_IN_SET( tt.id, t11.path ) 
	AND tt.type = 'story' 
GROUP BY
	tt.root 
	LIMIT 0,1
	) is not null then (
SELECT
	GROUP_CONCAT( tt.NAME SEPARATOR '/' ) 
FROM
	zt_module tt 
WHERE
	FIND_IN_SET( tt.id, t11.path ) 
	AND tt.type = 'story' 
GROUP BY
	tt.root 
	LIMIT 0,1
	) when t11.`name` is null then '' else t11.`name` end
	) AS `lastRunResult1`,
t1.`PRODUCT`
FROM `zt_case` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_testrun t51 on t51.`case` = t1.id
LEFT JOIN zt_testreport t61 on t51.task = t61.tasks
where
t61.id = ${srfwebcontext('srfparentkey','{"defname":"PATH","dename":"ZT_CASE"}')} 
	AND t1.DELETED = '0' ) t1 GROUP BY t1.lastRunResult1 ) t1) t1
```
### 项目报告关联-按模块(ModuleRePortCase_Project)<div id="Case_ModuleRePortCase_Project"></div>
```sql
SELECT 
t1.`AUTO`,
t1.`BRANCH`,
t1.`COLOR`,
t1.`DELETED`,
t1.`FRAME`,
t1.`FREQUENCY`,
t1.`FROMBUG`,
t1.`FROMCASEID`,
t1.`FROMCASEVERSION`,
t1.`HOWRUN`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.LASTRUNDATE AS `LASTRUNDATE`,
t1.`LASTRUNNER`,
t1.LASTRUNRESULT AS `LASTRUNRESULT`,
(case when t1.`LASTRUNRESULT` = '' or t1.`LASTRUNRESULT` is null then 'no' else t1.`LASTRUNRESULT` end) AS `LASTRUNRESULT1`,
t1.`LIB`,
t41.`NAME` AS `LIBNAME`,
t1.`LINKCASE`,
t1.`MODULE`,
	CONCAT(
	'/',
 case when	(
SELECT
	GROUP_CONCAT( tt.NAME SEPARATOR '/' ) 
FROM
	zt_module tt 
WHERE
	FIND_IN_SET( tt.id, t11.path ) 
	AND tt.type = 'story' 
GROUP BY
	tt.root 
	LIMIT 0,1
	) is not null then (
SELECT
	GROUP_CONCAT( tt.NAME SEPARATOR '/' ) 
FROM
	zt_module tt 
WHERE
	FIND_IN_SET( tt.id, t11.path ) 
	AND tt.type = 'story' 
GROUP BY
	tt.root 
	LIMIT 0,1
	) when t11.`name` is null then '' else t11.`name` end
	) AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`ORDER`,
t1.`PATH`,
t1.`PRI`,
t1.`PRODUCT`,
t31.`NAME` AS `PRODUCTNAME`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID` ) AS `RESULTCNT`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID`  and caseResult in('fail','blocked') ) AS `RESULTFALICNT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SCRIPTEDBY`,
t1.`SCRIPTEDDATE`,
t1.`SCRIPTLOCATION`,
t1.`SCRIPTSTATUS`,
t1.`STAGE`,
t1.`STATUS`,
(case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`,
(SELECT COUNT(1) FROM zt_casestep WHERE `case` = t1.`ID` AND version = t1.`VERSION` ) AS `STEPCNT`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TITLE`,
(SELECT COUNT(1) FROM zt_bug WHERE `case` = t1.`ID` ) AS `TOBUGCNT`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_case` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.STORY = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_testsuite t41 ON t1.LIB = t41.ID
LEFT JOIN zt_testrun t51 on t51.`case` = t1.id
LEFT JOIN zt_testreport t61 on FIND_IN_SET(t51.task, t61.tasks)
WHERE t1.DELETED = '0' 
t61.id = ${srfwebcontext('srfparentkey','{"defname":"PATH","dename":"ZT_CASE"}')} 

```
### 用例我得收藏(MyFavorite)<div id="Case_MyFavorite"></div>
```sql
SELECT
t1.`AUTO`,
t1.`BRANCH`,
t1.`COLOR`,
t1.`DELETED`,
t1.`FRAME`,
t1.`FREQUENCY`,
t1.`FROMBUG`,
t1.`FROMCASEID`,
t1.`FROMCASEVERSION`,
t1.`HOWRUN`,
t1.`ID`,
1 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LASTRUNDATE`,
t1.`LASTRUNNER`,
t1.`LASTRUNRESULT`,
(case when t1.`LASTRUNRESULT` = '' or t1.`LASTRUNRESULT` is null then 'no' else t1.`LASTRUNRESULT` end) AS `LASTRUNRESULT1`,
t1.`LIB`,
t41.`NAME` AS `LIBNAME`,
t1.`LINKCASE`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t11.path ) AND tt.type = 'story'  GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`ORDER`,
t1.`PATH`,
t1.`PRI`,
t1.`PRODUCT`,
t31.`NAME` AS `PRODUCTNAME`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID` ) AS `RESULTCNT`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID`  and caseResult in('fail','blocked') ) AS `RESULTFALICNT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SCRIPTEDBY`,
t1.`SCRIPTEDDATE`,
t1.`SCRIPTLOCATION`,
t1.`SCRIPTSTATUS`,
t1.`STAGE`,
t1.`STATUS`,
(case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`,
(SELECT COUNT(1) FROM zt_casestep WHERE `case` = t1.`ID` AND version = t1.`VERSION` ) AS `STEPCNT`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TITLE`,
(SELECT COUNT(1) FROM zt_bug WHERE `case` = t1.`ID` ) AS `TOBUGCNT`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_case` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.STORY = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_testsuite t41 ON t1.LIB = t41.ID 

WHERE t1.DELETED = '0' 
( t1.id in (select t.OBJECTID from T_IBZ_FAVORITES t where t.type = 'case' and t.account = #{srf.sessioncontext.srfloginname})  AND  t1.DELETED = '0' ) 

```
### 套件关联用例(NotCurTestSuite)<div id="Case_NotCurTestSuite"></div>
```sql
SELECT
	t1.`AUTO`,
	t1.`BRANCH`,
	t1.`COLOR`,
	t1.`DELETED`,
	t1.`FRAME`,
	t1.`FREQUENCY`,
	t1.`FROMBUG`,
	t1.`FROMCASEID`,
	t1.`FROMCASEVERSION`,
	t1.`HOWRUN`,
	t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'case' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
	t1.`KEYWORDS`,
	t1.`LASTEDITEDBY`,
	t1.`LASTEDITEDDATE`,
	t1.`LASTRUNDATE`,
	t1.`LASTRUNNER`,
	t1.`LASTRUNRESULT`,
(case when t1.`LASTRUNRESULT` = '' or t1.`LASTRUNRESULT` is null then 'no' else t1.`LASTRUNRESULT` end) as  `LASTRUNRESULT1`,
	t1.`LIB`,
	t1.`LINKCASE`,
	t1.`MODULE`,
	t11.`NAME` AS `MODULENAME`,
	t1.`OPENEDBY`,
	t1.`OPENEDDATE`,
	t1.`ORDER`,
	t1.`PATH`,
	t1.`PRI`,
	t1.`PRODUCT`,
	t31.`NAME` AS `PRODUCTNAME`,
	( SELECT COUNT( 1 ) FROM zt_testresult WHERE `case` = t1.`ID` ) AS `RESULTCNT`,
	( SELECT COUNT( 1 ) FROM zt_testresult WHERE `case` = t1.`ID` AND caseResult IN ( 'fail', 'blocked' ) ) AS `RESULTFALICNT`,
	t1.`REVIEWEDBY`,
	t1.`REVIEWEDDATE`,
	t1.`SCRIPTEDBY`,
	t1.`SCRIPTEDDATE`,
	t1.`SCRIPTLOCATION`,
	t1.`SCRIPTSTATUS`,
	t1.`STAGE`,
	t1.`STATUS`,
	( CASE WHEN t1.storyVersion < t21.version AND t21.`status` <> 'changed' THEN 'storychange' ELSE t1.`status` END ) AS `STATUS1`,
	( SELECT COUNT( 1 ) FROM zt_casestep WHERE `case` = t1.`ID` AND version = t1.`VERSION` ) AS `STEPCNT`,
	t1.`STORY`,
	t21.`TITLE` AS `STORYNAME`,
	t1.`STORYVERSION`,
	t1.`SUBSTATUS`,
	t1.`TITLE`,
	( SELECT COUNT( 1 ) FROM zt_bug WHERE `case` = t1.`ID` ) AS `TOBUGCNT`,
	t1.`TYPE`,
	t1.`VERSION` 
FROM
	`zt_case` t1
	LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID
	LEFT JOIN zt_story t21 ON t1.STORY = t21.ID
	LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID
WHERE NOT(EXISTS(SELECT * FROM zt_suitecase t41 
 WHERE 
 t1.ID = t41.`CASE`  AND  ( t41.`suite` = ${srfwebcontext('suite','{"defname":"TASK","dename":"ZT_TESTRUN"}')} ) )) 
t1.`deleted` = '0' 

```
### 测试单关联用例(NotCurTestTask)<div id="Case_NotCurTestTask"></div>
```sql
SELECT
t1.`AUTO`,
t1.`BRANCH`,
t1.`COLOR`,
t1.`DELETED`,
t1.`FRAME`,
t1.`FREQUENCY`,
t1.`FROMBUG`,
t1.`FROMCASEID`,
t1.`FROMCASEVERSION`,
t1.`HOWRUN`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'case' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LASTRUNDATE`,
t1.`LASTRUNNER`,
t1.`LASTRUNRESULT`,
(case when t1.`LASTRUNRESULT` = '' or t1.`LASTRUNRESULT` is null then 'no' else t1.`LASTRUNRESULT` end) as  `LASTRUNRESULT1`,
t1.`LIB`,
t1.`LINKCASE`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`ORDER`,
t1.`PATH`,
t1.`PRI`,
t1.`PRODUCT`,
t31.`NAME` AS `PRODUCTNAME`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID` ) AS `RESULTCNT`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID`  and caseResult in('fail','blocked') ) AS `RESULTFALICNT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SCRIPTEDBY`,
t1.`SCRIPTEDDATE`,
t1.`SCRIPTLOCATION`,
t1.`SCRIPTSTATUS`,
t1.`STAGE`,
t1.`STATUS`,
(case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`,
(SELECT COUNT(1) FROM zt_casestep WHERE `case` = t1.`ID` AND version = t1.`VERSION` ) AS `STEPCNT`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TITLE`,
(SELECT COUNT(1) FROM zt_bug WHERE `case` = t1.`ID` ) AS `TOBUGCNT`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_case` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.STORY = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 

WHERE NOT(EXISTS(SELECT * FROM zt_testrun t41 
 WHERE 
 t1.ID = t41.`CASE`  AND  ( t41.`task` = ${srfwebcontext('task','{"defname":"TASK","dename":"ZT_TESTRUN"}')} ) )) 
t1.DELETED = '0' 
t1.product in (select tt.product from zt_testtask t left join zt_projectproduct tt on tt.project = t.project where t.id = ${srfwebcontext('task','{"defname":"TASK","dename":"ZT_TESTRUN"}')}) 

```
### 测试单关联用例（项目关联）(NotCurTestTaskProject)<div id="Case_NotCurTestTaskProject"></div>
```sql
SELECT
t1.`AUTO`,
t1.`BRANCH`,
t1.`COLOR`,
t1.`DELETED`,
t1.`FRAME`,
t1.`FREQUENCY`,
t1.`FROMBUG`,
t1.`FROMCASEID`,
t1.`FROMCASEVERSION`,
t1.`HOWRUN`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'case' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LASTRUNDATE`,
t1.`LASTRUNNER`,
t1.`LASTRUNRESULT`,
(case when t1.`LASTRUNRESULT` = '' or t1.`LASTRUNRESULT` is null then 'no' else t1.`LASTRUNRESULT` end) as  `LASTRUNRESULT1`,
t1.`LIB`,
t1.`LINKCASE`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`ORDER`,
t1.`PATH`,
t1.`PRI`,
t1.`PRODUCT`,
t31.`NAME` AS `PRODUCTNAME`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID` ) AS `RESULTCNT`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID`  and caseResult in('fail','blocked') ) AS `RESULTFALICNT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SCRIPTEDBY`,
t1.`SCRIPTEDDATE`,
t1.`SCRIPTLOCATION`,
t1.`SCRIPTSTATUS`,
t1.`STAGE`,
t1.`STATUS`,
(case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`,
(SELECT COUNT(1) FROM zt_casestep WHERE `case` = t1.`ID` AND version = t1.`VERSION` ) AS `STEPCNT`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TITLE`,
(SELECT COUNT(1) FROM zt_bug WHERE `case` = t1.`ID` ) AS `TOBUGCNT`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_case` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.STORY = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 

WHERE NOT(EXISTS(SELECT * FROM zt_testrun t41 
 WHERE 
 t1.ID = t41.`CASE`  AND  ( t41.`task` = ${srfwebcontext('task','{"defname":"TASK","dename":"ZT_TESTRUN"}')} ) )) 
t1.DELETED = '0' 
t1.product in (select tt.product from zt_testtask t left join zt_projectproduct tt on tt.project = t.project where t.id = ${srfwebcontext('task','{"defname":"TASK","dename":"ZT_TESTRUN"}')}) 

```
### 测试报告关联用例(RePortCase)<div id="Case_RePortCase"></div>
```sql
SELECT
t1.`AUTO`,
t1.`BRANCH`,
t1.`COLOR`,
t1.`DELETED`,
t1.`FRAME`,
t1.`FREQUENCY`,
t1.`FROMBUG`,
t1.`FROMCASEID`,
t1.`FROMCASEVERSION`,
t1.`HOWRUN`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t51.`LASTRUNDATE` as LASTRUNDATE,
t51.`LASTRUNNER` as LASTRUNNER,
t51.`LASTRUNRESULT` as LASTRUNRESULT,
(case when t51.`LASTRUNRESULT` = '' or t51.`LASTRUNRESULT` is null then 'no' else t51.`LASTRUNRESULT` end) AS `LASTRUNRESULT1`,
t1.`LIB`,
t41.`NAME` AS `LIBNAME`,
t1.`LINKCASE`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`ORDER`,
t1.`PATH`,
t1.`PRI`,
t1.`PRODUCT`,
t31.`NAME` AS `PRODUCTNAME`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID` ) AS `RESULTCNT`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID`  and caseResult in('fail','blocked') ) AS `RESULTFALICNT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SCRIPTEDBY`,
t1.`SCRIPTEDDATE`,
t1.`SCRIPTLOCATION`,
t1.`SCRIPTSTATUS`,
t1.`STAGE`,
t1.`STATUS`,
t51.assignedTo as assignedTo,
(case when t1.version > t51.version then 'casechange' else t51.`status` end) as `status1`,
(SELECT COUNT(1) FROM zt_casestep WHERE `case` = t1.`ID` AND version = t1.`VERSION` ) AS `STEPCNT`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TITLE`,
(SELECT COUNT(1) FROM zt_bug WHERE `case` = t1.`ID` ) AS `TOBUGCNT`,
case when t1.`TYPE` = 'feature' then '功能测试' when t1.`TYPE` = 'performance' then '性能测试' when t1.`TYPE` = 'config' then '配置相关' when t1.`TYPE` = 'nstall' then '安装部署' when t1.`TYPE` = '	security' then '安全相关' when t1.`TYPE` = 'interface' then '接口测试' when t1.`TYPE` = 'unit' then '单元测试' when t1.`TYPE` = 'other' then '其他' end  as `TYPE`,
t1.`VERSION`
FROM `zt_case` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.STORY = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_testsuite t41 ON t1.LIB = t41.ID
LEFT JOIN zt_testrun t51 on t51.`case` = t1.id
LEFT JOIN zt_testreport t61 on t51.task = t61.tasks
WHERE t1.DELETED = '0' 
t61.id = ${srfwebcontext('srfparentkey','{"defname":"PATH","dename":"ZT_CASE"}')} 

```
### 测试报告关联用例-条目(RePortCaseEntry)<div id="Case_RePortCaseEntry"></div>
```sql
select t1.lastRunResult1,t1.RESULTCNT as RESULTCNT, t1.PRODUCT,CONCAT(ROUND(t1.RESULTCNT / t1.sss * 100, 2),'','%') as TASK from (SELECT
	t1.PRODUCT,
	t1.lastRunResult1,
	t1.RESULTCNT,
	(
SELECT
	COUNT( 1 ) 
FROM
	zt_case ttt 
LEFT JOIN zt_testrun t51 on t51.`case` = ttt.id
LEFT JOIN zt_testreport t61 on t51.task = t61.tasks
WHERE
	t61.id = ${srfwebcontext('srfparentkey','{"defname":"PATH","dename":"ZT_CASE"}')} 
	AND ttt.DELETED = '0' 
	) AS sss 
FROM
	(
SELECT
	t1.PRODUCT,
	t1.lastRunResult1,
	count( t1.lastRunResult1 ) AS RESULTCNT 
FROM
	(
SELECT
	t1.`PRODUCT`,
CASE
	
	WHEN t1.`TYPE` = 'feature' THEN
	'功能测试' 
	WHEN t1.`TYPE` = 'performance' THEN
	'性能测试' 
	WHEN t1.`TYPE` = 'config' THEN
	'配置相关' 
	WHEN t1.`TYPE` = 'nstall' THEN
	'安装部署' 
	WHEN t1.`TYPE` = '	security' THEN
	'安全相关' 
	WHEN t1.`TYPE` = 'interface' THEN
	'接口测试' 
	WHEN t1.`TYPE` = 'unit' THEN
	'单元测试' 
	WHEN t1.`TYPE` = 'other' THEN
	'其他' 
	END AS `lastRunResult1` 
FROM
	`zt_case` t1 
        LEFT JOIN zt_testrun t51 on t51.`case` = t1.id
        LEFT JOIN zt_testreport t61 on t51.task = t61.tasks
WHERE
	t61.id = ${srfwebcontext('srfparentkey','{"defname":"PATH","dename":"ZT_CASE"}')}
	AND t1.DELETED = '0' 
	) t1 
GROUP BY
	t1.lastRunResult1 
) t1) t1
```
### 项目报告关联用例-关联用例(RePortCase_Project)<div id="Case_RePortCase_Project"></div>
```sql
SELECT DISTINCT
t1.`AUTO`,
t1.`BRANCH`,
t1.`COLOR`,
t1.`DELETED`,
t1.`FRAME`,
t1.`FREQUENCY`,
t1.`FROMBUG`,
t1.`FROMCASEID`,
t1.`FROMCASEVERSION`,
t1.`HOWRUN`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LASTRUNDATE`,
t1.`LASTRUNNER`,
t1.`LASTRUNRESULT`,
(case when t1.`LASTRUNRESULT` = '' or t1.`LASTRUNRESULT` is null then 'no' else t1.`LASTRUNRESULT` end) AS `LASTRUNRESULT1`,
t1.`LIB`,
t41.`NAME` AS `LIBNAME`,
t1.`LINKCASE`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`ORDER`,
t1.`PATH`,
t1.`PRI`,
t1.`PRODUCT`,
t31.`NAME` AS `PRODUCTNAME`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID` ) AS `RESULTCNT`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID`  and caseResult in('fail','blocked') ) AS `RESULTFALICNT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SCRIPTEDBY`,
t1.`SCRIPTEDDATE`,
t1.`SCRIPTLOCATION`,
t1.`SCRIPTSTATUS`,
t1.`STAGE`,
t1.`STATUS`,
(case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`,
(SELECT COUNT(1) FROM zt_casestep WHERE `case` = t1.`ID` AND version = t1.`VERSION` ) AS `STEPCNT`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TITLE`,
(SELECT COUNT(1) FROM zt_bug WHERE `case` = t1.`ID` ) AS `TOBUGCNT`,
case when t1.`TYPE` = 'feature' then '功能测试' when t1.`TYPE` = 'performance' then '性能测试' when t1.`TYPE` = 'config' then '配置相关' when t1.`TYPE` = 'nstall' then '安装部署' when t1.`TYPE` = '	security' then '安全相关' when t1.`TYPE` = 'interface' then '接口测试' when t1.`TYPE` = 'unit' then '单元测试' when t1.`TYPE` = 'other' then '其他' end  as `TYPE`,
t1.`VERSION`
FROM `zt_case` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.STORY = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_testsuite t41 ON t1.LIB = t41.ID
LEFT JOIN zt_testrun t51 on t51.`case` = t1.id
LEFT JOIN zt_testreport t61 on FIND_IN_SET(t51.task, t61.tasks)
WHERE t1.DELETED = '0' 
t61.id = ${srfwebcontext('srfparentkey','{"defname":"PATH","dename":"ZT_CASE"}')} 

```
### 测试报告关联-执行人(RunERRePortCase)<div id="Case_RunERRePortCase"></div>
```sql
SELECT 
t1.`AUTO`,
t1.`BRANCH`,
t1.`COLOR`,
t1.`DELETED`,
t1.`FRAME`,
t1.`FREQUENCY`,
t1.`FROMBUG`,
t1.`FROMCASEID`,
t1.`FROMCASEVERSION`,
t1.`HOWRUN`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t71.date AS `LASTRUNDATE`,
t81.realname as `LASTRUNNER`,
t71.caseResult AS `LASTRUNRESULT`,
(case when t71.`caseResult` = '' or t71.`caseResult` is null then 'no' else t71.`caseResult` end) AS `LASTRUNRESULT1`,
t1.`LIB`,
t41.`NAME` AS `LIBNAME`,
t1.`LINKCASE`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`ORDER`,
t1.`PATH`,
t1.`PRI`,
t1.`PRODUCT`,
t31.`NAME` AS `PRODUCTNAME`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID` ) AS `RESULTCNT`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID`  and caseResult in('fail','blocked') ) AS `RESULTFALICNT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SCRIPTEDBY`,
t1.`SCRIPTEDDATE`,
t1.`SCRIPTLOCATION`,
t1.`SCRIPTSTATUS`,
t1.`STAGE`,
t1.`STATUS`,
(case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`,
(SELECT COUNT(1) FROM zt_casestep WHERE `case` = t1.`ID` AND version = t1.`VERSION` ) AS `STEPCNT`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TITLE`,
(SELECT COUNT(1) FROM zt_bug WHERE `case` = t1.`ID` ) AS `TOBUGCNT`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_case` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.STORY = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_testsuite t41 ON t1.LIB = t41.ID 
LEFT JOIN zt_testrun t61 on t61.`case` = t1.id 
LEFT JOIN zt_testresult t71 on t61.id = t71.run AND t71.`case` = t1.id
LEFT JOIN zt_user t81 on t71.lastRunner = t81.account
inner join zt_testreport t91 on t61.task = t91.tasks
WHERE t1.DELETED = '0' 
t91.id = ${srfwebcontext('srfparentkey','{"defname":"PATH","dename":"ZT_CASE"}')}
and 
( FIND_IN_SET(t1.id,t91.cases) ) and t71.date > t91.`begin` and t71.date < t91.`end` 

```
### 测试报告关联-执行人-条目(RunERRePortCaseEntry)<div id="Case_RunERRePortCaseEntry"></div>
```sql
select t1.lastRunResult1,t1.RESULTCNT as RESULTCNT,t1.PRODUCT, CONCAT(ROUND(t1.RESULTCNT / t1.sss * 100, 2),'','%') as TASK from (select t1.PRODUCT,
	t1.lastRunResult1,
	t1.RESULTCNT, (SELECT
	count(1)
FROM
	`zt_case` t1
	LEFT JOIN zt_testrun t61 ON t61.`case` = t1.id
	LEFT JOIN zt_testresult t71 ON t61.id = t71.run 
	AND t71.`case` = t1.id
	LEFT JOIN zt_user t81 ON t71.lastRunner = t81.account 
WHERE
	t1.DELETED = '0' 
	AND ( FIND_IN_SET( t1.id, ( SELECT tt.cases FROM zt_testreport tt WHERE tt.id = #{srf.webcontext.srfparentkey} ) ) ) 
	AND t61.task = ( SELECT tt.tasks FROM zt_testreport tt WHERE tt.id = #{srf.webcontext.srfparentkey} ) ) as sss from (SELECT
	t1.PRODUCT,
	t1.lastRunResult1,
	count( t1.lastRunResult1 ) AS RESULTCNT 
FROM
	(
SELECT
	t81.realname AS `lastRunResult1`,
	t1.`PRODUCT` 
FROM
	`zt_case` t1
	LEFT JOIN zt_testrun t61 ON t61.`case` = t1.id
	LEFT JOIN zt_testresult t71 ON t61.id = t71.run 
	AND t71.`case` = t1.id
	LEFT JOIN zt_user t81 ON t71.lastRunner = t81.account 
WHERE
	t1.DELETED = '0' 
	AND ( FIND_IN_SET( t1.id, ( SELECT tt.cases FROM zt_testreport tt WHERE tt.id = #{srf.webcontext.srfparentkey} ) ) ) 
	AND t61.task = ( SELECT tt.tasks FROM zt_testreport tt WHERE tt.id = #{srf.webcontext.srfparentkey} ) 
	) t1 
GROUP BY
	t1.LASTRUNRESULT1) t1) t1
```
### 项目报告关联-执行人(RunERRePortCase_Project)<div id="Case_RunERRePortCase_Project"></div>
```sql
SELECT 
t1.`AUTO`,
t1.`BRANCH`,
t1.`COLOR`,
t1.`DELETED`,
t1.`FRAME`,
t1.`FREQUENCY`,
t1.`FROMBUG`,
t1.`FROMCASEID`,
t1.`FROMCASEVERSION`,
t1.`HOWRUN`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t71.date AS `LASTRUNDATE`,
t81.realname as `LASTRUNNER`,
t71.caseResult AS `LASTRUNRESULT`,
(case when t71.`caseResult` = '' or t71.`caseResult` is null then 'no' else t71.`caseResult` end) AS `LASTRUNRESULT1`,
t1.`LIB`,
t41.`NAME` AS `LIBNAME`,
t1.`LINKCASE`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`ORDER`,
t1.`PATH`,
t1.`PRI`,
t1.`PRODUCT`,
t31.`NAME` AS `PRODUCTNAME`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID` ) AS `RESULTCNT`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID`  and caseResult in('fail','blocked') ) AS `RESULTFALICNT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SCRIPTEDBY`,
t1.`SCRIPTEDDATE`,
t1.`SCRIPTLOCATION`,
t1.`SCRIPTSTATUS`,
t1.`STAGE`,
t1.`STATUS`,
(case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`,
(SELECT COUNT(1) FROM zt_casestep WHERE `case` = t1.`ID` AND version = t1.`VERSION` ) AS `STEPCNT`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TITLE`,
(SELECT COUNT(1) FROM zt_bug WHERE `case` = t1.`ID` ) AS `TOBUGCNT`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_case` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.STORY = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_testsuite t41 ON t1.LIB = t41.ID 
LEFT JOIN zt_testrun t61 on t61.`case` = t1.id 
LEFT JOIN zt_testresult t71 on t61.id = t71.run AND t71.`case` = t1.id
LEFT JOIN zt_user t81 on t71.lastRunner = t81.account
inner join zt_testreport t91 on FIND_IN_SET(t61.task, t91.tasks)
WHERE t1.DELETED = '0' 
t91.id = ${srfwebcontext('srfparentkey','{"defname":"PATH","dename":"ZT_CASE"}')}
and 
( FIND_IN_SET(t1.id,t91.cases) ) and t71.date > t91.`begin` and t71.date < CONCAT(DATE_FORMAT(t91.`end`,'%Y-%m-%d'),' 23:59:59') 

```
### 测试报告关联--执行结果(RunRePortCase)<div id="Case_RunRePortCase"></div>
```sql
SELECT 
t1.`AUTO`,
t1.`BRANCH`,
t1.`COLOR`,
t1.`DELETED`,
t1.`FRAME`,
t1.`FREQUENCY`,
t1.`FROMBUG`,
t1.`FROMCASEID`,
t1.`FROMCASEVERSION`,
t1.`HOWRUN`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t71.date AS `LASTRUNDATE`,
t71.`LASTRUNNER`,
t71.caseResult AS `LASTRUNRESULT`,
case when t71.caseResult = 'n/a' then '忽略' when t71.caseResult = 'pass' then '通过' when t71.caseResult = 'fail' then '失败' when t71.caseResult = 'blocked' then '阻塞' end AS `LASTRUNRESULT1`,
t1.`LIB`,
t41.`NAME` AS `LIBNAME`,
t1.`LINKCASE`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`ORDER`,
t1.`PATH`,
t1.`PRI`,
t1.`PRODUCT`,
t31.`NAME` AS `PRODUCTNAME`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID` ) AS `RESULTCNT`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID`  and caseResult in('fail','blocked') ) AS `RESULTFALICNT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SCRIPTEDBY`,
t1.`SCRIPTEDDATE`,
t1.`SCRIPTLOCATION`,
t1.`SCRIPTSTATUS`,
t1.`STAGE`,
t1.`STATUS`,
(case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`,
(SELECT COUNT(1) FROM zt_casestep WHERE `case` = t1.`ID` AND version = t1.`VERSION` ) AS `STEPCNT`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TITLE`,
(SELECT COUNT(1) FROM zt_bug WHERE `case` = t1.`ID` ) AS `TOBUGCNT`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_case` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.STORY = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_testsuite t41 ON t1.LIB = t41.ID 
LEFT JOIN zt_testrun t61 on t61.`case` = t1.id 
LEFT JOIN zt_testresult t71 on t61.id = t71.run AND t71.`case` = t1.id
inner join zt_testreport t81 on t61.task = t81.tasks
WHERE t1.DELETED = '0' 
 t81.id = ${srfwebcontext('srfparentkey','{"defname":"PATH","dename":"ZT_CASE"}')}
and 
( FIND_IN_SET(t1.id,t81.cases) ) and t71.date > t81.`begin` and t71.date < t81.`end` 

```
### 测试报告关联--执行结果条目(RunRePortCaseEntry)<div id="Case_RunRePortCaseEntry"></div>
```sql
select  t1.lastRunResult1,t1.product,t1.RESULTCNT as RESULTCNT, CONCAT(ROUND(t1.RESULTCNT / t1.sss * 100, 2),'','%') as TASK from (SELECT
	t1.*,
	(
SELECT
	count( 1 ) 
FROM
	(
SELECT

CASE
	
WHEN
	t71.caseResult = 'n/a' THEN
	'忽略' 
WHEN t71.caseResult = 'pass' THEN
'通过' 
WHEN t71.caseResult = 'fail' THEN
'失败' 
WHEN t71.caseResult = 'blocked' THEN
'阻塞' 
END AS `LASTRUNRESULT1` 
FROM
	`zt_case` t1
	LEFT JOIN zt_testrun t61 ON t61.`case` = t1.id
	LEFT JOIN zt_testresult t71 ON t61.id = t71.run 
	AND t71.`case` = t1.id 
WHERE
	t1.DELETED = '0' 
	AND ( FIND_IN_SET( t1.id, ( SELECT tt.cases FROM zt_testreport tt WHERE tt.id = ${srfwebcontext('srfparentkey','{"defname":"PATH","dename":"ZT_CASE"}')} ) ) ) 
	AND t61.task = ( SELECT tt.tasks FROM zt_testreport tt WHERE tt.id = ${srfwebcontext('srfparentkey','{"defname":"PATH","dename":"ZT_CASE"}')} ) 
	) tt1 
	) AS sss 
FROM
	(
	SELECT
		t1.LASTRUNRESULT1,
t1.product,
		count( t1.LASTRUNRESULT1 ) AS RESULTCNT 
	FROM
		(
		SELECT
t1.product,
		CASE
				
			WHEN
				t71.caseResult = 'n/a' THEN
					'忽略' 
					WHEN t71.caseResult = 'pass' THEN
					'通过' 
					WHEN t71.caseResult = 'fail' THEN
					'失败' 
					WHEN t71.caseResult = 'blocked' THEN
					'阻塞' 
				END AS `LASTRUNRESULT1` 
			FROM
				`zt_case` t1
				LEFT JOIN zt_testrun t61 ON t61.`case` = t1.id
				LEFT JOIN zt_testresult t71 ON t61.id = t71.run 
				AND t71.`case` = t1.id 
			WHERE
				t1.DELETED = '0' 
				AND ( FIND_IN_SET( t1.id, ( SELECT tt.cases FROM zt_testreport tt WHERE tt.id = ${srfwebcontext('srfparentkey','{"defname":"PATH","dename":"ZT_CASE"}')} ) ) ) 
				AND t61.task = ( SELECT tt.tasks FROM zt_testreport tt WHERE tt.id = ${srfwebcontext('srfparentkey','{"defname":"PATH","dename":"ZT_CASE"}')} ) 
			) t1 
		GROUP BY
		t1.LASTRUNRESULT1 
) t1) t1
```
### 项目报告关联-执行结果(RunRePortCase_Project)<div id="Case_RunRePortCase_Project"></div>
```sql
SELECT 
t1.`AUTO`,
t1.`BRANCH`,
t1.`COLOR`,
t1.`DELETED`,
t1.`FRAME`,
t1.`FREQUENCY`,
t1.`FROMBUG`,
t1.`FROMCASEID`,
t1.`FROMCASEVERSION`,
t1.`HOWRUN`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t71.date AS `LASTRUNDATE`,
t71.`LASTRUNNER`,
t71.caseResult AS `LASTRUNRESULT`,
case when t71.caseResult = 'n/a' then '忽略' when t71.caseResult = 'pass' then '通过' when t71.caseResult = 'fail' then '失败' when t71.caseResult = 'blocked' then '阻塞' end AS `LASTRUNRESULT1`,
t1.`LIB`,
t41.`NAME` AS `LIBNAME`,
t1.`LINKCASE`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`ORDER`,
t1.`PATH`,
t1.`PRI`,
t1.`PRODUCT`,
t31.`NAME` AS `PRODUCTNAME`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID` ) AS `RESULTCNT`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID`  and caseResult in('fail','blocked') ) AS `RESULTFALICNT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SCRIPTEDBY`,
t1.`SCRIPTEDDATE`,
t1.`SCRIPTLOCATION`,
t1.`SCRIPTSTATUS`,
t1.`STAGE`,
t1.`STATUS`,
(case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`,
(SELECT COUNT(1) FROM zt_casestep WHERE `case` = t1.`ID` AND version = t1.`VERSION` ) AS `STEPCNT`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TITLE`,
(SELECT COUNT(1) FROM zt_bug WHERE `case` = t1.`ID` ) AS `TOBUGCNT`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_case` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.STORY = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_testsuite t41 ON t1.LIB = t41.ID 
LEFT JOIN zt_testrun t61 on t61.`case` = t1.id 
LEFT JOIN zt_testresult t71 on t61.id = t71.run AND t71.`case` = t1.id
inner join zt_testreport t81 on FIND_IN_SET(t61.task, t81.tasks)
WHERE t1.DELETED = '0' 
 t81.id = ${srfwebcontext('srfparentkey','{"defname":"PATH","dename":"ZT_CASE"}')}
and 
( FIND_IN_SET(t1.id,t81.cases) ) and t71.date > t81.`begin` and t71.date <  CONCAT(DATE_FORMAT(t81.`end`,'%Y-%m-%d'),' 23:59:59') 

```
### 默认（全部数据）(VIEW)<div id="Case_View"></div>
```sql
SELECT
t1.`AUTO`,
t1.`BRANCH`,
t1.`COLOR`,
t1.`DELETED`,
t1.`FRAME`,
t1.`FREQUENCY`,
t1.`FROMBUG`,
t1.`FROMCASEID`,
t1.`FROMCASEVERSION`,
t1.`HOWRUN`,
t1.`ID`,
0 AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LASTRUNDATE`,
t1.`LASTRUNNER`,
t1.`LASTRUNRESULT`,
(case when t1.`LASTRUNRESULT` = '' or t1.`LASTRUNRESULT` is null then 'no' else t1.`LASTRUNRESULT` end) AS `LASTRUNRESULT1`,
t1.`LIB`,
t11.`NAME` AS `LIBNAME`,
t1.`LINKCASE`,
t1.`MODULE`,
t31.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' )  FROM zt_module tt WHERE FIND_IN_SET( tt.id, t31.path ) AND tt.type = 'story'  GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`ORDER`,
t1.`PATH`,
t1.`PRECONDITION`,
t1.`PRI`,
t1.`PRODUCT`,
t41.`NAME` AS `PRODUCTNAME`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID` ) AS `RESULTCNT`,
(SELECT COUNT(1) FROM zt_testresult WHERE `case` = t1.`ID`  and caseResult in('fail','blocked') ) AS `RESULTFALICNT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SCRIPTEDBY`,
t1.`SCRIPTEDDATE`,
t1.`SCRIPTLOCATION`,
t1.`SCRIPTSTATUS`,
t1.`STAGE`,
t1.`STATUS`,
(case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`,
(SELECT COUNT(1) FROM zt_casestep WHERE `case` = t1.`ID` AND version = t1.`VERSION` ) AS `STEPCNT`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TITLE`,
(SELECT COUNT(1) FROM zt_bug WHERE `case` = t1.`ID` ) AS `TOBUGCNT`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_case` t1 
LEFT JOIN `zt_testsuite` t11 ON t1.`LIB` = t11.`ID` 
LEFT JOIN `zt_story` t21 ON t1.`STORY` = t21.`ID` 
LEFT JOIN `zt_module` t31 ON t1.`MODULE` = t31.`ID` 
LEFT JOIN `zt_product` t41 ON t1.`PRODUCT` = t41.`ID` 

WHERE t1.DELETED = '0' 

```

# **测试用例统计**(IBZ_CASESTATS)

### 数据查询(DEFAULT)<div id="CaseStats_Default"></div>
```sql
SELECT
0 AS `BLOCKEDCASE`,
0 AS `FAILCASE`,
t1.`ID`,
t1.`MODULE`,
0 AS `PASSCASE`,
t1.`PRODUCT`,
t1.`TITLE`,
0 AS `TOTALCASE`,
0 AS `TOTALRUNCASE`
FROM `zt_case` t1 

```
### 测试用例统计(TestCaseStats)<div id="CaseStats_TestCaseStats"></div>
```sql
select 
* 
from
(select 
0 as Module,
'/' as ModuleName, 
t1.id as Product, 
count(distinct t2.id) as TotalCase, 
sum(case when t3.caseResult = 'pass' then 1 else 0 end) as PassCase, 
sum(case when t3.caseResult = 'fail' then 1 else 0 end) as FailCase,
sum(case when t3.caseResult = 'blocked' then 1 else 0 end) as BlockedCase,
sum(case when t3.caseResult is not null then 1 else 0 end) as TotalRunCase,
case when sum(case when t3.caseResult is not null then 1 else 0 end) = 0 then '0.00%' else CONCAT(FORMAT((sum(case when t3.caseResult = 'pass' then 1 else 0 end) / sum(case when t3.caseResult is not null then 1 else 0 end)) * 100, 2),'%') end as PassRate
from zt_product t1 
left join zt_case t2 on t2.deleted = '0' and t2.module = 0 and t1.id = t2.product 
left join zt_testresult t3 on t2.id = t3.`case`
where t1.deleted = '0'
group by t1.id
union
select
t1.id as Module, 
case when t1.parent > 0 then (SELECT GROUP_CONCAT( tt.`name` SEPARATOR '/' ) FROM zt_module tt WHERE FIND_IN_SET( tt.id, t1.path )) ELSE t1.`name` END as ModuleName, 
t1.root as Product, 
count(distinct t2.id) as TotalCase, 
sum(case when t3.caseResult = 'pass' then 1 else 0 end) as PassCase, 
sum(case when t3.caseResult = 'fail' then 1 else 0 end) as FailCase,
sum(case when t3.caseResult = 'blocked' then 1 else 0 end) as BlockedCase,
sum(case when t3.caseResult is not null then 1 else 0 end) as TotalRunCase,
case when sum(case when t3.caseResult is not null then 1 else 0 end) = 0 then '0.00%' else CONCAT(FORMAT((sum(case when t3.caseResult = 'pass' then 1 else 0 end) / sum(case when t3.caseResult is not null then 1 else 0 end)) * 100, 2),'%') end as PassRate
from
zt_module t1
left join zt_case t2 on t1.id = t2.module and t2.deleted = '0' 
left join zt_testresult t3 on t2.id = t3.`case`
where t1.deleted = '0' and t1.type = 'story'
group by t1.id) t1
WHERE t1.product = #{srf.datacontext.product} 

```
### 默认（全部数据）(VIEW)<div id="CaseStats_View"></div>
```sql
SELECT
0 AS `BLOCKEDCASE`,
0 AS `FAILCASE`,
t1.`ID`,
t1.`MODULE`,
0 AS `PASSCASE`,
t1.`PRODUCT`,
t1.`TITLE`,
0 AS `TOTALCASE`,
0 AS `TOTALRUNCASE`
FROM `zt_case` t1 

```

# **用例步骤**(ZT_CASESTEP)

### 当前测试步骤(CurTest)<div id="CaseStep_CurTest"></div>
```sql
SELECT
t1.`CASE`,
t1.`DESC`,
t1.`EXPECT`,
t1.`ID`,
t1.id AS `CASESTEPID`,
t1.`PARENT`,
t1.`TYPE`,
t1.`VERSION`,
t21.stepResults as STEPS
FROM `zt_casestep` t1 
RIGHT JOIN zt_case t11 ON t1.`CASE` = t11.ID 
LEFT OUTER JOIN zt_testresult t21 ON t11.ID = t21.`CASE`  AND t1.version = t21.version

WHERE ( t21.`ID` = ${srfwebcontext('srfparentkey','{"defname":"ID","dename":"ZT_TESTRESULT"}')} ) 

```
### DEFAULT(DEFAULT)<div id="CaseStep_Default"></div>
```sql
SELECT
	t1.`CASE`,
	t1.`DESC`,
	t1.`EXPECT`,
        t1.id AS `CASESTEPID`,
	t1.`ID`,
	@rownum := @rownum + 1 AS RUNID,
	t1.`PARENT`,
	t1.`TYPE`,
	t1.`VERSION` 
FROM
	`zt_casestep` t1, (select @rownum := 0) m 

```
### DEFAULT1(DEFAULT1)<div id="CaseStep_Default1"></div>
```sql
SELECT
t1.`CASE`,
t1.id AS `CASESTEPID`,
t1.`DESC`,
t1.`EXPECT`,
t1.`ID`,
t1.`PARENT`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_casestep` t1 

```
### Mob(Mob)<div id="CaseStep_Mob"></div>
```sql
SELECT
	t1.`CASE`,
	t1.`DESC`,
	t1.`EXPECT`,
	t1.`ID`,
        t1.id AS `CASESTEPID`,
	t1.`PARENT`,
	t1.`TYPE`,
	t1.`VERSION` 
FROM
	`zt_casestep` t1
	inner JOIN zt_case t11 ON t1.`case` = t11.id 
	AND t1.version = t11.version
```
### 版本(Version)<div id="CaseStep_Version"></div>
```sql
SELECT t1.`CASE`,t1.type,t1.parent,t1.expect,t1.`desc`, t1.`ID`, t1.`VERSION` FROM `zt_casestep` t1
```
### 版本1(Versions)<div id="CaseStep_Versions"></div>
```sql
SELECT
	t1.ID,t1.`case`,t1.VERSION,CONCAT('#',t1.VERSION) as expect
FROM
	(
SELECT
	t1.`ID`,
	t1.`CASE`,
	t1.`VERSION` 
FROM
	`zt_casestep` t1 where t1.version <> 1 
GROUP BY
	t1.`CASE`,
	t1.`VERSION` UNION ALL
SELECT
	1 AS ID,
	t1.`id` AS `CASE`,
	1 AS `VERSION` 
FROM
	zt_case t1 

	) t1
```
### 默认（全部数据）(VIEW)<div id="CaseStep_View"></div>
```sql
SELECT
t1.`CASE`,
t1.id AS `CASESTEPID`,
t1.`DESC`,
t1.`EXPECT`,
t1.`ID`,
t1.`PARENT`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_casestep` t1 

```

# **公司**(ZT_COMPANY)

### DEFAULT(DEFAULT)<div id="Company_Default"></div>
```sql
SELECT
t1.`ADDRESS`,
t1.`ADMINS`,
t1.`BACKYARD`,
t1.`DELETED`,
t1.`FAX`,
t1.`GUEST`,
t1.`ID`,
t1.`NAME`,
t1.`PHONE`,
t1.`WEBSITE`,
t1.`ZIPCODE`
FROM `zt_company` t1 

WHERE t1.DELETED = '0' 

```
### 默认（全部数据）(VIEW)<div id="Company_View"></div>
```sql
SELECT
t1.`ADDRESS`,
t1.`ADMINS`,
t1.`BACKYARD`,
t1.`DELETED`,
t1.`FAX`,
t1.`GUEST`,
t1.`ID`,
t1.`NAME`,
t1.`PHONE`,
t1.`WEBSITE`,
t1.`ZIPCODE`
FROM `zt_company` t1 

WHERE t1.DELETED = '0' 

```

# **公司动态汇总**(IBZ_COMPANYSTATS)

### 公司动态统计(CompanyDynamicStats)<div id="CompanyStats_CompanyDynamicStats"></div>
```sql
select 
DATE_FORMAT(t1.date, '%Y-%m-%d') as `ZTDATE`, 
t1.date, 
sum(case when t1.objecttype = 'user' and t1.action = 'login' then 1 else 0 end) as `LoginCNT`,
IFNULL((select sum(t2.consumed) from zt_taskestimate t2 where t2.date = DATE_FORMAT(t1.date, '%Y-%m-%d')), 0) as `LogHours`, 
sum(case when t1.objecttype = 'story' and t1.action = 'opened' then 1 else 0 end) as `OpenedStoryCNT`, 
sum(case when t1.objecttype = 'story' and t1.action = 'closed' then 1 else 0 end) as `ClosedStoryCNT`, 
sum(case when t1.objecttype = 'task' and t1.action = 'opened' then 1 else 0 end) as `OpenedTaskCNT`, 
sum(case when t1.objecttype = 'task' and t1.action = 'finished' then 1 else 0 end) as `FinishedTaskCNT`, 
sum(case when t1.objecttype = 'bug' and t1.action = 'opened' then 1 else 0 end) as `OpenedBugCNT`, 
sum(case when t1.objecttype = 'bug' and t1.action = 'resolved' then 1 else 0 end) as `ResolvedBugCNT`, 
count(1) as `DynamicCNT` 
from zt_action t1 
where (DATE_FORMAT(t1.date, '%Y-%m-%d') >= #{srf.datacontext.begin} or #{srf.datacontext.begin} is null) and (DATE_FORMAT(t1.date, '%Y-%m-%d') <= #{srf.datacontext.end} or #{srf.datacontext.end} is null)
group by DATE_FORMAT(t1.date, '%Y-%m-%d')
```
### 数据查询(DEFAULT)<div id="CompanyStats_Default"></div>
```sql
SELECT
0 AS `CLOSEDSTORYCNT`,
t1.`DATE`,
0 AS `DYNAMICCNT`,
0 AS `FINISHEDTASKCNT`,
t1.`ID`,
0 AS `LOGHOURS`,
0 AS `LOGINCNT`,
0 AS `OPENEDBUGCNT`,
0 AS `OPENEDSTORYCNT`,
0 AS `OPENEDTASKCNT`,
0 AS `RESOLVEDBUGCNT`
FROM `zt_action` t1 

```
### 默认（全部数据）(VIEW)<div id="CompanyStats_View"></div>
```sql
SELECT
0 AS `CLOSEDSTORYCNT`,
t1.`COMMENT`,
t1.`DATE`,
0 AS `DYNAMICCNT`,
0 AS `FINISHEDTASKCNT`,
t1.`ID`,
0 AS `LOGHOURS`,
0 AS `LOGINCNT`,
0 AS `OPENEDBUGCNT`,
0 AS `OPENEDSTORYCNT`,
0 AS `OPENEDTASKCNT`,
0 AS `RESOLVEDBUGCNT`
FROM `zt_action` t1 

```

# **compile**(ZT_COMPILE)

### DEFAULT(DEFAULT)<div id="Compile_Default"></div>
```sql
SELECT
t1.`ATTIME`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`DELETED`,
t1.`ID`,
t1.`JOB`,
t1.`NAME`,
t1.`QUEUE`,
t1.`STATUS`,
t1.`TAG`,
t1.`TESTTASK`,
t1.`UPDATEDATE`
FROM `zt_compile` t1 

WHERE t1.DELETED = '0' 

```
### 默认（全部数据）(VIEW)<div id="Compile_View"></div>
```sql
SELECT
t1.`ATTIME`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`DELETED`,
t1.`ID`,
t1.`JOB`,
t1.`LOGS`,
t1.`NAME`,
t1.`QUEUE`,
t1.`STATUS`,
t1.`TAG`,
t1.`TESTTASK`,
t1.`UPDATEDATE`
FROM `zt_compile` t1 

WHERE t1.DELETED = '0' 

```

# **配置**(ZT_CONFIG)

### DEFAULT(DEFAULT)<div id="Config_Default"></div>
```sql
SELECT
t1.`ID`,
t1.`KEY`,
t1.`MODULE`,
t1.`OWNER`,
t1.`SECTION`
FROM `zt_config` t1 

```
### 默认（全部数据）(VIEW)<div id="Config_View"></div>
```sql
SELECT
t1.`ID`,
t1.`KEY`,
t1.`MODULE`,
t1.`OWNER`,
t1.`SECTION`,
t1.`VALUE`
FROM `zt_config` t1 

```

# **cron**(ZT_CRON)

### DEFAULT(DEFAULT)<div id="Cron_Default"></div>
```sql
SELECT
t1.`BUILDIN`,
t1.`DOM`,
t1.`DOW`,
t1.`H`,
t1.`ID`,
t1.`LASTTIME`,
t1.`M`,
t1.`MON`,
t1.`REMARK`,
t1.`STATUS`,
t1.`TYPE`
FROM `zt_cron` t1 

```
### 默认（全部数据）(VIEW)<div id="Cron_View"></div>
```sql
SELECT
t1.`BUILDIN`,
t1.`COMMAND`,
t1.`DOM`,
t1.`DOW`,
t1.`H`,
t1.`ID`,
t1.`LASTTIME`,
t1.`M`,
t1.`MON`,
t1.`REMARK`,
t1.`STATUS`,
t1.`TYPE`
FROM `zt_cron` t1 

```

# **部门**(ZT_DEPT)

### DEFAULT(DEFAULT)<div id="Dept_Default"></div>
```sql
SELECT
t1.`FUNCTION`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_DEPT WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`MANAGER`,
t1.`NAME`,
t1.`ORDER`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`POSITION`
FROM `zt_dept` t1 
LEFT JOIN `zt_dept` t11 ON t1.`PARENT` = t11.`ID` 

```
### 根部门(ROOT)<div id="Dept_Root"></div>
```sql
SELECT
t1.`FUNCTION`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_DEPT WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`MANAGER`,
t1.`NAME`,
t1.`ORDER`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`POSITION`
FROM `zt_dept` t1 
LEFT JOIN `zt_dept` t11 ON t1.`PARENT` = t11.`ID` 

WHERE ( t1.`PARENT` = 0 ) 

```
### 默认（全部数据）(VIEW)<div id="Dept_View"></div>
```sql
SELECT
t1.`FUNCTION`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_DEPT WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`MANAGER`,
t1.`NAME`,
t1.`ORDER`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`POSITION`
FROM `zt_dept` t1 
LEFT JOIN `zt_dept` t11 ON t1.`PARENT` = t11.`ID` 

```

# **文档**(ZT_DOC)

### 文档库文档（子库）(ChildDocLibDoc)<div id="Doc_ChildDocLibDoc"></div>
```sql
select t1.* from (select null as `ACL`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`, 
null as `ADDEDBY`,
null as `ADDEDDATE`,
'0' as `DELETED`,
null as `EDITEDBY`,
null as `EDITEDDATE`,
null as  `GROUPS`,
t1.`ID`,
''`KEYWORDS`,
${srfdatacontext('srfparentkey','{"defname":"ROOT","dename":"ZT_MODULE"}')} as `LIB`,
'' AS `LIBNAME`,
0 as `MODULE`,
'' AS `MODULENAME`,
0 as `PRODUCT`,
'' AS `PRODUCTNAME`,
0 as `PROJECT`,
'' AS `PROJECTNAME`,
t1.`name` as `TITLE`,
'text' as `TYPE`,
1 as `VERSION`,
'' as `VIEWS`,'module' as DOCQTYPE from zt_module t1 where t1.deleted = '0' and t1.type = 'doc' and t1.root = ${srfdatacontext('srfparentkey','{"defname":"ROOT","dename":"ZT_MODULE"}')} and t1.parent = ${srfdatacontext('parent','{"defname":"ROOT","dename":"ZT_MODULE"}')}
UNION
SELECT
t1.`ACL`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`, 
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
t1.`DELETED`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`GROUPS`,
t1.`ID`,
t1.`KEYWORDS`,
t1.`LIB`,
t31.`NAME` AS `LIBNAME`,
t1.`MODULE`,
t41.`NAME` AS `MODULENAME`,
t1.`PRODUCT`,
t21.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t11.`NAME` AS `PROJECTNAME`,
t1.`TITLE`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VIEWS`,
'doc' as DOCQTYPE
FROM `zt_doc` t1 
LEFT JOIN zt_project t11 ON t1.PROJECT = t11.ID 
LEFT JOIN zt_product t21 ON t1.PRODUCT = t21.ID 
LEFT JOIN zt_doclib t31 ON t1.LIB = t31.ID 
LEFT JOIN zt_module t41 ON t1.MODULE = t41.ID ) t1
WHERE t1.DELETED = '0' 

```
### DEFAULT(DEFAULT)<div id="Doc_Default"></div>
```sql
SELECT
t1.`ACL`,
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
t1.`DELETED`,
'doc' AS `DOCQTYPE`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`GROUPS`,
t1.`ID`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`,
t1.`KEYWORDS`,
t1.`LIB`,
t31.`NAME` AS `LIBNAME`,
t1.`MODULE`,
t41.`NAME` AS `MODULENAME`,
t1.`PRODUCT`,
t21.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t11.`NAME` AS `PROJECTNAME`,
t1.`TITLE`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VIEWS`
FROM `zt_doc` t1 
LEFT JOIN zt_project t11 ON t1.PROJECT = t11.ID 
LEFT JOIN zt_product t21 ON t1.PRODUCT = t21.ID 
LEFT JOIN zt_doclib t31 ON t1.LIB = t31.ID 
LEFT JOIN zt_module t41 ON t1.MODULE = t41.ID 

WHERE t1.DELETED = '0' 

```
### 文档库文档(DocLibAndDoc)<div id="Doc_DocLibAndDoc"></div>
```sql
select t1.* from (select null as `ACL`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`,
null as `ADDEDBY`,
null as `ADDEDDATE`,
'0' as `DELETED`,
null as `EDITEDBY`,
null as `EDITEDDATE`,
null as  `GROUPS`,
t1.`ID`,
''`KEYWORDS`,
'0' as `LIB`,
'' AS `LIBNAME`,
(case when ${srfdatacontext('srfparentkey','{"defname":"ROOT","dename":"ZT_MODULE"}')} is null then '0' else 
${srfdatacontext('srfparentkey','{"defname":"ROOT","dename":"ZT_MODULE"}')} end
)
 as `MODULE`,
'' AS `MODULENAME`,
t2.product as `PRODUCT`,
'' AS `PRODUCTNAME`,
0 as `PROJECT`,
'' AS `PROJECTNAME`,
t1.`name` as `TITLE`,
'module' as `TYPE`,
1 as `VERSION`,
(select count(1) from zt_doc t where t.deleted = '0' and t.module = t1.id) + (select count(1) from zt_module t where t.deleted = '0' and t.type = 'doc' and t.root = t1.root and t.parent = t1.id) as doccnt,
'' as `VIEWS`, 'module' as DOCQTYPE from zt_module t1 
LEFT JOIN zt_doclib t2 on t2.id = t1.root
where t1.deleted = '0' and t1.type = 'doc'  and t1.parent =  (case when ${srfdatacontext('srfparentkey','{"defname":"ROOT","dename":"ZT_MODULE"}')} is null then '0' else 
${srfdatacontext('srfparentkey','{"defname":"ROOT","dename":"ZT_MODULE"}')} end
)
UNION
SELECT
t1.`ACL`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`,
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
t1.`DELETED`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`GROUPS`,
t1.`ID`,
t1.`KEYWORDS`,
t1.`LIB`,
t31.`NAME` AS `LIBNAME`,
t1.`MODULE`,
t41.`NAME` AS `MODULENAME`,
t1.`PRODUCT`,
t21.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t11.`NAME` AS `PROJECTNAME`,
t1.`TITLE`,
t1.`TYPE`,
t1.`VERSION`,
0 as doccnt,
t1.`VIEWS`,
'doc' as DOCQTYPE
FROM `zt_doc` t1 
LEFT JOIN zt_project t11 ON t1.PROJECT = t11.ID 
LEFT JOIN zt_product t21 ON t1.PRODUCT = t21.ID 
LEFT JOIN zt_doclib t31 ON t1.LIB = t31.ID 
LEFT JOIN zt_module t41 ON t1.MODULE = t41.ID ) t1
WHERE t1.DELETED = '0' 
t1.module = (case when ${srfdatacontext('srfparentkey','{"defname":"ROOT","dename":"ZT_MODULE"}')} is null then '0' else 
${srfdatacontext('srfparentkey','{"defname":"ROOT","dename":"ZT_MODULE"}')} end
)
 

```
### 文档库文档(DocLibDoc)<div id="Doc_DocLibDoc"></div>
```sql
select t1.* from (select null as `ACL`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`,
null as `ADDEDBY`,
null as `ADDEDDATE`,
'0' as `DELETED`,
null as `EDITEDBY`,
null as `EDITEDDATE`,
null as  `GROUPS`,
t1.`ID`,
''`KEYWORDS`,
${srfdatacontext('srfroot','{"defname":"ROOT","dename":"ZT_MODULE"}')} as `LIB`,
'' AS `LIBNAME`,
(case when ${srfdatacontext('parent','{"defname":"ROOT","dename":"ZT_MODULE"}')} is null then '0' else 
${srfdatacontext('parent','{"defname":"ROOT","dename":"ZT_MODULE"}')} end
)
 as `MODULE`,
'' AS `MODULENAME`,
0 as `PRODUCT`,
'' AS `PRODUCTNAME`,
0 as `PROJECT`,
'' AS `PROJECTNAME`,
t1.`name` as `TITLE`,
'text' as `TYPE`,
1 as `VERSION`,
'' as `VIEWS`,'module' as DOCQTYPE from zt_module t1 where t1.deleted = '0' and t1.type = 'doc' and t1.root = ${srfdatacontext('srfroot','{"defname":"ROOT","dename":"ZT_MODULE"}')} and t1.parent =  (case when ${srfdatacontext('parent','{"defname":"ROOT","dename":"ZT_MODULE"}')} is null then '0' else 
${srfdatacontext('parent','{"defname":"ROOT","dename":"ZT_MODULE"}')} end
)
UNION
SELECT
t1.`ACL`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`,
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
t1.`DELETED`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`GROUPS`,
t1.`ID`,
t1.`KEYWORDS`,
t1.`LIB`,
t31.`NAME` AS `LIBNAME`,
t1.`MODULE`,
t41.`NAME` AS `MODULENAME`,
t1.`PRODUCT`,
t21.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t11.`NAME` AS `PROJECTNAME`,
t1.`TITLE`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VIEWS`,
'doc' as DOCQTYPE
FROM `zt_doc` t1 
LEFT JOIN zt_project t11 ON t1.PROJECT = t11.ID 
LEFT JOIN zt_product t21 ON t1.PRODUCT = t21.ID 
LEFT JOIN zt_doclib t31 ON t1.LIB = t31.ID 
LEFT JOIN zt_module t41 ON t1.MODULE = t41.ID ) t1
WHERE t1.DELETED = '0' 
t1.module = (case when ${srfdatacontext('parent','{"defname":"ROOT","dename":"ZT_MODULE"}')} is null then '0' else 
${srfdatacontext('parent','{"defname":"ROOT","dename":"ZT_MODULE"}')} end
)
 

```
### 文档库分类文档(DocModuleDoc)<div id="Doc_DocModuleDoc"></div>
```sql
select t1.* from (select null as `ACL`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`, 
null as `ADDEDBY`,
null as `ADDEDDATE`,
'0' as `DELETED`,
null as `EDITEDBY`,
null as `EDITEDDATE`,
null as  `GROUPS`,
t1.`ID`,
''`KEYWORDS`,
${srfdatacontext('lib','{"defname":"ROOT","dename":"ZT_MODULE"}')} as `LIB`,
'' AS `LIBNAME`,
${srfdatacontext('id','{"defname":"ROOT","dename":"ZT_MODULE"}')} as `MODULE`,
'' AS `MODULENAME`,
0 as `PRODUCT`,
'' AS `PRODUCTNAME`,
0 as `PROJECT`,
'' AS `PROJECTNAME`,
t1.`name` as `TITLE`,
'text' as `TYPE`,
1 as `VERSION`,
'' as `VIEWS`,'module' as DOCQTYPE from zt_module t1 where t1.deleted = '0' and t1.type = 'doc' and t1.root = ${srfdatacontext('lib','{"defname":"ROOT","dename":"ZT_MODULE"}')} and t1.parent = ${srfdatacontext('id','{"defname":"ROOT","dename":"ZT_MODULE"}')}
UNION
SELECT
t1.`ACL`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`, 
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
t1.`DELETED`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`GROUPS`,
t1.`ID`,
t1.`KEYWORDS`,
t1.`LIB`,
t31.`NAME` AS `LIBNAME`,
t1.`MODULE`,
t41.`NAME` AS `MODULENAME`,
t1.`PRODUCT`,
t21.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t11.`NAME` AS `PROJECTNAME`,
t1.`TITLE`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VIEWS`,
'doc' as DOCQTYPE
FROM `zt_doc` t1 
LEFT JOIN zt_project t11 ON t1.PROJECT = t11.ID 
LEFT JOIN zt_product t21 ON t1.PRODUCT = t21.ID 
LEFT JOIN zt_doclib t31 ON t1.LIB = t31.ID 
LEFT JOIN zt_module t41 ON t1.MODULE = t41.ID ) t1
WHERE t1.DELETED = '0' 
t1.module = ${srfdatacontext('id','{"defname":"ROOT","dename":"ZT_MODULE"}')} 

```
### 文档统计(DocStatus)<div id="Doc_DocStatus"></div>
```sql
select 
count(1) as `ALLDOCCNT`,
(select count(1) from zt_doc where TO_DAYS(DATE_FORMAT(NOW(),'%Y-%m-%d')) - TO_DAYS(DATE_FORMAT(editedDate,'%Y-%m-%d')) < 5 and deleted = '0') as `RECENTUPDATECNT`,
(select count(1) from zt_doc where DATE_FORMAT(NOW(),'%Y-%m-%d') = DATE_FORMAT(editedDate,'%Y-%m-%d') and deleted = '0') as `TODAYUPDATECNT`,
(select count(1) from zt_doc where TO_DAYS(DATE_FORMAT(NOW(),'%Y-%m-%d')) - TO_DAYS(DATE_FORMAT(addedDate,'%Y-%m-%d')) < 5 and deleted = '0') as `RECENTADDCNT`,
(select count(1) from zt_doc where addedBy = #{srf.sessioncontext.srfloginname} and deleted = '0') as `MYDOCCNT`,
(select count(1) from zt_doc where FIND_IN_SET(#{srf.sessioncontext.srfloginname},collector) > 0) as `MYFAVOURITECNT`
from zt_doc
where deleted = '0'
```
### 文件夹文档（子目录）(ModuleDocChild)<div id="Doc_ModuleDocChild"></div>
```sql
SELECT
t1.`ACL`,
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
t1.`DELETED`,
'doc' AS `DOCQTYPE`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`GROUPS`,
t1.`ID`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`,
t1.`KEYWORDS`,
t1.`LIB`,
t31.`NAME` AS `LIBNAME`,
t1.`MODULE`,
t41.`NAME` AS `MODULENAME`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`TITLE`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VIEWS`
FROM `zt_doc` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 
LEFT JOIN `zt_project` t21 ON t1.`PROJECT` = t21.`ID` 
LEFT JOIN `zt_doclib` t31 ON t1.`LIB` = t31.`ID` 
LEFT JOIN `zt_module` t41 ON t1.`MODULE` = t41.`ID` 

WHERE t1.DELETED = '0' 
( t1.`MODULE` = ${srfwebcontext('srfparentkey','{"defname":"MODULE","dename":"ZT_DOC"}')} ) 

```
### 我的收藏(MYFAVOURITE)<div id="Doc_MyFavourite"></div>
```sql
SELECT
	* 
FROM
	(
	SELECT
		t1.id,
		t1.`name` AS `title`,
		NULL AS `addedBy`,
		NULL AS `addedDate`,
		NULL AS `editedBy`,
		NULL AS `editedDate`,
		1 AS `ISFAVOURITES`, 
		'doclib' as DOCQTYPE
	FROM
		zt_doclib t1 
	WHERE
		t1.collector LIKE CONCAT_WS( '', '%,', #{srf.sessioncontext.srfloginname}, '%,' ) 
		AND t1.deleted = '0' UNION
	SELECT
		t2.id,
		t2.`name` AS `title`,
		NULL AS `addedBy`,
		NULL AS `addedDate`,
		NULL AS `editedBy`,
		NULL AS `editedDate`,
		1 AS `ISFAVOURITES`, 
		'module' as DOCQTYPE 
	FROM
		zt_module t2 
	WHERE
		t2.collector LIKE CONCAT_WS( '', '%,', #{srf.sessioncontext.srfloginname}, '%,' ) 
		AND t2.deleted = '0' UNION
	SELECT
		t3.id,
		t3.title AS `title`,
		t3.addedBy,
		t3.addedDate,
		t3.editedBy,
		t3.editedDate,
		1 AS `ISFAVOURITES`, 
		'doc' as DOCQTYPE 
	FROM
		zt_doc t3 
	WHERE
		t3.collector LIKE CONCAT_WS( '', '%,', #{srf.sessioncontext.srfloginname}, '%,' ) 
	AND t3.deleted = '0' 
	) t1
```
### 我的收藏(MyFavouritesOnlyDoc)<div id="Doc_MyFavouritesOnlyDoc"></div>
```sql
SELECT
	t1.`ACL`,
	t1.`ADDEDBY`,
	t1.`ADDEDDATE`,
	t1.`DELETED`,
	'doc' AS `DOCQTYPE`,
	t1.`EDITEDBY`,
	t1.`EDITEDDATE`,
	t1.`GROUPS`,
	t1.`ID`,
	1 AS `ISFAVOURITES`,
	t1.`KEYWORDS`,
	t1.`LIB`,
	t1.`MODULE`,
	t1.`PRODUCT`,
	t1.`PROJECT`,
	t1.`TITLE`,
	t1.`TYPE`,
	t1.`VERSION`,
	t1.`VIEWS` 
FROM
	`zt_doc` t1
WHERE t1.deleted = '0' 
FIND_IN_SET(#{srf.sessioncontext.srfloginname}, t1.collector ) > 0 

```
### 子目录文档(NotRootDoc)<div id="Doc_NotRootDoc"></div>
```sql
SELECT
t1.`ACL`,
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
t1.`DELETED`,
'doc' AS `DOCQTYPE`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`GROUPS`,
t1.`ID`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`, 
t1.`KEYWORDS`,
t1.`LIB`,
t31.`NAME` AS `LIBNAME`,
t1.`MODULE`,
t41.`NAME` AS `MODULENAME`,
t1.`PRODUCT`,
t21.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t11.`NAME` AS `PROJECTNAME`,
t1.`TITLE`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VIEWS`
FROM `zt_doc` t1 
LEFT JOIN zt_project t11 ON t1.PROJECT = t11.ID 
LEFT JOIN zt_product t21 ON t1.PRODUCT = t21.ID 
LEFT JOIN zt_doclib t31 ON t1.LIB = t31.ID 
LEFT JOIN zt_module t41 ON t1.MODULE = t41.ID 

WHERE t1.DELETED = '0' 
t1.module = ${srfdatacontext('srfparentkey','{"defname":"ROOT","dename":"ZT_MODULE"}')} 

```
### 根目录文档(RootDoc)<div id="Doc_RootDoc"></div>
```sql
SELECT
t1.`ACL`,
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
t1.`DELETED`,
'doc' AS `DOCQTYPE`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`GROUPS`,
t1.`ID`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`,
t1.`KEYWORDS`,
t1.`LIB`,
t31.`NAME` AS `LIBNAME`,
t1.`MODULE`,
t41.`NAME` AS `MODULENAME`,
t1.`PRODUCT`,
t21.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t11.`NAME` AS `PROJECTNAME`,
t1.`TITLE`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VIEWS`
FROM `zt_doc` t1 
LEFT JOIN zt_project t11 ON t1.PROJECT = t11.ID 
LEFT JOIN zt_product t21 ON t1.PRODUCT = t21.ID 
LEFT JOIN zt_doclib t31 ON t1.LIB = t31.ID 
LEFT JOIN zt_module t41 ON t1.MODULE = t41.ID 

WHERE t1.DELETED = '0' 
t1.module = '0' 
t1.lib = ${srfdatacontext('srfparentkey','{"defname":"ROOT","dename":"ZT_MODULE"}')} 

```
### 默认（全部数据）(VIEW)<div id="Doc_View"></div>
```sql
SELECT
t1.`ACL`,
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
t1.`COLLECTOR`,
t1.`DELETED`,
'doc' AS `DOCQTYPE`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`GROUPS`,
t1.`ID`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`,
t1.`KEYWORDS`,
t1.`LIB`,
t31.`NAME` AS `LIBNAME`,
t1.`MODULE`,
t41.`NAME` AS `MODULENAME`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`TITLE`,
t1.`TYPE`,
t1.`USERS`,
t1.`VERSION`,
t1.`VIEWS`
FROM `zt_doc` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 
LEFT JOIN `zt_project` t21 ON t1.`PROJECT` = t21.`ID` 
LEFT JOIN `zt_doclib` t31 ON t1.`LIB` = t31.`ID` 
LEFT JOIN `zt_module` t41 ON t1.`MODULE` = t41.`ID` 

WHERE t1.DELETED = '0' 

```

# **文档内容**(ZT_DOCCONTENT)

### 当前版本(CurVersion)<div id="DocContent_CurVersion"></div>
```sql
SELECT t1.id AS `ID`, t1.`DOC`, t1.`VERSION`, CONCAT('#',t1.`version`) as `TITLE` FROM `zt_doccontent` t1
```
### DEFAULT(DEFAULT)<div id="DocContent_Default"></div>
```sql
SELECT
t1.`DIGEST`,
t1.`DOC`,
t1.`ID`,
t1.`TITLE`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_doccontent` t1 

```
### 默认（全部数据）(VIEW)<div id="DocContent_View"></div>
```sql
SELECT
t1.`CONTENT`,
t1.`DIGEST`,
t1.`DOC`,
t1.`FILES`,
t1.`ID`,
t1.`TITLE`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_doccontent` t1 

```

# **文档库**(ZT_DOCLIB)

### 自定义文档库(ByCustom)<div id="DocLib_ByCustom"></div>
```sql
SELECT
t1.`ACL`,
t1.`DELETED`,
'doc' AS `DOCLIBTYPE`,
t1.`GROUPS`,
t1.`ID`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`,
t1.`MAIN`,
t1.`MDEPTID`,
(select count(1) from zt_module t where t.deleted = '0' and t.type = 'doc' and t.parent = 0 and t.root = t1.id) AS `MODULECNT`,
t1.`NAME`,
(select t.date from zt_action t where t.objectID = t1.id and t.objectType = 'doclib' and t.action = 'created') AS `OPENEDDATE`,
t1.`ORDER`,
t1.`ORGID`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`TYPE`
FROM `zt_doclib` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 
LEFT JOIN `zt_project` t21 ON t1.`PROJECT` = t21.`ID` 

WHERE t1.DELETED = '0' 
( t1.`TYPE` = 'custom'  AND  t1.`ORGID` =  ${srfsessioncontext('SRFORGID','{"defname":"ORGID","dename":"ZT_DOCLIB"}')} ) 

```
### 产品文档库(ByProduct)<div id="DocLib_ByProduct"></div>
```sql
select t1.* from (SELECT
	( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`,
	t1.`ACL`,
	t1.`DELETED`,
	t1.`GROUPS`,
	t1.`ID`,
	t1.`MAIN`,
	t1.`NAME`,
	t1.`ORDER`,
	t1.`PRODUCT`,
	t1.`PROJECT`,
	t1.`TYPE` ,
        'doc' as DOCLIBTYPE,
	(select count(1) from zt_doc t where t.lib = t1.id and t.product = t1.product and t.deleted = '0') as DOCCNT
FROM
	`zt_doclib` t1 
	
	UNION
	SELECT
	0 AS `ISFAVOURITES`,
	'default' as `ACL`,
	'0' as `DELETED`,
	'' as `GROUPS`,
	0 as `ID`,
	0 as `MAIN`,
	'附件库' as `NAME`,
	0 as `ORDER`,
	#{srf.datacontext.product} as `PRODUCT`, 
	0 as `PROJECT`,
	'product' as `TYPE` ,
        'file' as DOCLIBTYPE,
	(select count(1) from zt_file t where ((t.objectType ='product' and t.objectID =  #{srf.datacontext.product}
	) or (t.objectType = 'story' and exists(select 1 from zt_story tt where tt.id = t.objectID and tt.product =  #{srf.datacontext.product} 
	and tt.deleted = '0')) or (t.objectType = 'bug' and exists(select 1 from zt_bug tt where tt.id = t.objectID and tt.product =  #{srf.datacontext.product} 
	and tt.deleted = '0')) or (t.objectType = 'doc' and EXISTS(select 1 from zt_doc tt where tt.id = t.objectID and tt.product = #{srf.datacontext.product}
	and tt.deleted = '0'))) and t.deleted = '0') as DOCCNT
FROM
	dual  ) t1
WHERE t1.type = 'product'  AND t1.deleted = '0' 
 t1.`PRODUCT` = #{srf.datacontext.product} 

```
### 产品文档库(ByProductNotFiles)<div id="DocLib_ByProductNotFiles"></div>
```sql
SELECT
	t1.* 
FROM
	(
	SELECT
		( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`,
		t1.`ACL`,
		t1.`DELETED`,
		t1.`GROUPS`,
		t1.`ID`,
(select count(1) from zt_module t where t.deleted = '0' and t.type = 'doc' and t.parent = 0 and t.root = t1.id) as modulecnt,
(select t.date from zt_action t where t.objectID = t1.id and t.objectType = 'doclib' and t.action = 'created') as openedDate,
		t1.`MAIN`,
		t1.`NAME`,
		t1.`ORDER`,
		t1.`PRODUCT`,
		t1.`PROJECT`,
		t1.`TYPE`,
		'doc' AS DOCLIBTYPE,
		(
		SELECT
			count( 1 ) 
		FROM
			zt_doc t 
		WHERE
			t.lib = t1.id 
			AND t.product = t1.product 
			AND t.deleted = '0' 
		) AS DOCCNT 
	FROM
	`zt_doclib` t1 
	) t1
WHERE t1.type = 'product'  AND t1.deleted = '0' 
 t1.`PRODUCT` = #{srf.datacontext.product} 

```
### 项目文件库(ByProject)<div id="DocLib_ByProject"></div>
```sql
select t1.* from (SELECT
	( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`,
	t1.`ACL`,
	t1.`DELETED`,
	t1.`GROUPS`,
	t1.`ID`,
	t1.`MAIN`,
	t1.`NAME`,
	t1.`ORDER`,
	t1.`PRODUCT`,
	t1.`PROJECT`,
	t1.`TYPE` ,
        'doc' as DOCLIBTYPE,
	(select count(1) from zt_doc t where t.lib = t1.id and t.project = t1.project and t.deleted = '0') as DOCCNT
FROM
	`zt_doclib` t1 
	
	UNION
	SELECT
	0 AS `ISFAVOURITES`,
	'default' as `ACL`,
	'0' as `DELETED`,
	'' as `GROUPS`,
	0 as `ID`,
	0 as `MAIN`,
	'附件库' as `NAME`,
	0 as `ORDER`,
	0 as `PRODUCT`,
	#{srf.datacontext.project} as `PROJECT`,
	'project' as `TYPE` ,
        'file' as DOCLIBTYPE,
	(select count(1) from zt_file t where ((t.objectType ='project' and t.objectID = #{srf.datacontext.project}) or (t.objectType = 'task' and exists(select 1 from zt_task tt where tt.id = t.objectID and tt.project = #{srf.datacontext.project} and tt.deleted = '0')) or (t.objectType = 'build' and exists(select 1 from zt_build tt where tt.id = t.objectID and tt.project = #{srf.datacontext.project} and tt.deleted = '0')) or (t.objectType = 'doc' and EXISTS(select 1 from zt_doc tt where tt.id = t.objectID and tt.project = #{srf.datacontext.project} and tt.deleted = '0'))) and t.deleted = '0') as DOCCNT
FROM
	dual  ) t1
WHERE t1.type = 'project'  AND t1.deleted = '0' 
t1.project = #{srf.datacontext.project} 

```
### 项目文件库(ByProjectNotFiles)<div id="DocLib_ByProjectNotFiles"></div>
```sql
SELECT
	t1.* 
FROM
	(
	SELECT
		( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`,
		t1.`ACL`,
		t1.`DELETED`,
		t1.`GROUPS`,
		t1.`ID`,
		t1.`MAIN`,
		t1.`NAME`,
		t1.`ORDER`,
		t1.`PRODUCT`,
		t1.`PROJECT`,
		t1.`TYPE`,
		'doc' AS DOCLIBTYPE,
		(
		SELECT
			count( 1 ) 
		FROM
			zt_doc t 
		WHERE
			t.lib = t1.id 
			AND t.project = t1.project 
			AND t.deleted = '0' 
		) AS DOCCNT 
	FROM
	`zt_doclib` t1 
	) t1
WHERE t1.type = 'project'  AND t1.deleted = '0' 
t1.project = #{srf.datacontext.project} 

```
### 所属文档库(CurDocLib)<div id="DocLib_CurDocLib"></div>
```sql
SELECT
t1.`ACL`,
t1.`DELETED`,
'doc' AS `DOCLIBTYPE`,
t1.`GROUPS`,
t1.`ID`,
t1.`MAIN`,
 CONCAT_WS('',case when t1.type = 'product' then '/[产品]/' when t1.type = 'project' then '/[项目]/' else '/' end,case when t1.type = 'product' then t21.`name` when t1.type = 'project' then t11.`name` else '' end,case when t1.type = 'product' then '/' when t1.type = 'project' then '/' else '' end,t1.`name`)  as `NAME`,
t1.`ORDER`,
t1.`PRODUCT`,
t21.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t11.`NAME` AS `PROJECTNAME`,
t1.`TYPE`,
t1.orgid,
t1.MDEPTID
FROM `zt_doclib` t1 
LEFT JOIN zt_project t11 ON t1.PROJECT = t11.ID 
LEFT JOIN zt_product t21 ON t1.PRODUCT = t21.ID
WHERE t1.orgid = #{srf.sessioncontext.srforgid} 

```
### DEFAULT(DEFAULT)<div id="DocLib_Default"></div>
```sql
SELECT
t1.`ACL`,
t1.`DELETED`,
'doc' AS `DOCLIBTYPE`,
t1.`GROUPS`,
t1.`ID`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`,
t1.`MAIN`,
t1.`MDEPTID`,
(select count(1) from zt_module t where t.deleted = '0' and t.type = 'doc' and t.parent = 0 and t.root = t1.id) AS `MODULECNT`,
t1.`NAME`,
(select t.date from zt_action t where t.objectID = t1.id and t.objectType = 'doclib' and t.action = 'created') AS `OPENEDDATE`,
t1.`ORDER`,
t1.`ORGID`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`TYPE`
FROM `zt_doclib` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 
LEFT JOIN `zt_project` t21 ON t1.`PROJECT` = t21.`ID` 

WHERE t1.DELETED = '0' 
( t1.`ORGID` =  ${srfsessioncontext('SRFORGID','{"defname":"ORGID","dename":"ZT_DOCLIB"}')} ) 

```
### 我的收藏(MyFavourites)<div id="DocLib_MyFavourites"></div>
```sql
SELECT
	t1.`ACL`,
	t1.`DELETED`,
	'doc' AS `DOCLIBTYPE`,
	t1.`GROUPS`,
	t1.`ID`,
	'1' AS `ISFAVOURITES`,
	t1.`MAIN`,
	t1.`MDEPTID`,
	t1.`NAME`,
	t1.`ORDER`,
	t1.`ORGID`,
	t1.`PRODUCT`,
	t1.`PROJECT`,
	t1.`TYPE` 
FROM
	zt_doclib t1
WHERE t1.deleted = '0' 
FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 

```
### 根目录(RootModuleMuLu)<div id="DocLib_RootModuleMuLu"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t11.`NAME` AS `DOCLIBNAME`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t21.`NAME` AS `MODULENAME`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
'module'  as `TYPE`
FROM `zt_module` t1 
LEFT JOIN zt_doclib t11 ON t1.ROOT = t11.ID 
LEFT JOIN zt_module t21 ON t1.PARENT = t21.ID
WHERE t1.DELETED = '0' 
(t1.parent = 0 or t1.parent is null ) and t1.type = 'doc' 

```
### 默认（全部数据）(VIEW)<div id="DocLib_View"></div>
```sql
SELECT
t1.`ACL`,
t1.`COLLECTOR`,
t1.`DELETED`,
'doc' AS `DOCLIBTYPE`,
t1.`GROUPS`,
t1.`ID`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`,
t1.`MAIN`,
t1.`MDEPTID`,
(select count(1) from zt_module t where t.deleted = '0' and t.type = 'doc' and t.parent = 0 and t.root = t1.id) AS `MODULECNT`,
t1.`NAME`,
(select t.date from zt_action t where t.objectID = t1.id and t.objectType = 'doclib' and t.action = 'created') AS `OPENEDDATE`,
t1.`ORDER`,
t1.`ORGID`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`TYPE`,
t1.`USERS`
FROM `zt_doclib` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 
LEFT JOIN `zt_project` t21 ON t1.`PROJECT` = t21.`ID` 

WHERE t1.DELETED = '0' 

```

# **文档库分类**(IBZ_DOCLIBMODULE)

### 数据查询(AllDoclibModule_Custom)<div id="DocLibModule_AllDoclibModule_Custom"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t11.`NAME` AS `DOCLIBNAME`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t21.`NAME` AS `MODULENAME`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`, 
'module' AS `DOCQTYPE`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`
FROM `zt_module` t1 
LEFT JOIN zt_doclib t11 ON t1.ROOT = t11.ID 
LEFT JOIN zt_module t21 ON t1.PARENT = t21.ID 

WHERE t1.DELETED = '0' 
(t1.parent = 0 or t1.parent is null ) and t1.type = 'doc' 

```
### 子模块目录(ChildModuleByParent)<div id="DocLibModule_ChildModuleByParent"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t11.`NAME` AS `DOCLIBNAME`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t21.`NAME` AS `MODULENAME`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`, 
'module' AS `DOCQTYPE`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`
FROM `zt_module` t1 
LEFT JOIN zt_doclib t11 ON t1.ROOT = t11.ID 
LEFT JOIN zt_module t21 ON t1.PARENT = t21.ID 

WHERE t1.DELETED = '0' 
t1.type = 'doc' 
t1.parent = ${srfdatacontext('srfparentkey','{"defname":"ROOT","dename":"ZT_MODULE"}')} 

```
### 文档库分类子模块(ChildModuleByRealParent)<div id="DocLibModule_ChildModuleByRealParent"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t11.`NAME` AS `DOCLIBNAME`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t21.`NAME` AS `MODULENAME`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`, 
'module' AS `DOCQTYPE`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`
FROM `zt_module` t1 
LEFT JOIN zt_doclib t11 ON t1.ROOT = t11.ID 
LEFT JOIN zt_module t21 ON t1.PARENT = t21.ID
WHERE t1.deleted = '0' 
t1.type = 'doc' 
t1.parent = ${srfdatacontext('srfparent','{"defname":"ROOT","dename":"ZT_MODULE"}')} 

```
### 数据查询(DEFAULT)<div id="DocLibModule_Default"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
(select count(1) from zt_doc t where t.deleted = '0' and t.module = t1.id) + (select count(1) from zt_module t where t.deleted = '0' and t.type = 'doc' and t.root = t1.root and t.parent = t1.id) AS `DOCCNT`,
t21.`NAME` AS `DOCLIBNAME`,
t1.`GRADE`,
t1.`ID`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t11.`NAME` AS `MODULENAME`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_module` t11 ON t1.`PARENT` = t11.`ID` 
LEFT JOIN `zt_doclib` t21 ON t1.`ROOT` = t21.`ID` 

WHERE t1.DELETED = '0' 

```
### 数据查询(AllDoclibModule)<div id="DocLibModule_DefaultDoclib"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t11.`NAME` AS `DOCLIBNAME`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t21.`NAME` AS `MODULENAME`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`, 
'module' AS `DOCQTYPE`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`
FROM `zt_module` t1 
LEFT JOIN zt_doclib t11 ON t1.ROOT = t11.ID 
LEFT JOIN zt_module t21 ON t1.PARENT = t21.ID 

WHERE t1.DELETED = '0' 
t1.type = 'doc' 

```
### 我的收藏(MyFavourites)<div id="DocLibModule_MyFavourites"></div>
```sql
SELECT
	t1.`BRANCH`,
	t1.`DELETED`,
	t1.`GRADE`,
	t1.`ID`,
	'module' AS `DOCQTYPE`,
	1 AS `ISFAVOURITES`,
	( CASE WHEN EXISTS ( SELECT 1 FROM ZT_MODULE WHERE PARENT = t1.`ID` ) THEN FALSE ELSE TRUE END ) AS `ISLEAF`,
	t1.`NAME`,
	t1.`ORDER`,
	t1.`OWNER`,
	t1.`PARENT`,
	t1.`PATH`,
	t1.`ROOT`,
	t1.`SHORT`,
	t1.`TYPE` 
FROM
	`zt_module` t1
WHERE t1.deleted = '0' 
FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 

```
### 父模块(ParentModule)<div id="DocLibModule_ParentModule"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t11.`NAME` AS `DOCLIBNAME`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t21.`NAME` AS `MODULENAME`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`, 
'module' AS `DOCQTYPE`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`
FROM `zt_module` t1 
LEFT JOIN zt_doclib t11 ON t1.ROOT = t11.ID 
LEFT JOIN zt_module t21 ON t1.PARENT = t21.ID 

WHERE t1.DELETED = '0' 
t1.type = 'doc' 
t1.id <> #{srf.webcontext.id} and find_in_set(#{srf.webcontext.id},t1.path) = 0 

```
### 所有根模块目录(RootModuleMuLu)<div id="DocLibModule_RootModuleMuLu"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t11.`NAME` AS `DOCLIBNAME`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t21.`NAME` AS `MODULENAME`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
'module'  as `TYPE`,
'module' AS `DOCQTYPE`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`,
(select count(1) from zt_doc t where t.deleted = '0' and t.module = t1.id) + (select count(1) from zt_module t where t.deleted = '0' and t.type = 'doc' and t.root = t1.root and t.parent = t1.id) as doccnt
FROM `zt_module` t1 
LEFT JOIN zt_doclib t11 ON t1.ROOT = t11.ID 
LEFT JOIN zt_module t21 ON t1.PARENT = t21.ID 

WHERE t1.DELETED = '0' 
(t1.parent = 0 or t1.parent is null ) and t1.type = 'doc' 

```
### 根模块目录动态(RootModuleMuLuByRoot)<div id="DocLibModule_RootModuleMuLuByRoot"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t11.`NAME` AS `DOCLIBNAME`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t21.`NAME` AS `MODULENAME`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`, 
'module' AS `DOCQTYPE`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`
FROM `zt_module` t1 
LEFT JOIN zt_doclib t11 ON t1.ROOT = t11.ID 
LEFT JOIN zt_module t21 ON t1.PARENT = t21.ID 

WHERE t1.DELETED = '0' 
(t1.parent = 0 or t1.parent is null ) and t1.type = 'doc' 
t1.root = #{srf.webcontext.id} 

```
### 根模块目录动态(RootModuleMuLuBysrfparentkey)<div id="DocLibModule_RootModuleMuLuBysrfparentkey"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t11.`NAME` AS `DOCLIBNAME`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t21.`NAME` AS `MODULENAME`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`,
'module' AS `DOCQTYPE`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`
FROM `zt_module` t1 
LEFT JOIN zt_doclib t11 ON t1.ROOT = t11.ID 
LEFT JOIN zt_module t21 ON t1.PARENT = t21.ID 


WHERE t1.DELETED = '0' 
(t1.parent = 0 or t1.parent is null ) and t1.type = 'doc' 
t1.root = #{srf.webcontext.srfparentkey} 

```
### 默认（全部数据）(VIEW)<div id="DocLibModule_View"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`COLLECTOR`,
t1.`DELETED`,
(select count(1) from zt_doc t where t.deleted = '0' and t.module = t1.id) + (select count(1) from zt_module t where t.deleted = '0' and t.type = 'doc' and t.root = t1.root and t.parent = t1.id) AS `DOCCNT`,
t21.`NAME` AS `DOCLIBNAME`,
t1.`GRADE`,
t1.`ID`,
( CASE WHEN FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.collector ) > 0 THEN 1 ELSE 0 END ) AS `ISFAVOURITES`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t11.`NAME` AS `MODULENAME`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_module` t11 ON t1.`PARENT` = t11.`ID` 
LEFT JOIN `zt_doclib` t21 ON t1.`ROOT` = t21.`ID` 

WHERE t1.DELETED = '0' 

```

# **动态数据看板**(DYNADASHBOARD)

### DEFAULT(DEFAULT)<div id="DynaDashboard_Default"></div>
```sql
SELECT
t1.`APPID`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DYNADASHBOARDID`,
t1.`DYNADASHBOARDNAME`,
t1.`MODELID`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERID`
FROM `T_DYNADASHBOARD` t1 

```
### 默认（全部数据）(VIEW)<div id="DynaDashboard_View"></div>
```sql
SELECT
t1.`APPID`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DYNADASHBOARDID`,
t1.`DYNADASHBOARDNAME`,
t1.`MODEL`,
t1.`MODELID`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERID`
FROM `T_DYNADASHBOARD` t1 

```

# **effort**(ZT_EFFORT)

### DEFAULT(DEFAULT)<div id="Effort_Default"></div>
```sql
SELECT
t1.`BEGIN`,
t1.`DATE`,
t1.`DESC`,
t1.`END`,
t1.`ID`,
t1.`IDVALUE`,
t1.`NAME`,
t1.`STATUS`,
t1.`TODO`,
t1.`TYPE`,
t1.`USER`
FROM `zt_effort` t1 

```
### 默认（全部数据）(VIEW)<div id="Effort_View"></div>
```sql
SELECT
t1.`BEGIN`,
t1.`DATE`,
t1.`DESC`,
t1.`END`,
t1.`ID`,
t1.`IDVALUE`,
t1.`NAME`,
t1.`STATUS`,
t1.`TODO`,
t1.`TYPE`,
t1.`USER`
FROM `zt_effort` t1 

```

# **员工负载表**(IBZ_EMPLOYEELOAD)

### 数据查询(DEFAULT)<div id="EmpLoyeeload_Default"></div>
```sql
SELECT
t1.`ASSIGN`,
t1.`ASSIGNEDTO`,
t1.`ID`,
０ AS `LEFT`,
t1.`NAME`,
t1.`PROJECT`,
t11.`NAME` AS `PROJECTNAME`,
０ AS `TASKCNT`,
０ AS `TOTALLEFT`,
０ AS `TOTALTASKCNT`,
０ AS `WORKLOAD`
FROM `ZT_TASK` t1 
LEFT JOIN `zt_project` t11 ON t1.`PROJECT` = t11.`ID` 

```
### 获取员工负载表(GETWOERKLOAD)<div id="EmpLoyeeload_GETWOERKLOAD"></div>
```sql
select t1.* from(select t1.assignedTo,t1.project,t1.projectname,t1.taskcnt,t1.`left`,t1.totaltaskcnt,t1.totalleft,t1.workload,'assign' as isassigned from (
select t1.*,t4.taskcnt as totaltaskcnt,t4.`left` as totalleft,CONCAT(ROUND( t4.`left`/ ((case when  #{srf.datacontext.workday} 
is null then 6 else 
#{srf.datacontext.workday}  
end) * (case when 
#{srf.datacontext.everydaytime}  
is null then 7 else 
#{srf.datacontext.everydaytime}  
end)) * 100, 2),'%')  as WORKLOAD from ( select  t1.assignedTo,t1.project,t1.projectname,COUNT(1) as taskcnt,sum(t1.`left`) as `left` from( select t1.id,t1.`name`,t1.`left`,t1.project ,t2.`name` as projectname,t1.assignedTo from zt_task t1 left join zt_project t2 on t1.project = t2.id 
where t1.deleted = '0' and t1.`status` in ('wait','doing') and t2.deleted = '0' and t2.`status` in ('wait','doing') and t1.assignedTo <> '' and t1.parent >= 0 and not EXISTS(select  1  from zt_team t where t.type = 'task' and t.root = t1.id) 
union 
select t1.id,t1.`name`,t3.`left`,t1.project ,t2.`name` as projectname,t3.account as assignedTo from zt_task t1 left join zt_project t2 on t1.project = t2.id 
INNER join zt_team t3 on t1.id = t3.root and t3.type = 'task'
where t1.deleted = '0' and t1.`status` in ('wait','doing') and t2.deleted = '0' and t2.`status` in ('wait','doing')  and t1.`left` > 0 ) t1 GROUP BY t1.assignedTo,t1.project,t1.projectname) t1 left join zt_user tt on tt.account = t1.assignedTo 
left join  ( select  t1.assignedTo,COUNT(1) as taskcnt,sum(t1.`left`) as `left` from( select t1.id,t1.`name`,t1.`left`,t1.project ,t2.`name` as projectname,t1.assignedTo from zt_task t1 left join zt_project t2 on t1.project = t2.id 
where t1.deleted = '0' and t1.`status` in ('wait','doing') and t2.deleted = '0' and t2.`status` in ('wait','doing') and t1.assignedTo <> '' and t1.parent >= 0 and not EXISTS(select  1  from zt_team t where t.type = 'task' and t.root = t1.id) 
union 
select t1.id,t1.`name`,t3.`left`,t1.project ,t2.`name` as projectname,t3.account as assignedTo from zt_task t1 left join zt_project t2 on t1.project = t2.id 
INNER join zt_team t3 on t1.id = t3.root and t3.type = 'task'
where t1.deleted = '0' and t1.`status` in ('wait','doing') and t2.deleted = '0' and t2.`status` in ('wait','doing')  and t1.`left` > 0 ) t1 GROUP BY t1.assignedTo) t4 on t4.assignedTo = t1.assignedTo
where tt.dept =  #{srf.datacontext.dept} 
or  #{srf.datacontext.dept} 
is null) t1 
union
select t1.assignedTo,t1.project,t1.projectname,t1.taskcnt,t1.`left`,t1.totaltaskcnt,t1.totalleft,t1.workload, t1.isassigned as isassigned from (select  t1.account as assignedTo,t2.id as project,t2.`name` as projectname, 0 as taskcnt,0 as `left`,0 as totaltaskcnt,0 as totalleft,'0%' as workload,'noassign' as isassigned  from zt_team t1 
LEFT JOIN zt_project t2 on t2.id = t1.root
where t2.`status` not in ('cancel','closed','done','suspended')  and t1.type = 'project' 
and t1.account not in (SELECT assignedTo from zt_task where project = t1.root and `status` not in ('cancel','closed','done','pause') and assignedTo <> '' GROUP BY assignedTo) ORDER BY t1.ACCOUNT,t2.`name`)t1) t1 
where (t1.isassigned = #{srf.datacontext.assign}) or (#{srf.datacontext.assign} is null and t1.isassigned = 'assign')
```
### 默认（全部数据）(VIEW)<div id="EmpLoyeeload_View"></div>
```sql
SELECT
t1.`ASSIGN`,
t1.`ASSIGNEDTO`,
t1.`ID`,
０ AS `LEFT`,
t1.`NAME`,
t1.`PROJECT`,
t11.`NAME` AS `PROJECTNAME`,
０ AS `TASKCNT`,
０ AS `TOTALLEFT`,
０ AS `TOTALTASKCNT`,
０ AS `WORKLOAD`
FROM `ZT_TASK` t1 
LEFT JOIN `zt_project` t11 ON t1.`PROJECT` = t11.`ID` 

```

# **实体2**(ENTITY2)

### 数据查询(DEFAULT)<div id="Entity2_Default"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`ENTITY2ID`,
t1.`ENTITY2NAME`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_ENTITY2` t1 

```
### 默认（全部数据）(VIEW)<div id="Entity2_View"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`ENTITY2ID`,
t1.`ENTITY2NAME`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_ENTITY2` t1 

```

# **entry**(ZT_ENTRY)

### DEFAULT(DEFAULT)<div id="Entry_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CALLEDTIME`,
t1.`CODE`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`DELETED`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`FREEPASSWD`,
t1.`ID`,
t1.`IP`,
t1.`KEY`,
t1.`NAME`
FROM `zt_entry` t1 

WHERE t1.DELETED = '0' 

```
### 默认（全部数据）(VIEW)<div id="Entry_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CALLEDTIME`,
t1.`CODE`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`DELETED`,
t1.`DESC`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`FREEPASSWD`,
t1.`ID`,
t1.`IP`,
t1.`KEY`,
t1.`NAME`
FROM `zt_entry` t1 

WHERE t1.DELETED = '0' 

```

# **extension**(ZT_EXTENSION)

### DEFAULT(DEFAULT)<div id="Extension_Default"></div>
```sql
SELECT
t1.`AUTHOR`,
t1.`CODE`,
t1.`DEPENDS`,
t1.`ID`,
t1.`INSTALLEDTIME`,
t1.`NAME`,
t1.`SITE`,
t1.`STATUS`,
t1.`TYPE`,
t1.`VERSION`,
t1.`ZENTAOCOMPATIBLE`
FROM `zt_extension` t1 

```
### 默认（全部数据）(VIEW)<div id="Extension_View"></div>
```sql
SELECT
t1.`AUTHOR`,
t1.`CODE`,
t1.`DEPENDS`,
t1.`DESC`,
t1.`DIRS`,
t1.`FILES`,
t1.`ID`,
t1.`INSTALLEDTIME`,
t1.`LICENSE`,
t1.`NAME`,
t1.`SITE`,
t1.`STATUS`,
t1.`TYPE`,
t1.`VERSION`,
t1.`ZENTAOCOMPATIBLE`
FROM `zt_extension` t1 

```

# **附件**(ZT_FILE)

### DEFAULT(DEFAULT)<div id="File_Default"></div>
```sql
SELECT
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
t1.`DELETED`,
t1.`DOWNLOADS`,
t1.`EXTENSION`,
t1.`EXTRA`,
t1.`ID`,
t1.`OBJECTID`,
t1.`OBJECTTYPE`,
t1.`PATHNAME`,
t1.`SIZE`,
CONCAT_WS('',case when (t1.size/1024) > 1024 then ROUND(t1.size/(1024 * 1024), 1) when (t1.size/1024) > 1 then ROUND(t1.size/1024, 1) else ROUND(t1.size, 1) end, case when (t1.size/1024) > 1024 then 'MB' when (t1.size/1024) > 1 then 'KB' else 'B' end) AS `STRSIZE`,
t1.`TITLE`
FROM `zt_file` t1 

WHERE t1.DELETED = '0' 

```
### 文件库查询(DocLibFile)<div id="File_DocLibFile"></div>
```sql
SELECT
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
t1.`DELETED`,
t1.`DOWNLOADS`,
t1.`EXTENSION`,
t1.`EXTRA`,
t1.`ID`,
t1.`OBJECTID`,
t1.`OBJECTTYPE`,
t1.`PATHNAME`,
CONCAT_WS('',case when (t1.size/1024) > 1024 then ROUND(t1.size/(1024 * 1024), 1) when (t1.size/1024) > 1 then ROUND(t1.size/1024, 1) else ROUND(t1.size, 1) end, case when (t1.size/1024) > 1024 then 'MB' when (t1.size/1024) > 1 then 'KB' else 'B' end) as `STRSIZE`,
t1.size,
CONCAT_WS('',t1.`TITLE`,' [',UPPER(t1.objectType),' #',t1.objectID,']') AS `TITLE`,
'file' as doclibtype
FROM `zt_file` t1
WHERE t1.deleted = '0' 
((t1.objectType = 'project' and t1.objectID = #{srf.datacontext.project}) or (t1.objectType = 'task' and EXISTS(select 1 from zt_task t where t.deleted = '0' and t.project = #{srf.datacontext.project} and t.id = t1.objectID)) or (t1.objectType = 'doc' and EXISTS(select 1 from zt_doc t where t.deleted = '0' and t.project = #{srf.datacontext.project} and t.id = t1.objectID)) or (t1.objectType = 'build' and EXISTS(select 1 from zt_build t where t.deleted = '0' and t.project = #{srf.datacontext.project} and t.id = t1.objectID)) ) 

```
### 文件库查询(ProductDocLibFile)<div id="File_ProductDocLibFile"></div>
```sql
SELECT
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
t1.`DELETED`,
t1.`DOWNLOADS`,
t1.`EXTENSION`,
t1.`EXTRA`,
t1.`ID`,
t1.`OBJECTID`,
t1.`OBJECTTYPE`,
t1.`PATHNAME`,
CONCAT_WS('',case when (t1.size/1024) > 1024 then ROUND(t1.size/(1024 * 1024), 1) when (t1.size/1024) > 1 then ROUND(t1.size/1024, 1) else ROUND(t1.size, 1) end, case when (t1.size/1024) > 1024 then 'MB' when (t1.size/1024) > 1 then 'KB' else 'B' end) as `STRSIZE`,
t1.size,
CONCAT_WS('',t1.`TITLE`,' [',UPPER(t1.objectType),' #',t1.objectID,']') AS `TITLE`,
'file' as doclibtype
FROM `zt_file` t1
WHERE t1.deleted = '0' 
((t1.objectType = 'product' and t1.objectID = #{srf.datacontext.product}) or (t1.objectType = 'story' and EXISTS(select 1 from zt_story t where t.deleted = '0' and t.product = #{srf.datacontext.product} and t.id = t1.objectID)) or (t1.objectType = 'doc' and EXISTS(select 1 from zt_doc t where t.deleted = '0' and t.product = #{srf.datacontext.product} and t.id = t1.objectID)) or (t1.objectType = 'bug' and EXISTS(select 1 from zt_bug t where t.deleted = '0' and t.product = #{srf.datacontext.product} and t.id = t1.objectID)) ) 

```
### 动态(根据类型过滤)(Type)<div id="File_Type"></div>
```sql
SELECT
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
t1.`DELETED`,
t1.`DOWNLOADS`,
t1.`EXTENSION`,
t1.`EXTRA`,
t1.`ID`,
t1.`OBJECTID`,
t1.`OBJECTTYPE`,
t1.`PATHNAME`,
t1.`SIZE`,
CONCAT_WS('',case when (t1.size/1024) > 1024 then ROUND(t1.size/(1024 * 1024), 1) when (t1.size/1024) > 1 then ROUND(t1.size/1024, 1) else ROUND(t1.size, 1) end, case when (t1.size/1024) > 1024 then 'MB' when (t1.size/1024) > 1 then 'KB' else 'B' end) AS `STRSIZE`,
t1.`TITLE`
FROM `zt_file` t1 

WHERE t1.DELETED = '0' 
( t1.`OBJECTID` = ${srfdatacontext('srfparentkey','{"defname":"OBJECTID","dename":"ZT_FILE"}')}  AND  t1.`OBJECTTYPE` =  ${srfdatacontext('objecttype','{"defname":"OBJECTTYPE","dename":"ZT_FILE"}')}  AND  t1.`EXTRA` <> 'editor' ) 

```
### 查询附件(TypeNotBySrfparentkey)<div id="File_TypeNotBySrfparentkey"></div>
```sql
SELECT
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
t1.`DELETED`,
t1.`DOWNLOADS`,
t1.`EXTENSION`,
t1.`EXTRA`,
t1.`ID`,
t1.`OBJECTID`,
t1.`OBJECTTYPE`,
t1.`PATHNAME`,
t1.`SIZE`,
CONCAT_WS('',case when (t1.size/1024) > 1024 then ROUND(t1.size/(1024 * 1024), 1) when (t1.size/1024) > 1 then ROUND(t1.size/1024, 1) else ROUND(t1.size, 1) end, case when (t1.size/1024) > 1024 then 'MB' when (t1.size/1024) > 1 then 'KB' else 'B' end) AS `STRSIZE`,
t1.`TITLE`
FROM `zt_file` t1 

WHERE t1.DELETED = '0' 
( t1.`OBJECTID` = ${srfdatacontext('objectid','{"defname":"OBJECTID","dename":"ZT_FILE"}')}  AND  t1.`OBJECTTYPE` =  ${srfdatacontext('objecttype','{"defname":"OBJECTTYPE","dename":"ZT_FILE"}')}  AND  t1.`EXTRA` <> 'editor' ) 

```
### 默认（全部数据）(VIEW)<div id="File_View"></div>
```sql
SELECT
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
t1.`DELETED`,
t1.`DOWNLOADS`,
t1.`EXTENSION`,
t1.`EXTRA`,
t1.`ID`,
t1.`OBJECTID`,
t1.`OBJECTTYPE`,
t1.`PATHNAME`,
t1.`SIZE`,
CONCAT_WS('',case when (t1.size/1024) > 1024 then ROUND(t1.size/(1024 * 1024), 1) when (t1.size/1024) > 1 then ROUND(t1.size/1024, 1) else ROUND(t1.size, 1) end, case when (t1.size/1024) > 1024 then 'MB' when (t1.size/1024) > 1 then 'KB' else 'B' end) AS `STRSIZE`,
t1.`TITLE`
FROM `zt_file` t1 

WHERE t1.DELETED = '0' 

```

# **群组**(ZT_GROUP)

### DEFAULT(DEFAULT)<div id="Group_Default"></div>
```sql
SELECT
t1.`DESC`,
t1.`ID`,
t1.`NAME`,
t1.`ROLE`
FROM `zt_group` t1 

```
### 默认（全部数据）(VIEW)<div id="Group_View"></div>
```sql
SELECT
t1.`ACL`,
t1.`DESC`,
t1.`ID`,
t1.`NAME`,
t1.`ROLE`
FROM `zt_group` t1 

```

# **群组权限**(ZT_GROUPPRIV)

### DEFAULT(DEFAULT)<div id="GroupPriv_Default"></div>
```sql
SELECT
t1.`GROUP`,
concat(t1.`GROUP`,'__',t1.`MODULE`,'__',t1.`METHOD`) AS `ID`,
t1.`METHOD`,
t1.`MODULE`
FROM `zt_grouppriv` t1 

```
### 默认（全部数据）(VIEW)<div id="GroupPriv_View"></div>
```sql
SELECT
t1.`GROUP`,
concat(t1.`GROUP`,'__',t1.`MODULE`,'__',t1.`METHOD`) AS `ID`,
t1.`METHOD`,
t1.`MODULE`
FROM `zt_grouppriv` t1 

```

# **操作历史**(ZT_HISTORY)

### DEFAULT(DEFAULT)<div id="History_Default"></div>
```sql
SELECT
t1.`ACTION`,
t1.`DIFF`,
t1.`FIELD`,
t1.`ID`,
t1.`NEW`,
t1.`OLD`
FROM `zt_history` t1 

WHERE ( (t1.`FIELD` NOT LIKE '%cnt')  AND  (t1.`FIELD` NOT LIKE '%pk') ) 

```
### 默认（全部数据）(VIEW)<div id="History_View"></div>
```sql
SELECT
t1.`ACTION`,
t1.`DIFF`,
t1.`FIELD`,
t1.`ID`,
t1.`NEW`,
t1.`OLD`
FROM `zt_history` t1 

```

# **关键字**(IBIZPRO_KEYWORD)

### 数据查询(DEFAULT)<div id="IBIZProKeyword_Default"></div>
```sql
SELECT
t1.`ID`
FROM `T_IBIZPRO_KEYWORD` t1 

```
### 默认（全部数据）(VIEW)<div id="IBIZProKeyword_View"></div>
```sql
SELECT
t1.`ID`
FROM `T_IBIZPRO_KEYWORD` t1 

```

# **消息**(IBIZPRO_MESSAGE)

### 数据查询(DEFAULT)<div id="IBIZProMessage_Default"></div>
```sql
SELECT
t1.`IBIZPRO_MESSAGEID`,
t1.`IBIZPRO_MESSAGENAME`,
t1.`ISLINK`,
t1.`ISRETRY`,
t1.`ISSYNC`,
t1.`LINKURL`,
t1.`RETRYINTERVALTIME`,
t1.`RETRYTIMES`,
t1.`SUBJECT`,
t1.`TYPE`
FROM `T_IBIZPRO_MESSAGE` t1 

```
### 用户全部消息(UserAllMessages)<div id="IBIZProMessage_UserAllMessages"></div>
```sql

```
### 用户未读信息(UserUnreadMessages)<div id="IBIZProMessage_UserUnreadMessages"></div>
```sql

```
### 默认（全部数据）(VIEW)<div id="IBIZProMessage_View"></div>
```sql
SELECT
t1.`BCC`,
t1.`CC`,
t1.`CONTENT`,
t1.`FROM`,
t1.`IBIZPRO_MESSAGEID`,
t1.`IBIZPRO_MESSAGENAME`,
t1.`ISLINK`,
t1.`ISRETRY`,
t1.`ISSYNC`,
t1.`LINKURL`,
t1.`PARAM`,
t1.`RETRYINTERVALTIME`,
t1.`RETRYTIMES`,
t1.`SUBJECT`,
t1.`TO`,
t1.`TYPE`
FROM `T_IBIZPRO_MESSAGE` t1 

```

# **系统插件**(IBIZPRO_PLUGIN)

### 数据查询(DEFAULT)<div id="IBIZProPlugin_Default"></div>
```sql
SELECT
0 AS `COMMENTCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
0 AS `DOWNLOADCOUNT`,
t1.`DOWNLOADURL`,
t1.`IBIZPRO_PLUGINID`,
t1.`IBIZPRO_PLUGINNAME`,
t1.`KEYWORD`,
0 AS `SCORE`,
t1.`TAG`,
t1.`TYPE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`VERSION`
FROM `T_IBIZPRO_PLUGIN` t1 

```
### 默认（全部数据）(VIEW)<div id="IBIZProPlugin_View"></div>
```sql
SELECT
0 AS `COMMENTCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
0 AS `DOWNLOADCOUNT`,
t1.`DOWNLOADURL`,
t1.`IBIZPRO_PLUGINID`,
t1.`IBIZPRO_PLUGINNAME`,
t1.`KEYWORD`,
0 AS `SCORE`,
t1.`TAG`,
t1.`TYPE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`VERSION`
FROM `T_IBIZPRO_PLUGIN` t1 

```

# **标签**(IBIZPRO_TAG)

### 数据查询(DEFAULT)<div id="IBIZProTag_Default"></div>
```sql
SELECT
t1.`ID`
FROM `T_IBIZPRO_TAG` t1 

```
### 默认（全部数据）(VIEW)<div id="IBIZProTag_View"></div>
```sql
SELECT
t1.`ID`
FROM `T_IBIZPRO_TAG` t1 

```

# **平台产品**(IBZPRO_PRODUCT)

### 数据查询(DEFAULT)<div id="IBZProProduct_Default"></div>
```sql
SELECT
t1.`CODE`,
t1.`IBIZ_ID`,
t1.`ID`,
t1.`NAME`
FROM `zt_product` t1 

```
### 默认（全部数据）(VIEW)<div id="IBZProProduct_View"></div>
```sql
SELECT
t1.`CODE`,
t1.`IBIZ_ID`,
t1.`ID`,
t1.`NAME`
FROM `zt_product` t1 

```

# **需求**(IBZPRO_STORY)

### 数据查询(DEFAULT)<div id="IBZProStory_Default"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`IBIZ_ID`,
t1.`IBIZ_SOURCEID`,
t1.`IBIZ_SOURCENAME`,
t1.`IBIZ_SOURCEOBJECT`,
t1.`ID`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
t1.`MODULE`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PRI`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCENOTE`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_story` t1 

```
### 默认（全部数据）(VIEW)<div id="IBZProStory_View"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`IBIZ_ID`,
t1.`IBIZ_SOURCEID`,
t1.`IBIZ_SOURCENAME`,
t1.`IBIZ_SOURCEOBJECT`,
t1.`ID`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
t1.`MODULE`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PRI`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCENOTE`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_story` t1 

```

# **需求模块**(IBZPRO_STORYMODULE)

### 数据查询(DEFAULT)<div id="IBZProStoryModule_Default"></div>
```sql
SELECT
t1.`DELETED`,
t1.`GRADE`,
t1.`IBIZ_ID`,
t1.`IBIZ_STORYTYPE`,
t1.`ID`,
t1.`NAME`,
t1.`OWNER`,
t1.`PARENT`,
t1.`PATH`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_product` t11 ON t1.`ROOT` = t11.`ID` 

```
### 默认（全部数据）(VIEW)<div id="IBZProStoryModule_View"></div>
```sql
SELECT
t1.`COLLECTOR`,
t1.`DELETED`,
t1.`GRADE`,
t1.`IBIZ_ID`,
t1.`IBIZ_STORYTYPE`,
t1.`ID`,
t1.`NAME`,
t1.`OWNER`,
t1.`PARENT`,
t1.`PATH`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_product` t11 ON t1.`ROOT` = t11.`ID` 

```

# **系统模板**(IBZPRO_SYSTPL)

### 数据查询(DEFAULT)<div id="IBZProSysTpl_Default"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`FILE`,
t1.`IBIZ_SOURCEOBJECT`,
t1.`IBZPRO_SYSTPLID`,
t1.`IBZPRO_SYSTPLNAME`,
t1.`PUBLIC`,
t1.`TPLTYPE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZPRO_SYSTPL` t1 

```
### 默认（全部数据）(VIEW)<div id="IBZProSysTpl_View"></div>
```sql
SELECT
t1.`CONTENT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`FILE`,
t1.`IBIZ_SOURCEOBJECT`,
t1.`IBZPRO_SYSTPLID`,
t1.`IBZPRO_SYSTPLNAME`,
t1.`PUBLIC`,
t1.`TPLTYPE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZPRO_SYSTPL` t1 

```

# **运行生产系统**(IBZPRO_SYSTEM)

### 数据查询(DEFAULT)<div id="IBZProSystem_Default"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`IBZPRO_SYSTEMID`,
t1.`IBZPRO_SYSTEMNAME`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZPRO_SYSTEM` t1 

```
### 默认（全部数据）(VIEW)<div id="IBZProSystem_View"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`IBZPRO_SYSTEMID`,
t1.`IBZPRO_SYSTEMNAME`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZPRO_SYSTEM` t1 

```

# **文档**(IBZ_DOC)

### 产品下所有文档（用户）(AllDoc)<div id="IBzDoc_AllDoc"></div>
```sql
SELECT
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`IBZ_DOCID`,
t1.`IBZ_DOCNAME`,
t1.`ISCOLLECT`,
t1.`LIB`,
t1.`OBJECTTYPE`,
t1.`SIZE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZ_DOC` t1 

```
### DEFAULT(DEFAULT)<div id="IBzDoc_Default"></div>
```sql
SELECT
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`IBZ_DOCID`,
t1.`IBZ_DOCNAME`,
t1.`ISCOLLECT`,
t1.`LIB`,
t1.`OBJECTTYPE`,
t1.`ORDER`,
t1.`SIZE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZ_DOC` t1 

```
### 默认（全部数据）(VIEW)<div id="IBzDoc_View"></div>
```sql
SELECT
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`IBZ_DOCID`,
t1.`IBZ_DOCNAME`,
t1.`ISCOLLECT`,
t1.`LIB`,
t1.`OBJECTTYPE`,
t1.`ORDER`,
t1.`SIZE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZ_DOC` t1 

```

# **实体**(IBZ_LOGIN)

### DEFAULT(DEFAULT)<div id="IbiLogin_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`COMMITER`,
t1.`ID`,
t1.`REALNAME`
FROM `zt_user` t1 

```
### 默认（全部数据）(VIEW)<div id="IbiLogin_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`COMMITER`,
t1.`ID`,
t1.`REALNAME`
FROM `zt_user` t1 

```

# **索引检索**(IBIZPRO_INDEX)

### 数据查询(DEFAULT)<div id="IbizproIndex_Default"></div>
```sql
SELECT
t1.`DELETED`,
t1.`INDEXID`,
t1.`INDEXNAME`,
t1.`INDEX_TYPE`,
t1.`MDEPTID`,
t1.`ORGID`,
t1.INDEXDESC,
t1.color,
t1.project,
t1.acllist,
t1.acl
FROM (SELECT
'bug' AS `INDEX_TYPE`,v1.`ID` AS `INDEXID`
,v1.`TITLE` AS `INDEXNAME`
,v1.deleted AS `DELETED`
,v1.orgid AS `ORGID`
,v1.MDEPTID AS `MDEPTID`
,v1.steps as INDEXDESC
,v1.color
,v1.project
,v1.acllist
,v1.acl
FROM
(SELECT
t1.`ID`,
t1.`TITLE`,
t11.orgid,
t11.MDEPTID,
t1.deleted,
t1.steps,
t1.color,
t11.acl,
CONCAT_WS(',',t11.CREATEDBY,t11.qd,t11.po,t11.rd) as acllist,
(select GROUP_CONCAT(t.project) from zt_projectproduct t where t.product = t1.product GROUP BY t.product) as project
FROM `zt_bug` t1 left join zt_product t11 on t11.id = t1.product 
where t11.deleted = '0'
) v1
UNION ALL
SELECT
'case' AS `INDEX_TYPE`,v2.`ID` AS `INDEXID`
,v2.`TITLE` AS `INDEXNAME`
,v2.deleted AS `DELETED`
,v2.orgid AS `ORGID`
,v2.MDEPTID AS `MDEPTID`
,v2.precondition as INDEXDESC
,v2.color
,v2.project
,v2.acllist
,v2.acl
FROM
(SELECT
t1.`ID`,
t1.`TITLE`,
t11.orgid,
t11.MDEPTID,
t1.deleted,
t1.`PRECONDITION`,
t1.color,
t11.acl,
CONCAT_WS(',',t11.CREATEDBY,t11.qd,t11.po,t11.rd) as acllist,
(select GROUP_CONCAT(t.project) from zt_projectproduct t where t.product = t1.product GROUP BY t.product) as project
FROM `zt_case` t1 left join zt_product t11 on t11.id = t1.product 
where t11.deleted = '0'
) v2
UNION ALL
SELECT
'product' AS `INDEX_TYPE`
,v3.`ID` AS `INDEXID`
,v3.`NAME` AS `INDEXNAME`
,v3.deleted AS `DELETED`
,v3.orgid AS `ORGID`
,v3.MDEPTID AS `MDEPTID`
,v3.`desc` as INDEXDESC
, null as color
,v3.project
,v3.acllist
,v3.acl
FROM
(SELECT
t1.`ID`,
t1.`NAME`,
t1.orgid,
t1.MDEPTID,
t1.deleted,
t1.`desc`,
t1.acl,
CONCAT_WS(',',t1.CREATEDBY,t1.qd,t1.po,t1.rd) as acllist,
(select GROUP_CONCAT(t.project) from zt_projectproduct t where t.product = t1.id GROUP BY t.product) as project
FROM `zt_product` t1 
) v3
UNION ALL
SELECT
'project' AS `INDEX_TYPE`,v4.`ID` AS `INDEXID`
,v4.`NAME` AS `INDEXNAME`
,v4.deleted AS `DELETED`
,v4.orgid AS `ORGID`
,v4.MDEPTID AS `MDEPTID`
,v4.`desc` as INDEXDESC
,null as color
,v4.project
,v4.acllist
,v4.acl
FROM
(SELECT
t1.`ID`,
t1.`NAME`,
t1.orgid,
t1.MDEPTID,
t1.deleted,
t1.`desc`,
t1.acl,
CONCAT_WS(',',t1.openedBy,t1.pm,t1.qd,t1.po,t1.rd) as acllist,
t1.id as project
FROM `zt_project` t1 
) v4
UNION ALL
SELECT
'story' AS `INDEX_TYPE`,v5.`ID` AS `INDEXID`
,v5.`TITLE` AS `INDEXNAME`
,v5.deleted AS `DELETED`
,v5.orgid AS `ORGID`
,v5.MDEPTID AS `MDEPTID`
,v5.spec as INDEXDESC
,v5.color
,v5.project
,v5.acllist
,v5.acl
FROM
(SELECT
t1.`ID`,
t1.`TITLE`,
t11.orgid,
t11.MDEPTID,
t1.deleted,
t21.spec,
t1.color,
t11.acl,
CONCAT_WS(',',t11.CREATEDBY,t11.qd,t11.po,t11.rd) as acllist,
(select GROUP_CONCAT(t.project) from zt_projectproduct t where t.product = t1.product GROUP BY t.product) as project
FROM `zt_story` t1 left join zt_product t11 on t11.id = t1.product 
left join zt_storyspec t21 on t21.story = t1.id and t1.version = t21.version
where t11.deleted = '0' 
) v5
UNION ALL
SELECT
'task' AS `INDEX_TYPE`
,v6.`ID` AS `INDEXID`
,v6.`NAME` AS `INDEXNAME`
,v6.deleted AS `DELETED`
,v6.orgid AS `ORGID`
,v6.MDEPTID AS `MDEPTID`
,v6.`desc` as INDEXDESC
,v6.color
,v6.project
,v6.acllist
,v6.acl
FROM
(SELECT
t1.`ID`,
t1.`NAME`,
t11.orgid,
t11.MDEPTID,
t1.deleted,
t1.`desc`,
t1.color,
t1.project,
t11.acl,
CONCAT_WS(',',t11.openedBy,t11.pm,t11.qd,t11.po,t11.rd) as acllist
FROM `zt_task` t1 left join zt_project t11 on t11.id = t1.project 
where t11.deleted = '0'
) v6
) t1
WHERE t1.DELETED = '0' 
t1.orgid = #{srf.sessioncontext.srforgid} 
((t1.acl = 'open') or (FIND_IN_SET(#{srf.sessioncontext.srfloginname}, t1.acllist)) or (EXISTS(select 1 from zt_team t where t.type = 'project' and t.account = #{srf.sessioncontext.srfloginname} and FIND_IN_SET(t.root, t1.project)))) 

```
### 默认（全部数据）(VIEW)<div id="IbizproIndex_View"></div>
```sql
SELECT
t1.`ACL`,
t1.`ACLLIST`,
t1.`COLOR`,
t1.`DELETED`,
t1.`INDEXDESC`,
t1.`INDEXID`,
t1.`INDEXNAME`,
t1.`INDEX_TYPE`,
t1.`MDEPTID`,
t1.`ORGID`,
t1.`PROJECT`
FROM (SELECT
'bug' AS `INDEX_TYPE`,v1.`ID` AS `INDEXID`
,NULL AS `ACL`
,v1.`TITLE` AS `INDEXNAME`
,NULL AS `COLOR`
,v1.`DELETED` AS `DELETED`
,NULL AS `ACLLIST`
,v1.`STEPS` AS `INDEXDESC`
,NULL AS `MDEPTID`
,v1.`PROJECT` AS `PROJECT`
,NULL AS `ORGID`
FROM
(SELECT
t1.`DELETED`,
t1.`ID`,
t1.`PRODUCT`,
t1.`PROJECT`,
t1.`TITLE`
FROM `zt_bug` t1 
) v1
UNION ALL
SELECT
'case' AS `INDEX_TYPE`,v2.`ID` AS `INDEXID`
,NULL AS `ACL`
,v2.`TITLE` AS `INDEXNAME`
,NULL AS `COLOR`
,v2.`DELETED` AS `DELETED`
,NULL AS `ACLLIST`
,v2.`PRECONDITION` AS `INDEXDESC`
,NULL AS `MDEPTID`
,NULL AS `PROJECT`
,NULL AS `ORGID`
FROM
(SELECT
t1.`DELETED`,
t1.`ID`,
t1.`PRODUCT`,
t1.`TITLE`
FROM `zt_case` t1 
) v2
UNION ALL
SELECT
'product' AS `INDEX_TYPE`,v3.`ID` AS `INDEXID`
,NULL AS `ACL`
,v3.`NAME` AS `INDEXNAME`
,NULL AS `COLOR`
,v3.`DELETED` AS `DELETED`
,NULL AS `ACLLIST`
,v3.`DESC` AS `INDEXDESC`
,NULL AS `MDEPTID`
,NULL AS `PROJECT`
,NULL AS `ORGID`
FROM
(SELECT
t1.`DELETED`,
t1.`ID`,
t1.`NAME`
FROM `zt_product` t1 
) v3
UNION ALL
SELECT
'project' AS `INDEX_TYPE`,v4.`ID` AS `INDEXID`
,NULL AS `ACL`
,v4.`NAME` AS `INDEXNAME`
,NULL AS `COLOR`
,v4.`DELETED` AS `DELETED`
,NULL AS `ACLLIST`
,v4.`DESC` AS `INDEXDESC`
,NULL AS `MDEPTID`
,v4.`ID` AS `PROJECT`
,NULL AS `ORGID`
FROM
(SELECT
t1.`DELETED`,
t1.`ID`,
t1.`NAME`
FROM `zt_project` t1 
) v4
UNION ALL
SELECT
'story' AS `INDEX_TYPE`,v5.`ID` AS `INDEXID`
,NULL AS `ACL`
,v5.`TITLE` AS `INDEXNAME`
,v5.`COLOR` AS `COLOR`
,v5.`DELETED` AS `DELETED`
,NULL AS `ACLLIST`
,v5.`SPEC` AS `INDEXDESC`
,NULL AS `MDEPTID`
,NULL AS `PROJECT`
,NULL AS `ORGID`
FROM
(SELECT
t1.`COLOR`,
t1.`DELETED`,
t1.`ID`,
t1.`PRODUCT`,
t1.`TITLE`
FROM `zt_story` t1 
) v5
UNION ALL
SELECT
'task' AS `INDEX_TYPE`,v6.`ID` AS `INDEXID`
,NULL AS `ACL`
,v6.`NAME` AS `INDEXNAME`
,NULL AS `COLOR`
,v6.`DELETED` AS `DELETED`
,NULL AS `ACLLIST`
,v6.`DESC` AS `INDEXDESC`
,NULL AS `MDEPTID`
,v6.`PROJECT` AS `PROJECT`
,NULL AS `ORGID`
FROM
(SELECT
t1.`DELETED`,
t1.`ID`,
t1.`NAME`,
t1.`PROJECT`
FROM `zt_task` t1 
) v6
) t1 

```

# **产品日报**(IBIZPRO_PRODUCTDAILY)

### 数据查询(DEFAULT)<div id="IbizproProductDaily_Default"></div>
```sql
SELECT
t1.`BEGIN`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DATE`,
t1.`END`,
t1.`IBIZPRO_PRODUCTDAILYID`,
t1.`IBIZPRO_PRODUCTDAILYNAME`,
t1.`PO`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`TASKS`,
t1.`TOTALESTIMATES`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBIZPRO_PRODUCTDAILY` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 

```
### 产品日报(ProductDaily)<div id="IbizproProductDaily_ProductDaily"></div>
```sql
SELECT
t1.`BEGIN`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DATE`,
t1.`END`,
t1.`IBIZPRO_PRODUCTDAILYID`,
t1.`IBIZPRO_PRODUCTDAILYNAME`,
t1.`PO`,
t1.`PRODUCT`,
t2.`NAME` AS `PRODUCTNAME`,
t1.`TASKS`,
t1.`TOTALESTIMATES`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBIZPRO_PRODUCTDAILY` t1 
LEFT JOIN `ZT_PRODUCT` t2 ON t1.`PRODUCT` = t2.`ID`
```
### 默认（全部数据）(VIEW)<div id="IbizproProductDaily_View"></div>
```sql
SELECT
t1.`BEGIN`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DATE`,
t1.`END`,
t1.`IBIZPRO_PRODUCTDAILYID`,
t1.`IBIZPRO_PRODUCTDAILYNAME`,
t1.`PO`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`TASKS`,
t1.`TOTALESTIMATES`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBIZPRO_PRODUCTDAILY` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 

```

# **产品月报**(IBIZPRO_PRODUCTMONTHLY)

### 数据查询(DEFAULT)<div id="IbizproProductMonthly_Default"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DATE`,
t1.`IBIZPRO_PRODUCTMONTHLYID`,
t1.`IBIZPRO_PRODUCTMONTHLYNAME`,
t11.`PO`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`TASKS`,
t1.`TOTALESTIMATES`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`YEAR_MONTH`
FROM `T_IBIZPRO_PRODUCTMONTHLY` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 

```
### 默认（全部数据）(VIEW)<div id="IbizproProductMonthly_View"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DATE`,
t1.`IBIZPRO_PRODUCTMONTHLYID`,
t1.`IBIZPRO_PRODUCTMONTHLYNAME`,
t11.`PO`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`TASKS`,
t1.`TOTALESTIMATES`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`YEAR_MONTH`
FROM `T_IBIZPRO_PRODUCTMONTHLY` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 

```

# **产品周报**(IBIZPRO_PRODUCTWEEKLY)

### 数据查询(DEFAULT)<div id="IbizproProductWeekly_Default"></div>
```sql
SELECT
t1.`BEGINDATESTATS`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DATE`,
t1.`ENDDATESTATS`,
t1.`IBIZPRO_PRODUCTDAILYNAME`,
t1.`IBIZPRO_PRODUCTWEEKLYID`,
t1.`PO`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`TASKS`,
t1.`TOTALESTIMATES`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBIZPRO_PRODUCTWEEKLY` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 

```
### 默认（全部数据）(VIEW)<div id="IbizproProductWeekly_View"></div>
```sql
SELECT
t1.`BEGINDATESTATS`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DATE`,
t1.`ENDDATESTATS`,
t1.`IBIZPRO_PRODUCTDAILYNAME`,
t1.`IBIZPRO_PRODUCTWEEKLYID`,
t1.`PO`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`TASKS`,
t1.`TOTALESTIMATES`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBIZPRO_PRODUCTWEEKLY` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 

```

# **项目日报**(IBIZPRO_PROJECTDAILY)

### 数据查询(DEFAULT)<div id="IbizproProjectDaily_Default"></div>
```sql
SELECT
t1.`BEGIN`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DATE`,
t1.`END`,
t1.`IBIZPRO_PROJECTDAILYID`,
t1.`IBIZPRO_PROJECTDAILYNAME`,
t1.`PM`,
t1.`PROJECT`,
t11.`NAME` AS `PROJECTNAME`,
t1.`TASKS`,
t1.`TOTALESTIMATES`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBIZPRO_PROJECTDAILY` t1 
LEFT JOIN `zt_project` t11 ON t1.`PROJECT` = t11.`ID` 

```
### 默认（全部数据）(VIEW)<div id="IbizproProjectDaily_View"></div>
```sql
SELECT
t1.`BEGIN`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DATE`,
t1.`END`,
t1.`IBIZPRO_PROJECTDAILYID`,
t1.`IBIZPRO_PROJECTDAILYNAME`,
t1.`PM`,
t1.`PROJECT`,
t11.`NAME` AS `PROJECTNAME`,
t1.`TASKS`,
t1.`TOTALESTIMATES`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBIZPRO_PROJECTDAILY` t1 
LEFT JOIN `zt_project` t11 ON t1.`PROJECT` = t11.`ID` 

```

# **项目月报**(IBIZPRO_PROJECTMONTHLY)

### 数据查询(DEFAULT)<div id="IbizproProjectMonthly_Default"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DATE`,
t1.`IBIZPRO_PROJECTMONTHLYID`,
t1.`IBIZPRO_PROJECTMONTHLYNAME`,
t11.`PM`,
t1.`PROJECT`,
t11.`NAME` AS `PROJECTNAME`,
t1.`TASKS`,
t1.`TOTALESTIMATES`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`YEAR_MONTH`
FROM `T_IBIZPRO_PROJECTMONTHLY` t1 
LEFT JOIN `zt_project` t11 ON t1.`PROJECT` = t11.`ID` 

```
### 默认（全部数据）(VIEW)<div id="IbizproProjectMonthly_View"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DATE`,
t1.`IBIZPRO_PROJECTMONTHLYID`,
t1.`IBIZPRO_PROJECTMONTHLYNAME`,
t11.`PM`,
t1.`PROJECT`,
t11.`NAME` AS `PROJECTNAME`,
t1.`TASKS`,
t1.`TOTALESTIMATES`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`YEAR_MONTH`
FROM `T_IBIZPRO_PROJECTMONTHLY` t1 
LEFT JOIN `zt_project` t11 ON t1.`PROJECT` = t11.`ID` 

```

# **项目周报**(IBZPRO_PROJECTWEEKLY)

### 数据查询(DEFAULT)<div id="IbizproProjectWeekly_Default"></div>
```sql
SELECT
t1.`BEGINDATESTATS`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DATE`,
t1.`ENDDATESTATS`,
t1.`IBZPRO_PROJECTWEEKLYID`,
t1.`IBZPRO_PROJECTWEEKLYNAME`,
t1.`MONTH`,
t1.`PM`,
t1.`PROJECT`,
t11.`NAME` AS `PROJECTNAME`,
t1.`TASKS`,
t1.`TOTALESTIMATES`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`WEEK`,
t1.`YEAR`
FROM `T_IBZPRO_PROJECTWEEKLY` t1 
LEFT JOIN `zt_project` t11 ON t1.`PROJECT` = t11.`ID` 

```
### 默认（全部数据）(VIEW)<div id="IbizproProjectWeekly_View"></div>
```sql
SELECT
t1.`BEGINDATESTATS`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DATE`,
t1.`ENDDATESTATS`,
t1.`IBZPRO_PROJECTWEEKLYID`,
t1.`IBZPRO_PROJECTWEEKLYNAME`,
t1.`MONTH`,
t1.`PM`,
t1.`PROJECT`,
t11.`NAME` AS `PROJECTNAME`,
t1.`TASKS`,
t1.`TOTALESTIMATES`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`WEEK`,
t1.`YEAR`
FROM `T_IBZPRO_PROJECTWEEKLY` t1 
LEFT JOIN `zt_project` t11 ON t1.`PROJECT` = t11.`ID` 

```

# **代理**(IBZ_AGENT)

### 数据查询(DEFAULT)<div id="IbzAgent_Default"></div>
```sql
SELECT
t1.`AGENTBEGIN`,
t1.`AGENTEND`,
t1.`AGENTUSER`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`IBZ_AGENTID`,
t1.`IBZ_AGENTNAME`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZ_AGENT` t1 

```
### 默认（全部数据）(VIEW)<div id="IbzAgent_View"></div>
```sql
SELECT
t1.`AGENTBEGIN`,
t1.`AGENTEND`,
t1.`AGENTUSER`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`IBZ_AGENTID`,
t1.`IBZ_AGENTNAME`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZ_AGENT` t1 

```

# **测试用例**(IBZ_CASE)

### DEFAULT(DEFAULT)<div id="IbzCase_Default"></div>
```sql
SELECT
t1.`AUTO`,
t1.`DELETED`,
t1.`HOWRUN`,
t1.`ID`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LIB`,
t11.`NAME` AS `LIBNAME`,
t1.`MODULE`,
t21.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`ORDER`,
t1.`PATH`,
t1.`PRI`,
t1.`SCRIPTEDBY`,
t1.`SCRIPTEDDATE`,
t1.`SCRIPTLOCATION`,
t1.`SCRIPTSTATUS`,
t1.`STAGE`,
t1.`STATUS`,
t1.`TITLE`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_case` t1 
LEFT JOIN `zt_testsuite` t11 ON t1.`LIB` = t11.`ID` 
LEFT JOIN `zt_module` t21 ON t1.`MODULE` = t21.`ID` 

```
### 默认（全部数据）(VIEW)<div id="IbzCase_View"></div>
```sql
SELECT
t1.`AUTO`,
t1.`DELETED`,
t1.`HOWRUN`,
t1.`ID`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LIB`,
t11.`NAME` AS `LIBNAME`,
t1.`MODULE`,
t21.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`ORDER`,
t1.`PATH`,
t1.`PRECONDITION`,
t1.`PRI`,
t1.`SCRIPTEDBY`,
t1.`SCRIPTEDDATE`,
t1.`SCRIPTLOCATION`,
t1.`SCRIPTSTATUS`,
t1.`STAGE`,
t1.`STATUS`,
t1.`TITLE`,
t1.`TYPE`,
t1.`VERSION`
FROM `zt_case` t1 
LEFT JOIN `zt_testsuite` t11 ON t1.`LIB` = t11.`ID` 
LEFT JOIN `zt_module` t21 ON t1.`MODULE` = t21.`ID` 

```

# **日报**(IBZ_DAILY)

### 数据查询(DEFAULT)<div id="IbzDaily_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`DATE`,
t1.`IBZ_DAILYID`,
t1.`IBZ_DAILYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
t1.MAILTO AS `MAILTOPK`,
t1.`REPORTSTATUS`,
t1.`REPORTTO`,
t1.REPORTTO AS `REPORTTOPK`,
t1.`SUBMITTIME`,
t1.`TODAYTASK`,
t1.`TOMORROWPLANSTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`
FROM `T_IBZ_DAILY` t1 

```
### 我的日报（已提交和未提交）(MyAllDaily)<div id="IbzDaily_MyAllDaily"></div>
```sql
SELECT
		t1.`ACCOUNT`,
		t1.`CREATEDATE`,
		t1.`CREATEMAN`,
		t1.`CREATEMANNAME`,
		t1.`DATE`,
		t1.`IBZ_DAILYID`,
		t1.`IBZ_DAILYNAME`,
		t1.`ISSUBMIT`,
		t1.`MAILTO`,
		t1.MAILTO AS `MAILTOPK`,
		( CASE WHEN t11.id IS NOT NULL THEN '1' ELSE '0' END ) AS `REPORTSTATUS`,
		t1.`REPORTTO`,
		t1.REPORTTO AS `REPORTTOPK`,
		t1.`SUBMITTIME`,
		t1.`TODAYTASK`,
		t1.`TOMORROWPLANSTASK`,
		t1.`UPDATEDATE`,
		t1.`UPDATEMAN`,
		t1.`UPDATEMANNAME` 
	FROM
		`T_IBZ_DAILY` t1
		LEFT JOIN zt_action t11 ON t11.objectID = t1.IBZ_DAILYID 
		AND t11.objectType = 'daily' 
		AND t11.action = 'read' 
		AND t11.actor = #{srf.sessioncontext.srfloginname}
WHERE t1.account =  #{srf.sessioncontext.srfloginname} 

```
### 我收到的日报(MyDaily)<div id="IbzDaily_MyDaily"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`DATE`,
t1.`IBZ_DAILYID`,
t1.`IBZ_DAILYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
(case when t11.id is not null then '1' else '0' end ) as `REPORTSTATUS`,
t1.`REPORTTO`,
t1.`TODAYTASK`,
t1.`TOMORROWPLANSTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`
FROM `T_IBZ_DAILY` t1 left join zt_action t11 on t11.objectID = t1.IBZ_DAILYID and t11.objectType = 'daily' and t11.action = 'read' and t11.actor = #{srf.sessioncontext.srfloginname}
WHERE t1.issubmit = '1' 
(t1.REPORTTO = #{srf.sessioncontext.srfloginname} or FIND_IN_SET(#{srf.sessioncontext.srfloginname},t1.MAILTO)) 

```
### 我的日报(MyNotSubmit)<div id="IbzDaily_MyNotSubmit"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
DATE_FORMAT(t1.DATE,'%Y-%m-%d') as `DATE`,
t1.`IBZ_DAILYID`,
t1.`IBZ_DAILYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
'1' as `REPORTSTATUS`,
t1.`REPORTTO`,
t1.SUBMITTIME as `SUBMITTIME`,
t1.`TODAYTASK`,
t1.`TOMORROWPLANSTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`,
CONCAT_WS('','今日工作：',case when t1.WORKTODAY is null then '无' else t1.WORKTODAY end) as WORKTODAY,
t1.`COMMENT`,
CONCAT_WS('','明日计划：',case when t1.PLANSTOMORROW is null then '无' else t1.PLANSTOMORROW end) AS PLANSTOMORROW,
'daily' as type 
FROM `T_IBZ_DAILY` t1
```
### 我提交的日报(MySubmitDaily)<div id="IbzDaily_MySubmitDaily"></div>
```sql
SELECT
	t1. IBZ_DAILYID,
	t1.IBZ_DAILYNAME,
	t1.ACCOUNT,
	t1.DATE,
	t1.ISSUBMIT,
	t1.REPORTTO,
	t1.REPORTSTATUS
FROM
	t_ibz_daily t1 


WHERE t1.account=#{srf.sessioncontext.srfloginname} 

```
### 产品日报(ProductDaily)<div id="IbzDaily_ProductDaily"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`DATE`,
t1.`IBZ_DAILYID`,
t1.`IBZ_DAILYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
t1.MAILTO AS `MAILTOPK`,
t1.`REPORTSTATUS`,
t1.`REPORTTO`,
t1.REPORTTO AS `REPORTTOPK`,
t1.`SUBMITTIME`,
t1.`TODAYTASK`,
t1.`TOMORROWPLANSTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`
FROM `T_IBZ_DAILY` t1 
where
t1.`ISSUBMIT` = '1' 
AND exists(select 1 from zt_team t2 where t2.account = t1.`ACCOUNT` and t2.type = 'product' and t2.root = ${srfdatacontext('curproduct')}) 
AND DATE_FORMAT(t1.date,'%Y-%m-%d') = DATE_FORMAT(${srfdatacontext('date')},'%Y-%m-%d')
```
### 项目日报(ProjectDaily)<div id="IbzDaily_ProjectDaily"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`DATE`,
t1.`IBZ_DAILYID`,
t1.`IBZ_DAILYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
t1.MAILTO AS `MAILTOPK`,
t1.`REPORTSTATUS`,
t1.`REPORTTO`,
t1.REPORTTO AS `REPORTTOPK`,
t1.`SUBMITTIME`,
t1.`TODAYTASK`,
t1.`TOMORROWPLANSTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`
FROM `T_IBZ_DAILY` t1 

WHERE ( t1.`ISSUBMIT` = '1'  AND  t1.ACCOUNT in (select t.ACCOUNT from zt_team t where t.type = 'project' and t.root =${srfdatacontext('curproject')})  AND  DATE_FORMAT(t1.date,'%Y-%m-%d') = DATE_FORMAT(${srfdatacontext('date')},'%Y-%m-%d') ) 

```
### 默认（全部数据）(VIEW)<div id="IbzDaily_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`COMMENT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`DATE`,
t1.`IBZ_DAILYID`,
t1.`IBZ_DAILYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
t1.MAILTO AS `MAILTOPK`,
t1.`PLANSTOMORROW`,
t1.`REPORTSTATUS`,
t1.`REPORTTO`,
t1.REPORTTO AS `REPORTTOPK`,
t1.`SUBMITTIME`,
t1.`TODAYTASK`,
t1.`TOMORROWPLANSTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`,
t1.`WORKTODAY`
FROM `T_IBZ_DAILY` t1 

```

# **收藏**(IBZ_FAVORITES)

### 数据查询(DEFAULT)<div id="IbzFavorites_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`IBZ_FAVORITESID`,
t1.`IBZ_FAVORITESNAME`,
t1.`OBJECTID`,
t1.`TYPE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZ_FAVORITES` t1 

```
### 默认（全部数据）(VIEW)<div id="IbzFavorites_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`IBZ_FAVORITESID`,
t1.`IBZ_FAVORITESNAME`,
t1.`OBJECTID`,
t1.`TYPE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZ_FAVORITES` t1 

```

# **用例库**(IBZ_LIB)

### DEFAULT(DEFAULT)<div id="IbzLib_Default"></div>
```sql
SELECT
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
t1.`DELETED`,
t1.`ID`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`NAME`,
t1.`PRODUCT`,
t1.`TYPE`
FROM `zt_testsuite` t1 

WHERE ( t1.`TYPE` = 'library' ) 

```
### 默认（全部数据）(VIEW)<div id="IbzLib_View"></div>
```sql
SELECT
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
t1.`DELETED`,
t1.`DESC`,
t1.`ID`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`NAME`,
t1.`PRODUCT`,
t1.`TYPE`
FROM `zt_testsuite` t1 

```

# **用例库用例步骤**(IBZ_CASESTEP)

### DEFAULT(DEFAULT)<div id="IbzLibCaseStep_Default"></div>
```sql
SELECT
t1.`DESC`,
t1.`EXPECT`,
t1.`ID`,
t1.`TYPE`
FROM `ZT_CASESTEP` t1 

```
### 默认（全部数据）(VIEW)<div id="IbzLibCaseStep_View"></div>
```sql
SELECT
t1.`DESC`,
t1.`EXPECT`,
t1.`ID`,
t1.`TYPE`
FROM `ZT_CASESTEP` t1 

```

# **用例库用例步骤**(IBZ_LIBCASESTEPS)

### DEFAULT(DEFAULT)<div id="IbzLibCaseSteps_Default"></div>
```sql
SELECT
t1.`CASE`,
t1.`DESC`,
t1.`EXPECT`,
t1.`ID`,
t1.`PARENT`,
t1.`TYPE`,
t11.`VERSION`
FROM `zt_casestep` t1 
LEFT JOIN `zt_case` t11 ON t1.`CASE` = t11.`ID` 

```
### 默认（全部数据）(VIEW)<div id="IbzLibCaseSteps_View"></div>
```sql
SELECT
t1.`CASE`,
t1.`DESC`,
t1.`EXPECT`,
t1.`ID`,
t1.`PARENT`,
t1.`TYPE`,
t11.`VERSION`
FROM `zt_casestep` t1 
LEFT JOIN `zt_case` t11 ON t1.`CASE` = t11.`ID` 

```

# **用例库模块**(IBZ_LIBMODULE)

### DEFAULT(DEFAULT)<div id="IbzLibModule_Default"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_module` t11 ON t1.`PARENT` = t11.`ID` 

WHERE ( t1.`TYPE` = 'caselib' ) 

```
### 无枝叶(Root_NoBranch)<div id="IbzLibModule_Root_NoBranch"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_module` t11 ON t1.`PARENT` = t11.`ID` 

WHERE ( t1.`TYPE` = 'caselib'  AND  ( t1.`PARENT` IS NULL  OR  t1.`PARENT` = 0 ) ) 

```
### 默认（全部数据）(VIEW)<div id="IbzLibModule_View"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`COLLECTOR`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_module` t11 ON t1.`PARENT` = t11.`ID` 

```

# **月报**(IBZ_MONTHLY)

### 数据查询(DEFAULT)<div id="IbzMonthly_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`DATE`,
t1.`IBZ_MONTHLYID`,
t1.`IBZ_MONTHLYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
t1.MAILTO AS `MAILTOPK`,
t1.`NEXTMONTHPLANSTASK`,
t1.`REPORTSTATUS`,
t1.`REPORTTO`,
t1.REPORTTO AS `REPORTTOPK`,
t1.`SUBMITTIME`,
t1.`THISMONTHTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`
FROM `T_IBZ_MONTHLY` t1 

```
### 我的月报(MyMonthly)<div id="IbzMonthly_MyMonthly"></div>
```sql
SELECT
	t1.`ACCOUNT`,
	t1.`CREATEDATE`,
	t1.`CREATEMAN`,
	t1.`CREATEMANNAME`,
	t1.`DATE`,
	t1.`IBZ_MONTHLYID`,
	t1.`IBZ_MONTHLYNAME`,
	t1.`ISSUBMIT`,
	t1.`MAILTO`,
	( CASE WHEN t11.id IS NOT NULL THEN '1' ELSE '0' END ) AS `REPORTSTATUS`,
	t1.`REPORTTO`,
	t1.`THISMONTHTASK`,
	t1.`NEXTMONTHPLANSTASK`,
	t1.`UPDATEDATE`,
	t1.`UPDATEMAN`,
	t1.`UPDATEMANNAME` 
FROM
	`t_ibz_monthly` t1
	LEFT JOIN zt_action t11 ON t11.objectID = t1.IBZ_MONTHLYID 
	AND t11.objectType = 'monthly' 
	AND t11.action = 'read' 
	AND t11.actor = #{srf.sessioncontext.srfloginname}
WHERE t1.account = #{srf.sessioncontext.srfloginname} 

```
### 我的月报（移动端）(MyMonthlyMob)<div id="IbzMonthly_MyMonthlyMob"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
DATE_FORMAT(t1.DATE,'%Y-%m-%d') as `DATE`,
t1.`IBZ_MONTHLYID` AS IBZ_MONTHLYID,
t1.`IBZ_MONTHLYNAME` AS IBZ_MONTHLYNAME,
t1.`ISSUBMIT`,
t1.`MAILTO`,
'1' as `REPORTSTATUS`,
t1.`REPORTTO`,
t1.SUBMITTIME as `SUBMITTIME`,
t1.`THISMONTHTASK` AS THISMONTHTASK,
t1.`NEXTMONTHPLANSTASK` AS NEXTMONTHPLANSTASK,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`,
CONCAT_WS('','本月工作：',case when t1.WORKTHISMONTH is null then '无' else t1.WORKTHISMONTH end)  as WORKTHISMONTH,
t1.`COMMENT`,
CONCAT_WS('','下月计划：',case when t1.PLANSNEXTMONTH is null then '无' else t1.PLANSNEXTMONTH end) as PLANSNEXTMONTH,
'monthly' as type 
FROM `T_IBZ_MONTHLY` t1
WHERE t1.account = #{srf.sessioncontext.srfloginname} 

```
### 我收到的月报(MyReceivedMonthly)<div id="IbzMonthly_MyReceivedMonthly"></div>
```sql
SELECT
	t1.`ACCOUNT`,
	t1.`CREATEDATE`,
	t1.`CREATEMAN`,
	t1.`CREATEMANNAME`,
	t1.`DATE`,
	t1.`IBZ_MONTHLYID`,
	t1.`IBZ_MONTHLYNAME`,
	t1.`ISSUBMIT`,
	t1.`MAILTO`,
	( CASE WHEN t11.id IS NOT NULL THEN '1' ELSE '0' END ) AS `REPORTSTATUS`,
	t1.`REPORTTO`,
	t1.`THISMONTHTASK`,
	t1.`NEXTMONTHPLANSTASK`,
	t1.`UPDATEDATE`,
	t1.`UPDATEMAN`,
	t1.`UPDATEMANNAME` 
FROM
	`t_ibz_monthly` t1
	LEFT JOIN zt_action t11 ON t11.objectID = t1.IBZ_MONTHLYID 
	AND t11.objectType = 'monthly' 
	AND t11.action = 'read' 
	AND t11.actor = #{srf.sessioncontext.srfloginname}
WHERE t1.issubmit = '1' 
(t1.REPORTTO = #{srf.sessioncontext.srfloginname} OR FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.MAILTO )) 

```
### 我提交的月报(MySubmitMonthly)<div id="IbzMonthly_MySubmitMonthly"></div>
```sql
SELECT
	t1.`ACCOUNT`,
	t1.`CREATEDATE`,
	t1.`CREATEMAN`,
	t1.`CREATEMANNAME`,
	t1.`DATE`,
	t1.`IBZ_MONTHLYID`,
	t1.`IBZ_MONTHLYNAME`,
	t1.`ISSUBMIT`,
	t1.`MAILTO`,
	( CASE WHEN t11.id IS NOT NULL THEN '1' ELSE '0' END ) AS `REPORTSTATUS`,
	t1.`REPORTTO`,
	t1.`THISMONTHTASK`,
	t1.`NEXTMONTHPLANSTASK`,
	t1.`UPDATEDATE`,
	t1.`UPDATEMAN`,
	t1.`UPDATEMANNAME` 
FROM
	`t_ibz_monthly` t1
	LEFT JOIN zt_action t11 ON t11.objectID = t1.IBZ_MONTHLYID 
	AND t11.objectType = 'monthly' 
	AND t11.action = 'read' 
	AND t11.actor = #{srf.sessioncontext.srfloginname}
WHERE t1.issubmit = '1' 
t1.account = #{srf.sessioncontext.srfloginname} 

```
### 产品月报(ProductMonthly)<div id="IbzMonthly_ProductMonthly"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`DATE`,
t1.`IBZ_MONTHLYID`,
t1.`IBZ_MONTHLYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
t1.MAILTO AS `MAILTOPK`,
t1.`NEXTMONTHPLANSTASK`,
t1.`REPORTSTATUS`,
t1.`REPORTTO`,
t1.REPORTTO AS `REPORTTOPK`,
t1.`SUBMITTIME`,
t1.`THISMONTHTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`
FROM `T_IBZ_MONTHLY` t1 
where 
t1.`ISSUBMIT` = '1' 
AND DATE_FORMAT(t1.date,'%Y-%m') = ${srfdatacontext('yearmonth')} 
AND exists(select 1 from zt_team t2 where t2.account = t1.`ACCOUNT` and t2.type = 'product' and t2.root = ${srfdatacontext('curproduct')})
```
### 项目月报(ProjectMonthly)<div id="IbzMonthly_ProjectMonthly"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`DATE`,
t1.`IBZ_MONTHLYID`,
t1.`IBZ_MONTHLYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
t1.MAILTO AS `MAILTOPK`,
t1.`NEXTMONTHPLANSTASK`,
t1.`REPORTSTATUS`,
t1.`REPORTTO`,
t1.REPORTTO AS `REPORTTOPK`,
t1.`SUBMITTIME`,
t1.`THISMONTHTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`
FROM `T_IBZ_MONTHLY` t1 

WHERE ( t1.`ISSUBMIT` = '1'  AND  DATE_FORMAT(t1.date,'%Y-%m') = ${srfdatacontext('yearmonth')}  AND  t1.ACCOUNT in (select t.ACCOUNT from zt_team t where t.type = 'project' and t.root =${srfdatacontext('curproject')}) ) 

```
### 默认（全部数据）(VIEW)<div id="IbzMonthly_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`COMMENT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`DATE`,
t1.`IBZ_MONTHLYID`,
t1.`IBZ_MONTHLYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
t1.MAILTO AS `MAILTOPK`,
t1.`NEXTMONTHPLANSTASK`,
t1.`PLANSNEXTMONTH`,
t1.`REPORTSTATUS`,
t1.`REPORTTO`,
t1.REPORTTO AS `REPORTTOPK`,
t1.`SUBMITTIME`,
t1.`THISMONTHTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`,
t1.`WORKTHISMONTH`
FROM `T_IBZ_MONTHLY` t1 

```

# **我的地盘**(IBZ_MYTERRITORY)

### DEFAULT(DEFAULT)<div id="IbzMyTerritory_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ADDRESS`,
t1.`AVATAR`,
t1.`BIRTHDAY`,
t1.`CLIENTLANG`,
t1.`CLIENTSTATUS`,
t1.`COMMITER`,
t1.`DELETED`,
t1.`DEPT`,
t1.`DINGDING`,
t1.`EMAIL`,
t1.`FAILS`,
t1.`GENDER`,
t1.`ID`,
t1.`IP`,
t1.`JOIN`,
t1.`LAST`,
t1.`LOCKED`,
t1.`MOBILE`,
t1.`NICKNAME`,
t1.`PASSWORD`,
t1.`PHONE`,
t1.`QQ`,
t1.`RANZHI`,
t1.`REALNAME`,
t1.`ROLE`,
t1.`SCORE`,
t1.`SCORELEVEL`,
t1.`SKYPE`,
t1.`SLACK`,
t1.`VISITS`,
t1.`WEIXIN`,
t1.`WHATSAPP`,
t1.`ZIPCODE`
FROM `zt_user` t1 

```
### 我的工作(MyWork)<div id="IbzMyTerritory_MyWork"></div>
```sql
SELECT
	t1.`ACCOUNT`,
	t11.mytasks,
	CONCAT('已延期 ',t51.MYETASKS) as MYETASKS,
	t21.mybugs,
	CONCAT('已延期 ', t31.myEbugs) as myEbugs,
	t41.mystorys ,
	(select count(1) as projects from zt_project tt where tt.deleted = '0' and (tt.`status` <> 'closed' )) as  projects,
	concat('已延期 ', (select count(1) as eprojects from zt_project tt where tt.deleted = '0' and (tt.`status` <> 'closed' ) and tt.`end` < DATE_FORMAT(now(),'%Y-%m-%d'))) as  eprojects,
	(select count(1) as products from zt_product tt where tt.`status` = 'normal' and tt.deleted = '0' and tt.acl = 'open') as  products
FROM
	(select DISTINCT t1.actor as account from zt_action t1 where t1.actor <> '' and t1.actor is not null) t1
	LEFT JOIN ( SELECT t.assignedTo AS account, COUNT( 1 ) AS mytasks FROM zt_task t GROUP BY t.assignedTo ) t11 ON t1.account = t11.account
	LEFT JOIN ( SELECT t.assignedTo AS account, COUNT( 1 ) AS mybugs FROM zt_bug t GROUP BY t.assignedTo ) t21 ON t1.account = t21.account
	LEFT JOIN (
SELECT
	t.assignedTo AS account,
	COUNT( 1 ) AS myEbugs 
FROM
	zt_bug t 
WHERE
	t.DEADLINE < DATE_FORMAT( now( ), '%Y-%m-%d' ) 
	AND t.`status` = 'active' 
GROUP BY
	t.assignedTo 
	) t31 ON t1.account = t31.account
	LEFT JOIN ( SELECT t.assignedTo AS account, COUNT( 1 ) AS mystorys FROM zt_story t GROUP BY t.assignedTo ) t41 ON t1.account = t41.account
	LEFT JOIN ( SELECT t.assignedTo AS account, COUNT( 1 ) AS MYETASKS FROM zt_task t where (t.`status` = 'wait' or t.`status` = 'doing') and (t.DEADLINE < DATE_FORMAT(now(),'%Y-%m-%d') and t.deadline <> '0000-00-00') GROUP BY t.assignedTo ) t51 ON t1.account = t51.account
```
### 我的工作(MyWorkMob)<div id="IbzMyTerritory_MyWorkMob"></div>
```sql
SELECT
	t1.`ACCOUNT`,
	t11.mytasks,
	CONCAT(t51.MYETASKS) as MYETASKS,
	t21.mybugs,
	CONCAT( t31.myEbugs) as myEbugs,
	t41.mystorys ,
	(select count(1) as projects from zt_project tt where tt.deleted = '0' and (tt.`status` <> 'closed' )) as  projects,
	concat((select count(1) as eprojects from zt_project tt where tt.deleted = '0' and (tt.`status` <> 'closed' ) and tt.`end` < DATE_FORMAT(now(),'%Y-%m-%d'))) as  eprojects,
	(select count(1) as products from zt_product tt where tt.`status` = 'normal' and tt.deleted = '0' and tt.acl = 'open') as  products
FROM
	(select DISTINCT t1.actor as account from zt_action t1 where t1.actor <> '' and t1.actor is not null) t1
	LEFT JOIN ( SELECT t.assignedTo AS account, COUNT( 1 ) AS mytasks FROM zt_task t GROUP BY t.assignedTo ) t11 ON t1.account = t11.account
	LEFT JOIN ( SELECT t.assignedTo AS account, COUNT( 1 ) AS mybugs FROM zt_bug t GROUP BY t.assignedTo ) t21 ON t1.account = t21.account
	LEFT JOIN (
SELECT
	t.assignedTo AS account,
	COUNT( 1 ) AS myEbugs 
FROM
	zt_bug t 
WHERE
	t.DEADLINE < DATE_FORMAT( now( ), '%Y-%m-%d' ) 
	AND t.`status` = 'active' 
GROUP BY
	t.assignedTo 
	) t31 ON t1.account = t31.account
	LEFT JOIN ( SELECT t.assignedTo AS account, COUNT( 1 ) AS mystorys FROM zt_story t GROUP BY t.assignedTo ) t41 ON t1.account = t41.account
	LEFT JOIN ( SELECT t.assignedTo AS account, COUNT( 1 ) AS MYETASKS FROM zt_task t where (t.`status` = 'wait' or t.`status` = 'doing') and (t.DEADLINE < DATE_FORMAT(now(),'%Y-%m-%d') and t.deadline <> '0000-00-00') GROUP BY t.assignedTo ) t51 ON t1.account = t51.account
```
### 我的工作（项目经理）(MyWorkPm)<div id="IbzMyTerritory_MyWorkPm"></div>
```sql
SELECT
	t1.`ACCOUNT`,
	(select count(1) as projects from zt_project tt where tt.deleted = '0' and (tt.`status` <> 'closed' ) and tt.pm = t1.account) as  projects,
	concat('已延期 ', (select count(1) as eprojects from zt_project tt where tt.deleted = '0' and (tt.`status` <> 'closed' ) and tt.`end` < DATE_FORMAT(now(),'%Y-%m-%d') and tt.pm = t1.account) ) as  eprojects,
	(select count(1) as LEFTLCBCNT from T_PMSEE_MILESTONE tt left JOIN t_pmsee_project t2 on t2.PMSEE_PROJECTID = tt.projectid and tt.version = t2.MILESTONEVERSION where t2.deleted = '0' and tt.`status` = '0' and t2.PMO = t1.account) as LEFTLCBCNT,
	concat('今日里程碑 ',(select count(1) as LEFTLCBJZCNT from T_PMSEE_MILESTONE tt left JOIN t_pmsee_project t2 on t2.PMSEE_PROJECTID = tt.projectid and tt.version = t2.MILESTONEVERSION where t2.deleted = '0' and DATE_FORMAT(now(),'%Y-%m-%d') = DATE_FORMAT(tt.`end`,'%Y-%m-%d') and t2.PMO = t1.account)) as LEFTLCBJZCNT,
	(select count(1) as PROJECTTEAMCNT from (select t2.pm,tt.account from zt_team tt left join zt_project t2 on tt.root = t2.id where tt.type = 'project' and t2.PM is not null and t2.pm <> '' GROUP BY t2.PM, tt.account) tt where tt.pm = t1.account) as PROJECTTEAMCNT,
	concat('今日离场 ',(select count(1) as PROJECTTEAMJZCNT  from (select t2.pm,tt.account from zt_team tt left join zt_project t2 on tt.root = t2.id where DATE_FORMAT(now(),'%Y-%m-%d') = DATE_FORMAT(DATE_ADD(tt.`join`, INTERVAL tt.days day),'%Y-%m-%d')  and tt.type = 'project' and t2.PM is not null and t2.pm <> '' GROUP BY t2.PM, tt.account) tt where tt.pm = t1.account)) as PROJECTTEAMJZCNT,
	(select count(1) as MYTODOCNT from zt_todo t where t.`status` in( 'doing', 'wait') and t.type = 'custom' and t.cycle = '0' and t.account = t1.account) as MYTODOCNT,
	concat('今日截止 ',(select count(1) as MYTODOCNTJZ from zt_todo t where t.`status` in( 'doing', 'wait') and t.type = 'custom' and t.cycle = '0' and DATE_FORMAT(now(),'%Y-%m-%d') >= t.date and t.account = t1.account)) as MYTODOCNTJZ
FROM
	(select DISTINCT t1.actor as account from zt_action t1 where t1.actor <> '' and t1.actor is not null) t1
```
### 个人信息-个人贡献(PersonInfo)<div id="IbzMyTerritory_PersonInfo"></div>
```sql
SELECT #{srf.sessioncontext.srfloginname} as account, (SELECT count(1) from zt_todo where account = #{srf.sessioncontext.srfloginname} ) as mytodocnt,(SELECT count(1) from zt_story where deleted = '0' and openedBy = #{srf.sessioncontext.srfloginname}) as mystorys, (SELECT count(1) from zt_task where deleted = '0' and (`status` = 'done' or (`status` = 'closed' and closedReason = 'done') ) and parent >= 0 and ((finishedBy = #{srf.sessioncontext.srfloginname} and not EXISTS (SELECT 1 from zt_team t where t.root = id and t.type = 'task')) 
or FIND_IN_SET(#{srf.sessioncontext.srfloginname},finishedList)
)) as mytasks, (SELECT count(1) from zt_bug where `status` in ('closed','resolved') and resolution = 'fixed' and resolvedBy = #{srf.sessioncontext.srfloginname}) as mybugs,
(SELECT count(1) from zt_case where deleted = '0' and openedBy = 
#{srf.sessioncontext.srfloginname}) as MYFAVORITEBUGS
```
### 默认（全部数据）(VIEW)<div id="IbzMyTerritory_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ADDRESS`,
t1.`AVATAR`,
t1.`BIRTHDAY`,
t1.`CLIENTLANG`,
t1.`CLIENTSTATUS`,
t1.`COMMITER`,
t1.`DELETED`,
t1.`DEPT`,
t1.`DINGDING`,
t1.`EMAIL`,
t1.`FAILS`,
t1.`GENDER`,
t1.`ID`,
t1.`IP`,
t1.`JOIN`,
t1.`LAST`,
t1.`LOCKED`,
t1.`MOBILE`,
t1.`NICKNAME`,
t1.`PASSWORD`,
t1.`PHONE`,
t1.`QQ`,
t1.`RANZHI`,
t1.`REALNAME`,
t1.`ROLE`,
t1.`SCORE`,
t1.`SCORELEVEL`,
t1.`SKYPE`,
t1.`SLACK`,
t1.`VISITS`,
t1.`WEIXIN`,
t1.`WHATSAPP`,
t1.`ZIPCODE`
FROM `zt_user` t1 

```
### 欢迎(welcome)<div id="IbzMyTerritory_Welcome"></div>
```sql
select CONCAT(t1.REALNAME,'，',t1.NICKNAME,'!') as realname, t1.ACCOUNT from (SELECT
	t1.`ACCOUNT`,
	(case when DATE_FORMAT(now(),'%H') < 11 AND DATE_FORMAT(now(),'%H') >= 5   then '早上好' when DATE_FORMAT(now(),'%H') < 14 AND DATE_FORMAT(now(),'%H') >= 11 then '中午好' when DATE_FORMAT(now(),'%H') < 19 AND DATE_FORMAT(now(),'%H') >= 14 then '下午好' when (DATE_FORMAT(now(),'%H') >= 19 || DATE_FORMAT(now(),'%H') < 5) then '晚上好' end) AS `NICKNAME`,
	t1.`REALNAME`
FROM
	`zt_user` t1) t1
```

# **计划模板**(IBZ_PLANTEMPLET)

### CurUserTemplet(CurUserTemplet)<div id="IbzPlanTemplet_CurUserTemplet"></div>
```sql
SELECT
t1.`ACL`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`IBZ_PLANTEMPLETID`,
t1.`IBZ_PLANTEMPLETNAME`,
t1.`PLANS`,
t1.`PRODUCT`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZ_PLANTEMPLET` t1 

WHERE ( ( t1.`ACL` = 'open'  OR  t1.`CREATEMANNAME` =  ${srfsessioncontext('srfloginname','{"defname":"CREATEMANNAME","dename":"IBZ_PLANTEMPLET"}')} ) ) 

```
### 数据查询(DEFAULT)<div id="IbzPlanTemplet_Default"></div>
```sql
SELECT
t1.`ACL`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`IBZ_PLANTEMPLETID`,
t1.`IBZ_PLANTEMPLETNAME`,
t1.`PLANS`,
t1.`PRODUCT`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZ_PLANTEMPLET` t1 

```
### 默认（全部数据）(VIEW)<div id="IbzPlanTemplet_View"></div>
```sql
SELECT
t1.`ACL`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`IBZ_PLANTEMPLETID`,
t1.`IBZ_PLANTEMPLETNAME`,
t1.`PLANS`,
t1.`PRODUCT`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZ_PLANTEMPLET` t1 

```

# **计划模板详情**(IBZ_PLANTEMPLETDETAIL)

### 数据查询(DEFAULT)<div id="IbzPlanTempletDetail_Default"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`IBZ_PLANTEMPLETDETAILID`,
t1.`IBZ_PLANTEMPLETDETAILNAME`,
t1.`ORDER`,
t1.`PLANCODE`,
t1.`PLANTEMPLETID`,
t1.`TYPE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`DESC`,
t1.`EXPECT`
FROM `T_IBZ_PLANTEMPLETDETAIL` t1 

```
### 默认（全部数据）(VIEW)<div id="IbzPlanTempletDetail_View"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DESC`,
t1.`EXPECT`,
t1.`IBZ_PLANTEMPLETDETAILID`,
t1.`IBZ_PLANTEMPLETDETAILNAME`,
t1.`ORDER`,
t1.`PLANCODE`,
t1.`PLANTEMPLETID`,
t1.`TYPE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZ_PLANTEMPLETDETAIL` t1 

```

# **项目相关成员**(IBZ_PROJECTMEMBER)

### DEFAULT(DEFAULT)<div id="IbzProjectMember_Default"></div>
```sql
SELECT
(select t.account from zt_team t where t.type = 'project' and t.root = t1.id order by t.id asc LIMIT 4,1) AS `FIFTHMEMBER`,
(select t.account from zt_team t where t.type = 'project' and t.root = t1.id order by t.id asc LIMIT 3,1) AS `FOURTHMEMBER`,
(select t.account from zt_team t where t.type = 'project' and t.root = t1.id order by t.id asc LIMIT 0,1) AS `FRISTMEMBER`,
t1.`ID`,
t1.`NAME`,
t1.PM AS `PM`,
t1.PO AS `PO`,
t1.QD AS `QD`,
t1.RD AS `RD`,
(select t.account from zt_team t where t.type = 'project' and t.root = t1.id order by t.id asc LIMIT 1,1) AS `SECONDMEMBER`,
(select t.account from zt_team t where t.type = 'project' and t.root = t1.id order by t.id asc LIMIT 5,1) AS `SIXTHMEMBER`,
(select GROUP_CONCAT(t.account order by t.`order` asc) from zt_team t where t.type = 'project' and t.root = t1.id GROUP BY t.root) AS `TEAMEMBERS`,
(select t.account from zt_team t where t.type = 'project' and t.root = t1.id order by t.id asc LIMIT 2,1) AS `THIRDMEMBER`
FROM `zt_project` t1 

```
### 默认（全部数据）(VIEW)<div id="IbzProjectMember_View"></div>
```sql
SELECT
(select t.account from zt_team t where t.type = 'project' and t.root = t1.id order by t.id asc LIMIT 4,1) AS `FIFTHMEMBER`,
(select t.account from zt_team t where t.type = 'project' and t.root = t1.id order by t.id asc LIMIT 3,1) AS `FOURTHMEMBER`,
(select t.account from zt_team t where t.type = 'project' and t.root = t1.id order by t.id asc LIMIT 0,1) AS `FRISTMEMBER`,
t1.`ID`,
t1.`NAME`,
t1.PM AS `PM`,
t1.PO AS `PO`,
t1.QD AS `QD`,
t1.RD AS `RD`,
(select t.account from zt_team t where t.type = 'project' and t.root = t1.id order by t.id asc LIMIT 1,1) AS `SECONDMEMBER`,
(select t.account from zt_team t where t.type = 'project' and t.root = t1.id order by t.id asc LIMIT 5,1) AS `SIXTHMEMBER`,
(select GROUP_CONCAT(t.account order by t.`order` asc) from zt_team t where t.type = 'project' and t.root = t1.id GROUP BY t.root) AS `TEAMEMBERS`,
(select t.account from zt_team t where t.type = 'project' and t.root = t1.id order by t.id asc LIMIT 2,1) AS `THIRDMEMBER`
FROM `zt_project` t1 

```

# **汇报汇总**(IBZ_REPORT)

### 汇报汇总(AllReport)<div id="IbzReport_AllReport"></div>
```sql
select t1.* from (SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
DATE_FORMAT(t1.DATE,'%Y-%m-%d') as `DATE`,
t1.`IBZ_DAILYID`,
t1.`IBZ_DAILYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
'1' as `REPORTSTATUS`,
t1.`REPORTTO`,
DATE_FORMAT(t1.SUBMITTIME,'%H:%i') as `SUBMITTIME`,
t1.`TODAYTASK`,
t1.`TOMORROWPLANSTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`,
CONCAT_WS('','今日工作：',case when t1.WORKTODAY is null then '无' else t1.WORKTODAY end) as WORKTODAY,
t1.`COMMENT`,
CONCAT_WS('','明日计划：',case when t1.PLANSTOMORROW is null then '无' else t1.PLANSTOMORROW end) AS PLANSTOMORROW,
'daily' as type 
FROM `T_IBZ_DAILY` t1 
where t1.ISSUBMIT = '1'
UNION
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
DATE_FORMAT(t1.DATE,'%Y-%m-%d') as `DATE`,
t1.`IBZ_WEEKLYID` as IBZ_DAILYID,
t1.`IBZ_WEEKLYNAME` as IBZ_DAILYNAME,
t1.`ISSUBMIT`,
t1.`MAILTO`,
'1' as `REPORTSTATUS`,
t1.`REPORTTO`,
DATE_FORMAT(t1.SUBMITTIME,'%H:%i') as `SUBMITTIME`,
t1.`THISWEEKTASK` as TODAYTASK,
t1.`NEXTWEEKTASK` as TOMORROWPLANSTASK,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`,
CONCAT_WS('','本周工作：',case when t1.WORKTHISWEEK is null then '无' else t1.WORKTHISWEEK end)  as WORKTODAY,
t1.`COMMENT`,
CONCAT_WS('','下周计划：',case when t1.PLANNEXTWEEK is null then '无' else t1.PLANNEXTWEEK end) as PLANSTOMORROW,
'weekly' as type 
FROM `T_IBZ_WEEKLY` t1 
where t1.ISSUBMIT = '1'
UNION
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
DATE_FORMAT(t1.DATE,'%Y-%m-%d') as `DATE`,
t1.`IBZ_MONTHLYID` AS IBZ_DAILYID,
t1.`IBZ_MONTHLYNAME` AS IBZ_DAILYNAME,
t1.`ISSUBMIT`,
t1.`MAILTO`,
'1' as `REPORTSTATUS`,
t1.`REPORTTO`,
DATE_FORMAT(t1.SUBMITTIME,'%H:%i') as `SUBMITTIME`,
t1.`THISMONTHTASK` AS TODAYTASK,
t1.`NEXTMONTHPLANSTASK` AS TOMORROWPLANSTASK,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`,
CONCAT_WS('','本月工作：',case when t1.WORKTHISMONTH is null then '无' else t1.WORKTHISMONTH end)  as WORKTODAY,
t1.`COMMENT`,
CONCAT_WS('','下月计划：',case when t1.PLANSNEXTMONTH is null then '无' else t1.PLANSNEXTMONTH end) as PLANSTOMORROW,
'monthly' as type 
FROM `T_IBZ_MONTHLY` t1
where t1.ISSUBMIT = '1'
UNION
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
null as `CREATEMANNAME`,
DATE_FORMAT(t1.DATE,'%Y-%m-%d') as `DATE`,
t1.`IBZ_REPORTLYID` AS IBZ_DAILYID,
t1.`IBZ_REPORTLYNAME` AS IBZ_DAILYNAME,
t1.`ISSUBMIT`,
t1.`MAILTO`,
'1' as `REPORTSTATUS`,
t1.`REPORTTO`,
DATE_FORMAT(t1.SUBMITTIME,'%H:%i') as `SUBMITTIME`,
null AS TODAYTASK,
null AS TOMORROWPLANSTASK,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
null as `UPDATEMANNAME`,
CONCAT_WS('','工作内容：',case when t1.CONTENT is null then '无' else t1.CONTENT end)  as WORKTODAY,
null as `COMMENT`,
null as PLANSTOMORROW,
'reportly' as type 
FROM `T_IBZ_REPORTLY` t1
where t1.ISSUBMIT = '1'
) t1
```
### 数据查询(DEFAULT)<div id="IbzReport_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`DATE`,
t1.`IBZ_DAILYID`,
t1.`IBZ_DAILYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
t1.`REPORTSTATUS`,
t1.`REPORTTO`,
t1.`TODAYTASK`,
t1.`TOMORROWPLANSTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`
FROM `t_ibz_daily` t1 

```
### 汇报汇总（我收到的）(MyReAllReport)<div id="IbzReport_MyReAllReport"></div>
```sql
select t1.* from (SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
DATE_FORMAT(t1.DATE,'%Y-%m-%d') as `DATE`,
t1.`IBZ_DAILYID`,
t1.`IBZ_DAILYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
(case when t11.id is not null then '1' else '0' end ) as `REPORTSTATUS`,
t1.`REPORTTO`,
DATE_FORMAT(t1.SUBMITTIME,'%H:%i') as `SUBMITTIME`,
t1.`TODAYTASK`,
t1.`TOMORROWPLANSTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`,
CONCAT_WS('','今日工作：',case when t1.WORKTODAY is null then '无' else t1.WORKTODAY end) as WORKTODAY,
t1.`COMMENT`,
CONCAT_WS('','明日计划：',case when t1.PLANSTOMORROW is null then '无' else t1.PLANSTOMORROW end) AS PLANSTOMORROW,
'daily' as type 
FROM `T_IBZ_DAILY` t1 
left join zt_action t11 on t11.objectID = t1.IBZ_DAILYID and t11.objectType = 'daily' and t11.action = 'read' and t11.actor = #{srf.sessioncontext.srfloginname}
where t1.ISSUBMIT = '1'
UNION
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
DATE_FORMAT(t1.DATE,'%Y-%m-%d') as `DATE`,
t1.`IBZ_WEEKLYID` as IBZ_DAILYID,
t1.`IBZ_WEEKLYNAME` as IBZ_DAILYNAME,
t1.`ISSUBMIT`,
t1.`MAILTO`,
(case when t11.id is not null then '1' else '0' end ) as `REPORTSTATUS`,
t1.`REPORTTO`,
DATE_FORMAT(t1.SUBMITTIME,'%H:%i') as `SUBMITTIME`,
t1.`THISWEEKTASK` as TODAYTASK,
t1.`NEXTWEEKTASK` as TOMORROWPLANSTASK,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`,
CONCAT_WS('','本周工作：',case when t1.WORKTHISWEEK is null then '无' else t1.WORKTHISWEEK end)  as WORKTODAY,
t1.`COMMENT`,
CONCAT_WS('','下周计划：',case when t1.PLANNEXTWEEK is null then '无' else t1.PLANNEXTWEEK end) as PLANSTOMORROW,
'weekly' as type 
FROM `T_IBZ_WEEKLY` t1 
left join zt_action t11 on t11.objectID = t1.IBZ_WEEKLYID and t11.objectType = 'weekly' and t11.action = 'read' and t11.actor = #{srf.sessioncontext.srfloginname}
where t1.ISSUBMIT = '1'
UNION
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
DATE_FORMAT(t1.DATE,'%Y-%m-%d') as `DATE`,
t1.`IBZ_MONTHLYID` AS IBZ_DAILYID,
t1.`IBZ_MONTHLYNAME` AS IBZ_DAILYNAME,
t1.`ISSUBMIT`,
t1.`MAILTO`,
(case when t11.id is not null then '1' else '0' end ) as `REPORTSTATUS`,
t1.`REPORTTO`,
DATE_FORMAT(t1.SUBMITTIME,'%H:%i') as `SUBMITTIME`,
t1.`THISMONTHTASK` AS TODAYTASK,
t1.`NEXTMONTHPLANSTASK` AS TOMORROWPLANSTASK,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`,
CONCAT_WS('','本月工作：',case when t1.WORKTHISMONTH is null then '无' else t1.WORKTHISMONTH end)  as WORKTODAY,
t1.`COMMENT`,
CONCAT_WS('','下月计划：',case when t1.PLANSNEXTMONTH is null then '无' else t1.PLANSNEXTMONTH end) as PLANSTOMORROW,
'monthly' as type 
FROM `T_IBZ_MONTHLY` t1
left join zt_action t11 on t11.objectID = t1.IBZ_MONTHLYID and t11.objectType = 'monthly' and t11.action = 'read' and t11.actor = #{srf.sessioncontext.srfloginname}
where t1.ISSUBMIT = '1'
UNION 
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
null as `CREATEMANNAME`,
DATE_FORMAT(t1.DATE,'%Y-%m-%d') as `DATE`,
t1.`IBZ_REPORTLYID` as IBZ_DAILYID,
t1.`IBZ_REPORTLYNAME` as IBZ_DAILYNAME,
t1.`ISSUBMIT`,
t1.`MAILTO`,
(case when t11.id is not null then '1' else '0' end ) as `REPORTSTATUS`,
t1.`REPORTTO`,
DATE_FORMAT(t1.SUBMITTIME,'%H:%i') as `SUBMITTIME`,
null as TODAYTASK,
null as TOMORROWPLANSTASK,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
null as `UPDATEMANNAME`,
CONCAT_WS('','工作内容：',case when t1.CONTENT is null then '无' else t1.CONTENT end) as WORKTODAY,
null as `COMMENT`,
null as PLANSTOMORROW,
'reportly' as type 
FROM `T_IBZ_REPORTLY` t1 
left join zt_action t11 on t11.objectID = t1.IBZ_REPORTLYID and t11.objectType = 'reportly' and t11.action = 'read' and t11.actor = #{srf.sessioncontext.srfloginname}
where t1.ISSUBMIT = '1'
) t1
WHERE (t1.REPORTTO = #{srf.sessioncontext.srfloginname} or FIND_IN_SET(#{srf.sessioncontext.srfloginname},t1.MAILTO)) 

```
### 默认（全部数据）(VIEW)<div id="IbzReport_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`COMMENT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`DATE`,
t1.`IBZ_DAILYID`,
t1.`IBZ_DAILYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
t1.`PLANSTOMORROW`,
t1.`REPORTSTATUS`,
t1.`REPORTTO`,
t1.`TODAYTASK`,
t1.`TOMORROWPLANSTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`,
t1.`WORKTODAY`
FROM `t_ibz_daily` t1 

```

# **汇报角色配置**(IBZ_REPORT_ROLE_CONFIG)

### 数据查询(DEFAULT)<div id="IbzReportRoleConfig_Default"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`IBZ_REPORT_ROLE_CONFIGID`,
t1.`IBZ_REPORT_ROLE_CONFIGNAME`,
t1.`REPORT_ROLE`,
t1.`TYPE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZ_REPORT_ROLE_CONFIG` t1 

```
### 默认（全部数据）(VIEW)<div id="IbzReportRoleConfig_View"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`IBZ_REPORT_ROLE_CONFIGID`,
t1.`IBZ_REPORT_ROLE_CONFIGNAME`,
t1.`REPORT_ROLE`,
t1.`TYPE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZ_REPORT_ROLE_CONFIG` t1 

```

# **汇报**(IBZ_REPORTLY)

### 数据查询(DEFAULT)<div id="IbzReportly_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DATE`,
t1.`IBZ_REPORTLYID`,
t1.`IBZ_REPORTLYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
t1.mailto AS `MAILTOPK`,
t1.`REPORTSTATUS`,
t1.`REPORTTO`,
t1.reportto AS `REPORTTOPK`,
t1.`SUBMITTIME`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZ_REPORTLY` t1 

```
### 我所有的汇报(MyAllReportly)<div id="IbzReportly_MyAllReportly"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DATE`,
t1.`IBZ_REPORTLYID`,
t1.`IBZ_REPORTLYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
t1.`REPORTSTATUS`,
t1.`REPORTTO`,
t1.`SUBMITTIME`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZ_REPORTLY` t1
WHERE t1.account = #{srf.sessioncontext.srfloginname} 

```
### 我收到的汇报(MyReceived)<div id="IbzReportly_MyReceived"></div>
```sql
SELECT
	t1.`ACCOUNT`,
	t1.`CONTENT`,
	t1.`CREATEDATE`,
	t1.`CREATEMAN`,
	t1.`DATE`,
	t1.`IBZ_REPORTLYID`,
	t1.`IBZ_REPORTLYNAME`,
	t1.`ISSUBMIT`,
	t1.`MAILTO`,
	( CASE WHEN t11.id IS NOT NULL THEN '1' ELSE '0' END ) AS `REPORTSTATUS`,
	t1.`REPORTTO`,
	t1.`SUBMITTIME`,
	t1.`UPDATEDATE`,
	t1.`UPDATEMAN` 
FROM
	`t_ibz_reportly` t1
	LEFT JOIN zt_action t11 ON t11.objectID = t1.IBZ_REPORTLYID 
	AND t11.objectType = 'reportly' 
	AND t11.action = 'read' 
	AND t11.actor = #{srf.sessioncontext.srfloginname}
WHERE t1.issubmit = '1' 
(t1.REPORTTO = #{srf.sessioncontext.srfloginname} OR FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.MAILTO )) 

```
### 我的未提交汇报(MyReportlyMob)<div id="IbzReportly_MyReportlyMob"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
DATE_FORMAT(t1.DATE,'%Y-%m-%d') as `DATE`,
t1.`IBZ_REPORTLYID`,
t1.`IBZ_REPORTLYNAME`,
t1.`ISSUBMIT`,
CONCAT_WS('','工作内容：',case when t1.CONTENT is null then '无' else t1.CONTENT end)  as `CONTENT`,
t1.`MAILTO`,
'1' as `REPORTSTATUS`,
t1.`REPORTTO`,
t1.`SUBMITTIME`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`, 
'reportly' as type 
FROM `T_IBZ_REPORTLY` t1
WHERE t1.account = #{srf.sessioncontext.srfloginname} 

```
### 默认（全部数据）(VIEW)<div id="IbzReportly_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CONTENT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DATE`,
t1.`IBZ_REPORTLYID`,
t1.`IBZ_REPORTLYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
t1.mailto AS `MAILTOPK`,
t1.`REPORTSTATUS`,
t1.`REPORTTO`,
t1.reportto AS `REPORTTOPK`,
t1.`SUBMITTIME`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZ_REPORTLY` t1 

```

# **置顶**(IBZ_TOP)

### 数据查询(DEFAULT)<div id="IbzTop_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`IBZ_TOPID`,
t1.`IBZ_TOPNAME`,
t1.`OBJECTID`,
t1.`OBJECTORDER`,
t1.`TYPE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZ_TOP` t1 

```
### 默认（全部数据）(VIEW)<div id="IbzTop_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`IBZ_TOPID`,
t1.`IBZ_TOPNAME`,
t1.`OBJECTID`,
t1.`OBJECTORDER`,
t1.`TYPE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZ_TOP` t1 

```

# **周报**(IBZ_WEEKLY)

### 数据查询(DEFAULT)<div id="IbzWeekly_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`DATE`,
t1.`IBZ_WEEKLYID`,
t1.`IBZ_WEEKLYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
t1.mailto AS `MAILTOPK`,
t1.`NEXTWEEKTASK`,
t1.`REPORTSTATUS`,
t1.`REPORTTO`,
t1.reportto AS `REPORTTOPK`,
t1.`SUBMITTIME`,
t1.`THISWEEKTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`
FROM `T_IBZ_WEEKLY` t1 

```
### 我的周报(MyNotSubmit)<div id="IbzWeekly_MyNotSubmit"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`COMMENT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`DATE`,
t1.`IBZ_WEEKLYID`,
t1.`IBZ_WEEKLYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
t1.`NEXTWEEKTASK`,
CONCAT_WS('','下周计划：',case when t1.PLANNEXTWEEK is null then '无' else t1.PLANNEXTWEEK end) AS `PLANNEXTWEEK`,
'1' as `REPORTSTATUS`,
t1.`REPORTTO`,
t1.`SUBMITTIME`,
t1.`THISWEEKTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`,
CONCAT_WS('','本周工作：',case when t1.WORKTHISWEEK is null then '无' else t1.WORKTHISWEEK end)  as WORKTHISWEEK
FROM `T_IBZ_WEEKLY` t1
```
### 我收到的周报(MyWeekly)<div id="IbzWeekly_MyWeekly"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`COMMENT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`DATE`,
t1.`IBZ_WEEKLYID`,
t1.`IBZ_WEEKLYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
t1.`NEXTWEEKTASK`,
t1.`PLANNEXTWEEK`,
(case when t11.id is not null then '1' else '0' end ) as `REPORTSTATUS`,
t1.`REPORTTO`,
t1.`THISWEEKTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`
FROM `T_IBZ_WEEKLY` t1 
left join zt_action t11 on t11.objectID = t1.IBZ_WEEKLYID and t11.objectType = 'weekly' and t11.action = 'read' and t11.actor = #{srf.sessioncontext.srfloginname}
WHERE t1.issubmit = '1' 
(t1.REPORTTO = #{srf.sessioncontext.srfloginname} or FIND_IN_SET(#{srf.sessioncontext.srfloginname},t1.MAILTO)) 

```
### 产品团队成员周报(ProductTeamMemberWeekly)<div id="IbzWeekly_ProductTeamMemberWeekly"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`DATE`,
t1.`IBZ_WEEKLYID`,
t1.`IBZ_WEEKLYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
t1.mailto AS `MAILTOPK`,
t1.`NEXTWEEKTASK`,
t1.`REPORTSTATUS`,
t1.`REPORTTO`,
t1.reportto AS `REPORTTOPK`,
t1.`SUBMITTIME`,
t1.`THISWEEKTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`
FROM `T_IBZ_WEEKLY` t1 

WHERE ( t1.`ISSUBMIT` = '1'  AND  t1.ACCOUNT in (select t.ACCOUNT from zt_team t where t.type = 'product' and t.root =${srfdatacontext('product')})  
and YEARWEEK(t1.date,1) = YEARWEEK(${srfdatacontext('date')},1) ) 

```
### 项目周报(ProjectWeekly)<div id="IbzWeekly_ProjectWeekly"></div>
```sql
SELECT t1.`ACCOUNT`, t1.`CREATEDATE`, t1.`CREATEMAN`, t1.`CREATEMANNAME`, t1.`DATE`, t1.`IBZ_WEEKLYID`, t1.`IBZ_WEEKLYNAME`, t1.`ISSUBMIT`, t1.`MAILTO`, t1.MAILTO AS `MAILTOPK`, t1.`REPORTSTATUS`, t1.`REPORTTO`, t1.REPORTTO AS `REPORTTOPK`, t1.`SUBMITTIME`, t1.`THISWEEKTASK`, t1.`NEXTWEEKTASK`, t1.`UPDATEDATE`, t1.`UPDATEMAN`, t1.`UPDATEMANNAME` FROM `T_IBZ_WEEKLY` t1
WHERE ( t1.`ISSUBMIT` = '1'  AND  t1.ACCOUNT in (select t.ACCOUNT from zt_team t where t.type = 'project' and t.root =${srfdatacontext('curproject')})  AND  (DATE_FORMAT(t1.date,'%Y-%m-%d') between DATE_FORMAT(${srfdatacontext('begindatestats')},'%Y-%m-%d') and DATE_FORMAT(${srfdatacontext('enddatestats')},'%Y-%m-%d'))  ) 

```
### 默认（全部数据）(VIEW)<div id="IbzWeekly_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`COMMENT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`DATE`,
t1.`IBZ_WEEKLYID`,
t1.`IBZ_WEEKLYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
t1.mailto AS `MAILTOPK`,
t1.`NEXTWEEKTASK`,
t1.`PLANNEXTWEEK`,
t1.`REPORTSTATUS`,
t1.`REPORTTO`,
t1.reportto AS `REPORTTOPK`,
t1.`SUBMITTIME`,
t1.`THISWEEKTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`,
t1.`WORKTHISWEEK`
FROM `T_IBZ_WEEKLY` t1 

```

# **系统配置表**(IBZPRO_CONFIG)

### 数据查询(DEFAULT)<div id="IbzproConfig_Default"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`IBZPRO_CONFIGID`,
t1.`IBZPRO_CONFIGNAME`,
t1.`MANAGEMENTSTATUS`,
t1.`MEMO`,
t1.`SCOPE`,
t1.`TYPE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`VAILD`
FROM `T_IBZPRO_CONFIG` t1 

```
### 默认（全部数据）(VIEW)<div id="IbzproConfig_View"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`IBZPRO_CONFIGID`,
t1.`IBZPRO_CONFIGNAME`,
t1.`MANAGEMENTSTATUS`,
t1.`MEMO`,
t1.`SCOPE`,
t1.`TYPE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`VAILD`
FROM `T_IBZPRO_CONFIG` t1 

```

# **产品汇报用户任务**(IBIZPRO_PRODUCTUSERTASK)

### 数据查询(DEFAULT)<div id="IbzproProductUserTask_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CONSUMED`,
t1.`DATE`,
t1.`ID`,
t1.`LEFT`,
t1.`TASK`
FROM `zt_taskestimate` t1 

```
### 产品日报用户任务统计(ProductDailyUserTaskStats)<div id="IbzproProductUserTask_ProductDailyUserTaskStats"></div>
```sql
select 
t1.*,
t2.`name` AS taskname,
t2.deadline,
t2.ESTSTARTED,
t2.type AS TASKTYPE,
(CONCAT_WS('', CASE WHEN t2.consumed = 0 OR t2.consumed IS NULL THEN '0' WHEN t2.`left` = 0 OR t2.`left` IS NULL THEN '100' ELSE ROUND((ROUND(t2.`consumed`/( t2.`left` + t2.consumed ), 2 )) * 100 ) END, '%')) AS PROGRESSRATE, 
((CASE WHEN t2.deadline IS NULL OR t2.deadline = '0000-00-00' OR t2.deadline = '1970-01-01' THEN '' WHEN t2.`status` IN ( 'wait', 'doing' ) AND t2.deadline < DATE_FORMAT(now(), '%Y-%m-%d') THEN CONCAT_WS('', '延期', TIMESTAMPDIFF(DAY, t2.deadline, now()), '天') ELSE '' END)) AS DELAYDAYS 
from
(SELECT
t1.DATE,
t1.TASK,
t1.ACCOUNT,
ROUND(sum(t1.CONSUMED), 2) AS CONSUMED,
task AS id 
FROM
`zt_taskestimate` t1 
WHERE
t1.date >= DATE_FORMAT(${srfdatacontext('begin')}, '%Y-%m-%d')
AND t1.date <= DATE_FORMAT(${srfdatacontext('end')}, '%Y-%m-%d')
GROUP BY t1.DATE, t1.TASK, t1.ACCOUNT) t1
left join zt_task t2 
on t1.task = t2.id 
where 
FIND_IN_SET(t1.task, ${srfdatacontext('tasks')})
```
### 产品月报用户任务统计(ProductMonthlyUserTaskStats)<div id="IbzproProductUserTask_ProductMonthlyUserTaskStats"></div>
```sql
SELECT
t1.*,
t2.`name` AS taskname,
t2.deadline,
t2.ESTSTARTED,
t2.type AS TASKTYPE,
(CONCAT_WS('', CASE WHEN t2.consumed = 0 OR t2.consumed IS NULL THEN '0' WHEN t2.`left` = 0 OR t2.`left` IS NULL THEN '100' ELSE ROUND(( ROUND( t2.`consumed` /( t2.`left` + t2.consumed ), 2 )) * 100 ) END, '%')) AS PROGRESSRATE,
((CASE WHEN t2.deadline IS NULL OR t2.deadline = '0000-00-00' OR t2.deadline = '1970-01-01' THEN '' WHEN t2.`status` IN ( 'wait', 'doing' ) AND t2.deadline < DATE_FORMAT( now(), '%Y-%m-%d' ) THEN CONCAT_WS( '', '延期', TIMESTAMPDIFF( DAY, t2.deadline, now()), '天' ) ELSE '' END )) AS DELAYDAYS 
FROM
(SELECT
t1.DATE,
t1.TASK,
t1.ACCOUNT,
ROUND(sum(t1.CONSUMED), 2) AS CONSUMED,
task AS id 
FROM
`zt_taskestimate` t1 
WHERE ${srfdatacontext('yearmonth')} = DATE_FORMAT(t1.date, '%Y-%m')
GROUP BY t1.DATE, t1.TASK, t1.ACCOUNT 
) t1
LEFT JOIN zt_task t2 ON t1.task = t2.id
WHERE FIND_IN_SET(t1.task, ${srfdatacontext('tasks')})
```
### 产品周报用户任务统计(ProductWeeklyUserTaskStats)<div id="IbzproProductUserTask_ProductWeeklyUserTaskStats"></div>
```sql
select 
t1.*,
t2.`name` AS taskname,
t2.deadline,
t2.ESTSTARTED,
t2.type AS TASKTYPE,
(CONCAT_WS('', CASE WHEN t2.consumed = 0 OR t2.consumed IS NULL THEN '0' WHEN t2.`left` = 0 OR t2.`left` IS NULL THEN '100' ELSE ROUND((ROUND(t2.`consumed`/( t2.`left` + t2.consumed ), 2 )) * 100 ) END, '%')) AS PROGRESSRATE, 
((CASE WHEN t2.deadline IS NULL OR t2.deadline = '0000-00-00' OR t2.deadline = '1970-01-01' THEN '' WHEN t2.`status` IN ( 'wait', 'doing' ) AND t2.deadline < DATE_FORMAT(now(), '%Y-%m-%d') THEN CONCAT_WS('', '延期', TIMESTAMPDIFF(DAY, t2.deadline, now()), '天') ELSE '' END)) AS DELAYDAYS 
from
(SELECT
t1.DATE,
t1.TASK,
t1.ACCOUNT,
ROUND(sum(t1.CONSUMED), 2) AS CONSUMED,
task AS id 
FROM
`zt_taskestimate` t1 
WHERE
t1.date >= DATE_FORMAT(${srfdatacontext('begin')}, '%Y-%m-%d')
AND t1.date <= DATE_FORMAT(${srfdatacontext('end')}, '%Y-%m-%d')
GROUP BY t1.DATE, t1.TASK, t1.ACCOUNT) t1
left join zt_task t2 
on t1.task = t2.id 
where 
FIND_IN_SET(t1.task, ${srfdatacontext('tasks')})

```
### 默认（全部数据）(VIEW)<div id="IbzproProductUserTask_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CONSUMED`,
t1.`DATE`,
t1.`ID`,
t1.`LEFT`,
t1.`TASK`
FROM `zt_taskestimate` t1 

```

# **项目汇报用户任务**(IBIZPRO_PROJECTUSERTASK)

### 数据查询(DEFAULT)<div id="IbzproProjectUserTask_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CONSUMED`,
t1.`DATE`,
t1.`ID`,
t1.`LEFT`,
t1.`TASK`
FROM `zt_taskestimate` t1 

```
### 项目日报任务(ProjectDailyTask)<div id="IbzproProjectUserTask_ProjectDailyTask"></div>
```sql
select t1.*,t11.`name` as taskname,t11.deadline,t11.ESTSTARTED,t11.type as TASKTYPE,(CONCAT_WS('',case when t11.consumed = 0 or t11.consumed is null then '0' when t11.`left` = 0 or t11.`left` is null then '100' else ROUND((ROUND(t11.`consumed`/(t11.`left` + t11.consumed),2)) * 100) end ,'%')) as PROGRESSRATE,((case when t11.deadline is null or t11.deadline = '0000-00-00' or t11.deadline = '1970-01-01' then '' when t11.`status` in ('wait','doing') and t11.deadline <DATE_FORMAT(now(),'%Y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t11.deadline, now()),'天') else '' end))as DELAYDAYS from (select t1.DATE,t1.TASK,t1.ACCOUNT,ROUND(sum(t1.CONSUMED),2) as CONSUMED,task as id from (
SELECT
t1.`ACCOUNT`,
t1.`CONSUMED`,
t1.`DATE`,
t1.`ID`,
t1.`LEFT`,
t1.`TASK`
FROM `zt_taskestimate` t1 where t1.date >= DATE_FORMAT(${srfdatacontext('begin')},'%y-%m-%d') and t1.date <= DATE_FORMAT(${srfdatacontext('end')},'%y-%m-%d')) t1 GROUP BY t1.DATE,t1.TASK,t1.ACCOUNT) t1 left join zt_task t11 on t1.task = t11.id
WHERE FIND_IN_SET(t1.task, ${srfdatacontext('tasks')}) 

```
### 项目月报任务(ProjectMonthlyTask)<div id="IbzproProjectUserTask_ProjectMonthlyTask"></div>
```sql
select t1.*,t11.`name` as taskname,t11.deadline,t11.ESTSTARTED,t11.type as TASKTYPE,(CONCAT_WS('',case when t11.consumed = 0 or t11.consumed is null then '0' when t11.`left` = 0 or t11.`left` is null then '100' else ROUND((ROUND(t11.`consumed`/(t11.`left` + t11.consumed),2)) * 100) end ,'%')) as PROGRESSRATE,((case when t11.deadline is null or t11.deadline = '0000-00-00' or t11.deadline = '1970-01-01' then '' when t11.`status` in ('wait','doing') and t11.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t11.deadline, now()),'天') else '' end))as DELAYDAYS from (select t1.DATE,t1.TASK,t1.ACCOUNT,ROUND(sum(t1.CONSUMED),2) as CONSUMED,task as id from (
SELECT
t1.`ACCOUNT`,
t1.`CONSUMED`,
t1.`DATE`,
t1.`ID`,
t1.`LEFT`,
t1.`TASK`
FROM `zt_taskestimate` t1 where ${srfdatacontext('yearmonth')} =DATE_FORMAT(t1.date,'%Y-%m')) t1 GROUP BY t1.DATE,t1.TASK,t1.ACCOUNT) t1 left join zt_task t11 on t1.task = t11.id
WHERE FIND_IN_SET(t1.task, ${srfdatacontext('tasks')}) 

```
### 项目周报任务(ProjectWeeklyTask)<div id="IbzproProjectUserTask_ProjectWeeklyTask"></div>
```sql
select t1.*,t11.`name` as taskname,t11.deadline,t11.ESTSTARTED,t11.type as TASKTYPE,(CONCAT_WS('',case when t11.consumed = 0 or t11.consumed is null then '0' when t11.`left` = 0 or t11.`left` is null then '100' else ROUND((ROUND(t11.`consumed`/(t11.`left` + t11.consumed),2)) * 100) end ,'%')) as PROGRESSRATE,((case when t11.deadline is null or t11.deadline = '0000-00-00' or t11.deadline = '1970-01-01' then '' when t11.`status` in ('wait','doing') and t11.deadline <DATE_FORMAT(now(),'%Y-%m-%d') then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t11.deadline, now()),'天') else '' end))as DELAYDAYS from (select t1.DATE,t1.TASK,t1.ACCOUNT,ROUND(sum(t1.CONSUMED),2) as CONSUMED,task as id from ( SELECT t1.`ACCOUNT`, t1.`CONSUMED`, t1.`DATE`, t1.`ID`, t1.`LEFT`, t1.`TASK` FROM `zt_taskestimate` t1 where (DATE_FORMAT(t1.date,'%Y-%m-%d') between DATE_FORMAT(${srfdatacontext('begindatestats')},'%Y-%m-%d') and DATE_FORMAT(${srfdatacontext('enddatestats')},'%Y-%m-%d'))) t1 GROUP BY 
 t1.TASK,t1.ACCOUNT) t1 left join zt_task t11 on t1.task = t11.id
WHERE FIND_IN_SET(t1.task, ${srfdatacontext('tasks')}) 

```
### 默认（全部数据）(VIEW)<div id="IbzproProjectUserTask_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CONSUMED`,
t1.`DATE`,
t1.`ID`,
t1.`LEFT`,
t1.`TASK`,
t1.`WORK`
FROM `zt_taskestimate` t1 

```

# **ImChat**(ZT_IM_CHAT)

### DEFAULT(DEFAULT)<div id="ImChat_Default"></div>
```sql
SELECT
t1.`ADMINS`,
t1.`COMMITTERS`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`DISMISSDATE`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`GID`,
t1.`ID`,
t1.`LASTACTIVETIME`,
t1.`NAME`,
t1.`PUBLIC`,
t1.`SUBJECT`,
t1.`TYPE`
FROM `zt_im_chat` t1 

```
### 默认（全部数据）(VIEW)<div id="ImChat_View"></div>
```sql
SELECT
t1.`ADMINS`,
t1.`COMMITTERS`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`DISMISSDATE`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`GID`,
t1.`ID`,
t1.`LASTACTIVETIME`,
t1.`NAME`,
t1.`PUBLIC`,
t1.`SUBJECT`,
t1.`TYPE`
FROM `zt_im_chat` t1 

```

# **ImChatuser**(ZT_IM_CHATUSER)

### DEFAULT(DEFAULT)<div id="ImChatuser_Default"></div>
```sql
SELECT
t1.`CATEGORY`,
t1.`CGID`,
t1.`FREEZE`,
t1.`HIDE`,
t1.`ID`,
t1.`JOIN`,
t1.`MUTE`,
t1.`ORDER`,
t1.`QUIT`,
t1.`STAR`,
t1.`USER`
FROM `zt_im_chatuser` t1 

```
### 默认（全部数据）(VIEW)<div id="ImChatuser_View"></div>
```sql
SELECT
t1.`CATEGORY`,
t1.`CGID`,
t1.`FREEZE`,
t1.`HIDE`,
t1.`ID`,
t1.`JOIN`,
t1.`MUTE`,
t1.`ORDER`,
t1.`QUIT`,
t1.`STAR`,
t1.`USER`
FROM `zt_im_chatuser` t1 

```

# **ImClient**(ZT_IM_CLIENT)

### DEFAULT(DEFAULT)<div id="ImClient_Default"></div>
```sql
SELECT
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`DESC`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`ID`,
t1.`STATUS`,
t1.`STRATEGY`,
t1.`VERSION`
FROM `zt_im_client` t1 

```
### 默认（全部数据）(VIEW)<div id="ImClient_View"></div>
```sql
SELECT
t1.`CHANGELOG`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`DESC`,
t1.`DOWNLOADS`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`ID`,
t1.`STATUS`,
t1.`STRATEGY`,
t1.`VERSION`
FROM `zt_im_client` t1 

```

# **ImConference**(ZT_IM_CONFERENCE)

### DEFAULT(DEFAULT)<div id="ImConference_Default"></div>
```sql
SELECT
t1.`CGID`,
t1.`ID`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`RID`,
t1.`STATUS`
FROM `zt_im_conference` t1 

```
### 默认（全部数据）(VIEW)<div id="ImConference_View"></div>
```sql
SELECT
t1.`CGID`,
t1.`ID`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARTICIPANTS`,
t1.`RID`,
t1.`STATUS`
FROM `zt_im_conference` t1 

```

# **ImConferenceaction**(ZT_IM_CONFERENCEACTION)

### DEFAULT(DEFAULT)<div id="ImConferenceaction_Default"></div>
```sql
SELECT
t1.`DATE`,
t1.`ID`,
t1.`RID`,
t1.`TYPE`,
t1.`USER`
FROM `zt_im_conferenceaction` t1 

```
### 默认（全部数据）(VIEW)<div id="ImConferenceaction_View"></div>
```sql
SELECT
t1.`DATE`,
t1.`ID`,
t1.`RID`,
t1.`TYPE`,
t1.`USER`
FROM `zt_im_conferenceaction` t1 

```

# **ImMessage**(ZT_IM_MESSAGE)

### DEFAULT(DEFAULT)<div id="ImMessage_Default"></div>
```sql
SELECT
t1.`CGID`,
t1.`CONTENTTYPE`,
t1.`DATE`,
t1.`DELETED`,
t1.`GID`,
t1.`ID`,
t1.`ORDER`,
t1.`TYPE`,
t1.`USER`
FROM `zt_im_message` t1 

WHERE t1.DELETED = '0' 

```
### 默认（全部数据）(VIEW)<div id="ImMessage_View"></div>
```sql
SELECT
t1.`CGID`,
t1.`CONTENT`,
t1.`CONTENTTYPE`,
t1.`DATA`,
t1.`DATE`,
t1.`DELETED`,
t1.`GID`,
t1.`ID`,
t1.`ORDER`,
t1.`TYPE`,
t1.`USER`
FROM `zt_im_message` t1 

WHERE t1.DELETED = '0' 

```

# **ImMessagestatus**(ZT_IM_MESSAGESTATUS)

### DEFAULT(DEFAULT)<div id="ImMessagestatus_Default"></div>
```sql
SELECT
concat(t1.`USER`,'__',t1.`MESSAGE`) AS `ID`,
t1.`MESSAGE`,
t1.`STATUS`,
t1.`USER`
FROM `zt_im_messagestatus` t1 

```
### 默认（全部数据）(VIEW)<div id="ImMessagestatus_View"></div>
```sql
SELECT
concat(t1.`USER`,'__',t1.`MESSAGE`) AS `ID`,
t1.`MESSAGE`,
t1.`STATUS`,
t1.`USER`
FROM `zt_im_messagestatus` t1 

```

# **ImQueue**(ZT_IM_QUEUE)

### DEFAULT(DEFAULT)<div id="ImQueue_Default"></div>
```sql
SELECT
t1.`ADDDATE`,
t1.`ID`,
t1.`PROCESSDATE`,
t1.`STATUS`,
t1.`TYPE`
FROM `zt_im_queue` t1 

```
### 默认（全部数据）(VIEW)<div id="ImQueue_View"></div>
```sql
SELECT
t1.`ADDDATE`,
t1.`CONTENT`,
t1.`ID`,
t1.`PROCESSDATE`,
t1.`RESULT`,
t1.`STATUS`,
t1.`TYPE`
FROM `zt_im_queue` t1 

```

# **jenkins**(ZT_JENKINS)

### DEFAULT(DEFAULT)<div id="Jenkins_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`DELETED`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`ID`,
t1.`NAME`,
t1.`PASSWORD`,
t1.`TOKEN`,
t1.`URL`
FROM `zt_jenkins` t1 

WHERE t1.DELETED = '0' 

```
### 默认（全部数据）(VIEW)<div id="Jenkins_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`DELETED`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`ID`,
t1.`NAME`,
t1.`PASSWORD`,
t1.`TOKEN`,
t1.`URL`
FROM `zt_jenkins` t1 

WHERE t1.DELETED = '0' 

```

# **job**(ZT_JOB)

### DEFAULT(DEFAULT)<div id="Job_Default"></div>
```sql
SELECT
t1.`ATDAY`,
t1.`ATTIME`,
t1.`COMMENT`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`DELETED`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`FRAME`,
t1.`ID`,
t1.`JKHOST`,
t1.`JKJOB`,
t1.`LASTEXEC`,
t1.`LASTSTATUS`,
t1.`LASTTAG`,
t1.`NAME`,
t1.`PRODUCT`,
t1.`REPO`,
t1.`SVNDIR`,
t1.`TRIGGERTYPE`
FROM `zt_job` t1 

WHERE t1.DELETED = '0' 

```
### 默认（全部数据）(VIEW)<div id="Job_View"></div>
```sql
SELECT
t1.`ATDAY`,
t1.`ATTIME`,
t1.`COMMENT`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`DELETED`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`FRAME`,
t1.`ID`,
t1.`JKHOST`,
t1.`JKJOB`,
t1.`LASTEXEC`,
t1.`LASTSTATUS`,
t1.`LASTTAG`,
t1.`NAME`,
t1.`PRODUCT`,
t1.`REPO`,
t1.`SVNDIR`,
t1.`TRIGGERTYPE`
FROM `zt_job` t1 

WHERE t1.DELETED = '0' 

```

# **lang**(ZT_LANG)

### DEFAULT(DEFAULT)<div id="Lang_Default"></div>
```sql
SELECT
t1.`ID`,
t1.`KEY`,
t1.`LANG`,
t1.`MODULE`,
t1.`SECTION`,
t1.`SYSTEM`
FROM `zt_lang` t1 

```
### 默认（全部数据）(VIEW)<div id="Lang_View"></div>
```sql
SELECT
t1.`ID`,
t1.`KEY`,
t1.`LANG`,
t1.`MODULE`,
t1.`SECTION`,
t1.`SYSTEM`,
t1.`VALUE`
FROM `zt_lang` t1 

```

# **log**(ZT_LOG)

### DEFAULT(DEFAULT)<div id="Log_Default"></div>
```sql
SELECT
t1.`ACTION`,
t1.`CONTENTTYPE`,
t1.`DATE`,
t1.`ID`,
t1.`OBJECTID`,
t1.`OBJECTTYPE`,
t1.`URL`
FROM `zt_log` t1 

```
### 默认（全部数据）(VIEW)<div id="Log_View"></div>
```sql
SELECT
t1.`ACTION`,
t1.`CONTENTTYPE`,
t1.`DATA`,
t1.`DATE`,
t1.`ID`,
t1.`OBJECTID`,
t1.`OBJECTTYPE`,
t1.`RESULT`,
t1.`URL`
FROM `zt_log` t1 

```

# **模块**(ZT_MODULE)

### BugModule(BugModule)<div id="Module_BugModule"></div>
```sql
SELECT
	t1.`BRANCH`,
	t1.`DELETED`,
	t1.`GRADE`,
	t1.`ID`,
	CONCAT(
		'/',
	CASE
			
			WHEN t1.parent > 0 THEN
			(
			SELECT
				GROUP_CONCAT( tt.NAME SEPARATOR '/' ) 
			FROM
				zt_module tt 
			WHERE
				tt.type = 'story' 
				AND FIND_IN_SET( tt.id, t1.path ) 
			) ELSE t1.`name` 
		END 
		) AS `NAME`,
		(
			CONCAT_ws(
				'',
			CASE
					
					WHEN t1.parent > 0 THEN
					(
					SELECT
						GROUP_CONCAT( tt.`ORDER` SEPARATOR '-' ) 
					FROM
						zt_module tt 
					WHERE
						tt.type = 'story' 
					AND FIND_IN_SET( tt.id, t1.path )) ELSE t1.`ORDER` 
				END 
				)) AS ORDERPK,
			t1.`ORDER`,
			t1.`OWNER`,
		CASE
				
				WHEN t1.`PARENT` = 0 THEN
				NULL ELSE t1.parent 
			END AS parent,
			t11.`NAME` AS `PARENTNAME`,
			t1.`PATH`,
			t1.`ROOT`,
			t1.`SHORT`,
			t1.`TYPE` 
		FROM
			`zt_module` t1
			LEFT JOIN zt_module t11 ON t1.PARENT = t11.ID
WHERE t1.DELETED = '0' 
t1.type = 'story' 

```
### 数据查询(BugModuleCodeList)<div id="Module_BugModuleCodeList"></div>
```sql
SELECT
	t1.`BRANCH`,
	t1.`DELETED`,
	t1.`GRADE`,
	t1.`ID`,
	CONCAT( '/', t1.`name` ) AS `NAME`,
	t1.`ORDER`,
	t1.`OWNER`,
	case when t1.`PARENT` = 0 then null else t1.parent end as parent ,
	t11.`NAME` AS `PARENTNAME`,
	t1.`PATH`,
	t1.`ROOT`,
	t1.`SHORT`,
	t1.`TYPE`
FROM
	`zt_module` t1
	LEFT JOIN zt_module t11 ON t1.PARENT = t11.ID
WHERE t1.DELETED = '0' 
t1.type = 'story' 

```
### DEFAULT(DEFAULT)<div id="Module_Default"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
t1.`MDEPTID`,
t1.`NAME`,
t1.`ORDER`,
t1.`ORGID`,
t1.`OWNER`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_module` t11 ON t1.`PARENT` = t11.`ID` 

WHERE t1.DELETED = '0' 

```
### 文档目录查询(DocModule)<div id="Module_DocModule"></div>
```sql
SELECT
	t1.* 
FROM
	(
	SELECT
		'0' AS DELETED,
		0 AS ID,
		'/' AS NAME,
		0 AS PARENT,
		',0,' AS path,
		$ { srfdatacontext ( 'doclib', '{"defname":"ROOT","dename":"ZT_MODULE"}' )} AS root,
		'doc' AS type UNION
	SELECT
		t1.`DELETED`,
		t1.`ID`,
		CONCAT(
			'/',
		CASE
				
				WHEN (
				SELECT
					GROUP_CONCAT( tt.NAME SEPARATOR '/' ) 
				FROM
					zt_module tt 
				WHERE
					FIND_IN_SET( tt.id, t1.path ) 
					AND tt.type = 'doc' 
				GROUP BY
					tt.root 
					LIMIT 0,
					1 
					) IS NOT NULL THEN
					(
					SELECT
						GROUP_CONCAT( tt.NAME SEPARATOR '/' ) 
					FROM
						zt_module tt 
					WHERE
						FIND_IN_SET( tt.id, t1.path ) 
						AND tt.type = 'doc' 
					GROUP BY
						tt.root 
						LIMIT 0,
						1 
					) ELSE t1.`name` 
				END 
				) AS `NAME`,
				t1.`PARENT`,
				t1.`PATH`,
				t1.`ROOT`,
				t1.`TYPE` 
			FROM
				`zt_module` t1
			LEFT JOIN zt_module t11 ON t1.PARENT = t11.ID 
	) t1
WHERE t1.DELETED = '0' 
( t1.`TYPE` = 'doc'  AND  t1.`ROOT` =  ${srfdatacontext('doclib','{"defname":"ROOT","dename":"ZT_MODULE"}')} ) 

```
### 产品线(Line)<div id="Module_Line"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
t1.`MDEPTID`,
t1.`NAME`,
t1.`ORDER`,
t1.`ORGID`,
t1.`OWNER`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_module` t11 ON t1.`PARENT` = t11.`ID` 

WHERE t1.DELETED = '0' 
( t1.`TYPE` = 'line'  AND  t1.`ORGID` =  ${srfsessioncontext('SRFORGID','{"defname":"ORGID","dename":"ZT_MODULE"}')} ) 

```
### 需求模块(StoryModule)<div id="Module_StoryModule"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
t1.`MDEPTID`,
t1.`NAME`,
t1.`ORDER`,
t1.`ORGID`,
t1.`OWNER`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_module` t11 ON t1.`PARENT` = t11.`ID` 

WHERE t1.DELETED = '0' 
( t1.`ROOT` =  ${srfwebcontext('product','{"defname":"ROOT","dename":"ZT_MODULE"}')}  AND  t1.`TYPE` = 'story'  AND  <#assign _value=srfdatacontext('branch','{"ignoreempty":true,"defname":"BRANCH","dename":"ZT_MODULE"}')><#if _value?length gt 0>t1.`BRANCH` = ${_value}<#else>1=1</#if> ) 

```
### 任务模块(TaskModule)<div id="Module_TaskModule"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
t1.`MDEPTID`,
t1.`NAME`,
t1.`ORDER`,
t1.`ORGID`,
t1.`OWNER`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_module` t11 ON t1.`PARENT` = t11.`ID` 

WHERE t1.DELETED = '0' 
( t1.`ROOT` =  ${srfdatacontext('project','{"defname":"ROOT","dename":"ZT_MODULE"}')}  AND  t1.`TYPE` = 'task' ) 

```
### 默认（全部数据）(VIEW)<div id="Module_View"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`COLLECTOR`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
t1.`MDEPTID`,
t1.`NAME`,
t1.`ORDER`,
t1.`ORGID`,
t1.`OWNER`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_module` t11 ON t1.`PARENT` = t11.`ID` 

WHERE t1.DELETED = '0' 

```

# **通知**(ZT_NOTIFY)

### DEFAULT(DEFAULT)<div id="Notify_Default"></div>
```sql
SELECT
t1.`ACTION`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`ID`,
t1.`OBJECTID`,
t1.`OBJECTTYPE`,
t1.`SENDTIME`,
t1.`STATUS`,
t1.`SUBJECT`,
t1.`TOLIST`
FROM `zt_notify` t1 

```
### 默认（全部数据）(VIEW)<div id="Notify_View"></div>
```sql
SELECT
t1.`ACTION`,
t1.`CCLIST`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`DATA`,
t1.`FAILREASON`,
t1.`ID`,
t1.`OBJECTID`,
t1.`OBJECTTYPE`,
t1.`SENDTIME`,
t1.`STATUS`,
t1.`SUBJECT`,
t1.`TOLIST`
FROM `zt_notify` t1 

```

# **产品团队**(IBZ_PRODUCTTEAM)

### 数据查询(DEFAULT)<div id="PRODUCTTEAM_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CONSUMED`,
t1.`DAYS`,
t1.`END`,
t1.`ESTIMATE`,
t1.`HOURS`,
t1.`ID`,
t1.`JOIN`,
t1.`LEADINGCADRE`,
t1.`LEFT`,
t1.`LIMITED`,
t1.`ORDER`,
t1.`ROLE`,
t1.`ROOT`,
t1.`TEAMSTATUS`,
(t1.`DAYS` * t1.`HOURS`) AS `TOTAL`,
t1.`TYPE`,
(select t.realname from zt_user t where t.account = t1.account) AS `USERNAME`
FROM `zt_team` t1 

WHERE ( t1.`TYPE` = 'product' ) 

```
### 产品团队成员信息(ProductTeamInfo)<div id="PRODUCTTEAM_ProductTeamInfo"></div>
```sql
SELECT
	t1.account,
	t1.days,
	t1.hours,
	t1.id,
	t1.`join`,
        t1.`end`,
        t1.`LEADINGCADRE`,
	t1.limited,
	t1.`order`,
	t1.role,
	t1.root,
	( t1.days * t1.hours ) AS total,
	t1.type,
	( SELECT t.realname FROM zt_user t WHERE t.account = t1.account ) AS username,
	(
SELECT
	count( t2.id ) 
FROM
	zt_task t2 
WHERE
	t2.deleted = '0' 
	AND t2.project in (t1.zzz) 
	AND t2.parent >= 0 
	AND (
	t2.assignedTo = t1.account 
	OR t2.finishedBy = t1.account 
	OR t2.id IN ( SELECT t.root FROM zt_team t WHERE t.type = 'task' AND t.account = t1.account ) 
	) 
	) AS taskcnt,
	(
SELECT
	ROUND(sum( CASE WHEN tt.LEFT IS NOT NULL THEN tt.LEFT ELSE t2.LEFT END ), 1) 
FROM
	zt_task t2
	LEFT JOIN zt_team tt ON tt.root = t2.id 
	AND tt.type = 'task' 
WHERE
	t2.deleted = '0' 
	AND t2.project  in (t1.zzz) 
	AND t2.parent >= 0 
	AND ( t2.assignedTo = t1.account OR tt.account = t1.account ) 
	) AS `left`,
	(
SELECT
	ROUND(sum( CASE WHEN tt.estimate IS NOT NULL THEN tt.estimate ELSE t2.estimate END ), 1)
FROM
	zt_task t2
	LEFT JOIN zt_team tt ON tt.root = t2.id 
	AND tt.type = 'task' 
WHERE
	t2.deleted = '0' 
	AND t2.project  in (t1.zzz) 
	AND t2.parent >= 0 
	AND ( t2.assignedTo = t1.account OR tt.account = t1.account ) 
	) AS `estimate`,
	(
SELECT
	ROUND(sum( CASE WHEN tt.consumed IS NOT NULL THEN tt.consumed ELSE t2.consumed END ), 1)
FROM
	zt_task t2
	LEFT JOIN zt_team tt ON tt.root = t2.id 
	AND tt.type = 'task' 
WHERE
	t2.deleted = '0' 
	AND t2.project in (t1.zzz) 
	AND t2.parent >= 0 
	AND ( t2.assignedTo = t1.account OR tt.account = t1.account ) 
	) AS consumed 
FROM
	(	SELECT *,(SELECT GROUP_CONCAT(project) from zt_projectproduct where product =  t1.root and t1.type = 'product' ) as zzz from zt_team t1
) t1
WHERE t1.type = 'product' 

```
### 项目立项(ProjectApp)<div id="PRODUCTTEAM_ProjectApp"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CONSUMED`,
t1.`DAYS`,
t1.`END`,
t1.`ESTIMATE`,
t1.`HOURS`,
t1.`ID`,
t1.`JOIN`,
t1.`LEADINGCADRE`,
t1.`LEFT`,
t1.`LIMITED`,
t1.`ORDER`,
t1.`ROLE`,
t1.`ROOT`,
t1.`TEAMSTATUS`,
(t1.`DAYS` * t1.`HOURS`) AS `TOTAL`,
t1.`TYPE`,
(select t.realname from zt_user t where t.account = t1.account) AS `USERNAME`
FROM `zt_team` t1 

WHERE ( t1.`ROOT` = ${srfwebcontext('product','{"defname":"ROOT","dename":"IBZ_PRODUCTTEAM"}')}  AND  t1.`TYPE` = 'product' ) 

```
### 项目产品团队(ProjectProductTeam)<div id="PRODUCTTEAM_ProjectProductTeam"></div>
```sql
SELECT
	t1.* 
FROM
	(
SELECT
	t1.id,
	t1.root,
	t1.type,
	t1.account,
	t1.role,
	t1.limited,
	t1.`join`,
	t1.days,
	t1.hours,
	t1.estimate,
	t1.consumed,
	t1.`left`,
	t1.`order`,
	t1.`end`,
	t1.LEADINGCADRE,
	t1.TEAMSTATUS,
	t1.srfdcid,
	t1.dept,
	t1.org,
	t2.project,
	t2.branch,
	t2.plan 
FROM
	zt_team t1
	LEFT JOIN zt_projectproduct t2 ON t1.root = t2.product 
WHERE
	(t2.project = #{srf.sessioncontext.srfparentkey} and t1.type = 'project')
	OR (t1.root IN ( SELECT t1.product FROM zt_projectproduct t1 WHERE t1.project = #{srf.sessioncontext.srfparentkey} ) AND t1.type = 'product'  ) 
	) t1 

WHERE t1.project = #{srf.sessioncontext.srfparentkey} 

```
### 产品团队管理(RowEditDefaultProductTeam)<div id="PRODUCTTEAM_RowEditDefaultProductTeam"></div>
```sql
SELECT
	t1.* 
FROM
	(
SELECT
	t1.`ACCOUNT`,
	t1.`CONSUMED`,
	t1.`DAYS`,
        t1.`end`,
        t1.`LEADINGCADRE`,
	t1.`ESTIMATE`,
	t1.`HOURS`,
	t1.id as `ID`,
	t1.`JOIN`,
	t1.`LEFT`,
	t1.`LIMITED`,
	t1.`ORDER`,
	t1.`ROLE`,
	t1.`ROOT`,
	( t1.`DAYS` * t1.`HOURS` ) AS `TOTAL`,
	t1.`TYPE`,
	t2.`realname` AS `USERNAME` 
FROM
	`zt_team` t1
	LEFT JOIN `zt_user` t2 ON t2.`account` = t1.`account` 
	union 
	SELECT
	t1.`ACCOUNT`,
	0 AS `CONSUMED`,
	((select SUM(tt.days) from zt_project tt where  FIND_IN_SET(tt.id,(SELECT GROUP_CONCAT(project) FROM zt_projectproduct where product = #{srf.datacontext.root} )
	)) )
	AS `DAYS`,
        null as `end`,
        null as `LEADINGCADRE`,
	0 AS `ESTIMATE`,
	7 AS `HOURS`,
	null as `ID`,
	'2020-07-13' AS `JOIN`,
	0 AS `LEFT`,
	'no' AS `LIMITED`,
	0 AS `ORDER`,
	t3.`name` as `ROLE`,
	#{srf.datacontext.root} 
	as `ROOT`,
	90 AS `TOTAL`,
	'product' AS `TYPE`,
	t2.`realname` AS `USERNAME` 
FROM
	`zt_team` t1
	LEFT JOIN `zt_user` t2 ON t2.`account` = t1.`account` 
        left join zt_group t3 on t2.role = t3.role
	where t1.type = 'product' and t1.root = #{srf.datacontext.teams} 
	and t1.account not in (select  t.account from zt_team t where t.root = #{srf.datacontext.root} 
	and t.type = 'product')
	union 
	SELECT
	t2.`ACCOUNT`,
	0 AS `CONSUMED`,
	((select SUM(tt.days) from zt_project tt where  FIND_IN_SET(tt.id,(SELECT GROUP_CONCAT(project) FROM zt_projectproduct where product = #{srf.datacontext.root} )
	)) )
	AS `DAYS`,
        null as `end`,
        null as `LEADINGCADRE`,
	0 AS `ESTIMATE`,
	7 AS `HOURS`,
	null as `ID`,
	'2020-07-13' AS `JOIN`,
	0 AS `LEFT`,
	'no' AS `LIMITED`,
	0 AS `ORDER`,
	t3.`name` as `ROLE`,
	#{srf.datacontext.root}
	as `ROOT`,
	90 AS `TOTAL`,
	'product' AS `TYPE`,
	t2.`realname` AS `USERNAME` 
FROM
	`zt_dept` t1
	LEFT JOIN `zt_user` t2 ON t2.`dept` = t1.`id` 
	left join zt_group t3 on t2.role = t3.role
	where t1.id = #{srf.datacontext.dept} 
	and t2.account is not null and t2.account not in (select  t.account from zt_team t where t.root = #{srf.datacontext.root} 
	and t.type = 'product')
	) t1
WHERE t1.type = 'product' 

```
### 默认（全部数据）(VIEW)<div id="PRODUCTTEAM_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CONSUMED`,
t1.`DAYS`,
t1.`END`,
t1.`ESTIMATE`,
t1.`HOURS`,
t1.`ID`,
t1.`JOIN`,
t1.`LEADINGCADRE`,
t1.`LEFT`,
t1.`LIMITED`,
t1.`ORDER`,
t1.`ROLE`,
t1.`ROOT`,
t1.`TEAMSTATUS`,
(t1.`DAYS` * t1.`HOURS`) AS `TOTAL`,
t1.`TYPE`,
(select t.realname from zt_user t where t.account = t1.account) AS `USERNAME`
FROM `zt_team` t1 

```

# **实体属性**(PSDEFIELD)

### 数据查询(DEFAULT)<div id="PSDEField_Default"></div>
```sql
SELECT
t1.`ALLOWEMPTY`,
t1.`AUDITINFOFORMAT`,
t1.`BIZTAG`,
t1.`CHECKRECURSION`,
t1.`CODENAME`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CUSTOMEXPORTSCOPE`,
t1.`DBVALUEMODE`,
t1.`DBVALUEMODE2`,
t1.`DEFAULTVALUE`,
t1.`DEFTYPE`,
t1.`DERPSDEFID`,
t1.`DERPSDEFNAME`,
t1.`DUPCHECKMODE`,
t1.`DUPCHECKVALUES`,
t1.`DUPCHKPSDEFID`,
t1.`DUPCHKPSDEFNAME`,
t1.`DVT`,
t1.`DYNAMODELFLAG`,
t1.`ENABLEAUDIT`,
t1.`ENABLECOLPRIV`,
t1.`ENABLEQS`,
t1.`ENABLETEMPDATA`,
t1.`ENABLEUSERINPUT`,
t1.`ENAWRITEBACK`,
t1.`EXPORTSCOPE`,
t1.`EXTENDMODE`,
t1.`FKEY`,
t1.`FORMULAFIELDS`,
t1.`FORMULAFORMAT`,
t1.`IMPORTKEY`,
t1.`IMPORTORDER`,
t1.`IMPORTTAG`,
t1.`INDEXTYPE`,
t1.`LENGTH`,
t1.`LOCKFLAG`,
t1.`LOGICNAME`,
t1.`MAJORFIELD`,
t1.`MEMO`,
t1.`MULTIFORMFIELD`,
t1.`NO2DUPCHKPSDEFID`,
t1.`NO2DUPCHKPSDEFNAME`,
t1.`NO3DUPCHKPSDEFID`,
t1.`NO3DUPCHKPSDEFNAME`,
t1.`NULLVALORDER`,
t1.`ORDERVALUE`,
t1.`PASTERESET`,
t1.`PHYSICALFIELD`,
t1.`PKEY`,
t1.`PRECISION2`,
t1.`PREDEFINETYPE`,
t1.`PSDATATYPEID`,
t1.`PSDATATYPENAME`,
t1.`PSDEFIELDID`,
t1.`PSDEFIELDNAME`,
t1.`PSDEID`,
t1.`PSDENAME`,
t11.`PSSUBSYSSADEID`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`QUERYCOLUMN`,
t1.`QUERYCS`,
t1.`SERVICECODENAME`,
t1.`STATEFIELD`,
t1.`STRINGCASE`,
t1.`STRLENGTH`,
t1.`TABLENAME`,
t1.`TESTDATA`,
t1.`UNICODECHAR`,
t1.`UNIONKEYVALUE`,
t1.`UNIT`,
t1.`UNITWIDTH`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEOVMODE`,
t1.`USERCAT`,
t1.`USERPARAMS`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`,
t1.`VALIDFLAG`,
t1.`VALUEFORMAT`,
t1.`VALUEPSDEFID`,
t1.`VALUEPSDEFNAME`,
t1.`VIEWCOLLEVEL`
FROM `T_PSDEFIELD` t1 
LEFT JOIN `T_PSDATAENTITY` t11 ON t1.`PSDEID` = t11.`PSDATAENTITYID` 

```
### 默认（全部数据）(VIEW)<div id="PSDEField_View"></div>
```sql
SELECT
t1.`ALLOWEMPTY`,
t1.`AUDITINFOFORMAT`,
t1.`BIZTAG`,
t1.`CHECKRECURSION`,
t1.`CODENAME`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CUSTOMEXPORTSCOPE`,
t1.`DBVALUEMODE`,
t1.`DBVALUEMODE2`,
t1.`DEFAULTVALUE`,
t1.`DEFTYPE`,
t1.`DERPSDEFID`,
t1.`DERPSDEFNAME`,
t1.`DUPCHECKMODE`,
t1.`DUPCHECKVALUES`,
t1.`DUPCHKPSDEFID`,
t1.`DUPCHKPSDEFNAME`,
t1.`DVT`,
t1.`DYNAMODELFLAG`,
t1.`ENABLEAUDIT`,
t1.`ENABLECOLPRIV`,
t1.`ENABLEQS`,
t1.`ENABLETEMPDATA`,
t1.`ENABLEUSERINPUT`,
t1.`ENAWRITEBACK`,
t1.`EXPORTSCOPE`,
t1.`EXTENDMODE`,
t1.`FKEY`,
t1.`FORMULAFIELDS`,
t1.`FORMULAFORMAT`,
t1.`IMPORTKEY`,
t1.`IMPORTORDER`,
t1.`IMPORTTAG`,
t1.`INDEXTYPE`,
t1.`LENGTH`,
t1.`LOCKFLAG`,
t1.`LOGICNAME`,
t1.`MAJORFIELD`,
t1.`MEMO`,
t1.`MULTIFORMFIELD`,
t1.`NO2DUPCHKPSDEFID`,
t1.`NO2DUPCHKPSDEFNAME`,
t1.`NO3DUPCHKPSDEFID`,
t1.`NO3DUPCHKPSDEFNAME`,
t1.`NULLVALORDER`,
t1.`ORDERVALUE`,
t1.`PASTERESET`,
t1.`PHYSICALFIELD`,
t1.`PKEY`,
t1.`PRECISION2`,
t1.`PREDEFINETYPE`,
t1.`PSDATATYPEID`,
t1.`PSDATATYPENAME`,
t1.`PSDEFIELDID`,
t1.`PSDEFIELDNAME`,
t1.`PSDEID`,
t1.`PSDENAME`,
t11.`PSSUBSYSSADEID`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`QUERYCOLUMN`,
t1.`QUERYCS`,
t1.`SERVICECODENAME`,
t1.`STATEFIELD`,
t1.`STRINGCASE`,
t1.`STRLENGTH`,
t1.`TABLENAME`,
t1.`TESTDATA`,
t1.`UNICODECHAR`,
t1.`UNIONKEYVALUE`,
t1.`UNIT`,
t1.`UNITWIDTH`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEOVMODE`,
t1.`USERCAT`,
t1.`USERPARAMS`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`,
t1.`VALIDFLAG`,
t1.`VALUEFORMAT`,
t1.`VALUEPSDEFID`,
t1.`VALUEPSDEFNAME`,
t1.`VIEWCOLLEVEL`
FROM `T_PSDEFIELD` t1 
LEFT JOIN `T_PSDATAENTITY` t11 ON t1.`PSDEID` = t11.`PSDATAENTITYID` 

```

# **实体**(PSDATAENTITY)

### 数据查询(DEFAULT)<div id="PSDataEntity_Default"></div>
```sql
SELECT
t1.`ACCCTRLARCH`,
t1.`AUDITMODE`,
t1.`BASECLSPARAMS`,
t1.`BIZTAG`,
t1.`CODENAME`,
t1.`COLOR`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DATAACCMODE`,
t1.`DATACHGLOGMODE`,
t1.`DATAIMPEXPFLAG`,
t1.`DBTABSPACE`,
t1.`DECAT`,
t1.`DELOCKFLAG`,
t1.`DESN`,
t1.`DETAG`,
t1.`DETAG2`,
t1.`DETYPE`,
t1.`DSLINK`,
t1.`DYNAMICMODE`,
t1.`DYNAMODELFLAG`,
t1.`ENABLEAUDIT`,
t1.`ENABLEDALOG`,
t1.`ENABLEDATAVER`,
t1.`ENABLEDEACTION`,
t1.`ENABLEDEDATASET`,
t1.`ENABLEENTITYCACHE`,
t1.`ENABLEMOB`,
t1.`ENABLEMULTIDS`,
t1.`ENABLEOPNAMEMODEL`,
t1.`ENABLEORGMODEL`,
t1.`ENABLESELECT`,
t1.`ENABLEWFMODEL`,
t1.`ENAMULTIFORM`,
t1.`ENATEMPDATA`,
t1.`ENTITYCACHETIMEOUT`,
t1.`EXISTINGMODEL`,
t1.`EXTABLENAME`,
t1.`INDEXDETYPE`,
t1.`KEYRULE`,
t1.`LOCKFLAG`,
t1.`LOGICINVALIDVALUE`,
t1.`LOGICNAME`,
t1.`LOGICVALID`,
t1.`LOGICVALIDVALUE`,
t1.`MAXENTITYCACHECNT`,
t1.`MEMO`,
t21.`COLOR` AS `MODCOLOR`,
t1.`MODELIMPEXPFLAG`,
t1.`MODELSTATE`,
t1.`NOVIEWMODE`,
t1.`PSDATAENTITYID`,
t1.`PSDATAENTITYNAME`,
t1.`PSMODULEID`,
t21.`PSMODULENAME`,
t1.`PSSUBSYSSADEID`,
t31.`PSSUBSYSSADENAME`,
t1.`PSSUBSYSSERVICEAPIID`,
t11.`PSSUBSYSSERVICEAPINAME`,
t1.`PSSYSREQITEMID`,
t41.`PSSYSREQITEMNAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`REMOVEFLAG`,
t1.`SAASMODE`,
t1.`SERVICEAPIFLAG`,
t1.`SERVICECODENAME`,
t1.`STORAGEMODE`,
t1.`SUBSYSDE`,
t21.`SUBSYSMODULE`,
t1.`SVRPUBMODE`,
t1.`SYSTEMFLAG`,
t1.`TABLENAME`,
t1.`TESTCASEFLAG`,
t1.`TODOTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERACTION`,
t1.`USERCAT`,
t1.`USERPARAMS`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`,
t1.`VALIDFLAG`,
t1.`VIEWLEVEL`,
t1.`VIEWNAME`,
t1.`VIEWNAME2`,
t1.`VIEWNAME3`,
t1.`VIEWNAME4`,
t1.`VIRTUALFLAG`,
t1.`VKEYSEPARATOR`
FROM `T_PSDATAENTITY` t1 
LEFT JOIN `T_PSSUBSYSSERVICEAPI` t11 ON t1.`PSSUBSYSSERVICEAPIID` = t11.`PSSUBSYSSERVICEAPIID` 
LEFT JOIN `T_PSMODULE` t21 ON t1.`PSMODULEID` = t21.`PSMODULEID` 
LEFT JOIN `T_PSSUBSYSSADE` t31 ON t1.`PSSUBSYSSADEID` = t31.`PSSUBSYSSADEID` 
LEFT JOIN `T_PSSYSREQITEM` t41 ON t1.`PSSYSREQITEMID` = t41.`PSSYSREQITEMID` 

```
### 默认（全部数据）(VIEW)<div id="PSDataEntity_View"></div>
```sql
SELECT
t1.`ACCCTRLARCH`,
t1.`AUDITMODE`,
t1.`BASECLSPARAMS`,
t1.`BIZTAG`,
t1.`CODENAME`,
t1.`COLOR`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DATAACCMODE`,
t1.`DATACHGLOGMODE`,
t1.`DATAIMPEXPFLAG`,
t1.`DBTABSPACE`,
t1.`DECAT`,
t1.`DELOCKFLAG`,
t1.`DESN`,
t1.`DETAG`,
t1.`DETAG2`,
t1.`DETYPE`,
t1.`DSLINK`,
t1.`DYNAMICMODE`,
t1.`DYNAMODELFLAG`,
t1.`ENABLEAUDIT`,
t1.`ENABLEDALOG`,
t1.`ENABLEDATAVER`,
t1.`ENABLEDEACTION`,
t1.`ENABLEDEDATASET`,
t1.`ENABLEENTITYCACHE`,
t1.`ENABLEMOB`,
t1.`ENABLEMULTIDS`,
t1.`ENABLEOPNAMEMODEL`,
t1.`ENABLEORGMODEL`,
t1.`ENABLESELECT`,
t1.`ENABLEWFMODEL`,
t1.`ENAMULTIFORM`,
t1.`ENATEMPDATA`,
t1.`ENTITYCACHETIMEOUT`,
t1.`EXISTINGMODEL`,
t1.`EXTABLENAME`,
t1.`INDEXDETYPE`,
t1.`KEYRULE`,
t1.`LOCKFLAG`,
t1.`LOGICINVALIDVALUE`,
t1.`LOGICNAME`,
t1.`LOGICVALID`,
t1.`LOGICVALIDVALUE`,
t1.`MAXENTITYCACHECNT`,
t1.`MEMO`,
t21.`COLOR` AS `MODCOLOR`,
t1.`MODELIMPEXPFLAG`,
t1.`MODELSTATE`,
t1.`NOVIEWMODE`,
t1.`PSDATAENTITYID`,
t1.`PSDATAENTITYNAME`,
t1.`PSMODULEID`,
t21.`PSMODULENAME`,
t1.`PSSUBSYSSADEID`,
t31.`PSSUBSYSSADENAME`,
t1.`PSSUBSYSSERVICEAPIID`,
t11.`PSSUBSYSSERVICEAPINAME`,
t1.`PSSYSREQITEMID`,
t41.`PSSYSREQITEMNAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`REMOVEFLAG`,
t1.`SAASMODE`,
t1.`SERVICEAPIFLAG`,
t1.`SERVICECODENAME`,
t1.`STORAGEMODE`,
t1.`SUBSYSDE`,
t21.`SUBSYSMODULE`,
t1.`SVRPUBMODE`,
t1.`SYSTEMFLAG`,
t1.`TABLENAME`,
t1.`TESTCASEFLAG`,
t1.`TODOTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERACTION`,
t1.`USERCAT`,
t1.`USERPARAMS`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`,
t1.`VALIDFLAG`,
t1.`VIEWLEVEL`,
t1.`VIEWNAME`,
t1.`VIEWNAME2`,
t1.`VIEWNAME3`,
t1.`VIEWNAME4`,
t1.`VIRTUALFLAG`,
t1.`VKEYSEPARATOR`
FROM `T_PSDATAENTITY` t1 
LEFT JOIN `T_PSSUBSYSSERVICEAPI` t11 ON t1.`PSSUBSYSSERVICEAPIID` = t11.`PSSUBSYSSERVICEAPIID` 
LEFT JOIN `T_PSMODULE` t21 ON t1.`PSMODULEID` = t21.`PSMODULEID` 
LEFT JOIN `T_PSSUBSYSSADE` t31 ON t1.`PSSUBSYSSADEID` = t31.`PSSUBSYSSADEID` 
LEFT JOIN `T_PSSYSREQITEM` t41 ON t1.`PSSYSREQITEMID` = t41.`PSSYSREQITEMID` 

```

# **系统模块**(PSMODULE)

### 数据查询(DEFAULT)<div id="PSModule_Default"></div>
```sql
SELECT
t1.`CLSPKGPARAMS`,
t1.`CODENAME`,
t1.`COLOR`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DEFAULTFLAG`,
t1.`LOCKFLAG`,
t1.`MEMO`,
t1.`MODTAG`,
t1.`MODTAG2`,
t1.`MODTAG3`,
t1.`MODTAG4`,
t1.`MODULESN`,
t1.`NOVIEWMODE`,
t1.`ORDERVALUE`,
t1.`PKGCODENAME`,
t1.`PSMODULEID`,
t1.`PSMODULENAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`SERVICEAPIFLAG`,
t1.`SUBSYSMODULE`,
t1.`SYSREFTYPE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCAT`,
t1.`USERPARAMS`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`
FROM `T_PSMODULE` t1 

```
### 默认（全部数据）(VIEW)<div id="PSModule_View"></div>
```sql
SELECT
t1.`CLSPKGPARAMS`,
t1.`CODENAME`,
t1.`COLOR`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DEFAULTFLAG`,
t1.`LOCKFLAG`,
t1.`MEMO`,
t1.`MODTAG`,
t1.`MODTAG2`,
t1.`MODTAG3`,
t1.`MODTAG4`,
t1.`MODULESN`,
t1.`NOVIEWMODE`,
t1.`ORDERVALUE`,
t1.`PKGCODENAME`,
t1.`PSMODULEID`,
t1.`PSMODULENAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`SERVICEAPIFLAG`,
t1.`SUBSYSMODULE`,
t1.`SYSREFTYPE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCAT`,
t1.`USERPARAMS`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`
FROM `T_PSMODULE` t1 

```

# **外部接口实体**(PSSUBSYSSADE)

### 数据查询(DEFAULT)<div id="PSSubSysSADE_Default"></div>
```sql
SELECT
t1.`CODENAME`,
t1.`CODENAME2`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DETAG`,
t1.`DETAG2`,
t1.`LOGICNAME`,
t1.`MAJORFLAG`,
t1.`MEMO`,
t1.`PSSUBSYSSADEID`,
t1.`PSSUBSYSSADENAME`,
t1.`PSSUBSYSSERVICEAPIID`,
t11.`PSSUBSYSSERVICEAPINAME`,
t1.`SYNCMODELMODE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCAT`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`,
t1.`VALIDFLAG`
FROM `T_PSSUBSYSSADE` t1 
LEFT JOIN `T_PSSUBSYSSERVICEAPI` t11 ON t1.`PSSUBSYSSERVICEAPIID` = t11.`PSSUBSYSSERVICEAPIID` 

```
### 默认（全部数据）(VIEW)<div id="PSSubSysSADE_View"></div>
```sql
SELECT
t1.`CODENAME`,
t1.`CODENAME2`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DETAG`,
t1.`DETAG2`,
t1.`LOGICNAME`,
t1.`MAJORFLAG`,
t1.`MEMO`,
t1.`PSSUBSYSSADEID`,
t1.`PSSUBSYSSADENAME`,
t1.`PSSUBSYSSERVICEAPIID`,
t11.`PSSUBSYSSERVICEAPINAME`,
t1.`SYNCMODELMODE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCAT`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`,
t1.`VALIDFLAG`
FROM `T_PSSUBSYSSADE` t1 
LEFT JOIN `T_PSSUBSYSSERVICEAPI` t11 ON t1.`PSSUBSYSSERVICEAPIID` = t11.`PSSUBSYSSERVICEAPIID` 

```

# **外部服务接口**(PSSUBSYSSERVICEAPI)

### 数据查询(DEFAULT)<div id="PSSubSysServiceAPI_Default"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`PSMODULEID`,
t21.`PSMODULENAME`,
t1.`PSSUBSYSSERVICEAPIID`,
t1.`PSSUBSYSSERVICEAPINAME`,
t1.`PSSYSSERVICEAPIID`,
t11.`PSSYSSERVICEAPINAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_PSSUBSYSSERVICEAPI` t1 
LEFT JOIN `T_PSSYSSERVICEAPI` t11 ON t1.`PSSYSSERVICEAPIID` = t11.`PSSYSSERVICEAPIID` 
LEFT JOIN `T_PSMODULE` t21 ON t1.`PSMODULEID` = t21.`PSMODULEID` 

```
### 默认（全部数据）(VIEW)<div id="PSSubSysServiceAPI_View"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`PSMODULEID`,
t21.`PSMODULENAME`,
t1.`PSSUBSYSSERVICEAPIID`,
t1.`PSSUBSYSSERVICEAPINAME`,
t1.`PSSYSSERVICEAPIID`,
t11.`PSSYSSERVICEAPINAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_PSSUBSYSSERVICEAPI` t1 
LEFT JOIN `T_PSSYSSERVICEAPI` t11 ON t1.`PSSYSSERVICEAPIID` = t11.`PSSYSSERVICEAPIID` 
LEFT JOIN `T_PSMODULE` t21 ON t1.`PSMODULEID` = t21.`PSMODULEID` 

```

# **系统应用**(PSSYSAPP)

### 版本(Build)<div id="PSSysApp_Build"></div>
```sql
SELECT
t1.`APPFOLDER`,
t1.`APPMODE`,
t1.`APPPKGNAME`,
t1.`APPSN`,
t1.`APPTAG`,
t1.`APPTAG2`,
t1.`APPTAG3`,
t1.`APPTAG4`,
t1.`AUTOADDAPPVIEW`,
t1.`BTNNOPRIVDM`,
t1.`CODEFOLDER`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DEFAULTPUB`,
t1.`ENABLEC12TOC24`,
t1.`ENABLEDYNASYS`,
t1.`ENABLESTORYBOARD`,
t1.`ENALOCALSERVICE`,
t1.`FINOPRIVDM`,
t1.`FIUPDATEPRIVTAG`,
t1.`GCNOPRIVDM`,
t1.`GRIDCOLENABLELINK`,
t1.`GRIDFORCEFIT`,
t1.`GRIDROWACTIVEMODE`,
t1.`ICONFILE`,
t1.`LOGICNAME`,
t1.`MAINMENUSIDE`,
t1.`MEMO`,
t1.`ORIENTATIONMODE`,
t1.`PREVENTXSS`,
t1.`PSAPPTYPEID`,
t1.`PSAPPTYPENAME`,
t1.`PSPFCDNID`,
t1.`PSPFCDNNAME`,
t1.`PSPFID`,
t1.`PSPFSTYLEID`,
t1.`PSSTUDIOTHEMEID`,
t1.`PSSTUDIOTHEMENAME`,
t1.`PSSYSAPPID`,
t1.`PSSYSAPPNAME`,
t1.`PSSYSSERVICEAPIID`,
t11.`PSSYSSERVICEAPINAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`PUBREFVIEWONLY`,
t1.`PUBSYSREFVIEWONLY`,
t1.`REMOVEFLAG`,
t1.`SERVICECODENAME`,
t1.`STARTPAGEFILE`,
t1.`UACLOGIN`,
t1.`UISTYLE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCAT`,
t1.`USERPARAMS`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`,
t1.`VALIDFLAG`
FROM `T_PSSYSAPP` t1 
LEFT JOIN `T_PSSYSSERVICEAPI` t11 ON t1.`PSSYSSERVICEAPIID` = t11.`PSSYSSERVICEAPIID` 

```
### 数据查询(DEFAULT)<div id="PSSysApp_Default"></div>
```sql
SELECT
t1.`APPFOLDER`,
t1.`APPMODE`,
t1.`APPPKGNAME`,
t1.`APPSN`,
t1.`APPTAG`,
t1.`APPTAG2`,
t1.`APPTAG3`,
t1.`APPTAG4`,
t1.`AUTOADDAPPVIEW`,
t1.`BTNNOPRIVDM`,
t1.`CODEFOLDER`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DEFAULTPUB`,
t1.`ENABLEC12TOC24`,
t1.`ENABLEDYNASYS`,
t1.`ENABLESTORYBOARD`,
t1.`ENALOCALSERVICE`,
t1.`FINOPRIVDM`,
t1.`FIUPDATEPRIVTAG`,
t1.`GCNOPRIVDM`,
t1.`GRIDCOLENABLELINK`,
t1.`GRIDFORCEFIT`,
t1.`GRIDROWACTIVEMODE`,
t1.`ICONFILE`,
t1.`LOGICNAME`,
t1.`MAINMENUSIDE`,
t1.`MEMO`,
t1.`ORIENTATIONMODE`,
t1.`PREVENTXSS`,
t1.`PSAPPTYPEID`,
t1.`PSAPPTYPENAME`,
t1.`PSPFCDNID`,
t1.`PSPFCDNNAME`,
t1.`PSPFID`,
t1.`PSPFSTYLEID`,
t1.`PSSTUDIOTHEMEID`,
t1.`PSSTUDIOTHEMENAME`,
t1.`PSSYSAPPID`,
t1.`PSSYSAPPNAME`,
t1.`PSSYSSERVICEAPIID`,
t11.`PSSYSSERVICEAPINAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`PUBREFVIEWONLY`,
t1.`PUBSYSREFVIEWONLY`,
t1.`REMOVEFLAG`,
t1.`SERVICECODENAME`,
t1.`STARTPAGEFILE`,
t1.`UACLOGIN`,
t1.`UISTYLE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCAT`,
t1.`USERPARAMS`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`,
t1.`VALIDFLAG`
FROM `T_PSSYSAPP` t1 
LEFT JOIN `T_PSSYSSERVICEAPI` t11 ON t1.`PSSYSSERVICEAPIID` = t11.`PSSYSSERVICEAPIID` 

```
### 默认（全部数据）(VIEW)<div id="PSSysApp_View"></div>
```sql
SELECT
t1.`APPFOLDER`,
t1.`APPMODE`,
t1.`APPPKGNAME`,
t1.`APPSN`,
t1.`APPTAG`,
t1.`APPTAG2`,
t1.`APPTAG3`,
t1.`APPTAG4`,
t1.`AUTOADDAPPVIEW`,
t1.`BTNNOPRIVDM`,
t1.`CODEFOLDER`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DEFAULTPUB`,
t1.`ENABLEC12TOC24`,
t1.`ENABLEDYNASYS`,
t1.`ENABLESTORYBOARD`,
t1.`ENALOCALSERVICE`,
t1.`FINOPRIVDM`,
t1.`FIUPDATEPRIVTAG`,
t1.`GCNOPRIVDM`,
t1.`GRIDCOLENABLELINK`,
t1.`GRIDFORCEFIT`,
t1.`GRIDROWACTIVEMODE`,
t1.`ICONFILE`,
t1.`LOGICNAME`,
t1.`MAINMENUSIDE`,
t1.`MEMO`,
t1.`ORIENTATIONMODE`,
t1.`PFSTYLEPARAM`,
t1.`PREVENTXSS`,
t1.`PSAPPTYPEID`,
t1.`PSAPPTYPENAME`,
t1.`PSPFCDNID`,
t1.`PSPFCDNNAME`,
t1.`PSPFID`,
t1.`PSPFSTYLEID`,
t1.`PSSTUDIOTHEMEID`,
t1.`PSSTUDIOTHEMENAME`,
t1.`PSSYSAPPID`,
t1.`PSSYSAPPNAME`,
t1.`PSSYSSERVICEAPIID`,
t11.`PSSYSSERVICEAPINAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`PUBREFVIEWONLY`,
t1.`PUBSYSREFVIEWONLY`,
t1.`REMOVEFLAG`,
t1.`SERVICECODENAME`,
t1.`STARTPAGEFILE`,
t1.`UACLOGIN`,
t1.`UISTYLE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCAT`,
t1.`USERPARAMS`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`,
t1.`VALIDFLAG`
FROM `T_PSSYSAPP` t1 
LEFT JOIN `T_PSSYSSERVICEAPI` t11 ON t1.`PSSYSSERVICEAPIID` = t11.`PSSYSSERVICEAPIID` 

```

# **系统需求项**(PSSYSREQITEM)

### 数据查询(DEFAULT)<div id="PSSysReqItem_Default"></div>
```sql
SELECT
t1.`CODENAME`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`ITEMSN`,
t1.`ITEMTAG`,
t1.`ITEMTAG2`,
t1.`ITEMTYPE`,
t1.`MEMO`,
t1.`ORDERVALUE`,
t1.`PPSSYSREQITEMID`,
t21.`PSSYSREQITEMNAME` AS `PPSSYSREQITEMNAME`,
t1.`PSSYSREQITEMID`,
t1.`PSSYSREQITEMNAME`,
t1.`PSSYSREQMODULEID`,
t11.`PSSYSREQMODULENAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`PSSYSUSERCASEID`,
t1.`PSSYSUSERCASENAME`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCAT`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`,
t1.`VALIDFLAG`,
t1.`VER`
FROM `T_PSSYSREQITEM` t1 
LEFT JOIN `T_PSSYSREQMODULE` t11 ON t1.`PSSYSREQMODULEID` = t11.`PSSYSREQMODULEID` 
LEFT JOIN `T_PSSYSREQITEM` t21 ON t1.`PPSSYSREQITEMID` = t21.`PSSYSREQITEMID` 

```
### 默认（全部数据）(VIEW)<div id="PSSysReqItem_View"></div>
```sql
SELECT
t1.`CODENAME`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`ITEMSN`,
t1.`ITEMTAG`,
t1.`ITEMTAG2`,
t1.`ITEMTYPE`,
t1.`MEMO`,
t1.`ORDERVALUE`,
t1.`PPSSYSREQITEMID`,
t21.`PSSYSREQITEMNAME` AS `PPSSYSREQITEMNAME`,
t1.`PSSYSREQITEMID`,
t1.`PSSYSREQITEMNAME`,
t1.`PSSYSREQMODULEID`,
t11.`PSSYSREQMODULENAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`PSSYSUSERCASEID`,
t1.`PSSYSUSERCASENAME`,
t1.`REQCONTENT`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCAT`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`,
t1.`VALIDFLAG`,
t1.`VER`
FROM `T_PSSYSREQITEM` t1 
LEFT JOIN `T_PSSYSREQMODULE` t11 ON t1.`PSSYSREQMODULEID` = t11.`PSSYSREQMODULEID` 
LEFT JOIN `T_PSSYSREQITEM` t21 ON t1.`PPSSYSREQITEMID` = t21.`PSSYSREQITEMID` 

```

# **系统需求模块**(PSSYSREQMODULE)

### 数据查询(DEFAULT)<div id="PSSysReqModule_Default"></div>
```sql
SELECT
t1.`CODENAME`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`MEMO`,
t1.`MODULESN`,
t1.`MODULETAG`,
t1.`MODULETAG2`,
t1.`MODULETAG3`,
t1.`MODULETAG4`,
t1.`ORDERVALUE`,
t1.`PPSSYSREQMODULEID`,
t21.`PSSYSREQMODULENAME` AS `PPSSYSREQMODULENAME`,
t1.`PSMODULEID`,
t11.`PSMODULENAME`,
t1.`PSSYSREQMODULEID`,
t1.`PSSYSREQMODULENAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCAT`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`
FROM `T_PSSYSREQMODULE` t1 
LEFT JOIN `T_PSMODULE` t11 ON t1.`PSMODULEID` = t11.`PSMODULEID` 
LEFT JOIN `T_PSSYSREQMODULE` t21 ON t1.`PPSSYSREQMODULEID` = t21.`PSSYSREQMODULEID` 

```
### 默认（全部数据）(VIEW)<div id="PSSysReqModule_View"></div>
```sql
SELECT
t1.`CODENAME`,
t1.`CONTENT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`MEMO`,
t1.`MODULESN`,
t1.`MODULETAG`,
t1.`MODULETAG2`,
t1.`MODULETAG3`,
t1.`MODULETAG4`,
t1.`ORDERVALUE`,
t1.`PPSSYSREQMODULEID`,
t21.`PSSYSREQMODULENAME` AS `PPSSYSREQMODULENAME`,
t1.`PSMODULEID`,
t11.`PSMODULENAME`,
t1.`PSSYSREQMODULEID`,
t1.`PSSYSREQMODULENAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCAT`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`
FROM `T_PSSYSREQMODULE` t1 
LEFT JOIN `T_PSMODULE` t11 ON t1.`PSMODULEID` = t11.`PSMODULEID` 
LEFT JOIN `T_PSSYSREQMODULE` t21 ON t1.`PPSSYSREQMODULEID` = t21.`PSSYSREQMODULEID` 

```

# **系统运行会话**(PSSYSRUNSESSION)

### 数据查询(DEFAULT)<div id="PSSysRunSession_Default"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DEBUGMODE`,
t1.`ENABLEVC`,
t1.`ENDTIME`,
t1.`FIXDBMODEL`,
t1.`MEMO`,
t1.`PACKMODE`,
t1.`PSDSCONSOLEID`,
t1.`PSSYSAPPID`,
t1.`PSSYSAPPID2`,
t1.`PSSYSAPPNAME`,
t1.`PSSYSAPPNAME2`,
t1.`PSSYSRUNSESSIONID`,
t1.`PSSYSRUNSESSIONNAME`,
t1.`PSSYSSERVICEAPIID`,
t1.`PSSYSSERVICEAPINAME`,
t1.`PSSYSSFPUBID`,
t1.`PSSYSSFPUBNAME`,
t1.`PSSYSTEMDBCFGID`,
t1.`PSSYSTEMDBCFGNAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`REBUILDMODE`,
t1.`RUNMODE`,
t1.`RUNPARAM`,
t1.`RUNPARAM2`,
t1.`RUNPARAM3`,
t1.`RUNPARAM4`,
t1.`RUNPARAM5`,
t1.`RUNPARAM6`,
t1.`RUNSTATE`,
t1.`STARTTIME`,
t1.`STOPWHENTEMPLERROR`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_PSSYSRUNSESSION` t1 

```
### 默认（全部数据）(VIEW)<div id="PSSysRunSession_View"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DEBUGMODE`,
t1.`ENABLEVC`,
t1.`ENDTIME`,
t1.`FIXDBMODEL`,
t1.`MEMO`,
t1.`PACKMODE`,
t1.`PSDSCONSOLEID`,
t1.`PSSYSAPPID`,
t1.`PSSYSAPPID2`,
t1.`PSSYSAPPNAME`,
t1.`PSSYSAPPNAME2`,
t1.`PSSYSRUNSESSIONID`,
t1.`PSSYSRUNSESSIONNAME`,
t1.`PSSYSSERVICEAPIID`,
t1.`PSSYSSERVICEAPINAME`,
t1.`PSSYSSFPUBID`,
t1.`PSSYSSFPUBNAME`,
t1.`PSSYSTEMDBCFGID`,
t1.`PSSYSTEMDBCFGNAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`REBUILDMODE`,
t1.`RUNMODE`,
t1.`RUNPARAM`,
t1.`RUNPARAM2`,
t1.`RUNPARAM3`,
t1.`RUNPARAM4`,
t1.`RUNPARAM5`,
t1.`RUNPARAM6`,
t1.`RUNSTATE`,
t1.`STARTTIME`,
t1.`STOPWHENTEMPLERROR`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_PSSYSRUNSESSION` t1 

```

# **后台服务架构**(PSSYSSFPUB)

### 版本(Build)<div id="PSSysSFPub_Build"></div>
```sql
SELECT
t1.`BASECLSPKGCODENAME`,
t1.`CODENAME`,
t1.`CONTENTTYPE`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DEFAULTPUB`,
t1.`DOCPSSFSTYLEID`,
t1.`DOCPSSFSTYLENAME`,
t1.`MEMO`,
t1.`PKGCODENAME`,
t1.`PPSSYSSFPUBID`,
t11.`PSSYSSFPUBNAME` AS `PPSSYSSFPUBNAME`,
t1.`PSSFSTYLEID`,
t1.`PSSFSTYLEPARAMID`,
t1.`PSSFSTYLEPARAMNAME`,
t1.`PSSFSTYLEVERID`,
t1.`PSSYSSFPUBID`,
t1.`PSSYSSFPUBNAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`PUBFOLDER`,
t1.`PUBTAG`,
t1.`PUBTAG2`,
t1.`PUBTAG3`,
t1.`PUBTAG4`,
t1.`REMOVEFLAG`,
t1.`STYLEPARAMS`,
t1.`SUBSYSPKGFLAG`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCAT`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`,
t1.`VERSTR`
FROM `T_PSSYSSFPUB` t1 
LEFT JOIN `T_PSSYSSFPUB` t11 ON t1.`PPSSYSSFPUBID` = t11.`PSSYSSFPUBID` 

```
### 数据查询(DEFAULT)<div id="PSSysSFPub_Default"></div>
```sql
SELECT
t1.`BASECLSPKGCODENAME`,
t1.`CODENAME`,
t1.`CONTENTTYPE`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DEFAULTPUB`,
t1.`DOCPSSFSTYLEID`,
t1.`DOCPSSFSTYLENAME`,
t1.`MEMO`,
t1.`PKGCODENAME`,
t1.`PPSSYSSFPUBID`,
t11.`PSSYSSFPUBNAME` AS `PPSSYSSFPUBNAME`,
t1.`PSSFSTYLEID`,
t1.`PSSFSTYLEPARAMID`,
t1.`PSSFSTYLEPARAMNAME`,
t1.`PSSFSTYLEVERID`,
t1.`PSSYSSFPUBID`,
t1.`PSSYSSFPUBNAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`PUBFOLDER`,
t1.`PUBTAG`,
t1.`PUBTAG2`,
t1.`PUBTAG3`,
t1.`PUBTAG4`,
t1.`REMOVEFLAG`,
t1.`STYLEPARAMS`,
t1.`SUBSYSPKGFLAG`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCAT`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`,
t1.`VERSTR`
FROM `T_PSSYSSFPUB` t1 
LEFT JOIN `T_PSSYSSFPUB` t11 ON t1.`PPSSYSSFPUBID` = t11.`PSSYSSFPUBID` 

```
### 默认（全部数据）(VIEW)<div id="PSSysSFPub_View"></div>
```sql
SELECT
t1.`BASECLSPKGCODENAME`,
t1.`CODENAME`,
t1.`CONTENTTYPE`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DEFAULTPUB`,
t1.`DOCPSSFSTYLEID`,
t1.`DOCPSSFSTYLENAME`,
t1.`MEMO`,
t1.`PKGCODENAME`,
t1.`PPSSYSSFPUBID`,
t11.`PSSYSSFPUBNAME` AS `PPSSYSSFPUBNAME`,
t1.`PSSFSTYLEID`,
t1.`PSSFSTYLEPARAMID`,
t1.`PSSFSTYLEPARAMNAME`,
t1.`PSSFSTYLEVERID`,
t1.`PSSYSSFPUBID`,
t1.`PSSYSSFPUBNAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`PUBFOLDER`,
t1.`PUBTAG`,
t1.`PUBTAG2`,
t1.`PUBTAG3`,
t1.`PUBTAG4`,
t1.`REMOVEFLAG`,
t1.`STYLEPARAMS`,
t1.`SUBSYSPKGFLAG`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCAT`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`,
t1.`VERSTR`
FROM `T_PSSYSSFPUB` t1 
LEFT JOIN `T_PSSYSSFPUB` t11 ON t1.`PPSSYSSFPUBID` = t11.`PSSYSSFPUBID` 

```

# **系统服务接口**(PSSYSSERVICEAPI)

### 数据查询(DEFAULT)<div id="PSSysServiceAPI_Default"></div>
```sql
SELECT
t1.`APIMODE`,
t1.`APITAG`,
t1.`APITAG2`,
t1.`APITYPE`,
t1.`AUTHCHECKTOKENURI`,
t1.`AUTHCLIENTID`,
t1.`AUTHCLIENTSECRET`,
t1.`AUTHMODE`,
t1.`AUTHPARAM`,
t1.`AUTHPARAM2`,
t1.`AUTHPARAM3`,
t1.`AUTHPARAM4`,
t1.`CFGPSMODELSTORAGEID`,
t1.`CFGTAG`,
t1.`CODENAME`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DEFDEACTIONREQMETHOD`,
t1.`DEFDEDATASETREQMETHOD`,
t1.`DEFSELECTREQMETHOD`,
t1.`LOCKFLAG`,
t1.`MEMO`,
t1.`PREDEFINEDTYPE`,
t1.`PSMODULEID`,
t11.`PSMODULENAME`,
t1.`PSSYSSERVICEAPIID`,
t1.`PSSYSSERVICEAPINAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`SERVICECODENAME`,
t1.`SERVICEPARAM`,
t1.`SERVICEPARAM2`,
t1.`SERVICEPARAM3`,
t1.`SERVICEPARAM4`,
t1.`SERVICETYPE`,
t1.`UNIQUETAG`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCAT`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`,
t1.`VALIDFLAG`,
t1.`VER`
FROM `T_PSSYSSERVICEAPI` t1 
LEFT JOIN `T_PSMODULE` t11 ON t1.`PSMODULEID` = t11.`PSMODULEID` 

```
### 默认（全部数据）(VIEW)<div id="PSSysServiceAPI_View"></div>
```sql
SELECT
t1.`APIMODE`,
t1.`APITAG`,
t1.`APITAG2`,
t1.`APITYPE`,
t1.`AUTHCHECKTOKENURI`,
t1.`AUTHCLIENTID`,
t1.`AUTHCLIENTSECRET`,
t1.`AUTHMODE`,
t1.`AUTHPARAM`,
t1.`AUTHPARAM2`,
t1.`AUTHPARAM3`,
t1.`AUTHPARAM4`,
t1.`CFGPSMODELSTORAGEID`,
t1.`CFGTAG`,
t1.`CODENAME`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DEFDEACTIONREQMETHOD`,
t1.`DEFDEDATASETREQMETHOD`,
t1.`DEFSELECTREQMETHOD`,
t1.`LOCKFLAG`,
t1.`MEMO`,
t1.`PREDEFINEDTYPE`,
t1.`PSMODULEID`,
t11.`PSMODULENAME`,
t1.`PSSYSSERVICEAPIID`,
t1.`PSSYSSERVICEAPINAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`SERVICECODENAME`,
t1.`SERVICEPARAM`,
t1.`SERVICEPARAM2`,
t1.`SERVICEPARAM3`,
t1.`SERVICEPARAM4`,
t1.`SERVICETYPE`,
t1.`UNIQUETAG`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCAT`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`,
t1.`VALIDFLAG`,
t1.`VER`
FROM `T_PSSYSSERVICEAPI` t1 
LEFT JOIN `T_PSMODULE` t11 ON t1.`PSMODULEID` = t11.`PSMODULEID` 

```

# **系统数据库**(PSSYSTEMDBCFG)

### 版本(Build)<div id="PSSystemDBCfg_Build"></div>
```sql
SELECT
t1.`APPENDSCHEMA`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DBSCHEMANAME`,
t1.`DEFAULTFLAG`,
t1.`ENABLEWEBTOOL`,
t1.`MEMO`,
t1.`NODBINSTMODE`,
t1.`NULLVALORDER`,
t1.`OBJNAMECASE`,
t1.`PSSYSTEMDBCFGID`,
t1.`PSSYSTEMDBCFGNAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`PUBCOMMENTFLAG`,
t1.`PUBDBMODELFLAG`,
t1.`PUBFKEYFLAG`,
t1.`PUBINDEXFLAG`,
t1.`PUBVIEWFLAG`,
t1.`RESINFO`,
t1.`RESREADYTIME`,
t1.`RESSTATE`,
t1.`TABSPACE`,
t1.`TABSPACE2`,
t1.`TABSPACE3`,
t1.`TABSPACE4`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCAT`,
t1.`USERPARAMS`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`
FROM `T_PSSYSTEMDBCFG` t1 

```
### 数据查询(DEFAULT)<div id="PSSystemDBCfg_Default"></div>
```sql
SELECT
t1.`APPENDSCHEMA`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DBSCHEMANAME`,
t1.`DEFAULTFLAG`,
t1.`ENABLEWEBTOOL`,
t1.`MEMO`,
t1.`NODBINSTMODE`,
t1.`NULLVALORDER`,
t1.`OBJNAMECASE`,
t1.`PSSYSTEMDBCFGID`,
t1.`PSSYSTEMDBCFGNAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`PUBCOMMENTFLAG`,
t1.`PUBDBMODELFLAG`,
t1.`PUBFKEYFLAG`,
t1.`PUBINDEXFLAG`,
t1.`PUBVIEWFLAG`,
t1.`RESINFO`,
t1.`RESREADYTIME`,
t1.`RESSTATE`,
t1.`TABSPACE`,
t1.`TABSPACE2`,
t1.`TABSPACE3`,
t1.`TABSPACE4`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCAT`,
t1.`USERPARAMS`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`
FROM `T_PSSYSTEMDBCFG` t1 

```
### 默认（全部数据）(VIEW)<div id="PSSystemDBCfg_View"></div>
```sql
SELECT
t1.`APPENDSCHEMA`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DBSCHEMANAME`,
t1.`DEFAULTFLAG`,
t1.`ENABLEWEBTOOL`,
t1.`MEMO`,
t1.`NODBINSTMODE`,
t1.`NULLVALORDER`,
t1.`OBJNAMECASE`,
t1.`PSSYSTEMDBCFGID`,
t1.`PSSYSTEMDBCFGNAME`,
t1.`PSSYSTEMID`,
t1.`PSSYSTEMNAME`,
t1.`PUBCOMMENTFLAG`,
t1.`PUBDBMODELFLAG`,
t1.`PUBFKEYFLAG`,
t1.`PUBINDEXFLAG`,
t1.`PUBVIEWFLAG`,
t1.`RESINFO`,
t1.`RESREADYTIME`,
t1.`RESSTATE`,
t1.`TABSPACE`,
t1.`TABSPACE2`,
t1.`TABSPACE3`,
t1.`TABSPACE4`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCAT`,
t1.`USERPARAMS`,
t1.`USERTAG`,
t1.`USERTAG2`,
t1.`USERTAG3`,
t1.`USERTAG4`
FROM `T_PSSYSTEMDBCFG` t1 

```

# **产品**(ZT_PRODUCT)

### 全部产品(AllList)<div id="Product_AllList"></div>
```sql
SELECT
t1.`ACL`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` = 'active' AND DELETED = '0') AS `ACTIVEBUGCNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='active' AND DELETED = '0') AS `ACTIVESTORYCNT`,
(select count(1) from zt_build t where t.product = t1.id and t.deleted = '0') AS `BUILDCNT`,
(select count(1) from zt_case t where t.product = t1.id and t.deleted = '0') AS `CASECNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='changed' AND DELETED = '0') AS `CHANGEDSTORYCNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='closed' AND DELETED = '0') AS `CLOSEDSTORYCNT`,
t1.`CODE`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`CREATEDVERSION`,
t1.`DELETED`,
(select count(1) from zt_doc t where t.product = t1.id and t.deleted = '0') AS `DOCCNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='draft' AND DELETED = '0') AS `DRAFTSTORYCNT`,
t1.`IBIZ_ID`,
t1.`ID`,
'0' AS `ISTOP`,
t1.`LINE`,
t11.`NAME` AS `LINENAME`,
t1.`MDEPTID`,
t1.`NAME`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` <> 'closed' AND DELETED = '0') AS `NOTCLOSEDBUGCNT`,
t1.`ORDER`,
t1.`order` AS `ORDER1`,
t1.`ORGID`,
t1.`PO`,
t1.PO AS `POPK`,
t1.`PRODUCTCLASS`,
(SELECT COUNT(1) FROM ZT_PRODUCTPLAN WHERE PRODUCT= t1.`ID` AND DELETED = '0') AS `PRODUCTPLANCNT`,
t1.`QD`,
t1.QD AS `QDPK`,
t1.`RD`,
t1.RD AS `RDPK`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND DELETED = '0') AS `RELATEDBUGCNT`,
(select count(1) from zt_projectproduct t inner join zt_project t2 on t2.id = t.project where t.product = t1.id and t2.deleted = '0') AS `RELATEDPROJECTS`,
(SELECT COUNT(1) FROM ZT_RELEASE WHERE PRODUCT= t1.`ID` AND DELETED = '0') AS `RELEASECNT`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`SUPPROREPORT`,
t1.`TYPE`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `CONFIRMED` = 0 AND DELETED = '0') AS `UNCONFIRMBUGCNT`
FROM `zt_product` t1 
LEFT JOIN `zt_module` t11 ON t1.`LINE` = t11.`ID` 

WHERE t1.DELETED = '0' 
( ( t1.`STATUS` <> 'closed'  OR  (#{srf.datacontext.products} is not null and t1.id = #{srf.datacontext.products}) )  AND  t1.`ORGID` =  ${srfsessioncontext('SRFORGID','{"defname":"ORGID","dename":"ZT_PRODUCT"}')} ) 

```
### 所有产品(AllProduct)<div id="Product_AllProduct"></div>
```sql
SELECT
	id,
	`name`,
	`code`,
	line,
	type,
	`status`,
	subStatus,
	`desc`,
	PO,
	QD,
	RD,
	acl,
	whitelist,
	createdBy,
	createdDate,
	createdVersion,
	`order`,
	deleted,
	IBIZ_ID,
	ORGID,
	MDEPTID 
FROM
	zt_product 
WHERE
	deleted = '0'
```
### 校验产品名称或产品代号是否已经存在(CheckNameOrCode)<div id="Product_CheckNameOrCode"></div>
```sql
SELECT
t1.id
FROM `zt_product` t1 

```
### 当前项目(CURPROJECT)<div id="Product_CurProject"></div>
```sql
SELECT
t1.`ACL`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` = 'active' AND DELETED = '0') AS `ACTIVEBUGCNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='active' AND DELETED = '0') AS `ACTIVESTORYCNT`,
(select count(1) from zt_build t where t.product = t1.id and t.deleted = '0') AS `BUILDCNT`,
(select count(1) from zt_case t where t.product = t1.id and t.deleted = '0') AS `CASECNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='changed' AND DELETED = '0') AS `CHANGEDSTORYCNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='closed' AND DELETED = '0') AS `CLOSEDSTORYCNT`,
t1.`CODE`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`CREATEDVERSION`,
t1.`DELETED`,
(select count(1) from zt_doc t where t.product = t1.id and t.deleted = '0') AS `DOCCNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='draft' AND DELETED = '0') AS `DRAFTSTORYCNT`,
t1.`IBIZ_ID`,
t1.`ID`,
'0' AS `ISTOP`,
t1.`LINE`,
t11.`NAME` AS `LINENAME`,
t1.`MDEPTID`,
t1.`NAME`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` <> 'closed' AND DELETED = '0') AS `NOTCLOSEDBUGCNT`,
t1.`ORDER`,
t1.`order` AS `ORDER1`,
t1.`ORGID`,
t1.`PO`,
t1.PO AS `POPK`,
t1.`PRODUCTCLASS`,
(SELECT COUNT(1) FROM ZT_PRODUCTPLAN WHERE PRODUCT= t1.`ID` AND DELETED = '0') AS `PRODUCTPLANCNT`,
t1.`QD`,
t1.QD AS `QDPK`,
t1.`RD`,
t1.RD AS `RDPK`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND DELETED = '0') AS `RELATEDBUGCNT`,
(select count(1) from zt_projectproduct t inner join zt_project t2 on t2.id = t.project where t.product = t1.id and t2.deleted = '0') AS `RELATEDPROJECTS`,
(SELECT COUNT(1) FROM ZT_RELEASE WHERE PRODUCT= t1.`ID` AND DELETED = '0') AS `RELEASECNT`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`SUPPROREPORT`,
t1.`TYPE`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `CONFIRMED` = 0 AND DELETED = '0') AS `UNCONFIRMBUGCNT`
FROM `zt_product` t1 
LEFT JOIN `zt_module` t11 ON t1.`LINE` = t11.`ID` 

WHERE EXISTS(SELECT * FROM `zt_projectproduct` t21 
 WHERE 
 t1.`ID` = t21.`PRODUCT`  AND  ( t21.`PROJECT` = ${srfdatacontext('srfparentkey','{"defname":"PROJECT","dename":"ZT_PROJECTPRODUCT"}')} ) ) 
t1.DELETED = '0' 

```
### 当前用户(CurUer)<div id="Product_CurUer"></div>
```sql
SELECT
	t1.* 
FROM
	(
SELECT
        t1.MDEPTID,
        t1.orgid,
	t1.`ACL`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` = 'active' AND DELETED = '0' ) AS `ACTIVEBUGCNT`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS` = 'active' AND DELETED = '0' ) AS `ACTIVESTORYCNT`,
	( SELECT count( 1 ) FROM zt_build t WHERE t.product = t1.id AND t.deleted = '0' ) AS `BUILDCNT`,
	( SELECT count( 1 ) FROM zt_case t WHERE t.product = t1.id AND t.deleted = '0' ) AS `CASECNT`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS` = 'changed' AND DELETED = '0' ) AS `CHANGEDSTORYCNT`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS` = 'closed' AND DELETED = '0' ) AS `CLOSEDSTORYCNT`,
	t1.`CODE`,
	t1.`CREATEDBY`,
	t1.`CREATEDDATE`,
	t1.`CREATEDVERSION`,
	t1.`DELETED`,
	( SELECT count( 1 ) FROM zt_doc t WHERE t.product = t1.id AND t.deleted = '0' ) AS `DOCCNT`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS` = 'draft' AND DELETED = '0' ) AS `DRAFTSTORYCNT`,
	t1.`ID`,
	t1.`LINE`,
	t11.`NAME` AS `LINENAME`,
	t1.`NAME`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` <> 'closed' AND DELETED = '0' ) AS `NOTCLOSEDBUGCNT`,
	t1.`ORDER`,
	(CASE WHEN T2.OBJECTORDER IS NOT NULL THEN T2.OBJECTORDER ELSE  t1.`ORDER` END) as `ORDER1`,
	(CASE WHEN T2.OBJECTORDER IS NOT NULL THEN 1 ELSE 0 END) as `ISTOP`,
	t1.`PO`,
	( SELECT COUNT( 1 ) FROM ZT_PRODUCTPLAN WHERE PRODUCT = t1.`ID` AND DELETED = '0' ) AS `PRODUCTPLANCNT`,
	t1.`QD`,
	t1.`RD`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND DELETED = '0' ) AS `RELATEDBUGCNT`,
	(
SELECT
	count( 1 ) 
FROM
	zt_projectproduct t
	INNER JOIN zt_project t2 ON t2.id = t.project 
WHERE
	t.product = t1.id 
	AND t2.deleted = '0' 
	) AS `RELATEDPROJECTS`,
	( SELECT COUNT( 1 ) FROM ZT_RELEASE WHERE PRODUCT = t1.`ID` AND DELETED = '0' ) AS `RELEASECNT`,
	t1.`STATUS`,
	t1.`SUBSTATUS`,
	t1.`TYPE`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `CONFIRMED` = 0 AND DELETED = '0' ) AS `UNCONFIRMBUGCNT` 
FROM
	`zt_product` t1
	left join t_ibz_top t2 on t1.id = t2.OBJECTID and t2.type = 'product' and t2.ACCOUNT = #{srf.sessioncontext.srfloginname}
	LEFT JOIN zt_module t11 ON t1.LINE = t11.ID 
WHERE
	t1.deleted = '0' 
	AND (t1.acl = 'open' or  t1.CREATEDBY =  #{srf.sessioncontext.srfloginname} 
	or t1.PO = #{srf.sessioncontext.srfloginname} 
	or t1.RD = #{srf.sessioncontext.srfloginname} 
	or t1.QD =  #{srf.sessioncontext.srfloginname} 
	) 
	UNION
SELECT
       t1.MDEPTID,
        t1.orgid,
	t1.`ACL`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` = 'active' AND DELETED = '0' ) AS `ACTIVEBUGCNT`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS` = 'active' AND DELETED = '0' ) AS `ACTIVESTORYCNT`,
	( SELECT count( 1 ) FROM zt_build t WHERE t.product = t1.id AND t.deleted = '0' ) AS `BUILDCNT`,
	( SELECT count( 1 ) FROM zt_case t WHERE t.product = t1.id AND t.deleted = '0' ) AS `CASECNT`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS` = 'changed' AND DELETED = '0' ) AS `CHANGEDSTORYCNT`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS` = 'closed' AND DELETED = '0' ) AS `CLOSEDSTORYCNT`,
	t1.`CODE`,
	t1.`CREATEDBY`,
	t1.`CREATEDDATE`,
	t1.`CREATEDVERSION`,
	t1.`DELETED`,
	( SELECT count( 1 ) FROM zt_doc t WHERE t.product = t1.id AND t.deleted = '0' ) AS `DOCCNT`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS` = 'draft' AND DELETED = '0' ) AS `DRAFTSTORYCNT`,
	t1.`ID`,
	t1.`LINE`,
	t11.`NAME` AS `LINENAME`,
	t1.`NAME`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` <> 'closed' AND DELETED = '0' ) AS `NOTCLOSEDBUGCNT`,
	t1.`ORDER`,
	(CASE WHEN T2.OBJECTORDER IS NOT NULL THEN T2.OBJECTORDER ELSE  t1.`ORDER` END) as `ORDER1`,
	(CASE WHEN T2.OBJECTORDER IS NOT NULL THEN 1 ELSE 0 END) as `ISTOP`,
	t1.`PO`,
	( SELECT COUNT( 1 ) FROM ZT_PRODUCTPLAN WHERE PRODUCT = t1.`ID` AND DELETED = '0' ) AS `PRODUCTPLANCNT`,
	t1.`QD`,
	t1.`RD`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND DELETED = '0' ) AS `RELATEDBUGCNT`,
	(
SELECT
	count( 1 ) 
FROM
	zt_projectproduct t
	INNER JOIN zt_project t2 ON t2.id = t.project 
WHERE
	t.product = t1.id 
	AND t2.deleted = '0' 
	) AS `RELATEDPROJECTS`,
	( SELECT COUNT( 1 ) FROM ZT_RELEASE WHERE PRODUCT = t1.`ID` AND DELETED = '0' ) AS `RELEASECNT`,
	t1.`STATUS`,
	t1.`SUBSTATUS`,
	t1.`TYPE`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `CONFIRMED` = 0 AND DELETED = '0' ) AS `UNCONFIRMBUGCNT` 
FROM
	`zt_product` t1
	left join t_ibz_top t2 on t1.id = t2.OBJECTID and t2.type = 'product' and t2.ACCOUNT = #{srf.sessioncontext.srfloginname}
	LEFT JOIN zt_module t11 ON t1.LINE = t11.ID 
WHERE
	t1.deleted = '0' 
	AND t1.id IN (
SELECT t.root from zt_team t
WHERE
	t.account =  #{srf.sessioncontext.srfloginname} 
	AND t.type = 'product' 
	 
	) 
	) t1
WHERE t1.orgid = #{srf.sessioncontext.srforgid} 

```
### DEFAULT(DEFAULT)<div id="Product_Default"></div>
```sql
SELECT
t1.`ACL`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` = 'active' AND DELETED = '0') AS `ACTIVEBUGCNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='active' AND DELETED = '0') AS `ACTIVESTORYCNT`,
(select count(1) from zt_build t where t.product = t1.id and t.deleted = '0') AS `BUILDCNT`,
(select count(1) from zt_case t where t.product = t1.id and t.deleted = '0') AS `CASECNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='changed' AND DELETED = '0') AS `CHANGEDSTORYCNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='closed' AND DELETED = '0') AS `CLOSEDSTORYCNT`,
t1.`CODE`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`CREATEDVERSION`,
t1.`DELETED`,
(select count(1) from zt_doc t where t.product = t1.id and t.deleted = '0') AS `DOCCNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='draft' AND DELETED = '0') AS `DRAFTSTORYCNT`,
t1.`IBIZ_ID`,
t1.`ID`,
'0' AS `ISTOP`,
t1.`LINE`,
t11.`NAME` AS `LINENAME`,
t1.`MDEPTID`,
t1.`NAME`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` <> 'closed' AND DELETED = '0') AS `NOTCLOSEDBUGCNT`,
t1.`ORDER`,
t1.`order` AS `ORDER1`,
t1.`ORGID`,
t1.`PO`,
t1.PO AS `POPK`,
t1.`PRODUCTCLASS`,
(SELECT COUNT(1) FROM ZT_PRODUCTPLAN WHERE PRODUCT= t1.`ID` AND DELETED = '0') AS `PRODUCTPLANCNT`,
t1.`QD`,
t1.QD AS `QDPK`,
t1.`RD`,
t1.RD AS `RDPK`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND DELETED = '0') AS `RELATEDBUGCNT`,
(select count(1) from zt_projectproduct t inner join zt_project t2 on t2.id = t.project where t.product = t1.id and t2.deleted = '0') AS `RELATEDPROJECTS`,
(SELECT COUNT(1) FROM ZT_RELEASE WHERE PRODUCT= t1.`ID` AND DELETED = '0') AS `RELEASECNT`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`SUPPROREPORT`,
t1.`TYPE`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `CONFIRMED` = 0 AND DELETED = '0') AS `UNCONFIRMBUGCNT`
FROM `zt_product` t1 
LEFT JOIN `zt_module` t11 ON t1.`LINE` = t11.`ID` 

WHERE t1.DELETED = '0' 
( t1.`ORGID` =  ${srfsessioncontext('srforgid','{"defname":"ORGID","dename":"ZT_PRODUCT"}')} ) 

```
### ES批量的导入(ESBulk)<div id="Product_ESBulk"></div>
```sql
SELECT
	t1.id,
	t1.`name`,
	t1.desc,
	t1.deleted
FROM
	zt_product t1
```
### 产品总览(ProductPM)<div id="Product_ProductPM"></div>
```sql
select t.`status`, count(t.id) as SRFCOUNT from zt_product t where t.`status` <> '' and t.`status` is not null and t.deleted = '0' and t.orgid = #{srf.sessioncontext.srforgid} GROUP BY t.`status`
```
### 产品团队(ProductTeam)<div id="Product_ProductTeam"></div>
```sql
SELECT
t1.`ACL`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` = 'active' AND DELETED = '0') AS `ACTIVEBUGCNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='active' AND DELETED = '0') AS `ACTIVESTORYCNT`,
(select count(1) from zt_build t where t.product = t1.id and t.deleted = '0') AS `BUILDCNT`,
(select count(1) from zt_case t where t.product = t1.id and t.deleted = '0') AS `CASECNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='changed' AND DELETED = '0') AS `CHANGEDSTORYCNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='closed' AND DELETED = '0') AS `CLOSEDSTORYCNT`,
t1.`CODE`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`CREATEDVERSION`,
t1.`DELETED`,
(select count(1) from zt_doc t where t.product = t1.id and t.deleted = '0') AS `DOCCNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='draft' AND DELETED = '0') AS `DRAFTSTORYCNT`,
t1.`IBIZ_ID`,
t1.`ID`,
'0' AS `ISTOP`,
t1.`LINE`,
t11.`NAME` AS `LINENAME`,
t1.`MDEPTID`,
t1.`NAME`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` <> 'closed' AND DELETED = '0') AS `NOTCLOSEDBUGCNT`,
t1.`ORDER`,
t1.`order` AS `ORDER1`,
t1.`ORGID`,
t1.`PO`,
t1.PO AS `POPK`,
(SELECT COUNT(1) FROM ZT_PRODUCTPLAN WHERE PRODUCT= t1.`ID` AND DELETED = '0') AS `PRODUCTPLANCNT`,
t1.`QD`,
t1.QD AS `QDPK`,
t1.`RD`,
t1.RD AS `RDPK`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND DELETED = '0') AS `RELATEDBUGCNT`,
(select count(1) from zt_projectproduct t inner join zt_project t2 on t2.id = t.project where t.product = t1.id and t2.deleted = '0') AS `RELATEDPROJECTS`,
(SELECT COUNT(1) FROM ZT_RELEASE WHERE PRODUCT= t1.`ID` AND DELETED = '0') AS `RELEASECNT`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`TYPE`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `CONFIRMED` = 0 AND DELETED = '0') AS `UNCONFIRMBUGCNT`
FROM `zt_product` t1 
LEFT JOIN zt_module t11 ON t1.LINE = t11.ID 
LEFT JOIN zt_team t21 on t21.root = t1.id
WHERE t1.DELETED = '0' 
t21.type = 'product' 
 t21.account = #{srf.sessioncontext.srfloginname} 
t1.orgid = #{srf.sessioncontext.srforgid} 

```
### 当前项目(StoryCURPROJECT)<div id="Product_StoryCurProject"></div>
```sql
SELECT
t1.`ACL`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` = 'active' AND DELETED = '0') AS `ACTIVEBUGCNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='active' AND DELETED = '0') AS `ACTIVESTORYCNT`,
(select count(1) from zt_build t where t.product = t1.id and t.deleted = '0') AS `BUILDCNT`,
(select count(1) from zt_case t where t.product = t1.id and t.deleted = '0') AS `CASECNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='changed' AND DELETED = '0') AS `CHANGEDSTORYCNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='closed' AND DELETED = '0') AS `CLOSEDSTORYCNT`,
t1.`CODE`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`CREATEDVERSION`,
t1.`DELETED`,
(select count(1) from zt_doc t where t.product = t1.id and t.deleted = '0') AS `DOCCNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='draft' AND DELETED = '0') AS `DRAFTSTORYCNT`,
t1.`IBIZ_ID`,
t1.`ID`,
'0' AS `ISTOP`,
t1.`LINE`,
t11.`NAME` AS `LINENAME`,
t1.`MDEPTID`,
t1.`NAME`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` <> 'closed' AND DELETED = '0') AS `NOTCLOSEDBUGCNT`,
t1.`ORDER`,
t1.`order` AS `ORDER1`,
t1.`ORGID`,
t1.`PO`,
t1.PO AS `POPK`,
t1.`PRODUCTCLASS`,
(SELECT COUNT(1) FROM ZT_PRODUCTPLAN WHERE PRODUCT= t1.`ID` AND DELETED = '0') AS `PRODUCTPLANCNT`,
t1.`QD`,
t1.QD AS `QDPK`,
t1.`RD`,
t1.RD AS `RDPK`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND DELETED = '0') AS `RELATEDBUGCNT`,
(select count(1) from zt_projectproduct t inner join zt_project t2 on t2.id = t.project where t.product = t1.id and t2.deleted = '0') AS `RELATEDPROJECTS`,
(SELECT COUNT(1) FROM ZT_RELEASE WHERE PRODUCT= t1.`ID` AND DELETED = '0') AS `RELEASECNT`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`SUPPROREPORT`,
t1.`TYPE`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `CONFIRMED` = 0 AND DELETED = '0') AS `UNCONFIRMBUGCNT`
FROM `zt_product` t1 
LEFT JOIN `zt_module` t11 ON t1.`LINE` = t11.`ID` 

WHERE EXISTS(SELECT * FROM `zt_projectproduct` t21 
 WHERE 
 t1.`ID` = t21.`PRODUCT`  AND  ( t21.`PROJECT` = ${srfdatacontext('project','{"defname":"PROJECT","dename":"ZT_PROJECTPRODUCT"}')} ) ) 
t1.DELETED = '0' 

```
### 默认（全部数据）(VIEW)<div id="Product_View"></div>
```sql
SELECT
t1.`ACL`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` = 'active' AND DELETED = '0') AS `ACTIVEBUGCNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='active' AND DELETED = '0') AS `ACTIVESTORYCNT`,
(select count(1) from zt_build t where t.product = t1.id and t.deleted = '0') AS `BUILDCNT`,
(select count(1) from zt_case t where t.product = t1.id and t.deleted = '0') AS `CASECNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='changed' AND DELETED = '0') AS `CHANGEDSTORYCNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='closed' AND DELETED = '0') AS `CLOSEDSTORYCNT`,
t1.`CODE`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`CREATEDVERSION`,
t1.`DELETED`,
t1.`DESC`,
(select count(1) from zt_doc t where t.product = t1.id and t.deleted = '0') AS `DOCCNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='draft' AND DELETED = '0') AS `DRAFTSTORYCNT`,
t1.`IBIZ_ID`,
t1.`ID`,
'0' AS `ISTOP`,
t1.`LINE`,
t11.`NAME` AS `LINENAME`,
t1.`MDEPTID`,
t1.`NAME`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` <> 'closed' AND DELETED = '0') AS `NOTCLOSEDBUGCNT`,
t1.`ORDER`,
t1.`order` AS `ORDER1`,
t1.`ORGID`,
t1.`PO`,
t1.PO AS `POPK`,
t1.`PRODUCTCLASS`,
(SELECT COUNT(1) FROM ZT_PRODUCTPLAN WHERE PRODUCT= t1.`ID` AND DELETED = '0') AS `PRODUCTPLANCNT`,
t1.`QD`,
t1.QD AS `QDPK`,
t1.`RD`,
t1.RD AS `RDPK`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND DELETED = '0') AS `RELATEDBUGCNT`,
(select count(1) from zt_projectproduct t inner join zt_project t2 on t2.id = t.project where t.product = t1.id and t2.deleted = '0') AS `RELATEDPROJECTS`,
(SELECT COUNT(1) FROM ZT_RELEASE WHERE PRODUCT= t1.`ID` AND DELETED = '0') AS `RELEASECNT`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`SUPPROREPORT`,
t1.`TYPE`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `CONFIRMED` = 0 AND DELETED = '0') AS `UNCONFIRMBUGCNT`,
t1.`WHITELIST`
FROM `zt_product` t1 
LEFT JOIN `zt_module` t11 ON t1.`LINE` = t11.`ID` 

WHERE t1.DELETED = '0' 

```

# **产品生命周期**(IBZ_PRODUCTLIFE)

### DEFAULT(DEFAULT)<div id="ProductLife_Default"></div>
```sql
SELECT
t1.`BEGIN`,
t1.`BRANCH`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`END`,
t1.`IBZ_PRODUCTLIFEID`,
t1.`IBZ_PRODUCTLIFENAME`,
t1.`MARKER`,
t1.`PARENT`,
t1.`PRODUCT`,
t1.`TYPE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`YEAR`
FROM `T_IBZ_PRODUCTLIFE` t1 

```
### 获取产品路线(GetRoadmap)<div id="ProductLife_GetRoadmap"></div>
```sql
SELECT
	*
FROM
	(
		(
			SELECT
				t111.*
			FROM
				(
					SELECT
						CONCAT(
							t1.product,
							'-productplan-',
							t1.id
						) AS ibz_productlifeid,
						CASE
					WHEN t11.title IS NULL THEN
						t1.title
					ELSE
						CONCAT(t11.title, '/', t1.title)
					END AS ibz_productlifename,
					t1.product AS product,
					t1.branch AS branch,
					t1.parent AS parent,
					'productplan' AS type,
					t1.`begin` AS `begin`,
					t1.`end` AS `end`,
					LEFT (t1.`end`, 4) AS `year`,
					null AS marker
				FROM
					zt_productplan t1
				LEFT JOIN zt_productplan t11 ON t1.parent = t11.id
				WHERE
					t1.DELETED = '0'
				AND t1.product = ${srfdatacontext('product','{"defname":"product","dename":"IBZ_PRODUCTLIFE"}')}
				AND t1.parent != '-1'
				AND (#{srf.datacontext.branch} is null or t1.branch =  #{srf.datacontext.branch})
				AND LEFT (t1.`end`, 4) = ${srfdatacontext('year','{"defname":"year","dename":"IBZ_PRODUCTLIFE"}')}
				) t111,
				(
					SELECT
						(
							CASE
							WHEN max(date) IS NULL THEN
								0
							ELSE
								max(date)
							END
						) AS `lastdata`
					FROM
						zt_release
					WHERE
						DELETED = '0'
					AND product = ${srfdatacontext('product','{"defname":"product","dename":"IBZ_PRODUCTLIFE"}')}
					AND (#{srf.datacontext.branch} is null or branch =  #{srf.datacontext.branch})
				) t112
			WHERE
				t111.`end` > t112.lastdata
		)
		UNION
			(
				SELECT
					CONCAT(
						t2.product,
						'-release-',
						t2.id
					) AS ibz_productlifeid,
					t2.`name` AS ibz_productlifename,
					t2.product AS product,
					t2.branch AS branch,
					null AS parent,
					'release' AS type,
					null AS `begin`,
					t2.`date` AS `end`,
					LEFT (t2.`date`, 4) AS `year`,
					t2.marker AS marker
				FROM
					zt_release t2
				WHERE
					t2.DELETED = '0'
				AND t2.product = ${srfdatacontext('product','{"defname":"product","dename":"IBZ_PRODUCTLIFE"}')}
				AND LEFT (t2.`date`, 4) = ${srfdatacontext('year','{"defname":"year","dename":"IBZ_PRODUCTLIFE"}')}
				AND (#{srf.datacontext.branch} is null or t2.branch =  #{srf.datacontext.branch})
			)
	) AS ibz_productlife
ORDER BY
	`end`  ASC
```
### 获取产品路线(GetRoadmapS)<div id="ProductLife_GetRoadmapS"></div>
```sql
SELECT * FROM ( ( SELECT t111.* FROM ( SELECT CONCAT( t1.product, '-productplan-', t1.id ) AS ibz_productlifeid, CASE WHEN t11.title IS NULL THEN t1.title ELSE CONCAT(t11.title, '/', t1.title) END AS ibz_productlifename, t1.product AS product, t1.branch AS branch, t1.parent AS parent, 'productplan' AS type, t1.`begin` AS `begin`, t1.`end` AS `end`, LEFT (t1.`end`, 4) AS `year`, null AS marker FROM zt_productplan t1 LEFT JOIN zt_productplan t11 ON t1.parent = t11.id WHERE t1.DELETED = '0' AND t1.product = ${srfdatacontext('product','{"defname":"product","dename":"IBZ_PRODUCTLIFE"}')} AND t1.parent != '-1' AND (#{srf.datacontext.branch} is null or t1.branch = #{srf.datacontext.branch}) AND LEFT (t1.`end`, 4) = ${srfdatacontext('year','{"defname":"year","dename":"IBZ_PRODUCTLIFE"}')} ) t111, ( SELECT ( CASE WHEN max(date) IS NULL THEN 0 ELSE max(date) END ) AS `lastdata` FROM zt_release WHERE DELETED = '0' AND product = ${srfdatacontext('product','{"defname":"product","dename":"IBZ_PRODUCTLIFE"}')} AND (#{srf.datacontext.branch} is null or branch = #{srf.datacontext.branch}) ) t112 WHERE t111.`end` > t112.lastdata ) UNION ( SELECT CONCAT( t2.product, '-release-', t2.id ) AS ibz_productlifeid, t2.`name` AS ibz_productlifename, t2.product AS product, t2.branch AS branch, null AS parent, 'release' AS type, t2.`date` AS `begin`, t2.`date` AS `end`, LEFT (t2.`date`, 4) AS `year`, t2.marker AS marker FROM zt_release t2 WHERE t2.DELETED = '0' AND t2.product = ${srfdatacontext('product','{"defname":"product","dename":"IBZ_PRODUCTLIFE"}')} AND LEFT (t2.`date`, 4) = ${srfdatacontext('year','{"defname":"year","dename":"IBZ_PRODUCTLIFE"}')} AND (#{srf.datacontext.branch} is null or t2.branch = #{srf.datacontext.branch}) ) ) AS ibz_productlife
```
### 获取路线图年份(RoadMapYear)<div id="ProductLife_RoadMapYear"></div>
```sql
SELECT
	*
FROM
	(
		(
			SELECT DISTINCT
				LEFT (`end`, 4) AS `year`
			FROM
				zt_productplan,
				(
					SELECT
						(
							CASE
							WHEN MIN(LEFT(`date`, 4)) IS NULL THEN
								0
							ELSE
								MIN(LEFT(`date`, 4))
							END
						) AS `minyear`
					FROM
						zt_release
					WHERE
						deleted = '0'
					AND product = ${srfdatacontext('product','{"defname":"product","dename":"IBZ_PRODUCTLIFE"}')}
					AND (#{srf.datacontext.branch} is null or branch =  #{srf.datacontext.branch})
				) ztr
			WHERE
				deleted = '0'
			AND product = ${srfdatacontext('product','{"defname":"product","dename":"IBZ_PRODUCTLIFE"}')}
			AND (#{srf.datacontext.branch} is null or branch =  #{srf.datacontext.branch})
			AND LEFT (`end`, 4) < '2030'
			AND LEFT (`end`, 4) > `minyear`
		)
		UNION
			(
				SELECT DISTINCT
					LEFT (`date`, 4) AS `year`
				FROM
					zt_release
				WHERE
					deleted = '0'
				AND product = ${srfdatacontext('product','{"defname":"product","dename":"IBZ_PRODUCTLIFE"}')}
				AND (#{srf.datacontext.branch} is null or branch =  #{srf.datacontext.branch})
			)
	) as ibz_productlife
```
### 默认（全部数据）(VIEW)<div id="ProductLife_View"></div>
```sql
SELECT
t1.`BEGIN`,
t1.`BRANCH`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`END`,
t1.`IBZ_PRODUCTLIFEID`,
t1.`IBZ_PRODUCTLIFENAME`,
t1.`MARKER`,
t1.`PARENT`,
t1.`PRODUCT`,
t1.`TYPE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`YEAR`
FROM `T_IBZ_PRODUCTLIFE` t1 

```

# **产品线**(IBZ_PRODUCTLINE)

### DEFAULT(DEFAULT)<div id="ProductLine_Default"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`IBZ_PRODUCTLINEID`,
t1.`IBZ_PRODUCTLINENAME`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZ_PRODUCTLINE` t1 

```
### 默认（全部数据）(VIEW)<div id="ProductLine_View"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`IBZ_PRODUCTLINEID`,
t1.`IBZ_PRODUCTLINENAME`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_IBZ_PRODUCTLINE` t1 

```

# **需求模块**(IBZ_PRODUCTMODULE)

### BYPATH(BYPATH)<div id="ProductModule_ByPath"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 

WHERE t1.DELETED = '0' 
( t1.`TYPE` = 'story' ) 
FIND_IN_SET (${srfdatacontext('srfparentkey','{"defname":"ID","dename":"ZT_PRODUCTMODULE"}')}, t1.`PATH`) 

```
### DEFAULT(DEFAULT)<div id="ProductModule_Default"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t21.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`ROOT`,
t11.`NAME` AS `ROOTNAME`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_product` t11 ON t1.`ROOT` = t11.`ID` 
LEFT JOIN `zt_module` t21 ON t1.`PARENT` = t21.`ID` 

WHERE t1.DELETED = '0' 
( t1.`TYPE` = 'story' ) 

```
### 父模块(ParentModule)<div id="ProductModule_ParentModule"></div>
```sql
SELECT
	t1.`BRANCH`,
	t1.`DELETED`,
	t1.`GRADE`,
	t1.`ID`,
	( CASE WHEN EXISTS ( SELECT 1 FROM ZT_MODULE WHERE PARENT = t1.`ID` ) THEN FALSE ELSE TRUE END ) AS `ISLEAF`,
	CONCAT(
	'/',
	(
SELECT
	GROUP_CONCAT( tt.NAME SEPARATOR '/' ) 
FROM
	zt_module tt 
WHERE
	FIND_IN_SET( tt.id, t1.path ) 
	AND tt.type = 'story' 
GROUP BY
	tt.root 
	) 
	) AS `NAME`,
	t1.`ORDER`,
	t1.`OWNER`,
	t1.`PARENT`,
	t21.`NAME` AS `PARENTNAME`,
	t1.`PATH`,
	t1.`ROOT`,
	t11.`NAME` AS `ROOTNAME`,
	t1.`SHORT`,
	t1.`TYPE` 
FROM
	`zt_module` t1
	LEFT JOIN zt_product t11 ON t1.ROOT = t11.ID
	LEFT JOIN zt_module t21 ON t1.PARENT = t21.ID
WHERE t1.type = 'story' 
t1.id <> #{srf.webcontext.id} and find_in_set(#{srf.webcontext.id},t1.path) = 0 
t1.DELETED = '0' 

```
### 根模块(ROOT)<div id="ProductModule_Root"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t21.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`ROOT`,
t11.`NAME` AS `ROOTNAME`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_product` t11 ON t1.`ROOT` = t11.`ID` 
LEFT JOIN `zt_module` t21 ON t1.`PARENT` = t21.`ID` 

WHERE t1.DELETED = '0' 
( t1.`TYPE` = 'story'  AND  ( t1.`PARENT` IS NULL  OR  t1.`PARENT` = 0 ) ) 

```
### 根模块_无分支(Root_NoBranch)<div id="ProductModule_Root_NoBranch"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t21.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`ROOT`,
t11.`NAME` AS `ROOTNAME`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_product` t11 ON t1.`ROOT` = t11.`ID` 
LEFT JOIN `zt_module` t21 ON t1.`PARENT` = t21.`ID` 

WHERE t1.DELETED = '0' 
( t1.`TYPE` = 'story'  AND  t1.`BRANCH` = 0  AND  ( t1.`PARENT` IS NULL  OR  t1.`PARENT` = 0 ) ) 

```
### StoryModule(StoryModule)<div id="ProductModule_StoryModule"></div>
```sql
SELECT
	t1.* 
FROM
	(
	SELECT
		'0' AS deleted,
		0 AS ID,
		'/' AS `name`,- 1 AS ORDERPK,
		${srfwebcontext('product','{"defname":"product","dename":"ZT_PRODUCT"}')} AS root,
		'story' AS type 
	FROM
	DUAL UNION
	SELECT
		t1.`DELETED`,
		t1.`ID`,
		CONCAT(
			'/',case when t1.parent > 0 then (
			SELECT
				GROUP_CONCAT( tt.NAME SEPARATOR '/' ) 
			FROM
				zt_module tt 
			WHERE
				FIND_IN_SET( tt.id, t1.path ) 
				AND tt.type = 'story' 
			GROUP BY
				tt.root 
			) else t1.`name` end) AS `NAME`,
		(
			CONCAT_ws(
				'',
			CASE
					
					WHEN t1.parent > 0 THEN
						(
						SELECT
							GROUP_CONCAT( tt.`ORDER` SEPARATOR '-' ) 
						FROM
							zt_module tt 
						WHERE
							FIND_IN_SET( tt.id, t1.path ) 
							AND tt.type = 'story' 
						GROUP BY
							tt.root 
							LIMIT 0,
							1 
						) ELSE t1.`ORDER` 
					END 
					)) AS ORDERPK,
				t1.`ROOT`,
				t1.`TYPE` 
			FROM
			`zt_module` t1 
			where t1.type = 'story' and t1.deleted = '0'
	) t1
WHERE t1.DELETED = '0' 
( t1.`TYPE` = 'story' ) 

```
### 默认（全部数据）(VIEW)<div id="ProductModule_View"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`COLLECTOR`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t21.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`ROOT`,
t11.`NAME` AS `ROOTNAME`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_product` t11 ON t1.`ROOT` = t11.`ID` 
LEFT JOIN `zt_module` t21 ON t1.`PARENT` = t21.`ID` 

WHERE t1.DELETED = '0' 
( t1.`TYPE` = 'story' ) 

```

# **产品计划**(ZT_PRODUCTPLAN)

### 子计划(ChildPlan)<div id="ProductPlan_ChildPlan"></div>
```sql
SELECT
t1.`BEGIN`,
(case when t1.`begin` = '2030-01-01' then '待定' else t1.`begin` end) AS `BEGINSTR`,
t1.`BRANCH`,
((select count(t.id) FROM zt_bug t where (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0')) AS `BUGCNT`,
t1.`DELETED`,
t1.`END`,
t1.`ORDER`,
t1.`ORDER` AS `ORDERNUM`,
(case when t1.`end` = '2030-01-01' then '待定' else t1.`end` end) AS `ENDSTR`,
(select sum(t.estimate) from zt_story t where (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0' ) AS `ESTIMATECNT`,
(case when t1.`begin` = '2030-01-01' or t1.`end` = '2030-01-01' then 'on' else '' end) AS `FUTURE`,
t1.`ID`,
(case when t1.`end` > now() then '0' else '1' end) AS `ISEXPIRED`,
t1.`PARENT`,
t11.`TITLE` AS `PARENTNAME`,
t1.`PRODUCT`,
(case when (select COUNT(1) from zt_productplan t where t.deleted = '0' and t.parent = t1.id) > 0 then 'parent' else 'normal' end) AS `STATUSS`,
((select COUNT(t.id) from zt_story t where (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0' )) AS `STORYCNT`,
	case when ( To_Days( t1.`end` ) - To_Days( t1.`begin`) ) is null then null else ( To_Days( t1.`end` ) - To_Days( t1.`begin` ) ) + 1 end  AS `DURATION`,
t1.`TITLE` as `TITLE`
FROM `zt_productplan` t1 
LEFT JOIN `zt_productplan` t11 ON t1.`PARENT` = t11.`ID` 

WHERE t1.DELETED = '0' 

```
### DEFAULT(DEFAULT)<div id="ProductPlan_Default"></div>
```sql
SELECT
t1.`BEGIN`,
t1.`BEGINACT`,
(case when t1.`begin` = '2030-01-01' then '待定' else t1.`begin` end) AS `BEGINSTR`,
t1.`BRANCH`,
((select count(t.id) FROM zt_bug t where  t.product = t1.product and  (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0')) AS `BUGCNT`,
case when t1.`end` is null or t1.`end` = '0000-00-00' or t1.`end` = '1970-01-01' then '' when t1.`status` in('wait','doing') and t1.`end` < DATE_FORMAT(NOW(),'%Y-%m-%d') then CONCAT('','延期',TO_DAYS(NOW()) - TO_DAYS(t1.`end`),'天') ELSE '' end AS `DELAY`,
t1.`DELETED`,
(to_Days(t1.`END`) - to_Days(t1.`BEGIN`)) AS `DURATION`,
t1.`END`,
t1.`ENDACT`,
(case when t1.`end` = '2030-01-01' then '待定' else t1.`end` end) AS `ENDSTR`,
(select sum(t.estimate) from zt_story t where  t.product = t1.product and  (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0' ) AS `ESTIMATECNT`,
(case when t1.`begin` = '2030-01-01' or t1.`end` = '2030-01-01' then 'on' else '' end) AS `FUTURE`,
t1.`ID`,
(case when t1.`end` > now() then '0' else '1' end) AS `ISEXPIRED`,
t1.`ORDER`,
t1.`ORDER` AS `ORDERNUM`,
t1.`PARENT`,
t11.`TITLE` AS `PARENTNAME`,
t1.`PRODUCT`,
t1.`STATUS`,
(case when (select COUNT(1) from zt_productplan t where t.deleted = '0' and t.parent = t1.id) > 0 then 'parent' else 'normal' end) AS `STATUSS`,
((select COUNT(t.id) from zt_story t where  t.product = t1.product and  (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0' )) AS `STORYCNT`,
t1.`TITLE`
FROM `zt_productplan` t1 
LEFT JOIN `zt_productplan` t11 ON t1.`PARENT` = t11.`ID` 

WHERE t1.DELETED = '0' 

```
### 默认查询(DefaultParent)<div id="ProductPlan_DefaultParent"></div>
```sql
SELECT
t1.`BEGIN`,
t1.`BEGINACT`,
(case when t1.`begin` = '2030-01-01' then '待定' else t1.`begin` end) AS `BEGINSTR`,
t1.`BRANCH`,
((select count(t.id) FROM zt_bug t where  t.product = t1.product and  (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0')) AS `BUGCNT`,
case when t1.`end` is null or t1.`end` = '0000-00-00' or t1.`end` = '1970-01-01' then '' when t1.`status` in('wait','doing') and t1.`end` < DATE_FORMAT(NOW(),'%Y-%m-%d') then CONCAT('','延期',TO_DAYS(NOW()) - TO_DAYS(t1.`end`),'天') ELSE '' end AS `DELAY`,
t1.`DELETED`,
(to_Days(t1.`END`) - to_Days(t1.`BEGIN`)) AS `DURATION`,
t1.`END`,
t1.`ENDACT`,
(case when t1.`end` = '2030-01-01' then '待定' else t1.`end` end) AS `ENDSTR`,
(select sum(t.estimate) from zt_story t where  t.product = t1.product and  (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0' ) AS `ESTIMATECNT`,
(case when t1.`begin` = '2030-01-01' or t1.`end` = '2030-01-01' then 'on' else '' end) AS `FUTURE`,
t1.`ID`,
(case when t1.`end` > now() then '0' else '1' end) AS `ISEXPIRED`,
t1.`ORDER`,
t1.`ORDER` AS `ORDERNUM`,
t1.`PARENT`,
t11.`TITLE` AS `PARENTNAME`,
t1.`PRODUCT`,
t1.`STATUS`,
(case when (select COUNT(1) from zt_productplan t where t.deleted = '0' and t.parent = t1.id) > 0 then 'parent' else 'normal' end) AS `STATUSS`,
((select COUNT(t.id) from zt_story t where  t.product = t1.product and  (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0' )) AS `STORYCNT`,
t1.`TITLE`
FROM `zt_productplan` t1 
LEFT JOIN `zt_productplan` t11 ON t1.`PARENT` = t11.`ID` 

WHERE t1.DELETED = '0' 
( ( t1.`PARENT` <= 0 ) ) 

```
### 获取产品计划列表(GetList)<div id="ProductPlan_GetList"></div>
```sql
SELECT
t1.`BEGIN`,
t1.`BEGINACT`,
(case when t1.`begin` = '2030-01-01' then '待定' else t1.`begin` end) AS `BEGINSTR`,
t1.`BRANCH`,
((select count(t.id) FROM zt_bug t where  t.product = t1.product and  (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0')) AS `BUGCNT`,
case when t1.`end` is null or t1.`end` = '0000-00-00' or t1.`end` = '1970-01-01' then '' when t1.`status` in('wait','doing') and t1.`end` < DATE_FORMAT(NOW(),'%Y-%m-%d') then CONCAT('','延期',TO_DAYS(NOW()) - TO_DAYS(t1.`end`),'天') ELSE '' end AS `DELAY`,
t1.`DELETED`,
(to_Days(t1.`END`) - to_Days(t1.`BEGIN`)) AS `DURATION`,
t1.`END`,
t1.`ENDACT`,
(case when t1.`end` = '2030-01-01' then '待定' else t1.`end` end) AS `ENDSTR`,
(select sum(t.estimate) from zt_story t where  t.product = t1.product and  (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0' ) AS `ESTIMATECNT`,
(case when t1.`begin` = '2030-01-01' or t1.`end` = '2030-01-01' then 'on' else '' end) AS `FUTURE`,
t1.`ID`,
(case when t1.`end` > now() then '0' else '1' end) AS `ISEXPIRED`,
t1.`ORDER`,
t1.`ORDER` AS `ORDERNUM`,
t1.`PARENT`,
t11.`TITLE` AS `PARENTNAME`,
t1.`PRODUCT`,
t1.`STATUS`,
(case when (select COUNT(1) from zt_productplan t where t.deleted = '0' and t.parent = t1.id) > 0 then 'parent' else 'normal' end) AS `STATUSS`,
((select COUNT(t.id) from zt_story t where  t.product = t1.product and  (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0' )) AS `STORYCNT`,
t1.`TITLE`
FROM `zt_productplan` t1 
LEFT JOIN `zt_productplan` t11 ON t1.`PARENT` = t11.`ID` 

WHERE t1.DELETED = '0' 
( ( t1.`PRODUCT` = ${srfdatacontext('product','{"defname":"PRODUCT","dename":"ZT_PRODUCTPLAN"}')}  OR  t1.`PRODUCT` = ${srfdatacontext('products','{"defname":"PRODUCT","dename":"ZT_PRODUCTPLAN"}')} ) ) 

```
### 计划（代码表）(PlanCodeList)<div id="ProductPlan_PlanCodeList"></div>
```sql
select t1.* from ( SELECT
t1.`ID`,
t1.`TITLE`,
t1.product,
t1.parent
FROM `zt_productplan` t1 
UNION
SELECT
0 as `ID`,
'未计划' as `TITLE`,
0 as product,
0 as parent
FROM dual  ) t1
```
### 项目计划任务（项目管理-项目计划）(PlanTasks)<div id="ProductPlan_PlanTasks"></div>
```sql
SELECT
CASE
        
WHEN
        t1.`end` IS NULL 
        OR t1.`end` = '0000-00-00' 
        OR t1.`end` = '1970-01-01' THEN
        '' 
WHEN t1.`status` IN ( 'wait', 'doing' ) 
        AND t1.`end` < DATE_FORMAT( NOW( ), '%Y-%m-%d' ) THEN
                CONCAT( '', '延期', TO_DAYS( NOW( ) ) - TO_DAYS( t1.`end` ), '天' ) ELSE '' 
        END AS delay,
        t1.`BEGIN`,
        t1.beginact,
        case when  t1.beginact is null 
        OR t1.beginact = '0000-00-00' 
        OR t1.beginact = '1970-01-01'  then t1.`BEGIN`
        else t1.beginact
        end as  datebegin,
        ( CASE WHEN t1.`begin` = '2030-01-01' THEN '待定' ELSE t1.`begin` END ) AS `BEGINSTR`,
        t1.`BRANCH`,
        (
                (
                SELECT
                        count( t.id ) 
                FROM
                        zt_bug t 
                WHERE
                        t.product = t1.product 
                        AND (
                                t.plan = t1.id 
                                OR (
                                        t.plan IN ( SELECT t2.id FROM zt_productplan t2 WHERE t2.parent = t1.id AND t2.deleted = '0' ) 
                                ) 
                        ) 
                        AND t.deleted = '0' 
                ) 
        ) AS `BUGCNT`,
				t1.`DELETED`,
        t1.`END`,
        t1.endact,
        case when t1.endact is null 
        OR t1.`endact` = '0000-00-00' 
        OR t1.`endact` = '1970-01-01'  then t1.`END`
        else t1.endact end as dateend,
        ( CASE WHEN t1.`end` = '2030-01-01' THEN '待定' ELSE t1.`end` END ) AS `ENDSTR`,
        (
        SELECT
                sum( t.estimate ) 
        FROM
                zt_story t 
        WHERE
                t.product = t1.product 
                AND (
                        t.plan = t1.id 
                        OR (
                                t.plan IN ( SELECT t2.id FROM zt_productplan t2 WHERE t2.parent = t1.id AND t2.deleted = '0' ) 
                        ) 
                ) 
                AND t.deleted = '0' 
        ) AS `ESTIMATECNT`,
        ( CASE WHEN t1.`begin` = '2030-01-01' OR t1.`end` = '2030-01-01' THEN 'on' ELSE '' END ) AS `FUTURE`,
        t1.`ID`,
        ( CASE WHEN t1.`end` > now( ) THEN '0' ELSE '1' END ) AS `ISEXPIRED`,
        t1.`PARENT`,
        t11.`TITLE` AS `PARENTNAME`,
        t1.`PRODUCT`,
        (
        CASE
                        
                        WHEN ( SELECT COUNT( 1 ) FROM zt_productplan t WHERE t.deleted = '0' AND t.parent = t1.id ) > 0 THEN
                        'parent' ELSE 'normal' 
                END 
                ) AS `STATUSS`,
                (
                        (
                        SELECT
                                COUNT( t.id ) 
                        FROM
                                zt_story t 
                        WHERE
                                t.product = t1.product 
                                AND (
                                        t.plan = t1.id 
                                        OR (
                                                t.plan IN ( SELECT t2.id FROM zt_productplan t2 WHERE t2.parent = t1.id AND t2.deleted = '0' ) 
                                        ) 
                                ) 
                                AND t.deleted = '0' 
                        ) 
                ) AS `STORYCNT`,
                (
                        (
                        SELECT
                                COUNT( t.id ) 
                        FROM
                                zt_task t 
                        WHERE
                                t.deleted = '0' 
                                AND t.plan > 0 
                                AND (
                                        t.plan = t1.id 
                                        OR (
                                                t.plan IN ( SELECT t2.id FROM zt_productplan t2 WHERE t2.parent = t1.id AND t2.deleted = '0' ) 
                                        ) 
                                ) 
                        ) 
                ) AS `TASKSCNT`,
                (
                        (
                        SELECT
                                COUNT( t.id ) 
                        FROM
                                zt_task t 
                        WHERE
                                t.deleted = '0' 
                                AND t.plan > 0 
                                AND (
                                        t.plan = t1.id 
                                        OR (
                                                t.plan IN ( SELECT t2.id FROM zt_productplan t2 WHERE t2.parent = t1.id AND t2.deleted = '0' ) 
                                        ) 
                                ) 
                                AND t.`status` = 'done' 
                        ) 
                ) AS `FINISHEDTASKSCNT`,
                (
                        (
                        SELECT
                                COUNT( t.id ) 
                        FROM
                                zt_task t 
                        WHERE
                                t.deleted = '0' 
                                AND t.plan > 0 
                                AND (
                                        t.plan = t1.id 
                                        OR (
                                                t.plan IN ( SELECT t2.id FROM zt_productplan t2 WHERE t2.parent = t1.id AND t2.deleted = '0' ) 
                                        ) 
                                ) 
                                AND t.`status` IN ( 'doing', 'wait', 'pause' ) 
                        ) 
                ) AS `UNFINISHEDTASKSCNT`,
                (
                        (
                        SELECT
                                COUNT( t.id ) 
                        FROM
                                zt_task t 
                        WHERE
                                t.deleted = '0' 
                                AND t.plan > 0 
                                AND (
                                        t.plan = t1.id 
                                        OR (
                                                t.plan IN ( SELECT t2.id FROM zt_productplan t2 WHERE t2.parent = t1.id AND t2.deleted = '0' ) 
                                        ) 
                                ) 
                                AND t.`status` IN ( 'wait', 'doing', 'pause' ) 
                                AND t.deadline != '0000-00-00' 
                                AND t.deadline != '1970-01-01' 
                                AND t.deadline < DATE_FORMAT( now( ), '%y-%m-%d' ) 
                        ) 
                ) AS `DELAYTASKSCNT`,
                t1.`TITLE`,
                t1.`ORDER`,
                t1.`ORDER` AS `ORDERNUM`,
                IFNULL( t2.LeftEstimate, 0 ) AS leftestimate,
                IFNULL( t2.ConsumedEstimate, 0 ) AS consumedestimate,
                t1.`STATUS`
        FROM
                `zt_PRODUCTPLAN` t1
                LEFT JOIN `zt_PRODUCTPLAN` t11 ON t1.`PARENT` = t11.`ID`
                LEFT JOIN (
                SELECT
                        SUM( t.LeftEstimate ) AS LeftEstimate,
                        SUM( t.ConsumedEstimate ) AS ConsumedEstimate,
                        t.plan 
                FROM
                        (
                        SELECT
                                t1.task,
                                SUM( t1.LEFT ) AS LeftEstimate,
                                SUM( t1.consumed ) AS ConsumedEstimate,
                                t2.NAME,
                                t2.plan 
                        FROM
                                zt_taskestimate t1
                                LEFT JOIN zt_task t2 ON t1.task = t2.id 
                        WHERE
                                t2.deleted = '0' 
                                AND t2.plan > 0 
                        GROUP BY
                                t1.task 
                        ) t 
                WHERE
                        t.plan > 0 
                GROUP BY
                t.plan 
) t2 ON t1.id = t2.plan
				
WHERE t1.DELETED = '0' 
( ( t1.`PARENT` <= 0 ) ) 

```
### 项目立项(ProjectApp)<div id="ProductPlan_ProjectApp"></div>
```sql
SELECT
t1.`BEGIN`,
t1.`BEGINACT`,
(case when t1.`begin` = '2030-01-01' then '待定' else t1.`begin` end) AS `BEGINSTR`,
t1.`BRANCH`,
((select count(t.id) FROM zt_bug t where  t.product = t1.product and  (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0')) AS `BUGCNT`,
case when t1.`end` is null or t1.`end` = '0000-00-00' or t1.`end` = '1970-01-01' then '' when t1.`status` in('wait','doing') and t1.`end` < DATE_FORMAT(NOW(),'%Y-%m-%d') then CONCAT('','延期',TO_DAYS(NOW()) - TO_DAYS(t1.`end`),'天') ELSE '' end AS `DELAY`,
t1.`DELETED`,
(to_Days(t1.`END`) - to_Days(t1.`BEGIN`)) AS `DURATION`,
t1.`END`,
t1.`ENDACT`,
(case when t1.`end` = '2030-01-01' then '待定' else t1.`end` end) AS `ENDSTR`,
(select sum(t.estimate) from zt_story t where  t.product = t1.product and  (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0' ) AS `ESTIMATECNT`,
(case when t1.`begin` = '2030-01-01' or t1.`end` = '2030-01-01' then 'on' else '' end) AS `FUTURE`,
t1.`ID`,
(case when t1.`end` > now() then '0' else '1' end) AS `ISEXPIRED`,
t1.`ORDER`,
t1.`ORDER` AS `ORDERNUM`,
t1.`PARENT`,
t11.`TITLE` AS `PARENTNAME`,
t1.`PRODUCT`,
t1.`STATUS`,
(case when (select COUNT(1) from zt_productplan t where t.deleted = '0' and t.parent = t1.id) > 0 then 'parent' else 'normal' end) AS `STATUSS`,
((select COUNT(t.id) from zt_story t where  t.product = t1.product and  (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0' )) AS `STORYCNT`,
t1.`TITLE`
FROM `zt_productplan` t1 
LEFT JOIN `zt_productplan` t11 ON t1.`PARENT` = t11.`ID` 

WHERE t1.DELETED = '0' 
( t1.`PRODUCT` = ${srfwebcontext('product','{"defname":"PRODUCT","dename":"ZT_PRODUCTPLAN"}')} ) 

```
### 项目计划列表(ProjectPlan)<div id="ProductPlan_ProjectPlan"></div>
```sql
SELECT 	t1.`BEGIN`, (case when t1.`begin` = '2030-01-01' then '待定' else t1.`begin` end) AS `BEGINSTR`, 	t1.`BRANCH`, 	t1.`DELETED`, 	t1.`END`, (case when t1.`end` = '2030-01-01' then '待定' else t1.`end` end) AS `ENDSTR`, 	t1.`ORDER`,
t1.`ORDER` AS `ORDERNUM`,t1.`ID`, 	t1.`PARENT`, 	t11.`TITLE` AS `PARENTNAME`, 	t1.`PRODUCT`, 	(CONCAT(t31.`name`,'/',t1.title,'[',case when t1.`begin` is not null then t1.`begin` else '' end,'~',case when t1.`end` is not null then t1.`end` else '' end,']')) as `TITLE`  FROM 	`zt_productplan` t1 	LEFT JOIN zt_productplan t11 ON t1.PARENT = t11.ID 	LEFT JOIN zt_product t31 ON t1.product = t31.id  	LEFT JOIN zt_projectproduct t21 ON t31.id = t21.product and t1.id = t21.plan
WHERE ( t21.`PROJECT` = 	${srfdatacontext('srfparentkey','{"defname":"PROJECT","dename":"ZT_PROJECTPRODUCT"}')}
 or t1.parent in (	SELECT GROUP_CONCAT(t1.id)
FROM
	`zt_productplan` t1
	LEFT JOIN zt_productplan t11 ON t1.PARENT = t11.ID
	LEFT JOIN zt_product t31 ON t1.product = t31.id 
	LEFT JOIN zt_projectproduct t21 ON t31.id = t21.product and t1.id = t21.plan
	
	where t1.deleted = '0' and ( t21.`PROJECT` = 
${srfdatacontext('srfparentkey','{"defname":"PROJECT","dename":"ZT_PROJECTPRODUCT"}')}
 ) ) ) 
t1.DELETED = '0' 

```
### 根计划(RootPlan)<div id="ProductPlan_RootPlan"></div>
```sql
SELECT
t1.`BEGIN`,
(case when t1.`begin` = '2030-01-01' then '待定' else t1.`begin` end) AS `BEGINSTR`,
t1.`BRANCH`,
((select count(t.id) FROM zt_bug t where (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0')) AS `BUGCNT`,
t1.`DELETED`,
t1.`END`,
t1.`ORDER`,
t1.`ORDER` AS `ORDERNUM`,
(case when t1.`end` = '2030-01-01' then '待定' else t1.`end` end) AS `ENDSTR`,
(select sum(t.estimate) from zt_story t where (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0' ) AS `ESTIMATECNT`,
(case when t1.`begin` = '2030-01-01' or t1.`end` = '2030-01-01' then 'on' else '' end) AS `FUTURE`,
t1.`ID`,
(case when t1.`end` > now() then '0' else '1' end) AS `ISEXPIRED`,
t1.`PARENT`,
t11.`TITLE` AS `PARENTNAME`,
t1.`PRODUCT`,
(case when (select COUNT(1) from zt_productplan t where t.deleted = '0' and t.parent = t1.id) > 0 then 'parent' else 'normal' end) AS `STATUSS`,
((select COUNT(t.id) from zt_story t where (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0' )) AS `STORYCNT`,
	case when ( To_Days( t1.`end` ) - To_Days( t1.`begin`) ) is null then null else ( To_Days( t1.`end` ) - To_Days( t1.`begin` ) ) + 1 end  AS `DURATION`,
t1.`TITLE` as `TITLE`
FROM `zt_productplan` t1 
LEFT JOIN `zt_productplan` t11 ON t1.`PARENT` = t11.`ID` 

WHERE t1.DELETED = '0' 
t1.parent <= '0' 

```
### 任务计划(TaskPlan)<div id="ProductPlan_TaskPlan"></div>
```sql
SELECT
t1.`BEGIN`,
(case when t1.`begin` = '2030-01-01' then '待定' else t1.`begin` end) AS `BEGINSTR`,
t1.`BRANCH`,
((select count(t.id) FROM zt_bug t where (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0')) AS `BUGCNT`,
case when t1.`end` is null or t1.`end` = '0000-00-00' or t1.`end` = '1970-01-01' then '' when t1.`status` in('wait','doing') and t1.`end` < DATE_FORMAT(NOW(),'%Y-%m-%d') then CONCAT('','延期',TO_DAYS(NOW()) - TO_DAYS(t1.`end`),'天') ELSE '' end AS `DELAY`,
t1.`DELETED`,
(to_Days(t1.`END`) - to_Days(t1.`BEGIN`)) AS `DURATION`,
t1.`END`,
(case when t1.`end` = '2030-01-01' then '待定' else t1.`end` end) AS `ENDSTR`,
(select sum(t.estimate) from zt_story t where (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0' ) AS `ESTIMATECNT`,
(case when t1.`begin` = '2030-01-01' or t1.`end` = '2030-01-01' then 'on' else '' end) AS `FUTURE`,
t1.`ID`,
(case when t1.`end` > now() then '0' else '1' end) AS `ISEXPIRED`,
t1.`PARENT`,
t1.`ORDER`,
t1.`ORDER` AS `ORDERNUM`,
t11.`TITLE` AS `PARENTNAME`,
t1.`PRODUCT`,
t1.`STATUS`,
(case when (select COUNT(1) from zt_productplan t where t.deleted = '0' and t.parent = t1.id) > 0 then 'parent' else 'normal' end) AS `STATUSS`,
((select COUNT(t.id) from zt_story t where (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0' )) AS `STORYCNT`,
case when (t11.`TITLE` is not null) then CONCAT('/',t11.`TITLE`,'/',t1.`TITLE`) else CONCAT('/',t1.`TITLE`) end as TITLE ,case when t1.parent <= 0 then t1.`begin` else CONCAT_WS('',t11.`begin`,'-',t1.`begin`) end as BEGINTIME 
FROM `zt_productplan` t1 
LEFT JOIN `zt_productplan` t11 ON t1.`PARENT` = t11.`ID` 

WHERE t1.DELETED = '0' 
( t1.product in (select t.product from zt_projectproduct t where t.project =#{srf.webcontext.project} ) ) 

```
### 任务计划（根计划）(TaskPlanRoot)<div id="ProductPlan_TaskPlanRoot"></div>
```sql
SELECT
t1.`BEGIN`,
(case when t1.`begin` = '2030-01-01' then '待定' else t1.`begin` end) AS `BEGINSTR`,
t1.`BRANCH`,
((select count(t.id) FROM zt_bug t where (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0')) AS `BUGCNT`,
case when t1.`end` is null or t1.`end` = '0000-00-00' or t1.`end` = '1970-01-01' then '' when t1.`status` in('wait','doing') and t1.`end` < DATE_FORMAT(NOW(),'%Y-%m-%d') then CONCAT('','延期',TO_DAYS(NOW()) - TO_DAYS(t1.`end`),'天') ELSE '' end AS `DELAY`,
t1.`DELETED`,
(to_Days(t1.`END`) - to_Days(t1.`BEGIN`)) AS `DURATION`,
t1.`END`,
(case when t1.`end` = '2030-01-01' then '待定' else t1.`end` end) AS `ENDSTR`,
(select sum(t.estimate) from zt_story t where (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0' ) AS `ESTIMATECNT`,
(case when t1.`begin` = '2030-01-01' or t1.`end` = '2030-01-01' then 'on' else '' end) AS `FUTURE`,
t1.`ID`,
(case when t1.`end` > now() then '0' else '1' end) AS `ISEXPIRED`,
t1.`PARENT`,
t1.`ORDER`,
t1.`ORDER` AS `ORDERNUM`,
t11.`TITLE` AS `PARENTNAME`,
t1.`PRODUCT`,
t1.`STATUS`,
(case when (select COUNT(1) from zt_productplan t where t.deleted = '0' and t.parent = t1.id) > 0 then 'parent' else 'normal' end) AS `STATUSS`,
((select COUNT(t.id) from zt_story t where (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0' )) AS `STORYCNT`,
case when (t11.`TITLE` is not null) then CONCAT('/',t11.`TITLE`,'/',t1.`TITLE`) else CONCAT('/',t1.`TITLE`) end as TITLE ,case when t1.parent <= 0 then t1.`begin` else CONCAT_WS('',t11.`begin`,'-',t1.`begin`) end as BEGINTIME 
FROM `zt_productplan` t1 
LEFT JOIN `zt_productplan` t11 ON t1.`PARENT` = t11.`ID` 

WHERE t1.DELETED = '0' 
( t1.product in (select t.product from zt_projectproduct t where t.project =#{srf.webcontext.project} ) ) 
t1.`PARENT` <= 0 

```
### 默认（全部数据）(VIEW)<div id="ProductPlan_View"></div>
```sql
SELECT
t1.`BEGIN`,
t1.`BEGINACT`,
(case when t1.`begin` = '2030-01-01' then '待定' else t1.`begin` end) AS `BEGINSTR`,
t1.`BRANCH`,
((select count(t.id) FROM zt_bug t where  t.product = t1.product and  (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0')) AS `BUGCNT`,
case when t1.`end` is null or t1.`end` = '0000-00-00' or t1.`end` = '1970-01-01' then '' when t1.`status` in('wait','doing') and t1.`end` < DATE_FORMAT(NOW(),'%Y-%m-%d') then CONCAT('','延期',TO_DAYS(NOW()) - TO_DAYS(t1.`end`),'天') ELSE '' end AS `DELAY`,
t1.`DELETED`,
t1.`DESC`,
(to_Days(t1.`END`) - to_Days(t1.`BEGIN`)) AS `DURATION`,
t1.`END`,
t1.`ENDACT`,
(case when t1.`end` = '2030-01-01' then '待定' else t1.`end` end) AS `ENDSTR`,
(select sum(t.estimate) from zt_story t where  t.product = t1.product and  (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0' ) AS `ESTIMATECNT`,
(case when t1.`begin` = '2030-01-01' or t1.`end` = '2030-01-01' then 'on' else '' end) AS `FUTURE`,
t1.`ID`,
(case when t1.`end` > now() then '0' else '1' end) AS `ISEXPIRED`,
t1.`ORDER`,
t1.`ORDER` AS `ORDERNUM`,
t1.`PARENT`,
t11.`TITLE` AS `PARENTNAME`,
t1.`PRODUCT`,
t1.`STATUS`,
(case when (select COUNT(1) from zt_productplan t where t.deleted = '0' and t.parent = t1.id) > 0 then 'parent' else 'normal' end) AS `STATUSS`,
((select COUNT(t.id) from zt_story t where  t.product = t1.product and  (t.plan = t1.id or (t.plan in (select t2.id from zt_productplan t2 where t2.parent = t1.id and t2.deleted = '0')) )  and t.deleted = '0' )) AS `STORYCNT`,
t1.`TITLE`
FROM `zt_productplan` t1 
LEFT JOIN `zt_productplan` t11 ON t1.`PARENT` = t11.`ID` 

WHERE t1.DELETED = '0' 

```

# **产品统计**(IBZ_PRODUCTSTATS)

### DEFAULT(DEFAULT)<div id="ProductStats_Default"></div>
```sql
SELECT
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` = 'active' AND DELETED = '0' ) AS `ACTIVEBUGCNT`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS` = 'active' AND DELETED = '0' ) AS `ACTIVESTORYCNT`,
	1 AS `ASSIGNTOMEBUGCNT`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND DELETED = '0' ) AS `BUGCNT`,
	t1.`CODE`,
	(
SELECT
	t.NAME 
FROM
	zt_project t,
	zt_projectproduct t2 
WHERE
	t.id = t2.project 
	AND t2.product = t1.id 
	LIMIT 1 
	) AS `CURRPROJECT`,
	t1.`DELETED`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND STAGE = 'developing' AND DELETED = '0' ) AS `DEVELOPINGSTORYCNT`,
	t1.`ID`,
	t1.`NAME`,
	( SELECT COUNT( 1 ) FROM ZT_RELEASE WHERE PRODUCT = t1.`ID` AND `STATUS` = 'normal' AND DELETED = '0' ) AS `NORMALRELEASECNT`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` <> 'closed' AND DELETED = '0' ) AS `NOTCLOSEDBUGCNT`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND STAGE = 'planned' AND DELETED = '0' ) AS `PLANNEDSTORYCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_PROJECT t2
	LEFT JOIN ZT_PROJECTPRODUCT t ON t2.ID = t.project 
WHERE
	t.PRODUCT = t1.id 
	AND t2.DELETED = '0' 
	AND t2.`status` IN ( 'wait', 'doing' ) 
	AND t2.`END` < now( ) 
	) AS `POSTPONEDPROJECTCNT`,
	( SELECT COUNT( 1 ) FROM ZT_PRODUCTPLAN WHERE PRODUCT = t1.`ID` AND DELETED = '0' ) AS `PRODUCTPLANCNT`,
	( SELECT COUNT( 1 ) FROM ZT_RELEASE WHERE PRODUCT = t1.`ID` AND DELETED = '0' ) AS `RELEASECNT`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND STAGE = 'released' AND DELETED = '0' ) AS `RELEASEDSTORYCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_PROJECT
	LEFT JOIN ZT_PROJECTPRODUCT ON ZT_PROJECT.ID = ZT_PROJECTPRODUCT.project 
WHERE
	PRODUCT = t1.`ID` 
	AND DELETED = '0' 
	) AS `RESPROJECTCNT`,
	t1.`STATUS`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND DELETED = '0' ) AS `STORYCNT`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND STAGE = 'testing' AND DELETED = '0' ) AS `TESTINGSTORYCNT`,
	t1.`TYPE`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `CONFIRMED` = 0 AND DELETED = '0' ) AS `UNCONFIRMBUGCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_PROJECT
	LEFT JOIN ZT_PROJECTPRODUCT ON ZT_PROJECT.ID = ZT_PROJECTPRODUCT.project 
WHERE
	PRODUCT = t1.`ID` 
	AND `STATUS` <> 'closed' 
	AND DELETED = '0' 
	) AS `UNDONERESPROJECTCNT`,
	( SELECT COUNT( 1 ) FROM ZT_PRODUCTPLAN WHERE PRODUCT = t1.`ID` AND `END` > CURDATE( ) AND DELETED = '0' ) AS `UNENDPRODUCTPLANCNT`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND STAGE = 'wait' AND DELETED = '0' ) AS `WAITSTORYCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_BUG 
WHERE
	PRODUCT = t1.`ID` 
	AND DELETED = '0' 
	AND resolvedDate BETWEEN CONCAT(
	YEAR ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	MONTH ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	DAY ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	' 00:00:00' 
	) 
	AND CONCAT(
	YEAR ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	MONTH ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	DAY ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	' 23:59:59' 
	) 
	) AS `YESTERDAYCLOSEDBUGCNT`,
	5 AS `YESTERDAYCONFIRMBUGCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_BUG 
WHERE
	PRODUCT = t1.`ID` 
	AND `STATUS` = 'closed' 
	AND DELETED = '0' 
	AND CLOSEDDATE BETWEEN CONCAT(
	YEAR ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	MONTH ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	DAY ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	' 00:00:00' 
	) 
	AND CONCAT(
	YEAR ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	MONTH ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	DAY ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	' 23:59:59' 
	) 
	) AS `YESTERDAYRESOLVEDBUGCNT` ,
		(CASE WHEN T2.OBJECTORDER IS NOT NULL THEN T2.OBJECTORDER ELSE  t1.`ORDER` END) as `ORDER1`,
	(CASE WHEN T2.OBJECTORDER IS NOT NULL THEN 1 ELSE 0 END) as `ISTOP`
FROM
	`zt_product` t1
	left join t_ibz_top t2 on t1.id = t2.OBJECTID and t2.type = 'product' and t2.ACCOUNT = #{srf.sessioncontext.srfloginname}
WHERE t1.DELETED = '0' 
(t1.id in(
	SELECT t.root from zt_team t
WHERE
	t.account =  #{srf.sessioncontext.srfloginname}
	AND t.type = 'product' 
	 
	) or t1.acl = 'open' )
	 
t1.orgid = #{srf.sessioncontext.srforgid} 

```
### 未关闭产品(NoOpenProduct)<div id="ProductStats_NoOpenProduct"></div>
```sql
SELECT
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` = 'active' AND DELETED = '0' ) AS `ACTIVEBUGCNT`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS` = 'active' AND DELETED = '0' ) AS `ACTIVESTORYCNT`,
	1 AS `ASSIGNTOMEBUGCNT`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND DELETED = '0' ) AS `BUGCNT`,
	t1.`CODE`,
	(
SELECT
	t.NAME 
FROM
	zt_project t,
	zt_projectproduct t2 
WHERE
	t.id = t2.project 
	AND t2.product = t1.id 
	LIMIT 1 
	) AS `CURRPROJECT`,
	t1.`DELETED`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND STAGE = 'developing' AND DELETED = '0' ) AS `DEVELOPINGSTORYCNT`,
	t1.`ID`,
	t1.`NAME`,
	( SELECT COUNT( 1 ) FROM ZT_RELEASE WHERE PRODUCT = t1.`ID` AND `STATUS` = 'normal' AND DELETED = '0' ) AS `NORMALRELEASECNT`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` <> 'closed' AND DELETED = '0' ) AS `NOTCLOSEDBUGCNT`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND STAGE = 'planned' AND DELETED = '0' ) AS `PLANNEDSTORYCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_PROJECT t2
	LEFT JOIN ZT_PROJECTPRODUCT t ON t2.ID = t.project 
WHERE
	t.PRODUCT = t1.id 
	AND t2.DELETED = '0' 
	AND t2.`status` IN ( 'wait', 'doing' ) 
	AND t2.`END` < now( ) 
	) AS `POSTPONEDPROJECTCNT`,
	( SELECT COUNT( 1 ) FROM ZT_PRODUCTPLAN WHERE PRODUCT = t1.`ID` AND DELETED = '0' ) AS `PRODUCTPLANCNT`,
	( SELECT COUNT( 1 ) FROM ZT_RELEASE WHERE PRODUCT = t1.`ID` AND DELETED = '0' ) AS `RELEASECNT`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND STAGE = 'released' AND DELETED = '0' ) AS `RELEASEDSTORYCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_PROJECT
	LEFT JOIN ZT_PROJECTPRODUCT ON ZT_PROJECT.ID = ZT_PROJECTPRODUCT.project 
WHERE
	PRODUCT = t1.`ID` 
	AND DELETED = '0' 
	) AS `RESPROJECTCNT`,
	t1.`STATUS`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND DELETED = '0' ) AS `STORYCNT`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND STAGE = 'testing' AND DELETED = '0' ) AS `TESTINGSTORYCNT`,
	t1.`TYPE`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `CONFIRMED` = 0 AND DELETED = '0' ) AS `UNCONFIRMBUGCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_PROJECT
	LEFT JOIN ZT_PROJECTPRODUCT ON ZT_PROJECT.ID = ZT_PROJECTPRODUCT.project 
WHERE
	PRODUCT = t1.`ID` 
	AND `STATUS` <> 'closed' 
	AND DELETED = '0' 
	) AS `UNDONERESPROJECTCNT`,
	( SELECT COUNT( 1 ) FROM ZT_PRODUCTPLAN WHERE PRODUCT = t1.`ID` AND `END` > CURDATE( ) AND DELETED = '0' ) AS `UNENDPRODUCTPLANCNT`,
	( SELECT COUNT( 1 ) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND STAGE = 'wait' AND DELETED = '0' ) AS `WAITSTORYCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_BUG 
WHERE
	PRODUCT = t1.`ID` 
	AND DELETED = '0' 
	AND resolvedDate BETWEEN CONCAT(
	YEAR ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	MONTH ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	DAY ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	' 00:00:00' 
	) 
	AND CONCAT(
	YEAR ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	MONTH ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	DAY ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	' 23:59:59' 
	) 
	) AS `YESTERDAYCLOSEDBUGCNT`,
	5 AS `YESTERDAYCONFIRMBUGCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_BUG 
WHERE
	PRODUCT = t1.`ID` 
	AND `STATUS` = 'closed' 
	AND DELETED = '0' 
	AND CLOSEDDATE BETWEEN CONCAT(
	YEAR ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	MONTH ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	DAY ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	' 00:00:00' 
	) 
	AND CONCAT(
	YEAR ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	MONTH ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	DAY ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	' 23:59:59' 
	) 
	) AS `YESTERDAYRESOLVEDBUGCNT` ,
		(CASE WHEN T2.OBJECTORDER IS NOT NULL THEN T2.OBJECTORDER ELSE  t1.`ORDER` END) as `ORDER1`,
	(CASE WHEN T2.OBJECTORDER IS NOT NULL THEN 1 ELSE 0 END) as `ISTOP`
FROM
	`zt_product` t1
	left join t_ibz_top t2 on t1.id = t2.OBJECTID and t2.type = 'product' and t2.ACCOUNT = #{srf.sessioncontext.srfloginname}
WHERE t1.DELETED = '0' 
( t1.`STATUS` = 'normal' ) 
(t1.id IN (
SELECT
	t.product 
FROM
	zt_projectproduct t 
WHERE
	t.project IN (
SELECT
	t3.root 
FROM
	zt_team t3 
WHERE
	t3.account = #{srf.sessioncontext.srfloginname} 
	AND t3.type = 'project' 
	) 
	) or  t1.acl = 'open') 

```
### 产品质量表(ProdctQuantiGird)<div id="ProductStats_ProdctQuantiGird"></div>
```sql
SELECT t1.id,t1.`name`,IFNULL(t2.storycnt,0) as storycnt,IFNULL(t2.finishedStory,0) as finishedStorycnt,IFNULL(t3.bugcnt,0) as bugcnt,IFNULL(t3.resolvedBugCnt,0) as resolvedBugCnt,IFNULL(t3.importantbugcnt,0) as importantbugcnt,IFNULL(t4.bugstory,0)  as bugstory , CONCAT(IFNULL(ROUND(t3.importantbugcnt/t3.bugcnt,2)*100,0),'%') as ImportantBugpercent
from zt_product t1 LEFT JOIN (
SELECT t1.product,t1.`name`,
IFNULL(COUNT(1),0) as storycnt ,
SUM(IF(t1.stage in ('tested','developing','developed'),t1.num,0)) as finishedStory
from (
select t1.`stage`,t1.closedReason,t1.id as storyid,t1.product,t2.`name`, 1 as num from zt_story t1 LEFT JOIN zt_product t2 on t1.product = t2.id where t2.id <> '0' and t1.deleted = '0' and t2.deleted = '0') t1 GROUP BY t1.product  ) t2 on t1.id = t2.product LEFT JOIN (
SELECT t1.product,t1.`name`,
IFNULL(COUNT(1),0) as bugcnt,
SUM(IF(t1.`STATUS` = 'closed' and t1.resolution in ('fixed'),t1.num,0)) as resolvedBugCnt,
SUM(IF(t1.pri >= 1 and t1.pri <= 3,t1.num,0)) as importantbugcnt from 
 (
SELECT t1.`STATUS`,t1.resolution,t1.pri,t1.id,t1.product,t2.`name`,1 as num from zt_bug t1 LEFT JOIN zt_product t2 on t1.product = t2.id where t2.id <> '0' and t1.deleted = '0' and t2.deleted = '0'
) t1 GROUP BY t1.product) t3 on t1.id = t3.product  LEFT JOIN (
SELECT t2.product,t2.`name`,COUNT(1) as bugstory from (
SELECT t1.story,t1.product,t2.`name`,1 as num from zt_bug t1 LEFT JOIN zt_product t2 on t1.product = t2.id where t1.story <> 0 and t1.deleted = '0' and t2.deleted = '0' ) t2
GROUP BY t2.product ) t4 on t1.id = t4.product where t1.deleted = '0'
```
### 产品投入表(ProductInputTable)<div id="ProductStats_ProductInputTable"></div>
```sql
SELECT t1.id,t1.`name`,t1.RESPROJECTCNT,ROUND(IFNULL(t2.haveconsumed,0),2)  as haveconsumed from (
SELECT t1.id,t1.`name`,COUNT(1) as RESPROJECTCNT from zt_product t1 LEFT JOIN zt_projectproduct t2 on t1.id = t2.product LEFT JOIN zt_project t3 on t3.id = t2.project 
where t3.deleted = '0' and t1.deleted = '0'
GROUP BY t1.id) t1 
LEFT JOIN (
SELECT t1.id,t1.`name`,SUM(t4.consumed) as haveconsumed from zt_product t1 LEFT JOIN zt_projectproduct t2 on t1.id = t2.product LEFT JOIN zt_project t3 on t3.id = t2.project LEFT JOIN zt_task 
t4 on t4.project = t3.id 
where t3.deleted = '0' and t1.deleted = '0' and t3.deleted = '0' and t4.parent >= 0
GROUP BY t1.id ) t2 on t1.id = t2.id

```
### 产品完成统计表(Productcompletionstatistics)<div id="ProductStats_Productcompletionstatistics"></div>
```sql
SELECT *,CONCAT(ROUND(t1.finishedstorycnt/t1.storycnt,2)*100,'%') as ImportantBugpercent from (
SELECT t1.product,t1.`name`,
IFNULL(COUNT(1),0) as storycnt ,
SUM(IF(t1.stage in ('tested','developing','developed'),t1.num,0)) as finishedStorycnt
from (
select t1.`stage`,t1.closedReason,t1.id as storyid,t1.product,t2.`name`, 1 as num from zt_story t1 LEFT JOIN zt_product t2 on t1.product = t2.id where t2.id <> '0' and t1.deleted = '0' and t2.deleted = '0') t1 GROUP BY t1.product   ) t1
```
### 默认（全部数据）(VIEW)<div id="ProductStats_View"></div>
```sql
SELECT
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` = 'active' AND DELETED ='0') AS `ACTIVEBUGCNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND `STATUS`='active' AND DELETED = '0') AS `ACTIVESTORYCNT`,
1 AS `ASSIGNTOMEBUGCNT`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND DELETED = '0') AS `BUGCNT`,
t1.`CODE`,
(SELECT t.name FROM zt_project t,zt_projectproduct t2 WHERE t.id=t2.project and t2.product = t1.id limit 1) AS `CURRPROJECT`,
t1.`DELETED`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND STAGE = 'developing' AND DELETED = '0') AS `DEVELOPINGSTORYCNT`,
t1.`ID`,
t1.`NAME`,
(SELECT COUNT(1) FROM ZT_RELEASE WHERE PRODUCT= t1.`ID` AND  `STATUS`= 'normal' AND DELETED = '0') AS `NORMALRELEASECNT`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` <> 'closed' AND DELETED = '0') AS `NOTCLOSEDBUGCNT`,
t1.`order` AS `ORDER1`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND STAGE = 'planned' AND DELETED ='0') AS `PLANNEDSTORYCNT`,
(SELECT COUNT(1) FROM ZT_PROJECT t2 LEFT JOIN ZT_PROJECTPRODUCT t ON t2.ID = t.project WHERE t.PRODUCT = t1.id AND t2.DELETED = '0' and t2.`status` in( 'wait','doing') and t2.`END` < now() ) AS `POSTPONEDPROJECTCNT`,
(SELECT COUNT(1) FROM ZT_PRODUCTPLAN WHERE PRODUCT= t1.`ID` AND DELETED = '0') AS `PRODUCTPLANCNT`,
(SELECT COUNT(1) FROM ZT_RELEASE WHERE PRODUCT= t1.`ID` AND DELETED = '0') AS `RELEASECNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND STAGE = 'released' AND DELETED = '0') AS `RELEASEDSTORYCNT`,
(SELECT COUNT(1) FROM ZT_PROJECT LEFT JOIN ZT_PROJECTPRODUCT ON  ZT_PROJECT.ID = ZT_PROJECTPRODUCT.project WHERE PRODUCT = t1.`ID` AND DELETED = '0') AS `RESPROJECTCNT`,
t1.`STATUS`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND DELETED = '0') AS `STORYCNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND STAGE = 'testing' AND DELETED = '0') AS `TESTINGSTORYCNT`,
t1.`TYPE`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `CONFIRMED` = 0 AND DELETED = '0') AS `UNCONFIRMBUGCNT`,
(SELECT COUNT(1) FROM ZT_PROJECT LEFT JOIN ZT_PROJECTPRODUCT ON  ZT_PROJECT.ID = ZT_PROJECTPRODUCT.project WHERE PRODUCT = t1.`ID` AND `STATUS` <> 'closed' AND DELETED = '0') AS `UNDONERESPROJECTCNT`,
(SELECT COUNT(1) FROM ZT_PRODUCTPLAN WHERE PRODUCT= t1.`ID`  AND `END` > CURDATE() AND DELETED = '0') AS `UNENDPRODUCTPLANCNT`,
(SELECT COUNT(1) FROM ZT_STORY WHERE PRODUCT = t1.`ID` AND STAGE = 'wait' AND DELETED = '0') AS `WAITSTORYCNT`,
(SELECT COUNT( 1 ) FROM ZT_BUG WHERE PRODUCT = t1.`ID`  AND DELETED = '0' and resolvedDate BETWEEN CONCAT(YEAR(DATE_ADD(now(),INTERVAL -1 day)),'-',month(DATE_ADD(now(),INTERVAL -1 day)),'-',day(DATE_ADD(now(),INTERVAL -1 day)),' 00:00:00') and CONCAT(YEAR(DATE_ADD(now(),INTERVAL -1 day)),'-',month(DATE_ADD(now(),INTERVAL -1 day)),'-',day(DATE_ADD(now(),INTERVAL -1 day)),' 23:59:59')) AS `YESTERDAYCLOSEDBUGCNT`,
5 AS `YESTERDAYCONFIRMBUGCNT`,
(SELECT COUNT( 1 ) FROM ZT_BUG WHERE PRODUCT = t1.`ID` AND `STATUS` = 'closed' AND DELETED = '0' and CLOSEDDATE BETWEEN CONCAT(YEAR(DATE_ADD(now(),INTERVAL -1 day)),'-',month(DATE_ADD(now(),INTERVAL -1 day)),'-',day(DATE_ADD(now(),INTERVAL -1 day)),' 00:00:00') and CONCAT(YEAR(DATE_ADD(now(),INTERVAL -1 day)),'-',month(DATE_ADD(now(),INTERVAL -1 day)),'-',day(DATE_ADD(now(),INTERVAL -1 day)),' 23:59:59')) AS `YESTERDAYRESOLVEDBUGCNT`
FROM `zt_product` t1 

WHERE t1.DELETED = '0' 

```

# **产品汇总表**(IBZ_PRODUCTSUM)

### 数据查询(DEFAULT)<div id="ProductSum_Default"></div>
```sql
select t1.*  from(select 
t1.id,t1.`NAME`,t1.po,t1.PLAN, case when t1.`begin` = '2030-01-01' then '待定' else t1.`begin` end as `begin`,case when t1.`end` = '2030-01-01' then '待定' else t1.`end` end as `end`,
	sum( IF ( t1.`status` = 'draft', t1.rowcnt, 0 ) ) AS WAITSTORYCNT,
	SUM( IF ( t1.`status` = 'active', t1.rowcnt, 0 ) ) AS ACTIVESTORYCNT,
	SUM( IF ( t1.`status` = 'changed', t1.rowcnt, 0 ) ) AS CHANGEDSTORYCNT,
	SUM( IF ( t1.`status` = 'closed', t1.rowcnt, 0 ) ) AS CLOSEDSTORYCNT,
	sum( IF ( t1.`deleted` = '0',  t1.rowcnt, 0) ) AS STORYCNT 
FROM
	(
	SELECT
		1 AS rowcnt,
	t41.id as plan,
		t31.`NAME` AS `NAME`,
		t31.id AS id,
		t1.`status`,
		t31.po,
		t41.`begin`,
		t41.`end` ,
		t1.deleted
	FROM
		zt_product t31
		LEFT JOIN ZT_PRODUCTPLAN t41 ON t41.PRODUCT = t31.ID
		LEFT JOIN  `zt_story` t1  ON t41.id = t1.plan 
where t31.deleted = '0' and  (
			( ( t31.`status` = 'normal' ) OR ( t31.`status` = 'closed' AND #{srf.datacontext.closed} = '1' ) ) 
			and ( t41.id is null or
				( t41.`end` >= now( ) ) 
				OR ( t41.`end` <= now( ) AND #{srf.datacontext.expired} = '1' ) 
			) 
		)
) t1 GROUP BY t1.id,t1.`NAME`,t1.po,t1.PLAN,t1.`begin`,t1.`end`) t1
```
### 产品创建bug数_测试人员(ProductBugcnt_QA)<div id="ProductSum_ProductBugcnt_QA"></div>
```sql
SELECT t1.productid as id,t1.productname as `name`,t1.createbugcnt as bugcnt,t1.createbugcnt as storycnt,t2.productallbug, CONCAT(ROUND(t1.createbugcnt/(case when t2.productallbug = 0 or t2.productallbug is null then 1 else t2.productallbug end)*100,1),'%') as zhanbi, t1.openedBy from (
SELECT t1.openedBy,t1.productid,t1.productname,COUNT(1) as createbugcnt from (
SELECT t1.id ,t1.openedBy,t2.id as productid,t1.product,t2.`name` as productname from zt_bug t1
LEFT JOIN zt_product t2 on t2.id = t1.product where YEAR(t2.createdDate) = #{srf.webcontext.curyear}
) t1 GROUP BY t1.openedBy,t1.productid  )t1
LEFT JOIN ( select t1.productid,t1.productname,count(1) as productallbug from (
SELECT t1.id ,t1.openedBy,t2.id as productid,t1.product,t2.`name` as productname from zt_bug t1
LEFT JOIN zt_product t2 on t2.id = t1.product
) t1 GROUP BY t1.productid ) t2 on t2.productid = t1.productid HAVING t1.openedBy = 
 #{srf.sessioncontext.srfloginname}
```
### 产品创建需求占比(ProductCreateStory)<div id="ProductSum_ProductCreateStory"></div>
```sql
	SELECT
0 AS `ACTIVESTORYCNT`,
0 AS `CHANGEDSTORYCNT`,
0 AS `CLOSEDSTORYCNT`,
t1.`ID`,
t1.`NAME`,
0 AS `PLAN`,
t1.`PO`,
t2.storycnt AS `STORYCNT`,
0 AS `WAITSTORYCNT`,
t2.zhanbi
FROM `zt_product` t1

INNER JOIN (
SELECT t1.*,IFNULL(t2.storycnt,0) as storycnt,IFNULL(t2.allstorycnt,0) as allstorycnt,IFNULL(t2.zhanbi,0) as zhanbi from (
SELECT t1.id,t1.`name`,t1.`status` from zt_product t1 where t1.id in (SELECT t1.id from zt_product t1 where YEAR(t1.createddate) =   #{srf.webcontext.curyear} 
and (t1.createdBy =  #{srf.sessioncontext.srfloginname} 
or t1.po =  #{srf.sessioncontext.srfloginname} 
or t1.QD =  #{srf.sessioncontext.srfloginname} 
or t1.RD =  #{srf.sessioncontext.srfloginname} )) 
or t1.id in (SELECT DISTINCT t1.product from zt_story t1 where t1.openedBy =  #{srf.sessioncontext.srfloginname} 
and YEAR(t1.openeddate) =   #{srf.webcontext.curyear}) 
or t1.id in (SELECT DISTINCT t1.product from zt_productplan t1 LEFT JOIN zt_action t2 on t1.id = t2.OBJECTID and t2.objectType = 'productplan' where YEAR(t2.date) =   #{srf.webcontext.curyear} 
and t2.actor =  #{srf.sessioncontext.srfloginname} 
and t2.action = 'opened' ) ) t1

LEFT JOIN (
select t1.*,t2.allstorycnt , CONCAT(ROUND(t1.storycnt/(case when t2.allstorycnt = 0 or t2.allstorycnt is null then 1 else t2.allstorycnt end)*100,1),'%') as zhanbi from
(SELECT t1.openedBy,t1.productid,t1.productname,COUNT(1) as storycnt from (
SELECT t1.id,t1.openedBy,t2.id as productid ,t2.`name` as productname from zt_story t1 LEFT JOIN zt_product t2 on t2.id = t1.product) t1 GROUP BY t1.openedBy,t1.productid ) t1
LEFT JOIN (select t1.productid,t1.productname,COUNT(1) as allstorycnt from (
SELECT t1.id,t1.openedBy,t2.id as productid ,t2.`name` as productname from zt_story t1 LEFT JOIN zt_product t2 on t2.id = t1.product ) t1 GROUP BY t1.productid) t2 on t2.productid = t1.productid

having t1.openedBy =  #{srf.sessioncontext.srfloginname}
) t2 on t1.id = t2.productid
) t2 on t1.id = t2.id
```
### 产品计划数和需求数_产品经理(ProductPlancntAndStorycnt_PO)<div id="ProductSum_ProductPlancntAndStorycnt_PO"></div>
```sql
SELECT 0 AS `ACTIVESTORYCNT`, 0 AS `CHANGEDSTORYCNT`, 0 AS `CLOSEDSTORYCNT`, t1.`ID`, t1.`NAME`, t2.plan AS `PLAN`, t1.`PO`, t2.storycnt AS `STORYCNT`, t2.plan AS `WAITSTORYCNT`, t2.zhanbi FROM `zt_product` t1 INNER JOIN ( SELECT t1.*,IFNULL(t2.storycnt,0) as storycnt,IFNULL(t2.allstorycnt,0) as allstorycnt,IFNULL(t2.zhanbi,0) as zhanbi,IFNULL(t3.plancnt,0) as plan from ( SELECT t1.id,t1.`name`,t1.`status` from zt_product t1 where t1.id in (SELECT t1.id from zt_product t1 where YEAR(t1.createddate) =   #{srf.webcontext.curyear}  and (t1.createdBy = #{srf.sessioncontext.srfloginname} or t1.po = #{srf.sessioncontext.srfloginname} or t1.QD = #{srf.sessioncontext.srfloginname} or t1.RD = #{srf.sessioncontext.srfloginname} )) or t1.id in (SELECT DISTINCT t1.product from zt_story t1 where t1.openedBy = #{srf.sessioncontext.srfloginname} and YEAR(t1.openeddate) =   #{srf.webcontext.curyear} ) or t1.id in (SELECT DISTINCT t1.product from zt_productplan t1 LEFT JOIN zt_action t2 on t1.id = t2.OBJECTID and t2.objectType = 'productplan' where YEAR(t2.date) =   
#{srf.webcontext.curyear}  and t2.actor = #{srf.sessioncontext.srfloginname} and t2.action = 'opened' ) ) t1 LEFT JOIN ( select t1.*,t2.allstorycnt , CONCAT(ROUND(t1.storycnt/(case when t2.allstorycnt = 0 or t2.allstorycnt is null then 1 else t2.allstorycnt end)*100,1),'%') as zhanbi from (SELECT t1.openedBy,t1.productid,t1.productname,COUNT(1) as storycnt from ( SELECT t1.id,t1.openedBy,t2.id as productid ,t2.`name` as productname from zt_story t1 LEFT JOIN zt_product t2 on t2.id = t1.product) t1 GROUP BY t1.openedBy,t1.productid ) t1 LEFT JOIN (select t1.productid,t1.productname,COUNT(1) as allstorycnt from ( SELECT t1.id,t1.openedBy,t2.id as productid ,t2.`name` as productname from zt_story t1 LEFT JOIN zt_product t2 on t2.id = t1.product ) t1 GROUP BY t1.productid) t2 on t2.productid = t1.productid having t1.openedBy = #{srf.sessioncontext.srfloginname} ) t2 on t1.id = t2.productid LEFT JOIN ( SELECT t1.productid,t1.productname,count(1) as plancnt from ( select t1.id,t2.id as productid,t2.`name` as productname from zt_productplan t1 LEFT JOIN zt_product t2 on t2.id = t1.product where t1.parent >= 0 and t1.`begin` >= (CONCAT( YEAR(NOW()),'-01-01',' 00:00:00')) and t1.`end` <= (CONCAT( YEAR(NOW()),'-12-31',' 23:59:59')) and t1.parent >= '0' ) t1 GROUP BY t1.productid ) t3 on t3.productid = t1.id ) t2 on t2.id = t1.id
```
### 产品需求工时汇总(ProductStoryHoursSum)<div id="ProductSum_ProductStoryHoursSum"></div>
```sql
select t1.`id`, t1.`name`, t1.`po`, 
sum( IF ( t1.`stage` = 'closed' and t1.`parent` <> -1 and t1.`deleted` = '0', t1.`estimate`, 0 ) ) AS `CLOSEDSTAGESTORYHOURS`, 
sum( IF ( t1.`stage` = 'released' and t1.`parent` <> -1 and t1.`deleted` = '0', t1.`estimate`, 0 ) ) AS `RELEASEDSTAGESTORYHOURS`, 
sum( IF ( t1.`stage` = 'verified' and t1.`parent` <> -1 and t1.`deleted` = '0', t1.`estimate`, 0 ) ) AS `VERIFIEDSTAGESTORYHOURS`, 
sum( IF ( t1.`stage` = 'tested' and t1.`parent` <> -1 and t1.`deleted` = '0', t1.`estimate`, 0 ) ) AS `TESTEDSTAGESTORYHOURS`, 
sum( IF ( t1.`stage` = 'testing' and t1.`parent` <> -1 and t1.`deleted` = '0', t1.`estimate`, 0 ) ) AS `TESTINGSTAGESTORYHOURS`, 
sum( IF ( t1.`stage` = 'developed' and t1.`parent` <> -1 and t1.`deleted` = '0', t1.`estimate`, 0 ) ) AS `DEVELOPEDSTAGESTORYHOURS`, 
sum( IF ( t1.`stage` = 'developing' and t1.`parent` <> -1 and t1.`deleted` = '0', t1.`estimate`, 0 ) ) AS `DEVELOPINGSTAGESTORYHOURS`, 
sum( IF ( t1.`stage` = 'projected' and t1.`parent` <> -1 and t1.`deleted` = '0', t1.`estimate`, 0 ) ) AS `PROJECTEDSTAGESTORYHOURS`, 
sum( IF ( t1.`stage` = 'planed' and t1.`parent` <> -1 and t1.`deleted` = '0', t1.`estimate`, 0 ) ) AS `PLANEDSTAGESTORYHOURS`, 
sum( IF ( t1.`stage` = 'wait' and t1.`parent` <> -1 and t1.`deleted` = '0', t1.`estimate`, 0 ) ) AS `WAITSTAGESTORYHOURS`, 
sum( IF ( t1.`deleted` = '0' and t1.`parent` <> -1, t1.`estimate`, 0 ) ) AS `TOTALHOURS` 
from (select t1.`id`, t1.`name`, t1.`po`, t1.`status`, t2.`parent`, t2.`stage`, t2.`estimate`, t2.`deleted` from zt_product t1 left join zt_story t2 on t1.`id` = t2.`product` where t1.`deleted`= '0') t1
where t1.`status` = 'normal' or (t1.`status` = 'closed' and #{srf.datacontext.closed} = '1')
group by t1.`id`
```
### 产品需求汇总查询(ProductStorySum)<div id="ProductSum_ProductStorySum"></div>
```sql
select t1.`id`, t1.`name`, t1.`po`, 
sum( IF ( t1.`stage` = 'closed' and t1.`parent` <> -1 and t1.`deleted` = '0', t1.`rowcnt`, 0 ) ) AS `CLOSEDSTAGESTORYCNT`, 
sum( IF ( t1.`stage` = 'released' and t1.`parent` <> -1 and t1.`deleted` = '0', t1.`rowcnt`, 0 ) ) AS `RELEASEDSTAGESTORYCNT`, 
sum( IF ( t1.`stage` = 'verified' and t1.`parent` <> -1 and t1.`deleted` = '0', t1.`rowcnt`, 0 ) ) AS `VERIFIEDSTAGESTORYCNT`, 
sum( IF ( t1.`stage` = 'tested' and t1.`parent` <> -1 and t1.`deleted` = '0', t1.`rowcnt`, 0 ) ) AS `TESTEDSTAGESTORYCNT`, 
sum( IF ( t1.`stage` = 'testing' and t1.`parent` <> -1 and t1.`deleted` = '0', t1.`rowcnt`, 0 ) ) AS `TESTINGSTAGESTORYCNT`, 
sum( IF ( t1.`stage` = 'developed' and t1.`parent` <> -1 and t1.`deleted` = '0', t1.`rowcnt`, 0 ) ) AS `DEVELOPEDSTAGESTORYCNT`, 
sum( IF ( t1.`stage` = 'developing' and t1.`parent` <> -1 and t1.`deleted` = '0', t1.`rowcnt`, 0 ) ) AS `DEVELOPINGSTAGESTORYCNT`, 
sum( IF ( t1.`stage` = 'projected' and t1.`parent` <> -1 and t1.`deleted` = '0', t1.`rowcnt`, 0 ) ) AS `PROJECTEDSTAGESTORYCNT`, 
sum( IF ( t1.`stage` = 'planed' and t1.`parent` <> -1 and t1.`deleted` = '0', t1.`rowcnt`, 0 ) ) AS `PLANEDSTAGESTORYCNT`, 
sum( IF ( t1.`stage` = 'wait' and t1.`parent` <> -1 and t1.`deleted` = '0', t1.`rowcnt`, 0 ) ) AS `WAITSTAGESTORYCNT`, 
sum( IF ( t1.`deleted` = '0' and t1.`parent` <> -1, t1.`rowcnt`, 0 ) ) AS `STORYCNT` 
from (select t1.`id`, t1.`name`, t1.`po`, t1.`status`, t2.`parent`, t2.`stage`, t2.`deleted`, 1 as `rowcnt` from zt_product t1 left join zt_story t2 on t1.`id` = t2.`product` where t1.`deleted` = '0') t1 
where t1.`status` = 'normal' or (t1.`status` = 'closed' and #{srf.datacontext.closed} = '1')
group by t1.`id`
```
### 产品Bug类型统计(ProductSumBugType)<div id="ProductSum_ProductSumBugType"></div>
```sql
SELECT
	t1.*,
	SUM(
	IFNULL(t1.codeerror,0)  + IFNULL(t1.config,0) + IFNULL(t1.designdefect,0) +IFNULL(t1.standard,0)  + IFNULL(t1.performance,0) + IFNULL(t1.others,0) +IFNULL(t1.INSTALL,0)  + IFNULL(t1.automation,0) +IFNULL(t1.SECURITY ,0)
	) AS bugsum 
FROM
	(
SELECT
	t1.id,
	t1.NAME,
	t1.po,
	sum( IF ( t1.type = 'codeerror' AND t1.deleted = '0', t1.rowcnt, 0 ) ) AS 'codeerror',
	sum( IF ( t1.type = 'config' AND t1.deleted = '0', t1.rowcnt, 0 ) ) AS 'config',
	sum( IF ( t1.type = 'designdefect' AND t1.deleted = '0', t1.rowcnt, 0 ) ) AS 'designdefect',
	sum( IF ( t1.type = 'standard' AND t1.deleted = '0', t1.rowcnt, 0 ) ) AS 'standard',
	sum( IF ( t1.type = 'performance' AND t1.deleted = '0', t1.rowcnt, 0 ) ) AS 'performance',
	sum( IF ( t1.type = 'others' AND t1.deleted = '0', t1.rowcnt, 0 ) ) AS 'others',
	sum( IF ( t1.type = 'install' AND t1.deleted = '0', t1.rowcnt, 0 ) ) AS 'install',
	sum( IF ( t1.type = 'automation' AND t1.deleted = '0', t1.rowcnt, 0 ) ) AS 'automation',
	sum( IF ( t1.type = 'security' AND t1.deleted = '0', t1.rowcnt, 0 ) ) AS 'security' 
FROM
	(
SELECT
	t1.`id`,
	t1.`name`,
	t1.`po`,
	t1.`status`,
	t2.`type`,
	t2.`title`,
	t2.`deleted`,
	1 AS `rowcnt` 
FROM
	zt_product t1
	LEFT JOIN zt_bug t2 ON t1.`id` = t2.`product` 
WHERE
	t1.`deleted` = '0' 
	) t1 
WHERE
	t1.`status` = 'normal' 
	OR ( t1.`status` = 'closed' AND #{srf.datacontext.closed} = '1' ) 
GROUP BY
	t1.id 
	) t1 
GROUP BY
	t1.id
```
### 默认（全部数据）(VIEW)<div id="ProductSum_View"></div>
```sql
SELECT
0 AS `ACTIVESTORYCNT`,
0 AS `BUGCNT`,
0 AS `CHANGEDSTORYCNT`,
0 AS `CLOSEDSTAGESTORYCNT`,
0 AS `CLOSEDSTAGESTORYHOURS`,
0 AS `CLOSEDSTORYCNT`,
0 AS `DEVELOPEDSTAGESTORYCNT`,
0 AS `DEVELOPEDSTAGESTORYHOURS`,
0 AS `DEVELOPINGSTAGESTORYCNT`,
0 AS `DEVELOPINGSTAGESTORYHOURS`,
t1.`ID`,
t1.`NAME`,
0 AS `PLAN`,
0 AS `PLANEDSTAGESTORYCNT`,
0 AS `PLANEDSTAGESTORYHOURS`,
t1.`PO`,
0 AS `PROJECTEDSTAGESTORYCNT`,
0 AS `PROJECTEDSTAGESTORYHOURS`,
0 AS `RELEASEDSTAGESTORYCNT`,
0 AS `RELEASEDSTAGESTORYHOURS`,
0 AS `STORYCNT`,
0 AS `TESTEDSTAGESTORYCNT`,
0 AS `TESTEDSTAGESTORYHOURS`,
0 AS `TESTINGSTAGESTORYCNT`,
0 AS `TESTINGSTAGESTORYHOURS`,
0 AS `TOTALHOURS`,
0 AS `VERIFIEDSTAGESTORYCNT`,
0 AS `VERIFIEDSTAGESTORYHOURS`,
0 AS `WAITSTAGESTORYCNT`,
0 AS `WAITSTAGESTORYHOURS`,
0 AS `WAITSTORYCNT`
FROM `zt_product` t1 

```

# **项目**(ZT_PROJECT)

### 所有项目(AllProject)<div id="Project_AllProject"></div>
```sql
SELECT t1.id,t1.name FROM zt_project t1
```
### Bug表单中可选的项目列表(BugSelectableProjectList)<div id="Project_BugSelectableProjectList"></div>
```sql
SELECT
t1.`ACL`,
t1.`BEGIN`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `BUGCNT`,
t1.`CANCELEDBY`,
t1.`CANCELEDDATE`,
t1.`CATID`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CODE`,
t1.`DAYS`,
t1.`DELETED`,
t1.`END`,
t1.`ID`,
t1.`ISCAT`,
'0' AS `ISTOP`,
t1.`MDEPTID`,
t1.`NAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OPENEDVERSION`,
t1.`ORDER`,
t1.`order` AS `ORDER1`,
t1.`ORGID`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PM`,
t1.`PO`,
t1.`PRI`,
t1.`QD`,
t1.`RD`,
t1.`STATGE`,
t1.`STATUS`,
(SELECT COUNT(1) FROM ZT_STORY LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `STORYCNT`,
t1.`SUBSTATUS`,
t1.`SUPPROREPORT`,
(SELECT COUNT(1) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `TASKCNT`,
t1.`TEAM`,
(SELECT round(SUM(CONSUMED),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALCONSUMED`,
(SELECT round(SUM(ESTIMATE),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED =  '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALESTIMATE`,
(select sum(days * hours)  from zt_team tt where type = 'project' and root = t1.id) AS `TOTALHOURS`,
(SELECT round(SUM(`LEFT`),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' and `status` in ('doing','wait','pause') AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALLEFT`,
((SELECT round(SUM( `LEFT` ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) AND `status` in ('doing','wait','pause')) + (SELECT round(SUM( CONSUMED ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ))) AS `TOTALWH`,
t1.`TYPE`
FROM `zt_project` t1 
LEFT JOIN `zt_project` t11 ON t1.`PARENT` = t11.`ID` 
LEFT OUTER JOIN `zt_projectproduct` t21 ON t1.`ID` = t21.`PROJECT` 

WHERE ( ( ${srfwebcontext('product','{"defname":"PRODUCT","dename":"ZT_PROJECTPRODUCT"}')} is null  OR  t21.`PRODUCT` = ${srfwebcontext('product','{"defname":"PRODUCT","dename":"ZT_PROJECTPRODUCT"}')} ) ) 
t1.DELETED = '0' 

```
### 当前人的所有项目(CurPersonalProjects)<div id="Project_CurPersonalProjects"></div>
```sql
SELECT t1.id, t1.NAME,t2.product FROM zt_project t1 left join zt_projectproduct t2 on t1.id = t2.project

WHERE t2.product IN ( SELECT t1.root FROM zt_team t1 WHERE t1.type = 'product' 
AND t1.account =#{srf.datacontext.account} ) 
	OR t1.pm =#{srf.datacontext.account} or t1.id = 0 

```
### 当前计划项目(CurPlanProject)<div id="Project_CurPlanProject"></div>
```sql
SELECT
t1.`ACL`,
t1.`BEGIN`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `BUGCNT`,
t1.`CANCELEDBY`,
t1.`CANCELEDDATE`,
t1.`CATID`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CODE`,
t1.`DAYS`,
t1.`DELETED`,
t1.`END`,
t1.`ID`,
t1.`ISCAT`,
'0' AS `ISTOP`,
t1.`MDEPTID`,
t1.`NAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OPENEDVERSION`,
t1.`ORDER`,
t1.`order` AS `ORDER1`,
t1.`ORGID`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PM`,
t1.`PO`,
t1.`PRI`,
t1.`QD`,
t1.`RD`,
t1.`STATGE`,
t1.`STATUS`,
(SELECT COUNT(1) FROM ZT_STORY LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `STORYCNT`,
t1.`SUBSTATUS`,
t1.`SUPPROREPORT`,
(SELECT COUNT(1) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `TASKCNT`,
t1.`TEAM`,
(SELECT round(SUM(CONSUMED),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALCONSUMED`,
(SELECT round(SUM(ESTIMATE),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED =  '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALESTIMATE`,
(select sum(days * hours)  from zt_team tt where type = 'project' and root = t1.id) AS `TOTALHOURS`,
(SELECT round(SUM(`LEFT`),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' and `status` in ('doing','wait','pause') AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALLEFT`,
((SELECT round(SUM( `LEFT` ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) AND `status` in ('doing','wait','pause')) + (SELECT round(SUM( CONSUMED ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ))) AS `TOTALWH`,
t1.`TYPE`
FROM `zt_project` t1 
LEFT JOIN `zt_project` t11 ON t1.`PARENT` = t11.`ID` 
LEFT OUTER JOIN `zt_projectproduct` t21 ON t1.`ID` = t21.`PROJECT` 

WHERE ( t21.`PLAN` = ${srfwebcontext('plan','{"defname":"PLAN","dename":"ZT_PROJECTPRODUCT"}')} ) 
t1.DELETED = '0' 

```
### 当前项目(CurProduct)<div id="Project_CurProduct"></div>
```sql
SELECT
t1.`ACL`,
t1.`BEGIN`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `BUGCNT`,
t1.`CANCELEDBY`,
t1.`CANCELEDDATE`,
t1.`CATID`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CODE`,
t1.`DAYS`,
t1.`DELETED`,
t1.`END`,
t1.`ID`,
t1.`ISCAT`,
'0' AS `ISTOP`,
t1.`MDEPTID`,
t1.`NAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OPENEDVERSION`,
t1.`ORDER`,
t1.`order` AS `ORDER1`,
t1.`ORGID`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PM`,
t1.`PO`,
t1.`PRI`,
t1.`QD`,
t1.`RD`,
t1.`STATGE`,
t1.`STATUS`,
(SELECT COUNT(1) FROM ZT_STORY LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `STORYCNT`,
t1.`SUBSTATUS`,
t1.`SUPPROREPORT`,
(SELECT COUNT(1) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `TASKCNT`,
t1.`TEAM`,
(SELECT round(SUM(CONSUMED),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALCONSUMED`,
(SELECT round(SUM(ESTIMATE),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED =  '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALESTIMATE`,
(select sum(days * hours)  from zt_team tt where type = 'project' and root = t1.id) AS `TOTALHOURS`,
(SELECT round(SUM(`LEFT`),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' and `status` in ('doing','wait','pause') AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALLEFT`,
((SELECT round(SUM( `LEFT` ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) AND `status` in ('doing','wait','pause')) + (SELECT round(SUM( CONSUMED ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ))) AS `TOTALWH`,
t1.`TYPE`
FROM `zt_project` t1 
LEFT JOIN `zt_project` t11 ON t1.`PARENT` = t11.`ID` 

WHERE EXISTS(SELECT * FROM `zt_projectproduct` t21 
 WHERE 
 t1.`ID` = t21.`PROJECT`  AND  ( t21.`PRODUCT` = ${srfdatacontext('n_product_eq','{"defname":"PRODUCT","dename":"ZT_PROJECTPRODUCT"}')} ) ) 
t1.DELETED = '0' 

```
### 当前用户项目(CurUser)<div id="Project_CurUser"></div>
```sql
select t1.* from (SELECT
t1.MDEPTID,
t1.orgid,
t1.`ACL`,
t1.`BEGIN`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `BUGCNT`,
t1.`CANCELEDBY`,
(select count(1) + 1 from zt_doclib where type = 'project' and project = t1.`id`) as `DOCLIBCNT`,
t1.`CANCELEDDATE`,
t1.`CATID`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CODE`,
t1.`DAYS`,
t1.`DELETED`,
t1.`END`,
t1.`ID`,
t1.`ISCAT`,
t1.`NAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OPENEDVERSION`,
t1.`ORDER`,
(CASE WHEN T2.OBJECTORDER IS NOT NULL THEN T2.OBJECTORDER ELSE  t1.`ORDER` END) as `ORDER1`,
	(CASE WHEN T2.OBJECTORDER IS NOT NULL THEN 1 ELSE 0 END) as `ISTOP`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PM`,
t1.`PO`,
t1.`PRI`,
t1.`QD`,
t1.`RD`,
t1.`STATGE`,
t1.`STATUS`,
(SELECT COUNT(1) FROM ZT_STORY LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `STORYCNT`,
t1.`SUBSTATUS`,
(SELECT COUNT(1) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `TASKCNT`,
t1.`TEAM`,
(SELECT round(SUM(CONSUMED),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALCONSUMED`,
(SELECT round(SUM(ESTIMATE),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED =  '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALESTIMATE`,
(select sum(days * hours)  from zt_team tt where type = 'project' and root = t1.id) AS `TOTALHOURS`,
(SELECT round(SUM(`LEFT`),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' and `status` in ('doing','wait','pause') AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALLEFT`,
((SELECT round(SUM( `LEFT` ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) AND `status` in ('doing','wait','pause')) + (SELECT round(SUM( CONSUMED ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ))) AS `TOTALWH`,
t1.`TYPE`
FROM `zt_project` t1 
left join t_ibz_top t2 on t1.id = t2.OBJECTID and t2.type = 'project' and t2.ACCOUNT = #{srf.sessioncontext.srfloginname}
LEFT JOIN zt_project t11 ON t1.PARENT = t11.ID 
where t1.deleted = '0' and (t1.acl = 'open' or t1.OPENEDBY = #{srf.sessioncontext.srfloginname} or  t1.pm =  #{srf.sessioncontext.srfloginname} or t1.PO = #{srf.sessioncontext.srfloginname} or t1.RD = #{srf.sessioncontext.srfloginname} or t1.QD =  #{srf.sessioncontext.srfloginname} )
union 
SELECT
t1.MDEPTID,
t1.orgid,
t1.`ACL`,
t1.`BEGIN`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `BUGCNT`,
t1.`CANCELEDBY`,
(select count(1) + 1 from zt_doclib where type = 'project' and project = t1.`id`) as `DOCLIBCNT`,
t1.`CANCELEDDATE`,
t1.`CATID`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CODE`,
t1.`DAYS`,
t1.`DELETED`,
t1.`END`,
t1.`ID`,
t1.`ISCAT`,
t1.`NAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OPENEDVERSION`,
t1.`ORDER`,
(CASE WHEN T2.OBJECTORDER IS NOT NULL THEN T2.OBJECTORDER ELSE  t1.`ORDER` END) as `ORDER1`,
	(CASE WHEN T2.OBJECTORDER IS NOT NULL THEN 1 ELSE 0 END) as `ISTOP`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PM`,
t1.`PO`,
t1.`PRI`,
t1.`QD`,
t1.`RD`,
t1.`STATGE`,
t1.`STATUS`,
(SELECT COUNT(1) FROM ZT_STORY LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `STORYCNT`,
t1.`SUBSTATUS`,
(SELECT COUNT(1) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `TASKCNT`,
t1.`TEAM`,
(SELECT round(SUM(CONSUMED),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALCONSUMED`,
(SELECT round(SUM(ESTIMATE),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED =  '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALESTIMATE`,
(select sum(days * hours)  from zt_team tt where type = 'project' and root = t1.id) AS `TOTALHOURS`,
(SELECT round(SUM(`LEFT`),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' and `status` in ('doing','wait','pause') AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALLEFT`,
((SELECT round(SUM( `LEFT` ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) AND `status` in ('doing','wait','pause')) + (SELECT round(SUM( CONSUMED ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ))) AS `TOTALWH`,
t1.`TYPE`
FROM `zt_project` t1 
left join t_ibz_top t2 on t1.id = t2.OBJECTID and t2.type = 'project' and t2.ACCOUNT = #{srf.sessioncontext.srfloginname}
LEFT JOIN zt_project t11 ON t1.PARENT = t11.ID 
where t1.deleted = '0' and t1.acl = 'private' and t1.id in (select t3.root from zt_team t3 where t3.account = #{srf.sessioncontext.srfloginname}  
and t3.type = 'project')) t1
WHERE t1.orgid = #{srf.sessioncontext.srforgid} 

```
### 当前用户项目（企业版）(CurUserSa)<div id="Project_CurUserSa"></div>
```sql
SELECT
	t1.* 
FROM
	(
SELECT
	( SELECT tt.product FROM zt_projectproduct tt WHERE tt.project = t1.id LIMIT 0, 1 ) AS products,
	t1.MDEPTID,
	t1.orgid,
	t1.`ACL`,
	t1.`BEGIN`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND DELETED = '0' ) AS `BUGCNT`,
	t1.`CANCELEDBY`,
	( SELECT count( 1 ) + 1 FROM zt_doclib WHERE type = 'project' AND project = t1.`id` ) AS `DOCLIBCNT`,
	t1.`CANCELEDDATE`,
	t1.`CATID`,
	t1.`CLOSEDBY`,
	t1.`CLOSEDDATE`,
	t1.`CODE`,
	t1.`DAYS`,
	t1.`DELETED`,
	t1.`END`,
	t1.`ID`,
	t1.`ISCAT`,
	t1.`NAME`,
	t1.`OPENEDBY`,
	t1.`OPENEDDATE`,
	t1.`OPENEDVERSION`,
	t1.`ORDER`,
	( CASE WHEN T2.OBJECTORDER IS NOT NULL THEN T2.OBJECTORDER ELSE t1.`ORDER` END ) AS `ORDER1`,
	( CASE WHEN T2.OBJECTORDER IS NOT NULL THEN 1 ELSE 0 END ) AS `ISTOP`,
	t1.`PARENT`,
	t11.`NAME` AS `PARENTNAME`,
	t1.`PM`,
	t1.`PO`,
	t1.`PRI`,
	t1.`QD`,
	t1.`RD`,
	t1.`STATGE`,
	t1.`STATUS`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_STORY
	LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	) AS `STORYCNT`,
	t1.`SUBSTATUS`,
	( SELECT COUNT( 1 ) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' ) AS `TASKCNT`,
	t1.`TEAM`,
	(
SELECT
	round( SUM( CONSUMED ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) AS `TOTALCONSUMED`,
	(
SELECT
	round( SUM( ESTIMATE ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) AS `TOTALESTIMATE`,
	( SELECT sum( days * hours ) FROM zt_team tt WHERE type = 'project' AND root = t1.id ) AS `TOTALHOURS`,
	(
SELECT
	round( SUM( `LEFT` ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND `status` IN ( 'doing', 'wait', 'pause' ) 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) AS `TOTALLEFT`,
	(
	(
SELECT
	round( SUM( `LEFT` ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	AND `status` IN ( 'doing', 'wait', 'pause' ) 
	) + (
SELECT
	round( SUM( CONSUMED ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) 
	) AS `TOTALWH`,
	t1.`TYPE` 
FROM
	`zt_project` t1
	LEFT JOIN t_ibz_top t2 ON t1.id = t2.OBJECTID 
	AND t2.type = 'project' 
	AND t2.ACCOUNT = #{srf.sessioncontext.srfloginname} LEFT JOIN zt_project t11 ON t1.PARENT = t11.ID where t1.deleted = '0' and ( t1.acl = 'private' and t1.id in (select t4.project from zt_team t3 left join zt_projectproduct t4 on t3.root = t4.product where t3.account =#{srf.sessioncontext.srfloginname}  and t3.type = 'product'
	
	)  or t1.id = 0)
	) t1
```
### 当前用户待办项目(CurUserTodo)<div id="Project_CurUserTodo"></div>
```sql
SELECT
	t1.MDEPTID,
	t1.orgid,
	t1.`ACL`,
	t1.`BEGIN`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND DELETED = '0' ) AS `BUGCNT`,
	t1.`CANCELEDBY`,
	( SELECT count( 1 ) + 1 FROM zt_doclib WHERE type = 'project' AND project = t1.`id` ) AS `DOCLIBCNT`,
	t1.`CANCELEDDATE`,
	t1.`CATID`,
	t1.`CLOSEDBY`,
	t1.`CLOSEDDATE`,
	t1.`CODE`,
	t1.`DAYS`,
	t1.`DELETED`,
	t1.`END`,
	t1.`ID`,
	t1.`ISCAT`,
	t1.`NAME`,
	t1.`OPENEDBY`,
	t1.`OPENEDDATE`,
	t1.`OPENEDVERSION`,
	t1.`ORDER`,
	( CASE WHEN T2.OBJECTORDER IS NOT NULL THEN T2.OBJECTORDER ELSE t1.`ORDER` END ) AS `ORDER1`,
	( CASE WHEN T2.OBJECTORDER IS NOT NULL THEN 1 ELSE 0 END ) AS `ISTOP`,
	t1.`PARENT`,
	t11.`NAME` AS `PARENTNAME`,
	t1.`PM`,
	t1.`PO`,
	t1.`PRI`,
	t1.`QD`,
	t1.`RD`,
	t1.`STATGE`,
	t1.`STATUS`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_STORY
	LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	) AS `STORYCNT`,
	t1.`SUBSTATUS`,
	( SELECT COUNT( 1 ) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' ) AS `TASKCNT`,
	t1.`TEAM`,
	(
SELECT
	round( SUM( CONSUMED ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) AS `TOTALCONSUMED`,
	(
SELECT
	round( SUM( ESTIMATE ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) AS `TOTALESTIMATE`,
	( SELECT sum( days * hours ) FROM zt_team tt WHERE type = 'project' AND root = t1.id ) AS `TOTALHOURS`,
	(
SELECT
	round( SUM( `LEFT` ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND `status` IN ( 'doing', 'wait', 'pause' ) 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) AS `TOTALLEFT`,
	(
	(
SELECT
	round( SUM( `LEFT` ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	AND `status` IN ( 'doing', 'wait', 'pause' ) 
	) + (
SELECT
	round( SUM( CONSUMED ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) 
	) AS `TOTALWH`,
	t1.`TYPE` 
FROM
	`zt_project` t1
	LEFT JOIN t_ibz_top t2 ON t1.id = t2.OBJECTID 
	AND t2.type = 'project' 
	AND t2.ACCOUNT = #{srf.sessioncontext.srfloginname}
	LEFT JOIN zt_project t11 ON t1.PARENT = t11.ID 
WHERE
	t1.deleted = '0' 
	AND (
	t1.acl = 'open' 
	OR t1.OPENEDBY = #{srf.sessioncontext.srfloginname} 
	OR t1.pm = #{srf.sessioncontext.srfloginname}
	OR t1.PO = #{srf.sessioncontext.srfloginname} 
	OR t1.RD = #{srf.sessioncontext.srfloginname} 
	OR t1.QD = #{srf.sessioncontext.srfloginname}
	) UNION
SELECT
	t1.MDEPTID,
	t1.orgid,
	t1.`ACL`,
	t1.`BEGIN`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND DELETED = '0' ) AS `BUGCNT`,
	t1.`CANCELEDBY`,
	( SELECT count( 1 ) + 1 FROM zt_doclib WHERE type = 'project' AND project = t1.`id` ) AS `DOCLIBCNT`,
	t1.`CANCELEDDATE`,
	t1.`CATID`,
	t1.`CLOSEDBY`,
	t1.`CLOSEDDATE`,
	t1.`CODE`,
	t1.`DAYS`,
	t1.`DELETED`,
	t1.`END`,
	t1.`ID`,
	t1.`ISCAT`,
	t1.`NAME`,
	t1.`OPENEDBY`,
	t1.`OPENEDDATE`,
	t1.`OPENEDVERSION`,
	t1.`ORDER`,
	( CASE WHEN T2.OBJECTORDER IS NOT NULL THEN T2.OBJECTORDER ELSE t1.`ORDER` END ) AS `ORDER1`,
	( CASE WHEN T2.OBJECTORDER IS NOT NULL THEN 1 ELSE 0 END ) AS `ISTOP`,
	t1.`PARENT`,
	t11.`NAME` AS `PARENTNAME`,
	t1.`PM`,
	t1.`PO`,
	t1.`PRI`,
	t1.`QD`,
	t1.`RD`,
	t1.`STATGE`,
	t1.`STATUS`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_STORY
	LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	) AS `STORYCNT`,
	t1.`SUBSTATUS`,
	( SELECT COUNT( 1 ) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' ) AS `TASKCNT`,
	t1.`TEAM`,
	(
SELECT
	round( SUM( CONSUMED ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) AS `TOTALCONSUMED`,
	(
SELECT
	round( SUM( ESTIMATE ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) AS `TOTALESTIMATE`,
	( SELECT sum( days * hours ) FROM zt_team tt WHERE type = 'project' AND root = t1.id ) AS `TOTALHOURS`,
	(
SELECT
	round( SUM( `LEFT` ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND `status` IN ( 'doing', 'wait', 'pause' ) 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) AS `TOTALLEFT`,
	(
	(
SELECT
	round( SUM( `LEFT` ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	AND `status` IN ( 'doing', 'wait', 'pause' ) 
	) + (
SELECT
	round( SUM( CONSUMED ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) 
	) AS `TOTALWH`,
	t1.`TYPE` 
FROM
	`zt_project` t1
	LEFT JOIN t_ibz_top t2 ON t1.id = t2.OBJECTID 
	AND t2.type = 'project' 
	AND t2.ACCOUNT = #{srf.sessioncontext.srfloginname}
	LEFT JOIN zt_project t11 ON t1.PARENT = t11.ID 
WHERE
	t1.deleted = '0' 
	AND t1.acl = 'private' 
	AND t1.id IN ( select t3.root from zt_team t3 where t3.account = 
#{srf.sessioncontext.srfloginname} and t3.type = 'project') or t1.id in (SELECT DISTINCT t2.project from zt_team t1 left join zt_projectproduct t2 on t1.root = t2.product where t1.account = #{srf.sessioncontext.srfloginname} and t1.type = 'product')）
WHERE t1.orgid = #{srf.sessioncontext.srforgid} 

```
### DEFAULT(DEFAULT)<div id="Project_Default"></div>
```sql
SELECT
t1.`ACL`,
t1.`BEGIN`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `BUGCNT`,
t1.`CANCELEDBY`,
t1.`CANCELEDDATE`,
t1.`CATID`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CODE`,
t1.`DAYS`,
t1.`DELETED`,
t1.`END`,
t1.`ID`,
t1.`ISCAT`,
'0' AS `ISTOP`,
t1.`MDEPTID`,
t1.`NAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OPENEDVERSION`,
t1.`ORDER`,
t1.`order` AS `ORDER1`,
t1.`ORGID`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PM`,
t1.`PO`,
t1.`PRI`,
t1.`QD`,
t1.`RD`,
t1.`STATGE`,
t1.`STATUS`,
(SELECT COUNT(1) FROM ZT_STORY LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `STORYCNT`,
t1.`SUBSTATUS`,
t1.`SUPPROREPORT`,
(SELECT COUNT(1) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `TASKCNT`,
t1.`TEAM`,
(SELECT round(SUM(CONSUMED),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALCONSUMED`,
(SELECT round(SUM(ESTIMATE),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED =  '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALESTIMATE`,
(select sum(days * hours)  from zt_team tt where type = 'project' and root = t1.id) AS `TOTALHOURS`,
(SELECT round(SUM(`LEFT`),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' and `status` in ('doing','wait','pause') AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALLEFT`,
((SELECT round(SUM( `LEFT` ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) AND `status` in ('doing','wait','pause')) + (SELECT round(SUM( CONSUMED ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ))) AS `TOTALWH`,
t1.`TYPE`
FROM `zt_project` t1 
LEFT JOIN `zt_project` t11 ON t1.`PARENT` = t11.`ID` 

WHERE t1.DELETED = '0' 

```
### ES批量的导入(ESBulk)<div id="Project_ESBulk"></div>
```sql
SELECT
	t1.id,
	t1.`name`,
	t1.`desc`,
	t1.deleted
FROM
	zt_project t1
```
### 参与项目(年度总结)(InvolvedProject)<div id="Project_InvolvedProject"></div>
```sql
 select t1.* from (SELECT
t1.MDEPTID,
t1.orgid,
t1.`ACL`,
t1.`BEGIN`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `BUGCNT`,
t1.`CANCELEDBY`,
t1.`CANCELEDDATE`,
t1.`CATID`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CODE`,
t1.`DAYS`,
t1.`DELETED`,
t1.`END`,
t1.`ID`,
t1.`ISCAT`,
'0' AS `ISTOP`,
t1.`NAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OPENEDVERSION`,
t1.`ORDER`,
t1.`order` AS `ORDER1`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PM`,
t1.`PO`,
t1.`PRI`,
t1.`QD`,
t1.`RD`,
t1.`STATGE`,
CASE 
	WHEN t1.`STATUS` = 'wait' THEN
		'doing'
	ELSE
		t1.`STATUS`
END  as `STATUS`,
(SELECT COUNT(1) FROM ZT_STORY LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `STORYCNT`,
t1.`SUBSTATUS`,
(SELECT COUNT(1) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `TASKCNT`,
t1.`TEAM`,
(SELECT round(SUM(CONSUMED),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALCONSUMED`,
(SELECT round(SUM(ESTIMATE),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED =  '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALESTIMATE`,
(select sum(days * hours)  from zt_team tt where type = 'project' and root = t1.id) AS `TOTALHOURS`,
(SELECT round(SUM(`LEFT`),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' and `status` in ('doing','wait','pause') AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALLEFT`,
((SELECT round(SUM( `LEFT` ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) AND `status` in ('doing','wait','pause')) + (SELECT round(SUM( CONSUMED ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ))) AS `TOTALWH`,
t1.`TYPE`
FROM `zt_project` t1 
LEFT JOIN zt_project t11 ON t1.PARENT = t11.ID 
where t1.id in ( SELECT t1.id from zt_project t1 where (YEAR(t1.`begin`) <= #{srf.webcontext.curyear} 
and YEAR(t1.`end`) >= 
#{srf.webcontext.curyear}
) and ( t1.openedBy = #{srf.sessioncontext.srfloginname}
or t1.PO =#{srf.sessioncontext.srfloginname} 
or t1.PM =#{srf.sessioncontext.srfloginname}
or t1.QD =#{srf.sessioncontext.srfloginname} 
or t1.RD =#{srf.sessioncontext.srfloginname}
) ) or t1.id in ( select t1.root from zt_team t1 where t1.type = 'project' and t1.account =#{srf.sessioncontext.srfloginname} 
and 
YEAR(t1.`join`) = #{srf.webcontext.curyear} 
) or t1.id in  (SELECT DISTINCT t1.project from zt_task t1 LEFT JOIN zt_action t2 on t1.id = t2.OBJECTID and t2.objectType = 'task' where t2.actor = #{srf.sessioncontext.srfloginname}
and t2.action = 'finished' and left(t2.date,4) = #{srf.webcontext.curyear} 
)
) t1
WHERE t1.orgid = #{srf.sessioncontext.srforgid} 

```
### 参与项目完成需求任务bug(InvolvedProjectStoryTaskBug)<div id="Project_InvolvedProjectStoryTaskBug"></div>
```sql
SELECT t1.*,IFNULL(t2.storycnt,0) as storycnt,IFNULL(t3.taskcnt,0) as ycompletetaskcnt,IFNULL(t4.bugcnt,0) as bugcnt from (
select  t1.id,t1.`name`  from zt_project t1 where t1.id in ( SELECT t1.id from zt_project t1 where (YEAR(t1.`begin`) <= #{srf.webcontext.curyear} 
and YEAR(t1.`end`) >=#{srf.webcontext.curyear} ) and ( t1.openedBy =#{srf.sessioncontext.srfloginname}
or t1.PO =#{srf.sessioncontext.srfloginname} 
or t1.PM =#{srf.sessioncontext.srfloginname}
or t1.QD = #{srf.sessioncontext.srfloginname} 
or t1.RD = #{srf.sessioncontext.srfloginname}
) ) or t1.id in ( select t1.root from zt_team t1 where t1.type = 'project' and t1.account =#{srf.sessioncontext.srfloginname} 
and 
YEAR(t1.`join`) =#{srf.webcontext.curyear} 
) or t1.id in  (SELECT DISTINCT t1.project from zt_task t1 LEFT JOIN zt_action t2 on t1.id = t2.OBJECTID and t2.objectType = 'task' where t2.actor =#{srf.sessioncontext.srfloginname}

and t2.action = 'finished' and left(t2.date,4) =  #{srf.webcontext.curyear} 
)

) t1 
LEFT JOIN (
SELECT count(1) as storycnt,t1.project from (
select t1.id,t1.`title`,t1.`status`,t2.project from zt_story t1 LEFT JOIN zt_projectstory t2 on t2.story = t1.id 
) t1 GROUP BY t1.project 
) t2 on t2.project = t1.id
LEFT JOIN (
select  t1.project,count(1) as taskcnt from zt_task t1 where  t1.finishedBy = 
#{srf.sessioncontext.srfloginname} 
GROUP 
BY t1.project
) t3 on t3.project = t1.id
LEFT JOIN (
SELECT  t1.project,count(1) as bugcnt from zt_bug t1 where  t1.resolvedBy = 
#{srf.sessioncontext.srfloginname}	
GROUP BY t1.project
) t4 on t4.project = t1.id
```
### 我管辖的项目(MyManageProject)<div id="Project_MyManageProject"></div>
```sql
SELECT t1.ID,t1.`NAME` from zt_project t1
WHERE t1.PM = #{srf.sessioncontext.srfloginname} and t1.DELETED = '0' 

```
### 我的项目(MyProject)<div id="Project_MyProject"></div>
```sql
select t1.* from (SELECT
        t1.MDEPTID,
        t1.orgid,
	t1.`ACL`,
	t1.`BEGIN`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND DELETED = '0' ) AS `BUGCNT`,
	t1.`CANCELEDBY`,
	t1.`CANCELEDDATE`,
	t1.`CATID`,
	t1.`CLOSEDBY`,
	t1.`CLOSEDDATE`,
	t1.`CODE`,
	t1.`DAYS`,
	t1.`DELETED`,
	t1.`END`,
	t1.`ID`,
	t1.`ISCAT`,
	t1.`NAME`,
	t1.`OPENEDBY`,
	t1.`OPENEDDATE`,
	t1.`OPENEDVERSION`,
	t1.`ORDER`,
	t1.`ORDER` AS `ORDER1`,
	t1.`PARENT`,
	t11.`NAME` AS `PARENTNAME`,
	t1.`PM`,
	t1.`PO`,
	t1.`PRI`,
	t1.`QD`,
	t1.`RD`,
	t1.`STATGE`,
	t1.`STATUS`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_STORY
	LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	) AS `STORYCNT`,
	t1.`SUBSTATUS`,
	( SELECT COUNT( 1 ) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' ) AS `TASKCNT`,
	t1.`TEAM`,
	t1.`TYPE` ,
	t21.role,
	t21.account,
	t21.`join`,
	t21.hours
FROM
	`zt_project` t1
	LEFT JOIN zt_project t11 ON t1.PARENT = t11.ID
	INNER JOIN zt_team t21 on t1.ID = t21.root and t21.type= 'project' ) t1
WHERE t1.orgid = #{srf.sessioncontext.srforgid} 
t1.DELETED = '0' 

```
### 项目团队(ProjectTeam)<div id="Project_ProjectTeam"></div>
```sql
SELECT
t1.MDEPTID,
t1.orgid,
t1.`ACL`,
t1.`BEGIN`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `BUGCNT`,
t1.`CANCELEDBY`,
t1.`CANCELEDDATE`,
t1.`CATID`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CODE`,
t1.`DAYS`,
t1.`DELETED`,
t1.`END`,
t1.`ID`,
t1.`ISCAT`,
'0' AS `ISTOP`,
t1.`NAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OPENEDVERSION`,
t1.`ORDER`,
t1.`order` AS `ORDER1`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PM`,
t1.`PO`,
t1.`PRI`,
t1.`QD`,
t1.`RD`,
t1.`STATGE`,
t1.`STATUS`,
(SELECT COUNT(1) FROM ZT_STORY LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `STORYCNT`,
t1.`SUBSTATUS`,
(SELECT COUNT(1) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `TASKCNT`,
t1.`TEAM`,
(SELECT round(SUM(CONSUMED),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALCONSUMED`,
(SELECT round(SUM(ESTIMATE),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED =  '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALESTIMATE`,
(select sum(days * hours)  from zt_team tt where type = 'project' and root = t1.id) AS `TOTALHOURS`,
(SELECT round(SUM(`LEFT`),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALLEFT`,
((SELECT round(SUM( ESTIMATE ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) ) + (SELECT round(SUM( CONSUMED ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) )) AS `TOTALWH`,
t1.`TYPE`
FROM `zt_project` t1 
LEFT JOIN zt_project t11 ON t1.PARENT = t11.ID 
left join zt_team t21 on t1.id = t21.root
WHERE t1.DELETED = '0' 
t21.type = 'project' 
 t21.account = #{srf.sessioncontext.srfloginname} 
t1.orgid = #{srf.sessioncontext.srforgid} 

```
### 需求影响项目(StoryProject)<div id="Project_StoryProject"></div>
```sql
select concat(t1.`name`, '  ' ,t.ACCOUNTS) accounts,t1.id ,t1.`name`  from zt_project t1 left join (select GROUP_CONCAT(t.realnames Separator ' ') as ACCOUNTS,t.root from (select t.account,t1.realname, CONCAT(UPPER(left(t.account,1)),':',t1.realname) as realnames,t.root from zt_team t left join zt_user t1 on t1.account = t.account where  t.type = 'project' and t1.deleted = '0') t GROUP BY t.root) t on t1.id = t.root
WHERE t1.id in ( select tt.project from zt_task tt where tt.deleted = '0' and tt.story = ${srfdatacontext('story','{"defname":"PRODUCT","dename":"ZT_PROJECTPRODUCT"}')} ) 

```
### 未完成项目(UnDoneProject)<div id="Project_UnDoneProject"></div>
```sql
select t1.* from (SELECT
t1.MDEPTID,
t1.orgid,
t1.`ACL`,
t1.`BEGIN`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `BUGCNT`,
t1.`CANCELEDBY`,
(select count(1) + 1 from zt_doclib where type = 'project' and project = t1.`id`) as `DOCLIBCNT`,
t1.`CANCELEDDATE`,
t1.`CATID`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CODE`,
t1.`DAYS`,
t1.`DELETED`,
t1.`END`,
t1.`ID`,
t1.`ISCAT`,
t1.`NAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OPENEDVERSION`,
t1.`ORDER`,
(CASE WHEN T2.OBJECTORDER IS NOT NULL THEN T2.OBJECTORDER ELSE  t1.`ORDER` END) as `ORDER1`,
	(CASE WHEN T2.OBJECTORDER IS NOT NULL THEN 1 ELSE 0 END) as `ISTOP`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PM`,
t1.`PO`,
t1.`PRI`,
t1.`QD`,
t1.`RD`,
t1.`STATGE`,
t1.`STATUS`,
(SELECT COUNT(1) FROM ZT_STORY LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `STORYCNT`,
t1.`SUBSTATUS`,
(SELECT COUNT(1) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `TASKCNT`,
t1.`TEAM`,
(SELECT round(SUM(CONSUMED),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALCONSUMED`,
(SELECT round(SUM(ESTIMATE),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED =  '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALESTIMATE`,
(select sum(days * hours)  from zt_team tt where type = 'project' and root = t1.id) AS `TOTALHOURS`,
(SELECT round(SUM(`LEFT`),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' and `status` in ('doing','wait','pause') AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALLEFT`,
((SELECT round(SUM( `LEFT` ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) AND `status` in ('doing','wait','pause')) + (SELECT round(SUM( CONSUMED ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ))) AS `TOTALWH`,
t1.`TYPE`
FROM `zt_project` t1 
left join t_ibz_top t2 on t1.id = t2.OBJECTID and t2.type = 'project' and t2.ACCOUNT = #{srf.sessioncontext.srfloginname}
LEFT JOIN zt_project t11 ON t1.PARENT = t11.ID 
where t1.deleted = '0' and (t1.acl = 'open' or t1.OPENEDBY = #{srf.sessioncontext.srfloginname} or  t1.pm =  #{srf.sessioncontext.srfloginname} or t1.PO = #{srf.sessioncontext.srfloginname} or t1.RD = #{srf.sessioncontext.srfloginname} or t1.QD =  #{srf.sessioncontext.srfloginname} )
union 
SELECT
t1.MDEPTID,
t1.orgid,
t1.`ACL`,
t1.`BEGIN`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `BUGCNT`,
t1.`CANCELEDBY`,
(select count(1) + 1 from zt_doclib where type = 'project' and project = t1.`id`) as `DOCLIBCNT`,
t1.`CANCELEDDATE`,
t1.`CATID`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CODE`,
t1.`DAYS`,
t1.`DELETED`,
t1.`END`,
t1.`ID`,
t1.`ISCAT`,
t1.`NAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OPENEDVERSION`,
t1.`ORDER`,
(CASE WHEN T2.OBJECTORDER IS NOT NULL THEN T2.OBJECTORDER ELSE  t1.`ORDER` END) as `ORDER1`,
	(CASE WHEN T2.OBJECTORDER IS NOT NULL THEN 1 ELSE 0 END) as `ISTOP`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PM`,
t1.`PO`,
t1.`PRI`,
t1.`QD`,
t1.`RD`,
t1.`STATGE`,
t1.`STATUS`,
(SELECT COUNT(1) FROM ZT_STORY LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `STORYCNT`,
t1.`SUBSTATUS`,
(SELECT COUNT(1) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `TASKCNT`,
t1.`TEAM`,
(SELECT round(SUM(CONSUMED),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALCONSUMED`,
(SELECT round(SUM(ESTIMATE),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED =  '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALESTIMATE`,
(select sum(days * hours)  from zt_team tt where type = 'project' and root = t1.id) AS `TOTALHOURS`,
(SELECT round(SUM(`LEFT`),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' and `status` in ('doing','wait','pause') AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALLEFT`,
((SELECT round(SUM( `LEFT` ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) AND `status` in ('doing','wait','pause')) + (SELECT round(SUM( CONSUMED ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ))) AS `TOTALWH`,
t1.`TYPE`
FROM `zt_project` t1 
left join t_ibz_top t2 on t1.id = t2.OBJECTID and t2.type = 'project' and t2.ACCOUNT = #{srf.sessioncontext.srfloginname}
LEFT JOIN zt_project t11 ON t1.PARENT = t11.ID 
where t1.deleted = '0' and t1.acl = 'private' and t1.id in (select t3.root from zt_team t3 where t3.account = #{srf.sessioncontext.srfloginname}  
and t3.type = 'project')) t1
WHERE t1.orgid = #{srf.sessioncontext.srforgid} 
t1.`status` <> 'closed' 

```
### 默认（全部数据）(VIEW)<div id="Project_View"></div>
```sql
SELECT
t1.`ACL`,
t1.`BEGIN`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `BUGCNT`,
t1.`CANCELEDBY`,
t1.`CANCELEDDATE`,
t1.`CATID`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CODE`,
t1.`DAYS`,
t1.`DELETED`,
t1.`DESC`,
t1.`END`,
t1.`ID`,
t1.`ISCAT`,
'0' AS `ISTOP`,
t1.`MDEPTID`,
t1.`NAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`OPENEDVERSION`,
t1.`ORDER`,
t1.`order` AS `ORDER1`,
t1.`ORGID`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PM`,
t1.`PMSEEPROJECTINFO`,
t1.`PO`,
t1.`PRI`,
t1.`QD`,
t1.`RD`,
t1.`STATGE`,
t1.`STATUS`,
(SELECT COUNT(1) FROM ZT_STORY LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `STORYCNT`,
t1.`SUBSTATUS`,
t1.`SUPPROREPORT`,
(SELECT COUNT(1) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `TASKCNT`,
t1.`TEAM`,
(SELECT round(SUM(CONSUMED),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALCONSUMED`,
(SELECT round(SUM(ESTIMATE),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED =  '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALESTIMATE`,
(select sum(days * hours)  from zt_team tt where type = 'project' and root = t1.id) AS `TOTALHOURS`,
(SELECT round(SUM(`LEFT`),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' and `status` in ('doing','wait','pause') AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALLEFT`,
((SELECT round(SUM( `LEFT` ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) AND `status` in ('doing','wait','pause')) + (SELECT round(SUM( CONSUMED ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ))) AS `TOTALWH`,
t1.`TYPE`,
t1.`WHITELIST`
FROM `zt_project` t1 
LEFT JOIN `zt_project` t11 ON t1.`PARENT` = t11.`ID` 

WHERE t1.DELETED = '0' 

```

# **任务模块**(IBZ_PROJECTMODULE)

### BYPATH(BYPATH)<div id="ProjectModule_ByPath"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 

WHERE t1.DELETED = '0' 
t1.`TYPE` = 'task' 
FIND_IN_SET (${srfdatacontext('srfparentkey','{"defname":"ID","dename":"ZT_PROJECTMODULE"}')}, t1.`PATH`) 

```
### DEFAULT(DEFAULT)<div id="ProjectModule_Default"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t21.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`ROOT`,
t11.`NAME` AS `ROOTNAME`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_project` t11 ON t1.`ROOT` = t11.`ID` 
LEFT JOIN `zt_module` t21 ON t1.`PARENT` = t21.`ID` 

WHERE t1.DELETED = '0' 
( t1.`TYPE` = 'task' ) 

```
### 父模块(ParentModule)<div id="ProjectModule_ParentModule"></div>
```sql
SELECT
	t1.`BRANCH`,
	t1.`DELETED`,
	t1.`GRADE`,
	t1.`ID`,
	( CASE WHEN EXISTS ( SELECT 1 FROM ZT_MODULE WHERE PARENT = t1.`ID` ) THEN FALSE ELSE TRUE END ) AS `ISLEAF`,
	CONCAT(
	'/',
	(
SELECT
	GROUP_CONCAT( tt.NAME SEPARATOR '/' ) 
FROM
	zt_module tt 
WHERE
	FIND_IN_SET( tt.id, t1.path ) 
	AND tt.type = 'task' 
GROUP BY
	tt.root 
	) 
	) AS `NAME`,
	t1.`ORDER`,
	t1.`OWNER`,
	t1.`PARENT`,
	t21.`NAME` AS `PARENTNAME`,
	t1.`PATH`,
	t1.`ROOT`,
	t11.`NAME` AS `ROOTNAME`,
	t1.`SHORT`,
	t1.`TYPE` 
FROM
	`zt_module` t1
	LEFT JOIN zt_project t11 ON t1.ROOT = t11.ID
	LEFT JOIN zt_module t21 ON t1.PARENT = t21.ID
WHERE t1.type = 'task' 
t1.id <> #{srf.webcontext.id} and find_in_set(#{srf.webcontext.id},t1.path) = 0 
t1.DELETED = '0' 

```
### 根需求模块(ROOT)<div id="ProjectModule_Root"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t21.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`ROOT`,
t11.`NAME` AS `ROOTNAME`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_project` t11 ON t1.`ROOT` = t11.`ID` 
LEFT JOIN `zt_module` t21 ON t1.`PARENT` = t21.`ID` 

WHERE t1.DELETED = '0' 
( t1.`TYPE` = 'story'  AND  ( t1.`PARENT` IS NULL  OR  t1.`PARENT` = 0 ) ) 

```
### 根需求模块_无分支(Root_NoBranch)<div id="ProjectModule_Root_NoBranch"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t21.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`ROOT`,
t11.`NAME` AS `ROOTNAME`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_project` t11 ON t1.`ROOT` = t11.`ID` 
LEFT JOIN `zt_module` t21 ON t1.`PARENT` = t21.`ID` 

WHERE t1.DELETED = '0' 
( t1.`TYPE` = 'story'  AND  t1.`BRANCH` = 0  AND  ( t1.`PARENT` IS NULL  OR  t1.`PARENT` = 0 ) ) 

```
### 根任务模块(ROOT_TASK)<div id="ProjectModule_Root_Task"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t21.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`ROOT`,
t11.`NAME` AS `ROOTNAME`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_project` t11 ON t1.`ROOT` = t11.`ID` 
LEFT JOIN `zt_module` t21 ON t1.`PARENT` = t21.`ID` 

WHERE t1.DELETED = '0' 
( t1.`TYPE` = 'task'  AND  ( t1.`PARENT` IS NULL  OR  t1.`PARENT` = 0 ) ) 

```
### 任务模块(TaskModules)<div id="ProjectModule_TaskModules"></div>
```sql
select t1.* from (select t1.* from (SELECT
	t1.`BRANCH`,
	t1.`DELETED`,
	t1.`ID`,
	CONCAT(
	'/', case when t1.parent > 0 then
	(
SELECT
	GROUP_CONCAT( tt.NAME SEPARATOR '/' ) 
FROM
	zt_module tt 
WHERE
	FIND_IN_SET( tt.id, t1.path ) 
	AND tt.type = 'story' 
GROUP BY
	tt.root 
	) else t1.`name` end
	) AS `NAME`,
	(CONCAT_ws(
	'', 
 case when t1.parent > 0 then (
SELECT
	GROUP_CONCAT( tt.`ORDER` SEPARATOR '-' ) 
FROM
	zt_module tt 
WHERE
	FIND_IN_SET( tt.id, t1.path ) 
	AND tt.type = 'story' 
GROUP BY
	tt.root 
	LIMIT 0,1
	) else t1.`ORDER` end)) as ORDERPK,
	t1.`ORDER`,
	t1.`PARENT`,
	t1.`PATH`,
	t1.`ROOT`,
	t1.`TYPE` 
FROM
	`zt_module` t1
	LEFT JOIN zt_product t11 ON t1.ROOT = t11.ID
	LEFT JOIN zt_module t21 ON t1.PARENT = t21.ID
	where t1.type = 'story' and t1.deleted = '0' order by t1.path asc) t1
	UNION
	select t1.* from (SELECT
	t1.`BRANCH`,
	t1.`DELETED`,
	t1.`ID`,
	CONCAT(
	'/', case when t1.parent > 0 then
	(
SELECT
	GROUP_CONCAT( tt.NAME SEPARATOR '/' ) 
FROM
	zt_module tt 
WHERE
	FIND_IN_SET( tt.id, t1.path ) 
	AND tt.type = 'task' 
GROUP BY
	tt.root 
	) else t1.`name` end 
	) AS `NAME`,
		(CONCAT_WS(
	'', case when t1.parent > 0 then
	(
SELECT
	GROUP_CONCAT( tt.`order` SEPARATOR '-' ) 
FROM
	zt_module tt 
WHERE
	FIND_IN_SET( tt.id, t1.path ) 
	AND tt.type = 'task' 
GROUP BY
	tt.root
LIMIT 0,1 
	) else t1.`order` end
	)) AS `ORDERPK`,
	t1.`ORDER`,
	t1.`PARENT`,
	t1.`PATH`,
	t1.`ROOT`,
	t1.`TYPE` 
FROM
	`zt_module` t1
	LEFT JOIN zt_product t11 ON t1.ROOT = t11.ID
	LEFT JOIN zt_module t21 ON t1.PARENT = t21.ID
	where t1.type = 'task' and t1.deleted = '0' order by t1.path asc) t1
	UNION
	select 
	0 as branch,
	'0' as deleted,
	0 as id,
	'/' as `name`,
	'0' as ORDERPK,
	0 as `order`,
  0 as parent,
  ',0,' as path,
 #{srf.webcontext.project} as root,
  'task' as type	
	) t1
WHERE t1.DELETED = '0' 
( ( t1.`type` = 'task' and t1.`ROOT` = ${srfwebcontext('project','{"defname":"ROOT","dename":"IBZ_PROJECTMODULE"}')} )  or 
(${srfwebcontext('allmodules','{"defname":"ROOT","dename":"IBZ_PROJECTMODULE"}')} = '1' and t1.`type` = 'story' and t1.`root` in (select product from zt_projectproduct where project =  ${srfwebcontext('project','{"defname":"ROOT","dename":"IBZ_PROJECTMODULE"}')}))  or (t1.`id` = ${srfwebcontext('module','{"defname":"ROOT","dename":"IBZ_PROJECTMODULE"}')})) 

```
### 默认（全部数据）(VIEW)<div id="ProjectModule_View"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`COLLECTOR`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t21.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`ROOT`,
t11.`NAME` AS `ROOTNAME`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_project` t11 ON t1.`ROOT` = t11.`ID` 
LEFT JOIN `zt_module` t21 ON t1.`PARENT` = t21.`ID` 

WHERE t1.DELETED = '0' 

```

# **项目产品**(ZT_PROJECTPRODUCT)

### DEFAULT(DEFAULT)<div id="ProjectProduct_Default"></div>
```sql
SELECT
t1.`BRANCH`,
concat(t1.`PROJECT`,'__',t1.`PRODUCT`) AS `ID`,
t1.`PLAN`,
t31.`TITLE` AS `PLANNAME`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`
FROM `zt_projectproduct` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 
LEFT JOIN `zt_project` t21 ON t1.`PROJECT` = t21.`ID` 
LEFT JOIN `zt_productplan` t31 ON t1.`PLAN` = t31.`ID` 

```
### 关联计划(RelationPlan)<div id="ProjectProduct_RelationPlan"></div>
```sql
SELECT
t1.`BRANCH`,
concat(t1.`PROJECT`,'__',t1.`PRODUCT`) AS `ID`,
t1.`PLAN`,
t31.`TITLE` AS `PLANNAME`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`
FROM `zt_projectproduct` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 
LEFT JOIN `zt_project` t21 ON t1.`PROJECT` = t21.`ID` 
LEFT JOIN `zt_productplan` t31 ON t1.`PLAN` = t31.`ID` 

WHERE ( t1.`PLAN` <> 0 ) 

```
### 默认（全部数据）(VIEW)<div id="ProjectProduct_View"></div>
```sql
SELECT
t1.`BRANCH`,
concat(t1.`PROJECT`,'__',t1.`PRODUCT`) AS `ID`,
t1.`PLAN`,
t31.`TITLE` AS `PLANNAME`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`
FROM `zt_projectproduct` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 
LEFT JOIN `zt_project` t21 ON t1.`PROJECT` = t21.`ID` 
LEFT JOIN `zt_productplan` t31 ON t1.`PLAN` = t31.`ID` 

```

# **项目统计**(IBZ_PROJECTSTATS)

### DEFAULT(DEFAULT)<div id="ProjectStats_Default"></div>
```sql
SELECT
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND `STATUS` = 'active' AND DELETED = '0' ) AS `ACTIVEBUGCNT`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND DELETED = '0' ) AS `BUGCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_STORY 
WHERE
	`STATUS` = 'closed' 
	AND FIND_IN_SET ( PRODUCT, ( SELECT GROUP_CONCAT( PRODUCT ) FROM ZT_PROJECTPRODUCT WHERE PROJECT = t1.`ID` ) ) 
	AND DELETED = '0' 
	) AS `CLOSEDSTORYCNT`,
	t1.`DELETED`,
	t1.`END`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND `STATUS` <> 'active' AND DELETED = '0' ) AS `FINISHBUGCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND `STATUS` IN ( 'done', 'cancel', 'closed' ) 
	AND DELETED = '0' 
	) AS `FINISHTASKCNT`,
	t1.`ID`,
	t1.`NAME`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_STORY
	LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY 
WHERE
	stage = 'released' 
	AND PROJECT = t1.id 
	AND DELETED = '0' 
	) AS `RELEASEDSTORYCNT`,
	t1.`STATUS`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_STORY
	LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	) AS `STORYCNT`,
	( SELECT COUNT( 1 ) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' ) AS `TASKCNT`,
	(
SELECT
	round( SUM( CONSUMED ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) AS `TOTALCONSUMED`,
	(
SELECT
	round( SUM( ESTIMATE ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) AS `TOTALESTIMATE`,
	(
SELECT
	round( SUM( `LEFT` ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) AS `TOTALLEFT`,
	(
	(
SELECT
	round( SUM( `LEFT` ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) + (
SELECT
	round( SUM( CONSUMED ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) 
	) AS `TOTALWH`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND `STATUS` <> 'closed' AND DELETED = '0' ) AS `UNCLOSEDBUGCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_STORY
	LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY 
WHERE
	`STATUS` <> 'closed' 
	AND PROJECT = t1.`ID` 
	AND DELETED = '0' 
	) AS `UNCLOSEDSTORYCNT`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND `CONFIRMED` = 0 AND DELETED = '0' ) AS `UNCONFIRMEDBUGCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND `STATUS` NOT IN ( 'done', 'cancel', 'closed' ) 
	AND DELETED = '0' 
	) AS `UNDONETASKCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	zt_task t 
WHERE
	t.deleted = '0' 
	AND t.project = t1.id 
	AND t.`status` = 'closed' 
	AND t.closedDate BETWEEN CONCAT(
	YEAR ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	MONTH ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	DAY ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	' 00:00:00' 
	) 
	AND CONCAT(
	YEAR ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	MONTH ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	DAY ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	' 23:59:59' 
	) 
	) AS `YESTERDAYCTASKCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_BUG 
WHERE
	PROJECT = t1.`ID` 
	AND `STATUS` = 'resolved' 
	AND DELETED = '0' 
	AND RESOLVEDDATE BETWEEN CONCAT(
	YEAR ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	MONTH ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	DAY ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	' 00:00:00' 
	) 
	AND CONCAT(
	YEAR ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	MONTH ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	DAY ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	' 23:59:59' 
	) 
	) AS `YESTERDAYRBUGCNT` ,
	(CASE WHEN T2.OBJECTORDER IS NOT NULL THEN T2.OBJECTORDER ELSE  t1.`ORDER` END) as `ORDER1`,
	(CASE WHEN T2.OBJECTORDER IS NOT NULL THEN 1 ELSE 0 END) as `ISTOP`
FROM
	`zt_project` t1
	left join t_ibz_top t2 on t1.id = t2.OBJECTID and t2.type = 'project' and t2.ACCOUNT = #{srf.sessioncontext.srfloginname}
WHERE t1.DELETED = '0' 
((t1.acl = 'private' and t1.id in (select t3.root from zt_team t3 where t3.account = #{srf.sessioncontext.srfloginname}  and t3.type = 'project')) or t1.acl = 'open') 

```
### 未关闭产品(NOOpenProduct)<div id="ProjectStats_NoOpenProduct"></div>
```sql
SELECT
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND `STATUS` = 'active' AND DELETED = '0' ) AS `ACTIVEBUGCNT`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND DELETED = '0' ) AS `BUGCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_STORY 
WHERE
	`STATUS` = 'closed' 
	AND FIND_IN_SET ( PRODUCT, ( SELECT GROUP_CONCAT( PRODUCT ) FROM ZT_PROJECTPRODUCT WHERE PROJECT = t1.`ID` ) ) 
	AND DELETED = '0' 
	) AS `CLOSEDSTORYCNT`,
	t1.`DELETED`,
	t1.`END`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND `STATUS` <> 'active' AND DELETED = '0' ) AS `FINISHBUGCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND `STATUS` IN ( 'done', 'cancel', 'closed' ) 
	AND DELETED = '0' 
	) AS `FINISHTASKCNT`,
	t1.`ID`,
	t1.`NAME`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_STORY
	LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY 
WHERE
	stage = 'released' 
	AND PROJECT = t1.id 
	AND DELETED = '0' 
	) AS `RELEASEDSTORYCNT`,
	t1.`STATUS`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_STORY
	LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	) AS `STORYCNT`,
	( SELECT COUNT( 1 ) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' ) AS `TASKCNT`,
	(
SELECT
	round( SUM( CONSUMED ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) AS `TOTALCONSUMED`,
	(
SELECT
	round( SUM( ESTIMATE ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) AS `TOTALESTIMATE`,
	(
SELECT
	round( SUM( `LEFT` ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) AS `TOTALLEFT`,
	(
	(
SELECT
	round( SUM( `LEFT` ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) + (
SELECT
	round( SUM( CONSUMED ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	) 
	) AS `TOTALWH`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND `STATUS` <> 'closed' AND DELETED = '0' ) AS `UNCLOSEDBUGCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_STORY
	LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY 
WHERE
	`STATUS` <> 'closed' 
	AND PROJECT = t1.`ID` 
	AND DELETED = '0' 
	) AS `UNCLOSEDSTORYCNT`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND `CONFIRMED` = 0 AND DELETED = '0' ) AS `UNCONFIRMEDBUGCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND `STATUS` NOT IN ( 'done', 'cancel', 'closed' ) 
	AND DELETED = '0' 
	) AS `UNDONETASKCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	zt_task t 
WHERE
	t.deleted = '0' 
	AND t.project = t1.id 
	AND t.`status` = 'closed' 
	AND t.closedDate BETWEEN CONCAT(
	YEAR ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	MONTH ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	DAY ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	' 00:00:00' 
	) 
	AND CONCAT(
	YEAR ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	MONTH ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	DAY ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	' 23:59:59' 
	) 
	) AS `YESTERDAYCTASKCNT`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_BUG 
WHERE
	PROJECT = t1.`ID` 
	AND `STATUS` = 'resolved' 
	AND DELETED = '0' 
	AND RESOLVEDDATE BETWEEN CONCAT(
	YEAR ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	MONTH ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	DAY ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	' 00:00:00' 
	) 
	AND CONCAT(
	YEAR ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	MONTH ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	'-',
	DAY ( DATE_ADD( now( ), INTERVAL - 1 DAY ) ),
	' 23:59:59' 
	) 
	) AS `YESTERDAYRBUGCNT` ,
	(CASE WHEN T2.OBJECTORDER IS NOT NULL THEN T2.OBJECTORDER ELSE  t1.`ORDER` END) as `ORDER1`,
	(CASE WHEN T2.OBJECTORDER IS NOT NULL THEN 1 ELSE 0 END) as `ISTOP`
FROM
	`zt_project` t1
	left join t_ibz_top t2 on t1.id = t2.OBJECTID and t2.type = 'project' and t2.ACCOUNT = #{srf.sessioncontext.srfloginname}
WHERE t1.DELETED = '0' 
( t1.`STATUS` <> 'closed' ) 
((t1.acl = 'private' and t1.id in (select t3.root from zt_team t3 where t3.account = #{srf.sessioncontext.srfloginname}  and t3.type = 'project')) or t1.acl = 'open') 

```
### 项目bug类型(ProjectBugType)<div id="ProjectStats_ProjectBugType"></div>
```sql
SELECT
	t1.*,
	SUM(
	IFNULL( t1.codeerror, 0 ) + IFNULL( t1.config, 0 ) + IFNULL( t1.designdefect, 0 ) + IFNULL( t1.standard, 0 ) + IFNULL( t1.performance, 0 ) + IFNULL( t1.others, 0 ) + IFNULL( t1.INSTALL, 0 ) + IFNULL( t1.automation, 0 ) + IFNULL( t1.SECURITY, 0 ) 
	) AS bugcnt 
FROM
(
SELECT
	t1.id,
	t1.po,
	t1.`status`,
	t1.NAME,
	sum( IF ( t1.type = 'codeerror' AND t1.deleted = '0', t1.rowcnt, 0 ) ) AS 'codeerror',
	sum( IF ( t1.type = 'config' AND t1.deleted = '0', t1.rowcnt, 0 ) ) AS 'config',
	sum( IF ( t1.type = 'designdefect' AND t1.deleted = '0', t1.rowcnt, 0 ) ) AS 'designdefect',
	sum( IF ( t1.type = 'standard' AND t1.deleted = '0', t1.rowcnt, 0 ) ) AS 'standard',
	sum( IF ( t1.type = 'performance' AND t1.deleted = '0', t1.rowcnt, 0 ) ) AS 'performance',
	sum( IF ( t1.type = 'others' AND t1.deleted = '0', t1.rowcnt, 0 ) ) AS 'others',
	sum( IF ( t1.type = 'install' AND t1.deleted = '0', t1.rowcnt, 0 ) ) AS 'install',
	sum( IF ( t1.type = 'automation' AND t1.deleted = '0', t1.rowcnt, 0 ) ) AS 'automation',
	sum( IF ( t1.type = 'security' AND t1.deleted = '0', t1.rowcnt, 0 ) ) AS 'security' 
	from 
	(SELECT
	t1.`id`,
	t1.`name`,
	t1.`po`,
	t1.`status`,
	t2.`type`,
	t2.`title`,
	t2.`deleted`,
	1 AS `rowcnt` 
FROM
	zt_project t1
	LEFT JOIN zt_bug t2 ON t1.`id` = t2.`project` 
WHERE
	t1.`deleted` = '0'
)t1 
 GROUP BY t1.id )t1 GROUP BY t1.id
```
### 项目投入统计(ProjectInputStats)<div id="ProjectStats_ProjectInputStats"></div>
```sql
select
t1.id, 
t1.`name`, 
CONCAT(t1.`begin`, ' ~ ', t1.`end`) as `timescale`, 
t1.`begin`, 
t1.`end`, 
t1.`status`, 
(select count(1) from zt_task t2 where t1.id = t2.project and t2.deleted = '0') as `taskcnt`,
(select count(1) from zt_projectstory t2 where t1.id = t2.project and exists(select 1 from zt_story t3 where t2.story = t3.id and t3.deleted = '0')) as `storycnt`,
(select count(1) from zt_team t2 where t2.type = 'project' and t1.id = t2.root) as `membercnt`, 
IFNULL((select sum(t2.consumed) from zt_taskestimate t2 where exists(select 1 from zt_task t3 where t3.project = t1.id and t3.id = t2.task and t3.deleted = '0')), 0) as `projecttotalconsumed` 
from zt_project t1
WHERE t1.deleted = '0' 

```
### 项目进度(ProjectProgress)<div id="ProjectStats_ProjectProgress"></div>
```sql
SELECT
	t1.*,
	CONCAT(
	IFNULL( ROUND( ( t1.TOTALCONSUMED / ( t1.TOTALCONSUMED + t1.TOTALLEFT ) )*100, 2 ), 0 ),
	'%' 
	) AS progress 
FROM
	(
SELECT
	IFNULL(
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_STORY 
WHERE
	`STAGE` IN ( 'projected', 'developing' ) 
	AND FIND_IN_SET ( PRODUCT, ( SELECT GROUP_CONCAT( PRODUCT ) FROM ZT_PROJECTPRODUCT WHERE PROJECT = t1.`ID` ) ) 
	AND DELETED = '0' 
	),
	0 
	) AS `LEFTSTORYCNT`,
	t1.`DELETED`,
	t1.`ID`,
	t1.`NAME`,
	t1.`STATUS`,
	IFNULL(
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_STORY 
WHERE
	 FIND_IN_SET ( PRODUCT, ( SELECT GROUP_CONCAT( PRODUCT ) FROM ZT_PROJECTPRODUCT WHERE PROJECT = t1.`ID` ) ) 
	AND DELETED = '0'
	),
	0 
	) AS `STORYCNT`,
	IFNULL( ( SELECT COUNT( 1 ) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' ), 0 ) AS `TASKCNT`,
	IFNULL(
	(
SELECT
	round( SUM( CONSUMED ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	),
	0 
	) AS `TOTALCONSUMED`,
	IFNULL(
	(
SELECT
	round( SUM( `LEFT` ), 0 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) 
	),
	0 
	) AS `TOTALLEFT`,
	IFNULL(
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_TASK 
WHERE
	PROJECT = t1.`ID` 
	AND `STATUS` NOT IN ( 'done', 'cancel', 'closed' ) 
	AND DELETED = '0' 
	),
	0 
	) AS `UNDONETASKCNT`,
	( CASE WHEN T2.OBJECTORDER IS NOT NULL THEN T2.OBJECTORDER ELSE t1.`ORDER` END ) AS `ORDER1`,
	( CASE WHEN T2.OBJECTORDER IS NOT NULL THEN 1 ELSE 0 END ) AS `ISTOP` 
FROM
	`zt_project` t1
	LEFT JOIN t_ibz_top t2 ON t1.id = t2.OBJECTID 
	AND t2.type = 'project' 
	AND t2.ACCOUNT = #{srf.sessioncontext.srfloginname} 
WHERE
	t1.DELETED = '0' 
	AND (
	(
	t1.acl = 'private' 
	AND t1.id IN ( SELECT t3.root FROM zt_team t3 WHERE t3.account = #{srf.sessioncontext.srfloginname} AND t3.type = 'project' ) 
	) 
	OR t1.acl = 'open' 
	) 
	) t1
```
### 项目质量表查询(ProjectQuality)<div id="ProjectStats_ProjectQuality"></div>
```sql
SELECT t1.bugcnt,t1.completetaskcnt,t1.completestorycnt,t1.name,t1.storycnt,t1.FINISHBUGCNT,t1.taskcnt,t1.bugstory,t1.bugtask,IFNULL(t4.IMPORTANTBUGCNT,0) as IMPORTANTBUGCNT ,CONCAT(round(IFNULL((IMPORTANTBUGCNT/t1.bugcnt),0),2)*100,'%')  as SERIOUSBUGPROPORTION from (
SELECT
	t1.*,
	IFNULL( t3.bugtask, 0 ) AS bugtask 
FROM
	(
SELECT
	t1.*,
	IFNULL( t22.bugstory, 0 ) AS bugstory 
FROM
	(
SELECT
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND DELETED = '0' ) AS `BUGCNT`,
	t1.`DELETED`,
	t1.`END`,
	( SELECT COUNT( 1 ) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND `STATUS` <> 'active' AND DELETED = '0' ) AS `FINISHBUGCNT`,
	(SELECT count(1) from zt_task where PROJECT = t1.`ID` AND `STATUS`='done' AND DELETED = '0')as completetaskcnt,
	(SELECT count( 1 ) FROM (SELECT t1.project,t1.story from zt_projectstory t1 left join zt_story t2 on t1.story = t2.id where t2.stage in ('verified','released','closed') and t2.deleted='0'
)t2 WHERE t1.id = t2.project) as completestorycnt,
	t1.`ID`,
	t1.`NAME`,
	t1.`STATUS`,
	(
SELECT
	COUNT( 1 ) 
FROM
	ZT_STORY
	LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY 
WHERE
	PROJECT = t1.`ID` 
	AND DELETED = '0' 
	) AS `STORYCNT`,
	( SELECT COUNT( 1 ) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' ) AS `TASKCNT` 
FROM
	`zt_project` t1
	LEFT JOIN t_ibz_top t2 ON t1.id = t2.OBJECTID 
	AND t2.type = 'project' 
	AND t2.ACCOUNT = 'csd123_123' 
WHERE
	t1.DELETED = '0' 
	AND (
	(
	t1.acl = 'private' 
	AND t1.id IN ( SELECT t3.root FROM zt_team t3 WHERE t3.account = 'csd123_123' AND t3.type = 'project' ) 
	) 
	OR t1.acl = 'open' 
	) 
	) t1
	LEFT JOIN (
SELECT
	t1.project,
	count( 1 ) AS bugstory 
FROM
	(
SELECT
	t1.project,
	t1.story,
	t2.title 
FROM
	zt_bug t1
	LEFT JOIN zt_story t2 ON t1.story = t2.id
	LEFT JOIN zt_project t22 ON t1.project = t22.id 
WHERE
	t1.deleted = '0' 
	AND t1.project <> '0' 
	AND t1.story <> '0' 
	AND t22.deleted = '0' 
	) t1 
GROUP BY
	t1.project 
	) t22 ON t1.id = t22.project 
	) t1
	LEFT JOIN (
SELECT
	t1.project,
	count( 1 ) AS bugtask 
FROM
	(
SELECT
	t1.project,
	t1.task,
	t1.title,
	t2.NAME 
FROM
	zt_bug t1
	LEFT JOIN zt_task t2 ON t1.task = t2.id
	LEFT JOIN zt_project t22 ON t1.project = t22.id 
WHERE
	t1.deleted = '0' 
	AND t1.project <> '0' 
	AND t1.task <> '0' 
	AND t22.deleted = '0' 
	) t1 
GROUP BY
	t1.project 
	) t3 ON t1.id = t3.project
	)t1 Left join (SELECT t1.project, count(1) as IMPORTANTBUGCNT from zt_bug t1 where t1.severity <=3 and t1.deleted='0' and t1.project <> '0' GROUP BY t1.project)t4 on t1.id=t4.project
```
### 项目需求阶段统计(ProjectStoryStageStats)<div id="ProjectStats_ProjectStoryStageStats"></div>
```sql
select 
t1.id, 
t1.`name`, 
t1.`status`, 
sum(case when t3.`stage` = '' then 1 else 0 end) as `EmptyStageStoryCNT`, 
sum(case when t3.`stage` = 'wait' then 1 else 0 end) as `WaitStageStoryCNT`, 
sum(case when t3.`stage` = 'planned' then 1 else 0 end) as `PlannedStageStoryCNT`, 
sum(case when t3.`stage` = 'projected' then 1 else 0 end) as `ProjectedStageStoryCNT`, 
sum(case when t3.`stage` = 'developing' then 1 else 0 end) as `DevelopingStageStoryCNT`, 
sum(case when t3.`stage` = 'developed' then 1 else 0 end) as `DevelopedStageStoryCNT`, 
sum(case when t3.`stage` = 'testing' then 1 else 0 end) as `TestingStageStoryCNT`, 
sum(case when t3.`stage` = 'tested' then 1 else 0 end) as `TestedStageStoryCNT`, 
sum(case when t3.`stage` = 'verified' then 1 else 0 end) as `VerifiedStageStoryCNT`, 
sum(case when t3.`stage` = 'released' then 1 else 0 end) as `ReleasedStageStoryCNT`, 
sum(case when t3.`stage` = 'closed' then 1 else 0 end) as `ClosedStageStoryCNT`, 
sum(case when t3.`stage` is not null then 1 else 0 end) as `StoryCNT` 
from 
zt_project t1 
left join zt_projectstory t2 on t1.id = t2.project 
left join zt_story t3 on t2.story = t3.id and t3.deleted = '0' 
where t1.deleted = '0' 
group by t1.id
```
### 项目需求状态统计(ProjectStoryStatusStats)<div id="ProjectStats_ProjectStoryStatusStats"></div>
```sql
select 
t1.id, 
t1.`name`, 
t1.`status`, 
sum(case when t3.`status` = '' then 1 else 0 end) as `EmptyStory`, 
sum(case when t3.`status` = 'draft' then 1 else 0 end) as `DraftStory`, 
sum(case when t3.`status` = 'active' then 1 else 0 end) as `ActiveStory`, 
sum(case when t3.`status` = 'closed' then 1 else 0 end) as `ClosedStory`, 
sum(case when t3.`status` = 'changed' then 1 else 0 end) as `ChangedStory`, 
sum(case when t3.`status` is not null then 1 else 0 end) as `StoryCNT` 
from 
zt_project t1 
left join zt_projectstory t2 on t1.id = t2.project 
left join zt_story t3 on t2.story = t3.id and t3.deleted = '0' 
where t1.deleted = '0' 
group by t1.id
```
### 项目任务统计(任务状态)(ProjectTaskCountByTaskStatus)<div id="ProjectStats_ProjectTaskCountByTaskStatus"></div>
```sql
SELECT t1.project,t1.projectname  as name,
SUM(IF(t1.`status` = 'closed',t1.ss,0)) as closedtaskcnt,
SUM(IF(t1.`status` = 'cancel',t1.ss,0)) as cancelTaskcnt,
SUM(IF(t1.`status` = 'done',t1.ss,0)) as doneTaskcnt,
SUM(IF(t1.`status` = 'pause',t1.ss,0)) as pauseTaskcnt,
SUM(IF(t1.`status` = 'wait',t1.ss,0)) as waitTaskcnt,
SUM(IF(t1.`status` = 'doing',t1.ss,0)) as doingTaskcnt,
COUNT(1) as taskcnt,
t1.deleted
from (
select t1.`status`,t1.project,t2.`name` as projectname, 1 as ss,t2.deleted from zt_task t1 LEFT JOIN zt_project t2 on t1.project = t2.id where t1.deleted = '0' and t1.project <> '0' ) t1 GROUP BY t1.project
```
### 项目任务类型统计(ProjectTaskCountByType)<div id="ProjectStats_ProjectTaskCountByType"></div>
```sql
SELECT t2.id,t2.`name`,IFNULL(t1.designtaskcnt,0) as designtaskcnt,IFNULL(t1.discusstaskcnt,0) as discusstaskcnt, IFNULL(t1.studytaskcnt,0) as studytaskcnt,IFNULL(t1.uitaskcnt,0) as uitaskcnt, IFNULL(t1.testtaskcnt,0) as testtaskcnt,IFNULL(t1.servetaskcnt,0) as servetaskcnt,IFNULL(t1.develtaskcnt,0) as develtaskcnt,IFNULL(t1.misctaskcnt,0) as misctaskcnt,IFNULL(t1.affairtaskcnt,0) as affairtaskcnt,IFNULL(t1.taskcnt,0) as taskcnt ,t1.`status`,t1.`begin`,t1.closedDate as `end`

from (
SELECT t1.project,t1.`name`,t1.`status`,t1.`begin`,t1.closedDate,
SUM(IF(t1.type = 'design',t1.num,0)) as designtaskcnt,
SUM(IF(t1.type = 'discuss',t1.num,0)) as discusstaskcnt,
SUM(IF(t1.type = 'study',t1.num,0)) as studytaskcnt,
SUM(IF(t1.type = 'ui',t1.num,0)) as uitaskcnt,
SUM(IF(t1.type = 'test',t1.num,0)) as testtaskcnt,
SUM(IF(t1.type = 'serve',t1.num,0)) as servetaskcnt,
SUM(IF(t1.type = 'devel',t1.num,0)) as develtaskcnt,
SUM(IF(t1.type = 'misc',t1.num,0)) as misctaskcnt,
SUM(IF(t1.type = 'affair',t1.num,0)) as affairtaskcnt,
COUNT(1) as taskcnt
from(
select t1.type,t1.project,t2.`name`,t2.`status`,t2.`begin`,t2.closedDate,1 as num from zt_task t1 LEFT JOIN zt_project t2 on t1.project = t2.id where t1.deleted = '0' and t2.id <> '0' and t2.deleted = '0') t1
GROUP BY t1.project ) t1 RIGHT JOIN zt_project t2 on t1.project = t2.id where t2.deleted = '0' and (t1.`status` = #{srf.datacontext.status} or #{srf.datacontext.status} is null)
and (DATE_FORMAT(t1.`begin`,'%Y-%m-%d') >=  #{srf.datacontext.begin} or #{srf.datacontext.begin} is null)
and (DATE_FORMAT(t1.`closedDate`,'%Y-%m-%d') >=  #{srf.datacontext.end} or #{srf.datacontext.end} is null)

```
### 任务工时消耗剩余查询(TASKTIME)<div id="ProjectStats_TaskTime"></div>
```sql
SELECT
t1.`ID`,
(SELECT SUM(CONSUMED) FROM ZT_TASK WHERE PROJECT = t1.`ID`) AS `TIME`,
'CONSUMED' AS `TYPE`
FROM `zt_project` t1 WHERE t1.`ID` =  ${srfdatacontext('srfparentkey','{"defname":"PROJECTSTATS","dename":"IBZ_PROJECTSTATS"}')} AND t1.DELETED = 0
UNION ALL 
SELECT
t2.`ID`,
(SELECT SUM(`LEFT`) FROM ZT_TASK WHERE PROJECT = t2.`ID` AND t1.DELETED = 0) AS `TIME`,
'LEFT' AS `TYPE`
FROM `zt_project` t2 WHERE t2.`ID` = ${srfdatacontext('srfparentkey','{"defname":"PROJECTSTATS","dename":"IBZ_PROJECTSTATS"}')}  AND t1.DELETED = 0

```
### 默认（全部数据）(VIEW)<div id="ProjectStats_View"></div>
```sql
SELECT
(SELECT COUNT(1) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND `STATUS` = 'active' AND DELETED = '0') AS `ACTIVEBUGCNT`,
0 AS `ACTIVESTORY`,
t1.`BEGIN`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `BUGCNT`,
0 AS `CHANGEDSTORY`,
0 AS `CLOSEDSTAGESTORYCNT`,
0 AS `CLOSEDSTORY`,
(SELECT COUNT(1) FROM ZT_STORY WHERE `STATUS` =  'closed' AND FIND_IN_SET (PRODUCT, (SELECT GROUP_CONCAT(PRODUCT) FROM ZT_PROJECTPRODUCT WHERE PROJECT= t1.`ID`)) AND DELETED = '0' ) AS `CLOSEDSTORYCNT`,
t1.`DELETED`,
0 AS `DEVELOPEDSTAGESTORYCNT`,
0 AS `DEVELOPINGSTAGESTORYCNT`,
0 AS `DRAFTSTORY`,
0 AS `EMPTYSTAGESTORYCNT`,
0 AS `EMPTYSTORY`,
t1.`END`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND `STATUS` <> 'active' AND DELETED = '0') AS `FINISHBUGCNT`,
(SELECT COUNT(1) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND `STATUS` IN ('done','cancel','closed') AND DELETED = '0') AS `FINISHTASKCNT`,
t1.`ID`,
0 AS `MEMBERCNT`,
t1.`NAME`,
t1.`order` AS `ORDER1`,
0 AS `PLANNEDSTAGESTORYCNT`,
0 AS `PROJECTEDSTAGESTORYCNT`,
0 AS `PROJECTTOTALCONSUMED`,
0 AS `RELEASEDSTAGESTORYCNT`,
(SELECT COUNT(1) FROM ZT_STORY LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY WHERE stage = 'released' AND PROJECT = t1.id AND DELETED = '0') AS `RELEASEDSTORYCNT`,
null AS `SERIOUSBUGPROPORTION`,
t1.`STATUS`,
(SELECT COUNT(1) FROM ZT_STORY LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `STORYCNT`,
(SELECT COUNT(1) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0') AS `TASKCNT`,
0 AS `TESTEDSTAGESTORYCNT`,
0 AS `TESTINGSTAGESTORYCNT`,
(SELECT round(SUM(CONSUMED),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALCONSUMED`,
(SELECT round(SUM(ESTIMATE),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED =  '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALESTIMATE`,
(SELECT round(SUM(`LEFT`),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' or `parent` = '0' or `parent` = '-1')) AS `TOTALLEFT`,
((SELECT round(SUM( `LEFT` ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) ) + (SELECT round(SUM( CONSUMED ),0) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND DELETED = '0' AND ( `parent` = '' OR `parent` = '0' OR `parent` = '-1' ) )) AS `TOTALWH`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND `STATUS` <> 'closed' AND DELETED = '0') AS `UNCLOSEDBUGCNT`,
(SELECT COUNT(1) FROM ZT_STORY LEFT JOIN ZT_PROJECTSTORY ON ZT_STORY.ID = ZT_PROJECTSTORY.STORY WHERE `STATUS` <>  'closed' AND PROJECT = t1.`ID` AND DELETED = '0') AS `UNCLOSEDSTORYCNT`,
(SELECT COUNT(1) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND `CONFIRMED` = 0 AND DELETED = '0') AS `UNCONFIRMEDBUGCNT`,
(SELECT COUNT(1) FROM ZT_TASK WHERE PROJECT = t1.`ID` AND `STATUS` NOT IN ('done','cancel','closed') AND DELETED =  '0') AS `UNDONETASKCNT`,
0 AS `VERIFIEDSTAGESTORYCNT`,
0 AS `WAITSTAGESTORYCNT`,
(select COUNT(1) from zt_task t where t.deleted = '0' and t.project = t1.id and t.`status` = 'closed' and t.closedDate BETWEEN CONCAT(YEAR(DATE_ADD(now(),INTERVAL -1 day)),'-',month(DATE_ADD(now(),INTERVAL -1 day)),'-',day(DATE_ADD(now(),INTERVAL -1 day)),' 00:00:00') and CONCAT(YEAR(DATE_ADD(now(),INTERVAL -1 day)),'-',month(DATE_ADD(now(),INTERVAL -1 day)),'-',day(DATE_ADD(now(),INTERVAL -1 day)),' 23:59:59') ) AS `YESTERDAYCTASKCNT`,
(SELECT COUNT( 1 ) FROM ZT_BUG WHERE PROJECT = t1.`ID` AND `STATUS` = 'resolved' AND DELETED = '0' and RESOLVEDDATE BETWEEN CONCAT(YEAR(DATE_ADD(now(),INTERVAL -1 day)),'-',month(DATE_ADD(now(),INTERVAL -1 day)),'-',day(DATE_ADD(now(),INTERVAL -1 day)),' 00:00:00') and CONCAT(YEAR(DATE_ADD(now(),INTERVAL -1 day)),'-',month(DATE_ADD(now(),INTERVAL -1 day)),'-',day(DATE_ADD(now(),INTERVAL -1 day)),' 23:59:59')) AS `YESTERDAYRBUGCNT`
FROM `zt_project` t1 

WHERE t1.DELETED = '0' 

```

# **项目中需要做的需求**(ZT_PROJECTSTORY)

### DEFAULT(DEFAULT)<div id="ProjectStory_Default"></div>
```sql
SELECT
concat(t1.`PROJECT`,'__',t1.`STORY`) AS `ID`,
t1.`ORDER`,
t1.`PRODUCT`,
t1.`PROJECT`,
t1.`STORY`,
t1.`VERSION`
FROM `zt_projectstory` t1 

```
### 默认（全部数据）(VIEW)<div id="ProjectStory_View"></div>
```sql
SELECT
concat(t1.`PROJECT`,'__',t1.`STORY`) AS `ID`,
t1.`ORDER`,
t1.`PRODUCT`,
t1.`PROJECT`,
t1.`STORY`,
t1.`VERSION`
FROM `zt_projectstory` t1 

```

# **项目工时统计**(PROJECTTASKESTIMATE)

### 用户月工时详情(AccountDetail)<div id="ProjectTaskestimate_AccountDetail"></div>
```sql
SELECT T1.* FROM (SELECT
	t1.account,t1.project,t1.projectname,t1.`YEAR`,concat(t1.`YEAR`, t1.`MONTH`) as `MONTH`,
	sum(if(t1.`day` = '01',t1.consumed, 0)) as oneconsumed,
	sum(if(t1.`day` = '02',t1.consumed, 0)) as twoconsumed,
	sum(if(t1.`day` = '03',t1.consumed, 0)) as threeconsumed,
	sum(if(t1.`day` = '04',t1.consumed, 0)) as FOURONSUMED,
	sum(if(t1.`day` = '05',t1.consumed, 0)) as fiveconsumed,
	sum(if(t1.`day` = '06',t1.consumed, 0)) as sixconsumed,
	sum(if(t1.`day` = '07',t1.consumed, 0)) as sevenconsumed,
	sum(if(t1.`day` = '08',t1.consumed, 0)) as eightconsumed,
	sum(if(t1.`day` = '09',t1.consumed, 0)) as nineconsumed,
	sum(if(t1.`day` = '10',t1.consumed, 0)) as tenconsumed,
	sum(if(t1.`day` = '11',t1.consumed, 0)) as elevenconsumed,
	sum(if(t1.`day` = '12',t1.consumed, 0)) as twelveconsumed,
	sum(if(t1.`day` = '13',t1.consumed, 0)) as thirteenconsumed,
	sum(if(t1.`day` = '14',t1.consumed, 0)) as fourteenconsumed,
	sum(if(t1.`day` = '15',t1.consumed, 0)) as fifteenconsumed,
	sum(if(t1.`day` = '16',t1.consumed, 0)) as sixteenconsumed,
	sum(if(t1.`day` = '17',t1.consumed, 0)) as seventeenconsumed,
	sum(if(t1.`day` = '18',t1.consumed, 0)) as eighteenconsumed,
	sum(if(t1.`day` = '19',t1.consumed, 0)) as nineteenconsumed,
	sum(if(t1.`day` = '20',t1.consumed, 0)) as twentyconsumed,
	sum(if(t1.`day` = '21',t1.consumed, 0)) as twentyoneconsumed,
	sum(if(t1.`day` = '22',t1.consumed, 0)) as twentytwoconsumed,
	sum(if(t1.`day` = '23',t1.consumed, 0)) as twentythreeconsumed,
	sum(if(t1.`day` = '24',t1.consumed, 0)) as twentyfourconsumed,
	sum(if(t1.`day` = '25',t1.consumed, 0)) as twentyfiveconsumed,
	sum(if(t1.`day` = '26',t1.consumed, 0)) as twentysixconsumed,
	sum(if(t1.`day` = '27',t1.consumed, 0)) as twentysevenconsumed,
	sum(if(t1.`day` = '28',t1.consumed, 0)) as twentyeightconsumed,
	sum(if(t1.`day` = '29',t1.consumed, 0)) as twentynineconsumed,
	sum(if(t1.`day` = '30',t1.consumed, 0)) as thirtyconsumed,
	sum(if(t1.`day` = '31',t1.consumed, 0)) as thirtyoneconsumed,
	sum(if(t1.`day` = '01',t1.EVALUATIONTIME, 0)) as oneEVALUATIONTIME,
	sum(if(t1.`day` = '02',t1.EVALUATIONTIME, 0)) as twoEVALUATIONTIME,
	sum(if(t1.`day` = '03',t1.EVALUATIONTIME, 0)) as threeEVALUATIONTIME,
	sum(if(t1.`day` = '04',t1.EVALUATIONTIME, 0)) as fourEVALUATIONTIME,
	sum(if(t1.`day` = '05',t1.EVALUATIONTIME, 0)) as fiveEVALUATIONTIME,
	sum(if(t1.`day` = '06',t1.EVALUATIONTIME, 0)) as sixEVALUATIONTIME,
	sum(if(t1.`day` = '07',t1.EVALUATIONTIME, 0)) as sevenEVALUATIONTIME,
	sum(if(t1.`day` = '08',t1.EVALUATIONTIME, 0)) as eightEVALUATIONTIME,
	sum(if(t1.`day` = '09',t1.EVALUATIONTIME, 0)) as nineEVALUATIONTIME,
	sum(if(t1.`day` = '10',t1.EVALUATIONTIME, 0)) as tenEVALUATIONTIME,
	sum(if(t1.`day` = '11',t1.EVALUATIONTIME, 0)) as elevenEVALUATIONTIME,
	sum(if(t1.`day` = '12',t1.EVALUATIONTIME, 0)) as twelveEVALUATIONTIME,
	sum(if(t1.`day` = '13',t1.EVALUATIONTIME, 0)) as thirteenEVALUATIONTIME,
	sum(if(t1.`day` = '14',t1.EVALUATIONTIME, 0)) as fourteenEVALUATIONTIME,
	sum(if(t1.`day` = '15',t1.EVALUATIONTIME, 0)) as fifteenEVALUATIONTIME,
	sum(if(t1.`day` = '16',t1.EVALUATIONTIME, 0)) as sixteenEVALUATIONTIME,
	sum(if(t1.`day` = '17',t1.EVALUATIONTIME, 0)) as seventeenEVALUATIONTIME,
	sum(if(t1.`day` = '18',t1.EVALUATIONTIME, 0)) as eighteenEVALUATIONTIME,
	sum(if(t1.`day` = '19',t1.EVALUATIONTIME, 0)) as nineteenEVALUATIONTIME,
	sum(if(t1.`day` = '20',t1.EVALUATIONTIME, 0)) as twentyEVALUATIONTIME,
	sum(if(t1.`day` = '21',t1.EVALUATIONTIME, 0)) as twentyoneEVALUATIONTIME,
	sum(if(t1.`day` = '22',t1.EVALUATIONTIME, 0)) as twentytwoEVALUATIONTIME,
	sum(if(t1.`day` = '23',t1.EVALUATIONTIME, 0)) as twentythreeEVALUATIONTIME,
	sum(if(t1.`day` = '24',t1.EVALUATIONTIME, 0)) as twentyfourEVALUATIONTIME,
	sum(if(t1.`day` = '25',t1.EVALUATIONTIME, 0)) as twentyfiveEVALUATIONTIME,
	sum(if(t1.`day` = '26',t1.EVALUATIONTIME, 0)) as twentysixEVALUATIONTIME,
	sum(if(t1.`day` = '27',t1.EVALUATIONTIME, 0)) as twentysevenEVALUATIONTIME,
	sum(if(t1.`day` = '28',t1.EVALUATIONTIME, 0)) as twentyeightEVALUATIONTIME,
	sum(if(t1.`day` = '29',t1.EVALUATIONTIME, 0)) as twentynineEVALUATIONTIME,
	sum(if(t1.`day` = '30',t1.EVALUATIONTIME, 0)) as thirtyEVALUATIONTIME,
	sum(if(t1.`day` = '31',t1.EVALUATIONTIME, 0)) as thirtyoneEVALUATIONTIME,
	sum(if(t1.`day` = '01',t1.EVALUATIONCOST, 0)) as oneEVALUATIONCOST,
	sum(if(t1.`day` = '02',t1.EVALUATIONCOST, 0)) as twoEVALUATIONCOST,
	sum(if(t1.`day` = '03',t1.EVALUATIONCOST, 0)) as threeEVALUATIONCOST,
	sum(if(t1.`day` = '04',t1.EVALUATIONCOST, 0)) as fourEVALUATIONCOST,
	sum(if(t1.`day` = '05',t1.EVALUATIONCOST, 0)) as fiveEVALUATIONCOST,
	sum(if(t1.`day` = '06',t1.EVALUATIONCOST, 0)) as sixEVALUATIONCOST,
	sum(if(t1.`day` = '07',t1.EVALUATIONCOST, 0)) as sevenEVALUATIONCOST,
	sum(if(t1.`day` = '08',t1.EVALUATIONCOST, 0)) as eightEVALUATIONCOST,
	sum(if(t1.`day` = '09',t1.EVALUATIONCOST, 0)) as nineEVALUATIONCOST,
	sum(if(t1.`day` = '10',t1.EVALUATIONCOST, 0)) as tenEVALUATIONCOST,
	sum(if(t1.`day` = '11',t1.EVALUATIONCOST, 0)) as elevenEVALUATIONCOST,
	sum(if(t1.`day` = '12',t1.EVALUATIONCOST, 0)) as twelveEVALUATIONCOST,
	sum(if(t1.`day` = '13',t1.EVALUATIONCOST, 0)) as thirteenEVALUATIONCOST,
	sum(if(t1.`day` = '14',t1.EVALUATIONCOST, 0)) as fourteenEVALUATIONCOST,
	sum(if(t1.`day` = '15',t1.EVALUATIONCOST, 0)) as fifteenEVALUATIONCOST,
	sum(if(t1.`day` = '16',t1.EVALUATIONCOST, 0)) as sixteenEVALUATIONCOST,
	sum(if(t1.`day` = '17',t1.EVALUATIONCOST, 0)) as seventeenEVALUATIONCOST,
	sum(if(t1.`day` = '18',t1.EVALUATIONCOST, 0)) as eighteenEVALUATIONCOST,
	sum(if(t1.`day` = '19',t1.EVALUATIONCOST, 0)) as nineteenEVALUATIONCOST,
	sum(if(t1.`day` = '20',t1.EVALUATIONCOST, 0)) as twentyEVALUATIONCOST,
	sum(if(t1.`day` = '21',t1.EVALUATIONCOST, 0)) as twentyoneEVALUATIONCOST,
	sum(if(t1.`day` = '22',t1.EVALUATIONCOST, 0)) as twentytwoEVALUATIONCOST,
	sum(if(t1.`day` = '23',t1.EVALUATIONCOST, 0)) as twentythreeEVALUATIONCOST,
	sum(if(t1.`day` = '24',t1.EVALUATIONCOST, 0)) as twentyfourEVALUATIONCOST,
	sum(if(t1.`day` = '25',t1.EVALUATIONCOST, 0)) as twentyfiveEVALUATIONCOST,
	sum(if(t1.`day` = '26',t1.EVALUATIONCOST, 0)) as twentysixEVALUATIONCOST,
	sum(if(t1.`day` = '27',t1.EVALUATIONCOST, 0)) as twentysevenEVALUATIONCOST,
	sum(if(t1.`day` = '28',t1.EVALUATIONCOST, 0)) as twentyeightEVALUATIONCOST,
	sum(if(t1.`day` = '29',t1.EVALUATIONCOST, 0)) as twentynineEVALUATIONCOST,
	sum(if(t1.`day` = '30',t1.EVALUATIONCOST, 0)) as thirtyEVALUATIONCOST,
	sum(if(t1.`day` = '31',t1.EVALUATIONCOST, 0)) as thirtyoneEVALUATIONCOST,
	SUM(T1.consumed) AS consumed,SUM(T1.EVALUATIONCOST) AS EVALUATIONCOST, SUM(T1.INPUTCOST) AS INPUTCOST, SUM(T1.EVALUATIONTIME) AS EVALUATIONTIME,
(select ROUND(sum(t.consumed),2) from zt_taskestimate t left join zt_task tt on tt.id = t.task where tt.project <> t1.project and t.account = t1.account and DATE_FORMAT(t.date,'%Y%m') = concat(t1.`YEAR`, t1.`MONTH`)) as otherconsumed,
	(select ROUND(sum(t.EVALUATIONTIME),2) from zt_taskestimate t left join zt_task tt on tt.id = t.task where tt.project <> t1.project and t.account = t1.account and DATE_FORMAT(t.date,'%Y%m') = concat(t1.`YEAR`, t1.`MONTH`)) as otherEVALUATIONTIME
FROM
	(
SELECT YEAR
	( t1.date ) AS `year`,
	Right(100 + MONTH ( t1.date ), 2) AS `month`,
	Right(100 + day ( t1.date ), 2) AS `day`,
	t11.project,
	t21.`name` AS projectname,
	t1.account,
	t1.date,
	IFNULL( ROUND( sum( t1.consumed ), 2 ), 0 ) AS consumed,
	IFNULL( ROUND( sum( t1.EVALUATIONCOST ), 2 ), 0 ) AS EVALUATIONCOST,
	IFNULL( ROUND( sum( t1.INPUTCOST ), 2 ), 0 ) AS INPUTCOST,
	IFNULL( ROUND( sum( t1.EVALUATIONTIME ), 2 ), 0 ) AS EVALUATIONTIME
FROM
	`zt_taskestimate` t1
	LEFT JOIN `zt_task` t11 ON t1.`TASK` = t11.`ID`
	LEFT JOIN `zt_project` t21 ON t11.`PROJECT` = t21.`ID` 
WHERE
	t1.date <> '0000-00-00'  and (t11.deleted = '0' or t11.deleted is null)
GROUP BY
	t11.project,
	t21.`name`,
	t1.account,
	t1.date 
union 
select 
  YEAR( t1.date ) AS `year`,
	Right(100 + MONTH ( t1.date ), 2) AS `month`,
	Right(100 + day ( t1.date ), 2) AS `day`,
	t1.idvalue as project,
	t11.`name` AS projectname,
	t1.account,
	t1.date,
	ROUND( sum( t1.consumed ), 2 ) AS consumed,
	0.00 AS EVALUATIONCOST,
	0.00 AS INPUTCOST,
	0.00 AS EVALUATIONTIME
	from zt_todo t1 left join zt_project t11 on t11.id = t1.idvalue 
	where t1.type = 'custom' and t1.idvalue > 0
GROUP BY
	t1.idvalue,
	t11.`name`,
	t1.account,
	t1.date 
	) t1 GROUP BY t1.account, t1.project,t1.projectname,t1.`YEAR`,t1.`MONTH`) T1
WHERE t1.account =#{srf.webcontext.account} 
t1.`MONTH`=#{srf.webcontext.month} 

```
### 项目下用户工时详情(CurProjectUserEstimate)<div id="ProjectTaskestimate_CurProjectUserEstimate"></div>
```sql
SELECT
	t1.project,
	t1.projectname,
	t1.`YEAR`,
	concat( t1.`YEAR`, t1.`MONTH` ) AS `MONTH`,
	sum( IF ( t1.`day` = '01', t1.consumed, 0 ) ) AS oneconsumed,
	sum( IF ( t1.`day` = '02', t1.consumed, 0 ) ) AS twoconsumed,
	sum( IF ( t1.`day` = '03', t1.consumed, 0 ) ) AS threeconsumed,
	sum( IF ( t1.`day` = '04', t1.consumed, 0 ) ) AS FOURONSUMED,
	sum( IF ( t1.`day` = '05', t1.consumed, 0 ) ) AS fiveconsumed,
	sum( IF ( t1.`day` = '06', t1.consumed, 0 ) ) AS sixconsumed,
	sum( IF ( t1.`day` = '07', t1.consumed, 0 ) ) AS sevenconsumed,
	sum( IF ( t1.`day` = '08', t1.consumed, 0 ) ) AS eightconsumed,
	sum( IF ( t1.`day` = '09', t1.consumed, 0 ) ) AS nineconsumed,
	sum( IF ( t1.`day` = '10', t1.consumed, 0 ) ) AS tenconsumed,
	sum( IF ( t1.`day` = '11', t1.consumed, 0 ) ) AS elevenconsumed,
	sum( IF ( t1.`day` = '12', t1.consumed, 0 ) ) AS twelveconsumed,
	sum( IF ( t1.`day` = '13', t1.consumed, 0 ) ) AS thirteenconsumed,
	sum( IF ( t1.`day` = '14', t1.consumed, 0 ) ) AS fourteenconsumed,
	sum( IF ( t1.`day` = '15', t1.consumed, 0 ) ) AS fifteenconsumed,
	sum( IF ( t1.`day` = '16', t1.consumed, 0 ) ) AS sixteenconsumed,
	sum( IF ( t1.`day` = '17', t1.consumed, 0 ) ) AS seventeenconsumed,
	sum( IF ( t1.`day` = '18', t1.consumed, 0 ) ) AS eighteenconsumed,
	sum( IF ( t1.`day` = '19', t1.consumed, 0 ) ) AS nineteenconsumed,
	sum( IF ( t1.`day` = '20', t1.consumed, 0 ) ) AS twentyconsumed,
	sum( IF ( t1.`day` = '21', t1.consumed, 0 ) ) AS twentyoneconsumed,
	sum( IF ( t1.`day` = '22', t1.consumed, 0 ) ) AS twentytwoconsumed,
	sum( IF ( t1.`day` = '23', t1.consumed, 0 ) ) AS twentythreeconsumed,
	sum( IF ( t1.`day` = '24', t1.consumed, 0 ) ) AS twentyfourconsumed,
	sum( IF ( t1.`day` = '25', t1.consumed, 0 ) ) AS twentyfiveconsumed,
	sum( IF ( t1.`day` = '26', t1.consumed, 0 ) ) AS twentysixconsumed,
	sum( IF ( t1.`day` = '27', t1.consumed, 0 ) ) AS twentysevenconsumed,
	sum( IF ( t1.`day` = '28', t1.consumed, 0 ) ) AS twentyeightconsumed,
	sum( IF ( t1.`day` = '29', t1.consumed, 0 ) ) AS twentynineconsumed,
	sum( IF ( t1.`day` = '30', t1.consumed, 0 ) ) AS thirtyconsumed,
	sum( IF ( t1.`day` = '31', t1.consumed, 0 ) ) AS thirtyoneconsumed,
	sum( IF ( t1.`day` = '01', t1.EVALUATIONTIME, 0 ) ) AS oneEVALUATIONTIME,
	sum( IF ( t1.`day` = '02', t1.EVALUATIONTIME, 0 ) ) AS twoEVALUATIONTIME,
	sum( IF ( t1.`day` = '03', t1.EVALUATIONTIME, 0 ) ) AS threeEVALUATIONTIME,
	sum( IF ( t1.`day` = '04', t1.EVALUATIONTIME, 0 ) ) AS fourEVALUATIONTIME,
	sum( IF ( t1.`day` = '05', t1.EVALUATIONTIME, 0 ) ) AS fiveEVALUATIONTIME,
	sum( IF ( t1.`day` = '06', t1.EVALUATIONTIME, 0 ) ) AS sixEVALUATIONTIME,
	sum( IF ( t1.`day` = '07', t1.EVALUATIONTIME, 0 ) ) AS sevenEVALUATIONTIME,
	sum( IF ( t1.`day` = '08', t1.EVALUATIONTIME, 0 ) ) AS eightEVALUATIONTIME,
	sum( IF ( t1.`day` = '09', t1.EVALUATIONTIME, 0 ) ) AS nineEVALUATIONTIME,
	sum( IF ( t1.`day` = '10', t1.EVALUATIONTIME, 0 ) ) AS tenEVALUATIONTIME,
	sum( IF ( t1.`day` = '11', t1.EVALUATIONTIME, 0 ) ) AS elevenEVALUATIONTIME,
	sum( IF ( t1.`day` = '12', t1.EVALUATIONTIME, 0 ) ) AS twelveEVALUATIONTIME,
	sum( IF ( t1.`day` = '13', t1.EVALUATIONTIME, 0 ) ) AS thirteenEVALUATIONTIME,
	sum( IF ( t1.`day` = '14', t1.EVALUATIONTIME, 0 ) ) AS fourteenEVALUATIONTIME,
	sum( IF ( t1.`day` = '15', t1.EVALUATIONTIME, 0 ) ) AS fifteenEVALUATIONTIME,
	sum( IF ( t1.`day` = '16', t1.EVALUATIONTIME, 0 ) ) AS sixteenEVALUATIONTIME,
	sum( IF ( t1.`day` = '17', t1.EVALUATIONTIME, 0 ) ) AS seventeenEVALUATIONTIME,
	sum( IF ( t1.`day` = '18', t1.EVALUATIONTIME, 0 ) ) AS eighteenEVALUATIONTIME,
	sum( IF ( t1.`day` = '19', t1.EVALUATIONTIME, 0 ) ) AS nineteenEVALUATIONTIME,
	sum( IF ( t1.`day` = '20', t1.EVALUATIONTIME, 0 ) ) AS twentyEVALUATIONTIME,
	sum( IF ( t1.`day` = '21', t1.EVALUATIONTIME, 0 ) ) AS twentyoneEVALUATIONTIME,
	sum( IF ( t1.`day` = '22', t1.EVALUATIONTIME, 0 ) ) AS twentytwoEVALUATIONTIME,
	sum( IF ( t1.`day` = '23', t1.EVALUATIONTIME, 0 ) ) AS twentythreeEVALUATIONTIME,
	sum( IF ( t1.`day` = '24', t1.EVALUATIONTIME, 0 ) ) AS twentyfourEVALUATIONTIME,
	sum( IF ( t1.`day` = '25', t1.EVALUATIONTIME, 0 ) ) AS twentyfiveEVALUATIONTIME,
	sum( IF ( t1.`day` = '26', t1.EVALUATIONTIME, 0 ) ) AS twentysixEVALUATIONTIME,
	sum( IF ( t1.`day` = '27', t1.EVALUATIONTIME, 0 ) ) AS twentysevenEVALUATIONTIME,
	sum( IF ( t1.`day` = '28', t1.EVALUATIONTIME, 0 ) ) AS twentyeightEVALUATIONTIME,
	sum( IF ( t1.`day` = '29', t1.EVALUATIONTIME, 0 ) ) AS twentynineEVALUATIONTIME,
	sum( IF ( t1.`day` = '30', t1.EVALUATIONTIME, 0 ) ) AS thirtyEVALUATIONTIME,
	sum( IF ( t1.`day` = '31', t1.EVALUATIONTIME, 0 ) ) AS thirtyoneEVALUATIONTIME,
	sum( IF ( t1.`day` = '01', t1.EVALUATIONCOST, 0 ) ) AS oneEVALUATIONCOST,
	sum( IF ( t1.`day` = '02', t1.EVALUATIONCOST, 0 ) ) AS twoEVALUATIONCOST,
	sum( IF ( t1.`day` = '03', t1.EVALUATIONCOST, 0 ) ) AS threeEVALUATIONCOST,
	sum( IF ( t1.`day` = '04', t1.EVALUATIONCOST, 0 ) ) AS fourEVALUATIONCOST,
	sum( IF ( t1.`day` = '05', t1.EVALUATIONCOST, 0 ) ) AS fiveEVALUATIONCOST,
	sum( IF ( t1.`day` = '06', t1.EVALUATIONCOST, 0 ) ) AS sixEVALUATIONCOST,
	sum( IF ( t1.`day` = '07', t1.EVALUATIONCOST, 0 ) ) AS sevenEVALUATIONCOST,
	sum( IF ( t1.`day` = '08', t1.EVALUATIONCOST, 0 ) ) AS eightEVALUATIONCOST,
	sum( IF ( t1.`day` = '09', t1.EVALUATIONCOST, 0 ) ) AS nineEVALUATIONCOST,
	sum( IF ( t1.`day` = '10', t1.EVALUATIONCOST, 0 ) ) AS tenEVALUATIONCOST,
	sum( IF ( t1.`day` = '11', t1.EVALUATIONCOST, 0 ) ) AS elevenEVALUATIONCOST,
	sum( IF ( t1.`day` = '12', t1.EVALUATIONCOST, 0 ) ) AS twelveEVALUATIONCOST,
	sum( IF ( t1.`day` = '13', t1.EVALUATIONCOST, 0 ) ) AS thirteenEVALUATIONCOST,
	sum( IF ( t1.`day` = '14', t1.EVALUATIONCOST, 0 ) ) AS fourteenEVALUATIONCOST,
	sum( IF ( t1.`day` = '15', t1.EVALUATIONCOST, 0 ) ) AS fifteenEVALUATIONCOST,
	sum( IF ( t1.`day` = '16', t1.EVALUATIONCOST, 0 ) ) AS sixteenEVALUATIONCOST,
	sum( IF ( t1.`day` = '17', t1.EVALUATIONCOST, 0 ) ) AS seventeenEVALUATIONCOST,
	sum( IF ( t1.`day` = '18', t1.EVALUATIONCOST, 0 ) ) AS eighteenEVALUATIONCOST,
	sum( IF ( t1.`day` = '19', t1.EVALUATIONCOST, 0 ) ) AS nineteenEVALUATIONCOST,
	sum( IF ( t1.`day` = '20', t1.EVALUATIONCOST, 0 ) ) AS twentyEVALUATIONCOST,
	sum( IF ( t1.`day` = '21', t1.EVALUATIONCOST, 0 ) ) AS twentyoneEVALUATIONCOST,
	sum( IF ( t1.`day` = '22', t1.EVALUATIONCOST, 0 ) ) AS twentytwoEVALUATIONCOST,
	sum( IF ( t1.`day` = '23', t1.EVALUATIONCOST, 0 ) ) AS twentythreeEVALUATIONCOST,
	sum( IF ( t1.`day` = '24', t1.EVALUATIONCOST, 0 ) ) AS twentyfourEVALUATIONCOST,
	sum( IF ( t1.`day` = '25', t1.EVALUATIONCOST, 0 ) ) AS twentyfiveEVALUATIONCOST,
	sum( IF ( t1.`day` = '26', t1.EVALUATIONCOST, 0 ) ) AS twentysixEVALUATIONCOST,
	sum( IF ( t1.`day` = '27', t1.EVALUATIONCOST, 0 ) ) AS twentysevenEVALUATIONCOST,
	sum( IF ( t1.`day` = '28', t1.EVALUATIONCOST, 0 ) ) AS twentyeightEVALUATIONCOST,
	sum( IF ( t1.`day` = '29', t1.EVALUATIONCOST, 0 ) ) AS twentynineEVALUATIONCOST,
	sum( IF ( t1.`day` = '30', t1.EVALUATIONCOST, 0 ) ) AS thirtyEVALUATIONCOST,
	sum( IF ( t1.`day` = '31', t1.EVALUATIONCOST, 0 ) ) AS thirtyoneEVALUATIONCOST,
	SUM( T1.consumed ) AS consumed,
	SUM( T1.EVALUATIONCOST ) AS EVALUATIONCOST,
	SUM( T1.INPUTCOST ) AS INPUTCOST,
	SUM( T1.EVALUATIONTIME ) AS EVALUATIONTIME
 
FROM
(

SELECT YEAR
	( t1.date ) AS `year`,
	RIGHT ( 100 + MONTH ( t1.date ), 2 ) AS `month`,
	RIGHT ( 100 + DAY ( t1.date ), 2 ) AS `day`,
	t11.project,
	t21.`name` AS projectname,
	t1.date,
	IFNULL(ROUND( sum( t1.consumed ), 2 ),0) AS consumed,
        IFNULL(ROUND( sum( t1.EVALUATIONCOST ), 2 ),0) AS EVALUATIONCOST,
        IFNULL(ROUND( sum( t1.INPUTCOST ), 2 ),0) AS INPUTCOST,
        IFNULL(ROUND( sum( t1.EVALUATIONTIME ), 2 ),0) AS EVALUATIONTIME 
FROM
	`zt_taskestimate` t1
	LEFT JOIN `zt_task` t11 ON t1.`TASK` = t11.`ID`
	LEFT JOIN `zt_project` t21 ON t11.`PROJECT` = t21.`ID` 
WHERE
	
	t1.date <> '0000-00-00' 
	AND  (t11.deleted = '0'  or t11.deleted is null)
GROUP BY
	t11.project,
	t21.`name`,
	t1.date UNION
SELECT YEAR
	( t1.date ) AS `year`,
	RIGHT ( 100 + MONTH ( t1.date ), 2 ) AS `month`,
	RIGHT ( 100 + DAY ( t1.date ), 2 ) AS `day`,
	t1.idvalue AS project,
	t11.`name` AS projectname,
	t1.date,
	ROUND( sum( t1.consumed ), 2 ) AS consumed,
	0.00 AS EVALUATIONCOST,
	0.00 AS INPUTCOST,
	0.00 AS EVALUATIONTIME 
FROM
	zt_todo t1
	LEFT JOIN zt_project t11 ON t11.id = t1.idvalue 
WHERE
	t1.type = 'custom' 
	AND t1.idvalue > 0 
GROUP BY
	t1.idvalue,
	t11.`name`,
	t1.date 
	)t1
	GROUP BY
	t1.project,
	t1.`YEAR`,
	t1.`MONTH`
```
### 当前项目下用户们工时详情(CurProjectUserEstimateDetail)<div id="ProjectTaskestimate_CurProjectUserEstimateDetail"></div>
```sql
SELECT
	T1.* 
FROM
	(
SELECT
	t1.account,
	t1.project,
	t1.projectname,
	t1.`YEAR`,
	concat( t1.`YEAR`, t1.`MONTH` ) AS `MONTH`,
	sum( IF ( t1.`day` = '01', t1.consumed, 0 ) ) AS oneconsumed,
	sum( IF ( t1.`day` = '02', t1.consumed, 0 ) ) AS twoconsumed,
	sum( IF ( t1.`day` = '03', t1.consumed, 0 ) ) AS threeconsumed,
	sum( IF ( t1.`day` = '04', t1.consumed, 0 ) ) AS FOURONSUMED,
	sum( IF ( t1.`day` = '05', t1.consumed, 0 ) ) AS fiveconsumed,
	sum( IF ( t1.`day` = '06', t1.consumed, 0 ) ) AS sixconsumed,
	sum( IF ( t1.`day` = '07', t1.consumed, 0 ) ) AS sevenconsumed,
	sum( IF ( t1.`day` = '08', t1.consumed, 0 ) ) AS eightconsumed,
	sum( IF ( t1.`day` = '09', t1.consumed, 0 ) ) AS nineconsumed,
	sum( IF ( t1.`day` = '10', t1.consumed, 0 ) ) AS tenconsumed,
	sum( IF ( t1.`day` = '11', t1.consumed, 0 ) ) AS elevenconsumed,
	sum( IF ( t1.`day` = '12', t1.consumed, 0 ) ) AS twelveconsumed,
	sum( IF ( t1.`day` = '13', t1.consumed, 0 ) ) AS thirteenconsumed,
	sum( IF ( t1.`day` = '14', t1.consumed, 0 ) ) AS fourteenconsumed,
	sum( IF ( t1.`day` = '15', t1.consumed, 0 ) ) AS fifteenconsumed,
	sum( IF ( t1.`day` = '16', t1.consumed, 0 ) ) AS sixteenconsumed,
	sum( IF ( t1.`day` = '17', t1.consumed, 0 ) ) AS seventeenconsumed,
	sum( IF ( t1.`day` = '18', t1.consumed, 0 ) ) AS eighteenconsumed,
	sum( IF ( t1.`day` = '19', t1.consumed, 0 ) ) AS nineteenconsumed,
	sum( IF ( t1.`day` = '20', t1.consumed, 0 ) ) AS twentyconsumed,
	sum( IF ( t1.`day` = '21', t1.consumed, 0 ) ) AS twentyoneconsumed,
	sum( IF ( t1.`day` = '22', t1.consumed, 0 ) ) AS twentytwoconsumed,
	sum( IF ( t1.`day` = '23', t1.consumed, 0 ) ) AS twentythreeconsumed,
	sum( IF ( t1.`day` = '24', t1.consumed, 0 ) ) AS twentyfourconsumed,
	sum( IF ( t1.`day` = '25', t1.consumed, 0 ) ) AS twentyfiveconsumed,
	sum( IF ( t1.`day` = '26', t1.consumed, 0 ) ) AS twentysixconsumed,
	sum( IF ( t1.`day` = '27', t1.consumed, 0 ) ) AS twentysevenconsumed,
	sum( IF ( t1.`day` = '28', t1.consumed, 0 ) ) AS twentyeightconsumed,
	sum( IF ( t1.`day` = '29', t1.consumed, 0 ) ) AS twentynineconsumed,
	sum( IF ( t1.`day` = '30', t1.consumed, 0 ) ) AS thirtyconsumed,
	sum( IF ( t1.`day` = '31', t1.consumed, 0 ) ) AS thirtyoneconsumed,
	sum( IF ( t1.`day` = '01', t1.EVALUATIONTIME, 0 ) ) AS oneEVALUATIONTIME,
	sum( IF ( t1.`day` = '02', t1.EVALUATIONTIME, 0 ) ) AS twoEVALUATIONTIME,
	sum( IF ( t1.`day` = '03', t1.EVALUATIONTIME, 0 ) ) AS threeEVALUATIONTIME,
	sum( IF ( t1.`day` = '04', t1.EVALUATIONTIME, 0 ) ) AS fourEVALUATIONTIME,
	sum( IF ( t1.`day` = '05', t1.EVALUATIONTIME, 0 ) ) AS fiveEVALUATIONTIME,
	sum( IF ( t1.`day` = '06', t1.EVALUATIONTIME, 0 ) ) AS sixEVALUATIONTIME,
	sum( IF ( t1.`day` = '07', t1.EVALUATIONTIME, 0 ) ) AS sevenEVALUATIONTIME,
	sum( IF ( t1.`day` = '08', t1.EVALUATIONTIME, 0 ) ) AS eightEVALUATIONTIME,
	sum( IF ( t1.`day` = '09', t1.EVALUATIONTIME, 0 ) ) AS nineEVALUATIONTIME,
	sum( IF ( t1.`day` = '10', t1.EVALUATIONTIME, 0 ) ) AS tenEVALUATIONTIME,
	sum( IF ( t1.`day` = '11', t1.EVALUATIONTIME, 0 ) ) AS elevenEVALUATIONTIME,
	sum( IF ( t1.`day` = '12', t1.EVALUATIONTIME, 0 ) ) AS twelveEVALUATIONTIME,
	sum( IF ( t1.`day` = '13', t1.EVALUATIONTIME, 0 ) ) AS thirteenEVALUATIONTIME,
	sum( IF ( t1.`day` = '14', t1.EVALUATIONTIME, 0 ) ) AS fourteenEVALUATIONTIME,
	sum( IF ( t1.`day` = '15', t1.EVALUATIONTIME, 0 ) ) AS fifteenEVALUATIONTIME,
	sum( IF ( t1.`day` = '16', t1.EVALUATIONTIME, 0 ) ) AS sixteenEVALUATIONTIME,
	sum( IF ( t1.`day` = '17', t1.EVALUATIONTIME, 0 ) ) AS seventeenEVALUATIONTIME,
	sum( IF ( t1.`day` = '18', t1.EVALUATIONTIME, 0 ) ) AS eighteenEVALUATIONTIME,
	sum( IF ( t1.`day` = '19', t1.EVALUATIONTIME, 0 ) ) AS nineteenEVALUATIONTIME,
	sum( IF ( t1.`day` = '20', t1.EVALUATIONTIME, 0 ) ) AS twentyEVALUATIONTIME,
	sum( IF ( t1.`day` = '21', t1.EVALUATIONTIME, 0 ) ) AS twentyoneEVALUATIONTIME,
	sum( IF ( t1.`day` = '22', t1.EVALUATIONTIME, 0 ) ) AS twentytwoEVALUATIONTIME,
	sum( IF ( t1.`day` = '23', t1.EVALUATIONTIME, 0 ) ) AS twentythreeEVALUATIONTIME,
	sum( IF ( t1.`day` = '24', t1.EVALUATIONTIME, 0 ) ) AS twentyfourEVALUATIONTIME,
	sum( IF ( t1.`day` = '25', t1.EVALUATIONTIME, 0 ) ) AS twentyfiveEVALUATIONTIME,
	sum( IF ( t1.`day` = '26', t1.EVALUATIONTIME, 0 ) ) AS twentysixEVALUATIONTIME,
	sum( IF ( t1.`day` = '27', t1.EVALUATIONTIME, 0 ) ) AS twentysevenEVALUATIONTIME,
	sum( IF ( t1.`day` = '28', t1.EVALUATIONTIME, 0 ) ) AS twentyeightEVALUATIONTIME,
	sum( IF ( t1.`day` = '29', t1.EVALUATIONTIME, 0 ) ) AS twentynineEVALUATIONTIME,
	sum( IF ( t1.`day` = '30', t1.EVALUATIONTIME, 0 ) ) AS thirtyEVALUATIONTIME,
	sum( IF ( t1.`day` = '31', t1.EVALUATIONTIME, 0 ) ) AS thirtyoneEVALUATIONTIME,
	sum( IF ( t1.`day` = '01', t1.EVALUATIONCOST, 0 ) ) AS oneEVALUATIONCOST,
	sum( IF ( t1.`day` = '02', t1.EVALUATIONCOST, 0 ) ) AS twoEVALUATIONCOST,
	sum( IF ( t1.`day` = '03', t1.EVALUATIONCOST, 0 ) ) AS threeEVALUATIONCOST,
	sum( IF ( t1.`day` = '04', t1.EVALUATIONCOST, 0 ) ) AS fourEVALUATIONCOST,
	sum( IF ( t1.`day` = '05', t1.EVALUATIONCOST, 0 ) ) AS fiveEVALUATIONCOST,
	sum( IF ( t1.`day` = '06', t1.EVALUATIONCOST, 0 ) ) AS sixEVALUATIONCOST,
	sum( IF ( t1.`day` = '07', t1.EVALUATIONCOST, 0 ) ) AS sevenEVALUATIONCOST,
	sum( IF ( t1.`day` = '08', t1.EVALUATIONCOST, 0 ) ) AS eightEVALUATIONCOST,
	sum( IF ( t1.`day` = '09', t1.EVALUATIONCOST, 0 ) ) AS nineEVALUATIONCOST,
	sum( IF ( t1.`day` = '10', t1.EVALUATIONCOST, 0 ) ) AS tenEVALUATIONCOST,
	sum( IF ( t1.`day` = '11', t1.EVALUATIONCOST, 0 ) ) AS elevenEVALUATIONCOST,
	sum( IF ( t1.`day` = '12', t1.EVALUATIONCOST, 0 ) ) AS twelveEVALUATIONCOST,
	sum( IF ( t1.`day` = '13', t1.EVALUATIONCOST, 0 ) ) AS thirteenEVALUATIONCOST,
	sum( IF ( t1.`day` = '14', t1.EVALUATIONCOST, 0 ) ) AS fourteenEVALUATIONCOST,
	sum( IF ( t1.`day` = '15', t1.EVALUATIONCOST, 0 ) ) AS fifteenEVALUATIONCOST,
	sum( IF ( t1.`day` = '16', t1.EVALUATIONCOST, 0 ) ) AS sixteenEVALUATIONCOST,
	sum( IF ( t1.`day` = '17', t1.EVALUATIONCOST, 0 ) ) AS seventeenEVALUATIONCOST,
	sum( IF ( t1.`day` = '18', t1.EVALUATIONCOST, 0 ) ) AS eighteenEVALUATIONCOST,
	sum( IF ( t1.`day` = '19', t1.EVALUATIONCOST, 0 ) ) AS nineteenEVALUATIONCOST,
	sum( IF ( t1.`day` = '20', t1.EVALUATIONCOST, 0 ) ) AS twentyEVALUATIONCOST,
	sum( IF ( t1.`day` = '21', t1.EVALUATIONCOST, 0 ) ) AS twentyoneEVALUATIONCOST,
	sum( IF ( t1.`day` = '22', t1.EVALUATIONCOST, 0 ) ) AS twentytwoEVALUATIONCOST,
	sum( IF ( t1.`day` = '23', t1.EVALUATIONCOST, 0 ) ) AS twentythreeEVALUATIONCOST,
	sum( IF ( t1.`day` = '24', t1.EVALUATIONCOST, 0 ) ) AS twentyfourEVALUATIONCOST,
	sum( IF ( t1.`day` = '25', t1.EVALUATIONCOST, 0 ) ) AS twentyfiveEVALUATIONCOST,
	sum( IF ( t1.`day` = '26', t1.EVALUATIONCOST, 0 ) ) AS twentysixEVALUATIONCOST,
	sum( IF ( t1.`day` = '27', t1.EVALUATIONCOST, 0 ) ) AS twentysevenEVALUATIONCOST,
	sum( IF ( t1.`day` = '28', t1.EVALUATIONCOST, 0 ) ) AS twentyeightEVALUATIONCOST,
	sum( IF ( t1.`day` = '29', t1.EVALUATIONCOST, 0 ) ) AS twentynineEVALUATIONCOST,
	sum( IF ( t1.`day` = '30', t1.EVALUATIONCOST, 0 ) ) AS thirtyEVALUATIONCOST,
	sum( IF ( t1.`day` = '31', t1.EVALUATIONCOST, 0 ) ) AS thirtyoneEVALUATIONCOST,
        IFNULL( SUM( T1.consumed ) , 0 ) AS consumed,
	IFNULL( SUM( T1.EVALUATIONCOST ), 0 )  AS EVALUATIONCOST,
        IFNULL( SUM( T1.INPUTCOST ), 0 )  AS INPUTCOST,
        IFNULL(SUM( T1.EVALUATIONTIME ) , 0 )  AS EVALUATIONTIME,
	(
SELECT
	ROUND( sum( t.consumed ), 2 ) 
FROM
	zt_taskestimate t
	LEFT JOIN zt_task tt ON tt.id = t.task 
WHERE
	tt.project <> t1.project 
	AND t.account = t1.account 
	AND DATE_FORMAT( t.date, '%Y%m' ) = concat( t1.`YEAR`, t1.`MONTH` ) 
	) AS otherconsumed,
	(
SELECT
	ROUND( sum( t.EVALUATIONTIME ), 2 ) 
FROM
	zt_taskestimate t
	LEFT JOIN zt_task tt ON tt.id = t.task 
WHERE
	tt.project <> t1.project 
	AND t.account = t1.account 
	AND DATE_FORMAT( t.date, '%Y%m' ) = concat( t1.`YEAR`, t1.`MONTH` ) 
	) AS otherEVALUATIONTIME 
FROM
	(
SELECT YEAR
	( t1.date ) AS `year`,
	RIGHT ( 100 + MONTH ( t1.date ), 2 ) AS `month`,
	RIGHT ( 100 + DAY ( t1.date ), 2 ) AS `day`,
	t11.project,
	t21.`name` AS projectname,
	t1.account,
	t1.date,
	IFNULL( ROUND( sum( t1.consumed ), 2 ), 0 ) AS consumed,
	IFNULL( ROUND( sum( t1.EVALUATIONCOST ), 2 ), 0 ) AS EVALUATIONCOST,
	IFNULL( ROUND( sum( t1.INPUTCOST ), 2 ), 0 ) AS INPUTCOST,
	IFNULL( ROUND( sum( t1.EVALUATIONTIME ), 2 ), 0 ) AS EVALUATIONTIME  
FROM
	`zt_taskestimate` t1
	LEFT JOIN `zt_task` t11 ON t1.`TASK` = t11.`ID`
	LEFT JOIN `zt_project` t21 ON t11.`PROJECT` = t21.`ID` 
WHERE
	 t1.date <> '0000-00-00' 
	AND (t11.deleted = '0'  or t11.deleted  is null)
GROUP BY
	t11.project,
	t21.`name`,
	t1.account,
	t1.date UNION
SELECT YEAR
	( t1.date ) AS `year`,
	RIGHT ( 100 + MONTH ( t1.date ), 2 ) AS `month`,
	RIGHT ( 100 + DAY ( t1.date ), 2 ) AS `day`,
	t1.idvalue AS project,
	t11.`name` AS projectname,
	t1.account,
	t1.date,
	IFNULL( ROUND( sum( t1.consumed ), 2 ), 0 ) AS consumed,
	0.00 AS EVALUATIONCOST,
	0.00 AS INPUTCOST,
	0.00 AS EVALUATIONTIME 
FROM
	zt_todo t1
	LEFT JOIN zt_project t11 ON t11.id = t1.idvalue 
WHERE
	t1.type = 'custom' 
	AND t1.idvalue > 0 
GROUP BY
	t1.idvalue,
	t11.`name`,
	t1.account,
	t1.date 
	) t1 
GROUP BY
	t1.account,
	t1.project,
	t1.projectname,
	t1.`YEAR`,
	t1.`MONTH` 
	) T1
WHERE (t1.project = #{srf.webcontext.project} or (#{srf.webcontext.project} is null and t1.project is null)) 
t1.`MONTH`=#{srf.webcontext.month} 

```
### 数据查询(DEFAULT)<div id="ProjectTaskestimate_Default"></div>
```sql
SELECT T1.* FROM (SELECT
	t1.account,t1.project,t1.projectname,t1.`YEAR`,concat(t1.`YEAR`, t1.`MONTH`) as `MONTH`,
	sum(if(t1.`day` = '01',t1.consumed, 0)) as oneconsumed,
	sum(if(t1.`day` = '02',t1.consumed, 0)) as twoconsumed,
	sum(if(t1.`day` = '03',t1.consumed, 0)) as threeconsumed,
	sum(if(t1.`day` = '04',t1.consumed, 0)) as FOURONSUMED,
	sum(if(t1.`day` = '05',t1.consumed, 0)) as fiveconsumed,
	sum(if(t1.`day` = '06',t1.consumed, 0)) as sixconsumed,
	sum(if(t1.`day` = '07',t1.consumed, 0)) as sevenconsumed,
	sum(if(t1.`day` = '08',t1.consumed, 0)) as eightconsumed,
	sum(if(t1.`day` = '09',t1.consumed, 0)) as nineconsumed,
	sum(if(t1.`day` = '10',t1.consumed, 0)) as tenconsumed,
	sum(if(t1.`day` = '11',t1.consumed, 0)) as elevenconsumed,
	sum(if(t1.`day` = '12',t1.consumed, 0)) as twelveconsumed,
	sum(if(t1.`day` = '13',t1.consumed, 0)) as thirteenconsumed,
	sum(if(t1.`day` = '14',t1.consumed, 0)) as fourteenconsumed,
	sum(if(t1.`day` = '15',t1.consumed, 0)) as fifteenconsumed,
	sum(if(t1.`day` = '16',t1.consumed, 0)) as sixteenconsumed,
	sum(if(t1.`day` = '17',t1.consumed, 0)) as seventeenconsumed,
	sum(if(t1.`day` = '18',t1.consumed, 0)) as eighteenconsumed,
	sum(if(t1.`day` = '19',t1.consumed, 0)) as nineteenconsumed,
	sum(if(t1.`day` = '20',t1.consumed, 0)) as twentyconsumed,
	sum(if(t1.`day` = '21',t1.consumed, 0)) as twentyoneconsumed,
	sum(if(t1.`day` = '22',t1.consumed, 0)) as twentytwoconsumed,
	sum(if(t1.`day` = '23',t1.consumed, 0)) as twentythreeconsumed,
	sum(if(t1.`day` = '24',t1.consumed, 0)) as twentyfourconsumed,
	sum(if(t1.`day` = '25',t1.consumed, 0)) as twentyfiveconsumed,
	sum(if(t1.`day` = '26',t1.consumed, 0)) as twentysixconsumed,
	sum(if(t1.`day` = '27',t1.consumed, 0)) as twentysevenconsumed,
	sum(if(t1.`day` = '28',t1.consumed, 0)) as twentyeightconsumed,
	sum(if(t1.`day` = '29',t1.consumed, 0)) as twentynineconsumed,
	sum(if(t1.`day` = '30',t1.consumed, 0)) as thirtyconsumed,
	sum(if(t1.`day` = '31',t1.consumed, 0)) as thirtyoneconsumed,
	sum(if(t1.`day` = '01',t1.EVALUATIONTIME, 0)) as oneEVALUATIONTIME,
	sum(if(t1.`day` = '02',t1.EVALUATIONTIME, 0)) as twoEVALUATIONTIME,
	sum(if(t1.`day` = '03',t1.EVALUATIONTIME, 0)) as threeEVALUATIONTIME,
	sum(if(t1.`day` = '04',t1.EVALUATIONTIME, 0)) as fourEVALUATIONTIME,
	sum(if(t1.`day` = '05',t1.EVALUATIONTIME, 0)) as fiveEVALUATIONTIME,
	sum(if(t1.`day` = '06',t1.EVALUATIONTIME, 0)) as sixEVALUATIONTIME,
	sum(if(t1.`day` = '07',t1.EVALUATIONTIME, 0)) as sevenEVALUATIONTIME,
	sum(if(t1.`day` = '08',t1.EVALUATIONTIME, 0)) as eightEVALUATIONTIME,
	sum(if(t1.`day` = '09',t1.EVALUATIONTIME, 0)) as nineEVALUATIONTIME,
	sum(if(t1.`day` = '10',t1.EVALUATIONTIME, 0)) as tenEVALUATIONTIME,
	sum(if(t1.`day` = '11',t1.EVALUATIONTIME, 0)) as elevenEVALUATIONTIME,
	sum(if(t1.`day` = '12',t1.EVALUATIONTIME, 0)) as twelveEVALUATIONTIME,
	sum(if(t1.`day` = '13',t1.EVALUATIONTIME, 0)) as thirteenEVALUATIONTIME,
	sum(if(t1.`day` = '14',t1.EVALUATIONTIME, 0)) as fourteenEVALUATIONTIME,
	sum(if(t1.`day` = '15',t1.EVALUATIONTIME, 0)) as fifteenEVALUATIONTIME,
	sum(if(t1.`day` = '16',t1.EVALUATIONTIME, 0)) as sixteenEVALUATIONTIME,
	sum(if(t1.`day` = '17',t1.EVALUATIONTIME, 0)) as seventeenEVALUATIONTIME,
	sum(if(t1.`day` = '18',t1.EVALUATIONTIME, 0)) as eighteenEVALUATIONTIME,
	sum(if(t1.`day` = '19',t1.EVALUATIONTIME, 0)) as nineteenEVALUATIONTIME,
	sum(if(t1.`day` = '20',t1.EVALUATIONTIME, 0)) as twentyEVALUATIONTIME,
	sum(if(t1.`day` = '21',t1.EVALUATIONTIME, 0)) as twentyoneEVALUATIONTIME,
	sum(if(t1.`day` = '22',t1.EVALUATIONTIME, 0)) as twentytwoEVALUATIONTIME,
	sum(if(t1.`day` = '23',t1.EVALUATIONTIME, 0)) as twentythreeEVALUATIONTIME,
	sum(if(t1.`day` = '24',t1.EVALUATIONTIME, 0)) as twentyfourEVALUATIONTIME,
	sum(if(t1.`day` = '25',t1.EVALUATIONTIME, 0)) as twentyfiveEVALUATIONTIME,
	sum(if(t1.`day` = '26',t1.EVALUATIONTIME, 0)) as twentysixEVALUATIONTIME,
	sum(if(t1.`day` = '27',t1.EVALUATIONTIME, 0)) as twentysevenEVALUATIONTIME,
	sum(if(t1.`day` = '28',t1.EVALUATIONTIME, 0)) as twentyeightEVALUATIONTIME,
	sum(if(t1.`day` = '29',t1.EVALUATIONTIME, 0)) as twentynineEVALUATIONTIME,
	sum(if(t1.`day` = '30',t1.EVALUATIONTIME, 0)) as thirtyEVALUATIONTIME,
	sum(if(t1.`day` = '31',t1.EVALUATIONTIME, 0)) as thirtyoneEVALUATIONTIME,
	sum(if(t1.`day` = '01',t1.EVALUATIONCOST, 0)) as oneEVALUATIONCOST,
	sum(if(t1.`day` = '02',t1.EVALUATIONCOST, 0)) as twoEVALUATIONCOST,
	sum(if(t1.`day` = '03',t1.EVALUATIONCOST, 0)) as threeEVALUATIONCOST,
	sum(if(t1.`day` = '04',t1.EVALUATIONCOST, 0)) as fourEVALUATIONCOST,
	sum(if(t1.`day` = '05',t1.EVALUATIONCOST, 0)) as fiveEVALUATIONCOST,
	sum(if(t1.`day` = '06',t1.EVALUATIONCOST, 0)) as sixEVALUATIONCOST,
	sum(if(t1.`day` = '07',t1.EVALUATIONCOST, 0)) as sevenEVALUATIONCOST,
	sum(if(t1.`day` = '08',t1.EVALUATIONCOST, 0)) as eightEVALUATIONCOST,
	sum(if(t1.`day` = '09',t1.EVALUATIONCOST, 0)) as nineEVALUATIONCOST,
	sum(if(t1.`day` = '10',t1.EVALUATIONCOST, 0)) as tenEVALUATIONCOST,
	sum(if(t1.`day` = '11',t1.EVALUATIONCOST, 0)) as elevenEVALUATIONCOST,
	sum(if(t1.`day` = '12',t1.EVALUATIONCOST, 0)) as twelveEVALUATIONCOST,
	sum(if(t1.`day` = '13',t1.EVALUATIONCOST, 0)) as thirteenEVALUATIONCOST,
	sum(if(t1.`day` = '14',t1.EVALUATIONCOST, 0)) as fourteenEVALUATIONCOST,
	sum(if(t1.`day` = '15',t1.EVALUATIONCOST, 0)) as fifteenEVALUATIONCOST,
	sum(if(t1.`day` = '16',t1.EVALUATIONCOST, 0)) as sixteenEVALUATIONCOST,
	sum(if(t1.`day` = '17',t1.EVALUATIONCOST, 0)) as seventeenEVALUATIONCOST,
	sum(if(t1.`day` = '18',t1.EVALUATIONCOST, 0)) as eighteenEVALUATIONCOST,
	sum(if(t1.`day` = '19',t1.EVALUATIONCOST, 0)) as nineteenEVALUATIONCOST,
	sum(if(t1.`day` = '20',t1.EVALUATIONCOST, 0)) as twentyEVALUATIONCOST,
	sum(if(t1.`day` = '21',t1.EVALUATIONCOST, 0)) as twentyoneEVALUATIONCOST,
	sum(if(t1.`day` = '22',t1.EVALUATIONCOST, 0)) as twentytwoEVALUATIONCOST,
	sum(if(t1.`day` = '23',t1.EVALUATIONCOST, 0)) as twentythreeEVALUATIONCOST,
	sum(if(t1.`day` = '24',t1.EVALUATIONCOST, 0)) as twentyfourEVALUATIONCOST,
	sum(if(t1.`day` = '25',t1.EVALUATIONCOST, 0)) as twentyfiveEVALUATIONCOST,
	sum(if(t1.`day` = '26',t1.EVALUATIONCOST, 0)) as twentysixEVALUATIONCOST,
	sum(if(t1.`day` = '27',t1.EVALUATIONCOST, 0)) as twentysevenEVALUATIONCOST,
	sum(if(t1.`day` = '28',t1.EVALUATIONCOST, 0)) as twentyeightEVALUATIONCOST,
	sum(if(t1.`day` = '29',t1.EVALUATIONCOST, 0)) as twentynineEVALUATIONCOST,
	sum(if(t1.`day` = '30',t1.EVALUATIONCOST, 0)) as thirtyEVALUATIONCOST,
	sum(if(t1.`day` = '31',t1.EVALUATIONCOST, 0)) as thirtyoneEVALUATIONCOST,
	SUM(T1.consumed) AS consumed,SUM(T1.EVALUATIONCOST) AS EVALUATIONCOST, SUM(T1.INPUTCOST) AS INPUTCOST, SUM(T1.EVALUATIONTIME) AS EVALUATIONTIME,
(select ROUND(sum(t.consumed),2) from zt_taskestimate t left join zt_task tt on tt.id = t.task where tt.project <> t1.project and t.account = t1.account and tt.deleted = '0' and DATE_FORMAT(t.date,'%Y%m') = concat(t1.`YEAR`, t1.`MONTH`)) as otherconsumed,
	(select ROUND(sum(t.EVALUATIONTIME),2) from zt_taskestimate t left join zt_task tt on tt.id = t.task where tt.project <> t1.project and t.account = t1.account and tt.deleted = '0' and DATE_FORMAT(t.date,'%Y%m') = concat(t1.`YEAR`, t1.`MONTH`)) as otherEVALUATIONTIME
FROM
	(
SELECT YEAR
	( t1.date ) AS `year`,
	Right(100 + MONTH ( t1.date ), 2) AS `month`,
	Right(100 + day ( t1.date ), 2) AS `day`,
	t11.project,
	t21.`name` AS projectname,
	t1.account,
	t1.date,
	ROUND( sum( t1.consumed ), 2 ) AS consumed,
	ROUND( sum( t1.EVALUATIONCOST ), 2 ) AS EVALUATIONCOST,
	ROUND( sum( t1.INPUTCOST ), 2 ) AS INPUTCOST,
	ROUND( sum( t1.EVALUATIONTIME ), 2 ) AS EVALUATIONTIME
FROM
	`zt_taskestimate` t1
	LEFT JOIN `zt_task` t11 ON t1.`TASK` = t11.`ID`
	LEFT JOIN `zt_project` t21 ON t11.`PROJECT` = t21.`ID` 
WHERE
	t1.date <> '0000-00-00'  and (t11.deleted = '0' or t11.deleted is null)
GROUP BY
	t11.project,
	t21.`name`,
	t1.account,
	t1.date 
union 
select 
  YEAR( t1.date ) AS `year`,
	Right(100 + MONTH ( t1.date ), 2) AS `month`,
	Right(100 + day ( t1.date ), 2) AS `day`,
	t1.idvalue as project,
	t11.`name` AS projectname,
	t1.account,
	t1.date,
	ROUND( sum( t1.consumed ), 2 ) AS consumed,
	0.00 AS EVALUATIONCOST,
	0.00 AS INPUTCOST,
	0.00 AS EVALUATIONTIME
	from zt_todo t1 left join zt_project t11 on t11.id = t1.idvalue 
	where t1.type = 'custom' and t1.idvalue > 0
GROUP BY
	t1.idvalue,
	t11.`name`,
	t1.account,
	t1.date 
	) t1 GROUP BY t1.account, t1.project,t1.projectname,t1.`YEAR`,t1.`MONTH`) T1
WHERE t1.project =#{srf.webcontext.project} 

```
### 默认（全部数据）(VIEW)<div id="ProjectTaskestimate_View"></div>
```sql
SELECT
t1.`DATE`,
t1.`EVALUATIONCOST`,
t1.`INPUTCOST`
FROM `zt_taskestimate` t1 

```

# **项目团队**(IBZ_PROJECTTEAM)

### DEFAULT(DEFAULT)<div id="ProjectTeam_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CONSUMED`,
t1.`DAYS`,
t1.`ESTIMATE`,
DATE_ADD(t1.`join`, INTERVAL t1.days day) AS `EXITDATE`,
t1.`HOURS`,
t1.`ID`,
t1.`JOIN`,
t1.`LEFT`,
t1.`LIMITED`,
t1.`ORDER`,
t11.`PM`,
t11.`NAME` AS `PROJECTNAME`,
t1.`ROLE`,
t1.`ROOT`,
(t1.`DAYS` * t1.`HOURS`) AS `TOTAL`,
t1.`TYPE`,
(select t.realname from zt_user t where t.account = t1.account) AS `USERNAME`
FROM `zt_team` t1 
LEFT JOIN `zt_project` t11 ON t1.`ROOT` = t11.`ID` 

WHERE ( t1.`TYPE` = 'project' ) 

```
### 项目所有成员(ProjectAllMembers)<div id="ProjectTeam_ProjectAllMembers"></div>
```sql
select t11.ACCOUNT,t11.root,t21.realname as USERNAME  from zt_team t11 LEFT JOIN zt_user t21 ON t11.account = t21.account
WHERE t11.type = 'project' AND t11.root = #{srf.datacontext.account} AND t21.realname IS NOT null
 

```
### 项目成员（项目经理）(ProjectTeamPm)<div id="ProjectTeam_ProjectTeamPm"></div>
```sql
SELECT
	t1.account,
	t1.days,
	t1.hours,
	t1.id,
	t1.`join`,
	t1.limited,
	t1.`order`,
	t1.role,
	t1.root,
	( t1.days * t1.hours ) AS total,
	t1.type,
	( SELECT t.realname FROM zt_user t WHERE t.account = t1.account ) AS username,
	(
SELECT
	count( t2.id ) 
FROM
	zt_task t2 
WHERE
	t2.deleted = '0' 
	AND t2.project = t1.root 
	AND t2.parent >= 0 
	AND (
	t2.assignedTo = t1.account 
	OR t2.finishedBy = t1.account 
	OR t2.id IN ( SELECT t.root FROM zt_team t WHERE t.type = 'task' AND t.account = t1.account ) 
	) 
	) AS taskcnt,
	(
SELECT
	ROUND(sum( CASE WHEN tt.LEFT IS NOT NULL THEN tt.LEFT ELSE t2.LEFT END ), 1) 
FROM
	zt_task t2
	LEFT JOIN zt_team tt ON tt.root = t2.id 
	AND tt.type = 'task' 
WHERE
	t2.deleted = '0' 
	AND t2.project = t1.root 
	AND t2.parent >= 0 
	AND ( t2.assignedTo = t1.account OR tt.account = t1.account ) 
	) AS `left`,
	(
SELECT
	ROUND(sum( CASE WHEN tt.estimate IS NOT NULL THEN tt.estimate ELSE t2.estimate END ), 1)
FROM
	zt_task t2
	LEFT JOIN zt_team tt ON tt.root = t2.id 
	AND tt.type = 'task' 
WHERE
	t2.deleted = '0' 
	AND t2.project = t1.root 
	AND t2.parent >= 0 
	AND ( t2.assignedTo = t1.account OR tt.account = t1.account ) 
	) AS `estimate`,
	(
SELECT
	ROUND(sum( CASE WHEN tt.consumed IS NOT NULL THEN tt.consumed ELSE t2.consumed END ), 1)
FROM
	zt_task t2
	LEFT JOIN zt_team tt ON tt.root = t2.id 
	AND tt.type = 'task' 
WHERE
	t2.deleted = '0' 
	AND t2.project = t1.root 
	AND t2.parent >= 0 
	AND ( t2.assignedTo = t1.account OR tt.account = t1.account ) 
	) AS consumed ,
	t11.`PM`,
t11.`NAME` AS `PROJECTNAME`,
DATE_ADD(t1.`join`, INTERVAL t1.days day) as Exitdate
FROM
	zt_team t1 
	LEFT JOIN `zt_project` t11 ON t1.`ROOT` = t11.`ID`
WHERE ( t11.`PM` =  ${srfsessioncontext('srfloginname','{"defname":"PM","dename":"ZT_PROJECT"}')} ) 

```
### 行编辑查询(RowEditDefault)<div id="ProjectTeam_RowEditDefault"></div>
```sql
SELECT
	t1.* 
FROM
	(
SELECT
	t1.`ACCOUNT`,
	t1.`CONSUMED`,
	t1.`DAYS`,
	t1.`ESTIMATE`,
	t1.`HOURS`,
	t1.id as `ID`,
	t1.`JOIN`,
	t1.`LEFT`,
	t1.`LIMITED`,
	t1.`ORDER`,
	t1.`ROLE`,
	t1.`ROOT`,
	( t1.`DAYS` * t1.`HOURS` ) AS `TOTAL`,
	t1.`TYPE`,
	t2.`realname` AS `USERNAME` 
FROM
	`zt_team` t1
	LEFT JOIN `zt_user` t2 ON t2.`account` = t1.`account` 
	union 
	SELECT
	t1.`ACCOUNT`,
	0 AS `CONSUMED`,
	((select tt.days from zt_project tt where tt.id = #{srf.datacontext.root})) AS `DAYS`,
	0 AS `ESTIMATE`,
	7 AS `HOURS`,
	null as `ID`,
	'2020-07-13' AS `JOIN`,
	0 AS `LEFT`,
	'no' AS `LIMITED`,
	0 AS `ORDER`,
	t3.`name` as `ROLE`,
	#{srf.datacontext.root} as `ROOT`,
	90 AS `TOTAL`,
	'project' AS `TYPE`,
	t2.`realname` AS `USERNAME` 
FROM
	`zt_team` t1
	LEFT JOIN `zt_user` t2 ON t2.`account` = t1.`account` 
        left join zt_group t3 on t2.role = t3.role
	where t1.type = 'project' and t1.root = #{srf.datacontext.teams} and t1.account not in (select  t.account from zt_team t where t.root = #{srf.datacontext.root} and t.type = 'project')
	union 
	SELECT
	t2.`ACCOUNT`,
	0 AS `CONSUMED`,
	((select tt.days from zt_project tt where tt.id = #{srf.datacontext.root})) AS `DAYS`,
	0 AS `ESTIMATE`,
	7 AS `HOURS`,
	null as `ID`,
	'2020-07-13' AS `JOIN`,
	0 AS `LEFT`,
	'no' AS `LIMITED`,
	0 AS `ORDER`,
	t3.`name` as `ROLE`,
	#{srf.datacontext.root} as `ROOT`,
	90 AS `TOTAL`,
	'project' AS `TYPE`,
	t2.`realname` AS `USERNAME` 
FROM
	`zt_dept` t1
	LEFT JOIN `zt_user` t2 ON t2.`dept` = t1.`id` 
	left join zt_group t3 on t2.role = t3.role
	where t1.id = #{srf.datacontext.dept} and t2.account is not null and t2.account not in (select  t.account from zt_team t where t.root = #{srf.datacontext.root} and t.type = 'project')
	) t1
WHERE 	
( t1.`TYPE` = 'project' ) 

```
### 数据查询(TaskCntEstimateConsumedLeft)<div id="ProjectTeam_TaskCntEstimateConsumedLeft"></div>
```sql
SELECT
	t1.account,
	t1.days,
	t1.hours,
	t1.id,
	t1.`join`,
	t1.limited,
	t1.`order`,
	t1.role,
	t1.root,
	( t1.days * t1.hours ) AS total,
	t1.type,
	( SELECT t.realname FROM zt_user t WHERE t.account = t1.account ) AS username,
	(
SELECT
	count( t2.id ) 
FROM
	zt_task t2 
WHERE
	t2.deleted = '0' 
	AND t2.project = t1.root 
	AND t2.parent >= 0 
	AND (
	t2.assignedTo = t1.account 
	OR t2.finishedBy = t1.account 
	OR t2.id IN ( SELECT t.root FROM zt_team t WHERE t.type = 'task' AND t.account = t1.account ) 
	) 
	) AS taskcnt,
	(
SELECT
	ROUND(sum( CASE WHEN tt.LEFT IS NOT NULL THEN tt.LEFT ELSE t2.LEFT END ), 1) 
FROM
	zt_task t2
	LEFT JOIN zt_team tt ON tt.root = t2.id 
	AND tt.type = 'task' 
WHERE
	t2.deleted = '0' 
	AND t2.project = t1.root 
	AND t2.parent >= 0 
	AND ( t2.assignedTo = t1.account OR tt.account = t1.account ) 
	) AS `left`,
	(
SELECT
	ROUND(sum( CASE WHEN tt.estimate IS NOT NULL THEN tt.estimate ELSE t2.estimate END ), 1)
FROM
	zt_task t2
	LEFT JOIN zt_team tt ON tt.root = t2.id 
	AND tt.type = 'task' 
WHERE
	t2.deleted = '0' 
	AND t2.project = t1.root 
	AND t2.parent >= 0 
	AND ( t2.assignedTo = t1.account OR tt.account = t1.account ) 
	) AS `estimate`,
	(
SELECT
	ROUND(sum( CASE WHEN tt.consumed IS NOT NULL THEN tt.consumed ELSE t2.consumed END ), 1)
FROM
	zt_task t2
	LEFT JOIN zt_team tt ON tt.root = t2.id 
	AND tt.type = 'task' 
WHERE
	t2.deleted = '0' 
	AND t2.project = t1.root 
	AND t2.parent >= 0 
	AND ( t2.assignedTo = t1.account OR tt.account = t1.account ) 
	) AS consumed 
FROM
	zt_team t1
WHERE t1.type = 'project' 

```
### 默认（全部数据）(VIEW)<div id="ProjectTeam_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CONSUMED`,
t1.`DAYS`,
t1.`ESTIMATE`,
DATE_ADD(t1.`join`, INTERVAL t1.days day) AS `EXITDATE`,
t1.`HOURS`,
t1.`ID`,
t1.`JOIN`,
t1.`LEFT`,
t1.`LIMITED`,
t1.`ORDER`,
t11.`PM`,
t11.`NAME` AS `PROJECTNAME`,
t1.`ROLE`,
t1.`ROOT`,
(t1.`DAYS` * t1.`HOURS`) AS `TOTAL`,
t1.`TYPE`,
(select t.realname from zt_user t where t.account = t1.account) AS `USERNAME`
FROM `zt_team` t1 
LEFT JOIN `zt_project` t11 ON t1.`ROOT` = t11.`ID` 

WHERE ( t1.`TYPE` = 'project' ) 

```

# **项目其他活动**(PROJECTTODO)

### 数据查询(DEFAULT)<div id="ProjectTodo_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ASSIGNEDBY`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CONFIG`,
t1.`CONSUMED`,
t1.`COST`,
t1.`DATE`,
t1.`FINISHEDBY`,
t1.`FINISHEDDATE`,
t1.`ID`,
t1.`IDVALUE`,
t1.`NAME`,
t1.`PRI`,
t1.`PRIVATE`,
t1.`STATUS`,
t1.`TYPE`
FROM `zt_todo` t1 

```
### 默认（全部数据）(VIEW)<div id="ProjectTodo_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ASSIGNEDBY`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CONFIG`,
t1.`CONSUMED`,
t1.`COST`,
t1.`DATE`,
t1.`DESC`,
t1.`FINISHEDBY`,
t1.`FINISHEDDATE`,
t1.`ID`,
t1.`IDVALUE`,
t1.`NAME`,
t1.`PRI`,
t1.`PRIVATE`,
t1.`STATUS`,
t1.`TYPE`
FROM `zt_todo` t1 

```

# **relation**(ZT_RELATION)

### DEFAULT(DEFAULT)<div id="Relation_Default"></div>
```sql
SELECT
t1.`AID`,
t1.`ATYPE`,
t1.`AVERSION`,
t1.`BID`,
t1.`BTYPE`,
t1.`BVERSION`,
t1.`EXTRA`,
t1.`ID`,
t1.`PRODUCT`,
t1.`PROGRAM`,
t1.`PROJECT`,
t1.`RELATION`
FROM `zt_relation` t1 

```
### 默认（全部数据）(VIEW)<div id="Relation_View"></div>
```sql
SELECT
t1.`AID`,
t1.`ATYPE`,
t1.`AVERSION`,
t1.`BID`,
t1.`BTYPE`,
t1.`BVERSION`,
t1.`EXTRA`,
t1.`ID`,
t1.`PRODUCT`,
t1.`PROGRAM`,
t1.`PROJECT`,
t1.`RELATION`
FROM `zt_relation` t1 

```

# **发布**(ZT_RELEASE)

### DEFAULT(DEFAULT)<div id="Release_Default"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`BUILD`,
t21.`DATE` AS `BUILDDATE`,
t21.`BUILDER`,
t21.`NAME` AS `BUILDNAME`,
t1.`DATE`,
t1.`DELETED`,
t1.`ID`,
t1.`MARKER`,
t1.`NAME`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`STATUS`,
t1.`SUBSTATUS`
FROM `zt_release` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 
LEFT JOIN `zt_build` t21 ON t1.`BUILD` = t21.`ID` 

WHERE t1.DELETED = '0' 

```
### 获取产品发布(GetList)<div id="Release_GetList"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`BUILD`,
t21.`DATE` AS `BUILDDATE`,
t21.`BUILDER`,
t21.`NAME` AS `BUILDNAME`,
t1.`DATE`,
t1.`DELETED`,
t1.`ID`,
t1.`MARKER`,
t1.`NAME`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`STATUS`,
t1.`SUBSTATUS`
FROM `zt_release` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 
LEFT JOIN `zt_build` t21 ON t1.`BUILD` = t21.`ID` 

WHERE t1.DELETED = '0' 
( t1.`PRODUCT` = ${srfdatacontext('product','{"defname":"PRODUCT","dename":"ZT_RELEASE"}')}  AND  <#assign _value=srfdatacontext('status','{"ignoreempty":true,"defname":"STATUS","dename":"ZT_RELEASE"}')><#if _value?length gt 0>t1.`STATUS` =  ${_value}<#else>1=1</#if> ) 

```
### 测试报告关联发布(ReportRelease)<div id="Release_ReportRelease"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`BUILD`,
t21.`DATE` AS `BUILDDATE`,
t21.`BUILDER`,
t21.`NAME` AS `BUILDNAME`,
t1.`DATE`,
t1.`DELETED`,
t1.`ID`,
t1.`MARKER`,
t1.`NAME`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`STATUS`,
t1.`SUBSTATUS`
FROM `zt_release` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 
LEFT JOIN `zt_build` t21 ON t1.`BUILD` = t21.`ID` 

WHERE t1.DELETED = '0' 
( FIND_IN_SET(t1.`ID`, (select tt.builds from zt_testreport tt where tt.id = ${srfwebcontext('srfparentkey','{"defname":"ID","dename":"ZT_BUILD"}')})) ) 

```
### 默认（全部数据）(VIEW)<div id="Release_View"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`BUGS`,
t1.`BUILD`,
t21.`DATE` AS `BUILDDATE`,
t21.`BUILDER`,
t21.`NAME` AS `BUILDNAME`,
t1.`DATE`,
t1.`DELETED`,
t1.`DESC`,
t1.`ID`,
t1.`LEFTBUGS`,
t1.`MARKER`,
t1.`NAME`,
t1.`PRODUCT`,
t11.`NAME` AS `PRODUCTNAME`,
t1.`STATUS`,
t1.`STORIES`,
t1.`SUBSTATUS`
FROM `zt_release` t1 
LEFT JOIN `zt_product` t11 ON t1.`PRODUCT` = t11.`ID` 
LEFT JOIN `zt_build` t21 ON t1.`BUILD` = t21.`ID` 

WHERE t1.DELETED = '0' 

```

# **repo**(ZT_REPO)

### DEFAULT(DEFAULT)<div id="Repo_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CLIENT`,
t1.`COMMITS`,
t1.`DELETED`,
t1.`ENCODING`,
t1.`ENCRYPT`,
t1.`ID`,
t1.`LASTSYNC`,
t1.`NAME`,
t1.`PASSWORD`,
t1.`PATH`,
t1.`PREFIX`,
t1.`SCM`,
t1.`SYNCED`
FROM `zt_repo` t1 

WHERE t1.DELETED = '0' 

```
### 默认（全部数据）(VIEW)<div id="Repo_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ACL`,
t1.`CLIENT`,
t1.`COMMITS`,
t1.`DELETED`,
t1.`DESC`,
t1.`ENCODING`,
t1.`ENCRYPT`,
t1.`ID`,
t1.`LASTSYNC`,
t1.`NAME`,
t1.`PASSWORD`,
t1.`PATH`,
t1.`PREFIX`,
t1.`SCM`,
t1.`SYNCED`
FROM `zt_repo` t1 

WHERE t1.DELETED = '0' 

```

# **repobranch**(ZT_REPOBRANCH)

### DEFAULT(DEFAULT)<div id="RepoBranch_Default"></div>
```sql
SELECT
t1.`BRANCH`,
concat(t1.`REPO`,'__',t1.`REVISION`,'__',t1.`BRANCH`) AS `ID`,
t1.`REPO`,
t1.`REVISION`
FROM `zt_repobranch` t1 

```
### 默认（全部数据）(VIEW)<div id="RepoBranch_View"></div>
```sql
SELECT
t1.`BRANCH`,
concat(t1.`REPO`,'__',t1.`REVISION`,'__',t1.`BRANCH`) AS `ID`,
t1.`REPO`,
t1.`REVISION`
FROM `zt_repobranch` t1 

```

# **repofiles**(ZT_REPOFILES)

### DEFAULT(DEFAULT)<div id="RepoFiles_Default"></div>
```sql
SELECT
t1.`ACTION`,
t1.`ID`,
t1.`PARENT`,
t1.`PATH`,
t1.`REPO`,
t1.`REVISION`,
t1.`TYPE`
FROM `zt_repofiles` t1 

```
### 默认（全部数据）(VIEW)<div id="RepoFiles_View"></div>
```sql
SELECT
t1.`ACTION`,
t1.`ID`,
t1.`PARENT`,
t1.`PATH`,
t1.`REPO`,
t1.`REVISION`,
t1.`TYPE`
FROM `zt_repofiles` t1 

```

# **repohistory**(ZT_REPOHISTORY)

### DEFAULT(DEFAULT)<div id="RepoHistory_Default"></div>
```sql
SELECT
t1.`COMMIT`,
t1.`COMMITTER`,
t1.`ID`,
t1.`REPO`,
t1.`REVISION`,
t1.`TIME`
FROM `zt_repohistory` t1 

```
### 默认（全部数据）(VIEW)<div id="RepoHistory_View"></div>
```sql
SELECT
t1.`COMMENT`,
t1.`COMMIT`,
t1.`COMMITTER`,
t1.`ID`,
t1.`REPO`,
t1.`REVISION`,
t1.`TIME`
FROM `zt_repohistory` t1 

```

# **score**(ZT_SCORE)

### DEFAULT(DEFAULT)<div id="Score_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`AFTER`,
t1.`BEFORE`,
t1.`DESC`,
t1.`ID`,
t1.`METHOD`,
t1.`MODULE`,
t1.`SCORE`,
t1.`TIME`
FROM `zt_score` t1 

```
### 默认（全部数据）(VIEW)<div id="Score_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`AFTER`,
t1.`BEFORE`,
t1.`DESC`,
t1.`ID`,
t1.`METHOD`,
t1.`MODULE`,
t1.`SCORE`,
t1.`TIME`
FROM `zt_score` t1 

```

# **需求**(ZT_STORY)

### 指派给我的需求(AssignedToMyStory)<div id="Story_AssignedToMyStory"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t41.`NAME` AS `BRANCHNAME`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'story' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' ) FROM zt_module tt  WHERE FIND_IN_SET( tt.id, t11.path ) AND tt.type = 'story' GROUP BY	tt.root ) AS `MODULENAME1`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
t1.`PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCENOTE`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VERSION` AS `VERSIONC`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS isChild
FROM `zt_story` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.PARENT = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_branch t41 ON t1.BRANCH = t41.ID
WHERE t1.DELETED = '0' 
( t1.`ASSIGNEDTO` =  ${srfsessioncontext('srfloginname','{"defname":"ASSIGNEDTO","dename":"ZT_STORY"}')} ) 

```
### 指派给我的需求（日历）(AssignedToMyStoryCalendar)<div id="Story_AssignedToMyStoryCalendar"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t41.`NAME` AS `BRANCHNAME`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'story' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' ) FROM zt_module tt  WHERE FIND_IN_SET( tt.id, t11.path ) AND tt.type = 'story' GROUP BY	tt.root ) AS `MODULENAME1`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
t1.`PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCENOTE`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VERSION` AS `VERSIONC`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS isChild
FROM `zt_story` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.PARENT = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_branch t41 ON t1.BRANCH = t41.ID
WHERE t1.DELETED = '0' 
( t1.`ASSIGNEDTO` =  ${srfsessioncontext('srfloginname','{"defname":"ASSIGNEDTO","dename":"ZT_STORY"}')} ) 
t1.assignedDate <> '0000-00-00 00:00:00' and t1.assignedDate is not null 

```
### Bug相关需求(BugStory)<div id="Story_BugStory"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t41.`NAME` AS `BRANCHNAME`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'story' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} 
and t.OBJECTID = t1.id) AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
t1.`PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCENOTE`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`SUBSTATUS`,
CONCAT(t1.id,':',t1.title,'(优先级:',t1.pri,',预计工时:',t1.estimate,')') AS `TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS isChild
FROM `zt_story` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.PARENT = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_branch t41 ON t1.BRANCH = t41.ID
WHERE t1.DELETED = '0' 
( ( EXISTS ( SELECT 1 FROM zt_projectstory t51 WHERE t1.ID = t51.STORY AND ( t51.`PROJECT` = ${srfdatacontext('n_project_eq','{"defname":"PROJECT","dename":"ZT_PROJECTSTORY"}')}) )  OR  ${srfdatacontext('n_project_eq','{"defname":"PROJECT","dename":"ZT_PROJECTSTORY"}')} is null ) ) 

```
### 版本关联已完成的需求（选择数据源）(buildLinkCompletedStories)<div id="Story_BuildLinkCompletedStories"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t41.`NAME` AS `BRANCHNAME`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`ID`,
 0 AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
t1.`PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCENOTE`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VERSION` AS `VERSIONC`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS isChild
FROM `zt_story` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.PARENT = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_branch t41 ON t1.BRANCH = t41.ID 

WHERE 
t1.DELETED = '0' 
AND
	t1.id IN ( SELECT tt.story FROM zt_projectstory tt WHERE tt.project = (select ttt.project from zt_build ttt where ttt.id = ${srfdatacontext('build','{"defname":"ID","dename":"ZT_BUILD"}')}) ) 
	AND t1.product = ( SELECT t2.product FROM zt_build t2 WHERE t2.id = ${srfdatacontext('build','{"defname":"ID","dename":"ZT_BUILD"}')} )
	and t1.id not in (select t3.id from zt_story t3, zt_build t4 where t4.id = ${srfdatacontext('build','{"defname":"ID","dename":"ZT_BUILD"}')} and FIND_IN_SET(t3.id,t4.stories))
```
### 版本可关联的需求（产品内）(BuildLinkableStories)<div id="Story_BuildLinkableStories"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t41.`NAME` AS `BRANCHNAME`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'story' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
t1.`PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCENOTE`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS isChild
FROM `zt_story` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.PARENT = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_branch t41 ON t1.BRANCH = t41.ID 

WHERE t1.`product` in ( select t2.`product` from zt_projectproduct t2 where t2.project = ${srfdatacontext('project','{"defname":"ID","dename":"ZT_PRODUCT"}')}  ) 
t1.`STATUS` = 'active' 
t1.DELETED = '0' 

```
### 获取版本相关需求(BuildStories)<div id="Story_BuildStories"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'story' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
t1.`PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCENOTE`,
(select spec from zt_storyspec where story = t1.`ID` and version= t1.`VERSION`) AS `SPEC`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
(select verify from zt_storyspec where story = t1.`ID` and version= t1.`VERSION`) AS `VERIFY`,
t1.`VERSION`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS isChild
FROM `zt_story` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.PARENT = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
WHERE t1.`DELETED` = '0' AND FIND_IN_SET ( t1.ID, (SELECT STORIES FROM ZT_BUILD WHERE ID = ${srfdatacontext('srfparentkey','{"defname":"ID","dename":"ZT_BUILD"}')}))
```
### 通过模块查询(ByModule)<div id="Story_ByModule"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'story' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
(case when t1.`PLAN` = '0' then '' else t1.plan end ) as `PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCENOTE`,
(select spec from zt_storyspec where story = t1.`ID` and version= t1.`VERSION`) AS `SPEC`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
(select verify from zt_storyspec where story = t1.`ID` and version= t1.`VERSION`) AS `VERIFY`,
t1.`VERSION`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS isChild
FROM `zt_story` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.PARENT = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 

WHERE t1.DELETED = '0' 
FIND_IN_SET (${srfdatacontext('srfparentkey','{"defname":"ID","dename":"ZT_PROJECTMODULE"}')}, t11.`PATH`) 

```
### 相关用例需求(CaseStory)<div id="Story_CaseStory"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t41.`NAME` AS `BRANCHNAME`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'story' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} 
and t.OBJECTID = t1.id) AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
t1.`PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCENOTE`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`SUBSTATUS`,
CONCAT(t1.id,':',t1.title,'(优先级:',t1.pri,',预计工时:',t1.estimate,')') AS `TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS isChild
FROM `zt_story` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.PARENT = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_branch t41 ON t1.BRANCH = t41.ID
```
### DEFAULT(DEFAULT)<div id="Story_Default"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t41.`NAME` AS `BRANCHNAME`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'story' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' ) FROM zt_module tt  WHERE FIND_IN_SET( tt.id, t11.path ) AND tt.type = 'story' GROUP BY	tt.root ) AS `MODULENAME1`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
(case when t1.`PLAN` = '0' then '' else t1.plan end ) as `PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCENOTE`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VERSION` AS `VERSIONC`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS isChild
FROM `zt_story` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.PARENT = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_branch t41 ON t1.BRANCH = t41.ID
WHERE t1.DELETED = '0' 

```
### ES批量的导入(ESBulk)<div id="Story_ESBulk"></div>
```sql
SELECT
	t1.`ID`,
	t1.`TITLE`,
	t11.orgid,
	t11.MDEPTID,
	t1.deleted,
	t21.spec,
	t1.color,
	t11.acl,
	CONCAT_WS( ',', t11.CREATEDBY, t11.qd, t11.po, t11.rd ) AS acllist,
	t1.product
FROM
	`zt_story` t1
	LEFT JOIN zt_product t11 ON t11.id = t1.product
	LEFT JOIN zt_storyspec t21 ON t21.story = t1.id 
	AND t1.version = t21.version
```
### 获取产品需求(GetProductStories)<div id="Story_GetProductStories"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.ASSIGNEDTO AS `ASSIGNEDTOPK`,
t1.`BRANCH`,
t41.`NAME` AS `BRANCHNAME`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`IBIZ_ID`,
t1.`IBIZ_SOURCEID`,
t1.`IBIZ_SOURCENAME`,
t1.`IBIZ_SOURCEOBJECT`,
t1.`ID`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS `ISCHILD`,
'0' AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
'' AS `MAILTOPK`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' ) FROM zt_module tt  WHERE FIND_IN_SET( tt.id, t11.path ) AND tt.type = 'story' GROUP BY	tt.root limit 0,1) end) AS `MODULENAME1`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
t1.`PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCEID`,
t1.`SOURCENAME`,
t1.`SOURCENOTE`,
t1.`SOURCEOBJECT`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`STORYLATESTFINISHEDDATE`,
t1.`STORYPOINTS`,
t1.`STORYPROVIDEDATE`,
t1.`STORYPROVIDER`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VERSION` AS `VERSIONC`
FROM `zt_story` t1 
LEFT JOIN `zt_module` t11 ON t1.`MODULE` = t11.`ID` 
LEFT JOIN `zt_story` t21 ON t1.`PARENT` = t21.`ID` 
LEFT JOIN `zt_product` t31 ON t1.`PRODUCT` = t31.`ID` 
LEFT JOIN `zt_branch` t41 ON t1.`BRANCH` = t41.`ID` 

WHERE t1.DELETED = '0' 
( t1.`PRODUCT` = ${srfdatacontext('product','{"defname":"PRODUCT","dename":"ZT_STORY"}')}  AND  <#assign _value=srfdatacontext('branch','{"ignoreempty":true,"defname":"BRANCH","dename":"ZT_STORY"}')><#if _value?length gt 0>t1.`BRANCH` = ${_value}<#else>1=1</#if>  AND  <#assign _value=srfdatacontext('nodeid','{"ignoreempty":true,"defname":"MODULE","dename":"ZT_STORY"}')><#if _value?length gt 0>t1.`MODULE` = ${_value}<#else>1=1</#if> ) 

```
### 我代理的需求(MyAgentStory)<div id="Story_MyAgentStory"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
case when t51.`AGENTUSER` = #{srf.sessioncontext.srfloginname} then t51.`AGENTUSER` else t1.`ASSIGNEDTO` end as `ASSIGNEDTO`,
t1.`BRANCH`,
t41.`NAME` AS `BRANCHNAME`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'story' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' ) FROM zt_module tt  WHERE FIND_IN_SET( tt.id, t11.path ) AND tt.type = 'story' GROUP BY	tt.root ) AS `MODULENAME1`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
(case when t1.`PLAN` = '0' then '' else t1.plan end ) as `PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCENOTE`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VERSION` AS `VERSIONC`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS isChild
FROM `zt_story` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.PARENT = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_branch t41 ON t1.BRANCH = t41.ID
LEFT JOIN t_ibz_agent t51 ON t1.assignedTo = t51.CREATEMANNAME and DATE_FORMAT(now(), '%Y-%m-%d') >= t51.AGENTBEGIN and DATE_FORMAT(now(), '%Y-%m-%d') <= t51.AGENTEND
WHERE t1.deleted = '0' 

```
### 所创建需求数和对应的优先级及状态(MyCurOpenedStory)<div id="Story_MyCurOpenedStory"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.ASSIGNEDTO AS `ASSIGNEDTOPK`,
t1.`BRANCH`,
t41.`NAME` AS `BRANCHNAME`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`IBIZ_ID`,
t1.`IBIZ_SOURCEID`,
t1.`IBIZ_SOURCENAME`,
t1.`IBIZ_SOURCEOBJECT`,
t1.`ID`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS `ISCHILD`,
'0' AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
'' AS `MAILTOPK`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' ) FROM zt_module tt  WHERE FIND_IN_SET( tt.id, t11.path ) AND tt.type = 'story' GROUP BY	tt.root limit 0,1) end) AS `MODULENAME1`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
t1.`PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCEID`,
t1.`SOURCENAME`,
t1.`SOURCENOTE`,
t1.`SOURCEOBJECT`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`STORYLATESTFINISHEDDATE`,
t1.`STORYPOINTS`,
t1.`STORYPROVIDEDATE`,
t1.`STORYPROVIDER`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VERSION` AS `VERSIONC`
FROM `zt_story` t1 
LEFT JOIN `zt_module` t11 ON t1.`MODULE` = t11.`ID` 
LEFT JOIN `zt_story` t21 ON t1.`PARENT` = t21.`ID` 
LEFT JOIN `zt_product` t31 ON t1.`PRODUCT` = t31.`ID` 
LEFT JOIN `zt_branch` t41 ON t1.`BRANCH` = t41.`ID` 

WHERE t1.DELETED = '0' 
( DATE_FORMAT( t1.openedDate, '%Y' ) = DATE_FORMAT(now(), '%Y' )  AND  t1.`OPENEDBY` =  ${srfsessioncontext('srfloginname','{"defname":"OPENEDBY","dename":"ZT_STORY"}')} ) 

```
### 我的收藏(MyFavorites)<div id="Story_MyFavorites"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t41.`NAME` AS `BRANCHNAME`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`ID`,
1 AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' ) FROM zt_module tt  WHERE FIND_IN_SET( tt.id, t11.path ) AND tt.type = 'story' GROUP BY	tt.root ) AS `MODULENAME1`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
t1.`PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCENOTE`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VERSION` AS `VERSIONC`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS isChild
FROM `zt_story` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.PARENT = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_branch t41 ON t1.BRANCH = t41.ID
WHERE t1.DELETED = '0' 
t1.id in (select t.OBJECTID from T_IBZ_FAVORITES t where t.type = 'story' and t.account = #{srf.sessioncontext.srfloginname}) 

```
### 计划关联需求(去除已关联)(NotCurPlanLinkStory)<div id="Story_NotCurPlanLinkStory"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.ASSIGNEDTO AS `ASSIGNEDTOPK`,
t1.`BRANCH`,
t41.`NAME` AS `BRANCHNAME`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`ID`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS `ISCHILD`,
0 AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
t1.MAILTO AS `MAILTOPK`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' ) FROM zt_module tt  WHERE FIND_IN_SET( tt.id, t11.path ) AND tt.type = 'story' GROUP BY	tt.root limit 0,1) end) AS `MODULENAME1`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
t1.`PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCENOTE`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VERSION` AS `VERSIONC`
FROM `zt_story` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.PARENT = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_branch t41 ON t1.BRANCH = t41.ID 

WHERE t1.DELETED = '0' 
t1.plan <> ${srfwebcontext('plan','{"defname":"id","dename":"ZT_PRODUCTPLAN"}')} 
t1.product =  ${srfwebcontext('product','{"defname":"product","dename":"ZT_PRODUCT"}')} 
t1.`status` <> 'closed' 
t1.parent <> '-1' 

```
### 数据查询(ParentDefault)<div id="Story_ParentDefault"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t41.`NAME` AS `BRANCHNAME`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'story' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' ) FROM zt_module tt  WHERE FIND_IN_SET( tt.id, t11.path ) AND tt.type = 'story' GROUP BY	tt.root ) AS `MODULENAME1`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
(case when t1.`PLAN` = '0' then '' else t1.plan end ) as `PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCENOTE`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VERSION` AS `VERSIONC`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS isChild
FROM `zt_story` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.PARENT = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_branch t41 ON t1.BRANCH = t41.ID
WHERE t1.DELETED = '0' 
( ( t1.`PARENT` = -1  OR  t1.`PARENT` = 0 ) ) 

```
### 数据查询(ParentDefaultQ)<div id="Story_ParentDefaultQ"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t41.`NAME` AS `BRANCHNAME`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'story' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
(SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' ) FROM zt_module tt  WHERE FIND_IN_SET( tt.id, t11.path ) AND tt.type = 'story' GROUP BY	tt.root ) AS `MODULENAME1`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
(case when t1.`PLAN` = '0' then '' else t1.plan end ) as `PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCENOTE`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VERSION` AS `VERSIONC`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS isChild
FROM `zt_story` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.PARENT = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_branch t41 ON t1.BRANCH = t41.ID
WHERE t1.DELETED = '0' 
( ( t1.`PARENT` = -1  OR  t1.`PARENT` = 0 ) ) 
t1.`status` = 'active' and t1.stage = 'wait' 

```
### 项目关联需求(projectLinkStory)<div id="Story_ProjectLinkStory"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.ASSIGNEDTO AS `ASSIGNEDTOPK`,
t1.`BRANCH`,
t41.`NAME` AS `BRANCHNAME`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`IBIZ_ID`,
t1.`IBIZ_SOURCEID`,
t1.`IBIZ_SOURCENAME`,
t1.`IBIZ_SOURCEOBJECT`,
t1.`ID`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS `ISCHILD`,
'0' AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
'' AS `MAILTOPK`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' ) FROM zt_module tt  WHERE FIND_IN_SET( tt.id, t11.path ) AND tt.type = 'story' GROUP BY	tt.root limit 0,1) end) AS `MODULENAME1`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
t1.`PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCEID`,
t1.`SOURCENAME`,
t1.`SOURCENOTE`,
t1.`SOURCEOBJECT`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`STORYLATESTFINISHEDDATE`,
t1.`STORYPOINTS`,
t1.`STORYPROVIDEDATE`,
t1.`STORYPROVIDER`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VERSION` AS `VERSIONC`
FROM `zt_story` t1 
LEFT JOIN `zt_module` t11 ON t1.`MODULE` = t11.`ID` 
LEFT JOIN `zt_story` t21 ON t1.`PARENT` = t21.`ID` 
LEFT JOIN `zt_product` t31 ON t1.`PRODUCT` = t31.`ID` 
LEFT JOIN `zt_branch` t41 ON t1.`BRANCH` = t41.`ID` 
RIGHT JOIN `zt_product` t51 ON t1.`PRODUCT` = t51.`ID` 
LEFT OUTER JOIN `zt_projectproduct` t61 ON t51.`ID` = t61.`PRODUCT` 

WHERE ( t61.`PROJECT` = ${srfwebcontext('project','{"defname":"PROJECT","dename":"ZT_PROJECTPRODUCT"}')}  AND  (t61.plan = 0 or t1.plan = t61.plan or t1.plan in (select t.id from zt_productplan t where t.parent = t61.plan)) ) 
NOT(EXISTS(SELECT * FROM `zt_projectstory` t71 
 WHERE 
 t1.`ID` = t71.`STORY`  AND  ( t71.`PROJECT` = ${srfwebcontext('project','{"defname":"PROJECT","dename":"ZT_PROJECTSTORY"}')} ) )) 
t1.DELETED = '0' 
( t1.`STATUS` NOT  IN ('draft','closed') ) 

```
### 项目相关需求(ProjectStories)<div id="Story_ProjectStories"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.ASSIGNEDTO AS `ASSIGNEDTOPK`,
t1.`BRANCH`,
t41.`NAME` AS `BRANCHNAME`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`IBIZ_ID`,
t1.`IBIZ_SOURCEID`,
t1.`IBIZ_SOURCENAME`,
t1.`IBIZ_SOURCEOBJECT`,
t1.`ID`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS `ISCHILD`,
'0' AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
'' AS `MAILTOPK`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' ) FROM zt_module tt  WHERE FIND_IN_SET( tt.id, t11.path ) AND tt.type = 'story' GROUP BY	tt.root limit 0,1) end) AS `MODULENAME1`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
t1.`PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCEID`,
t1.`SOURCENAME`,
t1.`SOURCENOTE`,
t1.`SOURCEOBJECT`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`STORYPOINTS`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VERSION` AS `VERSIONC`,
${srfdatacontext('srfparentkey','{"defname":"PROJECT","dename":"ZT_PROJECTSTORY"}')} as project
FROM `zt_story` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.PARENT = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_branch t41 ON t1.BRANCH = t41.ID 

WHERE EXISTS(SELECT * FROM zt_projectstory t51 
 WHERE 
 t1.ID = t51.STORY  AND  ( t51.`PROJECT` = ${srfdatacontext('srfparentkey','{"defname":"PROJECT","dename":"ZT_PROJECTSTORY"}')} ) ) 
t1.DELETED = '0' 

```
### 版本可关联的完成的需求(ReleaseLinkableStories)<div id="Story_ReleaseLinkableStories"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t41.`NAME` AS `BRANCHNAME`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`ID`,
0 AS `ISFAVORITES`,
FALSE AS `ISLEAF`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
t1.`PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCENOTE`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VERSION` AS `VERSIONC`,
'0' AS isChild
FROM `zt_story` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.PARENT = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_branch t41 ON t1.BRANCH = t41.ID
WHERE t1.DELETED = '0' 
(t1.id IN ( select tt3.story from zt_release  tt1 
LEFT JOIN zt_build tt2 on tt1.build = tt2.id  
LEFT JOIN zt_projectstory tt3 on tt3.project = tt2.project
where tt1.id =  #{srf.datacontext.release}
)  or (select tt2.project from zt_release  tt1 
LEFT JOIN zt_build tt2 on tt1.build = tt2.id where tt1.id =  #{srf.datacontext.release}
) = 0)
	AND t1.product =  #{srf.datacontext.product}
	AND not FIND_IN_SET (
t1.ID,(SELECT STORIES FROM ZT_RELEASE WHERE ID =  #{srf.datacontext.release}
)) 

```
### 获取产品发布相关需求(ReleaseStories)<div id="Story_ReleaseStories"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'story' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
t1.`PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCENOTE`,
(select spec from zt_storyspec where story = t1.`ID` and version= t1.`VERSION`) AS `SPEC`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
(select verify from zt_storyspec where story = t1.`ID` and version= t1.`VERSION`) AS `VERIFY`,
t1.`VERSION`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS isChild
FROM `zt_story` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.PARENT = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
WHERE t1.`DELETED` = '0' AND FIND_IN_SET ( t1.ID, (SELECT STORIES FROM ZT_RELEASE WHERE ID = ${srfdatacontext('srfparentkey','{"defname":"ID","dename":"ZT_RELEASE"}')}))
```
### 报告完成需求(ReportStories)<div id="Story_ReportStories"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'story' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
t1.`PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCENOTE`,
(select spec from zt_storyspec where story = t1.`ID` and version= t1.`VERSION`) AS `SPEC`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
(select verify from zt_storyspec where story = t1.`ID` and version= t1.`VERSION`) AS `VERIFY`,
t1.`VERSION`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS isChild
FROM `zt_story` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.PARENT = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
WHERE t1.`DELETED` = '0' AND FIND_IN_SET ( t1.ID, (SELECT STORIES FROM ZT_TESTREPORT WHERE ID= ${srfdatacontext('srfparentkey','{"defname":"ID","dename":"ZT_TESTREPORT "}')}))
```
### 获取产品发布相关需求(StoryChild)<div id="Story_StoryChild"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`BRANCH`, t1.`CHILDSTORIES`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`DELETED`, t1.`DUPLICATESTORY`, t1.`ESTIMATE`, t1.`FROMBUG`, t1.`ID`, t1.`KEYWORDS`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LINKSTORIES`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t21.`TITLE` AS `PARENTNAME`, t11.`PATH`, t1.`PLAN`, t1.`PRI`, t31.`NAME` AS `PRODOCTNAME`, (select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'story' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,t1.`PRODUCT`, t1.`REVIEWEDBY`, t1.`REVIEWEDDATE`, t1.`SOURCE`, t1.`SOURCENOTE`, (select spec from zt_storyspec where story = t1.`ID` and version= t1.`VERSION`) AS `SPEC`, t1.`STAGE`, t1.`STAGEDBY`, t1.`STATUS`, t1.`SUBSTATUS`, t1.`TITLE`, t1.`TOBUG`, t1.`TYPE`, (select verify from zt_storyspec where story = t1.`ID` and version= t1.`VERSION`) AS `VERIFY`, t1.`VERSION` ,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS isChild
FROM `zt_story` t1 LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID LEFT JOIN zt_story t21 ON t1.PARENT = t21.ID LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID WHERE  FIND_IN_SET ( t1.ID, (select tt.childStories from zt_story tt where tt.id = ${srfdatacontext('srfparentkey','{"defname":"ID","dename":"ZT_RELEASE"}')}))
```
### 获取产品发布相关需求(StoryRelated)<div id="Story_StoryRelated"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`BRANCH`, t1.`CHILDSTORIES`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`DELETED`, t1.`DUPLICATESTORY`, t1.`ESTIMATE`, t1.`FROMBUG`, t1.`ID`, t1.`KEYWORDS`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LINKSTORIES`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t21.`TITLE` AS `PARENTNAME`, t11.`PATH`, t1.`PLAN`, t1.`PRI`, t31.`NAME` AS `PRODOCTNAME`, (select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'story' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,t1.`PRODUCT`, t1.`REVIEWEDBY`, t1.`REVIEWEDDATE`, t1.`SOURCE`, t1.`SOURCENOTE`, (select spec from zt_storyspec where story = t1.`ID` and version= t1.`VERSION`) AS `SPEC`, t1.`STAGE`, t1.`STAGEDBY`, t1.`STATUS`, t1.`SUBSTATUS`, t1.`TITLE`, t1.`TOBUG`, t1.`TYPE`, (select verify from zt_storyspec where story = t1.`ID` and version= t1.`VERSION`) AS `VERIFY`, t1.`VERSION` ,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS isChild
FROM `zt_story` t1 LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID LEFT JOIN zt_story t21 ON t1.PARENT = t21.ID LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID WHERE  FIND_IN_SET ( t1.ID, (select tt.linkStories from zt_story tt where tt.id = ${srfdatacontext('srfparentkey','{"defname":"ID","dename":"ZT_RELEASE"}')}))
```
### 需求细分(SubStory)<div id="Story_SubStory"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.ASSIGNEDTO AS `ASSIGNEDTOPK`,
t1.`BRANCH`,
t41.`NAME` AS `BRANCHNAME`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`IBIZ_ID`,
t1.`IBIZ_SOURCEID`,
t1.`IBIZ_SOURCENAME`,
t1.`IBIZ_SOURCEOBJECT`,
t1.`ID`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS `ISCHILD`,
'0' AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
'' AS `MAILTOPK`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' ) FROM zt_module tt  WHERE FIND_IN_SET( tt.id, t11.path ) AND tt.type = 'story' GROUP BY	tt.root limit 0,1) end) AS `MODULENAME1`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
t1.`PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCEID`,
t1.`SOURCENAME`,
t1.`SOURCENOTE`,
t1.`SOURCEOBJECT`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`STORYLATESTFINISHEDDATE`,
t1.`STORYPOINTS`,
t1.`STORYPROVIDEDATE`,
t1.`STORYPROVIDER`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VERSION` AS `VERSIONC`
FROM `zt_story` t1 
LEFT JOIN `zt_module` t11 ON t1.`MODULE` = t11.`ID` 
LEFT JOIN `zt_story` t21 ON t1.`PARENT` = t21.`ID` 
LEFT JOIN `zt_product` t31 ON t1.`PRODUCT` = t31.`ID` 
LEFT JOIN `zt_branch` t41 ON t1.`BRANCH` = t41.`ID` 

WHERE t1.DELETED = '0' 
( 1<>1 ) 

```
### 任务相关需求(TaskRelatedStory)<div id="Story_TaskRelatedStory"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BRANCH`,
t41.`NAME` AS `BRANCHNAME`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'story' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
t1.`PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCENOTE`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`SUBSTATUS`,
CONCAT(t1.id,':',t1.title,'(优先级:',t1.pri,',预计工时:',t1.estimate,')') AS `TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS isChild
FROM `zt_story` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.PARENT = t21.ID 
LEFT JOIN zt_product t31 ON t1.PRODUCT = t31.ID 
LEFT JOIN zt_branch t41 ON t1.BRANCH = t41.ID
WHERE t1.DELETED = '0' 
(t1.`id` in (select tt.`story` from zt_projectstory tt where tt.`project` = ${srfdatacontext('project','{"defname":"ID","dename":"ZT_RELEASE"}')}) or ${srfdatacontext('project','{"defname":"ID","dename":"ZT_RELEASE"}')} is null) 
(t1.module = #{srf.datacontext.module} or #{srf.datacontext.module}  = '0' or #{srf.datacontext.module} is null) 

```
### 默认（全部数据）(VIEW)<div id="Story_View"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.ASSIGNEDTO AS `ASSIGNEDTOPK`,
t1.`BRANCH`,
t41.`NAME` AS `BRANCHNAME`,
t1.`CHILDSTORIES`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`DELETED`,
t1.`DUPLICATESTORY`,
t1.`ESTIMATE`,
t1.`FROMBUG`,
t1.`IBIZ_ID`,
t1.`IBIZ_SOURCEID`,
t1.`IBIZ_SOURCENAME`,
t1.`IBIZ_SOURCEOBJECT`,
t1.`ID`,
(case when t1.parent in (-1) and t1.stage = 'wait' then '1' when t1.parent in (0) and t1.stage = 'wait' then '2'  else '0' end) AS `ISCHILD`,
'0' AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`KEYWORDS`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LINKSTORIES`,
t1.`MAILTO`,
'' AS `MAILTOPK`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT GROUP_CONCAT( tt.NAME SEPARATOR '>' ) FROM zt_module tt  WHERE FIND_IN_SET( tt.id, t11.path ) AND tt.type = 'story' GROUP BY	tt.root limit 0,1) end) AS `MODULENAME1`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t21.`TITLE` AS `PARENTNAME`,
t11.`PATH`,
t1.`PLAN`,
t1.`PRI`,
t31.`NAME` AS `PRODOCTNAME`,
t1.`PRODUCT`,
t1.`REVIEWEDBY`,
t1.`REVIEWEDDATE`,
t1.`SOURCE`,
t1.`SOURCEID`,
t1.`SOURCENAME`,
t1.`SOURCENOTE`,
t1.`SOURCEOBJECT`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STATUS`,
t1.`STORYLATESTFINISHEDDATE`,
t1.`STORYPOINTS`,
t1.`STORYPROVIDEDATE`,
t1.`STORYPROVIDER`,
t1.`SUBSTATUS`,
t1.`TITLE`,
t1.`TOBUG`,
t1.`TYPE`,
t1.`VERSION`,
t1.`VERSION` AS `VERSIONC`
FROM `zt_story` t1 
LEFT JOIN `zt_module` t11 ON t1.`MODULE` = t11.`ID` 
LEFT JOIN `zt_story` t21 ON t1.`PARENT` = t21.`ID` 
LEFT JOIN `zt_product` t31 ON t1.`PRODUCT` = t31.`ID` 
LEFT JOIN `zt_branch` t41 ON t1.`BRANCH` = t41.`ID` 

WHERE t1.DELETED = '0' 

```

# **需求描述**(ZT_STORYSPEC)

### DEFAULT(DEFAULT)<div id="StorySpec_Default"></div>
```sql
SELECT
concat(t1.`STORY`,'__',t1.`VERSION`) AS `ID`,
t1.`SPEC`,
t1.`STORY`,
t1.`TITLE`,
t1.`VERIFY`,
t1.`VERSION`
FROM `zt_storyspec` t1 

```
### 版本(Version)<div id="StorySpec_Version"></div>
```sql
SELECT
concat(t1.`STORY`,'__',t1.`VERSION`) AS `ID`,
t1.`STORY`,
t1.`VERSION`,
CONCAT('#',t1.`version`) as `TITLE`
FROM `zt_storyspec` t1 

```
### 默认（全部数据）(VIEW)<div id="StorySpec_View"></div>
```sql
SELECT
concat(t1.`STORY`,'__',t1.`VERSION`) AS `ID`,
t1.`SPEC`,
t1.`STORY`,
t1.`TITLE`,
t1.`VERIFY`,
t1.`VERSION`
FROM `zt_storyspec` t1 

```

# **需求阶段**(ZT_STORYSTAGE)

### DEFAULT(DEFAULT)<div id="StoryStage_Default"></div>
```sql
SELECT
t1.`BRANCH`,
concat(t1.`STORY`,'__',t1.`BRANCH`) AS `ID`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STORY`
FROM `zt_storystage` t1 

```
### 默认（全部数据）(VIEW)<div id="StoryStage_View"></div>
```sql
SELECT
t1.`BRANCH`,
concat(t1.`STORY`,'__',t1.`BRANCH`) AS `ID`,
t1.`STAGE`,
t1.`STAGEDBY`,
t1.`STORY`
FROM `zt_storystage` t1 

```

# **套件用例**(ZT_SUITECASE)

### DEFAULT(DEFAULT)<div id="SuiteCase_Default"></div>
```sql
SELECT
t1.`CASE`,
concat(t1.`SUITE`,'__',t1.`CASE`) AS `ID`,
t1.`PRODUCT`,
t1.`SUITE`,
t1.`VERSION`
FROM `zt_suitecase` t1 

```
### 默认（全部数据）(VIEW)<div id="SuiteCase_View"></div>
```sql
SELECT
t1.`CASE`,
concat(t1.`SUITE`,'__',t1.`CASE`) AS `ID`,
t1.`PRODUCT`,
t1.`SUITE`,
t1.`VERSION`
FROM `zt_suitecase` t1 

```

# **部门**(SYS_DEPT)

### 数据查询(DEFAULT)<div id="SysDepartment_Default"></div>
```sql
SELECT
t1.`BCODE`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DEPTCODE`,
t1.`DEPTID`,
t1.`DEPTLEVEL`,
t1.`DEPTNAME`,
t1.`DOMAINS`,
t1.`ENABLE`,
t1.`LEADERID`,
t1.`LEADERNAME`,
t1.`ORGID`,
t11.`ORGNAME`,
t1.`PDEPTID`,
t21.`DEPTNAME` AS `PDEPTNAME`,
t1.`SHORTNAME`,
t1.`SHOWORDER`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_SYS_DEPT` t1 
LEFT JOIN `T_SYS_ORG` t11 ON t1.`ORGID` = t11.`ORGID` 
LEFT JOIN `T_SYS_DEPT` t21 ON t1.`PDEPTID` = t21.`DEPTID` 

WHERE t1.ENABLE = 1 

```
### 默认（全部数据）(VIEW)<div id="SysDepartment_View"></div>
```sql
SELECT
t1.`BCODE`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DEPTCODE`,
t1.`DEPTID`,
t1.`DEPTLEVEL`,
t1.`DEPTNAME`,
t1.`DOMAINS`,
t1.`ENABLE`,
t1.`LEADERID`,
t1.`LEADERNAME`,
t1.`ORGID`,
t11.`ORGNAME`,
t1.`PDEPTID`,
t21.`DEPTNAME` AS `PDEPTNAME`,
t1.`SHORTNAME`,
t1.`SHOWORDER`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_SYS_DEPT` t1 
LEFT JOIN `T_SYS_ORG` t11 ON t1.`ORGID` = t11.`ORGID` 
LEFT JOIN `T_SYS_DEPT` t21 ON t1.`PDEPTID` = t21.`DEPTID` 

WHERE t1.ENABLE = 1 

```

# **人员**(IBZEMP)

### Bug用户(BugUser)<div id="SysEmployee_BugUser"></div>
```sql

```
### 联系人用户(ContActList)<div id="SysEmployee_ContActList"></div>
```sql
SELECT
t1.`ADDR`,
t1.`AVATAR`,
t1.`BCODE`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DOMAINS`,
t1.`EMAIL`,
t1.`ENABLE`,
t1.`IPADDR`,
t1.`LANG`,
t1.`LOGINNAME`,
t1.`MDEPTCODE`,
t1.`MDEPTID`,
t1.`MDEPTNAME`,
t1.`MEMO`,
t1.`NICKNAME`,
t1.`ORGCODE`,
t1.`ORGID`,
t1.`ORGNAME`,
t1.`PASSWORD`,
t1.`PERSONNAME`,
t1.`PHONE`,
t1.`RESERVER`,
t1.`SEX`,
t1.`SHOWORDER`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCODE`,
t1.`USERICON`,
t1.`USERID`,
t1.`USERNAME`
FROM `T_IBZEMP` t1 

```
### 数据查询(DEFAULT)<div id="SysEmployee_Default"></div>
```sql
SELECT
t1.`ADDR`,
t1.`AVATAR`,
t1.`BCODE`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DOMAINS`,
t1.`EMAIL`,
t1.`ENABLE`,
t1.`IPADDR`,
t1.`LANG`,
t1.`LOGINNAME`,
t1.`MDEPTCODE`,
t1.`MDEPTID`,
t1.`MDEPTNAME`,
t1.`MEMO`,
t1.`NICKNAME`,
t1.`ORGCODE`,
t1.`ORGID`,
t1.`ORGNAME`,
t1.`PASSWORD`,
t1.`PERSONNAME`,
t1.`PHONE`,
t1.`RESERVER`,
t1.`SEX`,
t1.`SHOWORDER`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCODE`,
t1.`USERICON`,
t1.`USERID`,
t1.`USERNAME`
FROM `T_IBZEMP` t1 

```
### 项目团队管理(ProductTeamM)<div id="SysEmployee_ProductTeamM"></div>
```sql
SELECT
t1.`ADDR`,
t1.`AVATAR`,
t1.`BCODE`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DOMAINS`,
t1.`EMAIL`,
t1.`ENABLE`,
t1.`IPADDR`,
t1.`LANG`,
t1.`LOGINNAME`,
t1.`MDEPTCODE`,
t1.`MDEPTID`,
t1.`MDEPTNAME`,
t1.`MEMO`,
t1.`NICKNAME`,
t1.`ORGCODE`,
t1.`ORGID`,
t1.`ORGNAME`,
t1.`PASSWORD`,
t1.`PERSONNAME`,
t1.`PHONE`,
t1.`RESERVER`,
t1.`SEX`,
t1.`SHOWORDER`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCODE`,
t1.`USERICON`,
t1.`USERID`,
t1.`USERNAME`
FROM `T_IBZEMP` t1 

WHERE ( (t1.account = ${srfwebcontext('account','{"defname":"ACCOUNT","dename":"ZT_USER"}')} or t1.account not in (select t.account from zt_team t where t.type = 'project' and t.root = ${srfwebcontext('srfparentkey','{"defname":"ACCOUNT","dename":"ZT_USER"}')})) ) 

```
### 项目团队管理(ProjectTeamM)<div id="SysEmployee_ProjectTeamM"></div>
```sql
SELECT
t1.`ADDR`,
t1.`AVATAR`,
t1.`BCODE`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DOMAINS`,
t1.`EMAIL`,
t1.`ENABLE`,
t1.`IPADDR`,
t1.`LANG`,
t1.`LOGINNAME`,
t1.`MDEPTCODE`,
t1.`MDEPTID`,
t1.`MDEPTNAME`,
t1.`MEMO`,
t1.`NICKNAME`,
t1.`ORGCODE`,
t1.`ORGID`,
t1.`ORGNAME`,
t1.`PASSWORD`,
t1.`PERSONNAME`,
t1.`PHONE`,
t1.`RESERVER`,
t1.`SEX`,
t1.`SHOWORDER`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCODE`,
t1.`USERICON`,
t1.`USERID`,
t1.`USERNAME`
FROM `T_IBZEMP` t1 

WHERE ( (t1.account = ${srfwebcontext('account','{"defname":"ACCOUNT","dename":"ZT_USER"}')} or t1.account not in (select t.account from zt_team t where t.type = 'project' and t.root = ${srfwebcontext('srfparentkey','{"defname":"ACCOUNT","dename":"ZT_USER"}')})) ) 

```
### 项目团队管理(ProjectTeamMProduct)<div id="SysEmployee_ProjectTeamMProduct"></div>
```sql
SELECT
t1.`ADDR`,
t1.`AVATAR`,
t1.`BCODE`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DOMAINS`,
t1.`EMAIL`,
t1.`ENABLE`,
t1.`IPADDR`,
t1.`LANG`,
t1.`LOGINNAME`,
t1.`MDEPTCODE`,
t1.`MDEPTID`,
t1.`MDEPTNAME`,
t1.`MEMO`,
t1.`NICKNAME`,
t1.`ORGCODE`,
t1.`ORGID`,
t1.`ORGNAME`,
t1.`PASSWORD`,
t1.`PERSONNAME`,
t1.`PHONE`,
t1.`RESERVER`,
t1.`SEX`,
t1.`SHOWORDER`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCODE`,
t1.`USERICON`,
t1.`USERID`,
t1.`USERNAME`
FROM `T_IBZEMP` t1 

WHERE ( (t1.account = ${srfwebcontext('account','{"defname":"ACCOUNT","dename":"ZT_USER"}')} or t1.account not in (select t.account from zt_team t where t.type = 'project' and t.root = ${srfwebcontext('srfparentkey','{"defname":"ACCOUNT","dename":"ZT_USER"}')})) ) 

```
### 项目团队成员(临时)(ProjectTeamTaskUserTemp)<div id="SysEmployee_ProjectTeamTaskUserTemp"></div>
```sql

```
### 项目团队成员(ProjectTeamUser)<div id="SysEmployee_ProjectTeamUser"></div>
```sql

```
### 项目团队成员(ProjectTeamUserTask)<div id="SysEmployee_ProjectTeamUserTask"></div>
```sql

```
### 项目团队成员选择(ProjectteamPk)<div id="SysEmployee_ProjectteamPk"></div>
```sql
SELECT
t1.`ADDR`,
t1.`AVATAR`,
t1.`BCODE`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DOMAINS`,
t1.`EMAIL`,
t1.`ENABLE`,
t1.`IPADDR`,
t1.`LANG`,
t1.`LOGINNAME`,
t1.`MDEPTCODE`,
t1.`MDEPTID`,
t1.`MDEPTNAME`,
t1.`MEMO`,
t1.`NICKNAME`,
t1.`ORGCODE`,
t1.`ORGID`,
t1.`ORGNAME`,
t1.`PASSWORD`,
t1.`PERSONNAME`,
t1.`PHONE`,
t1.`RESERVER`,
t1.`SEX`,
t1.`SHOWORDER`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCODE`,
t1.`USERICON`,
t1.`USERID`,
t1.`USERNAME`
FROM `T_IBZEMP` t1 

```
### 产品团队成员选择(StoryProductTeamChoice)<div id="SysEmployee_StoryProductTeamPK"></div>
```sql
SELECT
t1.`ADDR`,
t1.`AVATAR`,
t1.`BCODE`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DOMAINS`,
t1.`EMAIL`,
t1.`ENABLE`,
t1.`IPADDR`,
t1.`LANG`,
t1.`LOGINNAME`,
t1.`MDEPTCODE`,
t1.`MDEPTID`,
t1.`MDEPTNAME`,
t1.`MEMO`,
t1.`NICKNAME`,
t1.`ORGCODE`,
t1.`ORGID`,
t1.`ORGNAME`,
t1.`PASSWORD`,
t1.`PERSONNAME`,
t1.`PHONE`,
t1.`RESERVER`,
t1.`SEX`,
t1.`SHOWORDER`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCODE`,
t1.`USERICON`,
t1.`USERID`,
t1.`USERNAME`
FROM `T_IBZEMP` t1 

```
### 任务多人团队(TaskMTeam)<div id="SysEmployee_TaskMTeam"></div>
```sql

```
### 任务团队(TASKTEAM)<div id="SysEmployee_TaskTeam"></div>
```sql

```
### 默认（全部数据）(VIEW)<div id="SysEmployee_View"></div>
```sql
SELECT
t1.`ADDR`,
t1.`AVATAR`,
t1.`BCODE`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DOMAINS`,
t1.`EMAIL`,
t1.`ENABLE`,
t1.`IPADDR`,
t1.`LANG`,
t1.`LOGINNAME`,
t1.`MDEPTCODE`,
t1.`MDEPTID`,
t1.`MDEPTNAME`,
t1.`MEMO`,
t1.`NICKNAME`,
t1.`ORGCODE`,
t1.`ORGID`,
t1.`ORGNAME`,
t1.`PASSWORD`,
t1.`PERSONNAME`,
t1.`PHONE`,
t1.`RESERVER`,
t1.`SEX`,
t1.`SHOWORDER`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`USERCODE`,
t1.`USERICON`,
t1.`USERID`,
t1.`USERNAME`
FROM `T_IBZEMP` t1 

```

# **单位**(SYS_ORG)

### 数据查询(DEFAULT)<div id="SysOrganization_Default"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`DOMAINS`,
t1.`ENABLE`,
t1.`ORGCODE`,
t1.`ORGID`,
t1.`ORGLEVEL`,
t1.`ORGNAME`,
t1.`PORGID`,
t11.`ORGNAME` AS `PORGNAME`,
t1.`SHORTNAME`,
t1.`SHOWORDER`,
t1.`UPDATEDATE`
FROM `T_SYS_ORG` t1 
LEFT JOIN `T_SYS_ORG` t11 ON t1.`PORGID` = t11.`ORGID` 

WHERE t1.ENABLE = 1 

```
### 默认（全部数据）(VIEW)<div id="SysOrganization_View"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`DOMAINS`,
t1.`ENABLE`,
t1.`ORGCODE`,
t1.`ORGID`,
t1.`ORGLEVEL`,
t1.`ORGNAME`,
t1.`PORGID`,
t11.`ORGNAME` AS `PORGNAME`,
t1.`SHORTNAME`,
t1.`SHOWORDER`,
t1.`UPDATEDATE`
FROM `T_SYS_ORG` t1 
LEFT JOIN `T_SYS_ORG` t11 ON t1.`PORGID` = t11.`ORGID` 

WHERE t1.ENABLE = 1 

```

# **岗位**(SYS_POST)

### 数据查询(DEFAULT)<div id="SysPost_Default"></div>
```sql
SELECT
t1.`DOMAINS`,
t1.`MEMO`,
t1.`POSTCODE`,
t1.`POSTID`,
t1.`POSTNAME`
FROM `IBZPOST` t1 

```
### 默认（全部数据）(VIEW)<div id="SysPost_View"></div>
```sql
SELECT
t1.`DOMAINS`,
t1.`MEMO`,
t1.`POSTCODE`,
t1.`POSTID`,
t1.`POSTNAME`
FROM `IBZPOST` t1 

```

# **系统角色**(SYS_ROLE)

### 数据查询(DEFAULT)<div id="SysRole_Default"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`MEMO`,
t1.`PROLEID`,
t11.`SYS_ROLENAME` AS `PROLENAME`,
t1.`SYS_ROLEID`,
t1.`SYS_ROLENAME`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `IBZROLE` t1 
LEFT JOIN `IBZROLE` t11 ON t1.`PROLEID` = t11.`SYS_ROLEID` 

```
### 默认（全部数据）(VIEW)<div id="SysRole_View"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`MEMO`,
t1.`PROLEID`,
t11.`SYS_ROLENAME` AS `PROLENAME`,
t1.`SYS_ROLEID`,
t1.`SYS_ROLENAME`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `IBZROLE` t1 
LEFT JOIN `IBZROLE` t11 ON t1.`PROLEID` = t11.`SYS_ROLEID` 

```

# **组**(SYS_TEAM)

### 数据查询(DEFAULT)<div id="SysTeam_Default"></div>
```sql
SELECT
t1.`DOMAINS`,
t1.`MEMO`,
t1.`TEAMID`,
t1.`TEAMNAME`
FROM `IBZTEAM` t1 

```
### 默认（全部数据）(VIEW)<div id="SysTeam_View"></div>
```sql
SELECT
t1.`DOMAINS`,
t1.`MEMO`,
t1.`TEAMID`,
t1.`TEAMNAME`
FROM `IBZTEAM` t1 

```

# **组成员**(SYS_TEAMMEMBER)

### 数据查询(DEFAULT)<div id="SysTeamMember_Default"></div>
```sql
SELECT
t1.`DOMAINS`,
t31.`PERSONNAME`,
t1.`POSTID`,
t21.`POSTNAME`,
t1.`TEAMID`,
t1.`TEAMMEMBERID`,
t11.`TEAMNAME`,
t31.`USERICON`,
t1.`USERID`,
t31.`USERNAME`
FROM `IBZTEAMMEMBER` t1 
LEFT JOIN `IBZTEAM` t11 ON t1.`TEAMID` = t11.`TEAMID` 
LEFT JOIN `IBZPOST` t21 ON t1.`POSTID` = t21.`POSTID` 
LEFT JOIN `T_IBZEMP` t31 ON t1.`USERID` = t31.`USERID` 

```
### 默认（全部数据）(VIEW)<div id="SysTeamMember_View"></div>
```sql
SELECT
t1.`DOMAINS`,
t31.`PERSONNAME`,
t1.`POSTID`,
t21.`POSTNAME`,
t1.`TEAMID`,
t1.`TEAMMEMBERID`,
t11.`TEAMNAME`,
t31.`USERICON`,
t1.`USERID`,
t31.`USERNAME`
FROM `IBZTEAMMEMBER` t1 
LEFT JOIN `IBZTEAM` t11 ON t1.`TEAMID` = t11.`TEAMID` 
LEFT JOIN `IBZPOST` t21 ON t1.`POSTID` = t21.`POSTID` 
LEFT JOIN `T_IBZEMP` t31 ON t1.`USERID` = t31.`USERID` 

```

# **系统更新功能**(SYS_UPDATE_FEATURES)

### 数据查询(DEFAULT)<div id="SysUpdateFeatures_Default"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DISPLAYORDER`,
t1.`SYS_UPDATE_FEATURESID`,
t1.`SYS_UPDATE_FEATURESNAME`,
t1.`SYS_UPDATE_LOGID`,
t11.`SYS_UPDATE_LOGNAME`,
t1.`TYPE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPFEATURES`,
t1.FEATURESDESC
FROM `T_SYS_UPDATE_FEATURES` t1 
LEFT JOIN T_SYS_UPDATE_LOG t11 ON t1.SYS_UPDATE_LOGID = t11.SYS_UPDATE_LOGID 

```
### 默认（全部数据）(VIEW)<div id="SysUpdateFeatures_View"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DISPLAYORDER`,
t1.`FEATURESDESC`,
t1.`SYS_UPDATE_FEATURESID`,
t1.`SYS_UPDATE_FEATURESNAME`,
t1.`SYS_UPDATE_LOGID`,
t11.`SYS_UPDATE_LOGNAME`,
t1.`TYPE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPFEATURES`
FROM `T_SYS_UPDATE_FEATURES` t1 
LEFT JOIN `T_SYS_UPDATE_LOG` t11 ON t1.`SYS_UPDATE_LOGID` = t11.`SYS_UPDATE_LOGID` 

```

# **更新日志**(SYS_UPDATE_LOG)

### 数据查询(DEFAULT)<div id="SysUpdateLog_Default"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`LATESTUPDATE`,
t1.`SYS_UPDATE_LOGID`,
t1.`SYS_UPDATE_LOGNAME`,
t1.`UPDATE`,
t1.`UPDATEBRANCH`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_SYS_UPDATE_LOG` t1 

```
### 默认（全部数据）(VIEW)<div id="SysUpdateLog_View"></div>
```sql
SELECT
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`LATESTUPDATE`,
t1.`SYS_UPDATE_LOGID`,
t1.`SYS_UPDATE_LOGNAME`,
t1.`UPDATE`,
t1.`UPDATEBRANCH`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDESC`
FROM `T_SYS_UPDATE_LOG` t1 

```

# **系统用户**(SYS_USER)

### 数据查询(DEFAULT)<div id="SysUser_Default"></div>
```sql
SELECT
t1.`ADDR`,
t1.`AVATAR`,
t1.`BCODE`,
t1.`BIRTHDAY`,
t1.`CERTCODE`,
t1.`DOMAINS`,
t1.`EMAIL`,
t1.`FONTSIZE`,
t1.`LANG`,
t1.`LOGINNAME`,
t1.`MDEPTCODE`,
t1.`MDEPTID`,
t1.`MDEPTNAME`,
t1.`MEMO`,
t1.`NICKNAME`,
t1.`ORGCODE`,
t1.`ORGID`,
t1.`ORGNAME`,
t1.`PASSWORD`,
t1.`PERSONNAME`,
t1.`PHONE`,
t1.`POSTCODE`,
t1.`POSTID`,
t1.`POSTNAME`,
t1.`RESERVER`,
t1.`SEX`,
t1.`SUPERUSER`,
t1.`THEME`,
t1.`USERCODE`,
t1.`USERICON`,
t1.`USERID`,
t1.`USERNAME`
FROM `IBZUSER` t1 

```
### 默认（全部数据）(VIEW)<div id="SysUser_View"></div>
```sql
SELECT
t1.`ADDR`,
t1.`AVATAR`,
t1.`BCODE`,
t1.`BIRTHDAY`,
t1.`CERTCODE`,
t1.`DOMAINS`,
t1.`EMAIL`,
t1.`FONTSIZE`,
t1.`LANG`,
t1.`LOGINNAME`,
t1.`MDEPTCODE`,
t1.`MDEPTID`,
t1.`MDEPTNAME`,
t1.`MEMO`,
t1.`NICKNAME`,
t1.`ORGCODE`,
t1.`ORGID`,
t1.`ORGNAME`,
t1.`PASSWORD`,
t1.`PERSONNAME`,
t1.`PHONE`,
t1.`POSTCODE`,
t1.`POSTID`,
t1.`POSTNAME`,
t1.`RESERVER`,
t1.`SEX`,
t1.`SUPERUSER`,
t1.`THEME`,
t1.`USERCODE`,
t1.`USERICON`,
t1.`USERID`,
t1.`USERNAME`
FROM `IBZUSER` t1 

```

# **用户角色关系**(SYS_USER_ROLE)

### 数据查询(DEFAULT)<div id="SysUserRole_Default"></div>
```sql
SELECT
t1.`CREATEDATE`,
t21.`LOGINNAME`,
t21.`MDEPTNAME`,
t21.`ORGNAME`,
t1.`SYS_ROLEID`,
t11.`SYS_ROLENAME`,
t1.`SYS_USERID`,
t21.`PERSONNAME` AS `SYS_USERNAME`,
t1.`SYS_USER_ROLEID`,
t1.`UPDATEDATE`
FROM `IBZUSER_ROLE` t1 
LEFT JOIN `IBZROLE` t11 ON t1.`SYS_ROLEID` = t11.`SYS_ROLEID` 
LEFT JOIN `IBZUSER` t21 ON t1.`SYS_USERID` = t21.`USERID` 

```
### 默认（全部数据）(VIEW)<div id="SysUserRole_View"></div>
```sql
SELECT
t1.`CREATEDATE`,
t21.`LOGINNAME`,
t21.`MDEPTNAME`,
t21.`ORGNAME`,
t1.`SYS_ROLEID`,
t11.`SYS_ROLENAME`,
t1.`SYS_USERID`,
t21.`PERSONNAME` AS `SYS_USERNAME`,
t1.`SYS_USER_ROLEID`,
t1.`UPDATEDATE`
FROM `IBZUSER_ROLE` t1 
LEFT JOIN `IBZROLE` t11 ON t1.`SYS_ROLEID` = t11.`SYS_ROLEID` 
LEFT JOIN `IBZUSER` t21 ON t1.`SYS_USERID` = t21.`USERID` 

```

# **任务**(ZT_TASK)

### 指派给我任务(AssignedToMyTask)<div id="Task_AssignedToMyTask"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, (select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'task' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, t1.`TYPE`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) as `STATUS1`, (case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when t1.`status` = 'done' then 30 when t1.`status` = 'closed' then 40 when t1.`status` = 'cancel' then 50 else 60 end) as statusorder ,
t1.`PLAN`,
t1.`TASKSPECIES`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
( t1.`ASSIGNEDTO` =  #{srf.sessioncontext.srfloginname} ) 
(t1.parent <= 0 or (t1.parent > 0 and t1.assignedTo not in (select t.assignedTo from zt_task t where  t.deleted = '0' and t.id = t1.parent))) 

```
### 指派给我任务（EE）(AssignedToMyTaskEE)<div id="Task_AssignedToMyTaskEE"></div>
```sql
SELECT
	t1.`ASSIGNEDDATE`,
	t1.`ASSIGNEDTO`,
	t1.`CANCELEDBY`,
	t1.`CANCELEDDATE`,
	t1.`CLOSEDBY`,
	t1.`CLOSEDDATE`,
	t1.`CLOSEDREASON`,
	t1.`COLOR`,
	t1.`CONSUMED`,
	t1.`DEADLINE`,
	t1.`DELETED`,
	( To_Days( t1.`DEADLINE` ) - To_Days( t1.`ESTSTARTED` ) ) AS `DURATION`,
	t1.`ESTIMATE`,
	t1.`ESTSTARTED`,
	t1.`FINISHEDBY`,
	t1.`FINISHEDDATE`,
	t1.`FROMBUG`,
	t1.`ID`,
	(
SELECT
	( CASE WHEN COUNT( t.IBZ_FAVORITESID ) > 0 THEN 1 ELSE 0 END ) AS ISFAVORITES 
FROM
	T_IBZ_FAVORITES t 
WHERE
	t.TYPE = 'task' 
	AND t.ACCOUNT = #{srf.sessioncontext.srfloginname}
	
	AND t.OBJECTID = t1.id 
	) AS `ISFAVORITES`,
	( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
	t1.`LASTEDITEDBY`,
	t1.`LASTEDITEDDATE`,
	t1.`LEFT`,
	t1.`MODULE`,
	t11.`NAME` AS `MODULENAME`,
	(
	SELECT
	CASE
			
		WHEN
			tt.type = 'task' THEN
				GROUP_CONCAT( tt.NAME SEPARATOR '>' ) ELSE CONCAT_WS( '', t2.`name`, '>', GROUP_CONCAT( tt.NAME SEPARATOR '>' ) ) 
			END AS `name` 
		FROM
			zt_module tt
			LEFT JOIN zt_product t2 ON tt.root = t2.id 
		WHERE
			FIND_IN_SET( tt.id, t11.path ) 
		GROUP BY
			tt.root 
			LIMIT 0,
			1 
		) AS `MODULENAME1`,
		(
		SELECT
		CASE
				
			WHEN
				count( t.`id` ) > 0 THEN
					1 ELSE 0 
				END 
				FROM
					`zt_team` t 
				WHERE
					t.`type` = 'task' 
					AND t.`root` = t1.`id` 
				) AS `MULTIPLE`,
				t1.`NAME`,
				t1.`OPENEDBY`,
				t1.`OPENEDDATE`,
				t1.`PARENT`,
				t51.`NAME` AS `PARENTNAME`,
				t11.`PATH`,
				t1.`PRI`,
				t21.`PRODUCT`,
				t41.`NAME` AS `PRODUCTNAME`,
				t1.`PROJECT`,
				t31.`NAME` AS `PROJECTNAME`,
				t1.`REALSTARTED`,
				t1.`STATUS`,
				t1.`STORY`,
				t21.`TITLE` AS `STORYNAME`,
				t1.`STORYVERSION`,
				t1.`SUBSTATUS`,
				t1.`TYPE`,
				(
				CASE
						
						WHEN (
						SELECT
						CASE
								
							WHEN
								count( t.`id` ) > 0 THEN
									1 ELSE 0 
								END 
								FROM
									`zt_team` t 
								WHERE
									t.`type` = 'task' 
									AND t.`root` = t1.`id` 
									) = 1 THEN
									'10' 
									WHEN t1.parent = - 1 THEN
									'20' 
									WHEN t1.parent = 0 THEN
									'30' ELSE '40' 
								END 
								) AS `TASKTYPE`,
								( CASE WHEN t1.storyVersion < t21.version AND t21.`status` <> 'changed' THEN 'storychange' ELSE t1.`status` END ) AS `STATUS1`,
								(
								CASE
										
										WHEN t1.`status` = 'wait' THEN
										10 
										WHEN t1.`status` = 'doing' THEN
										20 
										WHEN t1.`status` = 'done' THEN
										30 
										WHEN t1.`status` = 'closed' THEN
										40 
										WHEN t1.`status` = 'cancel' THEN
										50 ELSE 60 
									END 
									) AS statusorder,
									t1.`PLAN`,
									t1.`TASKSPECIES`,
									t1.`ORDERNUM` AS `ORDERNUM`,
				CASE
        WHEN t1.`status` = 'wait' THEN
        CONCAT_WS( '', 60,case when t1.`PRI` = 1 then 4
        when t1.pri = 2 then 3 
        when t1.pri = 3 then 2 
        when t1.pri = 4 then 1 end , LPAD( t1.id, 8, 0 ) ) 
        WHEN t1.`status` = 'doing' THEN
        CONCAT_WS( '', 50,case when t1.`PRI` = 1 then 4
        when t1.pri = 2 then 3
        when t1.pri = 3 then 2 
        when t1.pri = 4 then 1 end , LPAD( t1.id, 8, 0 ) ) 
        WHEN t1.`status` = 'done' THEN
        CONCAT_WS( '', 40,case when t1.`PRI` = 1 then 4 
        when t1.pri = 2 then 3 
        when t1.pri = 3 then 2 
        when t1.pri = 4 then 1 end , LPAD( t1.id, 8, 0 ) ) 
        WHEN t1.`status` = 'closed' THEN
        CONCAT_WS( '', 30,case when t1.`PRI` = 1 then 4 
        when t1.pri = 2 then 3 
        when t1.pri = 3 then 2 
        when t1.pri = 4 then 1 end , LPAD( t1.id, 8, 0 ) ) 
        WHEN t1.`status` = 'cancel' THEN
        CONCAT_WS( '', 20,case when t1.`PRI` = 1 then 4 
        when t1.pri = 2 then 3  
        when t1.pri = 3 then 2 
        when t1.pri = 4 then 1 end , LPAD( t1.id, 8, 0 ) ) 
        ELSE CONCAT_WS( '', 10,case when t1.`PRI` = 1 then 4 
        when t1.pri = 2 then 3  
        when t1.pri = 3 then 2 
        when t1.pri = 4 then 1 end , LPAD( t1.id, 8, 0 ) ) 
        END AS `TASKSN`,
									t61.`TITLE` AS `PLANNAME` 
								FROM
									`zt_task` t1
									LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID
									LEFT JOIN zt_story t21 ON t1.STORY = t21.ID
									LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID
									LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID
								LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID
LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
( t1.`ASSIGNEDTO` =  #{srf.sessioncontext.srfloginname} ) 
(#{srf.datacontext.formitem} is null or #{srf.datacontext.formitem} = 'ALL' or (#{srf.datacontext.formitem} = 'CY' and t1.`status` in( 'doing' ,'wait')) or (#{srf.datacontext.formitem} = 'HH' and t1.`status` = 'done' )) 

```
### 指派给我任务（PC）(AssignedToMyTaskPc)<div id="Task_AssignedToMyTaskPc"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, (select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'task' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, t1.`TYPE`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) as `STATUS1`, (case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when t1.`status` = 'done' then 30 when t1.`status` = 'closed' then 40 when t1.`status` = 'cancel' then 50 else 60 end) as statusorder ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID  LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
( t1.`ASSIGNEDTO` =  #{srf.sessioncontext.srfloginname} ) 
t1.estStarted is not null and t1.estStarted <> '0000-00-00' and t1.estStarted <> '1970-01-01' 
t1.parent >= 0 
t1.assigneddate is not null and t1.assigneddate <> '0000-00-00 00:00:00' 

```
### Bug相关任务(BugTask)<div id="Task_BugTask"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, 0 AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, CONCAT_WS(':',t1.id,t61.realname,t1.`name`) as `NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, t1.`TYPE`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) as `STATUS1`, (case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when t1.`status` = 'done' then 30 when t1.`status` = 'closed' then 40 when t1.`status` = 'cancel' then 50 else 60 end) as statusorder ,
t1.`PLAN`,
t71.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN zt_user t61 on t61.account = t1.assignedTo  LEFT JOIN `zt_productplan` t71 ON t1.`PLAN` = t71.`ID`
WHERE t1.deleted = '0' 
(t1.project = #{srf.webcontext.project} or t1.project = #{srf.webcontext.curproject}) 

```
### 通过模块查询(ByModule)<div id="Task_ByModule"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, t1.`DELETED`, (select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'task' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) as DELAY, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, t1.`TYPE`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') as progressrate, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) as `STATUS1`, (case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when t1.`status` = 'done' then 30 when t1.`status` = 'closed' then 40 when t1.`status` = 'cancel' then 50 else 60 end) as statusorder ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
(( t1.`PARENT` = 0  or  t1.`PARENT` = -1) and t1.`project` = #{srf.datacontext.project}) 
FIND_IN_SET (#{srf.datacontext.srfparentkey}, t11.`PATH`) 

```
### 通过模块查询（聚微）(ByModuleEE)<div id="Task_ByModuleEE"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, t1.`DELETED`, (select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'task' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) as DELAY, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, t1.`TYPE`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') as progressrate, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) as `STATUS1`, (case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when t1.`status` = 'done' then 30 when t1.`status` = 'closed' then 40 when t1.`status` = 'cancel' then 50 else 60 end) as statusorder ,
t1.`PLAN`,
t1.`ORDERNUM` AS `ORDERNUM`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
(( t1.`PARENT` = 0  or  t1.`PARENT` = -1) and t1.`project` = #{srf.datacontext.project}) 
FIND_IN_SET (#{srf.datacontext.srfparentkey}, t11.`PATH`) 

```
### 数据查询（子任务）(ChildDefault)<div id="Task_ChildDefault"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
case when exists (select 1 from zt_team t where t.type = 'task' and t1.id = t.root) then (select GROUP_CONCAT(t.account) from zt_team t where t.type = 'task' and t1.id = t.root) else t1.assignedTo end as `ASSIGNEDTO`,
t1.`CANCELEDBY`,
t1.`CANCELEDDATE`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`CONSUMED`,
t1.`DEADLINE`,
t1.`DELETED`,
case when ( To_Days( t1.`DEADLINE` ) - To_Days( t1.`ESTSTARTED` ) ) is null then null else ( To_Days( t1.`DEADLINE` ) - To_Days( t1.`ESTSTARTED` ) ) + 1 end  AS `DURATION`,
t1.`ESTIMATE`,
t1.`ESTSTARTED`,
t1.`FINISHEDBY`,
t1.`FINISHEDDATE`,
t1.`FROMBUG`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'task' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LEFT`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
(SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) AS `MODULENAME1`,
(case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`,
( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`,
t1.`NAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t51.`NAME` AS `PARENTNAME`,
t11.`PATH`,
t1.`PRI`,
t21.`PRODUCT`,
t41.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t31.`NAME` AS `PROJECTNAME`,
t1.`REALSTARTED`,
t1.`STATUS`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TYPE`,
	( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`,
CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') as progressrate,
(case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) as `STATUS1`,
(case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when t1.`status` = 'done' then 30 when t1.`status` = 'closed' then 40 when t1.`status` = 'cancel' then 50 else 60 end)
as statusorder,
t1.`PLAN`,
t1.`TASKSPECIES`,
t1.`ORDERNUM` AS `ORDERNUM`,
t61.`TITLE` AS `PLANNAME`
FROM `zt_task` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.STORY = t21.ID 
LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID 
LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID 
LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID
LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
t1.`parent` = #{srf.webcontext.parent} 

```
### 数据查询（子任务）（更多）(ChildDefaultMore)<div id="Task_ChildDefaultMore"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
case when exists (select 1 from zt_team t where t.type = 'task' and t1.id = t.root) then (select GROUP_CONCAT(t.account) from zt_team t where t.type = 'task' and t1.id = t.root) else t1.assignedTo end as `ASSIGNEDTO`,
t1.`CANCELEDBY`,
t1.`CANCELEDDATE`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`CONSUMED`,
t1.`DEADLINE`,
t1.`DELETED`,
case when ( To_Days( t1.`DEADLINE` ) - To_Days( t1.`ESTSTARTED` ) ) is null then null else ( To_Days( t1.`DEADLINE` ) - To_Days( t1.`ESTSTARTED` ) ) + 1 end  AS `DURATION`,
t1.`ESTIMATE`,
t1.`ESTSTARTED`,
t1.`FINISHEDBY`,
t1.`FINISHEDDATE`,
t1.`FROMBUG`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'task' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LEFT`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
(SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) AS `MODULENAME1`,
(case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`,
( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`,
t1.`NAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t51.`NAME` AS `PARENTNAME`,
t11.`PATH`,
t1.`PRI`,
t21.`PRODUCT`,
t41.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t31.`NAME` AS `PROJECTNAME`,
t1.`REALSTARTED`,
t1.`STATUS`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TYPE`,
	( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`,
CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') as progressrate,
(case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) as `STATUS1`,
(case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when t1.`status` = 'done' then 30 when t1.`status` = 'closed' then 40 when t1.`status` = 'cancel' then 50 else 60 end)
as statusorder,
t1.`PLAN`,
t1.`TASKSPECIES`,
t1.`ORDERNUM` AS `ORDERNUM`,
t61.`TITLE` AS `PLANNAME`
FROM `zt_task` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.STORY = t21.ID 
LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID 
LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID 
LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID
LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
t1.`parent` = #{srf.n_parent_eq} 

```
### 子任务(ChildTask)<div id="Task_ChildTask"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, 0 AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MAILTO`, '' AS `MAILTOPK`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (case when t1.module = '0' then '/' else (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.CONSUMED AS `MYCONSUMED`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') AS `PROGRESSRATE`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`, case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when  t1.`status` = 'done' then 30 when  t1.`status` = 'closed' then 40 when  t1.`status` = 'cancel' then 50  else 60 end AS `STATUSORDER`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, t1.`TYPE`, DATE_FORMAT(t1.lastediteddate,'%Y-%m-%d') AS `UPDATEDATE` ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
( 1<>1 ) 

```
### 子任务（树）(ChildTaskTree)<div id="Task_ChildTaskTree"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t61.`REALNAME`  AS `ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`))  AS `DURATION`, 0 AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, CONCAT_WS('','子任务-',t1.`name`,'-',t1.assignedTo,'-',t1.id) as `NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, t1.`TYPE`, '40' AS `TASKTYPE`, t1.`status` as `STATUS1`, (case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when t1.`status` = 'done' then 30 when t1.`status` = 'closed' then 40 when t1.`status` = 'cancel' then 50 else 60 end) as statusorder  ,
t1.`PLAN`,
t71.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID  LEFT JOIN zt_user t61 on t1.ASSIGNEDTO =  t61.ACCOUNT
LEFT JOIN `zt_productplan` t71 ON t1.`PLAN` = t71.`ID`
```
### 用户年度完成任务(CurFinishTask)<div id="Task_CurFinishTask"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, 0 AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MAILTO`, '' AS `MAILTOPK`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (case when t1.module = '0' then '/' else (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.CONSUMED AS `MYCONSUMED`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') AS `PROGRESSRATE`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`, case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when  t1.`status` = 'done' then 30 when  t1.`status` = 'closed' then 40 when  t1.`status` = 'cancel' then 50  else 60 end AS `STATUSORDER`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, t1.`TYPE`, DATE_FORMAT(t1.lastediteddate,'%Y-%m-%d') AS `UPDATEDATE` ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
( t1.`FINISHEDBY` =  #{srf.sessioncontext.srfloginname}  AND  left(t1.finishedDate,4) = #{srf.webcontext.curyear} ) 

```
### 指定人员任务(CurPersonTasks)<div id="Task_CurPersonTasks"></div>
```sql

SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, t1.`DELETED`, ( To_Days( t1.`DEADLINE` ) - To_Days( t1.`ESTSTARTED` ) ) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, ( SELECT ( CASE WHEN COUNT( t.IBZ_FAVORITESID ) > 0 THEN 1 ELSE 0 END ) AS ISFAVORITES FROM T_IBZ_FAVORITES t WHERE t.TYPE = 'task' AND t.ACCOUNT = #{srf.datacontext.account} AND t.OBJECTID = t1.id ) AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, ( SELECT CASE WHEN tt.type = 'task' THEN GROUP_CONCAT( tt.NAME SEPARATOR '>' ) ELSE CONCAT_WS( '', t2.`name`, '>', GROUP_CONCAT( tt.NAME SEPARATOR '>' ) ) END AS `name` FROM zt_module tt LEFT JOIN zt_product t2 ON tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root LIMIT 0, 1 ) AS `MODULENAME1`, ( SELECT CASE WHEN count( t.`id` ) > 0 THEN 1 ELSE 0 END FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, t1.`TYPE`, ( CASE WHEN ( SELECT CASE WHEN count( t.`id` ) > 0 THEN 1 ELSE 0 END FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) = 1 THEN '10' WHEN t1.parent = - 1 THEN '20' WHEN t1.parent = 0 THEN '30' ELSE '40' END ) AS `TASKTYPE`, ( CASE WHEN t1.storyVersion < t21.version AND t21.`status` <> 'changed' THEN 'storychange' ELSE t1.`status` END ) AS `STATUS1`, ( CASE WHEN t1.`status` = 'wait' THEN 10 WHEN t1.`status` = 'doing' THEN 20 WHEN t1.`status` = 'done' THEN 30 WHEN t1.`status` = 'closed' THEN 40 WHEN t1.`status` = 'cancel' THEN 50 ELSE 60 END ) AS statusorder, t1.`PLAN`, t1.`TASKSPECIES`, t1.`ORDERNUM` AS `ORDERNUM`, t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1 LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID LEFT JOIN zt_story t21 ON t1.STORY = t21.ID LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
(t1.assignedTo = #{srf.datacontext.account} OR FIND_IN_SET( #{srf.datacontext.account}, t1.finishedList ) OR t1.finishedBy =#{srf.datacontext.account} ) 
(t1.parent <= 0 OR ( t1.parent > 0 AND NOT EXISTS (SELECT 1 FROM zt_task t WHERE t.deleted = '0' 
AND t.id = t1.parent AND (t.assignedTo = #{srf.datacontext.account} OR t.finishedBy =
 #{srf.datacontext.account} OR FIND_IN_SET( #{srf.datacontext.account}, t.finishedList ) ) ) ) ) 

```
### DEFAULT(DEFAULT)<div id="Task_Default"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
case when exists (select 1 from zt_team t where t.type = 'task' and t1.id = t.root) then (select GROUP_CONCAT(t.account) from zt_team t where t.type = 'task' and t1.id = t.root) else t1.assignedTo end as `ASSIGNEDTO`,
t1.`CANCELEDBY`,
t1.`CANCELEDDATE`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`CONSUMED`,
t1.`DEADLINE`,
t1.`DELETED`,
case when ( To_Days( t1.`DEADLINE` ) - To_Days( t1.`ESTSTARTED` ) ) is null then null else ( To_Days( t1.`DEADLINE` ) - To_Days( t1.`ESTSTARTED` ) ) + 1 end  AS `DURATION`,
t1.`ESTIMATE`,
t1.`ESTSTARTED`,
t1.`FINISHEDBY`,
t1.`FINISHEDDATE`,
t1.`FROMBUG`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'task' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LEFT`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
(SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) AS `MODULENAME1`,
(case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`,
( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`,
t1.`NAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t51.`NAME` AS `PARENTNAME`,
t11.`PATH`,
t1.`PRI`,
t21.`PRODUCT`,
t41.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t31.`NAME` AS `PROJECTNAME`,
t1.`REALSTARTED`,
t1.`STATUS`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TYPE`,
	( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`,
CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') as progressrate,
(case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) as `STATUS1`,
(case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when t1.`status` = 'done' then 30 when t1.`status` = 'closed' then 40 when t1.`status` = 'cancel' then 50 else 60 end)
as statusorder,
t1.`PLAN`,
t1.`TASKSPECIES`,
t61.`TITLE` AS `PLANNAME`
FROM `zt_task` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.STORY = t21.ID 
LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID 
LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID 
LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID
LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 

```
### DefaultRow(DefaultRow)<div id="Task_DefaultRow"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, (select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'task' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1 ) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, t1.`TYPE`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, t1.`desc`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) as `STATUS1`, (case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when t1.`status` = 'done' then 30 when t1.`status` = 'closed' then 40 when t1.`status` = 'cancel' then 50 else 60 end) as statusorder ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 

```
### ES批量的导入(ESBulk)<div id="Task_ESBulk"></div>
```sql
SELECT t1.id, t1.`name`, t1.desc, t1.deleted FROM zt_task t1
```
### 我代理的任务(MyAgentTask)<div id="Task_MyAgentTask"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, case when t61.AGENTUSER = #{srf.sessioncontext.srfloginname} then t61.AGENTUSER else t1.assignedTo end as `ASSIGNEDTO`,  t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, (select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'task' and t.ACCOUNT = #{srf.sessioncontext.srfloginname}) AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) AS `MODULENAME1`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, t1.`TYPE`, 	( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') as progressrate, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) as `STATUS1`, (case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when t1.`status` = 'done' then 30 when t1.`status` = 'closed' then 40 when t1.`status` = 'cancel' then 50 else 60 end) as statusorder  ,
t1.`PLAN`,
t71.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN t_ibz_agent t61 ON t1.assignedTo = t61.CREATEMANNAME and DATE_FORMAT(now(), '%Y-%m-%d') >= t61.AGENTBEGIN and DATE_FORMAT(now(), '%Y-%m-%d') <= t61.AGENTEND LEFT JOIN `zt_productplan` t71 ON t1.`PLAN` = t71.`ID`
WHERE t1.deleted = '0' 

```
### 我相关的任务(MyAllTask)<div id="Task_MyAllTask"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, (select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'task' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, t1.`TYPE`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) as `STATUS1`, (case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when t1.`status` = 'done' then 30 when t1.`status` = 'closed' then 40 when t1.`status` = 'cancel' then 50 else 60 end) as statusorder ,
t1.`PLAN`,
t1.`TASKSPECIES`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
(t1.assignedTo = #{srf.sessioncontext.srfloginname} or t1.openedBy =#{srf.sessioncontext.srfloginname}  or FIND_IN_SET(#{srf.sessioncontext.srfloginname}, t1.finishedList) or t1.closedBy = #{srf.sessioncontext.srfloginname} or t1.finishedBy = #{srf.sessioncontext.srfloginname} or t1.canceledBy = #{srf.sessioncontext.srfloginname}) 
(t1.parent <= 0 or (t1.parent > 0 and not EXISTS (select 1 from zt_task t where  t.deleted = '0' and t.id = t1.parent and (t.assignedTo =#{srf.sessioncontext.srfloginname} or t.openedBy =#{srf.sessioncontext.srfloginname} or t.closedBy = #{srf.sessioncontext.srfloginname} or t.finishedBy = #{srf.sessioncontext.srfloginname} or t.canceledBy =#{srf.sessioncontext.srfloginname} or FIND_IN_SET(#{srf.sessioncontext.srfloginname}, t.finishedList) )))) 

```
### 我相关的任务（聚微）(MyAllTaskEE)<div id="Task_MyAllTaskEE"></div>
```sql
SELECT
	t1.`ASSIGNEDDATE`,
	t1.`ASSIGNEDTO`,
	t1.`CANCELEDBY`,
	t1.`CANCELEDDATE`,
	t1.`CLOSEDBY`,
	t1.`CLOSEDDATE`,
	t1.`CLOSEDREASON`,
	t1.`COLOR`,
	t1.`CONSUMED`,
	t1.`DEADLINE`,
	t1.`DELETED`,
	( To_Days( t1.`DEADLINE` ) - To_Days( t1.`ESTSTARTED` ) ) AS `DURATION`,
	t1.`ESTIMATE`,
	t1.`ESTSTARTED`,
	t1.`FINISHEDBY`,
	t1.`FINISHEDDATE`,
	t1.`FROMBUG`,
	t1.`ID`,
	(
SELECT
	( CASE WHEN COUNT( t.IBZ_FAVORITESID ) > 0 THEN 1 ELSE 0 END ) AS ISFAVORITES 
FROM
	T_IBZ_FAVORITES t 
WHERE
	t.TYPE = 'task' 
	AND t.ACCOUNT = #{srf.sessioncontext.srfloginname} 
	AND t.OBJECTID = t1.id ) AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, ( SELECT CASE WHEN tt.type = 'task' THEN GROUP_CONCAT( tt.NAME SEPARATOR '>' ) ELSE CONCAT_WS( '', t2.`name`, '>', GROUP_CONCAT( tt.NAME SEPARATOR '>' ) ) END AS `name` FROM zt_module tt LEFT JOIN zt_product t2 ON tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root LIMIT 0, 1 ) AS `MODULENAME1`, ( SELECT CASE WHEN count( t.`id` ) > 0 THEN 1 ELSE 0 END FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, t1.`TYPE`, ( CASE WHEN ( SELECT CASE WHEN count( t.`id` ) > 0 THEN 1 ELSE 0 END FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) = 1 THEN '10' WHEN t1.parent = - 1 THEN '20' WHEN t1.parent = 0 THEN '30' ELSE '40' END ) AS `TASKTYPE`, ( CASE WHEN t1.storyVersion < t21.version AND t21.`status` <> 'changed' THEN 'storychange' ELSE t1.`status` END ) AS `STATUS1`, ( CASE WHEN t1.`status` = 'wait' THEN 10 WHEN t1.`status` = 'doing' THEN 20 WHEN t1.`status` = 'done' THEN 30 WHEN t1.`status` = 'closed' THEN 40 WHEN t1.`status` = 'cancel' THEN 50 ELSE 60 END ) AS statusorder, t1.`PLAN`, t1.`TASKSPECIES`, t1.`ORDERNUM` AS `ORDERNUM`, CASE WHEN t1.`status` = 'wait' THEN CONCAT_WS( '', 60,case when t1.`PRI` = 1 then 4 when t1.pri = 2 then 3 when t1.pri = 3 then 2 when t1.pri = 4 then 1 end , LPAD( t1.id, 8, 0 ) ) WHEN t1.`status` = 'doing' THEN CONCAT_WS( '', 50,case when t1.`PRI` = 1 then 4 when t1.pri = 2 then 3 when t1.pri = 3 then 2 when t1.pri = 4 then 1 end , LPAD( t1.id, 8, 0 ) ) WHEN t1.`status` = 'done' THEN CONCAT_WS( '', 40,case when t1.`PRI` = 1 then 4 when t1.pri = 2 then 3 when t1.pri = 3 then 2 when t1.pri = 4 then 1 end , LPAD( t1.id, 8, 0 ) ) WHEN t1.`status` = 'closed' THEN CONCAT_WS( '', 30,case when t1.`PRI` = 1 then 4 when t1.pri = 2 then 3 when t1.pri = 3 then 2 when t1.pri = 4 then 1 end , LPAD( t1.id, 8, 0 ) ) WHEN t1.`status` = 'cancel' THEN CONCAT_WS( '', 20,case when t1.`PRI` = 1 then 4 when t1.pri = 2 then 3 when t1.pri = 3 then 2 when t1.pri = 4 then 1 end , LPAD( t1.id, 8, 0 ) ) ELSE CONCAT_WS( '', 10,case when t1.`PRI` = 1 then 4 when t1.pri = 2 then 3 when t1.pri = 3 then 2 when t1.pri = 4 then 1 end , LPAD( t1.id, 8, 0 ) ) END AS `TASKSN`, t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1 LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID LEFT JOIN zt_story t21 ON t1.STORY = t21.ID LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
(t1.assignedTo = #{srf.sessioncontext.srfloginname} or t1.openedBy =#{srf.sessioncontext.srfloginname}  or FIND_IN_SET(#{srf.sessioncontext.srfloginname}, t1.finishedList) or t1.closedBy = #{srf.sessioncontext.srfloginname} or t1.finishedBy = #{srf.sessioncontext.srfloginname} or t1.canceledBy = #{srf.sessioncontext.srfloginname}) 
(#{srf.datacontext.formitem} is null or #{srf.datacontext.formitem} = 'ALL' or (#{srf.datacontext.formitem} = 'CY' and (t1.finishedBy = #{srf.sessioncontext.srfloginname} or FIND_IN_SET(#{srf.sessioncontext.srfloginname}, t1.finishedList))) or (#{srf.datacontext.formitem} = 'HH' and t1.closedBy = #{srf.sessioncontext.srfloginname})) 

```
### 我完成的任务（汇报）(MyCompleteTask)<div id="Task_MyCompleteTask"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, (SELECT sum(tt.consumed) from zt_taskestimate tt where tt.task = t1.id and tt.date = DATE_FORMAT(#{srf.datacontext.date}, '%Y-%m-%d' ) and tt.account =#{srf.sessioncontext.srfloginname}) as 'consumed', t1.`DEADLINE`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, 0 AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MAILTO`, '' AS `MAILTOPK`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (case when t1.module = '0' then '/' else (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.CONSUMED AS `MYCONSUMED`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') AS `PROGRESSRATE`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`, case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when  t1.`status` = 'done' then 30 when  t1.`status` = 'closed' then 40 when  t1.`status` = 'cancel' then 50  else 60 end AS `STATUSORDER`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, t1.`TYPE`, DATE_FORMAT(t1.lastediteddate,'%Y-%m-%d') AS `UPDATEDATE` ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
EXISTS(select 1 from zt_taskestimate t where t.task = t1.id and t.account = #{srf.sessioncontext.srfloginname} and DATE_FORMAT( t.date, '%Y-%m-%d' ) = DATE_FORMAT( CASE WHEN #{srf.datacontext.date} IS NULL THEN now() ELSE #{srf.datacontext.date} END, '%Y-%m-%d' )) 
t31.deleted='0' 

```
### 我完成的任务（移动端日报）(MyCompleteTaskMobDaily)<div id="Task_MyCompleteTaskMobDaily"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, 0 AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MAILTO`, '' AS `MAILTOPK`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (case when t1.module = '0' then '/' else (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.CONSUMED AS `MYCONSUMED`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') AS `PROGRESSRATE`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`, case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when  t1.`status` = 'done' then 30 when  t1.`status` = 'closed' then 40 when  t1.`status` = 'cancel' then 50  else 60 end AS `STATUSORDER`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, t1.`TYPE`, DATE_FORMAT(t1.lastediteddate,'%Y-%m-%d') AS `UPDATEDATE` ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE FIND_IN_SET(t1.id,(select TODAYTASK from t_ibz_daily where IBZ_dailyID = #{srf.datacontext.srfparentkey})) 
t1.deleted='0' 

```
### 我完成的任务（移动端月报）(MyCompleteTaskMobMonthly)<div id="Task_MyCompleteTaskMobMonthly"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, 0 AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MAILTO`, '' AS `MAILTOPK`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (case when t1.module = '0' then '/' else (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.CONSUMED AS `MYCONSUMED`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') AS `PROGRESSRATE`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`, case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when  t1.`status` = 'done' then 30 when  t1.`status` = 'closed' then 40 when  t1.`status` = 'cancel' then 50  else 60 end AS `STATUSORDER`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, t1.`TYPE`, DATE_FORMAT(t1.lastediteddate,'%Y-%m-%d') AS `UPDATEDATE` ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
FIND_IN_SET(t1.id,(select thismonthtask from t_ibz_monthly where IBZ_MONTHLYID = #{srf.datacontext.srfparentkey} )) 

```
### 我完成的任务（月报展示）(MyCompleteTaskMonthlyZS)<div id="Task_MyCompleteTaskMonthlyZS"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, (select IFNULL(SUM(consumed),0) from zt_taskestimate where task = t1.id and account = #{srf.sessioncontext.srfloginname} and DATE_FORMAT(date,'%Y-%m') = DATE_FORMAT(#{srf.datacontext.date},'%Y-%m')) as `CONSUMED`, t1.`DEADLINE`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, 0 AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MAILTO`, '' AS `MAILTOPK`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (case when t1.module = '0' then '/' else (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.CONSUMED AS `MYCONSUMED`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') AS `PROGRESSRATE`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`, case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when  t1.`status` = 'done' then 30 when  t1.`status` = 'closed' then 40 when  t1.`status` = 'cancel' then 50  else 60 end AS `STATUSORDER`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, t1.`TYPE`, DATE_FORMAT(t1.lastediteddate,'%Y-%m-%d') AS `UPDATEDATE` ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID  LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.deleted = '0' 
(FIND_IN_SET (t1.`ID`,#{srf.webcontext.todaytask}) ) 

```
### 我完成的任务（汇报）(MyCompleteTaskZS)<div id="Task_MyCompleteTaskZS"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, (SELECT sum(tt.consumed) from zt_taskestimate tt where tt.task = t1.id and tt.date = DATE_FORMAT(#{srf.datacontext.date}, '%Y-%m-%d' ) and tt.account =#{srf.sessioncontext.srfloginname}) as 'consumed', t1.`DEADLINE`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, 0 AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MAILTO`, '' AS `MAILTOPK`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (case when t1.module = '0' then '/' else (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.CONSUMED AS `MYCONSUMED`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') AS `PROGRESSRATE`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`, case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when  t1.`status` = 'done' then 30 when  t1.`status` = 'closed' then 40 when  t1.`status` = 'cancel' then 50  else 60 end AS `STATUSORDER`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, t1.`TYPE`, DATE_FORMAT(t1.lastediteddate,'%Y-%m-%d') AS `UPDATEDATE` ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
(FIND_IN_SET (t1.`ID`,#{srf.webcontext.todaytask}) ) 

```
### 我的收藏(MyFavorites)<div id="Task_MyFavorites"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, (select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'task' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, t1.`TYPE`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') as progressrate, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) as `STATUS1`, (case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when t1.`status` = 'done' then 30 when t1.`status` = 'closed' then 40 when t1.`status` = 'cancel' then 50 else 60 end) as statusorder ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
t1.id in (select t.OBJECTID from T_IBZ_FAVORITES t where t.type = 'task' and t.account = #{srf.sessioncontext.srfloginname}) 

```
### 我计划参与的任务（移动端月报）(MyPlansTaskMobMonthly)<div id="Task_MyPlansTaskMobMonthly"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, 0 AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MAILTO`, '' AS `MAILTOPK`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (case when t1.module = '0' then '/' else (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.CONSUMED AS `MYCONSUMED`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') AS `PROGRESSRATE`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`, case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when  t1.`status` = 'done' then 30 when  t1.`status` = 'closed' then 40 when  t1.`status` = 'cancel' then 50  else 60 end AS `STATUSORDER`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, t1.`TYPE`, DATE_FORMAT(t1.lastediteddate,'%Y-%m-%d') AS `UPDATEDATE` ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
FIND_IN_SET(t1.id,(select nextmonthplanstask from t_ibz_monthly where IBZ_MONTHLYID = #{srf.datacontext.srfparentkey} )) 

```
### 我的任务(MyProjectTask)<div id="Task_MyProjectTask"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) as DELAY, (select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'task'  and t.OBJECTID = t1.id) AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root LIMIT 0,1) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, t1.`TYPE`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') as progressrate, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) as `STATUS1`, (case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when t1.`status` = 'done' then 30 when t1.`status` = 'closed' then 40 when t1.`status` = 'cancel' then 50 else 60 end) as statusorder ,
t1.`PLAN`,
t1.`TASKSPECIES`,
t1.`ORDERNUM` AS `ORDERNUM`,
t61.`TITLE` AS `PLANNAME`  FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
(( t1.`PARENT` <= 0 ) and t1.project = #{srf.datacontext.project } and t1.assignedTo =  #{srf.sessioncontext.srfloginname} ) 

```
### 我计划参与的任务（汇报）(MyTomorrowPlanTask)<div id="Task_MyTomorrowPlanTask"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, 0 AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MAILTO`, '' AS `MAILTOPK`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (case when t1.module = '0' then '/' else (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.CONSUMED AS `MYCONSUMED`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') AS `PROGRESSRATE`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`, case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when  t1.`status` = 'done' then 30 when  t1.`status` = 'closed' then 40 when  t1.`status` = 'cancel' then 50  else 60 end AS `STATUSORDER`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, t1.`TYPE`, DATE_FORMAT(t1.lastediteddate,'%Y-%m-%d') AS `UPDATEDATE` ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
( t1.`STATUS` IN ('doing','wait')  AND  t1.`ASSIGNEDTO` =  #{srf.sessioncontext.srfloginname} ) 

```
### 我计划参与的任务（汇报）(MyTomorrowPlanTaskMobDaily)<div id="Task_MyTomorrowPlanTaskMobDaily"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, 0 AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MAILTO`, '' AS `MAILTOPK`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (case when t1.module = '0' then '/' else (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.CONSUMED AS `MYCONSUMED`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') AS `PROGRESSRATE`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`, case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when  t1.`status` = 'done' then 30 when  t1.`status` = 'closed' then 40 when  t1.`status` = 'cancel' then 50  else 60 end AS `STATUSORDER`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, t1.`TYPE`, DATE_FORMAT(t1.lastediteddate,'%Y-%m-%d') AS `UPDATEDATE` ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID
LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
( FIND_IN_SET(t1.id,(select tomorrowplanstask from t_ibz_daily where IBZ_dailyID = #{srf.datacontext.srfparentkey})) ) 

```
### 移动端下周计划参与(汇报)(NextWeekCompleteTaskMobZS)<div id="Task_NextWeekCompleteTaskMobZS"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, 0 AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MAILTO`, '' AS `MAILTOPK`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (case when t1.module = '0' then '/' else (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.CONSUMED AS `MYCONSUMED`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') AS `PROGRESSRATE`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`, case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when  t1.`status` = 'done' then 30 when  t1.`status` = 'closed' then 40 when  t1.`status` = 'cancel' then 50  else 60 end AS `STATUSORDER`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, t1.`TYPE`, DATE_FORMAT(t1.lastediteddate,'%Y-%m-%d') AS `UPDATEDATE` ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
find_in_set(t1.id, (select nextweektask from t_ibz_weekly where ibz_weeklyid = #{srf.datacontext.srfparentkey} )) 

```
### 本周完成的任务(汇报)(NextWeekCompleteTaskZS)<div id="Task_NextWeekCompleteTaskZS"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, 0 AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MAILTO`, '' AS `MAILTOPK`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (case when t1.module = '0' then '/' else (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.CONSUMED AS `MYCONSUMED`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') AS `PROGRESSRATE`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`, case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when  t1.`status` = 'done' then 30 when  t1.`status` = 'closed' then 40 when  t1.`status` = 'cancel' then 50  else 60 end AS `STATUSORDER`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, t1.`TYPE`, DATE_FORMAT(t1.lastediteddate,'%Y-%m-%d') AS `UPDATEDATE` ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
( FIND_IN_SET(t1.id, case when  #{srf.webcontext.nextweektask} is null then 0 else #{srf.webcontext.nextweektask} end  ) ) 

```
### 下周计划完成任务(汇报)(NextWeekPlanCompleteTask)<div id="Task_NextWeekPlanCompleteTask"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, 0 AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MAILTO`, '' AS `MAILTOPK`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (case when t1.module = '0' then '/' else (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.CONSUMED AS `MYCONSUMED`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') AS `PROGRESSRATE`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`, case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when  t1.`status` = 'done' then 30 when  t1.`status` = 'closed' then 40 when  t1.`status` = 'cancel' then 50  else 60 end AS `STATUSORDER`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, t1.`TYPE`, DATE_FORMAT(t1.lastediteddate,'%Y-%m-%d') AS `UPDATEDATE` ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
( t1.`STATUS` IN ('doing','wait')  AND  t1.`ASSIGNEDTO` =  #{srf.sessioncontext.srfloginname} ) 

```
### 人员任务(PersonnalTasks)<div id="Task_PersonnalTasks"></div>
```sql
SELECT
	t1.`ASSIGNEDDATE`,
	t1.`ASSIGNEDTO`,
	t1.`CANCELEDBY`,
	t1.`CANCELEDDATE`,
	t1.`CLOSEDBY`,
	t1.`CLOSEDDATE`,
	t1.`CLOSEDREASON`,
	t1.`COLOR`,
	t1.`CONSUMED`,
	t1.`DEADLINE`,
	t1.`DELETED`,
	( To_Days( t1.`DEADLINE` ) - To_Days( t1.`ESTSTARTED` ) ) AS `DURATION`,
	t1.`ESTIMATE`,
	t1.`ESTSTARTED`,
	t1.`FINISHEDBY`,
	t1.`FINISHEDDATE`,
	t1.`FROMBUG`,
	t1.`ID`,
	(
SELECT
	( CASE WHEN COUNT( t.IBZ_FAVORITESID ) > 0 THEN 1 ELSE 0 END ) AS ISFAVORITES 
FROM
	T_IBZ_FAVORITES t 
WHERE
	t.TYPE = 'task' 
	AND t.ACCOUNT = #{srf.sessioncontext.srfloginname} 
	AND t.OBJECTID = t1.id 
	) AS `ISFAVORITES`,
	( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
	t1.`LASTEDITEDBY`,
	t1.`LASTEDITEDDATE`,
	t1.`LEFT`,
	t1.`MODULE`,
	t11.`NAME` AS `MODULENAME`,
	(
SELECT
CASE
	
WHEN
	tt.type = 'task' THEN
	GROUP_CONCAT( tt.NAME SEPARATOR '>' ) ELSE CONCAT_WS( '', t2.`name`, '>', GROUP_CONCAT( tt.NAME SEPARATOR '>' ) ) 
	END AS `name` 
FROM
	zt_module tt
	LEFT JOIN zt_product t2 ON tt.root = t2.id 
WHERE
	FIND_IN_SET( tt.id, t11.path ) 
GROUP BY
	tt.root 
	LIMIT 0,
	1 
	) AS `MODULENAME1`,
	(
	SELECT
	CASE
			
		WHEN
			count( t.`id` ) > 0 THEN
				1 ELSE 0 
			END 
			FROM
				`zt_team` t 
			WHERE
				t.`type` = 'task' 
				AND t.`root` = t1.`id` 
			) AS `MULTIPLE`,
			t1.`NAME`,
			t1.`OPENEDBY`,
			t1.`OPENEDDATE`,
			t1.`PARENT`,
			t51.`NAME` AS `PARENTNAME`,
			t11.`PATH`,
			t1.`PRI`,
			t21.`PRODUCT`,
			t41.`NAME` AS `PRODUCTNAME`,
			t1.`PROJECT`,
			t31.`NAME` AS `PROJECTNAME`,
			t1.`REALSTARTED`,
			t1.`STATUS`,
			t1.`STORY`,
			t21.`TITLE` AS `STORYNAME`,
			t1.`STORYVERSION`,
			t1.`SUBSTATUS`,
			t1.`TYPE`,
			(
			CASE
					
					WHEN (
					SELECT
					CASE
							
						WHEN
							count( t.`id` ) > 0 THEN
								1 ELSE 0 
							END 
							FROM
								`zt_team` t 
							WHERE
								t.`type` = 'task' 
								AND t.`root` = t1.`id` 
								) = 1 THEN
								'10' 
								WHEN t1.parent = - 1 THEN
								'20' 
								WHEN t1.parent = 0 THEN
								'30' ELSE '40' 
							END 
							) AS `TASKTYPE`,
							( CASE WHEN t1.storyVersion < t21.version AND t21.`status` <> 'changed' THEN 'storychange' ELSE t1.`status` END ) AS `STATUS1`,
							(
							CASE
									
									WHEN t1.`status` = 'wait' THEN
									10 
									WHEN t1.`status` = 'doing' THEN
									20 
									WHEN t1.`status` = 'done' THEN
									30 
									WHEN t1.`status` = 'closed' THEN
									40 
									WHEN t1.`status` = 'cancel' THEN
									50 ELSE 60 
								END 
								) AS statusorder,
								t1.`PLAN`,
								t1.`TASKSPECIES`,
								t1.`ORDERNUM` AS `ORDERNUM`,
								t61.`TITLE` AS `PLANNAME` 
							FROM
								`zt_task` t1
								LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID
								LEFT JOIN zt_story t21 ON t1.STORY = t21.ID
								LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID
								LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID
								LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID
								LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
(t1.assignedTo = #{srf.sessioncontext.srfloginname} OR FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t1.finishedList ) OR t1.finishedBy = #{srf.sessioncontext.srfloginname} ) 
(t1.parent <= 0 OR ( t1.parent > 0 AND NOT EXISTS (SELECT 1 FROM zt_task t WHERE t.deleted = '0' 
AND t.id = t1.parent AND (t.assignedTo = #{srf.sessioncontext.srfloginname} OR t.finishedBy =
 #{srf.sessioncontext.srfloginname} OR FIND_IN_SET( #{srf.sessioncontext.srfloginname}, t.finishedList ) ) ) ) ) 

```
### 相关任务（计划）(PlanTask)<div id="Task_PlanTask"></div>
```sql
SELECT
t1.`ASSIGNEDDATE`,
case when exists (select 1 from zt_team t where t.type = 'task' and t1.id = t.root) then (select GROUP_CONCAT(t.account) from zt_team t where t.type = 'task' and t1.id = t.root) else t1.assignedTo end as `ASSIGNEDTO`,
t1.`CANCELEDBY`,
t1.`CANCELEDDATE`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`CONSUMED`,
t1.`DEADLINE`,
t1.`DELETED`,
(To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`,
t1.`ESTIMATE`,
t1.`ESTSTARTED`,
t1.`FINISHEDBY`,
t1.`FINISHEDDATE`,
t1.`FROMBUG`,
t1.`ID`,
(select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'task' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LEFT`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
(SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) AS `MODULENAME1`,
(case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`,
( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`,
t1.`NAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`PARENT`,
t51.`NAME` AS `PARENTNAME`,
t11.`PATH`,
t1.`PRI`,
t21.`PRODUCT`,
t41.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t31.`NAME` AS `PROJECTNAME`,
t1.`REALSTARTED`,
t1.`STATUS`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TYPE`,
	( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`,
CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') as progressrate,
(case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) as `STATUS1`,
(case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when t1.`status` = 'done' then 30 when t1.`status` = 'closed' then 40 when t1.`status` = 'cancel' then 50 else 60 end)
as statusorder,
t1.`PLAN`,
t1.`TASKSPECIES`,
t1.`ORDERNUM` AS `ORDERNUM`,
t61.`TITLE` AS `PLANNAME`
FROM `zt_task` t1 
LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID 
LEFT JOIN zt_story t21 ON t1.STORY = t21.ID 
LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID 
LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID 
LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID
LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
t1.plan=#{srf.webcontext.plan} 

```
### 项目任务（项目立项）(ProjectAppTask)<div id="Task_ProjectAppTask"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) as DELAY, (select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'task' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root LIMIT 0,1) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, t1.`TYPE`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') as progressrate, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) as `STATUS1`, (case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when t1.`status` = 'done' then 30 when t1.`status` = 'closed' then 40 when t1.`status` = 'cancel' then 50 else 60 end) as statusorder ,
t1.`PLAN`,
t1.`TASKSPECIES`,
t1.`ORDERNUM` AS `ORDERNUM`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.deleted='0' 
t1.project=#{srf.webcontext.project} 

```
### 项目成员任务(ProjectMemberTask)<div id="Task_ProjectMemberTask"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) as DELAY, (select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'task'  and t.OBJECTID = t1.id) AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root LIMIT 0,1) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, t1.`TYPE`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') as progressrate, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) as `STATUS1`, (case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when t1.`status` = 'done' then 30 when t1.`status` = 'closed' then 40 when t1.`status` = 'cancel' then 50 else 60 end) as statusorder ,
t1.`PLAN`,
t1.`TASKSPECIES`,
t1.`ORDERNUM` AS `ORDERNUM`,
t61.`TITLE` AS `PLANNAME`  FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
(( t1.`PARENT` <= 0 ) and t1.project = #{srf.datacontext.project } and (t1.assignedTo = #{srf.datacontext.account} OR t1.finishedBy = #{srf.datacontext.account})) 

```
### 项目任务(ProjectTask)<div id="Task_ProjectTask"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) as DELAY, (select (case when COUNT(t.IBZ_FAVORITESID) > 0 then 1 else 0 end ) as ISFAVORITES from T_IBZ_FAVORITES t where t.TYPE = 'task' and t.ACCOUNT = #{srf.sessioncontext.srfloginname} and t.OBJECTID = t1.id) AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root LIMIT 0,1) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, t1.`TYPE`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') as progressrate, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) as `STATUS1`, (case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when t1.`status` = 'done' then 30 when t1.`status` = 'closed' then 40 when t1.`status` = 'cancel' then 50 else 60 end) as statusorder ,
t1.`PLAN`,
t1.`TASKSPECIES`,
t61.`TITLE` AS `PLANNAME`  FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
(( t1.`PARENT` <= 0 ) and t1.`project` = #{srf.n_project_eq}) 

```
### 项目任务（聚微）(ProjectTaskEE)<div id="Task_ProjectTaskEE"></div>
```sql
SELECT
	t1.`ASSIGNEDDATE`,
	t1.`ASSIGNEDTO`,
	t1.`CANCELEDBY`,
	t1.`CANCELEDDATE`,
	t1.`CLOSEDBY`,
	t1.`CLOSEDDATE`,
	t1.`CLOSEDREASON`,
	t1.`COLOR`,
	t1.`CONSUMED`,
	t1.`DEADLINE`,
	t1.`DELETED`,
	( To_Days( t1.`DEADLINE` ) - To_Days( t1.`ESTSTARTED` ) ) AS `DURATION`,
	t1.`ESTIMATE`,
	t1.`ESTSTARTED`,
	t1.`FINISHEDBY`,
	t1.`FINISHEDDATE`,
	t1.`FROMBUG`,
	t1.`ID`,
	(
CASE
	
	WHEN t1.deadline IS NULL 
	OR t1.deadline = '0000-00-00' 
	OR t1.deadline = '1970-01-01' THEN
	'' 
WHEN t1.`status` IN ( 'wait', 'doing' ) 
	AND t1.deadline < DATE_FORMAT( now( ), '%y-%m-%d' ) THEN
		CONCAT_WS( '', '延期', TIMESTAMPDIFF( DAY, t1.deadline, now( ) ), '天' ) ELSE '' 
	END 
	) AS DELAY,
	(
	SELECT
		( CASE WHEN COUNT( t.IBZ_FAVORITESID ) > 0 THEN 1 ELSE 0 END ) AS ISFAVORITES 
	FROM
		T_IBZ_FAVORITES t 
	WHERE
		t.TYPE = 'task' 
		AND t.ACCOUNT = #{srf.sessioncontext.srfloginname}
		
		AND t.OBJECTID = t1.id 
	) AS `ISFAVORITES`,
	( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
	t1.`LASTEDITEDBY`,
	t1.`LASTEDITEDDATE`,
	t1.`LEFT`,
	t1.`MODULE`,
	t11.`NAME` AS `MODULENAME`,
	(
	SELECT
	CASE
			
		WHEN
			tt.type = 'task' THEN
				GROUP_CONCAT( tt.NAME SEPARATOR '>' ) ELSE CONCAT_WS( '', t2.`name`, '>', GROUP_CONCAT( tt.NAME SEPARATOR '>' ) ) 
			END AS `name` 
		FROM
			zt_module tt
			LEFT JOIN zt_product t2 ON tt.root = t2.id 
		WHERE
			FIND_IN_SET( tt.id, t11.path ) 
		GROUP BY
			tt.root 
			LIMIT 0,
			1 
		) AS `MODULENAME1`,
		(
		SELECT
		CASE
				
			WHEN
				count( t.`id` ) > 0 THEN
					1 ELSE 0 
				END 
				FROM
					`zt_team` t 
				WHERE
					t.`type` = 'task' 
					AND t.`root` = t1.`id` 
				) AS `MULTIPLE`,
				t1.`NAME`,
				t1.`OPENEDBY`,
				t1.`OPENEDDATE`,
				t1.`PARENT`,
				t51.`NAME` AS `PARENTNAME`,
				t11.`PATH`,
				t1.`PRI`,
				t21.`PRODUCT`,
				t41.`NAME` AS `PRODUCTNAME`,
				t1.`PROJECT`,
				t31.`NAME` AS `PROJECTNAME`,
				t1.`REALSTARTED`,
				t1.`STATUS`,
				t1.`STORY`,
				t21.`TITLE` AS `STORYNAME`,
				t1.`STORYVERSION`,
				t1.`SUBSTATUS`,
				t1.`TYPE`,
				(CASE WHEN ( SELECT CASE WHEN count( t.`id` ) > 0 THEN 1 ELSE 0 END FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id`) = 1 THEN '10' WHEN ( SELECT CASE WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_task` t WHERE t.parent = t1.`id` and t.deleted = '0' ) = 1 THEN '20' WHEN t1.parent = 0 THEN '30' ELSE '40' END ) AS `TASKTYPE`,
								CONCAT_WS(
									'',
								CASE
										
										WHEN t1.consumed = 0 
										OR t1.consumed IS NULL THEN
											'0' 
											WHEN t1.`left` = 0 
											OR t1.`left` IS NULL THEN
												'100' ELSE ROUND( ( ROUND( t1.`consumed` / ( t1.`left` + t1.consumed ), 2 ) ) * 100 ) 
											END,
											'%' 
										) AS progressrate,
										( CASE WHEN t1.storyVersion < t21.version AND t21.`status` <> 'changed' THEN 'storychange' ELSE t1.`status` END ) AS `STATUS1`,
										(
										CASE
        WHEN t1.`status` = 'wait' THEN
        CONCAT_WS( '', 60,case when t1.`PRI` = 1 then 4
        when t1.pri = 2 then 3 
        when t1.pri = 3 then 2 
        when t1.pri = 4 then 1 end , LPAD( t1.id, 12, 0 ) ) 
        WHEN t1.`status` = 'doing' THEN
        CONCAT_WS( '', 50,case when t1.`PRI` = 1 then 4
        when t1.pri = 2 then 3
        when t1.pri = 3 then 2 
        when t1.pri = 4 then 1 end , LPAD( t1.id, 12, 0 ) ) 
        WHEN t1.`status` = 'done' THEN
        CONCAT_WS( '', 40,case when t1.`PRI` = 1 then 4 
        when t1.pri = 2 then 3 
        when t1.pri = 3 then 2 
        when t1.pri = 4 then 1 end , LPAD( t1.id, 12, 0 ) ) 
        WHEN t1.`status` = 'closed' THEN
        CONCAT_WS( '', 30,case when t1.`PRI` = 1 then 4 
        when t1.pri = 2 then 3 
        when t1.pri = 3 then 2 
        when t1.pri = 4 then 1 end , LPAD( t1.id, 12, 0 ) ) 
        WHEN t1.`status` = 'cancel' THEN
        CONCAT_WS( '', 20,case when t1.`PRI` = 1 then 4 
        when t1.pri = 2 then 3  
        when t1.pri = 3 then 2 
        when t1.pri = 4 then 1 end , LPAD( t1.id, 12, 0 ) ) 
        ELSE CONCAT_WS( '', 10,case when t1.`PRI` = 1 then 4 
        when t1.pri = 2 then 3  
        when t1.pri = 3 then 2 
        when t1.pri = 4 then 1 end , LPAD( t1.id, 12, 0 ) ) 
        END 
											) AS statusorder,
											t1.`PLAN`,
											t1.`TASKSPECIES`,
											t1.`ORDERNUM` AS `ORDERNUM`,
											t61.`TITLE` AS `PLANNAME` 
										FROM
											`zt_task` t1
											LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID
											LEFT JOIN zt_story t21 ON t1.STORY = t21.ID
											LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID
											LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID
										LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID
LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
( t1.`project` = #{srf.n_project_eq}) 
(#{srf.datacontext.formitem} is null or #{srf.datacontext.formitem} = 'ALL' or (#{srf.datacontext.formitem} = 'ZQ' and t1.TASKSPECIES='cycle') or (#{srf.datacontext.formitem} = 'FZQ' and t1.TASKSPECIES<>'cycle')) 

```
### 根任务(RootTask)<div id="Task_RootTask"></div>
```sql
SELECT
	t1.`ASSIGNEDDATE`,
	t61.`REALNAME` AS `ASSIGNEDTO`,
	t1.`CANCELEDBY`,
	t1.`CANCELEDDATE`,
	t1.`CLOSEDBY`,
	t1.`CLOSEDDATE`,
	t1.`CLOSEDREASON`,
	t1.`COLOR`,
	t1.`CONSUMED`,
	t1.`DEADLINE`,
	t1.`DELETED`,
	case when ( To_Days( t1.`DEADLINE` ) - To_Days( t1.`ESTSTARTED` ) ) is null then null else ( To_Days( t1.`DEADLINE` ) - To_Days( t1.`ESTSTARTED` ) ) + 1 end  AS `DURATION`,
	0 AS `ISFAVORITES`,
	( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
	t1.`ESTIMATE`,
	t1.`ESTSTARTED`,
	t1.`FINISHEDBY`,
	t1.`FINISHEDDATE`,
	t1.`FROMBUG`,
	t1.`ID`,
	t1.`LASTEDITEDBY`,
	t1.`LASTEDITEDDATE`,
	t1.`LEFT`,
	t1.`MODULE`,
	t11.`NAME` AS `MODULENAME`,
	CONCAT_WS( '', '任务-', t1.`name`, '-', t1.assignedTo, '-', t1.id ) AS `NAME`,
	t1.`OPENEDBY`,
	t1.`OPENEDDATE`,
	t1.`PARENT`,
	t51.`NAME` AS `PARENTNAME`,
	t1.`PRI`,
	t21.`PRODUCT`,
	t41.`NAME` AS `PRODUCTNAME`,
	t1.`PROJECT`,
	t31.`NAME` AS `PROJECTNAME`,
	t1.`REALSTARTED`,
	t1.`STATUS`,
	t1.`STORY`,
	t21.`TITLE` AS `STORYNAME`,
	t1.`STORYVERSION`,
	t1.`SUBSTATUS`,
	t1.`TYPE`,
	'40' AS `TASKTYPE`,
	t1.`status` AS `STATUS1`,
	(
CASE
	
	WHEN t1.`status` = 'wait' THEN
	10 
	WHEN t1.`status` = 'doing' THEN
	20 
	WHEN t1.`status` = 'done' THEN
	30 
	WHEN t1.`status` = 'closed' THEN
	40 
	WHEN t1.`status` = 'cancel' THEN
	50 ELSE 60 
END 
	) AS statusorder,
t1.`PLAN`,
t71.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID  LEFT JOIN zt_user t61 on t1.ASSIGNEDTO =  t61.ACCOUNT 
LEFT JOIN `zt_productplan` t71 ON t1.`PLAN` = t71.`ID`
WHERE t1.DELETED = '0' 
t1.`PARENT` in (0,-1) 

```
### 关联计划（当前项目未关联）(TaskLinkPlan)<div id="Task_TaskLinkPlan"></div>
```sql
SELECT
t1.`ASSIGN`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`CANCELEDBY`,
t1.`CANCELEDDATE`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`CONFIG_BEFOREDAYS`,
t1.`CONFIG_BEGIN`,
t1.`CONFIG_DAY`,
t1.`CONFIG_END`,
t1.`CONFIG_MONTH`,
t1.`CONFIG_TYPE`,
t1.`CONFIG_WEEK`,
t1.`CONSUMED`,
t1.`CYCLE`,
t1.`DEADLINE`,
(case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`,
t1.`DELETED`,
(To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`,
t1.`ESTIMATE`,
t1.`ESTSTARTED`,
t1.`FINISHEDBY`,
t1.`FINISHEDDATE`,
t1.`FROMBUG`,
t1.`ID`,
t1.`IDVALUE`,
t1.`INPUTCOST`,
0 AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LEFT`,
t1.`MAILTO`,
'' AS `MAILTOPK`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`,
( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`,
t1.CONSUMED AS `MYCONSUMED`,
t1.`NAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`ORDERNUM`,
t1.`PARENT`,
t61.`NAME` AS `PARENTNAME`,
t11.`PATH`,
t1.`PLAN`,
t31.`TITLE` AS `PLANNAME`,
t1.`PRI`,
t21.`PRODUCT`,
t51.`NAME` AS `PRODUCTNAME`,
CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') AS `PROGRESSRATE`,
t1.`PROJECT`,
t41.`NAME` AS `PROJECTNAME`,
t1.`REALSTARTED`,
t1.`STATUS`,
(case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`,
CASE 	WHEN t1.`status` = 'wait' THEN 	CONCAT_WS( '', 60,t1.`PRI`, LPAD( t1.id, 8, 0 ) )  	WHEN t1.`status` = 'doing' THEN 	CONCAT_WS( '', 50,t1.`PRI`, LPAD( t1.id, 8, 0 ) )  	WHEN t1.`status` = 'done' THEN 	CONCAT_WS( '', 40,t1.`PRI`, LPAD( t1.id, 8, 0 ) )  	WHEN t1.`status` = 'closed' THEN 	CONCAT_WS( '', 30,t1.`PRI`, LPAD( t1.id, 8, 0 ) )  	WHEN t1.`status` = 'cancel' THEN 	CONCAT_WS( '', 20,t1.`PRI`, LPAD( t1.id, 8, 0 ) ) ELSE CONCAT_WS( '', 10,t1.pri, LPAD( t1.id, 8, 0 ) )  	END AS `STATUSORDER`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASKSPECIES`,
(CASE WHEN ( SELECT CASE WHEN count( t.`id` ) > 0 THEN 1 ELSE 0 END FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id`) = 1 THEN '10' WHEN ( SELECT CASE WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_task` t WHERE t.parent = t1.`id` and t.deleted = '0' ) = 1 THEN '20' WHEN t1.parent = 0 THEN '30' ELSE '40' END ) AS `TASKTYPE`,
t1.`TYPE`,
DATE_FORMAT(t1.lastediteddate,'%Y-%m-%d') AS `UPDATEDATE`
FROM `zt_task` t1 
LEFT JOIN `zt_module` t11 ON t1.`MODULE` = t11.`ID` 
LEFT JOIN `zt_story` t21 ON t1.`STORY` = t21.`ID` 
LEFT JOIN `zt_productplan` t31 ON t1.`PLAN` = t31.`ID` 
LEFT JOIN `zt_project` t41 ON t1.`PROJECT` = t41.`ID` 
LEFT JOIN `zt_product` t51 ON t21.`PRODUCT` = t51.`ID` 
LEFT JOIN `zt_task` t61 ON t1.`PARENT` = t61.`ID` 

WHERE t1.DELETED = '0' 
( t1.project in (select t.project from zt_projectproduct t left join zt_productplan tt on tt.product = t.product where tt.id = ${srfwebcontext('productplan','{"defname":"PLAN","dename":"ZT_TASK"}')})  AND  ( t1.`PLAN` <> ${srfwebcontext('productplan','{"defname":"PLAN","dename":"ZT_TASK"}')}  OR  t1.`PLAN` IS NULL ) ) 

```
### 我本月完成的任务（下拉列表框）(ThisMonthCompleteTaskChoice)<div id="Task_ThisMonthCompleteTaskChoice"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, 0 AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MAILTO`, '' AS `MAILTOPK`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (case when t1.module = '0' then '/' else (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.CONSUMED AS `MYCONSUMED`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') AS `PROGRESSRATE`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`, case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when  t1.`status` = 'done' then 30 when  t1.`status` = 'closed' then 40 when  t1.`status` = 'cancel' then 50  else 60 end AS `STATUSORDER`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, t1.`TYPE`, DATE_FORMAT(t1.lastediteddate,'%Y-%m-%d') AS `UPDATEDATE` ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID 
LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.deleted = '0' 
t31.deleted = '0' 
EXISTS(select 1 from zt_taskestimate t where t.task = t1.id and t.account = #{srf.sessioncontext.srfloginname} and DATE_FORMAT( t.date, '%Y-%m' ) = DATE_FORMAT( CASE WHEN #{srf.datacontext.date} IS NULL THEN now() ELSE #{srf.datacontext.date} END, '%Y-%m' )) 

```
### 本周完成的任务(汇报)(ThisWeekCompleteTask)<div id="Task_ThisWeekCompleteTask"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, 0 AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MAILTO`, '' AS `MAILTOPK`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (case when t1.module = '0' then '/' else (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.CONSUMED AS `MYCONSUMED`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') AS `PROGRESSRATE`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`, case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when  t1.`status` = 'done' then 30 when  t1.`status` = 'closed' then 40 when  t1.`status` = 'cancel' then 50  else 60 end AS `STATUSORDER`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, t1.`TYPE`, DATE_FORMAT(t1.lastediteddate,'%Y-%m-%d') AS `UPDATEDATE` ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
( ( ( t1.`STATUS` IN ('doing','wait')  AND  t1.`ASSIGNEDTO` =  #{srf.sessioncontext.srfloginname} )  OR  ( t1.`FINISHEDBY` =  #{srf.sessioncontext.srfloginname} ) ) ) 

```
### 本周已完成任务(下拉框选择)(ThisWeekCompleteTaskChoice)<div id="Task_ThisWeekCompleteTaskChoice"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, 0 AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MAILTO`, '' AS `MAILTOPK`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (case when t1.module = '0' then '/' else (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.CONSUMED AS `MYCONSUMED`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') AS `PROGRESSRATE`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`, case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when  t1.`status` = 'done' then 30 when  t1.`status` = 'closed' then 40 when  t1.`status` = 'cancel' then 50  else 60 end AS `STATUSORDER`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, t1.`TYPE`, DATE_FORMAT(t1.lastediteddate,'%Y-%m-%d') AS `UPDATEDATE` ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
t31.deleted = '0' 
EXISTS(SELECT 1 from zt_taskestimate t2 where t1.id = t2.task and t2.account = #{srf.sessioncontext.srfloginname} and YEARWEEK(t2.date,1) = YEARWEEK(case when #{srf.datacontext.date} is null then now() else #{srf.datacontext.date}  end,1) ) 

```
### 移动端本周已完成任务(汇报)(ThisWeekCompleteTaskMobZS)<div id="Task_ThisWeekCompleteTaskMobZS"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, t1.`CONSUMED`, t1.`DEADLINE`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, 0 AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MAILTO`, '' AS `MAILTOPK`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (case when t1.module = '0' then '/' else (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.CONSUMED AS `MYCONSUMED`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') AS `PROGRESSRATE`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`, case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when  t1.`status` = 'done' then 30 when  t1.`status` = 'closed' then 40 when  t1.`status` = 'cancel' then 50  else 60 end AS `STATUSORDER`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, t1.`TYPE`, DATE_FORMAT(t1.lastediteddate,'%Y-%m-%d') AS `UPDATEDATE` ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
find_in_set(t1.id, (select thisweektask from t_ibz_weekly where ibz_weeklyid = #{srf.datacontext.srfparentkey} )) 

```
### 本周完成的任务(汇报)(ThisWeekCompleteTaskZS)<div id="Task_ThisWeekCompleteTaskZS"></div>
```sql
SELECT t1.`ASSIGNEDDATE`, t1.`ASSIGNEDTO`, t1.`CANCELEDBY`, t1.`CANCELEDDATE`, t1.`CLOSEDBY`, t1.`CLOSEDDATE`, t1.`CLOSEDREASON`, t1.`COLOR`, (select IFNULL(SUM(t.consumed),0) from zt_taskestimate t where t.task = t1.id and t.account = #{srf.sessioncontext.srfloginname} and YEARWEEK(t.date,1) = YEARWEEK(#{srf.datacontext.date},1) ) as `consumed`, t1.`DEADLINE`, (case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`, t1.`DELETED`, (To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`, t1.`ESTIMATE`, t1.`ESTSTARTED`, t1.`FINISHEDBY`, t1.`FINISHEDDATE`, t1.`FROMBUG`, t1.`ID`, 0 AS `ISFAVORITES`, ( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`, t1.`LASTEDITEDBY`, t1.`LASTEDITEDDATE`, t1.`LEFT`, t1.`MAILTO`, '' AS `MAILTOPK`, t1.`MODULE`, t11.`NAME` AS `MODULENAME`, (case when t1.module = '0' then '/' else (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`, ( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`, t1.CONSUMED AS `MYCONSUMED`, t1.`NAME`, t1.`OPENEDBY`, t1.`OPENEDDATE`, t1.`PARENT`, t51.`NAME` AS `PARENTNAME`, t11.`PATH`, t1.`PRI`, t21.`PRODUCT`, t41.`NAME` AS `PRODUCTNAME`, CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') AS `PROGRESSRATE`, t1.`PROJECT`, t31.`NAME` AS `PROJECTNAME`, t1.`REALSTARTED`, t1.`STATUS`, (case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`, case when t1.`status` = 'wait' then 10 when t1.`status` = 'doing' then 20 when  t1.`status` = 'done' then 30 when  t1.`status` = 'closed' then 40 when  t1.`status` = 'cancel' then 50  else 60 end AS `STATUSORDER`, t1.`STORY`, t21.`TITLE` AS `STORYNAME`, t1.`STORYVERSION`, t1.`SUBSTATUS`, ( CASE WHEN ( SELECT CASE	 WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_team` t  WHERE t.`type` = 'task'  AND t.`root` = t1.`id`  ) = 1 THEN '10'  WHEN t1.parent = - 1 THEN'20'   WHEN t1.parent = 0 THEN '30' ELSE '40' END) AS `TASKTYPE`, t1.`TYPE`, DATE_FORMAT(t1.lastediteddate,'%Y-%m-%d') AS `UPDATEDATE` ,
t1.`PLAN`,
t61.`TITLE` AS `PLANNAME` FROM `zt_task` t1  LEFT JOIN zt_module t11 ON t1.MODULE = t11.ID  LEFT JOIN zt_story t21 ON t1.STORY = t21.ID  LEFT JOIN zt_project t31 ON t1.PROJECT = t31.ID  LEFT JOIN zt_product t41 ON t21.PRODUCT = t41.ID  LEFT JOIN zt_task t51 ON t1.PARENT = t51.ID LEFT JOIN `zt_productplan` t61 ON t1.`PLAN` = t61.`ID`
WHERE t1.DELETED = '0' 
( FIND_IN_SET(t1.id, case when  #{srf.webcontext.thisweektask} is null then 0 else #{srf.webcontext.thisweektask} end  ) ) 

```
### todo列表查询(TodoListTask)<div id="Task_TodoListTask"></div>
```sql
SELECT 	t1.*  FROM 	( SELECT 	t1.id, 	t1.`name`, 	t1.pri, 	t1.assignedTo, 	t1.finishedBy, 	t1.finishedDate, 	t1.estStarted, CASE 	 	WHEN t1.`status` IN ( 'done', 'closed' ) THEN 	'1' ELSE '0'  	END AS isfinished, 	( SELECT count( 1 ) FROM zt_action WHERE objectType = 'task' AND action = 'commented' AND objectid = t1.id ) AS ReplyCount, CASE 		 		WHEN t1.`desc` IS NULL  		OR t1.`desc` = '' THEN 			'0' ELSE '1'  		END AS hasDetail, 		t1.project, 		t1.`TYPE`, 		t1.`status`, 		t1.deleted, 		t1.openeddate, 		t1.closeddate, 		t1.assigneddate,                 t1.consumed, 		t1.deadline, 		t11.`name` AS projectname, 		t1.canceleddate, 		t1.lastediteddate,                 DATE_FORMAT(t1.lastediteddate,'%Y-%m-%d') as updatedate, 		t1.lasteditedby, 		t1.openedby, 		t1.closedby, 		t1.canceledby  	FROM 		`zt_task` t1 	LEFT JOIN zt_project t11 ON t1.project = t11.id  ) t1
WHERE t1.deleted = '0' 

```
### 任务类型分组(TypeGroup)<div id="Task_TypeGroup"></div>
```sql
SELECT
	(
SELECT
	MIN( ESTSTARTED ) 
FROM
	`zt_task` 
WHERE
	DELETED = '0' 
	AND TYPE = t1.`TYPE` 
	AND PROJECT = t1.`PROJECT` 
	AND ESTSTARTED <> '0000-00-00' 
	AND estStarted <> '0002-11-30' 
	AND estStarted <> '1970-01-01' 
	) AS `ESTSTARTED`,
	(
SELECT
	MAX( DEADLINE ) 
FROM
	`zt_task` 
WHERE
	DELETED = '0' 
	AND TYPE = t1.`TYPE` 
	AND PROJECT = t1.`PROJECT` 
	AND DEADLINE <> '0000-00-00' 
	AND estStarted <> '0002-11-30' 
	AND estStarted <> '1970-01-01' 
	) AS `DEADLINE`,
	(
SELECT
	DATEDIFF( MAX( DEADLINE ), MIN( ESTSTARTED ) ) + 1 
FROM
	`zt_task` 
WHERE
	DELETED = '0' 
	AND TYPE = t1.`TYPE` 
	AND PROJECT = t1.`PROJECT` 
	AND ESTSTARTED <> '0000-00-00' 
	AND DEADLINE <> '0000-00-00' 
	AND estStarted <> '0002-11-30' 
	AND estStarted <> '1970-01-01' 
	) AS `DURATION`,
	t1.`TYPE`,
	t1.`PLAN`,
	t1.project
	FROM
		`zt_task` t1
	WHERE
	t1.DELETED = '0' and  t1.project = #{srf.datacontext.project}
```
### 任务类型分组（计划）(TypeGroupPlan)<div id="Task_TypeGroupPlan"></div>
```sql
SELECT
	(
SELECT
	MIN( ESTSTARTED ) 
FROM
	`zt_task` 
WHERE
	DELETED = '0' 
	AND TYPE = t1.`TYPE` 
	AND plan = t1.plan
	AND ESTSTARTED <> '0000-00-00' 
	AND estStarted <> '0002-11-30' 
	AND estStarted <> '1970-01-01' 
	) AS `ESTSTARTED`,
	(
SELECT
	MAX( DEADLINE ) 
FROM
	`zt_task` 
WHERE
	DELETED = '0' 
	AND TYPE = t1.`TYPE` 
	AND plan = t1.plan
	AND DEADLINE <> '0000-00-00' 
	AND estStarted <> '0002-11-30' 
	AND estStarted <> '1970-01-01' 
	) AS `DEADLINE`,
	(
SELECT
	DATEDIFF( MAX( DEADLINE ), MIN( ESTSTARTED ) ) + 1 
FROM
	`zt_task` 
WHERE
	DELETED = '0' 
	AND TYPE = t1.`TYPE` 
	AND plan = t1.plan
	AND ESTSTARTED <> '0000-00-00' 
	AND DEADLINE <> '0000-00-00' 
	AND estStarted <> '0002-11-30' 
	AND estStarted <> '1970-01-01' 
	) AS `DURATION`,
	t1.`TYPE`,
	t1.`PLAN`,
	t1.project
	FROM
		`zt_task` t1
	WHERE
	t1.DELETED = '0' and t1.plan =  #{srf.datacontext.srfparentkey}
```
### 默认（全部数据）(VIEW)<div id="Task_View"></div>
```sql
SELECT
t1.`ASSIGN`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`CANCELEDBY`,
t1.`CANCELEDDATE`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CLOSEDREASON`,
t1.`COLOR`,
t1.`CONFIG_BEFOREDAYS`,
t1.`CONFIG_BEGIN`,
t1.`CONFIG_DAY`,
t1.`CONFIG_END`,
t1.`CONFIG_MONTH`,
t1.`CONFIG_TYPE`,
t1.`CONFIG_WEEK`,
t1.`CONSUMED`,
t1.`CYCLE`,
t1.`DEADLINE`,
(case when t1.deadline is null or t1.deadline = '0000-00-00' or t1.deadline = '1970-01-01' then '' when t1.`status` in ('wait','doing') and t1.deadline <DATE_FORMAT(now(),'%y-%m-%d')  then CONCAT_WS('','延期',TIMESTAMPDIFF(DAY, t1.deadline, now()),'天') else '' end) AS `DELAY`,
t1.`DELETED`,
t1.`DESC`,
(To_Days(t1.`DEADLINE`)-To_Days(t1.`ESTSTARTED`)) AS `DURATION`,
t1.`ESTIMATE`,
t1.`ESTSTARTED`,
t1.`FINISHEDBY`,
t1.`FINISHEDDATE`,
t1.`FINISHEDLIST`,
t1.`FROMBUG`,
t1.`ID`,
t1.`IDVALUE`,
t1.`INPUTCOST`,
0 AS `ISFAVORITES`,
( CASE WHEN t1.parent > 0 THEN TRUE ELSE FALSE END ) AS `ISLEAF`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`LEFT`,
t1.`MAILTO`,
'' AS `MAILTOPK`,
t1.`MODULE`,
t11.`NAME` AS `MODULENAME`,
(case when t1.module = '0' then '/' else (SELECT case when tt.type = 'task' then GROUP_CONCAT( tt.NAME SEPARATOR '>' ) else CONCAT_WS('',t2.`name`,'>',GROUP_CONCAT( tt.NAME SEPARATOR '>' )) end as `name` FROM zt_module tt left join zt_product t2 on tt.root = t2.id WHERE FIND_IN_SET( tt.id, t11.path ) GROUP BY tt.root limit 0,1) end) AS `MODULENAME1`,
( SELECT case when count( t.`id` ) > 0 then 1 else 0 end FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id` ) AS `MULTIPLE`,
t1.CONSUMED AS `MYCONSUMED`,
t1.`NAME`,
t1.`OPENEDBY`,
t1.`OPENEDDATE`,
t1.`ORDERNUM`,
t1.`PARENT`,
t61.`NAME` AS `PARENTNAME`,
t11.`PATH`,
t1.`PLAN`,
t31.`TITLE` AS `PLANNAME`,
t1.`PRI`,
t21.`PRODUCT`,
t51.`NAME` AS `PRODUCTNAME`,
CONCAT_WS('',case when t1.consumed = 0 or t1.consumed is null then '0' when t1.`left` = 0 or t1.`left` is null then '100' else ROUND((ROUND(t1.`consumed`/(t1.`left` + t1.consumed),2)) * 100) end ,'%') AS `PROGRESSRATE`,
t1.`PROJECT`,
t41.`NAME` AS `PROJECTNAME`,
t1.`REALSTARTED`,
t1.`STATUS`,
(case when t1.storyVersion < t21.version and t21.`status` <> 'changed' then 'storychange'  else t1.`status` end ) AS `STATUS1`,
CASE 	WHEN t1.`status` = 'wait' THEN 	CONCAT_WS( '', 60,t1.`PRI`, LPAD( t1.id, 8, 0 ) )  	WHEN t1.`status` = 'doing' THEN 	CONCAT_WS( '', 50,t1.`PRI`, LPAD( t1.id, 8, 0 ) )  	WHEN t1.`status` = 'done' THEN 	CONCAT_WS( '', 40,t1.`PRI`, LPAD( t1.id, 8, 0 ) )  	WHEN t1.`status` = 'closed' THEN 	CONCAT_WS( '', 30,t1.`PRI`, LPAD( t1.id, 8, 0 ) )  	WHEN t1.`status` = 'cancel' THEN 	CONCAT_WS( '', 20,t1.`PRI`, LPAD( t1.id, 8, 0 ) ) ELSE CONCAT_WS( '', 10,t1.pri, LPAD( t1.id, 8, 0 ) )  	END AS `STATUSORDER`,
t1.`STORY`,
t21.`TITLE` AS `STORYNAME`,
t1.`STORYVERSION`,
t1.`SUBSTATUS`,
t1.`TASKSPECIES`,
(CASE WHEN ( SELECT CASE WHEN count( t.`id` ) > 0 THEN 1 ELSE 0 END FROM `zt_team` t WHERE t.`type` = 'task' AND t.`root` = t1.`id`) = 1 THEN '10' WHEN ( SELECT CASE WHEN count( t.`id` ) > 0 THEN 1 ELSE 0  END  FROM `zt_task` t WHERE t.parent = t1.`id` and t.deleted = '0' ) = 1 THEN '20' WHEN t1.parent = 0 THEN '30' ELSE '40' END ) AS `TASKTYPE`,
t1.`TYPE`,
DATE_FORMAT(t1.lastediteddate,'%Y-%m-%d') AS `UPDATEDATE`
FROM `zt_task` t1 
LEFT JOIN `zt_module` t11 ON t1.`MODULE` = t11.`ID` 
LEFT JOIN `zt_story` t21 ON t1.`STORY` = t21.`ID` 
LEFT JOIN `zt_productplan` t31 ON t1.`PLAN` = t31.`ID` 
LEFT JOIN `zt_project` t41 ON t1.`PROJECT` = t41.`ID` 
LEFT JOIN `zt_product` t51 ON t21.`PRODUCT` = t51.`ID` 
LEFT JOIN `zt_task` t61 ON t1.`PARENT` = t61.`ID` 

WHERE t1.DELETED = '0' 

```

# **任务预计**(ZT_TASKESTIMATE)

### 日志日(ActionDate)<div id="TaskEstimate_ActionDate"></div>
```sql
SELECT 
t1.`year`,t1.month,
CONCAT( t1.`YEAR`, RIGHT ( 100+ t1.`MONTH`, 2 ),RIGHT ( 100+ t1.`day`, 2 )) as `dayname`,
CONCAT( t1.`YEAR`,'-', RIGHT ( 100+ t1.`MONTH`, 2 ),'-',RIGHT ( 100+ t1.`day`, 2 )) AS day
from (
	
SELECT DISTINCT Year 
(t1.date) as `year`,
MONTH ( t1.date ) AS `MONTH` ,
DAY(t1.date) as `day` 
FROM
	zt_taskestimate t1 
WHERE
	t1.date <> '0000-00-00'  and t1.account = #{srf.datacontext.account}
	)t1
```
### 日志日（项目）(ActionDateByProject)<div id="TaskEstimate_ActionDateByProject"></div>
```sql
	SELECT
		t1.`year`,
		t1.MONTH,
		CONCAT( t1.`YEAR`,'-', RIGHT ( 100+ t1.`MONTH`, 2 ),'-', RIGHT ( 100+ t1.`day`, 2 ) ) AS `dayname`,
		CONCAT(
			t1.`YEAR`,
			'-',
			RIGHT ( 100+ t1.`MONTH`, 2 ),
			'-',
			RIGHT ( 100+ t1.`day`, 2 ) 
		) AS DAY 
	FROM
		(
		SELECT DISTINCT YEAR
			( t1.date ) AS `year`,
			MONTH ( t1.date ) AS `MONTH`,
			DAY ( t1.date ) AS `day` 
		FROM
			zt_taskestimate t1
			LEFT JOIN zt_task t2 ON t1.task = t2.ID 
		WHERE
			t1.date <> '0000-00-00' 
		AND t2.project = #{srf.datacontext.project} 
	) t1
```
### 日志月(ActionMonth)<div id="TaskEstimate_ActionMonth"></div>
```sql
SELECT
	t1.`YEAR`,
	concat( t1.`YEAR`, RIGHT ( 100+ t1.`MONTH`, 2 ) ) AS `MONTH`,
	t1.`MONTH` as monthorder,
	concat( t1.`MONTH`, '月' ) AS monthname 
FROM
	(
SELECT DISTINCT YEAR
	( t1.date ) AS `year`,
	MONTH ( t1.date ) AS `MONTH` 
FROM
	zt_taskestimate t1 
WHERE
	t1.date <> '0000-00-00' 
	) t1
```
### 日志年(ActionYear)<div id="TaskEstimate_ActionYear"></div>
```sql
select t1.`YEAR`,concat(t1.`year`, '年') as yearname from (select DISTINCT year( t1.date ) AS `year` from zt_taskestimate t1 where t1.date <> '0000-00-00' ) t1
```
### 所有人员(AllAccounts)<div id="TaskEstimate_AllAccounts"></div>
```sql
SELECT
	t1.`ACCOUNT`,
	t1.`DATE`,
	t1.`ID`,
	t11.`PROJECT`,
	t21.`NAME` AS `PROJECTNAME`,
	t1.`TASK`,
	t11.`NAME` AS `TASKNAME`,
	t21.pm 
FROM
	`zt_TASKESTIMATE` t1
	LEFT JOIN `zt_task` t11 ON t1.`TASK` = t11.`ID`
	LEFT JOIN `zt_PROJECT` t21 ON t11.`PROJECT` = t21.`ID` GROUP BY t1.account
```
### 所有项目(AllProjects)<div id="TaskEstimate_AllProjects"></div>
```sql
SELECT t11.account,t11.ID,t11.task,t21.`name` ,t31.ID AS project,t31.`name` as projectname FROM `zt_taskestimate` t11 LEFT JOIN zt_task t21 ON t11.task = t21.id LEFT JOIN zt_project t31 ON t21.project = t31.ID
WHERE t31.pm= #{srf.sessioncontext.srfloginname} 
 t31.ID IS NOT null GROUP BY t21.project 

```
### DEFAULT(DEFAULT)<div id="TaskEstimate_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CONSUMED`,
t1.`DATE`,
t1.`date` AS `DATES`,
t11.`DELETED`,
t1.`EVALUATIONCOST`,
t1.`EVALUATIONSTATUS`,
t1.`EVALUATIONTIME`,
t1.`FILES`,
t1.`ID`,
t1.`INPUTCOST`,
t1.`LEFT`,
t1.`MONTHNAME`,
t11.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`TASK`,
t11.`NAME` AS `TASKNAME`,
t11.`TASKSPECIES`,
t11.`TYPE`
FROM `zt_taskestimate` t1 
LEFT JOIN `zt_task` t11 ON t1.`TASK` = t11.`ID` 
LEFT JOIN `zt_project` t21 ON t11.`PROJECT` = t21.`ID` 

```
### DEFAULT1(DEFAULT1)<div id="TaskEstimate_Defaults"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CONSUMED`,
t1.`DATE`,
t1.`date` AS `DATES`,
t11.`DELETED`,
t1.`EVALUATIONCOST`,
t1.`EVALUATIONSTATUS`,
t1.`EVALUATIONTIME`,
t1.`FILES`,
t1.`ID`,
t1.`INPUTCOST`,
t1.`LEFT`,
t1.`MONTHNAME`,
t11.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`TASK`,
t11.`NAME` AS `TASKNAME`,
t11.`TASKSPECIES`,
t11.`TYPE`
FROM `zt_taskestimate` t1 
LEFT JOIN `zt_task` t11 ON t1.`TASK` = t11.`ID` 
LEFT JOIN `zt_project` t21 ON t11.`PROJECT` = t21.`ID` 

WHERE ( 1<>1 ) 

```
### 我管辖的所有人员(MyAccounts)<div id="TaskEstimate_MyAccounts"></div>
```sql
SELECT
	t1.`ACCOUNT`,
	t1.`DATE`,
	t1.`ID`,
	t11.`PROJECT`,
	t21.`NAME` AS `PROJECTNAME`,
	t1.`TASK`,
	t11.`NAME` AS `TASKNAME`,
	t21.pm 
FROM
	`zt_TASKESTIMATE` t1
	LEFT JOIN `zt_task` t11 ON t1.`TASK` = t11.`ID`
	LEFT JOIN `zt_PROJECT` t21 ON t11.`PROJECT` = t21.`ID`

WHERE t21.pm = #{srf.sessioncontext.srfloginname} GROUP BY t1.account 

```
### 日志日（我的）(MyActionDate)<div id="TaskEstimate_MyActionDate"></div>
```sql
	SELECT
		t1.`year`,
		t1.MONTH,
		CONCAT( t1.`YEAR`,'-', RIGHT ( 100+ t1.`MONTH`, 2 ),'-', RIGHT ( 100+ t1.`day`, 2 ) ) AS `dayname`,
		CONCAT(
			t1.`YEAR`,
			'-',
			RIGHT ( 100+ t1.`MONTH`, 2 ),
			'-',
			RIGHT ( 100+ t1.`day`, 2 ) 
		) AS DAY 
	FROM
		(
		SELECT DISTINCT YEAR
			( t1.date ) AS `year`,
			MONTH ( t1.date ) AS `MONTH`,
			DAY ( t1.date ) AS `day` 
		FROM
			zt_taskestimate t1
			LEFT JOIN zt_task t2 ON t1.task = t2.ID 
		WHERE
			t1.date <> '0000-00-00' 
		AND t2.project =  #{srf.sessioncontext.srfloginname} 
	) t1
```
### 人员日志(PersonalEstimate)<div id="TaskEstimate_PersonalEstimate"></div>
```sql
select t1.* from (SELECT t1.`ACCOUNT`, t1.`CONSUMED`, t1.`DATE`, t1.`date` AS `DATES`,REPLACE(t1.`date`,'-','') as dayname, t11.`DELETED`, t1.`EVALUATIONCOST`, t1.`EVALUATIONSTATUS`, t1.`EVALUATIONTIME`, t1.`FILES`, t1.`ID`, t1.`INPUTCOST`, t1.`LEFT`, t1.`MONTHNAME`, t11.`PROJECT`, t21.`NAME` AS `PROJECTNAME`, t1.`TASK`, t11.`NAME` AS `TASKNAME`, t11.`TASKSPECIES`, t11.`TYPE` FROM `zt_taskestimate` t1 LEFT JOIN `zt_task` t11 ON t1.`TASK` = t11.`ID` LEFT JOIN `zt_project` t21 ON t11.`PROJECT` = t21.`ID` )t1
WHERE (t1.`deleted` = '0' or t1.`task` = 0) 
t1.account = #{srf.datacontext.account} 
t1.dayname = #{srf.datacontext.dayname} 

```
### 日志月（项目）(ProjectActionMonth)<div id="TaskEstimate_ProjectActionMonth"></div>
```sql
SELECT
	t1.`YEAR`,
	concat( t1.`YEAR`, RIGHT ( 100+ t1.`MONTH`, 2 ) ) AS `MONTH`,
	t1.`MONTH` as monthorder,
	concat( t1.`MONTH`, '月' ) AS monthname ,
	t1.project
FROM
	(
SELECT DISTINCT YEAR
	( t1.date ) AS `year`,
	MONTH ( t1.date ) AS `MONTH` ,
	t.project
FROM
	zt_taskestimate t1 left join zt_task t on t.id = t1.task
WHERE
	t1.date <> '0000-00-00' 
	) t1
WHERE t1.project =#{srf.webcontext.project} 

```
### 日志年（项目）(ProjectActionYear)<div id="TaskEstimate_ProjectActionYear"></div>
```sql
SELECT
	t1.`YEAR`,
	concat( t1.`year`, '年' ) AS yearname ,
	t1.project
FROM
	( SELECT DISTINCT YEAR ( t1.date ) AS `year`,t.project FROM zt_taskestimate t1 left join zt_task t on t.id = t1.task WHERE t1.date <> '0000-00-00'  ) t1
WHERE t1.project =#{srf.webcontext.project} 

```
### 项目成员日志(ProjectMemberLog)<div id="TaskEstimate_ProjectMemberLog"></div>
```sql
 select t1.* from (SELECT t1.`ACCOUNT`, t1.`CONSUMED`, t1.`DATE`, t1.`date` AS `DATES`,t1.`date` as dayname, t11.`DELETED`, t1.`EVALUATIONCOST`, t1.`EVALUATIONSTATUS`, t1.`EVALUATIONTIME`, t1.`FILES`, t1.`ID`, t1.`INPUTCOST`, t1.`LEFT`, t1.`MONTHNAME`, t11.`PROJECT`, t21.`NAME` AS `PROJECTNAME`, t1.`TASK`, t11.`NAME` AS `TASKNAME`, t11.`TASKSPECIES`, t11.`TYPE` FROM `zt_taskestimate` t1 LEFT JOIN `zt_task` t11 ON t1.`TASK` = t11.`ID` LEFT JOIN `zt_project` t21 ON t11.`PROJECT` = t21.`ID` )t1
WHERE t1.`DELETED` = '0' 
t1.project = #{srf.datacontext.project} 
t1.dayname = #{srf.datacontext.dayname} 

```
### 项目日志(ProjectTaskEstimate)<div id="TaskEstimate_ProjectTaskEstimate"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CONSUMED`,
t1.`DATE`,
t1.`date` AS `DATES`,
t11.`DELETED`,
t1.`EVALUATIONCOST`,
t1.`EVALUATIONSTATUS`,
t1.`EVALUATIONTIME`,
t1.`FILES`,
t1.`ID`,
t1.`INPUTCOST`,
t1.`LEFT`,
t1.`MONTHNAME`,
t11.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`TASK`,
t11.`NAME` AS `TASKNAME`,
t11.`TASKSPECIES`,
t11.`TYPE`
FROM `zt_taskestimate` t1 
LEFT JOIN `zt_task` t11 ON t1.`TASK` = t11.`ID` 
LEFT JOIN `zt_project` t21 ON t11.`PROJECT` = t21.`ID` 

WHERE ( t11.`PROJECT` = ${srfwebcontext('project','{"defname":"PROJECT","dename":"ZT_TASKESTIMATE"}')}  AND  t11.`DELETED` = '0' ) 

```
### 人员参与的所有项目(SomeoneJoinAllPros)<div id="TaskEstimate_SomeoneJoinAllPros"></div>
```sql
SELECT
	t1.`ACCOUNT`,
	t1.`DATE`,
	t1.`ID`,
	t11.`PROJECT`,
	t21.`NAME` AS `PROJECTNAME`,
	t1.`TASK`,
	t11.`NAME` AS `TASKNAME`,
	t21.pm 
FROM
	`zt_TASKESTIMATE` t1
	LEFT JOIN `zt_task` t11 ON t1.`TASK` = t11.`ID`
	LEFT JOIN `zt_PROJECT` t21 ON t11.`PROJECT` = t21.`ID` 

WHERE  t11.project is not null 
 t1.account = #{srf.sessioncontext.account}  GROUP BY t11.project 

```
### 默认（全部数据）(VIEW)<div id="TaskEstimate_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CONSUMED`,
t1.`DATE`,
t1.`date` AS `DATES`,
t11.`DELETED`,
t1.`EVALUATIONCOST`,
t1.`EVALUATIONDESC`,
t1.`EVALUATIONSTATUS`,
t1.`EVALUATIONTIME`,
t1.`FILES`,
t1.`ID`,
t1.`INPUTCOST`,
t1.`LEFT`,
t1.`MONTHNAME`,
t11.`PROJECT`,
t21.`NAME` AS `PROJECTNAME`,
t1.`TASK`,
t11.`NAME` AS `TASKNAME`,
t11.`TASKSPECIES`,
t11.`TYPE`,
t1.`WORK`
FROM `zt_taskestimate` t1 
LEFT JOIN `zt_task` t11 ON t1.`TASK` = t11.`ID` 
LEFT JOIN `zt_project` t21 ON t11.`PROJECT` = t21.`ID` 

```

# **任务工时统计**(TASKESTIMATESTATS)

### 日志月(ActionMonth)<div id="TaskEstimateStats_ActionMonth"></div>
```sql
select t1.`YEAR`,concat(t1.`YEAR`, Right(100+ t1.`MONTH`,2)) as `MONTH`,concat(t1.`MONTH`,'月') as monthname from (select DISTINCT year( t1.date ) AS `year`,MONTH(t1.date) as `MONTH` from zt_taskestimate t1 where t1.date <> '0000-00-00' ) t1
```
### 日志年(ActionYear)<div id="TaskEstimateStats_ActionYear"></div>
```sql
select t1.`YEAR`,concat(t1.`year`, '年') as yearname from (select DISTINCT year( t1.date ) AS `year` from zt_taskestimate t1 where t1.date <> '0000-00-00' ) t1
```
### 数据查询(DEFAULT)<div id="TaskEstimateStats_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`DATE`,
t1.`EVALUATIONCOST`,
t1.`EVALUATIONSTATUS`,
t1.`EVALUATIONTIME`,
t1.`ID`,
t1.`INPUTCOST`,
t1.`MONTHNAME`
FROM `zt_taskestimate` t1 

```
### 任务工时统计(TaskEstimateStatsSum)<div id="TaskEstimateStats_TaskEstimateStatsSum"></div>
```sql
SELECT
	t1.* 
FROM
	(
SELECT
	t1.date,
	t2.NAME,
	t2.id,
	t1.account,
	count( 1 ) AS taskcnt,
	round( sum( t1.consumed ), 2 ) AS consumed 
FROM
	(
SELECT
	t1.NAME AS taskname,
	t1.project,
	t2.* 
FROM
	zt_task t1
	RIGHT JOIN ( SELECT t1.task, t1.date, t1.consumed, t1.account FROM zt_taskestimate t1  ) t2 ON t1.id = t2.task 
	) t1
	LEFT JOIN zt_project t2 ON t1.project = t2.id 
GROUP BY
	t2.id,
	t1.account 
ORDER BY
	t1.account 
	) t1
```
### 默认（全部数据）(VIEW)<div id="TaskEstimateStats_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`DATE`,
t1.`EVALUATIONCOST`,
t1.`EVALUATIONSTATUS`,
t1.`EVALUATIONTIME`,
t1.`ID`,
t1.`INPUTCOST`,
t1.`MONTHNAME`
FROM `zt_taskestimate` t1 

```

# **待办消息记录**(TASKMSGRECORD)

### 数据查询(DEFAULT)<div id="TaskMsgRecord_Default"></div>
```sql
SELECT
t1.`APPTASKID`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DATAID`,
t1.`ENABLE`,
t1.`MEMO`,
t1.`TASKMSGRECORDID`,
t1.`TASKMSGRECORDNAME`,
t1.`TASKTYPE`,
t1.`TASKUSERID`,
t1.`TITLE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_TASKMSGRECORD` t1 

WHERE t1.ENABLE = 1 

```
### 默认（全部数据）(VIEW)<div id="TaskMsgRecord_View"></div>
```sql
SELECT
t1.`APPTASKID`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`DATAID`,
t1.`ENABLE`,
t1.`MEMO`,
t1.`TASKMSGRECORDID`,
t1.`TASKMSGRECORDNAME`,
t1.`TASKTYPE`,
t1.`TASKUSERID`,
t1.`TITLE`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`
FROM `T_TASKMSGRECORD` t1 

WHERE t1.ENABLE = 1 

```

# **任务统计**(IBZ_TASKSTATS)

### 数据查询(DEFAULT)<div id="TaskStats_Default"></div>
```sql
SELECT
t1.`ID`,
t1.`NAME`,
0 AS `TASKEFFICIENT`,
0 AS `TOTALCONSUMED`,
0 AS `TOTALESTIMATE`,
0 AS `TOTALLEFT`
FROM `zt_task` t1 

```
### 任务完成汇总表(TaskFinishHuiZong)<div id="TaskStats_TaskFinishHuiZong"></div>
```sql
SELECT t4.account as finishedby,t1.project,t1.projectname,t1.id as taskid,t1.taskname,t1.pri as taskpri,t1.estStarted as taskeststarted,t1.realStarted as taskrealstart,t1.deadline as taskdeadline,t1.finishedDate as taskfinisheddate,null as delay,t1.estimate as taskestimate,t1.consumed as totalconsumed, t2.taskcnt,t2.projectconsumed,t3.userconsumed 
,t4.dept,DATE_FORMAT(t1.finishedDate,'%Y-%m-%d') as `begin`,DATE_FORMAT(t1.finishedDate,'%Y-%m-%d') as `end`
from (
select t1.finishedBy,t1.project,t2.`name` as projectname,t1.id,t1.`name` as taskname ,t1.pri,t1.estStarted,t1.realStarted,t1.deadline,t1.finishedDate,null as delay,t1.estimate,t1.consumed
from zt_task t1 LEFT JOIN zt_project t2 on t1.project = t2.id where (t1.`status` = 'done' or (t1.`status` = 'closed' and closedReason = 'done')) and t2.deleted ='0' and t1.deleted = '0'  and t2.id <> 0 and t1.finishedBy <> '' and t1.finishedBy is not null and t1.parent >= 0 and not EXISTS (select 1 from zt_team t where t.root = t1.id and t.type = 'task')

UNION
select t3.account as finishedBy,t1.project,t2.`name` as projectname,t1.id,t1.`name` as taskname ,t1.pri,t1.estStarted,t1.realStarted,t1.deadline,t1.finishedDate,null as delay,t1.estimate,t3.consumed
from zt_task t1 LEFT JOIN zt_project t2 on t1.project = t2.id inner JOIN zt_team t3 on t3.root = t1.id and t3.type = 'task'
where t2.deleted ='0' and t1.deleted = '0' and FIND_IN_SET(t3.account,t1.finishedList) and t2.id <> 0 and t1.parent >= 0 and t3.`left` = 0 

) t1 LEFT JOIN (

SELECT t1.finishedBy,t1.project,t1.projectname,t1.id,t1.taskname,COUNT(1) as taskcnt,SUM(t1.consumed) as projectconsumed from (
select t1.finishedBy,t1.project,t2.`name` as projectname,t1.id,t1.`name` as taskname ,t1.consumed
from zt_task t1 LEFT JOIN zt_project t2 on t1.project = t2.id where (t1.`status` = 'done' or (t1.`status` = 'closed' and closedReason = 'done')) and t2.deleted ='0' and t1.deleted = '0'  and t2.id <> 0 and t1.finishedBy <> '' and t1.finishedBy is not null and t1.parent >= 0 and not EXISTS (select 1 from zt_team t where t.root = t1.id and t.type = 'task')

UNION
select t3.account as finishedBy,t1.project,t2.`name` as projectname,t1.id,t1.`name` as taskname ,t3.consumed
from zt_task t1 LEFT JOIN zt_project t2 on t1.project = t2.id inner JOIN zt_team t3 on t3.root = t1.id and t3.type = 'task'
where t2.deleted ='0' and t1.deleted = '0' and FIND_IN_SET(t3.account,t1.finishedList) and t2.id <> 0 and t1.parent >= 0 and t3.`left` = 0 
) t1 GROUP BY t1.finishedBy,t1.project  

) t2 on t1.finishedBy = t2.finishedBy  and t1.project = t2.project   
LEFT JOIN ( 

SELECT t1.finishedBy,SUM(t1.consumed) as userconsumed from (
select t1.finishedBy,t1.project,t2.`name` as projectname,t1.id,t1.`name` as taskname ,t1.consumed
from zt_task t1 LEFT JOIN zt_project t2 on t1.project = t2.id where (t1.`status` = 'done' or (t1.`status` = 'closed' and closedReason = 'done')) and t2.deleted ='0' and t1.deleted = '0'  and t2.id <> 0 and t1.finishedBy <> '' and t1.finishedBy is not null and t1.parent >= 0 and not EXISTS (select 1 from zt_team t where t.root = t1.id and t.type = 'task')

UNION
select t3.account as finishedBy,t1.project,t2.`name` as projectname,t1.id,t1.`name` as taskname ,t3.consumed
from zt_task t1 LEFT JOIN zt_project t2 on t1.project = t2.id inner JOIN zt_team t3 on t3.root = t1.id and t3.type = 'task'
where t2.deleted ='0' and t1.deleted = '0' and FIND_IN_SET(t3.account,t1.finishedList) and t2.id <> 0 and t1.parent >= 0 and t3.`left` = 0 

) t1 GROUP BY t1.finishedBy

) t3 on t1.finishedBy = t3.finishedBy

left JOIN zt_user t4 on t1.finishedBy = t4.account
where (t4.dept = #{srf.datacontext.dept} or #{srf.datacontext.dept} is null) 
and (DATE_FORMAT(t1.finishedDate,'%Y-%m-%d') >= #{srf.datacontext.begin} or #{srf.datacontext.begin} is null)
and (DATE_FORMAT(t1.finishedDate,'%Y-%m-%d') >= #{srf.datacontext.end} or #{srf.datacontext.end} is null)
ORDER BY t4.account,t1.project

```
### 用户完成任务统计(UserFinishTaskSum)<div id="TaskStats_UserFinishTaskSum"></div>
```sql
SELECT
	t1.project,
	t2.`name` as PROJECTNAME,
	t1.finishedBy,
	sum( t1.estimate ) AS TOTALESTIMATE,
	sum( t1.consumed ) AS TOTALCONSUMED,
	CASE WHEN sum( t1.consumed ) <= sum( t1.estimate ) THEN
		'100.00%' ELSE CONCAT ( FORMAT(( sum( t1.estimate ) / sum( t1.consumed )) * 100, 2 ), '%' ) 
	END AS TASKEFFICIENT
FROM
	((
		SELECT
			t1.project,
			t1.finishedBy,
			t1.estimate,
			CASE WHEN t2.consumed IS NULL THEN
				t1.consumed ELSE t2.consumed 
			END AS consumed 
		FROM
			zt_task t1
			LEFT JOIN zt_taskestimate t2 ON t1.id = t2.task 
		WHERE
			t1.deleted = '0' 
			AND t1.parent <> - 1 
			AND t1.`status` IN ( 'done', 'closed' ) 
			AND t1.finishedBy <> '' 
			AND t1.id NOT IN ( SELECT t1.id FROM zt_team t1 WHERE t1.type = 'task' ) 
			) UNION
		(
		SELECT
			t1.project,
			t2.account AS finishedBy,
			t2.estimate,
			CASE WHEN t3.consumed IS NULL THEN
				t2.consumed ELSE t3.consumed 
			END AS consumed 
		FROM
			zt_task t1
			JOIN zt_team t2 ON t2.type = 'task' 
			AND t1.id = t2.root
			LEFT JOIN zt_taskestimate t3 ON t1.id = t3.task 
			AND t2.account = t3.account 
		WHERE
			t1.deleted = '0' 
			AND t1.parent <> - 1 
		AND t1.`status` IN ( 'done', 'closed' )) 
	) t1
	JOIN zt_project t2 ON t1.project = t2.id 
WHERE
	t2.deleted = '0' 
GROUP BY
	t1.project,
	t1.finishedBy
```
### 默认（全部数据）(VIEW)<div id="TaskStats_View"></div>
```sql
SELECT
t1.`ID`,
t1.`NAME`,
0 AS `TASKEFFICIENT`,
0 AS `TOTALCONSUMED`,
0 AS `TOTALESTIMATE`,
0 AS `TOTALLEFT`
FROM `zt_task` t1 

```

# **任务团队**(IBZ_TASKTEAM)

### DEFAULT(DEFAULT)<div id="TaskTeam_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CONSUMED`,
t1.`DAYS`,
t1.`ESTIMATE`,
t1.`HOURS`,
t1.`ID`,
t1.`JOIN`,
t1.`LEFT`,
t1.`LIMITED`,
t1.`ORDER`,
t1.`ROLE`,
t1.`ROOT`,
(t1.`DAYS` * t1.`HOURS`) AS `TOTAL`,
t1.`TYPE`,
t1.account AS `USERNAME`
FROM `zt_team` t1 

WHERE ( t1.`TYPE` = 'task' ) 

```
### 默认（全部数据）(VIEW)<div id="TaskTeam_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CONSUMED`,
t1.`DAYS`,
t1.`ESTIMATE`,
t1.`HOURS`,
t1.`ID`,
t1.`JOIN`,
t1.`LEFT`,
t1.`LIMITED`,
t1.`ORDER`,
t1.`ROLE`,
t1.`ROOT`,
(t1.`DAYS` * t1.`HOURS`) AS `TOTAL`,
t1.`TYPE`,
t1.account AS `USERNAME`
FROM `zt_team` t1 

```

# **群组**(ZT_TEAM)

### DEFAULT(DEFAULT)<div id="Team_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CONSUMED`,
t1.`DAYS`,
t1.`ESTIMATE`,
t1.`HOURS`,
t1.`ID`,
t1.`JOIN`,
t1.`LEFT`,
t1.`LIMITED`,
t1.`ORDER`,
t1.`ROLE`,
t1.`ROOT`,
t1.`TYPE`
FROM `zt_team` t1 

```
### 默认（全部数据）(VIEW)<div id="Team_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CONSUMED`,
t1.`DAYS`,
t1.`ESTIMATE`,
t1.`HOURS`,
t1.`ID`,
t1.`JOIN`,
t1.`LEFT`,
t1.`LIMITED`,
t1.`ORDER`,
t1.`ROLE`,
t1.`ROOT`,
t1.`TYPE`
FROM `zt_team` t1 

```

# **测试模块**(IBZ_TESTMODULE)

### BYPATH(BYPATH)<div id="TestModule_ByPath"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t1.`PATH`,
t1.`ROOT`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 

WHERE t1.DELETED = '0' 
( t1.`TYPE` = 'story' ) 
FIND_IN_SET (${srfdatacontext('srfparentkey','{"defname":"ID","dename":"ZT_PRODUCTMODULE"}')}, t1.`PATH`) 

```
### DEFAULT(DEFAULT)<div id="TestModule_Default"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`ROOT`,
t21.`NAME` AS `ROOTNAME`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_module` t11 ON t1.`PARENT` = t11.`ID` 
LEFT JOIN `zt_product` t21 ON t1.`ROOT` = t21.`ID` 

WHERE t1.DELETED = '0' 
( t1.`TYPE` = 'story'  AND  t1.`DELETED` = '0' ) 

```
### 父模块(ParentModule)<div id="TestModule_ParentModule"></div>
```sql
SELECT
	t1.`BRANCH`,
	t1.`DELETED`,
	t1.`GRADE`,
	t1.`ID`,
	( CASE WHEN EXISTS ( SELECT 1 FROM ZT_MODULE WHERE PARENT = t1.`ID` ) THEN FALSE ELSE TRUE END ) AS `ISLEAF`,
	CONCAT(
	'/',
	(
SELECT
	GROUP_CONCAT( tt.NAME SEPARATOR '/' ) 
FROM
	zt_module tt 
WHERE
	FIND_IN_SET( tt.id, t1.path ) 
	AND tt.type = 'story' 
GROUP BY
	tt.root 
	) 
	) AS `NAME`,
	t1.`ORDER`,
	t1.`OWNER`,
	t1.`PARENT`,
	t21.`NAME` AS `PARENTNAME`,
	t1.`PATH`,
	t1.`ROOT`,
	t11.`NAME` AS `ROOTNAME`,
	t1.`SHORT`,
	t1.`TYPE` 
FROM
	`zt_module` t1
	LEFT JOIN zt_product t11 ON t1.ROOT = t11.ID
	LEFT JOIN zt_module t21 ON t1.PARENT = t21.ID
WHERE t1.type = 'story' 
t1.id <> #{srf.webcontext.id} and find_in_set(#{srf.webcontext.id},t1.path) = 0 
t1.DELETED = '0' 

```
### 根模块(ROOT)<div id="TestModule_Root"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`ROOT`,
t21.`NAME` AS `ROOTNAME`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_module` t11 ON t1.`PARENT` = t11.`ID` 
LEFT JOIN `zt_product` t21 ON t1.`ROOT` = t21.`ID` 

WHERE t1.DELETED = '0' 
( t1.`TYPE` = 'story'  AND  ( t1.`PARENT` IS NULL  OR  t1.`PARENT` = 0 ) ) 

```
### 根模块_无分支(Root_NoBranch)<div id="TestModule_Root_NoBranch"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`ROOT`,
t21.`NAME` AS `ROOTNAME`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_module` t11 ON t1.`PARENT` = t11.`ID` 
LEFT JOIN `zt_product` t21 ON t1.`ROOT` = t21.`ID` 

WHERE t1.DELETED = '0' 
( t1.`TYPE` = 'story'  AND  t1.`BRANCH` = 0  AND  ( t1.`PARENT` IS NULL  OR  t1.`PARENT` = 0 ) ) 

```
### TestModule(TestModule)<div id="TestModule_TestModule"></div>
```sql
SELECT
	t1.`BRANCH`,
	t1.`DELETED`,
	t1.`GRADE`,
	t1.`ID`,
	( CASE WHEN EXISTS ( SELECT 1 FROM ZT_MODULE WHERE PARENT = t1.`ID` ) THEN FALSE ELSE TRUE END ) AS `ISLEAF`,
	CONCAT('/',(select GROUP_CONCAT(tt.name SEPARATOR '/') from zt_module tt where FIND_IN_SET(tt.id,t1.path) and tt.type = 'story' GROUP BY tt.root)) as `NAME`,
	t1.`ORDER`,
	t1.`OWNER`,
	t1.`PARENT`,
	t21.`NAME` AS `PARENTNAME`,
	t1.`PATH`,
	t1.`ROOT`,
	t11.`NAME` AS `ROOTNAME`,
	t1.`SHORT`,
	t1.`TYPE` 
FROM
	`zt_module` t1
	LEFT JOIN zt_product t11 ON t1.ROOT = t11.ID
	LEFT JOIN zt_module t21 ON t1.PARENT = t21.ID
WHERE ( t1.`TYPE` = 'story'  AND  t1.`DELETED` = '0' ) 

```
### 默认（全部数据）(VIEW)<div id="TestModule_View"></div>
```sql
SELECT
t1.`BRANCH`,
t1.`COLLECTOR`,
t1.`DELETED`,
t1.`GRADE`,
t1.`ID`,
(CASE WHEN EXISTS (SELECT 1 FROM ZT_MODULE WHERE  PARENT = t1.`ID`) THEN FALSE ELSE TRUE  END ) AS `ISLEAF`,
t1.`NAME`,
t1.`ORDER`,
t1.`OWNER`,
t1.`PARENT`,
t11.`NAME` AS `PARENTNAME`,
t1.`PATH`,
t1.`ROOT`,
t21.`NAME` AS `ROOTNAME`,
t1.`SHORT`,
t1.`TYPE`
FROM `zt_module` t1 
LEFT JOIN `zt_module` t11 ON t1.`PARENT` = t11.`ID` 
LEFT JOIN `zt_product` t21 ON t1.`ROOT` = t21.`ID` 

WHERE t1.DELETED = '0' 

```

# **测试报告**(ZT_TESTREPORT)

### DEFAULT(DEFAULT)<div id="TestReport_Default"></div>
```sql
SELECT
t1.`BEGIN`,
t1.`BUILDS`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`DELETED`,
t1.`END`,
t1.`ID`,
t1.`OBJECTID`,
t1.`OBJECTTYPE`,
t1.`OWNER`,
t1.`PRODUCT`,
t21.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t11.`NAME` AS `PROJECTNAME`,
t1.`TASKS`,
t1.`TITLE`
FROM `zt_testreport` t1 
LEFT JOIN `zt_project` t11 ON t1.`PROJECT` = t11.`ID` 
LEFT JOIN `zt_product` t21 ON t1.`PRODUCT` = t21.`ID` 

WHERE t1.DELETED = '0' 

```
### 默认（全部数据）(VIEW)<div id="TestReport_View"></div>
```sql
SELECT
t1.`BEGIN`,
t1.`BUGS`,
t1.`BUILDS`,
t1.`CASES`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`DELETED`,
t1.`END`,
t1.`ID`,
t1.`MEMBERS`,
t1.`OBJECTID`,
t1.`OBJECTTYPE`,
t1.`OWNER`,
t1.`PRODUCT`,
t21.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t11.`NAME` AS `PROJECTNAME`,
t1.`REPORT`,
t1.`STORIES`,
t1.`TASKS`,
t1.`TITLE`
FROM `zt_testreport` t1 
LEFT JOIN `zt_project` t11 ON t1.`PROJECT` = t11.`ID` 
LEFT JOIN `zt_product` t21 ON t1.`PRODUCT` = t21.`ID` 

WHERE t1.DELETED = '0' 

```

# **测试结果**(ZT_TESTRESULT)

### CurTestRun(CurTestRun)<div id="TestResult_CurTestRun"></div>
```sql
SELECT
t1.`CASE`,
t1.`CASERESULT`,
t1.`COMPILE`,
t1.`DATE`,
t1.`DURATION`,
t1.`ID`,
t1.`JOB`,
t1.`LASTRUNNER`,
t11.`MODULE`,
t21.`NAME` AS `MODULENAME`,
t11.`PRODUCT`,
t1.`RUN`,
t11.`STORY`,
t1.`VERSION`
FROM `zt_testresult` t1 
LEFT JOIN `zt_case` t11 ON t1.`CASE` = t11.`ID` 
LEFT JOIN `zt_module` t21 ON t11.`MODULE` = t21.`ID` 
RIGHT JOIN `zt_testrun` t31 ON t1.`RUN` = t31.`ID` 

WHERE ( t31.`TASK` = ${srfwebcontext('task','{"defname":"TASK","dename":"ZT_TESTRUN"}')} ) 

```
### DEFAULT(DEFAULT)<div id="TestResult_Default"></div>
```sql
SELECT
t1.`CASE`,
t1.`CASERESULT`,
t1.`COMPILE`,
t1.`DATE`,
t1.`DURATION`,
t1.`ID`,
t1.`JOB`,
t1.`LASTRUNNER`,
t11.`MODULE`,
t21.`NAME` AS `MODULENAME`,
t11.`PRODUCT`,
t1.`RUN`,
t11.`STORY`,
t1.`VERSION`
FROM `zt_testresult` t1 
LEFT JOIN `zt_case` t11 ON t1.`CASE` = t11.`ID` 
LEFT JOIN `zt_module` t21 ON t11.`MODULE` = t21.`ID` 

```
### 默认（全部数据）(VIEW)<div id="TestResult_View"></div>
```sql
SELECT
t1.`CASE`,
t1.`CASERESULT`,
t1.`COMPILE`,
t1.`DATE`,
t1.`DURATION`,
t1.`ID`,
t1.`JOB`,
t1.`LASTRUNNER`,
t11.`MODULE`,
t21.`NAME` AS `MODULENAME`,
t11.`PRECONDITION`,
t11.`PRODUCT`,
t1.`RUN`,
t1.`STEPRESULTS`,
t11.`STORY`,
t1.`VERSION`,
t1.`XML`
FROM `zt_testresult` t1 
LEFT JOIN `zt_case` t11 ON t1.`CASE` = t11.`ID` 
LEFT JOIN `zt_module` t21 ON t11.`MODULE` = t21.`ID` 

```

# **测试运行**(ZT_TESTRUN)

### DEFAULT(DEFAULT)<div id="TestRun_Default"></div>
```sql
SELECT
t1.`ASSIGNEDTO`,
t1.`CASE`,
t1.`ID`,
t1.`LASTRUNDATE`,
t1.`LASTRUNNER`,
t1.`LASTRUNRESULT`,
t1.`STATUS`,
t1.`TASK`,
t1.`VERSION`
FROM `zt_testrun` t1 

```
### 默认（全部数据）(VIEW)<div id="TestRun_View"></div>
```sql
SELECT
t1.`ASSIGNEDTO`,
t1.`CASE`,
t1.`ID`,
t1.`LASTRUNDATE`,
t1.`LASTRUNNER`,
t1.`LASTRUNRESULT`,
t1.`STATUS`,
t1.`TASK`,
t1.`VERSION`
FROM `zt_testrun` t1 

```

# **测试套件**(ZT_TESTSUITE)

### DEFAULT(DEFAULT)<div id="TestSuite_Default"></div>
```sql
SELECT
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
0 AS `CASECNT`,
t1.`DELETED`,
t1.`ID`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`NAME`,
t1.`PRODUCT`,
t1.`TYPE`
FROM `zt_testsuite` t1 

WHERE t1.DELETED = '0' 

```
### 公开套件(PublicTestSuite)<div id="TestSuite_PublicTestSuite"></div>
```sql
SELECT
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
0 AS `CASECNT`,
t1.`DELETED`,
t1.`ID`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`NAME`,
t1.`PRODUCT`,
t1.`TYPE`
FROM `zt_testsuite` t1 

WHERE t1.DELETED = '0' 
( ( t1.`TYPE` = 'public'  OR  ( t1.`TYPE` = 'private'  AND  t1.`ADDEDBY` =  ${srfsessioncontext('srfloginname','{"defname":"ADDEDBY","dename":"ZT_TESTSUITE"}')} ) )  AND  t1.`PRODUCT` = ${srfwebcontext('srfparentkey','{"defname":"PRODUCT","dename":"ZT_TESTSUITE"}')} ) 

```
### 默认（全部数据）(VIEW)<div id="TestSuite_View"></div>
```sql
SELECT
t1.`ADDEDBY`,
t1.`ADDEDDATE`,
0 AS `CASECNT`,
t1.`DELETED`,
t1.`DESC`,
t1.`ID`,
t1.`LASTEDITEDBY`,
t1.`LASTEDITEDDATE`,
t1.`NAME`,
t1.`PRODUCT`,
t1.`TYPE`
FROM `zt_testsuite` t1 

WHERE t1.DELETED = '0' 

```

# **测试版本**(ZT_TESTTASK)

### DEFAULT(DEFAULT)<div id="TestTask_Default"></div>
```sql
SELECT
t1.`AUTO`,
t1.`BEGIN`,
t1.`BUILD`,
t11.`NAME` AS `BUILDNAME`,
0 AS `CASECNT`,
t1.`DELETED`,
t1.`END`,
t1.`ID`,
t1.`MAILTO`,
'' AS `MAILTOPK`,
t1.`NAME`,
t1.`OWNER`,
t1.OWNER AS `OWNERPK`,
t1.`PRI`,
t1.`PRODUCT`,
t21.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t31.`NAME` AS `PROJECTTNAME`,
t1.`STATUS`,
t1.`SUBSTATUS`
FROM `zt_testtask` t1 
LEFT JOIN `zt_build` t11 ON t1.`BUILD` = t11.`ID` 
LEFT JOIN `zt_product` t21 ON t1.`PRODUCT` = t21.`ID` 
LEFT JOIN `zt_project` t31 ON t1.`PROJECT` = t31.`ID` 

WHERE t1.DELETED = '0' 

```
### 我的测试单(MyTestTaskPc)<div id="TestTask_MyTestTaskPc"></div>
```sql
SELECT
	t1.`id`,
	t1.`name`,
	t1.`product`,
	t1.`project`,
	t1.`build`,
	t1.`owner`,
	t1.`pri`,
	t1.`begin`,
	t1.`end`,
	t1.`mailto`,
	t1.`desc`,
	t1.`report`,
	t1.`status`,
	t1.`auto`,
	t1.`subStatus`,
	t1.`deleted`
FROM
	zt_testtask t1 
WHERE
	t1.`owner` = #{srf.sessioncontext.srfloginname} 
	and t1.`deleted` = '0'
```
### 默认（全部数据）(VIEW)<div id="TestTask_View"></div>
```sql
SELECT
t1.`AUTO`,
t1.`BEGIN`,
t1.`BUILD`,
t11.`NAME` AS `BUILDNAME`,
0 AS `CASECNT`,
t1.`DELETED`,
t1.`DESC`,
t1.`END`,
t1.`ID`,
t1.`MAILTO`,
'' AS `MAILTOPK`,
t1.`NAME`,
t1.`OWNER`,
t1.OWNER AS `OWNERPK`,
t1.`PRI`,
t1.`PRODUCT`,
t21.`NAME` AS `PRODUCTNAME`,
t1.`PROJECT`,
t31.`NAME` AS `PROJECTTNAME`,
t1.`REPORT`,
t1.`STATUS`,
t1.`SUBSTATUS`
FROM `zt_testtask` t1 
LEFT JOIN `zt_build` t11 ON t1.`BUILD` = t11.`ID` 
LEFT JOIN `zt_product` t21 ON t1.`PRODUCT` = t21.`ID` 
LEFT JOIN `zt_project` t31 ON t1.`PROJECT` = t31.`ID` 

WHERE t1.DELETED = '0' 

```

# **待办**(ZT_TODO)

### DEFAULT(DEFAULT)<div id="Todo_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ASSIGNEDBY`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
case when t1.`BEGIN` = 2400 then null else t1.`begin` end as `begin`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CONFIG`,
t1.`CYCLE`,
t1.`DATE` as `DATE`,
(case when t1.`DATE` = '2030-01-01' then '待定' else t1.`DATE` end) AS `DATE1`,
case when t1.`END` = 2400 then null else t1.`END` end as `END`,
t1.`FINISHEDBY`,
t1.`FINISHEDDATE`,
t1.`ID`,
t1.`IDVALUE`,
case when t1.type = 'bug' then t11.`TITLE` when t1.type = 'task' then t21.`name` when t1.type = 'story' then t31.`TITLE` else t1.`NAME` end `name`,
t1.`PRI`,
t1.`PRIVATE`,
t1.`STATUS`,
t1.`TYPE`
FROM `zt_todo` t1 
LEFT JOIN zt_bug t11 on t11.id = t1.idvalue
LEFT JOIN zt_task t21 on t21.id = t1.idvalue
LEFT JOIN zt_story t31 on t31.id = t1.idvalue
WHERE ((#{srf.datacontext.thisyear} = 'thisyear' and YEAR(t1.date) = year(now())) or #{srf.datacontext.thisyear} is null) 
( t1.`ACCOUNT` =  ${srfsessioncontext('srfloginname','{"defname":"ACCOUNT","dename":"ZT_TODO"}')}  or t1.`assignedTo` =  ${srfsessioncontext('srfloginname','{"defname":"ACCOUNT","dename":"ZT_TODO"}')}  or t1.`finishedBy` =  ${srfsessioncontext('srfloginname','{"defname":"ACCOUNT","dename":"ZT_TODO"}')}   ) 

```
### 我的待办(MyTodo)<div id="Todo_MyTodo"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ASSIGNEDBY`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BEGIN`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CONFIG`,
t1.`CYCLE`,
t1.`DATE`,
(case when t1.`DATE` = '2030-01-01' then '待定' else t1.`DATE` end) AS `DATE1`,
t1.`END`,
t1.`FINISHEDBY`,
t1.`FINISHEDDATE`,
t1.`ID`,
t1.`IDVALUE`,
t1.`NAME`,
t1.`PRI`,
t1.`PRIVATE`,
t1.`STATUS`,
t1.`TYPE`
FROM `zt_todo` t1 

WHERE ( t1.`ACCOUNT` =  ${srfsessioncontext('srfloginname','{"defname":"ACCOUNT","dename":"ZT_TODO"}')}  or t1.`assignedTo` =  ${srfsessioncontext('srfloginname','{"defname":"ACCOUNT","dename":"ZT_TODO"}')}  or t1.`finishedBy` =  ${srfsessioncontext('srfloginname','{"defname":"ACCOUNT","dename":"ZT_TODO"}')}   ) 
t1.`CYCLE` = 0 

```
### 我的待办(MyTodoPc)<div id="Todo_MyTodoPc"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ASSIGNEDBY`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.`BEGIN`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CONFIG`,
t1.`CYCLE`,
t1.`DATE`,
(case when t1.`DATE` = '2030-01-01' then '待定' else t1.`DATE` end) AS `DATE1`,
t1.`END`,
t1.`FINISHEDBY`,
t1.`FINISHEDDATE`,
t1.`ID`,
t1.`IDVALUE`,
t1.`NAME`,
t1.`PRI`,
t1.`PRIVATE`,
t1.`STATUS`,
t1.`TYPE`
FROM `zt_todo` t1 

WHERE ( t1.`ACCOUNT` =  ${srfsessioncontext('srfloginname','{"defname":"ACCOUNT","dename":"ZT_TODO"}')}  or t1.`assignedTo` =  ${srfsessioncontext('srfloginname','{"defname":"ACCOUNT","dename":"ZT_TODO"}')}  or t1.`finishedBy` =  ${srfsessioncontext('srfloginname','{"defname":"ACCOUNT","dename":"ZT_TODO"}')}   ) 
t1.`CYCLE` = 0 
t1.date is not null 

```
### MyUpcoming(MyUpcoming)<div id="Todo_MyUpcoming"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ASSIGNEDBY`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
case when t1.`BEGIN` = 2400 then null else t1.`begin` end as `begin`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CONFIG`,
t1.`CYCLE`,
t1.`DATE` as `DATE`,
DATE_FORMAT(t1.`date`,'%c月%d日') AS `DATE1`,
case when t1.`END` = 2400 then null else t1.`END` end as `END`,
t1.`FINISHEDBY`,
t1.`FINISHEDDATE`,
t1.`ID`,
t1.`IDVALUE`,
case when t1.type = 'bug' then t11.`TITLE` when t1.type = 'task' then t21.`name` when t1.type = 'story' then t31.`TITLE` else t1.`NAME` end `name`,
t1.`PRI`,
t1.`PRIVATE`,
t1.`STATUS`,
t1.`TYPE`
FROM `zt_todo` t1 
LEFT JOIN zt_bug t11 on t11.id = t1.idvalue
LEFT JOIN zt_task t21 on t21.id = t1.idvalue
LEFT JOIN zt_story t31 on t31.id = t1.idvalue
WHERE ( t1.`ACCOUNT` =  ${srfsessioncontext('srfloginname','{"defname":"ACCOUNT","dename":"ZT_TODO"}')}  or t1.`assignedTo` =  ${srfsessioncontext('srfloginname','{"defname":"ACCOUNT","dename":"ZT_TODO"}')}  or t1.`finishedBy` =  ${srfsessioncontext('srfloginname','{"defname":"ACCOUNT","dename":"ZT_TODO"}')}   ) 

```
### 默认（全部数据）(VIEW)<div id="Todo_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ASSIGNEDBY`,
t1.`ASSIGNEDDATE`,
t1.`ASSIGNEDTO`,
t1.ASSIGNEDTO AS `ASSIGNEDTOPK`,
t1.`BEGIN`,
t1.`CLOSEDBY`,
t1.`CLOSEDDATE`,
t1.`CONFIG`,
t1.`CONSUMED`,
t1.`COST`,
t1.`CYCLE`,
t1.`DATE`,
(case when t1.`DATE` = '2030-01-01' then '待定' else t1.`DATE` end) AS `DATE1`,
t1.`DESC`,
t1.`END`,
t1.`FINISHEDBY`,
t1.`FINISHEDDATE`,
t1.`ID`,
t1.`IDVALUE`,
t1.`NAME`,
t1.`PRI`,
t1.`PRIVATE`,
t1.`STATUS`,
t1.`TYPE`
FROM `zt_todo` t1 

```

# **用户**(ZT_USER)

### Bug用户(BugUser)<div id="User_BugUser"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ADDRESS`,
t1.`AVATAR`,
t1.`BIRTHDAY`,
t1.`CLIENTLANG`,
t1.`CLIENTSTATUS`,
t1.`COMMITER`,
t1.`DELETED`,
t1.`DEPT`,
t1.`DINGDING`,
t1.`EMAIL`,
t1.`FAILS`,
t1.`GENDER`,
t1.`ID`,
t1.`IP`,
t1.`JOIN`,
t1.`LAST`,
t1.`LOCKED`,
t1.`MOBILE`,
t1.`NICKNAME`,
t1.`PASSWORD`,
t1.`PHONE`,
t1.`QQ`,
t1.`RANZHI`,
t1.`REALNAME`,
t1.`ROLE`,
t1.`SCORE`,
t1.`SCORELEVEL`,
t1.`SKYPE`,
t1.`SLACK`,
t1.`VISITS`,
t1.`WEIXIN`,
t1.`WHATSAPP`,
t1.`ZIPCODE`
FROM `zt_user` t1 

WHERE t1.DELETED = '0' 
(${srfdatacontext('project','{"defname":"ID","dename":"ZT_RELEASE"}')} is null or ${srfdatacontext('project','{"defname":"ID","dename":"ZT_RELEASE"}')} = '0' or t1.account in (select tt.account from zt_team tt where tt.type = 'project' and tt.root = ${srfdatacontext('project','{"defname":"ID","dename":"ZT_RELEASE"}')})) 

```
### DEFAULT(DEFAULT)<div id="User_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ADDRESS`,
t1.`AVATAR`,
t1.`BIRTHDAY`,
t1.`CLIENTLANG`,
t1.`CLIENTSTATUS`,
t1.`COMMITER`,
t1.`DELETED`,
t1.`DEPT`,
t1.`DINGDING`,
t1.`EMAIL`,
t1.`FAILS`,
t1.`GENDER`,
t1.`ID`,
t1.`IP`,
t1.`JOIN`,
t1.`LAST`,
t1.`LOCKED`,
t1.`MOBILE`,
t1.`NICKNAME`,
t1.`PASSWORD`,
t1.`PHONE`,
t1.`QQ`,
t1.`RANZHI`,
t1.`REALNAME`,
t1.`ROLE`,
t1.`SCORE`,
t1.`SCORELEVEL`,
t1.`SKYPE`,
t1.`SLACK`,
t1.`VISITS`,
t1.`WEIXIN`,
t1.`WHATSAPP`,
t1.`ZIPCODE`
FROM `zt_user` t1 

WHERE t1.DELETED = '0' 

```
### 账户获取登录名(getByLogin)<div id="User_GetByLogin"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ADDRESS`,
t1.`AVATAR`,
t1.`BIRTHDAY`,
t1.`CLIENTLANG`,
t1.`CLIENTSTATUS`,
t1.`COMMITER`,
t1.`DELETED`,
t1.`DEPT`,
t1.`DINGDING`,
t1.`EMAIL`,
t1.`FAILS`,
t1.`GENDER`,
t1.`ID`,
t1.`IP`,
t1.`JOIN`,
t1.`LAST`,
t1.`LOCKED`,
t1.`MOBILE`,
t1.`NICKNAME`,
t1.`PASSWORD`,
t1.`PHONE`,
t1.`QQ`,
t1.`RANZHI`,
t1.`REALNAME`,
t1.`ROLE`,
t1.`SCORE`,
t1.`SCORELEVEL`,
t1.`SKYPE`,
t1.`SLACK`,
t1.`VISITS`,
t1.`WEIXIN`,
t1.`WHATSAPP`,
t1.`ZIPCODE`
FROM `zt_user` t1 

WHERE t1.DELETED = '0' 
( ( t1.`ACCOUNT` = 'account'  OR  t1.`MOBILE` = 'mobile'  OR  t1.`EMAIL` = 'email'  OR  t1.`COMMITER` = 'commiter' ) ) 

```
### 项目团队管理(ProjectTeamM)<div id="User_ProjectTeamM"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ADDRESS`,
t1.`AVATAR`,
t1.`BIRTHDAY`,
t1.`CLIENTLANG`,
t1.`CLIENTSTATUS`,
t1.`COMMITER`,
t1.`DELETED`,
t1.`DEPT`,
t1.`DINGDING`,
t1.`EMAIL`,
t1.`FAILS`,
t1.`GENDER`,
t1.`ID`,
t1.`IP`,
t1.`JOIN`,
t1.`LAST`,
t1.`LOCKED`,
t1.`MOBILE`,
t1.`NICKNAME`,
t1.`PASSWORD`,
t1.`PHONE`,
t1.`QQ`,
t1.`RANZHI`,
t1.`REALNAME`,
t1.`ROLE`,
t1.`SCORE`,
t1.`SCORELEVEL`,
t1.`SKYPE`,
t1.`SLACK`,
t1.`VISITS`,
t1.`WEIXIN`,
t1.`WHATSAPP`,
t1.`ZIPCODE`
FROM `zt_user` t1 

WHERE t1.DELETED = '0' 
( (t1.account = ${srfwebcontext('account','{"defname":"ACCOUNT","dename":"ZT_USER"}')} or t1.account not in (select t.account from zt_team t where t.type = 'project' and t.root = ${srfwebcontext('srfparentkey','{"defname":"ACCOUNT","dename":"ZT_USER"}')})) ) 

```
### 项目团队成员(ProjectTeamUser)<div id="User_ProjectTeamUser"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ADDRESS`,
t1.`AVATAR`,
t1.`BIRTHDAY`,
t1.`CLIENTLANG`,
t1.`CLIENTSTATUS`,
t1.`COMMITER`,
t1.`DELETED`,
t1.`DEPT`,
t1.`DINGDING`,
t1.`EMAIL`,
t1.`FAILS`,
t1.`GENDER`,
t1.`ID`,
t1.`IP`,
t1.`JOIN`,
t1.`LAST`,
t1.`LOCKED`,
t1.`MOBILE`,
t1.`NICKNAME`,
t1.`PASSWORD`,
t1.`PHONE`,
t1.`QQ`,
t1.`RANZHI`,
t1.`REALNAME`,
t1.`ROLE`,
t1.`SCORE`,
t1.`SCORELEVEL`,
t1.`SKYPE`,
t1.`SLACK`,
t1.`VISITS`,
t1.`WEIXIN`,
t1.`WHATSAPP`,
t1.`ZIPCODE`
FROM `zt_user` t1 

WHERE t1.DELETED = '0' 
t1.`account` in (select t.`account` from zt_team t where t.`type` = 'project' and t.`root` = ${srfdatacontext('project','{"defname":"ID","dename":"ZT_RELEASE"}')}) 
t1.`account` not in(select t2.`account` from zt_user t2 where FIND_IN_SET(t2.`account`, ${srfdatacontext('account','{"defname":"ID","dename":"ZT_RELEASE"}')})) 

```
### 项目团队成员(ProjectTeamUserTask)<div id="User_ProjectTeamUserTask"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ADDRESS`,
t1.`AVATAR`,
t1.`BIRTHDAY`,
t1.`CLIENTLANG`,
t1.`CLIENTSTATUS`,
t1.`COMMITER`,
t1.`DELETED`,
t1.`DEPT`,
t1.`DINGDING`,
t1.`EMAIL`,
t1.`FAILS`,
t1.`GENDER`,
t1.`ID`,
t1.`IP`,
t1.`JOIN`,
t1.`LAST`,
t1.`LOCKED`,
t1.`MOBILE`,
t1.`NICKNAME`,
t1.`PASSWORD`,
t1.`PHONE`,
t1.`QQ`,
t1.`RANZHI`,
t1.`REALNAME`,
t1.`ROLE`,
t1.`SCORE`,
t1.`SCORELEVEL`,
t1.`SKYPE`,
t1.`SLACK`,
t1.`VISITS`,
t1.`WEIXIN`,
t1.`WHATSAPP`,
t1.`ZIPCODE`
FROM `zt_user` t1 

WHERE t1.DELETED = '0' 
t1.`account` in (select t.`account` from zt_team t where t.`type` = 'project' and t.`root` = (select tt.`project` from zt_task tt where tt.`id` = ${srfdatacontext('srfparentkey','{"defname":"ID","dename":"ZT_RELEASE"}')})) 
t1.`account` not in(select t2.`account` from zt_user t2 where FIND_IN_SET(t2.`account`, ${srfdatacontext('account','{"defname":"ID","dename":"ZT_RELEASE"}')})) 

```
### TASKTEAM(TASKTEAM)<div id="User_TaskTeam"></div>
```sql
SELECT t1.`ACCOUNT`, t1.`ADDRESS`, t1.`AVATAR`, t1.`BIRTHDAY`, t1.`CLIENTLANG`, t1.`CLIENTSTATUS`, t1.`COMMITER`, t1.`DELETED`, t1.`DEPT`, t1.`DINGDING`, t1.`EMAIL`, t1.`FAILS`, t1.`GENDER`, t1.`ID`, t1.`IP`, t1.`JOIN`, t1.`LAST`, t1.`LOCKED`, t1.`MOBILE`, t1.`NICKNAME`, t1.`PASSWORD`, t1.`PHONE`, t1.`QQ`, t1.`RANZHI`, t1.`REALNAME`, t1.`ROLE`, t1.`SCORE`, t1.`SCORELEVEL`, t1.`SKYPE`, t1.`SLACK`, t1.`VISITS`, t1.`WEIXIN`, t1.`WHATSAPP`, t1.`ZIPCODE` FROM `zt_user` t1
WHERE t1.DELETED = '0' 
(t1.`account` in (select t.`account` from zt_team t where t.`type` = 'task' and ${srfdatacontext('multiple','{"defname":"ID","dename":"ZT_RELEASE"}')} = '1'  and t.`root` = ${srfdatacontext('id','{"defname":"ID","dename":"ZT_RELEASE"}')}) or 
t1.`account` in (select t.`account` from zt_team t where t.`type` = 'project' and ${srfdatacontext('multiple','{"defname":"ID","dename":"ZT_RELEASE"}')} = '0'  and t.`root` = (select tt.`project` from zt_task tt where tt.`id` = ${srfdatacontext('id','{"defname":"ID","dename":"ZT_RELEASE"}')}))
) 

```
### 默认（全部数据）(VIEW)<div id="User_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ADDRESS`,
t1.`AVATAR`,
t1.`BIRTHDAY`,
t1.`CLIENTLANG`,
t1.`CLIENTSTATUS`,
t1.`COMMITER`,
t1.`DELETED`,
t1.`DEPT`,
t1.`DINGDING`,
t1.`EMAIL`,
t1.`FAILS`,
t1.`GENDER`,
t1.`ID`,
t1.`IP`,
t1.`JOIN`,
t1.`LAST`,
t1.`LOCKED`,
t1.`MOBILE`,
t1.`NICKNAME`,
t1.`PASSWORD`,
t1.`PHONE`,
t1.`QQ`,
t1.`RANZHI`,
t1.`REALNAME`,
t1.`ROLE`,
t1.`SCORE`,
t1.`SCORELEVEL`,
t1.`SKYPE`,
t1.`SLACK`,
t1.`VISITS`,
t1.`WEIXIN`,
t1.`WHATSAPP`,
t1.`ZIPCODE`
FROM `zt_user` t1 

WHERE t1.DELETED = '0' 

```

# **用户联系方式**(ZT_USERCONTACT)

### 抄送联系人(CurUSERCONTACT)<div id="UserContact_CurUSERCONTACT"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ID`,
t1.USERLIST,
t1.`LISTNAME`
FROM `zt_usercontact` t1 

WHERE ( t1.`ACCOUNT` =  ${srfsessioncontext('srfloginname','{"defname":"ACCOUNT","dename":"ZT_USERCONTACT"}')} ) 

```
### DEFAULT(DEFAULT)<div id="UserContact_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ID`,
t1.`LISTNAME`
FROM `zt_usercontact` t1 

```
### 我的联系人(MyUSERCONTACT)<div id="UserContact_MyUSERCONTACT"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ID`,
t1.`LISTNAME`
FROM `zt_usercontact` t1 

WHERE ( t1.`ACCOUNT` =  ${srfsessioncontext('srfloginname','{"defname":"ACCOUNT","dename":"ZT_USERCONTACT"}')} ) 

```
### 默认（全部数据）(VIEW)<div id="UserContact_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ID`,
t1.`LISTNAME`,
t1.`USERLIST`
FROM `zt_usercontact` t1 

```

# **用户组**(ZT_USERGROUP)

### DEFAULT(DEFAULT)<div id="UserGroup_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`GROUP`,
concat(t1.`ACCOUNT`,'__',t1.`GROUP`) AS `ID`
FROM `zt_usergroup` t1 

```
### 默认（全部数据）(VIEW)<div id="UserGroup_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`GROUP`,
concat(t1.`ACCOUNT`,'__',t1.`GROUP`) AS `ID`
FROM `zt_usergroup` t1 

```

# **用户查询**(ZT_USERQUERY)

### DEFAULT(DEFAULT)<div id="UserQuery_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ID`,
t1.`MODULE`,
t1.`SHORTCUT`,
t1.`TITLE`
FROM `zt_userquery` t1 

```
### 默认（全部数据）(VIEW)<div id="UserQuery_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`FORM`,
t1.`ID`,
t1.`MODULE`,
t1.`SHORTCUT`,
t1.`SQL`,
t1.`TITLE`
FROM `zt_userquery` t1 

```

# **用户模板**(ZT_USERTPL)

### DEFAULT(DEFAULT)<div id="UserTpl_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CONTENT`,
t1.`ID`,
t1.`PUBLIC`,
t1.`TITLE`,
t1.`TYPE`
FROM `zt_usertpl` t1 

WHERE ( ( t1.`PUBLIC` = '1'  OR  t1.`ACCOUNT` =  ${srfwebcontext('account','{"defname":"ACCOUNT","dename":"ZT_USERTPL"}')} )  AND  t1.`TYPE` =  ${srfwebcontext('type','{"defname":"TYPE","dename":"ZT_USERTPL"}')} ) 

```
### 我的模板(MyUserTpl)<div id="UserTpl_MyUserTpl"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CONTENT`,
t1.`ID`,
t1.`PUBLIC`,
t1.`TITLE`,
t1.`TYPE`
FROM `zt_usertpl` t1 

WHERE ( t1.`ACCOUNT` =  ${srfsessioncontext('srfloginname','{"defname":"ACCOUNT","dename":"ZT_USERTPL"}')} ) 

```
### 默认（全部数据）(VIEW)<div id="UserTpl_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`CONTENT`,
t1.`ID`,
t1.`PUBLIC`,
t1.`TITLE`,
t1.`TYPE`
FROM `zt_usertpl` t1 

```

# **用户视图**(ZT_USERVIEW)

### DEFAULT(DEFAULT)<div id="UserView_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
concat(t1.`ACCOUNT`) AS `ID`
FROM `zt_userview` t1 

```
### 默认（全部数据）(VIEW)<div id="UserView_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
concat(t1.`ACCOUNT`) AS `ID`,
t1.`PRODUCTS`,
t1.`PROJECTS`
FROM `zt_userview` t1 

```

# **用户年度工作内容统计**(IBZ_USERYEARWORKSTATS)

### 数据查询(DEFAULT)<div id="UserYearWorkStats_Default"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ADDRESS`,
t1.`AVATAR`,
t1.`BIRTHDAY`,
t1.`CLIENTLANG`,
t1.`CLIENTSTATUS`,
t1.`COMMITER`,
'一月' AS `CURMONTH`,
(DATE_FORMAT(now(),'%Y')) AS `CURYEAR`,
t1.`DELETED`,
t1.`DEPT`,
t1.`DINGDING`,
t1.`EMAIL`,
t1.`FAILS`,
t1.`GENDER`,
t1.`ID`,
t1.`IP`,
t1.`JOIN`,
(case when t1.role in ('dev','td','pm') then 'dev' when t1.role in ('qd','qa') then 'qa' else 'po' end) AS `JUDGEROLE`,
t1.`LAST`,
t1.`LOCKED`,
t1.`MOBILE`,
0 AS `MONTESTIMATE`,
0 AS `MONTHFINISHTASK`,
0 AS `MONTRESOLVEDBUG`,
t1.`NICKNAME`,
t1.`PASSWORD`,
t1.`PHONE`,
t1.`QQ`,
t1.`RANZHI`,
t1.`REALNAME`,
t1.`ROLE`,
t1.`SCORE`,
t1.`SCORELEVEL`,
t1.`SKYPE`,
t1.`SLACK`,
 CONCAT_WS('',(DATE_FORMAT(now(),'%Y')),'年工作内容统计一览表 —— ',t1.realname) AS `TITLE`,
t1.`VISITS`,
t1.`WEIXIN`,
t1.`WHATSAPP`,
0 AS `YEARACTIONCNT`,
0 AS `YEARBUGCNT`,
0 AS `YEARCASECNT`,
0 AS `YEARESTIMATECNT`,
0 AS `YEARLOGCNT`,
0 AS `YEARPLANCNT`,
0 AS `YEARPRODUCTCNT`,
0 AS `YEARSTORYCNT`,
(select count(1) from zt_action t where t.actor = t1.account and t.action = 'login' and DATE_FORMAT(t.date,'%Y') = DATE_FORMAT(now(),'%Y')) AS `YEARVISITS`,
t1.`ZIPCODE`
FROM `zt_user` t1 

```
### 月完成任务数及累计工时和解决Bug数(MonthFinishTaskAndBug)<div id="UserYearWorkStats_MonthFinishTaskAndBug"></div>
```sql
select t1.account,CONCAT_WS('',CASE 
	WHEN t21.`YEAR` is NULL THEN
		#{srf.webcontext.curyear}
	ELSE
		t21.`YEAR`
END ,'-',t1.CURMONTH,'-01 00:00:00') as CURMONTH,case when t11.consumed is null then 0 else t11.consumed end as MONTESTIMATE,case when t11.MONTHFINISHTASK is null then 0 else t11.MONTHFINISHTASK end as MONTHFINISHTASK,case when t21.ss is null then 0 else t21.ss end as MONTRESOLVEDBUG   from (
select * from (select DISTINCT t1.actor as account,'01' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'02' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'03' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'04' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'05' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'06' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'07' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'08' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'09' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'10' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'11' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'12' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t) t1 left join (
SELECT
	t11.account,
	t11.date,
	sum( t11.`consumed` ) AS `consumed`,
	COUNT( t11.task ) AS MONTHFINISHTASK 
FROM
	(
SELECT
  DATE_FORMAT( t11.date, '%Y' ) AS `YEAR`,
	DATE_FORMAT( t11.date, '%m' ) AS date,
	t11.account,
	t11.`consumed`,
	t11.task 
FROM
	zt_taskestimate t11 
	) t11 
WHERE
	t11.date NOT IN ( '00', '' ) and t11.`YEAR` = #{srf.webcontext.curyear}
GROUP BY
	t11.account,
	t11.date ) t11 on t11.account = t1.account and t11.date = t1.curmonth
	left join (
SELECT
	tt.`YEAR`,
	tt.resolvedBy,
	tt.resolvedDate,
	COUNT( 1 ) AS ss 
FROM
	( SELECT DATE_FORMAT( t.resolvedDate, '%Y' ) as `YEAR`,DATE_FORMAT( t.resolvedDate, '%m' ) AS resolvedDate, t.resolvedBy FROM zt_bug t WHERE t.resolvedBy <> '' ) tt 
WHERE
	tt.resolvedDate <> '00'  and tt.`YEAR` =   #{srf.webcontext.curyear}
GROUP BY
	tt.resolvedBy,
	tt.resolvedDate ) t21 on t1.account = t21.resolvedBy AND t21.resolvedDate = t1.CURMONTH
WHERE t1.account = #{srf.sessioncontext.srfloginname} 

```
### 月创建Bug数和创建用例数(MonthOpenedBugAndCase)<div id="UserYearWorkStats_MonthOpenedBugAndCase"></div>
```sql
 select t1.account,CONCAT_WS('',case when t11.`year` is not null then t11.`YEAR` when t21.`year` is not null then t21.`year` else DATE_FORMAT( now(), '%Y' ) end  ,'-',t1.CURMONTH,'-01 00:00:00') as CURMONTH,case when t11.MONTRESOLVEDBUG is null then 0 else t11.MONTRESOLVEDBUG end as MONTRESOLVEDBUG,case when t21.YEARCASECNT is null then 0 else t21.YEARCASECNT end as YEARCASECNT  from (
select * from (select DISTINCT t1.actor as account,'01' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'02' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'03' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'04' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'05' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'06' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'07' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'08' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'09' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'10' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'11' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'12' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t) t1 left join (select t11.openedBy,t11.openedDate,COUNT(1) as MONTRESOLVEDBUG,t11.`year` from (select t11.openedBy, DATE_FORMAT( t11.openedDate, '%Y' ) AS `YEAR`, 	DATE_FORMAT( t11.openedDate, '%m' ) AS openedDate from zt_bug t11  where t11.deleted = '0') t11 where t11.`YEAR` = #{srf.webcontext.curyear}
					 GROUP BY t11.openedBy,t11.openedDate ) t11 on t11.openedBy = t1.account and t11.openedDate = t1.curmonth 	left join (select t11.openedBy,t11.openedDate,COUNT(1) as YEARCASECNT,t11.`year` from (select t11.openedBy, DATE_FORMAT( t11.openedDate, '%Y' ) AS `YEAR`, 	DATE_FORMAT( t11.openedDate, '%m' ) AS openedDate from zt_case t11  where t11.deleted = '0') t11 where t11.`YEAR` =#{srf.webcontext.curyear} 
					 GROUP BY t11.openedBy,t11.openedDate ) t21 on t21.openedBy = t1.account and t21.openedDate = t1.curmonth
WHERE t1.account = #{srf.sessioncontext.srfloginname} 

```
### 月创建需求数(MonthOpenedStory)<div id="UserYearWorkStats_MonthOpenedStory"></div>
```sql
select t1.account,CONCAT_WS('',CASE 
	WHEN t11.`YEAR` is NULL THEN
		 #{srf.webcontext.curyear}
	ELSE
		t11.`YEAR`
END ,'-',t1.CURMONTH,'-01 00:00:00') as CURMONTH,case when t11.YEARSTORYCNT is null then 0 else t11.YEARSTORYCNT end as YEARSTORYCNT  from (
select * from (select DISTINCT t1.actor as account,'01' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'02' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'03' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'04' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'05' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'06' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'07' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'08' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'09' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'10' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'11' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t
UNION
select * from (select DISTINCT t1.actor as account,'12' as curmonth from zt_action t1 where t1.actor <> '' and t1.actor is not null) t) t1 left join (select t11.openedBy,t11.`year`,t11.openedDate,COUNT(1) as YEARSTORYCNT from (select t11.openedBy, DATE_FORMAT( t11.openedDate, '%Y' ) AS `YEAR`,
	DATE_FORMAT( t11.openedDate, '%m' ) AS openedDate from zt_story t11  where t11.deleted = '0') t11 where t11.`YEAR` =  #{srf.webcontext.curyear}
	GROUP BY t11.openedBy,t11.openedDate ) t11 on t11.openedBy = t1.account and t11.openedDate = t1.curmonth
WHERE t1.account = #{srf.sessioncontext.srfloginname} 

```
### 默认（全部数据）(VIEW)<div id="UserYearWorkStats_View"></div>
```sql
SELECT
t1.`ACCOUNT`,
t1.`ADDRESS`,
t1.`AVATAR`,
t1.`BIRTHDAY`,
t1.`CLIENTLANG`,
t1.`CLIENTSTATUS`,
t1.`COMMITER`,
'一月' AS `CURMONTH`,
(DATE_FORMAT(now(),'%Y')) AS `CURYEAR`,
t1.`DELETED`,
t1.`DEPT`,
t1.`DINGDING`,
t1.`EMAIL`,
t1.`FAILS`,
t1.`GENDER`,
t1.`ID`,
t1.`IP`,
t1.`JOIN`,
(case when t1.role in ('dev','td','pm') then 'dev' when t1.role in ('qd','qa') then 'qa' else 'po' end) AS `JUDGEROLE`,
t1.`LAST`,
t1.`LOCKED`,
t1.`MOBILE`,
0 AS `MONTESTIMATE`,
0 AS `MONTHFINISHTASK`,
0 AS `MONTRESOLVEDBUG`,
t1.`NICKNAME`,
t1.`PASSWORD`,
t1.`PHONE`,
t1.`QQ`,
t1.`RANZHI`,
t1.`REALNAME`,
t1.`ROLE`,
t1.`SCORE`,
t1.`SCORELEVEL`,
t1.`SKYPE`,
t1.`SLACK`,
 CONCAT_WS('',(DATE_FORMAT(now(),'%Y')),'年工作内容统计一览表 —— ',t1.realname) AS `TITLE`,
t1.`VISITS`,
t1.`WEIXIN`,
t1.`WHATSAPP`,
0 AS `YEARACTIONCNT`,
0 AS `YEARBUGCNT`,
0 AS `YEARCASECNT`,
0 AS `YEARESTIMATECNT`,
0 AS `YEARLOGCNT`,
0 AS `YEARPLANCNT`,
0 AS `YEARPRODUCTCNT`,
0 AS `YEARSTORYCNT`,
(select count(1) from zt_action t where t.actor = t1.account and t.action = 'login' and DATE_FORMAT(t.date,'%Y') = DATE_FORMAT(now(),'%Y')) AS `YEARVISITS`,
t1.`ZIPCODE`
FROM `zt_user` t1 

```

# **网页钩子**(ZT_WEBHOOK)

### DEFAULT(DEFAULT)<div id="WebHook_Default"></div>
```sql
SELECT
t1.`CONTENTTYPE`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`DELETED`,
t1.`DOMAIN`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`ID`,
t1.`NAME`,
t1.`PARAMS`,
t1.`SECRET`,
t1.`SENDTYPE`,
t1.`TYPE`,
t1.`URL`
FROM `zt_webhook` t1 

WHERE t1.DELETED = '0' 

```
### 默认（全部数据）(VIEW)<div id="WebHook_View"></div>
```sql
SELECT
t1.`ACTIONS`,
t1.`CONTENTTYPE`,
t1.`CREATEDBY`,
t1.`CREATEDDATE`,
t1.`DELETED`,
t1.`DESC`,
t1.`DOMAIN`,
t1.`EDITEDBY`,
t1.`EDITEDDATE`,
t1.`ID`,
t1.`NAME`,
t1.`PARAMS`,
t1.`PRODUCTS`,
t1.`PROJECTS`,
t1.`SECRET`,
t1.`SENDTYPE`,
t1.`TYPE`,
t1.`URL`
FROM `zt_webhook` t1 

WHERE t1.DELETED = '0' 

```

