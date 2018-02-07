--2018-1-30
ALTER TABLE `mabtc`.`mb_sub_user`
ADD COLUMN `btc_desc` VARCHAR(100) NULL AFTER `site_id`,
ADD COLUMN `bch_desc` VARCHAR(100) NULL AFTER `btc_desc`;
INSERT INTO `mabtc`.`mb_site` (`id`, `name`, `last_time`) VALUES ('2', 'btc.com', '2018-01-29');
INSERT INTO `mabtc`.`mb_sub_user` (`id`, `name`, `site_id`, `btc_desc`, `bch_desc`) VALUES ('1', 'jieyuemuli', '2', 'access_key=r4K3P5KvUztOXWx5X3eSQmAgkaMbEAcJQRfQXGpc&puid=127574', 'access_key=r4K3P5KvUztOXWx5X3eSQmAgkaMbEAcJQRfQXGpc&puid=137148');
INSERT INTO `mabtc`.`mb_sub_user` (`id`, `name`, `site_id`, `btc_desc`, `bch_desc`) VALUES ('3', 'huobitewuhai', '2', 'access_key=r4K3P5KvUztOXWx5X3eSQmAgkaMbEAcJQRfQXGpc&puid=126090', '');
--2018-2-8
INSERT INTO `mabtc`.`mb_sub_user` (`id`, `name`, `site_id`) VALUES ('999', 'btccom返佣', '2');
