# CRM---SENAC-RJ

SCRIPT DE CRIAÇÃO DO BANCO 

-- -----------------------------------------------------
-- Schema `crm_senac` 
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `crm_senac` DEFAULT CHARACTER SET utf8 ;

USE crm_senac;

-- -----------------------------------------------------
-- Table `crm_senac`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm_senac`.`status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `crm_senac`.`status` insert
-- -----------------------------------------------------
INSERT INTO STATUS (descricao) VALUES ("Ativo");
INSERT INTO STATUS (descricao) VALUES ("Inativo");

-- -----------------------------------------------------
-- Table `crm_senac`.`funil_etapa`
-- -----------------------------------------------------
-- CREATE TABLE IF NOT EXISTS `crm_senac`.`funil_etapa` (
--  `id` INT NOT NULL AUTO_INCREMENT,
--  `descricao` VARCHAR(100) NOT NULL,
--  `status_id` INT NOT NULL,
--  PRIMARY KEY (`id`),
--  CONSTRAINT `fk_status1`
--    FOREIGN KEY (`status_id`)
--    REFERENCES `crm_senac`.`status` (`status_id`)
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION)
-- ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `crm_senac`.`nivel_instrucao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm_senac`.`nivel_instrucao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_nivel_instrucao_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `crm_senac`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `crm_senac`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm_senac`.`produto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `descricao` VARCHAR(700) NOT NULL,
  `nivel_instrucao_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_produto_nivel_instrucao1_idx` (`nivel_instrucao_id` ASC),
  INDEX `fk_produto_status1_idx` (`status_id` ASC),
  CONSTRAINT `fk_produto_nivel_instrucao1`
    FOREIGN KEY (`nivel_instrucao_id`)
    REFERENCES `crm_senac`.`nivel_instrucao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `crm_senac`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `crm_senac`.`oferta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm_senac`.`oferta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  `data_inicio` DATE NULL,
  `data_fim` DATE NULL,
  `preco` DOUBLE NOT NULL,
  `produto_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`id`, `produto_id`),
  INDEX `fk_oferta_produto1_idx` (`produto_id` ASC),
  CONSTRAINT `fk_oferta_produto1`
    FOREIGN KEY (`produto_id`)
    REFERENCES `crm_senac`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_oferta_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `crm_senac`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `crm_senac`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm_senac`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `login` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `cargo` VARCHAR(70) NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_usuario_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `crm_senac`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `crm_senac`.`perfil_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm_senac`.`perfil_usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NULL,
  `usuario_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_perfil_usuario_usuario1_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_perfil_usuario_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `crm_senac`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_perfil_usuario_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `crm_senac`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `crm_senac`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm_senac`.`cliente` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `cpf` VARCHAR(12) NOT NULL,
  `nome` VARCHAR(200) NOT NULL,
  `sobrenome` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `telefone` VARCHAR(11) NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  CONSTRAINT `fk_cliente_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `crm_senac`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `crm_senac`.`cliente_possivel_produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm_senac`.`cliente_possivel_produto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `produto_id` INT NOT NULL,
  `cliente_id` INT(11) NOT NULL,
  `data` DATE NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`id`, `produto_id`, `cliente_id`),
  INDEX `fk_cliente_possivel_produto_cliente1_idx` (`cliente_id` ASC),
  INDEX `fk_cliente_possivel_produto_produto1_idx` (`produto_id` ASC),
  CONSTRAINT `fk_cliente_possivel_produto_produto1`
    FOREIGN KEY (`produto_id`)
    REFERENCES `crm_senac`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_possivel_produto_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `crm_senac`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_possivel_produto_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `crm_senac`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `crm_senac`.`acao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm_senac`.`acao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(700) NOT NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_acao_status1_idx` (`status_id` ASC),
  CONSTRAINT `fk_acao_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `crm_senac`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `crm_senac`.`cliente_oferta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm_senac`.`cliente_oferta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `preco` DOUBLE NOT NULL,
  `cliente_id` INT(11) NOT NULL,
--  `funil_etapa_id` INT NOT NULL,
  `oferta_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`id`, `cliente_id`, /*`funil_etapa_id`,*/ `oferta_id`),
  INDEX `fk_cliente_oferta_cliente1_idx` (`cliente_id` ASC),
--  INDEX `fk_funil_etapa1_idx` (`funil_etapa_id` ASC),
  INDEX `fk_cliente_oferta_oferta1_idx` (`oferta_id` ASC),
  CONSTRAINT `fk_cliente_oferta_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `crm_senac`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
-- CONSTRAINT `fk_funil_etapa1` 
--  FOREIGN KEY (`funil_etapa_id`)
--    REFERENCES `crm_senac`.`funil_etapa` (`funil_etapa_id`)
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_oferta_oferta1`
    FOREIGN KEY (`oferta_id`)
    REFERENCES `crm_senac`.`oferta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_oferta_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `crm_senac`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `crm_senac`.`acao_usuario_cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm_senac`.`acao_usuario_cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cliente_id` INT(11) NOT NULL,
  `acao_id` INT NOT NULL,
  `descricao` VARCHAR(700) NULL,
  `data` DATE NOT NULL,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`id`, `usuario_id`),
  INDEX `fk_acao_usuario_cliente_cliente1_idx` (`cliente_id` ASC),
  INDEX `fk_acao_usuario_cliente_acao1_idx` (`acao_id` ASC),
  INDEX `fk_acao_usuario_cliente_usuario1_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_acao_usuario_cliente_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `crm_senac`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_acao_usuario_cliente_acao1`
    FOREIGN KEY (`acao_id`)
    REFERENCES `crm_senac`.`acao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_acao_usuario_cliente_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `crm_senac`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `crm_senac`.`chatbot_cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm_senac`.`chatbot_cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NULL,
  `email` VARCHAR(45) NULL,
  `produto` VARCHAR(45) NULL,
  `telefone` VARCHAR(11) NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_chatbot_cliente_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `crm_senac`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `crm_senac`.`categoria_dado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm_senac`.`categoria_dado` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_categoria_dado_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `crm_senac`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `crm_senac`.`dado_tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm_senac`.`dado_tipo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  `categoria_dado_id` INT NOT NULL,
  `obrigatorio` VARCHAR(45) NULL,
  `padrao` VARCHAR(1) NULL,
  `mascara` VARCHAR(200) NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_dado_tipo_categoria_dado1_idx` (`categoria_dado_id` ASC),
  CONSTRAINT `fk_dado_tipo_categoria_dado1`
    FOREIGN KEY (`categoria_dado_id`)
    REFERENCES `crm_senac`.`categoria_dado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dado_tipo_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `crm_senac`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `crm_senac`.`cliente_dado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm_senac`.`cliente_dado` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `valor` DOUBLE NOT NULL,
  `cliente_id` INT(11) NOT NULL,
  `dado_tipo_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cliente_dado_cliente_idx` (`cliente_id` ASC),
  INDEX `fk_cliente_dado_dado_tipo1_idx` (`dado_tipo_id` ASC), 
  CONSTRAINT `fk_cliente`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `crm_senac`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_dado_dado_tipo1`
    FOREIGN KEY (`dado_tipo_id`)
    REFERENCES `crm_senac`.`dado_tipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_dado_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `crm_senac`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `crm_senac`.`acao_usuario_cliente_oferta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm_senac`.`acao_usuario_cliente_oferta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `acao_id` INT NOT NULL,
  `descricao` VARCHAR(700) NULL,
  `data` DATE NULL,
  `usuario_id` INT NOT NULL,
  `cliente_oferta_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`id`, `usuario_id`, `cliente_oferta_id`),
  INDEX `fk_acao_usuario_cliente_oferta_acao1_idx` (`acao_id` ASC),
  INDEX `fk_acao_usuario_cliente_oferta_usuario1_idx` (`usuario_id` ASC),
  INDEX `fk_acao_usuario_cliente_oferta_cliente_oferta1_idx` (`cliente_oferta_id` ASC),
  CONSTRAINT `fk_acao_usuario_cliente_oferta_acao1`
    FOREIGN KEY (`acao_id`)
    REFERENCES `crm_senac`.`acao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_acao_usuario_cliente_oferta_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `crm_senac`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_acao_usuario_cliente_oferta_cliente_oferta1`
    FOREIGN KEY (`cliente_oferta_id`)
    REFERENCES `crm_senac`.`cliente_oferta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_acao_usuario_cliente_oferta_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `crm_senac`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `crm_senac`.`agenda_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm_senac`.`agenda_usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cliente_id` INT(11) NOT NULL,
  `cliente_oferta_id` INT NULL,
--  `funil_etapa_id` INT NULL,
  `produto_id` INT NOT NULL,
  `data_inicio` TIMESTAMP NOT NULL,
  `data_fim` TIMESTAMP NOT NULL,
  `usuario_id` INT NOT NULL,
  `status_id` INT NOT NULL,		
  PRIMARY KEY (`id`, `cliente_id`, `produto_id`),
  INDEX `fk_agenda_usuario_cliente1_idx` (`cliente_id` ASC),
  INDEX `fk_agenda_usuario_cliente_oferta1_idx` (`cliente_oferta_id` ASC),
--  INDEX `fk_funil_etapa1_idx` (`funil_etapa_id` ASC),
  INDEX `fk_agenda_usuario_produto1_idx` ( `produto_id` ASC),
  INDEX `fk_agenda_usuario_usuario1_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_agenda_usuario_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `crm_senac`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
	CONSTRAINT `fk_agenda_usuario_cliente_oferta1`
    FOREIGN KEY (`cliente_oferta_id`)
    REFERENCES `crm_senac`.`cliente_oferta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
--    CONSTRAINT `fk_funil_etapa1`
--    FOREIGN KEY (`funil_etapa_id`)
--    REFERENCES `crm_senac`.`funil_etapa` (`funil_etapa_id`)
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION,
    CONSTRAINT `fk_agenda_usuario_produto1`
    FOREIGN KEY (`produto_id`)
    REFERENCES `crm_senac`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_agenda_usuario_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `crm_senac`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_agenda_usuario_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `crm_senac`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `crm_senac`.`cliente` insert
-- -----------------------------------------------------
INSERT INTO cliente (cpf,nome,sobrenome,email,telefone,status_id) VALUES (100223587,"Eve","Deleon","sit.nulla@enimCurabitur.net","10112901033",2),(101334705,"Freya","Washington","quis.tristique@arcuvel.ca","66779525602",2),(102445823,"Chase","Sellers","egetMauris@lectusante.net","50076209918",2),(103556941,"Eden","Ratliff","tempor.arcu@tellus.ca","47344762975",2),(104668059,"Libby","Miranda","ac.ffacilisis@ac.ca","50058689592",2),(105779177,"Neve","Mullins","Quisque@loremac.co.uk","43510050636",2),(106890295,"Gil","Schmidt","morbi.senectus@nec.com","44242389094",2),(108001413,"Jasper","Frost","dignissim@elenuncrisus.org","75766425577",2),(109112531,"Caesar","Banks","euismod.arcu@bibenderDuis.co.uk","72180187977",2),(110223649,"Mia","Mcclure","luctus.vulputate@diamvel.net","00879805734",2);
INSERT INTO cliente (cpf,nome,sobrenome,email,telefone,status_id) VALUES (111334767,"Wesley","Riddle","est@dolorvitae.edu","83070045228",2),(112445885,"Fredericka","Johns","arcu@eu.net","91822429267",2),(113557003,"Jessamine","Cash","porttitor.tellus@duiin.edu","21336672271",2),(114668121,"Brittany","Henson","Etiam.ligula@ornareliberoat.edu","15795563490",2),(115779239,"Brenden","Humphrey","Donec@facilisis.net","26946251845",2),(116890357,"Fletcher","Sanford","erat.pede@eu.org","58490527584",2),(118001475,"Macaulay","Abbott","porttitor.Sed@accumsmlibero.co.uk","60124205960",2),(119112593,"Hamilton","Kaufman","dolor@miacmattis.co.uk","49139286857",2),(120223711,"Rowan","Acevedo","nec@fringillapurusmauris.ca","32998689030",2),(121334829,"Paula","Benton","SuspendisseNullam@ornareFuscemollis.net","95324202374",2);
INSERT INTO cliente (cpf,nome,sobrenome,email,telefone,status_id) VALUES (122445947,"Ivan","Kelly","eros@sem.com","46852850956",2),(123557065,"Conan","Freeman","semper@faucibusMorbivehicula.co.uk","46852558086",2),(124668183,"Ivy","Mueller","vel@ametmassaQuisque.net","79772735219",2),(125779301,"Mona","Rivers","Nunc@vel.ca","81985834372",2),(126890419,"Gannon","Ellis","Vivamus@tellusjusto.org","09430157430",2),(128001537,"Sophia","Merrill","lacus.Cras.interdum@tortornibh.co.uk","51939967079",2),(129112655,"Zane","Zimmerman","mauris@risusaultricies.ca","49605997948",2),(130223773,"Cameron","Sears","aliquam@semegestasblandit.org","98436926429",2),(131334891,"Quentin","Vang","erat.neque.non@ac.co.uk","25025935934",2),(132446009,"Zephr","Briggs","orci.in.consequat@Nulla.net","01802798464",2);
INSERT INTO cliente (cpf,nome,sobrenome,email,telefone,status_id) VALUES (133557127,"Lareina","Hooper","ipsum@purusmauris.ca","29420415703",2),(134668245,"Helen","Carroll","orci.consectetuer.euismod@Crasdictum.edu","89313028766",2),(135779363,"Martin","Patrick","in@amet.com","67986763860",2),(136890481,"Roary","Leon","aptent@tellusimperdietnon.co.uk","27891418620",2),(138001599,"Amal","Powers","Vivamus@cursuspurusNullam.ca","58488692643",2),(139112717,"Olympia","Serrano","Aenean.gravida.nunc@Phasellus.com","62793283345",2),(140223835,"Lisandra","Hoffman","elit@acmetus.edu","72587581304",2),(141334953,"Orlando","Yates","fringilla.porttitor.vulputate@idmagnaet.co.uk","71549379569",2),(142446071,"Ezra","Delaney","nostra.per@eget.org","45266148607",2),(143557189,"Dominique","Leon","natoque.penatibus.et@nibh.co.uk","34335358760",2);
INSERT INTO cliente (cpf,nome,sobrenome,email,telefone,status_id) VALUES (144668307,"Zeph","Collins","magna.a@nec.co.uk","77224233742",2),(145779425,"Armando","Rodriquez","netus.et@nascetur.co.uk","53422275006",2),(146890543,"Sydnee","Hicks","nisi.Mauris.nulla@Cras.com","76299013887",2),(148001661,"Wendy","Acosta","magna.a@eratvolutpatNulla.net","18409120013",2),(149112779,"Xenos","Foster","nunc.id@Mauris.edu","35509968307",2),(150223897,"Kelly","Norton","dui.semper.et@senectusetnetus.co.uk","22511347424",2),(151335015,"Adele","Sanford","ornare@tristiquesenectuset.com","79577270845",2),(152446133,"Stephanie","Chambers","ut@Integereu.org","79733405146",2),(153557251,"Ann","Valdez","ultricies@felisDonectempor.ca","06362564419",2),(154668369,"Porter","Yates","adipiscing@nonbibendumsed.com","52820293912",2);
INSERT INTO cliente (cpf,nome,sobrenome,email,telefone,status_id) VALUES (155779487,"Guy","Browning","a.felis.ullamcorper@odioEtiam.edu","71601259572",2),(156890605,"Tamekah","Mckenzie","Maecenas.mi@rutrummagna.edu","85187543475",2),(158001723,"Maia","Jordan","varius.Nam@metus.ca","19429676050",2),(159112841,"Clark","Daniels","Lorem.ipsum.dolor@fringillaestMauris.edu","96288746460",2),(160223959,"Wesley","Phillips","varius.Nam@habitantmorbi.com","38737373526",2),(161335077,"Davis","Gibson","in.faucibus@dolor.co.uk","07224328390",2),(162446195,"Cyrus","Palmer","massa.Suspendisse.eleifend@neccursus.net","55715743267",2),(163557313,"Rajah","Osborne","cursus@venenatisvel.org","03847175508",2),(164668431,"Justina","Wilcox","in.sodales.elit@ultricesaauctor.com","07265000418",2),(165779549,"Fiona","Ochoa","ac@egestasligulaNullam.edu","06508005542",2);
INSERT INTO cliente (cpf,nome,sobrenome,email,telefone,status_id) VALUES (166890667,"Oliver","Valdez","Pellentesque@volutpatnuncsit.org","58124763728",2),(168001785,"Sonya","Pierce","dolor.sit.amet@tellussemmollis.ca","85864420251",2),(169112903,"Mary","Nash","tempor@ullamcorper.edu","52989862693",2),(170224021,"Ivy","Decker","mi.eleifend.egestas@pharetra.com","05066455450",2),(171335139,"Ora","Frank","purus@Maecenasornareegestas.net","40002670479",2),(172446257,"Aquila","Elliott","faucibus.orci.luctus@Aeneaneuismod.net","90883640737",2),(173557375,"Cairo","Greer","vel@tortordictum.org","87302605215",2),(174668493,"Kaseem","Mckee","arcu.ac@elitfermentumrisus.com","33748979739",2),(175779611,"Desiree","James","Maecenas@acipsumPhasellus.com","95129212643",2),(176890729,"Isaiah","Roach","nec@iaculisodio.net","12262255000",2);
INSERT INTO cliente (cpf,nome,sobrenome,email,telefone,status_id) VALUES (178001847,"Lucius","Bonner","ipsum.Suspendisse@velitjustonec.net","30654120472",2),(179112965,"Dorothy","Horne","risus@gravidanuncsed.edu","73987040793",2),(180224083,"Dustin","Webb","ac.feugiat@pede.co.uk","08583688920",2),(181335201,"Illiana","Mccray","massa.Integer.vitae@eueratsemper.com","89539086141",2),(182446319,"Tiger","Zimmerman","Cras.eu@eget.edu","40457118423",2),(183557437,"Chancellor","Copeland","elementum.at.egestas@facilisisSuspendisse.edu","29562499578",2),(184668555,"Halee","Hurley","nibh.Aliquam@elementumsem.co.uk","54915263852",2),(185779673,"Riley","Baldwin","Pellentesque@diamnunc.edu","64187770319",2),(186890791,"Jade","Sandoval","faucibus.leo.in@aliquetsem.ca","40434086427",2),(188001909,"Ramona","Leon","laoreet@anteMaecenas.com","67631512309",2);
INSERT INTO cliente (cpf,nome,sobrenome,email,telefone,status_id) VALUES (200224207,"Arden","Bowen","at@sitamet.org","40397928893",2),(201335325,"Ulysses","Moreno","ultricies.ligula@nequevenenatis.com","69630990432",2),(202446443,"Adria","Medina","Mauris.nulla@amifringilla.edu","57092707966",2),(203557561,"Logan","Medina","sagittis@semelitpharetra.com","39415276426",2),(204668679,"Wyoming","Lancaster","eleifend.nunc.risus@dictum.com","86562810934",2),(205779797,"April","Cannon","massa.Mauris@montesnasceturridiculus.co.uk","04929918919",2),(206890915,"Carol","Chen","Proin.mi.Aliquam@Fusce.org","96031788836",2),(208002033,"Talon","Stokes","non.quam@non.net","09442805816",2),(209113151,"Elton","Brown","dictum@Quisqueliberolacus.com","23548684025",2),(210224269,"Rahim","Fitzgerald","eu@diam.com","88023163893",2);

-- -----------------------------------------------------
-- Table `crm_senac`.`nivel_instrucao` insert
-- -----------------------------------------------------
INSERT INTO nivel_instrucao (descricao, status_id) VALUES ("Graduação", 2);
INSERT INTO nivel_instrucao (descricao, status_id) VALUES ("Cursdo Técnico", 2);
INSERT INTO nivel_instrucao (descricao, status_id) VALUES ("Pós-Graduação e MBA", 2);

-- -----------------------------------------------------
-- Table `crm_senac`.`produto` insert
-- -----------------------------------------------------
INSERT INTO produto (nome, descricao, nivel_instrucao_id, status_id) VALUES ("Graduação Tecnológica em Análise e Desenvolvimento de Sistemas","Aprenda a projetar sistemas de informação aplicando princípios de orientação a objetos e metodologia de desenvolvimento de sistemas existente no mercado.", "1", 2);
INSERT INTO produto (nome, descricao, nivel_instrucao_id, status_id) VALUES ("Graduação Tecnológica em Design Gráfico","Aprenda a planejar e executar a programação visual de jornais, revistas, livros e outros materiais impressos, produzir imagens, criar e editar infográficos, páginas e portais da internet e animações em meio digital.", "1", 2);
INSERT INTO produto (nome, descricao, nivel_instrucao_id, status_id) VALUES ("Graduação Tecnológica em Redes de Computadores","Aprenda a projetar sistemas de informação aplicando princípios de orientação a objetos e metodologia de desenvolvimento de sistemas existente no mercado.", "1", 2);
INSERT INTO produto (nome, descricao, nivel_instrucao_id, status_id) VALUES ("Graduação Tecnológica em Logística","Aprenda a gerenciar redes de distribuição e unidades logísticas, estabelecendo processos de compras, identificando fornecedores, negociando e estabelecendo padrões de recebimento, armazenamento, movimentação e embalagem de materiais.", "1", 2);
INSERT INTO produto (nome, descricao, nivel_instrucao_id, status_id) VALUES ("MBA em Gestão e Governança de TI","Curso destinado a profissionais que atuam ou pretendem atuar na gestão de equipes de TI com a utilização de práticas de governança alinhadas aos objetivos estratégicos de sua organização.", "3", 2);
INSERT INTO produto (nome, descricao, nivel_instrucao_id, status_id) VALUES ("Especialização em Big Data","Prepare-se para as transformações e impactos que o Big Data e os processos analíticos estão provocando nas organizações.", "3", 2);
INSERT INTO produto (nome, descricao, nivel_instrucao_id, status_id) VALUES ("Pós-Graduação em Engenharia de Software","Curso destinado a Desenvolvedores de Sistemas que tem objetivo de ampliar seus conhecimentos com o uso de metodologias ágeis.", "3", 2);
INSERT INTO produto (nome, descricao, nivel_instrucao_id, status_id) VALUES ("Técnico em Informática","Habilitação técnica que prepara o aluno para atuação tanto na área de desenvolvimento de sistemas e websites, como na operação de TI.", "2", 2);
INSERT INTO produto (nome, descricao, nivel_instrucao_id, status_id) VALUES ("Técnico em Design de Interiores","O projeto de interiores seja residencial ou comercial deve revelar identidade de seus proprietários ou se comuniquem com seus clientes. O Curso Técnico em Design de Interiores vai capacitar os alunos para resolver, com criatividade e técnica, problemas relacionados à funcionalidade, estética e qualidade do projeto.", "2", 2);
INSERT INTO produto (nome, descricao, nivel_instrucao_id, status_id) VALUES ("Técnico em Logística","Com duração de 13 meses, este curso proporciona aos seus concluintes a possibilidade de Registro no CRA – Conselho Regional de Administração. Este registro é obrigatório para os que pretendem concorrer a vagas de concurso público específicas para Técnicos. Já nas empresas privadas, o órgão busca obter reserva de mercado defendendo que apenas os registrados ocupem os cargos elegíveis. Para aproveitar todas as vantagens oferecidas pelo Conselho, enquanto ainda estiver cursando o Técnico, o aluno pode requerer gratuitamente a carteirinha de estudante do CRA.", "2", 2);

-- -----------------------------------------------------
-- Table `crm_senac`.`usuario` insert
-- -----------------------------------------------------
INSERT INTO usuario (nome, login, senha, cargo, status_id) VALUES ("Pedro", "205306", "234s", "Funcionário" , 2);
INSERT INTO usuario (nome, login, senha, cargo, status_id) VALUES ("João", "204302", "234p", "Funcionário" , 2);
INSERT INTO usuario (nome, login, senha, cargo, status_id) VALUES ("Kelly", "205306", "234p", "Cliente" , 2);
INSERT INTO usuario (nome, login, senha, cargo, status_id) VALUES ("Vitor", "105306", "234p", "Cliente" , 2);
INSERT INTO usuario (nome, login, senha, cargo, status_id) VALUES ("Fernanda", "202206", "334p", "Cliente" , 2);
INSERT INTO usuario (nome, login, senha, cargo, status_id) VALUES ("Vitor", "222206", "334h", "Cliente" , 2);
INSERT INTO usuario (nome, login, senha, cargo, status_id) VALUES ("Brenda", "222226", "334f", "Funcionário" , 2);
INSERT INTO usuario (nome, login, senha, cargo, status_id) VALUES ("Karla", "222222", "334g", "Cliente" , 2);
INSERT INTO usuario (nome, login, senha, cargo, status_id) VALUES ("Lucas", "222002", "Gh220", "Cliente" , 2);
INSERT INTO usuario (nome, login, senha, cargo, status_id) VALUES ("Marcelo", "212002", "Gh220", "Cliente" , 2);

-- -----------------------------------------------------
-- Table `crm_senac`.`oferta` insert
-- -----------------------------------------------------
INSERT INTO oferta (descricao, data_inicio, data_fim, preco, produto_id, status_id) VALUES ("2020.2", "2020-06-12", "2020-12-23", 699, 1, 2);
INSERT INTO oferta (descricao, data_inicio, data_fim, preco, produto_id, status_id) VALUES ("2020.1", "2020-01-12", "2020-06-23", 699, 2, 2);
INSERT INTO oferta (descricao, data_inicio, data_fim, preco, produto_id, status_id) VALUES ("2020.1", "2020-06-12", "2020-12-23", 699, 3, 2);
INSERT INTO oferta (descricao, data_inicio, data_fim, preco, produto_id, status_id) VALUES ("2020.1", "2020-06-12", "2020-12-23", 699, 4, 2);
INSERT INTO oferta (descricao, data_inicio, data_fim, preco, produto_id, status_id) VALUES ("2020.2", "2020-06-12", "2020-12-23", 699, 5, 2);
INSERT INTO oferta (descricao, data_inicio, data_fim, preco, produto_id, status_id) VALUES ("2020.1", "2020-06-12", "2020-12-23", 699, 6, 2);
INSERT INTO oferta (descricao, data_inicio, data_fim, preco, produto_id, status_id) VALUES ("2020.2", "2020-06-12", "2020-12-23", 699, 7, 2);
INSERT INTO oferta (descricao, data_inicio, data_fim, preco, produto_id, status_id) VALUES ("2020.1", "2020-06-12", "2020-12-23", 699, 8, 2);
INSERT INTO oferta (descricao, data_inicio, data_fim, preco, produto_id, status_id) VALUES ("2020.2", "2020-06-12", "2020-12-23", 699, 9, 2);
INSERT INTO oferta (descricao, data_inicio, data_fim, preco, produto_id, status_id) VALUES ("2020.1", "2020-06-12", "2020-12-23", 699, 10, 2);

-- -----------------------------------------------------
-- Table `crm_senac`.`acao` insert
-- -----------------------------------------------------
INSERT INTO acao (descricao, status_id) VALUES ("Contato pot telefone", 2);
INSERT INTO acao (descricao, status_id) VALUES ("Contato por e-mail", 2);
INSERT INTO acao (descricao, status_id) VALUES ("Contato pessoal", 2);
