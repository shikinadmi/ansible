# ansible playbooks

## Playbooks
### tests
Создан для отладки plays и прочего, для быстрой отладки в docker localhost


```commandline
compose-db-1: The files belonging to this database system will be owned by user "postgres".
compose-db-1: This user must also own the server process.
compose-db-1: 
compose-db-1: The database cluster will be initialized with locale "en_US.utf8".
compose-db-1: The default database encoding has accordingly been set to "UTF8".
compose-db-1: The default text search configuration will be set to "english".
compose-db-1: 
compose-db-1: Data page checksums are disabled.
compose-db-1: 
compose-db-1: fixing permissions on existing directory /var/lib/postgresql/data ... ok
compose-db-1: creating subdirectories ... ok
compose-db-1: selecting dynamic shared memory implementation ... posix
compose-db-1: selecting default max_connections ... 100
compose-db-1: selecting default shared_buffers ... 128MB
compose-db-1: selecting default time zone ... Etc/UTC
compose-db-1: creating configuration files ... ok
compose-db-1: running bootstrap script ... ok
compose-db-1: performing post-bootstrap initialization ... ok
compose-db-1: syncing data to disk ... ok
compose-db-1: 
compose-db-1: 
compose-db-1: Success. You can now start the database server using:
compose-db-1: 
compose-db-1:     pg_ctl -D /var/lib/postgresql/data -l logfile start
compose-db-1: 
compose-db-1: initdb: warning: enabling "trust" authentication for local connections
compose-db-1: initdb: hint: You can change this by editing pg_hba.conf or using the option -A, or --auth-local and --auth-host, the next time you run initdb.
compose-db-1: waiting for server to start....2024-03-28 10:32:31.130 UTC [48] LOG:  starting PostgreSQL 15.6 (Debian 15.6-1.pgdg120+2) on aarch64-unknown-linux-gnu, compiled by gcc (Debian 12.2.0-14) 12.2.0, 64-bit
compose-db-1: 2024-03-28 10:32:31.131 UTC [48] LOG:  listening on Unix socket "/var/run/postgresql/.s.PGSQL.5432"
compose-db-1: 2024-03-28 10:32:31.134 UTC [51] LOG:  database system was shut down at 2024-03-28 10:32:31 UTC
compose-db-1: 2024-03-28 10:32:31.137 UTC [48] LOG:  database system is ready to accept connections
compose-db-1:  done
compose-db-1: server started
compose-playbook-1: 
compose-db-1: 
compose-playbook-1: PLAY [configurations] **********************************************************
compose-db-1: /usr/local/bin/docker-entrypoint.sh: ignoring /docker-entrypoint-initdb.d/*
compose-playbook-1: 
compose-db-1: 
compose-playbook-1: TASK [psql_pprb : Создание БД и назначение владельца схемы в БД pprb] **********
compose-db-1: waiting for server to shut down....2024-03-28 10:32:31.263 UTC [48] LOG:  received fast shutdown request
compose-playbook-1: changed: [local]
compose-db-1: 2024-03-28 10:32:31.264 UTC [48] LOG:  aborting any active transactions
compose-playbook-1: 
compose-db-1: 2024-03-28 10:32:31.264 UTC [48] LOG:  background worker "logical replication launcher" (PID 54) exited with exit code 1
compose-db-1: 2024-03-28 10:32:31.265 UTC [49] LOG:  shutting down
compose-playbook-1: TASK [psql_pprb : Проверка доступности БД  postgres:5432/pprb] *****************
compose-db-1: 2024-03-28 10:32:31.265 UTC [49] LOG:  checkpoint starting: shutdown immediate
compose-playbook-1: ok: [local]
compose-db-1: 2024-03-28 10:32:31.269 UTC [49] LOG:  checkpoint complete: wrote 3 buffers (0.0%); 0 WAL file(s) added, 0 removed, 0 recycled; write=0.002 s, sync=0.001 s, total=0.005 s; sync files=2, longest=0.001 s, average=0.001 s; distance=0 kB, estimate=0 kB
compose-playbook-1: 
compose-db-1: 2024-03-28 10:32:31.270 UTC [48] LOG:  database system is shut down
compose-playbook-1: TASK [psql_pprb : Доступность БД postgres:5432/pprb] ***************************
compose-db-1:  done
compose-playbook-1: ok: [local] => {
compose-db-1: server stopped
compose-playbook-1:     "msg": "Подключение к БД postgres:5432/pprb прошло успешно"
compose-db-1: 
compose-playbook-1: }
compose-db-1: PostgreSQL init process complete; ready for start up.
compose-playbook-1: 
compose-db-1: 
compose-playbook-1: TASK [psql_pprb : fail] ********************************************************
compose-db-1: 2024-03-28 10:32:31.376 UTC [1] LOG:  starting PostgreSQL 15.6 (Debian 15.6-1.pgdg120+2) on aarch64-unknown-linux-gnu, compiled by gcc (Debian 12.2.0-14) 12.2.0, 64-bit
compose-playbook-1: skipping: [local]
compose-db-1: 2024-03-28 10:32:31.376 UTC [1] LOG:  listening on IPv4 address "0.0.0.0", port 5432
compose-playbook-1: 
compose-db-1: 2024-03-28 10:32:31.376 UTC [1] LOG:  listening on IPv6 address "::", port 5432
compose-playbook-1: TASK [psql_pprb : Завершение сессий пользователя template_users к схеме template_users] ***
compose-db-1: 2024-03-28 10:32:31.377 UTC [1] LOG:  listening on Unix socket "/var/run/postgresql/.s.PGSQL.5432"
compose-db-1: 2024-03-28 10:32:31.379 UTC [62] LOG:  database system was shut down at 2024-03-28 10:32:31 UTC
compose-db-1: 2024-03-28 10:32:31.381 UTC [1] LOG:  database system is ready to accept connections
compose-db-1: 2024-03-28 10:32:37.181 UTC [89] ERROR:  schema "pprb_user_domainlogin" already exists
compose-db-1: 2024-03-28 10:32:37.181 UTC [89] STATEMENT:  CREATE SCHEMA pprb_user_domainlogin;
compose-db-1: 2024-03-28 10:32:37.497 UTC [91] ERROR:  schema "pprb_user_domainlogin" already exists
compose-db-1: 2024-03-28 10:32:37.497 UTC [91] STATEMENT:  CREATE SCHEMA pprb_user_domainlogin;
compose-db-1: 2024-03-28 10:32:38.567 UTC [60] LOG:  checkpoint starting: immediate force wait
compose-db-1: 2024-03-28 10:32:38.605 UTC [60] LOG:  checkpoint complete: wrote 927 buffers (5.7%); 1 WAL file(s) added, 0 removed, 0 recycled; write=0.010 s, sync=0.012 s, total=0.039 s; sync files=308, longest=0.002 s, average=0.001 s; distance=8455 kB, estimate=8455 kB
compose-db-1: 2024-03-28 10:32:38.898 UTC [60] LOG:  checkpoint starting: immediate force wait
compose-db-1: 2024-03-28 10:32:38.916 UTC [60] LOG:  checkpoint complete: wrote 919 buffers (5.6%); 0 WAL file(s) added, 0 removed, 1 recycled; write=0.007 s, sync=0.010 s, total=0.018 s; sync files=303, longest=0.001 s, average=0.001 s; distance=4226 kB, estimate=8032 kB
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Удаление Шаблонной Схемы template_users из БД pprb] **********
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Удаление пользователя template_users из БД pprb] *************
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Завершение сессий пользователя pprb_user_domainlogin к схеме pprb_user_domainlogin] ***
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Удаление Схемы pprb_user_domainlogin из БД pprb] *************
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Удаление пользователя pprb_user_domainlogin из БД pprb] ******
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Создание пользователя template_users в БД pprb] **************
compose-playbook-1: changed: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Создание схемы template_users, владелец template_users в БД pprb] ***
compose-playbook-1: changed: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Предоставление владения схемой template_users пользователю template_users] ***
compose-playbook-1: changed: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Alter role template_users with superuser] ********************
compose-playbook-1: changed: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Тестовое подключение к БД pprb для проверки создания Шаблонной Схемы и Пользователя Шаблонной Схемы] ***
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Создание пользователя pprb_user_domainlogin в БД pprb] *******
compose-playbook-1: changed: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Создание схемы pprb_user_domainlogin, владелец pprb_user_domainlogin в БД pprb] ***
compose-playbook-1: changed: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Предоставление прав владения схемой pprb_user_domainlogin пользователю pprb_user_domainlogin] ***
compose-playbook-1: changed: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Alter role pprb_user_domainlogin with superuser] *************
compose-playbook-1: changed: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Тестовое подключение к БД pprb для проверки создания Схемы и Пользователя] ***
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Тестовое подключение к БД pprb для проверки создания Шаблонной Схемы и Пользователя] ***
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Удаление старого Dump File Schema Template /database/schema/dump_template_schema.tar.gz] ***
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Создание каталога для Dump FIle Schema Template /database/schema] ***
compose-playbook-1: changed: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Создание Dump File Schema Template /database/schema/dump_template_schema.tar.gz] ***
compose-playbook-1: changed: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Тестовое подключение к БД pprb для проверки создания Схемы и Пользователя] ***
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Удаление старого Dump File Schema User /database/stand_pprb_user_domainlogin/dump_schema_pprb_user_domainlogin.tar.gz] ***
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Создание каталога для Dump FIle Schema User /database/stand_pprb_user_domainlogin] ***
compose-playbook-1: changed: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Создание Dump File Schema User /database/stand_pprb_user_domainlogin/dump_schema_pprb_user_domainlogin.tar.gz] ***
compose-playbook-1: changed: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Удаление старого Dump File БД /database/dump_pprb.tar.gz] ****
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Создание каталога для Dump FIle БД /database] ****************
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Создание Dump File БД /database/dump_pprb.tar.gz] ************
compose-playbook-1: changed: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Создание каталога щаблона для копии дампа пользователя] ******
compose-playbook-1: ok: [local] => (item=/database/schema)
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Копирование дампа пользователя в каталог щаблона если он существует] ***
compose-playbook-1: changed: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Создание каталога пользователя для копии дампа шаблона] ******
compose-playbook-1: ok: [local] => (item=/database/schema)
compose-playbook-1: ok: [local] => (item=/database/stand_pprb_user_domainlogin)
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Копирование дампа шаблона в каталог пользователя если он существует] ***
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Поиск устаревших файлов дампов схем пользователя /database/stand_pprb_user_domainlogin] ***
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : debug] *******************************************************
compose-playbook-1: ok: [local] => {
compose-playbook-1:     "dump_files": {
compose-playbook-1:         "changed": false,
compose-playbook-1:         "examined": 1,
compose-playbook-1:         "failed": false,
compose-playbook-1:         "files": [],
compose-playbook-1:         "matched": 0,
compose-playbook-1:         "msg": "All paths examined",
compose-playbook-1:         "skipped_paths": {}
compose-playbook-1:     }
compose-playbook-1: }
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Удаление устаревших файлов дампов схем пользователя /database/stand_pprb_user_domainlogin] ***
compose-playbook-1: skipping: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Восстановление Шаблонной схемы из файла дампа /database/schema/dump_template_schema.tar.gz] ***
compose-playbook-1: changed: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Выдача прав Шаблонному пользователю template_users с привелегиями ALL на схему template_users] ***
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Восстановление пользовательной схемы из файла дампа шаблона /database/schema/dump_template_schema.tar.gz] ***
compose-playbook-1: skipping: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Выдача прав пользователю pprb_user_domainlogin с привелегиями ALL на схему pprb_user_domainlogin] ***
compose-playbook-1: skipping: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Восстановление пользовательной схемы из файла дампа /database/stand_pprb_user_domainlogin/dump_schema_pprb_user_domainlogin.tar.gz] ***
compose-playbook-1: changed: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Выдача прав пользователю pprb_user_domainlogin с привелегиями ALL на схему pprb_user_domainlogin] ***
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Проверка сушествования файла дампа БД для дальнейшего использования в восстановлении] ***
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : fail] ********************************************************
compose-playbook-1: skipping: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Создание БД pprb_testrestoredump] ****************************
compose-playbook-1: changed: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Восстановление БД pprb_testrestoredump] **********************
compose-playbook-1: changed: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Проверка доступности БД pprb_testrestoredump] ****************
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Закрытие всех сессий] ****************************************
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Вывод результата закрытия всех сессий] ***********************
compose-playbook-1: ok: [local] => {
compose-playbook-1:     "msg": {
compose-playbook-1:         "changed": false,
compose-playbook-1:         "failed": false,
compose-playbook-1:         "query": "SELECT pg_terminate_backend(pg_stat_activity.pid) FROM pg_stat_activity WHERE pg_stat_activity.datname = 'pprb' AND pid <> pg_backend_pid();",
compose-playbook-1:         "query_all_results": [
compose-playbook-1:             {}
compose-playbook-1:         ],
compose-playbook-1:         "query_list": [
compose-playbook-1:             "SELECT pg_terminate_backend(pg_stat_activity.pid) FROM pg_stat_activity WHERE pg_stat_activity.datname = 'pprb' AND pid <> pg_backend_pid();"
compose-playbook-1:         ],
compose-playbook-1:         "query_result": {},
compose-playbook-1:         "rowcount": 0,
compose-playbook-1:         "statusmessage": "SELECT 0"
compose-playbook-1:     }
compose-playbook-1: }
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Удаление БД pprb] ********************************************
compose-playbook-1: changed: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Восстановление всей БД] **************************************
compose-playbook-1: changed: [local]
compose-playbook-1: 
compose-playbook-1: TASK [psql_pprb : Удаление БД pprb_testrestoredump] ****************************
compose-playbook-1: changed: [local]
compose-playbook-1: 
compose-playbook-1: TASK [sys_info : Gather system facts] ******************************************
compose-playbook-1: ok: [local]
compose-playbook-1: 
compose-playbook-1: TASK [sys_info : Display CPU information] **************************************
compose-playbook-1: ok: [local] => {
compose-playbook-1:     "system_facts.ansible_cpu": "VARIABLE IS NOT DEFINED!"
compose-playbook-1: }
compose-playbook-1: 
compose-playbook-1: TASK [sys_info : Display memory information] ***********************************
compose-playbook-1: ok: [local] => {
compose-playbook-1:     "system_facts.ansible_memtotal_mb": "VARIABLE IS NOT DEFINED!"
compose-playbook-1: }
compose-playbook-1: 
compose-playbook-1: TASK [sys_info : Display disk information] *************************************
compose-playbook-1: ok: [local] => {
compose-playbook-1:     "system_facts.ansible_facts.disks": "VARIABLE IS NOT DEFINED!"
compose-playbook-1: }
compose-playbook-1: 
compose-playbook-1: TASK [sys_info : Display network information] **********************************
compose-playbook-1: ok: [local] => {
compose-playbook-1:     "system_facts.ansible_facts.networks": "VARIABLE IS NOT DEFINED!"
compose-playbook-1: }
compose-playbook-1: 
compose-playbook-1: RUNNING HANDLER [psql_pprb : create_template_file] *****************************
compose-playbook-1: ok: [local] => {
compose-playbook-1:     "msg": "Создание шаблона БД /database/schema/dump_template_schema.tar.gz прошло успешно"
compose-playbook-1: }
compose-playbook-1: 
compose-playbook-1: PLAY RECAP *********************************************************************
compose-playbook-1: local                      : ok=55   changed=22   unreachable=0    failed=0    skipped=5    rescued=0    ignored=0   
compose-playbook-1: 

```