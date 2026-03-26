-- 创建pass表，存储用户信息
CREATE TABLE IF NOT EXISTS pass (
    id VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    state VARCHAR(20) NOT NULL
);

-- 创建text表，存储文本信息
CREATE TABLE IF NOT EXISTS text (
    id INT AUTO_INCREMENT PRIMARY KEY,
    text TEXT NOT NULL,
    date DATE NOT NULL,
    state VARCHAR(20) NOT NULL
);

-- 插入测试数据
INSERT INTO pass (id, password, role, state) VALUES
('1001', '123456', '部门', '正常'),
('1002', '123456', '办公室', '正常'),
('1003', '123456', '副厂长', '正常'),
('1004', '123456', '厂长', '正常'),
('1005', '123456', '系统管理', '正常');
