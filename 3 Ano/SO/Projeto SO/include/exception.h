#pragma once
/**
 * \defgroup exception Exception
 * \brief The \c Exception class
 * \author Artur Pereira - 2022-2025
 */
#include <exception>

#include "errno.h"

/** @{ */

/**
 * \brief The \b somm exception class
 * \ingroup exception
 */
class Exception: public std::exception
{
  public:
    int en;                 ///< (system) error number
    const char *func;       ///< name of function that has thrown the exception
    char msg[100];          ///< buffer to store the exception message

    /**
     * \brief the constructor
     * \param _en (system) error number 
     * \param _func name of function throwing the exception
     */
     Exception(int _en, const char *_func);

    /**
     * \brief default exception message
     * \return pointer to exception message
     */
    const char *what() const throw();
};

/** @} */

