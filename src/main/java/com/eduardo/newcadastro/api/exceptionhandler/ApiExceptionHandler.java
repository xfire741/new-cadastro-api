package com.eduardo.newcadastro.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.eduardo.newcadastro.domain.exception.ChangePersonEmailOwnerException;
import com.eduardo.newcadastro.domain.exception.EntityInUseException;
import com.eduardo.newcadastro.domain.exception.EntityNotFoundException;
import com.eduardo.newcadastro.domain.exception.InvalidCepException;
import com.eduardo.newcadastro.domain.exception.InvalidLoginException;
import com.eduardo.newcadastro.domain.exception.InvalidPhoneTypeException;
import com.eduardo.newcadastro.domain.exception.PersonAlreadyHaveAddressException;
import com.eduardo.newcadastro.domain.exception.UserWithSameLoginException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	

	private static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Erro no sistema, por favor tente novamente mais tarde. "
			+ "Se o erro persistir entre em contato com o administrador do sistema";

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<?> handleEntityNotFoundException(
			EntityNotFoundException e, WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail, OffsetDateTime.now()).userMessage(detail).build();
		
		return handleExceptionInternal(e, problem, 
				new HttpHeaders(), status, request);

	}
	
	@ExceptionHandler(PersonAlreadyHaveAddressException.class)
	protected ResponseEntity<?> handlePersonAlreadyHaveAddressException(PersonAlreadyHaveAddressException e,
			WebRequest request) {
		
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.USUARIO_COM_ENDERECO_EXISTENTE;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail, OffsetDateTime.now()).userMessage(detail).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
		
	}
	
	@ExceptionHandler(InvalidLoginException.class)
	protected ResponseEntity<?> handleInvalidLoginException(InvalidLoginException e,
			WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.DADOS_INVALIDOS;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail, OffsetDateTime.now()).userMessage(detail).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
		
	}
	
	@ExceptionHandler(UserWithSameLoginException.class)
	protected ResponseEntity<?> handleUserWithSameLoginException(UserWithSameLoginException e,
			WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.DADOS_INVALIDOS;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail, OffsetDateTime.now()).userMessage(detail).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
		
	}
	
	@ExceptionHandler(InvalidCepException.class)
	protected ResponseEntity<?> handlePersonAlreadyHaveAddressException(InvalidCepException e,
			WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.DADOS_INVALIDOS;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail, OffsetDateTime.now()).userMessage(detail).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
		
	}
	
	@ExceptionHandler(InvalidPhoneTypeException.class)
	protected ResponseEntity<?> handleInvalidPhoneTypeException(InvalidPhoneTypeException e,
			WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.TIPO_DE_TELEFONE_INVALIDO;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail, OffsetDateTime.now()).userMessage(detail).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
		
	}
	
	@ExceptionHandler(ChangePersonEmailOwnerException.class)
	protected ResponseEntity<?> handleInvalidPhoneTypeException(ChangePersonEmailOwnerException e,
			WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.MUDAR_DONO_DE_EMAIL;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail, OffsetDateTime.now()).userMessage(detail).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
		
	}
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, 
			ProblemType problemType, String detail, OffsetDateTime timestamp) {
		return Problem.builder()
				.timestamp(timestamp)
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.detail(detail);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if(body == null) {
			body = Problem.builder()
					.timestamp(OffsetDateTime.now())
				.title(status.getReasonPhrase())
				.status(status.value())
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();
		} else if (body instanceof String) {
			body = Problem.builder()
					.timestamp(OffsetDateTime.now())
					.title((String) body)
					.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
					.status(status.value())
					.build();
		}
			
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleUncaughtzs(EntityInUseException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ProblemType problemType = ProblemType.ERRO_SISTEMA;
		
		
		String detail = MSG_ERRO_GENERICA_USUARIO_FINAL;
		
		ex.printStackTrace();
		
		Problem problem = createProblemBuilder(status, problemType, detail, OffsetDateTime.now()).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
	}
	
	@ExceptionHandler(EntityInUseException.class)
	protected ResponseEntity<?> handleEntityInUseException(EntityInUseException e, WebRequest request) {
		
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail, OffsetDateTime.now()).userMessage(detail).build();
		
		return handleExceptionInternal(e, problem, 
				new HttpHeaders(), status, request);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request, ex.getBindingResult());
	}
	
	private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult,
			HttpHeaders headers, HttpStatus status, WebRequest request, BindingResult bindResult) {
		ProblemType problemType = ProblemType.DADOS_INVALIDOS;
		String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
		
		List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream()
		        .map(objectError -> {
		            String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
		            
		            String name = objectError.getObjectName();
		            
		            if (objectError instanceof FieldError) {
		                name = ((FieldError) objectError).getField();
		            }
		            
		            return Problem.Object.builder()
		                .name(name)
		                .userMessage(message)
		                .build();
		        })
		        .collect(Collectors.toList());
		
		Problem problem = createProblemBuilder(status, problemType, detail, OffsetDateTime.now())
		    .userMessage(detail)
		    .objects(problemObjects)
		    .build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
}
