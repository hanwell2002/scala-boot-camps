-- public.employee definition

-- DROP TABLE public.employee;
DROP  TABLE IF EXISTS  public.employee;

CREATE TABLE  public.employee (
	emp_id serial PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	email varchar NULL,
	phone varchar(20) NULL
);

--TRUNCATE TABLE  public.employee
DELETE FROM public.employee;

INSERT INTO public.employee (first_name,last_name,email,phone) VALUES
	 ('James','Bond','james.bond@007.com','(+44)020-7493-7953'),
	 ('Barack','Obama','obama.library@nara.gov','(847) 252-5700'),
	 ('Joe','Biden','geoge.biden@whitehouse.com','+1(202) 456-1111');

COMMIT;

